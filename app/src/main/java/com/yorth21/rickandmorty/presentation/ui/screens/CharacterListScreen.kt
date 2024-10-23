package com.yorth21.rickandmorty.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.yorth21.rickandmorty.R
import com.yorth21.rickandmorty.presentation.viewmodel.CharacterListViewModel
import androidx.compose.runtime.getValue
import androidx.core.view.WindowInsetsCompat
import coil.compose.AsyncImage
import com.yorth21.rickandmorty.data.models.characters.Result

@Composable
fun CharacterListScreen(
    viewModel: CharacterListViewModel = hiltViewModel()
) {


    val insets = WindowInsetsCompat.toWindowInsetsCompat(
        androidx.compose.ui.platform.LocalView.current.rootWindowInsets
    )

    val statusBarHeight = insets.getInsets(WindowInsetsCompat.Type.statusBars()).top
    val navigationBarHeight = insets.getInsets(WindowInsetsCompat.Type.navigationBars()).bottom

    Box(
        Modifier
            .fillMaxSize()
            .padding(start = 16.dp,
                end = 16.dp,
                top = statusBarHeight.dp,
                bottom = navigationBarHeight.dp)) {
        CharacterList(Modifier.align(Alignment.TopCenter), viewModel)
    }
}

@Composable
fun CharacterList(modifier: Modifier, viewModel: CharacterListViewModel) {
    Column(modifier = modifier) {
        Header()
        List(viewModel)
    }
}

@Composable
fun Header() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(id = R.string.app_name),
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )
        Icon(
            imageVector = Icons.Filled.Star,
            contentDescription = "Menu",
            modifier = Modifier
                .padding(12.dp)
                .size(24.dp)
                .clip(CircleShape)
                .background(color = colorResource(id = R.color.purple_200)),
            tint = colorResource(id = R.color.white)
        )
    }
}

@Composable
fun List(viewModel: CharacterListViewModel) {
    val state by viewModel.state.collectAsState()

    LazyColumn {
        items(state) { character ->
            CharacterItem(character)
        }

        item {
            LaunchedEffect(Unit) {
                viewModel.loadMoreCharacters()
            }
        }
    }
}

@Composable
fun CharacterItem(character: Result) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(64.dp)
                .background(color = colorResource(id = R.color.purple_200))
        ) {
            AsyncImage(
                model = character.image,
                contentDescription = character.name,
                modifier = Modifier.fillMaxSize(),
                contentScale = androidx.compose.ui.layout.ContentScale.Crop
            )
        }
        Column(modifier = Modifier.padding(start = 16.dp)) {
            Text(text = character.name, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Text(text = character.status, fontSize = 16.sp)
            Text(text = character.location.name, fontSize = 16.sp)
        }
    }
}




