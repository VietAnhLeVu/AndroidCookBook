package com.example.androidcookbook

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.androidcookbook.ui.CookbookApp
import com.example.androidcookbook.ui.screen.auth.SignBackground
import com.example.androidcookbook.ui.theme.AndroidCookbookTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidCookbookTheme {
                CookbookApp()
            }
        }
    }
}
