package com.example.topdownshooter

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth

@Composable
fun Home(modifier: Modifier = Modifier,
         onPlayClick: () -> Unit = {},
         navController: NavController = rememberNavController()){

    fun LogoutClick(navController: NavController) {
        FirebaseAuth.getInstance().signOut()
        navController.navigate(Screen.Login.route)
    }




    Box(modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter){
        Image(painter = painterResource(id = R.drawable.splash),
            contentDescription = "",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds)
        Column(modifier = Modifier.padding(40.dp),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Image(painter = painterResource(id = R.drawable.playnow),
                contentDescription = "play now",
                modifier = Modifier.width(400.dp).height(80.dp)
                    .clickable {
                        onPlayClick()
                    },
                contentScale = ContentScale.FillBounds)
            Spacer(modifier = Modifier.height(16.dp))
            Image(painter = painterResource(id = R.drawable.highscore),
                contentDescription = "high score" ,
                modifier = Modifier.width(300.dp).height(60.dp)
                    .clickable {
                    //onHighscoreClick()
                },
                contentScale = ContentScale.FillBounds)
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.BottomStart // Ensures alignment in the bottom-left
        ) {
            Image(
                painter = painterResource(id = R.drawable.baseline_logout_24),
                contentDescription = "Logout",
                modifier = Modifier.size(100.dp).
                clickable {
                    LogoutClick(navController)
                },
                contentScale = ContentScale.FillBounds
            )
        }
        }

    }


@Preview(showBackground = true)
@Composable
fun GameHomeViewPreview() {
    Home()
}