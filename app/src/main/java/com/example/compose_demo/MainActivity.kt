package com.example.compose_demo

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.PaintingStyle.Companion.Stroke
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose_demo.ui.compose.LineDemo
import com.example.compose_demo.ui.theme.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposedemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    LineDemo()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposedemoTheme {
        SmileyFaceCanvas(modifier = Modifier.size(50.dp))
    }
}

@Composable
fun SmileyFaceCanvas(
    modifier: Modifier
) {
    Canvas(
        modifier = modifier.size(300.dp),
        onDraw = {
            // Head
            drawCircle(
                Brush.linearGradient(
                    colors = listOf(greenLight700, green700)
                ),
                radius = size.width / 2,
                center = center,
                style = Stroke(width = size.width * 0.075f)
            )

            // Smile
            val smilePadding = size.width * 0.15f
            drawArc(
                color = red700,
                startAngle = 0f,
                sweepAngle = 180f,
                useCenter = true,
                topLeft = Offset(smilePadding, smilePadding),
                size = Size(size.width - (smilePadding * 2f), size.height - (smilePadding * 2f))
            )

            // Left eye
            drawRect(
                color = dark,
                topLeft = Offset(size.width * 0.25f, size.height / 4),
                size = Size(smilePadding, smilePadding)
            )

            // Right eye
            drawRect(
                color = dark,
                topLeft = Offset((size.width * 0.75f) - smilePadding, size.height / 4),
                size = Size(smilePadding, smilePadding)
            )
        }
    )
}