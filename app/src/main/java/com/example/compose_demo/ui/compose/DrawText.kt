package com.example.compose_demo.ui.compose

import android.graphics.Typeface
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DrawText(text: String) {
    val textPaint = Paint().asFrameworkPaint().apply {
        isAntiAlias = true
        textSize = 24.sp.value
        color = android.graphics.Color.BLUE
        typeface = Typeface.create(Typeface.MONOSPACE, Typeface.BOLD)
    }
    Canvas(
        modifier = Modifier.fillMaxSize(),
        onDraw = {
            drawIntoCanvas {
                it.nativeCanvas.drawText(
                    text,
                    0f,            // x-coordinates of the origin (top left)
                    120.dp.toPx(), // y-coordinates of the origin (top left)
                    textPaint
                )
            }
        }
    )
}