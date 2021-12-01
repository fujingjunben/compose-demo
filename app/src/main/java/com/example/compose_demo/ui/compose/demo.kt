package com.example.compose_demo.ui.compose

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawStyle
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose_demo.data.Node
import com.example.compose_demo.data.NodeContent


@Preview
@Composable
fun MindMap() {
    val head = Node(mutableListOf(), NodeContent("head", "head"));
    for (i in 0..4) {
        head.children.add(Node(mutableListOf(), NodeContent("$i", "node$i")))
    }
    Node(head)
}

@Composable
fun Node(node: Node) {
    Column {
        node.children.map {
            TextWithBorder(content = it.content.label)
        }
    }
}

@Composable
fun TextWithBorder(content: String) {
    Text(
        content,
        modifier = Modifier
            .padding(5.dp)
            .border(
                width = 2.dp,
                color = Color.Green,
                shape = CircleShape
            )
            .padding(vertical = 4.dp, horizontal = 8.dp)
    )
}

@Composable
fun NodeWithPath(
    start: Offset,
    modifier: Modifier,
    content: @Composable () -> Unit
) {
    Layout(
        modifier = modifier,
        content = content
    ) { measurables, constraints ->
        check(measurables.size == 2)
        val placeables = measurables.map {
            it.measure(constraints)
        }

        val pathPlaceable = placeables[0]
        val nodePlaceable = placeables[1]

        layout(constraints.maxWidth, constraints.maxHeight) {

        }
    }
}

@Preview
@Composable
fun LineDemo() {
    val size = 200.dp
    val n = 5
    val padding = size.div(n)

    repeat(n + 1) {
        val topPadding = padding.times(it)
        NodeWithLine(
            Modifier
                .padding(top = topPadding)
                .size(width = size, height = size - topPadding)
        )

        Box(modifier = Modifier.offset(
            x = size - 20.dp, y = topPadding)) {
            Text(it.toString())
        }
    }
}

@Composable
fun NodeWithLine(modifier: Modifier) {
    val path = Path()
    Canvas(
        modifier = modifier,
        onDraw = {
            val width = size.width
            val height = size.height

            drawPath(
                path = path,
                color = Color.Red,
                style = Stroke(width = 5f)
            )

            path.reset()
            path.moveTo(0f, height)
            path.lineTo(width / 2, 0f)
            path.lineTo(width, 0f)
        })
}
