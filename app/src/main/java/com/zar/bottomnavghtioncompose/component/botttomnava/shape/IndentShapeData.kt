package com.zar.bottomnavghtioncompose.component.botttomnava.shape

import com.zar.bottomnavghtioncompose.component.botttomnava.anim.ShapeCornerRadius
import com.zar.bottomnavghtioncompose.component.botttomnava.anim.shapeCornerRadius


data class IndentShapeData(
    val xIndent: Float = 0f,
    val height: Float = 0f,
    val width: Float = 0f,
    val cornerRadius: ShapeCornerRadius = shapeCornerRadius(0f),
    val ballOffset: Float = 0f,
)