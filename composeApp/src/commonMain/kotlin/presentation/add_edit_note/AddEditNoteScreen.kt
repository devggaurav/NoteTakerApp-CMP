package presentation.add_edit_note

import androidx.compose.animation.Animatable
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import domain.model.Note
import getPlatform
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import presentation.NotesViewModel
import presentation.add_edit_note.component.TransparentHintTextField


//
// Created by Code For Android on 25/06/24.
// Copyright (c) 2024 CFA. All rights reserved.
//

data class AddEditNoteScreen(val note: Note?) : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val viewModel = getScreenModel<AddEditViewModel>()


        val noteColor = note?.color ?: viewModel.noteColor.value
        val scaffoldState = remember { SnackbarHostState() }
        val scope = rememberCoroutineScope()

        val titleState = viewModel.noteTitle.value
        val contentState = viewModel.noteContent.value

        val noteBackGroundAnimatable = remember {
            Animatable(
                Color(if (noteColor != -1) noteColor else viewModel.noteColor.value)
            )
        }

        LaunchedEffect(note){
            note?.let { it ->
                viewModel.getNote(it)
            }
        }

        LaunchedEffect(key1 = true) {

            viewModel.eventFlow.collectLatest { event ->
                when (event) {
                    AddEditViewModel.UiEvent.SaveNote -> {
                        navigator.pop()
                    }

                    is AddEditViewModel.UiEvent.ShowSnackBar -> {
                        scaffoldState.showSnackbar(message = event.message)

                    }
                }


            }


        }
        Scaffold(
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                        viewModel.onEvent(AddEditNoteEvent.SaveNote)
                    },
                    containerColor = MaterialTheme.colorScheme.primary
                ) {
                    Icon(imageVector = Icons.Filled.Done, contentDescription = "Save note")

                }

            },
            snackbarHost = { SnackbarHost(scaffoldState) }


        ) { paddingValues ->
            val topPadding =
                if (getPlatform().isAndroid) paddingValues.calculateTopPadding() + 20.dp else paddingValues.calculateTopPadding()
            Column(
                modifier = Modifier.fillMaxSize().background(noteBackGroundAnimatable.value)
                    .padding(
                        top = topPadding,
                        bottom = paddingValues.calculateBottomPadding(),
                        start = 10.dp,
                        end = 10.dp
                    )
            ) {

                Row(
                    modifier = Modifier.fillMaxWidth().padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Note.noteColors.forEach { color ->
                        val colorInt = color.toArgb()
                        Box(modifier = Modifier.size(50.dp)
                            .shadow(15.dp, CircleShape)
                            .clip(CircleShape)
                            .background(color)
                            .border(
                                width = 3.dp,
                                color = if (viewModel.noteColor.value == colorInt) {
                                    Color.Black
                                } else Color.Transparent,
                                shape = CircleShape
                            ).clickable {

                                scope.launch {
                                    noteBackGroundAnimatable.animateTo(
                                        targetValue = Color(colorInt),
                                        animationSpec = tween(
                                            durationMillis = 500
                                        )
                                    )
                                }
                                viewModel.onEvent(AddEditNoteEvent.ChangeColor(colorInt))
                            }

                        )


                    }


                }
                Spacer(modifier = Modifier.height(16.dp))
                TransparentHintTextField(
                    text = titleState.text,
                    hint = titleState.hint,
                    onValueChange = {
                        viewModel.onEvent(AddEditNoteEvent.EnteredTitle(it))
                    },
                    onFocusChange = {
                        viewModel.onEvent(AddEditNoteEvent.ChangeTitleFocus(it))
                    },
                    isHintVisible = titleState.isHintVisible,
                    singleLine = true,
                    textStyle = MaterialTheme.typography.headlineLarge

                )
                Spacer(modifier = Modifier.height(16.dp))

                TransparentHintTextField(
                    text = contentState.text,
                    hint = contentState.hint,
                    onValueChange = { it ->

                        viewModel.onEvent(AddEditNoteEvent.EnteredContent(it))
                    },
                    onFocusChange = {
                        viewModel.onEvent(AddEditNoteEvent.ChangeContentFocus(it))
                    },
                    isHintVisible = contentState.isHintVisible,
                    textStyle = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.fillMaxWidth()

                )


            }


        }


    }

}
