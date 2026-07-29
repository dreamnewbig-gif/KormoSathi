package com.kormosathi.app.repository

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.kormosathi.app.model.Category
import kotlinx.coroutines.tasks.await

class CategoryRepository {

    private val db = FirebaseFirestore.getInstance()

    suspend fun getCategories(): List<Category> {
        return try {

            val snapshot = db.collection("categories")
                .get()
                .await()

            val categories = snapshot.documents.mapNotNull { document ->

                document.toObject(Category::class.java)?.copy(
                    id = document.id
                )

            }
                .filter { it.isActive }
                .sortedBy { it.order }

            Log.d(
                "CategoryRepository",
                "Loaded ${categories.size} categories"
            )

            categories

        } catch (e: Exception) {

            Log.e(
                "CategoryRepository",
                "Failed to load categories",
                e
            )

            throw e
        }
    }
}