package util

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.FontResource


//
// Created by Code For Android on 26/06/24.
// Copyright (c) 2024 CFA. All rights reserved.
//

@Composable
fun getFonts(res : FontResource) : FontFamily = FontFamily(Font(res))