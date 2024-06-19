package data

import domain.model.Note
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration


//
// Created by Code For Android on 19/06/24.
// Copyright (c) 2024 CFA. All rights reserved.
//

class NotesDb {

    private var realm: Realm? = null


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
}