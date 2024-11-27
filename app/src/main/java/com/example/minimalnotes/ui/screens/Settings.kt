package com.example.minimalnotes.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.minimalnotes.ui.theme.nunitoFontFamily

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Settings (
    modifier: Modifier = Modifier,
) {
        var checked by remember { mutableStateOf(false) }
        Scaffold(
            topBar = {
                TopAppBar(
                    colors = topAppBarColors(
                        containerColor = Color.Transparent,
                        titleContentColor = Color(0xFF4B4A4B),
                    ),
                    title = {
                        Row (
                            modifier = Modifier.fillMaxSize(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text("Settings")
                        }
                    }
                )
            },
        ) {
            Column (
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFDeDFDE))
                    .padding(start = 30.dp, top = 120.dp, end = 30.dp)
            ){
                Row (
                    modifier = Modifier
                        .background(color = Color.White, shape = RoundedCornerShape(10.dp))
                        .fillMaxWidth()
                        .padding(start = 16.dp, top = 20.dp, end = 16.dp, bottom = 20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        "Dark Mode",
                        style = TextStyle(
                            color = Color(0xFF4B4A4B),
                            fontFamily = nunitoFontFamily,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 14.sp
                        )
                    )
                    Switch(
                        checked = checked,
                        onCheckedChange = {
                            checked = it
                        },
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = Color.White,
                            checkedTrackColor = Color.Green,
                            uncheckedThumbColor = Color.White,
                            uncheckedTrackColor = Color(0xFFDeDFDE),
                        )
                    )
                }
            }
        }
}
