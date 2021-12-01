package com.example.compose_demo.data

data class NodeContent(val id: String, val label: String)
data class Node(val children: MutableList<Node>, val content: NodeContent)