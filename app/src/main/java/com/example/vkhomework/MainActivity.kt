package com.example.vkhomework

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.vkhomework.ui.NumberGridScreen
import com.example.vkhomework.ui.theme.VKHomeWorkTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VKHomeWorkTheme {
                NumberGridScreen()
            }
        }
    }
}
