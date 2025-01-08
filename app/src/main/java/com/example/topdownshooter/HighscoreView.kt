package com.example.topdownshooter

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

@Composable
fun HighscoreView() {
    val highScores = remember { mutableStateListOf<HighScore>() }

    LaunchedEffect(Unit) {
        val db = FirebaseFirestore.getInstance()
        db.collection("highscores")
            .orderBy("score", Query.Direction.DESCENDING) // Sort by score in descending order
            .get()
            .addOnSuccessListener { result ->
                highScores.clear() // Clear existing scores to prevent duplication
                for (document in result) {
                    val highScore = document.toObject(HighScore::class.java)
                    highScores.add(highScore)
                }
            }
            .addOnFailureListener { exception ->
                // Handle failure
            }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "High Scores", modifier = Modifier.padding(bottom = 16.dp))
        LazyColumn {
            items(highScores) { score ->
                Text(
                    text = "${score.playerName}: ${score.score}",
                    modifier = Modifier.padding(vertical = 4.dp)
                )
            }
        }
    }
}