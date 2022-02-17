package com.myunidays.segmenktexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.myunidays.segmenkt.Analytics
import com.myunidays.segmenkt.Configuration
import com.myunidays.segmenkt.WriteKey
import com.myunidays.segmenktexample.ui.theme.SegmenktExampleTheme

class MainActivity : ComponentActivity() {

    init {
        val segmentConfig = Configuration(
            writeKey = WriteKey(
                android = "123",
                ios = "ABC"
            ),
            context = applicationContext
        )
        Analytics.setupWithConfiguration(segmentConfig)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SegmenktExampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting("Android")
                }
            }
        }
        Analytics.shared(applicationContext).track("Main Screen")
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SegmenktExampleTheme {
        Greeting("Android")
    }
}