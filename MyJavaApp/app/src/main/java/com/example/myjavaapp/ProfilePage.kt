package com.example.myjavaapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

private val PROFILE_CARD_CORNER_SIZE = 20.dp

@Composable
fun ProfilePage() {
    Card (
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White  
        ),
        shape = RoundedCornerShape(corner = CornerSize(PROFILE_CARD_CORNER_SIZE)),
        modifier = Modifier
            .fillMaxWidth()  
            .padding(horizontal = 16.dp, vertical = 100.dp)
            .border(width = 1.dp, color = Color.LightGray.copy(alpha = 0.6f), shape = RoundedCornerShape(corner = CornerSize(PROFILE_CARD_CORNER_SIZE)))
    ) {
        Column (
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(corner = CornerSize(PROFILE_CARD_CORNER_SIZE)))
        ) {
            Image(painter = painterResource(R.drawable.seven),
                contentDescription = "seven",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(16.dp)
                    .size( width = 200.dp, height = 200.dp)
                    .clip(shape = CircleShape)
                    .border(width = 2.dp, color = Color.Red, shape = CircleShape)
            )

            Text("NUM SEVEN", fontWeight = FontWeight.Bold,
                color = Color.DarkGray
            )
            Text("China",
                modifier = Modifier.padding(16.dp)
            )

            Row (horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp) ){

                ProfileStatus("Followers", 98)
                ProfileStatus("Following", 126)
                ProfileStatus("Posts", 30)
            }

            Row (
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxWidth().padding(16.dp)
            ){
                Button(
                    onClick = {},
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Follow User")
                }

                Button(
                    onClick = {},
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Follow")
                }
            }
        }
    }
}

@Composable
fun ProfileStatus(title: String, count: Int) {
    Column  (horizontalAlignment = Alignment.CenterHorizontally){
        Text(text = count.toString(), fontWeight = FontWeight.Bold)
        Text(text = title, color = Color.Gray)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewProfilePage() {
    ProfilePage()
}