package com.example.compose_demo.ui.compose

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.example.compose_demo.data.Node
import com.example.compose_demo.data.NodeContent
import kotlin.math.roundToInt


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
fun NodeAt(
    offset: DpOffset,
    modifier: Modifier,
    content: @Composable () -> Unit
) {
    Layout(
        modifier = modifier,
        content = content
    ) { measurables, constraints ->
        val placeables = measurables.map {
            it.measure(constraints)
        }

        val nodePlaceable = placeables[0]

        layout(constraints.maxWidth, constraints.maxHeight) {
            nodePlaceable.placeRelative(
                x = offset.x.toPx().roundToInt(),
                y = offset.y.toPx().roundToInt() - nodePlaceable.height / 2
            )
        }
    }
}

@Preview
@Composable
fun LineDemo1() {
    BoxWithConstraints(
        modifier = Modifier
            .size(500.dp)
    ) {
        val width = maxWidth
        val height = maxHeight

        val start = DpOffset(0.dp, height / 2)
        val end = DpOffset(start.x + 100.dp, start.y)

        LineBetween(start = start, end = end)
        NodeAt(offset = end, modifier = Modifier,
            content = {
                Text(
                    text = "Hello World",
                    modifier = Modifier
                        .border(
                            width = 2.dp,
                            color = Color.Gray,
                            shape = CircleShape
                        )
                        .padding(15.dp)
                )
            }
        )


    }


}

@Composable
fun LineBetween(
    start: DpOffset,
    end: DpOffset,
    modifier: Modifier = Modifier
) {
    val path = Path()
    Canvas(
        modifier = modifier,
        onDraw = {

            drawPath(
                path = path,
                color = Color.Red,
                style = Stroke(width = 5f)
            )

            path.reset()
            path.moveTo(start.x.toPx(), start.y.toPx())
            path.lineTo(end.x.toPx(), end.y.toPx())
        })
}