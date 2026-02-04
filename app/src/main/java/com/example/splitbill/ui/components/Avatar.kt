package com.example.splitbill.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.splitbill.domain.model.Person
import kotlin.math.abs

@Composable
fun Avatar(
    person: Person,
    size: Dp = 40.dp,
    modifier: Modifier = Modifier
) {
    val initials = remember(person.name) {
        person.name.split(" ")
            .mapNotNull { it.firstOrNull()?.uppercase() }
            .take(2)
            .joinToString("")
    }

    val backgroundColor = remember(person.name) {
        val hash = abs(person.name.hashCode())
        val colors = listOf(
            Color(0xFFF04438), // Red
            Color(0xFF12B76A), // Green
            Color(0xFF4F75FE), // Blue
            Color(0xFFF79009), // Orange
            Color(0xFF9E77ED), // Purple
            Color(0xFFEE46BC)  // Pink
        )
        colors[hash % colors.size]
    }

    Box(
        modifier = modifier
            .size(size)
            .clip(CircleShape)
            .background(backgroundColor),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = initials,
            color = Color.White,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight.Bold,
                fontSize = (size.value * 0.4).sp
            )
        )
    }
}
