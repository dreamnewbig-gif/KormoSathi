package com.kormosathi.app.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.kormosathi.app.model.SavedService
import kotlinx.coroutines.tasks.await

class SavedServiceRepository {

    private val firestore = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()

    suspend fun saveService(ServiceId: String): Result<String> {
        return try {
            val userUid = auth.currentUser?.uid
                ?: return Result.failure(Exception("User not logged in"))

            val saveId = firestore.collection("saved_Services").document().id
            val savedService = SavedService(
                saveId = saveId,
                userUid = userUid,
                ServiceId = ServiceId,
                savedAt = System.currentTimeMillis()
            )

            firestore.collection("saved_Services")
                .document(saveId)
                .set(savedService)
                .await()

            Result.success(saveId)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun unsaveService(ServiceId: String): Result<Unit> {
        return try {
            val userUid = auth.currentUser?.uid
                ?: return Result.failure(Exception("User not logged in"))

            val saved = firestore.collection("saved_Services")
                .whereEqualTo("userUid", userUid)
                .whereEqualTo("ServiceId", ServiceId)
                .get()
                .await()

            for (doc in saved.documents) {
                firestore.collection("saved_Services").document(doc.id).delete().await()
            }

            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getSavedServices(): List<SavedService> {
        return try {
            val userUid = auth.currentUser?.uid ?: return emptyList()

            firestore.collection("saved_Services")
                .whereEqualTo("userUid", userUid)
                .orderBy("savedAt", com.google.firebase.firestore.Query.Direction.DESCENDING)
                .get()
                .await()
                .documents
                .mapNotNull { it.toObject(SavedService::class.java) }
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun isServiceSaved(ServiceId: String): Boolean {
        return try {
            val userUid = auth.currentUser?.uid ?: return false

            val result = firestore.collection("saved_Services")
                .whereEqualTo("userUid", userUid)
                .whereEqualTo("ServiceId", ServiceId)
                .get()
                .await()

            !result.isEmpty
        } catch (e: Exception) {
            false
        }
    }
}
