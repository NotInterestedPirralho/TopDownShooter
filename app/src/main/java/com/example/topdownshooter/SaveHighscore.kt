package com.example.topdownshooter

import com.google.firebase.firestore.FirebaseFirestore

fun saveHighScore(playerName: String, score: Int) {
    val db = FirebaseFirestore.getInstance()
    val highScore = hashMapOf(
        "playerName" to playerName,
        "score" to score
    )
    db.collection("highscores")
        .add(highScore)
        .addOnSuccessListener { documentReference ->
            // Handle success
        }
        .addOnFailureListener { e ->
            // Handle failure
        }
}