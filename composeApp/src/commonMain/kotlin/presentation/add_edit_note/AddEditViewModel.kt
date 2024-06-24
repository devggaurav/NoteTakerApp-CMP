package presentation.add_edit_note

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.toArgb
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import domain.model.Note
import domain.use_case.NoteUseCases
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import org.mongodb.kbson.ObjectId


//
// Created by Code For Android on 24/06/24.
// Copyright (c) 2024 CFA. All rights reserved.
//

class AddEditViewModel(private val noteUseCases: NoteUseCases) : ScreenModel {

    private val _noteTitle = mutableStateOf(
        NoteTextFieldState(
            hint = "Enter title..."
        )
    )
    val noteTitle: State<NoteTextFieldState> = _noteTitle

    private val _noteContent = mutableStateOf(
        NoteTextFieldState(
            hint = "Enter some content"
        )
    )
    val noteContent: State<NoteTextFieldState> = _noteContent

    private val _noteColor = mutableStateOf(Note.noteColors.random().toArgb())
    val noteColor: State<Int> = _noteColor

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var currentNoteId: ObjectId? = null


    fun getNote(note: Note) {
        screenModelScope.launch {
            noteUseCases.getNote(note)?.also { note ->
                currentNoteId = note._id
                _noteTitle.value = _noteTitle.value.copy(
                    text = note.title,
                    isHintVisible = false
                )
                _noteContent.value = _noteContent.value.copy(
                    text = note.content,
                    isHintVisible = false
                )
                _noteColor.value = note.color


            }


        }


    }


    fun onEvent(addEditNoteEvent: AddEditNoteEvent) {
        when (addEditNoteEvent) {
            is AddEditNoteEvent.ChangeColor -> {
                _noteColor.value = addEditNoteEvent.color
            }

            is AddEditNoteEvent.ChangeContentFocus -> TODO()
            is AddEditNoteEvent.ChangeTitleFocus -> TODO()
            is AddEditNoteEvent.EnteredContent -> TODO()
            is AddEditNoteEvent.EnteredTitle -> TODO()
            AddEditNoteEvent.SaveNote -> TODO()
        }


    }


    sealed class UiEvent {
        data class ShowSnackBar(val message: String) : UiEvent()
        data object SaveNote : UiEvent()
    }
}