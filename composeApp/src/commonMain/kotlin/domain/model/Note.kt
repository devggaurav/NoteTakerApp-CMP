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
import ui.theme.errorContainerDarkMediumContrast
import ui.theme.errorDarkMediumContrast
import ui.theme.inversePrimaryLight
import ui.theme.outlineDarkHighContrast
import ui.theme.tertiaryDark


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

    val first: Color = inversePrimaryLight
    val second: Color = tertiaryDark
    val third: Color = errorDarkMediumContrast
    val fourth: Color = outlineDarkHighContrast
    val fifth: Color = errorContainerDarkMediumContrast


}