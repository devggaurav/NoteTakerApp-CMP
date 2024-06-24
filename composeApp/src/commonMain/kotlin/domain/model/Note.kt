package domain.model

import androidx.compose.ui.graphics.Color
import domain.model.colorList.fifth
import domain.model.colorList.first
import domain.model.colorList.fourth
import domain.model.colorList.second
import domain.model.colorList.third
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId


//
// Created by Code For Android on 18/06/24.
// Copyright (c) 2024 CFA. All rights reserved.
//


class Note : RealmObject {

    var title: String = ""
    var content: String = ""
    var date: Long = 0
    var color: Int = 0

    @PrimaryKey
    var _id: ObjectId = ObjectId()

    companion object {
        val noteColors = listOf(first, second, third, fourth, fifth)
    }

}

class InvalidNoteException(message: String) : Exception(message)

object colorList {

    val first: Color = Color(0xFFE57373)
    val second: Color = Color(0xFFF06292)
    val third: Color = Color(0xFFBA68C8)
    val fourth: Color = Color(0xFF9575CD)
    val fifth: Color = Color(0xFF7986CB)


}