package com.example.vkhomework.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.vkhomework.R

@Composable
fun NumberGridScreen(
    viewModel: NumberGridViewModel = viewModel(),
) {
    val itemCount by viewModel.itemCount.collectAsState()
    val gridPadding = dimensionResource(R.dimen.grid_padding)
    val gridSpacing = dimensionResource(R.dimen.grid_spacing)

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { viewModel.addItem() },
                containerColor = colorResource(R.color.fab_background),
                contentColor = colorResource(R.color.fab_icon_tint),
                modifier = Modifier.padding(dimensionResource(R.dimen.fab_margin)),
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(R.string.add_item_content_description)
                )
            }
        }
    ) { innerPadding ->
        val layoutDirection = LocalLayoutDirection.current

        LazyVerticalGrid(
            columns = GridCells.Fixed(integerResource(R.integer.grid_column_count)),
            contentPadding = PaddingValues(
                start = innerPadding.calculateStartPadding(layoutDirection) + gridPadding,
                top = innerPadding.calculateTopPadding() + gridPadding,
                end = innerPadding.calculateEndPadding(layoutDirection) + gridPadding,
                bottom = innerPadding.calculateBottomPadding() + dimensionResource(R.dimen.grid_bottom_content_padding)
            ),
            horizontalArrangement = Arrangement.spacedBy(gridSpacing),
            verticalArrangement = Arrangement.spacedBy(gridSpacing),
            modifier = Modifier.fillMaxSize()
        ) {
            items(count = itemCount, key = { it }) { number ->
                SquareNumberCard(number = number)
            }
        }
    }
}

@Composable
fun SquareNumberCard(
    number: Int,
    modifier: Modifier = Modifier,
) {
    val backgroundColor = colorResource(
        if ((number % 2) == 0) R.color.item_even_background else R.color.item_odd_background
    )
    val textSizeSp = with(LocalDensity.current) { dimensionResource(R.dimen.item_text_size).toSp() }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(1f),
        shape = RoundedCornerShape(dimensionResource(R.dimen.card_corner_radius)),
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        elevation = CardDefaults.cardElevation(defaultElevation = dimensionResource(R.dimen.card_elevation))
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = number.toString(),
                color = colorResource(R.color.item_text_color),
                fontSize = textSizeSp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
