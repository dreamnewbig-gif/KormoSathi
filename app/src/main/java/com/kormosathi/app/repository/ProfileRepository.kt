package com.kormosathi.app.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.kormosathi.app.model.UserProfile
import kotlinx.coroutines.tasks.await
import com.google.firebase.firestore.SetOptions

class ProfileRepository {

    private val firestore = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()

    suspend fun saveProfile(profile: UserProfile): Result<Unit> {
        return try {

            val uid = auth.currentUser?.uid
                ?: return Result.failure(Exception("User not logged in"))

            val profileData = mapOf(
                "name" to profile.name,
                "gender" to profile.gender,
                "age" to profile.age,
                "district" to profile.district,
                "block" to profile.block,
                "village" to profile.village,
                "pincode" to profile.pincode,
                "category" to profile.category,
                "experience" to profile.experience,
                "expectedSalary" to profile.expectedSalary,
                "profileCompleted" to true
            )

            firestore.collection("users")
                .document(uid)
                .set(profileData, SetOptions.merge())
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

    suspend fun isProfileCompleted(): Boolean {
        val profile = getProfile()
        return profile?.profileCompleted ?: false
    }
}