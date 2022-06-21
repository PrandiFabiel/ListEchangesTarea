package com.example.listechangestarea.ui.screens

import android.view.ContentInfo
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.listechangestarea.data.remote.dto.DtoExchange

@Composable
fun ExchangesListScreen(
    viewModel: ViewModelExchange = hiltViewModel()
) {
    val state = viewModel.state.value


    Scaffold(

        topBar = {
            TopAppBar(
                title = {
                    Text("List of Exchanges")
                }
            )
        }

    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(it)
        ) {

            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(state.exchanges) { exchange ->
                    ExchangeItem(exchange = exchange, onClick = {})
                }
            }
            if (state.isLoading) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = CenterHorizontally
                ) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}

@Composable
fun ExchangeItem(
    exchange: DtoExchange,
    onClick: (DtoExchange) -> Unit
) {
    Card(
        elevation = 10.dp,
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick(exchange) }
            .padding(10.dp)
    ) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp)) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)) {
                Text(
                    text = "Name: ",
                    style = MaterialTheme.typography.body1,
                    maxLines = 1,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = exchange.name,
                    style = MaterialTheme.typography.body1,
                    maxLines = 1,
                )
            }
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)) {
                Text(
                    text = "Description: ",
                    style = MaterialTheme.typography.body1,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = exchange.description ?: "Is Empty",
                    style = MaterialTheme.typography.body1,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)) {
                Text(
                    text = "Status: ",
                    style = MaterialTheme.typography.body2,
                    fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = if (exchange.active) "Active" else "UnActive",
                    style = MaterialTheme.typography.body2,
                    color = if (exchange.active) Color.Green else Color.Red,
                    fontStyle = FontStyle.Italic,
                )
            }
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)) {
                Text(
                    text = "Last Update: ",
                    style = MaterialTheme.typography.body2,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = exchange.last_updated,
                    style = MaterialTheme.typography.body2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }

    }
}
