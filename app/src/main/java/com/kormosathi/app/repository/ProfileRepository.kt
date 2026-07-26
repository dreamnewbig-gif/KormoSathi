package com.kormosathi.app.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.kormosathi.app.model.UserProfile
import kotlinx.coroutines.tasks.await

class ProfileRepository {

    private val firestore = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()

    suspend fun saveProfile(profile: UserProfile): Result<Unit> {
        return try {

            val uid = auth.currentUser?.uid
                ?: return Result.failure(Exception("User not logged in"))

            firestore.collection("users")
                .document(uid)
                .set(profile.copy(uid = uid))
                .await()

            Result.success(Unit)

        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getProfile(): UserProfile? {

        val uid = auth.currentUser?.uid ?: return null

        val snapshot = firestore.collection("users")
            .document(uid)
            .get()
            .await()

        return snapshot.toObject(UserProfile::class.java)
    }
}