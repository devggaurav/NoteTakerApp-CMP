package data

import domain.model.Note
import domain.model.RequestState
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.ext.query
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map


//
// Created by Code For Android on 19/06/24.
// Copyright (c) 2024 CFA. All rights reserved.
//

class NotesDb : NotesRepository {

    private var realm: Realm? = null

    init {
        configureDb()
    }


    fun configureDb() {

        if (realm == null || realm!!.isClosed()) {

            val config = RealmConfiguration.Builder(
                schema = setOf(Note::class)
            )
                .compactOnLaunch()
                .build()
            realm = Realm.open(config)
        }


    }

    override fun getNotes(): Flow<RequestState<List<Note>>> {
        return realm?.query<Note>()
            ?.asFlow()?.map { result ->
                RequestState.Success(result.list)
            } ?: flowOf(RequestState.Error(("Realm is null")))
    }

    override suspend fun getNoteById(note: Note): Note? {
        return realm?.query<Note>(query = "_id == $0", note._id)?.first()?.find()
    }

    override suspend fun insertNote(note: Note) {
        realm?.write { copyToRealm(note) }
    }

    override suspend fun deleteNote(note: Note) {
        realm?.write {
            try {
                val queriedTask = query<Note>(query = "_id == $0", note._id)
                    .first()
                    .find()
                queriedTask?.let {
                    findLatest(it)?.let { currentNote ->
                        delete(currentNote)
                    }
                }
            } catch (e: Exception) {
                println(e)
            }
        }
    }
}