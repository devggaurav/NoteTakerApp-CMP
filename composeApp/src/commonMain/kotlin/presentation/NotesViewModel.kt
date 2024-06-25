package presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import domain.model.Note
import domain.model.NoteAction
import domain.use_case.NoteUseCases
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import util.NoteOrder
import util.OrderType


//
// Created by Code For Android on 21/06/24.
// Copyright (c) 2024 CFA. All rights reserved.
//

class NotesViewModel(
    private val noteUseCases: NoteUseCases
) : ScreenModel {

    private val _state = mutableStateOf(NoteState())
    val state: State<NoteState> = _state

    private var recentlyDeletedNote: Note? = null


    private var getNotesJob: Job? = null

    init {
        getNotes(NoteOrder.Date(OrderType.Descending))
    }

    fun onAction(action: NoteAction) {
        when (action) {

            is NoteAction.DeleteNote -> {
                screenModelScope.launch {
                    noteUseCases.deleteNote(action.note)
                    recentlyDeletedNote = action.note
                }
            }

            is NoteAction.Order -> {
                if (state.value.noteOrder::class == action.noteOrder::class &&
                    state.value.noteOrder.orderType == action.noteOrder.orderType
                ) {
                    return
                }
                getNotes(action.noteOrder)
            }

            NoteAction.RestoreNote -> {
                screenModelScope.launch {
                    val newNote = Note()
                    recentlyDeletedNote?.let { it ->
                        newNote.apply {
                            title = it.title
                            content = it.content
                            date = it.date
                            color = it.color
                        }

                    }

                    noteUseCases.addNote(newNote ?: return@launch)
                    recentlyDeletedNote = null
                }
            }

            NoteAction.ToggleOrderSection -> {
                _state.value = state.value.copy(
                    isOrderSectionVisible = !state.value.isOrderSectionVisible
                )
            }
        }


    }


    private fun getNotes(noteOrder: NoteOrder) {
        getNotesJob?.cancel()
        getNotesJob = noteUseCases.getNotes(noteOrder).onEach { notes ->
            _state.value = state.value.copy(
                notes = notes.getSuccessData(),
                noteOrder = noteOrder
            )

        }.launchIn(screenModelScope)

    }


}