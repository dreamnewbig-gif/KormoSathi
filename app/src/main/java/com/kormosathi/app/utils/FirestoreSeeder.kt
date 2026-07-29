package com.kormosathi.app.utils

import com.google.firebase.firestore.FirebaseFirestore
import com.kormosathi.app.model.Category

object FirestoreSeeder {

    private val db = FirebaseFirestore.getInstance()

    fun seedCategories() {

        CategoryData.categories.forEach { category ->

            db.collection("categories")
                .document(category.id)
                .set(category)

        }

    }

}