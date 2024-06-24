package presentation.homeComponent


//
// Created by Code For Android on 24/06/24.
// Copyright (c) 2024 CFA. All rights reserved.
//

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb

fun blendARGB(color1: Int, color2: Int, ratio: Float): Int {
    val inverseRatio = 1 - ratio
    val a = (Color(color1).alpha * 255 * ratio + Color(color2).alpha * 255 * inverseRatio).toInt()
    val r = (Color(color1).red * 255 * ratio + Color(color2).red * 255 * inverseRatio).toInt()
    val g = (Color(color1).green * 255 * ratio + Color(color2).green * 255 * inverseRatio).toInt()
    val b = (Color(color1).blue * 255 * ratio + Color(color2).blue * 255 * inverseRatio).toInt()
    return (a shl 24) or (r shl 16) or (g shl 8) or b
}