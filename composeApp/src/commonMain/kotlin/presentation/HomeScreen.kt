package presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel


//
// Created by Code For Android on 21/06/24.
// Copyright (c) 2024 CFA. All rights reserved.
//

class HomeScreen : Screen {

    @Composable
    override fun Content() {

        val viewModel = getScreenModel<NotesViewModel>()
        val state = viewModel.state.value
        val scaffoldState = remember { SnackbarHostState() }
        val scope = rememberCoroutineScope()

        Scaffold(
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {

                    },
                    containerColor = Color.Magenta

                ) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "Add Note")
                }
            },
            snackbarHost = { SnackbarHost(hostState = scaffoldState) }
        ) { paddingValues ->

            Column(
                modifier = Modifier.fillMaxSize().padding(paddingValues)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Your Note",
                        style = MaterialTheme.typography.headlineLarge
                    )
                    IconButton(
                        onClick = {},

                        ) {
                        Icon(imageVector = Icons.Default.Edit, contentDescription = "Sort")
                    }


                }
                AnimatedVisibility(
                    visible = state.isOrderSectionVisible,
                    enter = fadeIn() + slideInVertically(),
                    exit = fadeOut() + slideOutVertically()
                ){



                }


            }


        }


    }
}