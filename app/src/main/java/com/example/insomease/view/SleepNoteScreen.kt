package com.example.insomease.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.insomease.R
import com.example.insomease.viewmodel.SleepNoteViewModel


@Composable
fun SleepNoteScreen(viewModel: SleepNoteViewModel = viewModel()) {
    val selectedFeeling by viewModel.selectedFeeling.collectAsState()
    val selectedActivities by viewModel.selectedActivities.collectAsState()
    val sleepNoteTime by viewModel.sleepNoteTime.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0C1631))
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Spacer(modifier = Modifier.height(90.dp))

        // Title
        Text(
            text = "Add sleep note",
            fontFamily = FontFamily(Font(R.font.poppins)),
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            color = Color.White,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Feelings Section
        Text(
            text = "How do you feel right now?",
            fontFamily = FontFamily(Font(R.font.poppins)),
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            color = Color.White,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        FeelingGrid(selectedFeeling, onFeelingSelected = { viewModel.selectFeeling(it) })

        Spacer(modifier = Modifier.height(32.dp))

        // Activities Section
        Text(
            text = "Any pre-sleep activities?",
            fontFamily = FontFamily(Font(R.font.poppins)),
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            color = Color.White,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        ActivityGrid(selectedActivities, onActivityToggled = { viewModel.toggleActivity(it) })

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {
                viewModel.saveSleepNote()
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF514388)),
            shape = RoundedCornerShape(30.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
        ) {
            Text(
                text = "Done",
                fontFamily = FontFamily(Font(R.font.poppins)),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color.White
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        BottomNavigationBar()
    }
}


@Composable
fun FeelingGrid(selectedFeeling: String, onFeelingSelected: (String) -> Unit) {
    val feelings = listOf("Stress", "Happy", "Calm", "Tired", "Anxious", "Neutral")
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        for (row in feelings.chunked(3)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                row.forEach { feeling ->
                    FeelingItem(
                        feeling = feeling,
                        isSelected = feeling == selectedFeeling,
                        onFeelingSelected = onFeelingSelected
                    )
                }
            }
        }
    }
}

@Composable
fun FeelingItem(feeling: String, isSelected: Boolean, onFeelingSelected: (String) -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .width(80.dp)
            .clickable { onFeelingSelected(feeling) }
    ) {
        Box(
            modifier = Modifier
                .size(56.dp)
                .background(
                    color = if (isSelected) Color(0xFF514388) else Color(0xFF1C3365),
                    shape = CircleShape
                )
        ) {
            Text(
                text = "😣",
                fontSize = 24.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.align(Alignment.Center)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = feeling,
            fontFamily = FontFamily(Font(R.font.poppins)),
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            color = Color.White,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun ActivityGrid(selectedActivities: List<String>, onActivityToggled: (String) -> Unit) {
    val activities = listOf("Coffee", "Nicotine", "Alcohol", "Eat Late", "Meditation")
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        for (row in activities.chunked(3)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                row.forEach { activity ->
                    ActivityItem(
                        activity = activity,
                        isSelected = selectedActivities.contains(activity),
                        onActivityToggled = onActivityToggled
                    )
                }
            }
        }
    }
}

@Composable
fun ActivityItem(activity: String, isSelected: Boolean, onActivityToggled: (String) -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .width(80.dp)
            .clickable { onActivityToggled(activity) }
    ) {
        Box(
            modifier = Modifier
                .size(56.dp)
                .background(
                    color = if (isSelected) Color(0xFF514388) else Color(0xFF1C3365),
                    shape = CircleShape
                )
        ) {
            Text(
                text = "😣",
                fontSize = 24.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.align(Alignment.Center)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = activity,
            fontFamily = FontFamily(Font(R.font.poppins)),
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            color = Color.White,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun BottomNavigationBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF0D1527))
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(R.drawable.house), // Replace with actual icon
                contentDescription = "home",
                modifier = Modifier.size(24.dp)
            )
            Text(
                text = "Home",
                fontFamily = FontFamily(Font(R.font.poppins)),
                fontSize = 12.sp,
                color = Color.White
            )
        }

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(R.drawable.bed),
                contentDescription = "sleep tracker",
                modifier = Modifier.size(24.dp)
            )
            Text(
                text = "Sleep",
                fontFamily = FontFamily(Font(R.font.poppins)),
                fontSize = 12.sp,
                color = Color.White
            )
        }

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(R.drawable.brain), // Replace with actual icon
                contentDescription = "relax",
                modifier = Modifier.size(24.dp)
            )
            Text(
                text = "Relax",
                fontFamily = FontFamily(Font(R.font.poppins)),
                fontSize = 12.sp,
                color = Color.White
            )
        }

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(R.drawable.person_3_sequence), // Replace with actual icon
                contentDescription = "Profile",
                modifier = Modifier.size(24.dp)
            )
            Text(
                text = "Profile",
                fontFamily = FontFamily(Font(R.font.poppins)),
                fontSize = 12.sp,
                color = Color.White
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SleepNotePreview() {
    SleepNoteScreen()
}