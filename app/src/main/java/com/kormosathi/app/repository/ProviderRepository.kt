package com.kormosathi.app.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.kormosathi.app.model.Provider
import kotlinx.coroutines.tasks.await

class ProviderRepository {

    private val db = FirebaseFirestore.getInstance()

    suspend fun createProvider(provider: Provider): Result<Unit> {
        return try {
            db.collection("providers")
                .document(provider.id)
                .set(provider)
                .await()

            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun updateProvider(provider: Provider): Result<Unit> {
        return try {
            db.collection("providers")
                .document(provider.id)
                .set(provider)
                .await()

            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getProvider(providerId: String): Provider? {
        return try {
            db.collection("providers")
                .document(providerId)
                .get()
                .await()
                .toObject(Provider::class.java)
        } catch (e: Exception) {
            null
        }
    }

    suspend fun getApprovedProviders(): List<Provider> {
        return try {
            db.collection("providers")
                .whereEqualTo("isApproved", true)
                .whereEqualTo("isAvailable", true)
                .get()
                .await()
                .toObjects(Provider::class.java)
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun updateAvailability(
        providerId: String,
        available: Boolean
    ): Result<Unit> {

        return try {

            db.collection("providers")
                .document(providerId)
                .update("isAvailable", available)
                .await()

            Result.success(Unit)

        } catch (e: Exception) {

            Result.failure(e)

        }
    }
}