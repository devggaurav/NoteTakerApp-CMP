package domain.use_case

import data.NotesRepository
import domain.model.Note


//
// Created by Code For Android on 25/06/24.
// Copyright (c) 2024 CFA. All rights reserved.
//

class UpdateNote(
    private val repository: NotesRepository
) {
    suspend operator fun invoke(note: Note) {
        if (note.title.isBlank()) {
            throw IllegalArgumentException("The title of the note can't be empty")
        }
        if (note.content.isBlank()) {
            throw IllegalArgumentException("The content of the note can't be empty")
        }
        repository.updateNote(note)
    }


}