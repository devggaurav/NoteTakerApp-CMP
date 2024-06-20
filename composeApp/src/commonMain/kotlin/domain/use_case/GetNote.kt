package domain.use_case

import data.NotesRepository
import domain.model.Note


//
// Created by Code For Android on 20/06/24.
// Copyright (c) 2024 CFA. All rights reserved.
//

class GetNote(private val repository: NotesRepository) {

    suspend operator fun invoke(note: Note): Note? {
        return repository.getNoteById(note)
    }
}