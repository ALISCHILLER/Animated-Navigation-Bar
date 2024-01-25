package com.zar.bottomnavghtioncompose.component.test

import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Article
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.WorkOutline
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zar.bottomnavghtioncompose.component.test.BottomNavItem

@Composable
fun CurvedBottomNavigation(

) {
    val curveSize = 12.dp
    val backgroundGradient = Brush.verticalGradient(
        colors = listOf(
            Color(0xFFE7E7E7),
            Color(0xFFFFFFFF),
            Color(0xFFD8D8D8)
        )
    )
    val items = listOf(
        BottomNavItem("Home", Icons.Default.Home),
        BottomNavItem("Tasks", Icons.Default.WorkOutline),
        BottomNavItem("Report", Icons.Default.Article),
        BottomNavItem("Settings", Icons.Default.Settings)
    )
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(72.dp)
            .graphicsLayer(
                clip = true,
                shape = RoundedCornerShape(topStart = curveSize, topEnd = curveSize)
            )
            .background(backgroundGradient)
    ) {

        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            items.forEachIndexed { index, item ->
                Column(
                    modifier = Modifier
                    ,
                    verticalArrangement = Arrangement.SpaceBetween
                ){
                    Image(imageVector = item.icon , contentDescription = item.label)
                    Text(text = item.label)
                }
            }


        }

    }
}


data class BottomNa(val label: String, val icon: ImageVector)
@Preview
@Composable
fun CurvedBottomNavigationPreview() {
    CurvedBottomNavigation()
}


//@Composable
//fun AnimatedNavigationBar(
//    modifier: Modifier = Modifier,
//    selectedIndex: Int,
//    barColor: Color = Color.White,
//    ballColor: Color = Color.Black,
//    content: @Composable () -> Unit,
//){
//
//
//    Box(
//        modifier = modifier
//    ) {
//        Layout(
//            modifier = Modifier
//                .graphicsLayer {
//                    clip = true
//                    shape = indentShape.value
//                }
//                .background(barColor),
//            content = content,
//            measurePolicy = measurePolicy
//        )
//
//
//    }
//
//
//}
//
//@Preview
//@Composable
//fun AnimatedNavigationBarPreview() {
//    AnimatedNavigationBar()
//}




