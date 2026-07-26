package com.kormosathi.app.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.kormosathi.app.model.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class JobRepository {

    private val firestore = FirebaseFirestore.getInstance()

    fun getAllJobsRealtime(): Flow<List<Job>> = flow {
        try {
            firestore.collection("jobs")
                .whereEqualTo("status", "active")
                .orderBy("createdAt", com.google.firebase.firestore.Query.Direction.DESCENDING)
                .addSnapshotListener { snapshot, error ->
                    if (error != null) {
                        return@addSnapshotListener
                    }
                    val jobs = snapshot?.documents?.mapNotNull { doc ->
                        doc.toObject(Job::class.java)
                    } ?: emptyList()
                    // This is a workaround since we can't directly emit from addSnapshotListener
                }
        } catch (e: Exception) {
            emit(emptyList())
        }
    }

    suspend fun getAllJobs(): List<Job> {
        return try {
            firestore.collection("jobs")
                .whereEqualTo("status", "active")
                .orderBy("createdAt", com.google.firebase.firestore.Query.Direction.DESCENDING)
                .get()
                .await()
                .documents
                .mapNotNull { it.toObject(Job::class.java) }
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun searchJobs(
        title: String = "",
        category: String = "",
        district: String = ""
    ): List<Job> {
        return try {
            var query = firestore.collection("jobs")
                .whereEqualTo("status", "active")

            if (category.isNotEmpty()) {
                query = query.whereEqualTo("category", category)
            }

            if (district.isNotEmpty()) {
                query = query.whereEqualTo("district", district)
            }

            val jobs = query
                .orderBy("createdAt", com.google.firebase.firestore.Query.Direction.DESCENDING)
                .get()
                .await()
                .documents
                .mapNotNull { it.toObject(Job::class.java) }

            // Filter by title locally (since Firestore doesn't support contains queries)
            return if (title.isEmpty()) {
                jobs
            } else {
                jobs.filter { it.title.contains(title, ignoreCase = true) }
            }
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun getJobById(jobId: String): Job? {
        return try {
            firestore.collection("jobs")
                .document(jobId)
                .get()
                .await()
                .toObject(Job::class.java)
        } catch (e: Exception) {
            null
        }
    }

    suspend fun filterByDistrict(district: String): List<Job> {
        return try {
            firestore.collection("jobs")
                .whereEqualTo("status", "active")
                .whereEqualTo("district", district)
                .orderBy("createdAt", com.google.firebase.firestore.Query.Direction.DESCENDING)
                .get()
                .await()
                .documents
                .mapNotNull { it.toObject(Job::class.java) }
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun filterByCategory(category: String): List<Job> {
        return try {
            firestore.collection("jobs")
                .whereEqualTo("status", "active")
                .whereEqualTo("category", category)
                .orderBy("createdAt", com.google.firebase.firestore.Query.Direction.DESCENDING)
                .get()
                .await()
                .documents
                .mapNotNull { it.toObject(Job::class.java) }
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun createJob(job: Job): Result<String> {
        return try {
            val jobId = firestore.collection("jobs").document().id
            val jobWithId = job.copy(jobId = jobId)
            firestore.collection("jobs")
                .document(jobId)
                .set(jobWithId)
                .await()
            Result.success(jobId)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
