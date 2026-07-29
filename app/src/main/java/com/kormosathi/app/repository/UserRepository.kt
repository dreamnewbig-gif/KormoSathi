package com.kormosathi.app.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.kormosathi.app.model.User
import kotlinx.coroutines.tasks.await

class UserRepository {

    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()

    suspend fun createUser(user: User): Result<Unit> {

        return try {

            db.collection("users")
                .document(user.id)
                .set(user)
                .await()

            Result.success(Unit)

        } catch (e: Exception) {

            Result.failure(e)

        }

    }

    suspend fun getCurrentUser(): User? {

        return try {

            val uid = auth.currentUser?.uid ?: return null

            db.collection("users")
                .document(uid)
                .get()
                .await()
                .toObject(User::class.java)

        } catch (e: Exception) {

            null

        }

    }

    suspend fun updateUser(user: User): Result<Unit> {

        return try {

            db.collection("users")
                .document(user.id)
                .set(user)
                .await()

            Result.success(Unit)

        } catch (e: Exception) {

            Result.failure(e)

        }

    }

}