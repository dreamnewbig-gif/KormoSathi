package com.kormosathi.app.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.kormosathi.app.model.Booking
import kotlinx.coroutines.tasks.await

class BookingRepository {

    private val db = FirebaseFirestore.getInstance()

    suspend fun createBooking(
        booking: Booking
    ): Result<Unit> {

        return try {

            db.collection("bookings")
                .document(booking.id)
                .set(booking)
                .await()

            Result.success(Unit)

        } catch (e: Exception) {

            Result.failure(e)

        }

    }

    suspend fun getCustomerBookings(
        customerId: String
    ): List<Booking> {

        return try {

            db.collection("bookings")
                .whereEqualTo("customerId", customerId)
                .get()
                .await()
                .toObjects(Booking::class.java)

        } catch (e: Exception) {

            emptyList()

        }

    }

    suspend fun getProviderBookings(
        providerId: String
    ): List<Booking> {

        return try {

            db.collection("bookings")
                .whereEqualTo("providerId", providerId)
                .get()
                .await()
                .toObjects(Booking::class.java)

        } catch (e: Exception) {

            emptyList()

        }

    }

    suspend fun updateBookingStatus(
        bookingId: String,
        status: String
    ): Result<Unit> {

        return try {

            db.collection("bookings")
                .document(bookingId)
                .update("status", status)
                .await()

            Result.success(Unit)

        } catch (e: Exception) {

            Result.failure(e)

        }

    }
    suspend fun getApplicantsByService(
        serviceId: String
    ): List<Booking> {

        return try {

            db.collection("bookings")
                .whereEqualTo(
                    "serviceId",
                    serviceId
                )
                .get()
                .await()
                .toObjects(
                    Booking::class.java
                )

        } catch (e: Exception) {

            emptyList()

        }
    }

}