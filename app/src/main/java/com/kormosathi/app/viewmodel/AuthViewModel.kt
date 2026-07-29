package com.kormosathi.app.viewmodel

import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kormosathi.app.auth.AuthRepository
import com.kormosathi.app.model.User
import com.kormosathi.app.model.UserProfile
import com.kormosathi.app.repository.ProfileRepository
import com.kormosathi.app.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class AuthViewModel(
    private val authRepository: AuthRepository = AuthRepository(),
    private val userRepository: UserRepository = UserRepository(),
    private val profileRepository: ProfileRepository = ProfileRepository()

) : ViewModel() {


    data class AuthUiState(

        val isLoading: Boolean = false,

        val errorMessage: String = "",

        val phoneNumber: String = "",

        val isOtpSent: Boolean = false,

        val isAuthenticated: Boolean = false

    )



    private val _uiState = MutableStateFlow(

        AuthUiState(

            isAuthenticated = authRepository.isLoggedIn()

        )

    )


    val uiState: StateFlow<AuthUiState> =
        _uiState.asStateFlow()



    fun sendOtp(
        activity: Activity,
        phoneNumber: String
    ) {


        val normalizedPhone = phoneNumber.trim()



        if (normalizedPhone.length != 10) {


            _uiState.update {

                it.copy(

                    errorMessage =
                        "অনুগ্রহ করে ১০ সংখ্যার মোবাইল নম্বর লিখুন"

                )

            }

            return

        }



        _uiState.update {

            it.copy(

                isLoading = true,

                errorMessage = "",

                phoneNumber = normalizedPhone

            )

        }



        authRepository.sendOtp(

            activity = activity,

            phoneNumber = normalizedPhone,


            onCodeSent = {


                _uiState.update {

                    it.copy(

                        isLoading = false,

                        isOtpSent = true

                    )

                }


            },


            onVerificationCompleted = { credential ->


                authRepository.signInWithCredential(

                    credential = credential,


                    onSuccess = {


                        handleSuccessfulSignIn(
                            normalizedPhone
                        )


                    },


                    onError = { message ->


                        _uiState.update {

                            it.copy(

                                isLoading = false,

                                errorMessage = message

                            )

                        }


                    }

                )


            },


            onError = { message ->


                _uiState.update {

                    it.copy(

                        isLoading = false,

                        errorMessage = message

                    )

                }


            }


        )


    }




    fun verifyOtp(
        otp: String
    ) {


        if (otp.length != 6) {


            _uiState.update {

                it.copy(

                    errorMessage =
                        "অনুগ্রহ করে ৬-সংখ্যার OTP লিখুন"

                )

            }


            return

        }



        _uiState.update {

            it.copy(

                isLoading = true,

                errorMessage = ""

            )

        }



        authRepository.verifyOtp(

            otp = otp,


            onSuccess = {


                handleSuccessfulSignIn(

                    _uiState.value.phoneNumber

                )


            },


            onError = { message ->


                _uiState.update {

                    it.copy(

                        isLoading = false,

                        errorMessage = message

                    )

                }


            }


        )


    }





    private fun handleSuccessfulSignIn(
        phoneNumber: String
    ) {


        val currentUser =
            authRepository.currentUser()



        if (currentUser == null) {


            _uiState.update {


                it.copy(

                    isLoading = false,

                    errorMessage =
                        "Authentication failed"

                )


            }


            return

        }




        viewModelScope.launch {



            val user = User(

                id = currentUser.uid,

                name = "",

                phone =
                    currentUser.phoneNumber
                        ?: phoneNumber,


                email =
                    currentUser.email ?: "",


                photo = ""

            )





            val result =
                userRepository.createUser(user)





            result.onSuccess {



                // Create empty profile for new user

                profileRepository.saveProfile(

                    UserProfile(

                        uid = currentUser.uid,

                        phone =
                            currentUser.phoneNumber
                                ?: phoneNumber,


                        profileCompleted = false

                    )

                )





                _uiState.update {


                    it.copy(

                        isLoading = false,

                        isAuthenticated = true,

                        errorMessage = ""

                    )


                }



            }.onFailure { exception ->



                _uiState.update {


                    it.copy(

                        isLoading = false,

                        errorMessage =
                            exception.message
                                ?: "Failed to save user"

                    )


                }


            }



        }


    }






    fun isUserLoggedIn(): Boolean {

        return authRepository.isLoggedIn()

    }







    fun logout() {


        authRepository.signOut()


        _uiState.value = AuthUiState()


    }







    fun clearError() {


        _uiState.update {


            it.copy(

                errorMessage = ""

            )


        }


    }


}