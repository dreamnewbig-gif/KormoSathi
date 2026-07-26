package com.kormosathi.app.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.kormosathi.app.model.SavedJob
import kotlinx.coroutines.tasks.await

class SavedJobRepository {

    private val firestore = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()

    suspend fun saveJob(jobId: String): Result<String> {
        return try {
            val userUid = auth.currentUser?.uid
                ?: return Result.failure(Exception("User not logged in"))

            val saveId = firestore.collection("saved_jobs").document().id
            val savedJob = SavedJob(
                saveId = saveId,
                userUid = userUid,
                jobId = jobId,
                savedAt = System.currentTimeMillis()
            )

            firestore.collection("saved_jobs")
                .document(saveId)
                .set(savedJob)
                .await()

            Result.success(saveId)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun unsaveJob(jobId: String): Result<Unit> {
        return try {
            val userUid = auth.currentUser?.uid
                ?: return Result.failure(Exception("User not logged in"))

            val saved = firestore.collection("saved_jobs")
                .whereEqualTo("userUid", userUid)
                .whereEqualTo("jobId", jobId)
                .get()
                .await()

            for (doc in saved.documents) {
                firestore.collection("saved_jobs").document(doc.id).delete().await()
            }

            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getSavedJobs(): List<SavedJob> {
        return try {
            val userUid = auth.currentUser?.uid ?: return emptyList()

            firestore.collection("saved_jobs")
                .whereEqualTo("userUid", userUid)
                .orderBy("savedAt", com.google.firebase.firestore.Query.Direction.DESCENDING)
                .get()
                .await()
                .documents
                .mapNotNull { it.toObject(SavedJob::class.java) }
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun isJobSaved(jobId: String): Boolean {
        return try {
            val userUid = auth.currentUser?.uid ?: return false

            val result = firestore.collection("saved_jobs")
                .whereEqualTo("userUid", userUid)
                .whereEqualTo("jobId", jobId)
                .get()
                .await()

            !result.isEmpty
        } catch (e: Exception) {
            false
        }
    }
}
