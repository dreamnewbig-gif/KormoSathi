package com.kormosathi.app.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.kormosathi.app.model.Provider
import kotlinx.coroutines.tasks.await

class ProviderRepository {

    private val db =
        FirebaseFirestore.getInstance()


    suspend fun saveProvider(
        provider: Provider
    ): Result<Boolean> {

        return try {

            db.collection("providers")
                .document(provider.id)
                .set(provider)
                .await()

            Result.success(true)

        } catch (e: Exception) {

            Result.failure(e)

        }
    }


    suspend fun getProviderById(
        providerId: String
    ): Provider? {

        return try {

            val document =
                db.collection("providers")
                    .document(providerId)
                    .get()
                    .await()

            document.toObject(
                Provider::class.java
            )

        } catch (e: Exception) {

            null

        }
    }


    suspend fun getProvidersByService(
        serviceId: String
    ): List<Provider> {

        return try {

            val snapshot =
                db.collection("providers")
                    .whereArrayContains(
                        "serviceItemIds",
                        serviceId
                    )
                    .get()
                    .await()

            snapshot.documents.mapNotNull {

                it.toObject(
                    Provider::class.java
                )

            }

        } catch (e: Exception) {

            emptyList()

        }
    }
}