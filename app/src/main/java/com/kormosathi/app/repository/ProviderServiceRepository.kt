package com.kormosathi.app.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.kormosathi.app.model.ProviderService
import kotlinx.coroutines.tasks.await

class ProviderServiceRepository {

    private val db = FirebaseFirestore.getInstance()

    suspend fun addProviderService(
        providerService: ProviderService
    ): Result<Unit> {

        return try {

            db.collection("provider_services")
                .document(providerService.id)
                .set(providerService)
                .await()

            Result.success(Unit)

        } catch (e: Exception) {

            Result.failure(e)

        }

    }

    suspend fun updateProviderService(
        providerService: ProviderService
    ): Result<Unit> {

        return try {

            db.collection("provider_services")
                .document(providerService.id)
                .set(providerService)
                .await()

            Result.success(Unit)

        } catch (e: Exception) {

            Result.failure(e)

        }

    }

    suspend fun getProviderServices(
        providerId: String
    ): List<ProviderService> {

        return try {

            db.collection("provider_services")
                .whereEqualTo("providerId", providerId)
                .whereEqualTo("isActive", true)
                .get()
                .await()
                .toObjects(ProviderService::class.java)

        } catch (e: Exception) {

            emptyList()

        }

    }

    suspend fun getServiceProviders(
        serviceId: String
    ): List<ProviderService> {

        return try {

            db.collection("provider_services")
                .whereEqualTo("serviceId", serviceId)
                .whereEqualTo("isActive", true)
                .get()
                .await()
                .toObjects(ProviderService::class.java)

        } catch (e: Exception) {

            emptyList()

        }

    }

    suspend fun deleteProviderService(
        id: String
    ): Result<Unit> {

        return try {

            db.collection("provider_services")
                .document(id)
                .delete()
                .await()

            Result.success(Unit)

        } catch (e: Exception) {

            Result.failure(e)

        }

    }

}