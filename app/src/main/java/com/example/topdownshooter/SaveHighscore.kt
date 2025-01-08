package com.example.topdownshooter

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

fun saveHighScore(playerName: String, score: Int) {
    val db = FirebaseFirestore.getInstance()
    val highscoresRef = db.collection("highscores")

    // Fetch the current high scores
    highscoresRef.orderBy("score", Query.Direction.ASCENDING).get()
        .addOnSuccessListener { querySnapshot ->
            val highScores = querySnapshot.documents

            if (highScores.size < 100) {
                // If less than 100 scores, directly add the new high score
                addHighScore(playerName, score, highscoresRef)
            } else {
                // Check if the new score is higher than the lowest score
                val lowestScoreDoc = highScores[0]
                val lowestScore = lowestScoreDoc.getLong("score")?.toInt() ?: 0

                if (score > lowestScore) {
                    // Replace the lowest score with the new score
                    highscoresRef.document(lowestScoreDoc.id).delete()
                        .addOnSuccessListener {
                            addHighScore(playerName, score, highscoresRef)
                        }
                        .addOnFailureListener { e ->
                            // Handle failure to delete the lowest score
                        }
                }
            }
        }
        .addOnFailureListener { e ->
            // Handle failure to fetch the high scores
        }
}

// Helper function to add a high score to the collection
private fun addHighScore(playerName: String, score: Int, highscoresRef: CollectionReference) {
    val highScore = hashMapOf(
        "playerName" to playerName,
        "score" to score
    )

    highscoresRef.add(highScore)
        .addOnSuccessListener { documentReference ->
            // Handle success
        }
        .addOnFailureListener { e ->
            // Handle failure
        }
}
