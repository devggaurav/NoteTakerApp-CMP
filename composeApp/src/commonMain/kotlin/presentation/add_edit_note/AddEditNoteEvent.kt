package presentation.add_edit_note

import androidx.compose.ui.focus.FocusState


//
// Created by Code For Android on 24/06/24.
// Copyright (c) 2024 CFA. All rights reserved.
//

sealed class AddEditNoteEvent {

    data class EnteredTitle(val value: String) : AddEditNoteEvent()
    data class ChangeTitleFocus(val focusState: FocusState) : AddEditNoteEvent()
    data class EnteredContent(val value: String) : AddEditNoteEvent()
    data class ChangeContentFocus(val focusState: FocusState) : AddEditNoteEvent()
    data class ChangeColor(val color: Int) : AddEditNoteEvent()
    data object SaveNote : AddEditNoteEvent()

}