package domain.use_case

import data.NotesRepository
import domain.model.Note
import domain.model.RequestState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import util.NoteOrder
import util.OrderType


//
// Created by Code For Android on 20/06/24.
// Copyright (c) 2024 CFA. All rights reserved.
//

class GetNotes(private val repository: NotesRepository) {


   operator fun invoke(
       noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending)
   ) : Flow<List<Note>> {
       return repository.getNotes().map { notes ->
           when(noteOrder.orderType){
               is OrderType.Ascending -> {
                   when(noteOrder){
                       is NoteOrder.Title -> notes.getSuccessData().sortedBy { it.title.lowercase() }
                       is NoteOrder.Date -> notes.getSuccessData().sortedBy { it.date }
                       is NoteOrder.Color -> notes.getSuccessData().sortedBy { it.color }
                   }
               }
               is OrderType.Descending -> {
                   when(noteOrder){
                       is NoteOrder.Title -> notes.getSuccessData().sortedByDescending { it.title.lowercase() }
                       is NoteOrder.Date -> notes.getSuccessData().sortedByDescending { it.date }
                       is NoteOrder.Color -> notes.getSuccessData().sortedByDescending { it.color }
                   }
               }




           }

       }


   }
}