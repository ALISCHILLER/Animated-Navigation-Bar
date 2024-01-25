package com.zar.bottomnavghtioncompose.component.test


import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.material.BottomNavigation
import androidx.compose.ui.Alignment
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.layout.onGloballyPositioned

@Composable
private fun BottomNavigationCurve() {
    var selectedItem by remember { mutableStateOf(0) }

    val items = listOf(
        Icons.Default.Home,
        Icons.Default.Search,
        Icons.Default.Add,
        Icons.Default.Favorite,
        Icons.Default.AccountCircle,
    )

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Bottom
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .background(Color.Blue)
        ) {
            // Bottom navigation curve

            Canvas(
                modifier = Modifier
                    .width(70.dp)
                    .height(90.dp)
                    .padding(bottom = 8.dp)
                    .graphicsLayer(
                        translationY = (-8).toFloat(),
                        clip = true,
                        shape = CircleShape
                    )
            ) {
                val cornerRadius = 32f

                drawRoundRect(
                    color = Color.Red,
                    size = Size(size.width, size.height + 8.dp.toPx()),
                    cornerRadius = CornerRadius(12f, 12F)
                )
            }


            // Bottom navigation items
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                items.forEachIndexed { index, icon ->
                    BottomNavigationItem(
                        icon = {
                            Icon(
                                imageVector = icon,
                                contentDescription = null,
                                tint = if (index == selectedItem) {
                                    MaterialTheme.colorScheme.onPrimary
                                } else {
                                    MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
                                }
                            )
                        },
                        selected = index == selectedItem,
                        onClick = {
                            selectedItem = index
                        },
                        modifier = Modifier
                            .weight(1f)
                            .graphicsLayer(
                                translationY = if (index == selectedItem) 4.dp.value else 0f
                            ),

                        )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BottomNavigationCurvePreview() {
    BottomNavigationCurve()
}


@Composable
fun CurvedBottomNavigatio() {
    var selectedItem by remember { mutableStateOf(0) }
    var bottomNavigationWidth by remember { mutableStateOf(0) }

    val items = listOf(
        Icons.Default.Home,
        Icons.Default.Search,
        Icons.Default.Add,
        Icons.Default.Favorite,
        Icons.Default.AccountCircle,
    )

    val curveHeight = 20.dp

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = curveHeight)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .background(MaterialTheme.colorScheme.surface)
        ) {
            BottomNavigation(
                backgroundColor = Color.Transparent,
                elevation = 0.dp,
                modifier = Modifier
                    .fillMaxSize()
                    .layoutId("bottomNavigation")
            ) {
                items.forEachIndexed { index, icon ->
                    BottomNavigationItem(
                        icon = {
                            val selected = index == selectedItem
                            val scale by animateFloatAsState(
                                targetValue = if (selected) 1.2f else 1f,
                                tween(
                                    durationMillis = 300,
                                    easing = FastOutSlowInEasing
                                ), label = ""
                            )
                            val iconColor =
                                if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onBackground.copy(
                                    alpha = 0.6f
                                )

                            Box(
                                modifier = Modifier
                                    .graphicsLayer(scaleX = scale, scaleY = scale)
                                    .padding(8.dp)
                            ) {
                                Icon(
                                    imageVector = icon,
                                    contentDescription = null,
                                    tint = iconColor
                                )
                            }
                        },
                        selected = index == selectedItem,
                        onClick = {
                            selectedItem = index
                        },
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            // Curved indicator for the selected item
            if (selectedItem >= 0 && selectedItem < items.size) {
                val selectedNavItemWidth = (bottomNavigationWidth / items.size) * selectedItem
                val centerOffset = (bottomNavigationWidth / items.size) * 0.5f + selectedNavItemWidth
                val animatedFraction by animateFloatAsState(
                    targetValue = if (selectedItem >= 0 && selectedItem < items.size) 1f else 0f,
                    tween(
                        durationMillis = 300,
                        easing = FastOutSlowInEasing
                    ), label = ""
                )
                Canvas(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(curveHeight)
                        .align(Alignment.BottomCenter)
                        .onGloballyPositioned { layoutCoordinates ->
                            bottomNavigationWidth = layoutCoordinates.size.width.toInt()
                        }
                ) {


                    drawCircle(
                        color = Color.Blue,
                        radius = bottomNavigationWidth * animatedFraction,
                        center = Offset(centerOffset, curveHeight.toPx())
                    )
                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun CurvedBottomNavigatioPreview() {

    CurvedBottomNavigatio()

}






