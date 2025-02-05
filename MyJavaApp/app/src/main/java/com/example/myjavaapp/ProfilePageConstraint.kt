package com.example.myjavaapp

import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.constraintlayout.compose.atLeastWrapContent

@Composable
fun ProfilePageConstraint() {
    Card (elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(
                horizontal = 16.dp,
                vertical = 100.dp
            )
            .background(Color.White)
            .border(
                width = 1.dp,
                color = Color.LightGray,
                shape = RoundedCornerShape(corner = CornerSize(10.dp))
            )
    ) {
        ConstraintLayout(modifier = Modifier.fillMaxSize()) {

            val (image, nameText, countryText, rowStats, buttonFollow, buttonMessage) = createRefs()

            Image(
                painter = painterResource(R.drawable.seven),
                contentDescription = "Seven",
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .size(200.dp)
                    .clip(shape = CircleShape)
                    .border(color = Color.Red, width = 2.dp, shape = CircleShape)
                    .constrainAs(image) {
                        top.linkTo(parent.top, margin = 20.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            )

            Text(text = "SEVEN",
                modifier = Modifier.constrainAs(nameText) {
                    top.linkTo(image.bottom, margin = 8.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
            )
            Text(text = "China",
                modifier = Modifier.constrainAs(countryText) {
                    top.linkTo(nameText.bottom, margin = 6.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
            )

            Row (horizontalArrangement = Arrangement.SpaceEvenly,
//                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
                modifier = Modifier
                    .constrainAs(rowStats) {
                        top.linkTo(countryText.bottom)
                        start.linkTo(parent.start, margin = 16.dp)
                        end.linkTo(parent.end, margin = 16.dp)
                        width = Dimension.fillToConstraints
                    }
//                    .background(color = Color.Yellow)
            ){

                ProfileStat("Followers", 98, Modifier.weight(1f))
                ProfileStat("Following", 126, Modifier.weight(1f))
                ProfileStat("Posts", 30, Modifier.weight(1f))
            }

            Button(
                onClick = {},
                modifier = Modifier.constrainAs(buttonFollow) {
                    top.linkTo(rowStats.bottom, margin = 8.dp)
                    start.linkTo(parent.start, margin = 16.dp)
                    end.linkTo(buttonMessage.start)
                    width = Dimension.fillToConstraints
                    height = Dimension.preferredWrapContent
                }.padding(horizontal = 4.dp)
            ) {
                Text("Follow User")
            }

            Button(
                onClick = {},
                modifier = Modifier.constrainAs(buttonMessage) {
                    top.linkTo(rowStats.bottom, margin = 8.dp)
                    start.linkTo(buttonFollow.end)
                    end.linkTo(parent.end, margin = 16.dp)
                    width = Dimension.fillToConstraints
                    height = Dimension.preferredWrapContent
                }.padding(horizontal = 4.dp)
            ) {
                Text("Message")
            }        
        }
    }
}

@Composable
fun ProfileStat(title: String, count: Int, modifiler: Modifier = Modifier) {
    Column  (horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifiler
    ){
        Text(text = count.toString(), fontWeight = FontWeight.Bold)
        Text(text = title, color = Color.Gray)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewProfilePageConstraint() {
    ProfilePageConstraint()
}