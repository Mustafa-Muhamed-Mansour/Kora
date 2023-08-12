package com.kora.navgraph

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kora.home.NewsByCountry
import com.kora.home.NewsViewModel
import com.kora.route.RouteScreen.Companion.NEWS_PAGE

@Composable
fun NavOfPage(newsViewModel: NewsViewModel) {

    NavHost(navController = rememberNavController(), startDestination = NEWS_PAGE, builder = {
        composable(route = NEWS_PAGE) {
            NewsByCountry(newsViewModel = newsViewModel)
        }
    })

}