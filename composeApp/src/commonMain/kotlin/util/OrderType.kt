package util


//
// Created by Code For Android on 20/06/24.
// Copyright (c) 2024 CFA. All rights reserved.
//

sealed class OrderType {
    object Ascending : OrderType()
    object Descending : OrderType()

}