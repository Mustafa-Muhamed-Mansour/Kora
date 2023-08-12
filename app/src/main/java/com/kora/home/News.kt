package com.kora.home

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.kora.model.ArticleModel
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp


@Composable
fun NewsByCountry(newsViewModel: NewsViewModel) {

    val context = LocalContext.current
    val country: MutableState<String> = remember { mutableStateOf("") }
    val result = newsViewModel.list.value


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.sdp)
    ) {

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 5.sdp, top = 0.sdp, end = 5.sdp, bottom = 5.sdp),
            value = country.value,
            onValueChange = {
                country.value = it
                newsViewModel.newsByCountry(country.value)
            },
            singleLine = true,
            leadingIcon = {
                Icon(imageVector = Icons.Default.Search, contentDescription = null)
            },
            label = { Text(text = "Search here", color = Color.Black) },
        )


        if (result.isLoading) {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(5.sdp)
                )
            }
        }


        if (result.error.isNotBlank()) {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = newsViewModel.list.value.error
                )
            }
        }

        if (result.data.isNotEmpty()) {
            LazyColumn {
                newsViewModel.list.value.data?.let {
                    items(it) { itArticle ->
                        NewsByCountry(context, itArticle)
                    }
                }
            }
        }
    }

//    Box(modifier = Modifier.fillMaxSize()) {
//
//        Card(
//            modifier = Modifier
//                .fillMaxWidth()
//                .fillMaxHeight()
//                .padding(5.sdp)
//                .clip(RoundedCornerShape(5.sdp)),
//            elevation = 10.sdp
//        ) {
//
//            LazyColumn {
//                if (news.isNullOrEmpty()) {
//                    item {
//                        CircularProgressIndicator(
//                            modifier = Modifier
//                                .fillMaxSize()
//                                .wrapContentSize(
//                                    Alignment.Center
//                                )
//                        )
//                        Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
//                    }
//                } else {
//                    news.forEach() { newsRes ->
//                        items(newsRes.articleModels.size) {
//                            NewsByCountry(
//                                context = context,
//                                news = newsRes.articleModels[it]
//                            )
//                        }
//                    }
//                }
//            }
//        }
//    }

}

@Composable
fun NewsByCountry(context: Context, news: ArticleModel) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.sdp)
            .clip(RoundedCornerShape(10.sdp))
            .padding(vertical = 5.sdp)
            .clickable {
                clickedOnArticleToOpen(context, news)
            }, elevation = 15.sdp
    ) {
        Column(modifier = Modifier.fillMaxSize()) {

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(3.sdp),
                text = news.title,
                fontSize = 23.ssp,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

//            Spacer(modifier = Modifier.padding(10.sdp))


            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.5f)
                    .padding(5.sdp),
                text = news.author,
                fontSize = 17.ssp,
                color = Color.Black,
                fontStyle = FontStyle.Italic,
                textAlign = TextAlign.End
            )
        }

    }
}


fun clickedOnArticleToOpen(context: Context, news: ArticleModel) {
    val newsURL = Intent(Intent.ACTION_VIEW)
    newsURL.data = Uri.parse(news.url)
    context.startActivity(newsURL)
}




