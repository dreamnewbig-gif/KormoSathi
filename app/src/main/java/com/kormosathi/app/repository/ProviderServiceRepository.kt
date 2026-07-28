package com.kormosathi.app.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.kormosathi.app.model.ProviderService
import kotlinx.coroutines.tasks.await

class ProviderServiceRepository {

    private val db = FirebaseFirestore.getInstance()

    suspend fun saveService(service: ProviderService): Boolean {
        return try {
            db.collection("provider_services")
                .document(service.id)
                .set(service)
                .await()
            true
        } catch (e: Exception) {
            false
        }
    }

    suspend fun getProviderServices(providerId: String): List<ProviderService> {
        return try {
            db.collection("provider_services")
                .whereEqualTo("providerId", providerId)
                .get()
                .await()
                .toObjects(ProviderService::class.java)
        } catch (e: Exception) {
            emptyList()
        }
    }
}