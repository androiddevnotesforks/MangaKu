package com.uwaisalqadri.mangaku.android.presentation.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.uwaisalqadri.mangaku.android.presentation.destinations.DetailScreenDestination
import com.uwaisalqadri.mangaku.android.presentation.search.composables.SearchField
import com.uwaisalqadri.mangaku.android.presentation.search.composables.SearchResult
import com.uwaisalqadri.mangaku.android.presentation.search.composables.StaggeredVerticalGrid
import com.uwaisalqadri.mangaku.android.presentation.theme.composables.BackButton
import com.uwaisalqadri.mangaku.android.presentation.theme.composables.ShimmerSearchItem
import com.uwaisalqadri.mangaku.android.presentation.theme.composables.TopBar
import org.koin.androidx.compose.getViewModel

@Destination
@Composable
fun SearchScreen(
    navigator: DestinationsNavigator,
    viewModel: SearchViewModel = getViewModel()
) {
    val searchManga by viewModel.searchManga.collectAsState()
    var query by viewModel.query

    LazyColumn(
        modifier = Modifier.background(color = MaterialTheme.colors.primary)
    ) {

        item {
            BackButton(
                modifier = Modifier.padding(start = 25.dp, top = 25.dp)
            ) {
                navigator.popBackStack()
            }
        }

        item {
            Spacer(modifier = Modifier.padding(top = 20.dp))
        }

        item {
            TopBar(name = "Search")
        }

        item {
            SearchField(
                query = query,
                placeholder = "Search All Manga..",
                onQueryChanged = viewModel::onQueryChanged,
                onExecuteSearch = { viewModel.getSearchManga(query) },
                onEraseQuery = { query = "" },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 20.dp)
            )
        }

        item {
            StaggeredVerticalGrid(
                maxColumnWidth = 150.dp
            ) {
                if (searchManga.loading) {
                    repeat(10) {
                        ShimmerSearchItem()
                    }
                } else {
                    searchManga.data?.forEach { manga ->
                        SearchResult(manga = manga) {
                            navigator.navigate(
                                DetailScreenDestination(mangaId = it)
                            )
                        }
                    }
                }
            }
        }

        item {
            Spacer(
                modifier = Modifier
                    .background(color = Color.Transparent)
                    .height(200.dp)
            )
        }

    }
}










