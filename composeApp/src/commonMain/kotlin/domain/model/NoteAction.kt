package domain.model

import util.NoteOrder


//
// Created by Code For Android on 19/06/24.
// Copyright (c) 2024 CFA. All rights reserved.
//

sealed class NoteAction {
    data class Order(val noteOrder: NoteOrder) : NoteAction()
    data class DeleteNote(val note: Note) : NoteAction()
    data object RestoreNote : NoteAction()
    data object ToggleOrderSection : NoteAction()
}