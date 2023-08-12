package com.kora

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.kora.home.NewsViewModel
import com.kora.navgraph.NavOfPage
import com.kora.ui.theme.KoraTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel: NewsViewModel by viewModels()

        setContent {
            NavOfPage(newsViewModel = viewModel)
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//}