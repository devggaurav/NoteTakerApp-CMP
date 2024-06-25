package data

import domain.model.Note
import domain.model.RequestState
import kotlinx.coroutines.flow.Flow


//
// Created by Code For Android on 19/06/24.
// Copyright (c) 2024 CFA. All rights reserved.
//

interface NotesRepository {

    fun getNotes(): Flow<RequestState<List<Note>>>

    suspend fun getNoteById(note: Note): Note?

    suspend fun insertNote(note: Note)

    suspend fun updateNote(note: Note)
    suspend fun deleteNote(note: Note)


}