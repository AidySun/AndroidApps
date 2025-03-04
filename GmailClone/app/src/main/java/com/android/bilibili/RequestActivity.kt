package com.android.bilibili

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class RequestActivity : ComponentActivity() {
    private val launcher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val response = result.data?.getStringExtra("response")
            // 处理返回的响应
            response?.let {
                // 可以在这里处理响应，比如显示Toast
            Toast.makeText(this, response, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RequestScreen { message ->
                // 启动ResponseActivity并传递消息
                val intent = Intent(this, ResponseActivity::class.java).apply {
                    putExtra("message", message)
                }
                launcher.launch(intent)
            }
        }
    }
}

@Composable
fun RequestScreen(onSendClick: (String) -> Unit) {
    var inputText by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Request Activity")

        TextField(
            value = inputText,
            onValueChange = { inputText = it },
            label = { Text("Enter message") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        Button(
            onClick = { onSendClick(inputText) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Send to Response Activity")
        }
    }
}

