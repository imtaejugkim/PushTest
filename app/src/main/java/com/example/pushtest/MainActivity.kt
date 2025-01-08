package com.example.pushtest

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.firebase.messaging.FirebaseMessaging


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyBottomSheet()
        }
    }
}

@Composable
fun MyApp() {
    var token by remember { mutableStateOf("토큰 버튼을 눌러주세요") }
    var title by remember { mutableStateOf("메시징 Title") }
    var body by remember { mutableStateOf("메시징 Body") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(10.dp)
    ) {
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                FirebaseMessaging.getInstance().token.addOnSuccessListener {
                    token = it
                    Log.d("token", it)
                }
            }
        ) {
            Text(text = "Firebase token 값 확인하기")
        }
        Text(
            text = token,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(color = Color.Black)
        ) {
            Text(
                text = token,
                modifier = Modifier
                    .fillMaxWidth(),
                color = Color.White
            )
            Text(
                text = token,
                modifier = Modifier
                    .fillMaxWidth(),
                color = Color.White
            )
        }
    }
}

@Composable
@Preview
fun MyAppPreview() {
    MyTimeApp()
}