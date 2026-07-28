package com.kormosathi.app.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.kormosathi.app.model.SubCategory
import kotlinx.coroutines.tasks.await

class SubCategoryRepository {

    private val db = FirebaseFirestore.getInstance()

    suspend fun getSubCategories(categoryId: String): List<SubCategory> {

        return try {

            db.collection("subcategories")
                .whereEqualTo("categoryId", categoryId)
                .whereEqualTo("isActive", true)
                .orderBy("order")
                .get()
                .await()
                .toObjects(SubCategory::class.java)

        } catch (e: Exception) {
            emptyList()
        }

    }

}