package com.kormosathi.app.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.kormosathi.app.model.Employer
import com.kormosathi.app.model.Job
import com.kormosathi.app.model.JobApplication
import kotlinx.coroutines.tasks.await

class EmployerRepository {

    private val firestore = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()

    suspend fun createEmployerProfile(employer: Employer): Result<String> {
        return try {
            val uid = auth.currentUser?.uid
                ?: return Result.failure(Exception("User not logged in"))

            val employerId = firestore.collection("employers").document().id
            val employerWithId = employer.copy(employerId = employerId, uid = uid)

            firestore.collection("employers")
                .document(employerId)
                .set(employerWithId)
                .await()

            Result.success(employerId)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getEmployerProfile(): Employer? {
        return try {
            val uid = auth.currentUser?.uid ?: return null

            val snapshot = firestore.collection("employers")
                .whereEqualTo("uid", uid)
                .get()
                .await()

            snapshot.documents.firstOrNull()?.toObject(Employer::class.java)
        } catch (e: Exception) {
            null
        }
    }

    suspend fun getEmployerById(employerId: String): Employer? {
        return try {
            firestore.collection("employers")
                .document(employerId)
                .get()
                .await()
                .toObject(Employer::class.java)
        } catch (e: Exception) {
            null
        }
    }

    suspend fun updateEmployerProfile(employer: Employer): Result<Unit> {
        return try {
            firestore.collection("employers")
                .document(employer.employerId)
                .update(
                    mapOf(
                        "companyName" to employer.companyName,
                        "ownerName" to employer.ownerName,
                        "phone" to employer.phone,
                        "email" to employer.email,
                        "address" to employer.address,
                        "businessType" to employer.businessType
                    )
                )
                .await()

            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun postJob(job: Job): Result<String> {
        return try {
            val employer = getEmployerProfile()
                ?: return Result.failure(Exception("Employer profile not found"))

            val jobId = firestore.collection("jobs").document().id
            val jobWithId = job.copy(
                jobId = jobId,
                employerUid = auth.currentUser?.uid ?: "",
                employerName = employer.companyName
            )

            firestore.collection("jobs")
                .document(jobId)
                .set(jobWithId)
                .await()

            Result.success(jobId)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun editJob(job: Job): Result<Unit> {
        return try {
            firestore.collection("jobs")
                .document(job.jobId)
                .update(
                    mapOf(
                        "title" to job.title,
                        "description" to job.description,
                        "category" to job.category,
                        "district" to job.district,
                        "block" to job.block,
                        "salary" to job.salary
                    )
                )
                .await()

            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun deleteJob(jobId: String): Result<Unit> {
        return try {
            // Delete all applications for this job first
            val applications = firestore.collection("applications")
                .whereEqualTo("jobId", jobId)
                .get()
                .await()

            for (doc in applications.documents) {
                firestore.collection("applications").document(doc.id).delete().await()
            }

            // Then delete the job
            firestore.collection("jobs")
                .document(jobId)
                .delete()
                .await()

            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getEmployerJobs(): List<Job> {
        return try {
            val uid = auth.currentUser?.uid ?: return emptyList()

            firestore.collection("jobs")
                .whereEqualTo("employerUid", uid)
                .orderBy("createdAt", com.google.firebase.firestore.Query.Direction.DESCENDING)
                .get()
                .await()
                .documents
                .mapNotNull { it.toObject(Job::class.java) }
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun getJobApplicants(jobId: String): List<JobApplication> {
        return try {
            firestore.collection("applications")
                .whereEqualTo("jobId", jobId)
                .orderBy("appliedAt", com.google.firebase.firestore.Query.Direction.DESCENDING)
                .get()
                .await()
                .documents
                .mapNotNull { it.toObject(JobApplication::class.java) }
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun acceptApplicant(applicationId: String): Result<Unit> {
        return try {
            firestore.collection("applications")
                .document(applicationId)
                .update("status", "accepted")
                .await()

            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun rejectApplicant(applicationId: String): Result<Unit> {
        return try {
            firestore.collection("applications")
                .document(applicationId)
                .update("status", "rejected")
                .await()

            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
