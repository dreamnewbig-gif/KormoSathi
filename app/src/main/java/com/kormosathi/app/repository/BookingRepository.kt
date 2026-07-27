package com.kormosathi.app.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.kormosathi.app.model.Booking
import kotlinx.coroutines.tasks.await

class BookingRepository {

    private val firestore = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()

    suspend fun applyForService(
        ServiceId: String,
        applicantName: String,
        phone: String
    ): Result<String> {
        return try {
            val applicantUid = auth.currentUser?.uid
                ?: return Result.failure(Exception("User not logged in"))

            // Check if user already applied for this Service
            val existingBooking = firestore.collection("Bookings")
                .whereEqualTo("ServiceId", ServiceId)
                .whereEqualTo("applicantUid", applicantUid)
                .get()
                .await()

            if (!existingBooking.isEmpty) {
                return Result.failure(Exception("Already applied for this Service"))
            }

            val BookingId = firestore.collection("Bookings").document().id
            val Booking = Booking(
                BookingId = BookingId,
                ServiceId = ServiceId,
                applicantUid = applicantUid,
                applicantName = applicantName,
                phone = phone,
                appliedAt = System.currentTimeMillis(),
                status = "pending"
            )

            firestore.collection("Bookings")
                .document(BookingId)
                .set(Booking)
                .await()

            Result.success(BookingId)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getMyBookings(): List<Booking> {
        return try {
            val applicantUid = auth.currentUser?.uid
                ?: return emptyList()

            firestore.collection("Bookings")
                .whereEqualTo("applicantUid", applicantUid)
                .orderBy("appliedAt", com.google.firebase.firestore.Query.Direction.DESCENDING)
                .get()
                .await()
                .documents
                .mapNotNull { it.toObject(Booking::class.java) }
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun getBookingsForService(ServiceId: String): List<Booking> {
        return try {
            firestore.collection("Bookings")
                .whereEqualTo("ServiceId", ServiceId)
                .orderBy("appliedAt", com.google.firebase.firestore.Query.Direction.DESCENDING)
                .get()
                .await()
                .documents
                .mapNotNull { it.toObject(Booking::class.java) }
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun hasUserApplied(ServiceId: String): Boolean {
        return try {
            val applicantUid = auth.currentUser?.uid ?: return false

            val result = firestore.collection("Bookings")
                .whereEqualTo("ServiceId", ServiceId)
                .whereEqualTo("applicantUid", applicantUid)
                .get()
                .await()

            !result.isEmpty
        } catch (e: Exception) {
            false
        }
    }

    suspend fun updateBookingStatus(
        BookingId: String,
        status: String
    ): Result<Unit> {
        return try {
            firestore.collection("Bookings")
                .document(BookingId)
                .update("status", status)
                .await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
