package com.example.topdownshooter.login

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

data class RegisterState(
    val username: String = "",
    val email: String = "",
    val password: String = "",
    val passwordConfirmation: String = "",
    val isLoading: Boolean = false,
    val error: String? = null
)

class RegisterViewModel : ViewModel() {

    var state = mutableStateOf(RegisterState())
        private set

    fun onUserNameChange(username: String) {
        state.value = state.value.copy(username = username)
    }

    fun onEmailChange(email: String) {
        state.value = state.value.copy(email = email)
    }

    fun onPasswordChange(password: String) {
        state.value = state.value.copy(password = password)
    }

    fun onPasswordConfirmationChange(passwordConfirmation: String) {
        state.value = state.value.copy(passwordConfirmation = passwordConfirmation)
    }

    fun userinserted(): Boolean {
        if (state.value.email.isEmpty() || state.value.password.isEmpty()) {

            state.value = state.value.copy(error = "Email and password cannot be empty")
            return false
        }else return true
    }

    fun CheckPasswords(): Boolean {
        if (state.value.password != state.value.passwordConfirmation) {
            state.value = state.value.copy(error = "Passwords do not match")
            return false
        } else return true
    }

    fun onRegisterClick(onRegisterSuccess: () -> Unit) {
        // Reset previous error state
        state.value = state.value.copy(error = null, isLoading = true)

        // Validate user input before making Firebase call
        if (!userinserted()) {
            state.value = state.value.copy(isLoading = false) // Stop loading if validation fails
            return
        }

        if (!CheckPasswords()) {
            state.value = state.value.copy(isLoading = false) // Stop loading if passwords don't match
            return
        }

        // Proceed with Firebase registration
        val auth: FirebaseAuth = Firebase.auth
        auth.createUserWithEmailAndPassword(state.value.email, state.value.password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    val profileUpdates = com.google.firebase.auth.UserProfileChangeRequest.Builder()
                        .setDisplayName(state.value.username)
                        .build()

                    user?.updateProfile(profileUpdates)?.addOnCompleteListener { profileTask ->
                        if (profileTask.isSuccessful) {
                            Log.d(TAG, "User profile updated.")
                            state.value = state.value.copy(isLoading = false)
                            onRegisterSuccess()
                        } else {
                            Log.w(TAG, "User profile update failed.", profileTask.exception)
                            state.value = state.value.copy(
                                error = profileTask.exception?.message ?: "Failed to update profile",
                                isLoading = false
                            )
                        }
                    }
                } else {
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    state.value = state.value.copy(
                        error = task.exception?.message ?: "Registration failed",
                        isLoading = false
                    )
                }
            }
    }
}