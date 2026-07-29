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
                .whereEqualTo("subCategoryId", subCategoryId)
                .whereEqualTo("isActive", true)
                .orderBy("order")
                .get()
                .await()
                .toObjects(Service::class.java)

        } catch (e: Exception) {

            emptyList()

        }

    }

}