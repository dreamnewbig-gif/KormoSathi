package com.kormosathi.app.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.kormosathi.app.model.Category
import kotlinx.coroutines.tasks.await

class CategoryRepository {

    private val firestore = FirebaseFirestore.getInstance()

    suspend fun getCategories(): List<Category> {
        return try {
            firestore.collection("categories")
                .whereEqualTo("isActive", true)
                .orderBy("order")
                .get()
                .await()
                .toObjects(Category::class.java)

        } catch (e: Exception) {
            emptyList()
        }
    }
}