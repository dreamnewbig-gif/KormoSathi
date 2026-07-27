package com.kormosathi.app.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.kormosathi.app.model.Service
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class ServiceRepository {

    private val firestore = FirebaseFirestore.getInstance()

    fun getAllServicesRealtime(): Flow<List<Service>> = flow {
        try {
            firestore.collection("Services")
                .whereEqualTo("status", "active")
                .orderBy("createdAt", com.google.firebase.firestore.Query.Direction.DESCENDING)
                .addSnapshotListener { snapshot, error ->
                    if (error != null) {
                        return@addSnapshotListener
                    }
                    val services = snapshot?.documents?.mapNotNull { doc ->
                        doc.toObject(Service::class.java)
                    } ?: emptyList()
                    // This is a workaround since we can't directly emit from addSnapshotListener
                }
        } catch (e: Exception) {
            emit(emptyList())
        }
    }

    suspend fun getAllServices(): List<Service> {
        return try {
            firestore.collection("Services")
                .whereEqualTo("status", "active")
                .orderBy("createdAt", com.google.firebase.firestore.Query.Direction.DESCENDING)
                .get()
                .await()
                .documents
                .mapNotNull { it.toObject(Service::class.java) }
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun searchServices(
        title: String = "",
        category: String = "",
        district: String = ""
    ): List<Service> {
        return try {
            var query = firestore.collection("Services")
                .whereEqualTo("status", "active")

            if (category.isNotEmpty()) {
                query = query.whereEqualTo("category", category)
            }

            if (district.isNotEmpty()) {
                query = query.whereEqualTo("district", district)
            }

            val services = query
                .orderBy("createdAt", com.google.firebase.firestore.Query.Direction.DESCENDING)
                .get()
                .await()
                .documents
                .mapNotNull { it.toObject(Service::class.java) }

            // Filter by title locally (since Firestore doesn't support contains queries)
            return if (title.isEmpty()) {
                services
            } else {
                services.filter { it.title.contains(title, ignoreCase = true) }
            }
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun getServiceById(ServiceId: String): Service? {
        return try {
            firestore.collection("Services")
                .document(ServiceId)
                .get()
                .await()
                .toObject(Service::class.java)
        } catch (e: Exception) {
            null
        }
    }

    suspend fun filterByDistrict(district: String): List<Service> {
        return try {
            firestore.collection("Services")
                .whereEqualTo("status", "active")
                .whereEqualTo("district", district)
                .orderBy("createdAt", com.google.firebase.firestore.Query.Direction.DESCENDING)
                .get()
                .await()
                .documents
                .mapNotNull { it.toObject(Service::class.java) }
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun filterByCategory(category: String): List<Service> {
        return try {
            firestore.collection("Services")
                .whereEqualTo("status", "active")
                .whereEqualTo("category", category)
                .orderBy("createdAt", com.google.firebase.firestore.Query.Direction.DESCENDING)
                .get()
                .await()
                .documents
                .mapNotNull { it.toObject(Service::class.java) }
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun createService(service: Service): Result<String> {
        return try {
            val ServiceId = firestore.collection("Services").document().id
            val ServiceWithId = service.copy(ServiceId = ServiceId)
            firestore.collection("Services")
                .document(ServiceId)
                .set(ServiceWithId)
                .await()
            Result.success(ServiceId)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
