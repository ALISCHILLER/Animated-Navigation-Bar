package com.zar.bottomnavghtioncompose.component.botttomnava

import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AcUnit
import androidx.compose.material.icons.filled.AddAlert
import androidx.compose.material.icons.filled.Analytics
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.isSpecified
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.zar.bottomnavghtioncompose.component.botttomnava.anim.BallAnimInfo
import com.zar.bottomnavghtioncompose.component.botttomnava.anim.BallAnimation
import com.zar.bottomnavghtioncompose.component.botttomnava.anim.Height
import com.zar.bottomnavghtioncompose.component.botttomnava.anim.IndentAnimation
import com.zar.bottomnavghtioncompose.component.botttomnava.anim.Parabolic
import com.zar.bottomnavghtioncompose.component.botttomnava.anim.ShapeCornerRadius
import com.zar.bottomnavghtioncompose.component.botttomnava.anim.shapeCornerRadius
import com.zar.bottomnavghtioncompose.component.botttomnava.layout.animatedNavBarMeasurePolicy
import com.zar.bottomnavghtioncompose.ui.theme.barcolor
import com.zar.bottomnavghtioncompose.component.botttomnava.util.ballTransform
import com.zar.bottomnavghtioncompose.component.botttomnava.util.noRippleClickable

@Composable
fun AnimatedNavigationBar(
    modifier: Modifier = Modifier,
    selectedIndex: Int,
    barColor: Color = Color.White,
    ballColor: Color = Color.Black,
    cornerRadius: ShapeCornerRadius = shapeCornerRadius(0f),
    ballAnimation: BallAnimation = Parabolic(tween(300)),
    indentAnimation: IndentAnimation = Height(tween(300)),
    navigationBarItems: Array<NavigationBarItems>,
    content: @Composable () -> Unit,
) {
    var itemPositions by remember { mutableStateOf(listOf<Offset>()) }
    val measurePolicy = animatedNavBarMeasurePolicy {
        itemPositions = it.map { xCord ->
            Offset(xCord, 0f)
        }
    }

    val selectedItemOffset by remember(selectedIndex, itemPositions) {
        derivedStateOf {
            if (itemPositions.isNotEmpty()) itemPositions[selectedIndex] else Offset.Unspecified
        }
    }

    val indentShape = indentAnimation.animateIndentShapeAsState(
        shapeCornerRadius = cornerRadius,
        targetOffset = selectedItemOffset
    )

    val ballAnimInfoState = ballAnimation.animateAsState(
        targetOffset = selectedItemOffset,
    )

    Box(
        modifier = modifier
    ) {
        Layout(
            modifier = Modifier
                .graphicsLayer {
                    clip = true
                    shape = indentShape.value
                }
                .background(barColor),
            content = content,
            measurePolicy = measurePolicy
        )

        if (ballAnimInfoState.value.offset.isSpecified) {
            ColorBall(
                ballAnimInfo = ballAnimInfoState.value,
                ballColor = ballColor,
                sizeDp = ballSize,
                nav = navigationBarItems.get(selectedIndex)
            )
        }
    }

}

@Composable
private fun ColorBall(
    modifier: Modifier = Modifier,
    ballColor: Color,
    ballAnimInfo: BallAnimInfo,
    sizeDp: Dp,
    nav:NavigationBarItems
) {
    Column(
        modifier = modifier
            .ballTransform(ballAnimInfo)
            .size(sizeDp)
            .clip(shape = CircleShape)
            .background(ballColor),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Icon(
            modifier = Modifier.size(26.dp),
            imageVector = nav.icon,
            contentDescription = "Bottom Bar",
            tint = Color.White
        )
    }
}


val ballSize = 70.dp
@Composable
fun BottomNavaghtion() {
    var selectedIndex by remember {
        mutableStateOf(0)
    }
    val navigationBarItem = remember {
       NavigationBarItems.values()
    }
    AnimatedNavigationBar(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 40.dp)
            .height(85.dp),
        selectedIndex = selectedIndex,
        cornerRadius = shapeCornerRadius(cornerRadius = 0.dp),
        ballAnimation = Parabolic(tween(300)),
        indentAnimation = Height(tween(300)),
        barColor = barcolor,
        ballColor = Color.Red,
        navigationBarItems =NavigationBarItems.values()
    ) {
        navigationBarItem.forEachIndexed() { index, it ->

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .noRippleClickable {
                        selectedIndex = index
                        when (index) {

                            else -> {}
                        }

                    },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    modifier = Modifier.size(26.dp),
                    imageVector = it.icon,
                    contentDescription = "Bottom Bar",
                    tint = Color.Black
                )
            }
        }
    }
}
enum class NavigationBarItems(val icon: ImageVector) {
    Setting(icon = Icons.Default.Settings),
    Report(icon = Icons.Default.Analytics),
    g(icon = Icons.Default.Home),
    h(icon = Icons.Default.AcUnit),
    u(icon = Icons.Default.AddAlert),

}