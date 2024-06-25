package di

import data.NotesDb
import data.NotesRepository
import domain.use_case.AddNote
import domain.use_case.DeleteNote
import domain.use_case.GetNote
import domain.use_case.GetNotes
import domain.use_case.NoteUseCases
import domain.use_case.UpdateNote
import org.koin.core.context.startKoin
import org.koin.dsl.module
import presentation.NotesViewModel
import presentation.add_edit_note.AddEditViewModel


//
// Created by Code For Android on 21/06/24.
// Copyright (c) 2024 CFA. All rights reserved.
//


val appModule = module {
    single<NotesRepository> { NotesDb() }
    single<NoteUseCases> {
        NoteUseCases(
            getNotes = GetNotes(get()),
            addNote = AddNote(get()),
            getNote = GetNote(get()),
            deleteNote = DeleteNote(get()),
            updateNote = UpdateNote(get())
        )
    }
    factory {
        NotesViewModel(get())
    }
    factory {
        AddEditViewModel(get())
    }
}


fun initializeKoin() {
    startKoin {
        modules(appModule)
    }

}