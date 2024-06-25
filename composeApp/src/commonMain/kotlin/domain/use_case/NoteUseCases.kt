package domain.use_case


//
// Created by Code For Android on 20/06/24.
// Copyright (c) 2024 CFA. All rights reserved.
//

data class NoteUseCases(
    val getNotes: GetNotes,
    val deleteNote: DeleteNote,
    val addNote: AddNote,
    val getNote: GetNote,
    val updateNote : UpdateNote
)
