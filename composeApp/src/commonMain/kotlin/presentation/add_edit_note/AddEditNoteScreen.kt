package presentation.add_edit_note

import androidx.compose.animation.Animatable
import androidx.compose.animation.core.Animatable
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import domain.model.Note
import kotlinx.coroutines.flow.collectLatest
import presentation.NotesViewModel


//
// Created by Code For Android on 25/06/24.
// Copyright (c) 2024 CFA. All rights reserved.
//

data class AddEditNoteScreen(val note: Note?) : Screen {

    @Composable
    override fun Content() {
        val noteColor = note?.color
        val viewModel = getScreenModel<AddEditViewModel>()
        val scaffoldState = remember { SnackbarHostState() }
        val scope = rememberCoroutineScope()

        val titleState = viewModel.noteTitle.value
        val contentState = viewModel.noteContent.value

        val noteBackGroundAnimatable = remember {
            noteColor?.let { it ->
                Animatable(
                    Color(if (it != -1) it else viewModel.noteColor.value)
                )
            }
        }

        LaunchedEffect(key1 = true) {

            viewModel.eventFlow.collectLatest { event ->
                when (event) {
                    AddEditViewModel.UiEvent.SaveNote -> {

                    }
                    is AddEditViewModel.UiEvent.ShowSnackBar -> {
                        scaffoldState.showSnackbar(message = event.message)

                    }
                }


            }


        }
        Scaffold(



        ){

        }



    }

}
