package com.zar.bottomnavghtioncompose.component.botttomnava.util

fun lerp(start: Float, stop: Float, fraction: Float) =
    (start * (1 - fraction) + stop * fraction)