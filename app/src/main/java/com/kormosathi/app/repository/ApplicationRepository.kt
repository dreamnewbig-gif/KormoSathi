package com.kormosathi.app.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.kormosathi.app.model.JobApplication
import kotlinx.coroutines.tasks.await

class ApplicationRepository {

    private val firestore = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()

    suspend fun applyForJob(
        jobId: String,
        applicantName: String,
        phone: String
    ): Result<String> {
        return try {
            val applicantUid = auth.currentUser?.uid
                ?: return Result.failure(Exception("User not logged in"))

            // Check if user already applied for this job
            val existingApplication = firestore.collection("applications")
                .whereEqualTo("jobId", jobId)
                .whereEqualTo("applicantUid", applicantUid)
                .get()
                .await()

            if (!existingApplication.isEmpty) {
                return Result.failure(Exception("Already applied for this job"))
            }

            val applicationId = firestore.collection("applications").document().id
            val application = JobApplication(
                applicationId = applicationId,
                jobId = jobId,
                applicantUid = applicantUid,
                applicantName = applicantName,
                phone = phone,
                appliedAt = System.currentTimeMillis(),
                status = "pending"
            )

            firestore.collection("applications")
                .document(applicationId)
                .set(application)
                .await()

            Result.success(applicationId)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getMyApplications(): List<JobApplication> {
        return try {
            val applicantUid = auth.currentUser?.uid
                ?: return emptyList()

            firestore.collection("applications")
                .whereEqualTo("applicantUid", applicantUid)
                .orderBy("appliedAt", com.google.firebase.firestore.Query.Direction.DESCENDING)
                .get()
                .await()
                .documents
                .mapNotNull { it.toObject(JobApplication::class.java) }
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun getApplicationsForJob(jobId: String): List<JobApplication> {
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

    suspend fun hasUserApplied(jobId: String): Boolean {
        return try {
            val applicantUid = auth.currentUser?.uid ?: return false

            val result = firestore.collection("applications")
                .whereEqualTo("jobId", jobId)
                .whereEqualTo("applicantUid", applicantUid)
                .get()
                .await()

            !result.isEmpty
        } catch (e: Exception) {
            false
        }
    }

    suspend fun updateApplicationStatus(
        applicationId: String,
        status: String
    ): Result<Unit> {
        return try {
            firestore.collection("applications")
                .document(applicationId)
                .update("status", status)
                .await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
