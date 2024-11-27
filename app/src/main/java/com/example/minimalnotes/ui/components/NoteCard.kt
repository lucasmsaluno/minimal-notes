package com.example.minimalnotes.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.minimalnotes.data.Note

@Composable
fun NoteCard (
    note: Note,
    modifier: Modifier = Modifier,
    onNoteClick: (Int) -> Unit
) {
    Row (
        modifier = Modifier
            .background(
                color = Color.White,
                shape = RoundedCornerShape(10.dp)
            )
            .fillMaxWidth()
            .padding(20.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Text(text = note.title)
        Icon(
            imageVector = Icons.Default.KeyboardArrowRight,
            contentDescription = null,
            modifier.clickable {
                onNoteClick(note.id)
            }
        )
    }
    Spacer(modifier = Modifier.height(20.dp))
}