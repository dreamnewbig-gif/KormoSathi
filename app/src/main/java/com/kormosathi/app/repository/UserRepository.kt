package com.kormosathi.app.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.kormosathi.app.model.User

class UserRepository {

    private val db = FirebaseFirestore.getInstance()

    fun saveUser(
        user: User,
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit
    ) {

        val document = db.collection("users").document(user.uid)

        document.get()
            .addOnSuccessListener { snapshot ->

                if (snapshot.exists()) {

                    // User already exists.
                    // Don't overwrite profileCompleted or profile data.
                    onSuccess()

                } else {

                    document
                        .set(user)
                        .addOnSuccessListener {
                            onSuccess()
                        }
                        .addOnFailureListener { exception ->
                            onFailure(exception)
                        }

                }

            }
            .addOnFailureListener { exception ->
                onFailure(exception)
            }

    }

}