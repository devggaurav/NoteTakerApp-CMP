package domain.model


//
// Created by Code For Android on 19/06/24.
// Copyright (c) 2024 CFA. All rights reserved.
//

sealed class NoteAction {
     data class getNoteById(val note: Note) : NoteAction()
     data class addNote(val note: Note) : NoteAction()
     data class deleteNote(val note: Note) : NoteAction()

}