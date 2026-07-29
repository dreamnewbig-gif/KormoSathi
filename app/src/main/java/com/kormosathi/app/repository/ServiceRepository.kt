package com.kormosathi.app.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.kormosathi.app.model.Service
import kotlinx.coroutines.tasks.await

class ServiceRepository {

    private val db = FirebaseFirestore.getInstance()

    suspend fun getServices(
        subCategoryId: String
    ): List<Service> {

        return try {

            db.collection("services")
                .whereEqualTo(
                    "subCategoryId",
                    subCategoryId
                )
                .whereEqualTo(
                    "isActive",
                    true
                )
                .get()
                .await()
                .toObjects(Service::class.java)

        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun getAllServices(): List<Service> {

        return try {

            db.collection("services")
                .whereEqualTo(
                    "isActive",
                    true
                )
                .get()
                .await()
                .toObjects(Service::class.java)

        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun searchServices(
        query: String
    ): List<Service> {

        val allServices = getAllServices()

        if (query.isBlank()) {
            return allServices
        }

        return allServices.filter { service ->

            service.title.contains(
                query,
                ignoreCase = true
            ) ||
                    service.nameEn.contains(
                        query,
                        ignoreCase = true
                    ) ||
                    service.nameBn.contains(
                        query,
                        ignoreCase = true
                    )

        }
    }

    suspend fun filterByDistrict(
        district: String
    ): List<Service> {

        return try {

            db.collection("services")
                .whereEqualTo(
                    "district",
                    district
                )
                .whereEqualTo(
                    "isActive",
                    true
                )
                .get()
                .await()
                .toObjects(Service::class.java)

        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun filterByCategory(
        category: String
    ): List<Service> {

        return try {

            db.collection("services")
                .whereEqualTo(
                    "category",
                    category
                )
                .whereEqualTo(
                    "isActive",
                    true
                )
                .get()
                .await()
                .toObjects(Service::class.java)

        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun getServiceById(
        serviceId: String
    ): Service? {

        return try {

            db.collection("services")
                .document(serviceId)
                .get()
                .await()
                .toObject(Service::class.java)

        } catch (e: Exception) {
            null
        }
    }
}