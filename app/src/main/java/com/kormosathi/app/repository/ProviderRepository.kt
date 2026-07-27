package com.kormosathi.app.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.kormosathi.app.model.Provider
import com.kormosathi.app.model.Service
import com.kormosathi.app.model.Booking
import kotlinx.coroutines.tasks.await

class ProviderRepository {

    private val firestore = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()

    suspend fun createProviderProfile(provider: Provider): Result<String> {
        return try {
            val uid = auth.currentUser?.uid
                ?: return Result.failure(Exception("User not logged in"))

            val ProviderId = firestore.collection("Providers").document().id
            val ProviderWithId = provider.copy(ProviderId = ProviderId, uid = uid)

            firestore.collection("Providers")
                .document(ProviderId)
                .set(ProviderWithId)
                .await()

            Result.success(ProviderId)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getProviderProfile(): Provider? {
        return try {
            val uid = auth.currentUser?.uid ?: return null

            val snapshot = firestore.collection("Providers")
                .whereEqualTo("uid", uid)
                .get()
                .await()

            snapshot.documents.firstOrNull()?.toObject(Provider::class.java)
        } catch (e: Exception) {
            null
        }
    }

    suspend fun getProviderById(ProviderId: String): Provider? {
        return try {
            firestore.collection("Providers")
                .document(ProviderId)
                .get()
                .await()
                .toObject(Provider::class.java)
        } catch (e: Exception) {
            null
        }
    }

    suspend fun updateProviderProfile(provider: Provider): Result<Unit> {
        return try {
            firestore.collection("Providers")
                .document(provider.ProviderId)
                .update(
                    mapOf(
                        "companyName" to provider.companyName,
                        "ownerName" to provider.ownerName,
                        "phone" to provider.phone,
                        "email" to provider.email,
                        "address" to provider.address,
                        "businessType" to provider.businessType
                    )
                )
                .await()

            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun postService(service: Service): Result<String> {
        return try {
            val Provider = getProviderProfile()
                ?: return Result.failure(Exception("Provider profile not found"))

            val ServiceId = firestore.collection("Services").document().id
            val ServiceWithId = service.copy(
                ServiceId = ServiceId,
                ProviderUid = auth.currentUser?.uid ?: "",
                ProviderName = Provider.companyName
            )

            firestore.collection("Services")
                .document(ServiceId)
                .set(ServiceWithId)
                .await()

            Result.success(ServiceId)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun editService(service: Service): Result<Unit> {
        return try {
            firestore.collection("Services")
                .document(service.ServiceId)
                .update(
                    mapOf(
                        "title" to service.title,
                        "description" to service.description,
                        "category" to service.category,
                        "district" to service.district,
                        "block" to service.block,
                        "salary" to service.salary
                    )
                )
                .await()

            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun deleteService(ServiceId: String): Result<Unit> {
        return try {
            // Delete all Bookings for this Service first
            val Bookings = firestore.collection("Bookings")
                .whereEqualTo("ServiceId", ServiceId)
                .get()
                .await()

            for (doc in Bookings.documents) {
                firestore.collection("Bookings").document(doc.id).delete().await()
            }

            // Then delete the Service
            firestore.collection("Services")
                .document(ServiceId)
                .delete()
                .await()

            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getProviderServices(): List<Service> {
        return try {
            val uid = auth.currentUser?.uid ?: return emptyList()

            firestore.collection("Services")
                .whereEqualTo("ProviderUid", uid)
                .orderBy("createdAt", com.google.firebase.firestore.Query.Direction.DESCENDING)
                .get()
                .await()
                .documents
                .mapNotNull { it.toObject(Service::class.java) }
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun getServiceApplicants(ServiceId: String): List<Booking> {
        return try {
            firestore.collection("Bookings")
                .whereEqualTo("ServiceId", ServiceId)
                .orderBy("appliedAt", com.google.firebase.firestore.Query.Direction.DESCENDING)
                .get()
                .await()
                .documents
                .mapNotNull { it.toObject(Booking::class.java) }
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun acceptApplicant(BookingId: String): Result<Unit> {
        return try {
            firestore.collection("Bookings")
                .document(BookingId)
                .update("status", "accepted")
                .await()

            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun rejectApplicant(BookingId: String): Result<Unit> {
        return try {
            firestore.collection("Bookings")
                .document(BookingId)
                .update("status", "rejected")
                .await()

            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
