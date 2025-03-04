package com.example.myjavaapp

import android.content.res.Configuration
import android.util.Log
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import androidx.compose.runtime.*



private val TAG_IMAGE = "image"
private val TAG_NAME = "name"
private val TAG_COUNTRY = "country"
private val TAG_STATS = "stats"
private val TAG_FOLLOW = "follow"
private val TAG_MESSAGE = "message"
private val TAG_FOLLOWERS = "followers"

private val MARGIN = 16.dp

@Composable
fun ProfilePageConstraint() {

        // 新增方向监测代码
    val configuration = LocalConfiguration.current
    var currentOrientation by remember { mutableStateOf(configuration.orientation) }
    
    LaunchedEffect(configuration) {
        if (currentOrientation != configuration.orientation) {
            currentOrientation = configuration.orientation
            val orientationName = when (configuration.orientation) {
                Configuration.ORIENTATION_LANDSCAPE -> "Landscape"
                Configuration.ORIENTATION_PORTRAIT -> "Portrait"
                else -> "Undefined"
            }
            Log.d("YDIA", "Orientation changed to: $orientationName")
        }
    }
    
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
        BoxWithConstraints {
            println("minWidth: " + minWidth)
            var constraints = if (minWidth < 600.dp)  {
                portraitConstraints(MARGIN)
            } else {
                landscapeConstraints(MARGIN)
            }

            ConstraintLayout(modifier = Modifier.fillMaxSize()) {

                val guideline = createGuidelineFromTop(fraction = 0.1f)

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
//                        top.linkTo(parent.top, margin = 20.dp)
                            top.linkTo(guideline)
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
                            top.linkTo(countryText.bottom, margin = 16.dp)
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
                    modifier = Modifier
                        .constrainAs(buttonFollow) {
                            top.linkTo(rowStats.bottom, margin = 16.dp)
                            start.linkTo(parent.start, margin = 16.dp)
                            end.linkTo(buttonMessage.start)
                            width = Dimension.fillToConstraints
                            height = Dimension.preferredWrapContent
                        }
                        .padding(horizontal = 4.dp)
                ) {
                    Text("Follow User")
                }

                Button(
                    onClick = {},
                    modifier = Modifier
                        .constrainAs(buttonMessage) {
                            top.linkTo(rowStats.bottom, margin = 16.dp)
                            start.linkTo(buttonFollow.end)
                            end.linkTo(parent.end, margin = 16.dp)
                            width = Dimension.fillToConstraints
                            height = Dimension.preferredWrapContent
                        }
                        .padding(horizontal = 4.dp)
                ) {
                    Text("Message")
                }
            }
        }
    }
}
private fun portraitConstraints(margin: Dp): ConstraintSet {
    println("正在生成约束集...")

    return ConstraintSet {
        val image = createRefFor(TAG_IMAGE)
        val name = createRefFor(TAG_NAME)
        val country = createRefFor(TAG_COUNTRY)
        val stats = createRefFor(TAG_STATS)
        val followButton = createRefFor(TAG_FOLLOW)
        val messageButton = createRefFor(TAG_MESSAGE)

        constrain(image) {
            top.linkTo(parent.top, margin = margin)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }

        constrain(name) {
            top.linkTo(image.bottom, margin = margin)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
        constrain(country) {
            top.linkTo(name.bottom, margin = margin)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
        constrain(stats) {
            top.linkTo(country.bottom, margin = margin)
            start.linkTo(parent.start, margin = margin)
            end.linkTo(parent.end, margin = margin)
            width = Dimension.fillToConstraints
        }
        constrain(followButton) {
            top.linkTo(stats.bottom, margin = margin)
            start.linkTo(parent.start, margin = margin)
            end.linkTo(messageButton.start)
            width = Dimension.fillToConstraints
            height = Dimension.preferredWrapContent
        }
        constrain(messageButton) {
            top.linkTo(stats.bottom, margin = margin)
            start.linkTo(followButton.end)
            end.linkTo(parent.end, margin = margin)
            width = Dimension.fillToConstraints
            height = Dimension.preferredWrapContent
        }
    }

}

private fun landscapeConstraints(margin: Dp): ConstraintSet {

    return ConstraintSet {
        val image = createRefFor(TAG_IMAGE)
        val name = createRefFor(TAG_NAME)
        val country = createRefFor(TAG_COUNTRY)
        val stats = createRefFor(TAG_STATS)
        val followButton = createRefFor(TAG_FOLLOW)
        val messageButton = createRefFor(TAG_MESSAGE)

        constrain(image) {
            top.linkTo(parent.top)
            start.linkTo(parent.start, margin = margin)
            bottom.linkTo(parent.bottom)
//            end.linkTo(parent.end)
        }

        constrain(name) {
            top.linkTo(image.bottom, margin = margin)
            start.linkTo(parent.start, margin = margin)
        }

        constrain(country) {
            top.linkTo(name.bottom, margin = margin)
            start.linkTo(parent.start, margin = margin)
        }

        constrain(stats) {
            top.linkTo(country.bottom, margin = margin)
            start.linkTo(parent.start, margin = margin)
            end.linkTo(parent.end, margin = margin)
            width = Dimension.fillToConstraints
        }
        constrain(followButton) {
            top.linkTo(stats.bottom, margin = margin)
            start.linkTo(parent.start, margin = margin)
            end.linkTo(messageButton.start)
            width = Dimension.fillToConstraints
            height = Dimension.preferredWrapContent
        }
        constrain(messageButton) {
            top.linkTo(stats.bottom, margin = margin)
            start.linkTo(followButton.end)
            end.linkTo(parent.end, margin = margin)
            width = Dimension.fillToConstraints
            height = Dimension.preferredWrapContent
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

@Preview(showBackground = true, device = Devices.AUTOMOTIVE_1024p, widthDp = 720, heightDp = 360)
@Composable
fun PreviewProfilePageConstraint() {
    ProfilePageConstraint()
}