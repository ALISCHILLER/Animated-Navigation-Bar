package com.zar.visitzarangar.ui.bottomNav


import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawOutline
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zar.bottomnavghtioncompose.R
import com.zar.bottomnavghtioncompose.ui.theme.BottomNavghtionComposeTheme


@Composable
fun CustomBottomNavigation() {
    val items = listOf(
        BottomNavItem("Home", Icons.Default.Home),
        BottomNavItem("Tasks", Icons.Default.WorkOutline),
        BottomNavItem("Report", Icons.Default.Article),
        BottomNavItem("Settings", Icons.Default.Settings)
    )

    // Selected index state
    var selectedIndex by remember { mutableStateOf(0) }

    // Draw the curved background with border
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(72.dp)
            .background(MaterialTheme.colorScheme.background)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer(
                    shape = RoundedCornerShape(16.dp)
                )
                .border(2.dp, Color.Black, shape = RoundedCornerShape(16.dp))
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
                    .height(72.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                items.forEachIndexed { index, item ->
                    BottomNavItem(
                        item = item,
                        isSelected = index == selectedIndex,
                        onSelected = { selectedIndex = index }
                    )
                }
            }
        }
    }
}

@Composable
fun BottomNavItem(
    item: BottomNavItem,
    isSelected: Boolean,
    onSelected: () -> Unit
) {
    var size by remember { mutableStateOf(0.dp) }
    if (isSelected) {
        size = 60.dp
    } else {
        size = 32.dp
    }

    val offset by animateDpAsState(
        targetValue = if (isSelected) 0.dp else 16.dp,
        animationSpec = tween(durationMillis = 300)
    )

    Column(
        modifier = Modifier
            .clickable { onSelected() }
            .padding(8.dp)
            .graphicsLayer(
                translationY = if (isSelected) offset.value else 0f,
                scaleY = if (isSelected) 1.5f else 1f, // Scale the icon vertically
            )
    ) {
        Icon(
            modifier = Modifier
                .size(size)
                .clip(CircleShape)
                .background(
                    if (isSelected) MaterialTheme.colorScheme.primary
                    else Color.Transparent
                ),
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = item.label,
            tint = if (isSelected) Color.White else LocalContentColor.current
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = item.label,
            style = MaterialTheme.typography.labelSmall,
            color = if (isSelected) MaterialTheme.colorScheme.primary else LocalContentColor.current
        )
    }
}


data class BottomNavItem(val label: String, val icon: ImageVector)

@Preview(showBackground = true)
@Composable
fun CustomBottomNavigationPreview() {
    BottomNavghtionComposeTheme {
        CustomBottomNavigation()
    }

}

@Composable
fun CurvedCustomBottomNavigation(
    items: List<BottomNavItemm>,
    selectedItem: BottomNavItemm,
    onItemSelected: (BottomNavItemm) -> Unit
) {
    val curveSize = 32.dp
    val backgroundGradient = Brush.verticalGradient(
        colors = listOf(
            Color(0xFF2A2A2A),
            Color(0xFF4A4A4A),
            Color(0xFF2A2A2A)
        )
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
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            items.forEach { item ->
                Icon(
                    item.icon,
                    contentDescription = item.title,
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { onItemSelected(item) }
                        .padding(16.dp),
                    tint = if (selectedItem == item) item.selectedColor else item.unselectedColor
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewCurvedCustomBottomNavigation() {
    val items = listOf(
        BottomNavItemm(Icons.Default.Home, "Home", Color.Blue, Color.Gray),
        BottomNavItemm(Icons.Default.Search, "Search", Color.Blue, Color.Gray),
        BottomNavItemm(Icons.Default.WorkOutline, "Favorite", Color.Blue, Color.Gray),
        BottomNavItemm(Icons.Default.Person, "Profile", Color.Blue, Color.Gray)
    )
    val selectedItem = items[0]

    CurvedCustomBottomNavigation(items = items, selectedItem = selectedItem, onItemSelected = {})
}

data class BottomNavItemm(
    val icon: ImageVector,
    val title: String,
    val selectedColor: Color,
    val unselectedColor: Color
)

//@Composable
//fun CustomBottomNavigatio() {
//    Box(
//        modifier = Modifier
//            .fillMaxWidth()
//            .height(72.dp)
//            .graphicsLayer(
//                shape = RoundedCornerShape(
//                    topStart = 32.dp,
//                    topEnd = 32.dp,
//                    bottomStart = 0.dp,
//                    bottomEnd = 0.dp
//                )
//            )
//            .background(MaterialTheme.colorScheme.primary)
//    ) {
//        Row(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(horizontal = 16.dp)
//                .height(72.dp),
//            horizontalArrangement = Arrangement.SpaceBetween
//        ) {
//            BottomNavigationItem(Icons.Default.Home, "Home")
//            BottomNavigationItem(Icons.Default.Message, "Messages")
//            BottomNavigationItem(Icons.Default.Favorite, "Favorites")
//            BottomNavigationItem(Icons.Default.Person, "Profile")
//        }
//    }
//}
//
//@Composable
//fun BottomNavigationItem(icon: ImageVector, label: String) {
//    val color = Color.White
//    Column(
//        modifier = Modifier
//            .padding(8.dp),
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Icon(
//            imageVector = icon,
//            contentDescription = label,
//            tint = color,
//            modifier = Modifier.size(24.dp)
//        )
//        Spacer(modifier = Modifier.height(4.dp))
//        Text(
//            text = label,
//            color = color,
//            fontSize = 12.sp
//        )
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun CustomBottomNavigatioPreview() {
//    CustomBottomNavigatio()
//}


fun CustomBottomNavigationShape(size: Size): Outline {
    val curveRadius = 32f // adjust according to your design
    val path = Path().apply {
        moveTo(0f, curveRadius)
        lineTo(size.width / 2f - curveRadius, curveRadius)
        quadraticBezierTo(size.width / 2f, 0f, size.width / 2f + curveRadius, curveRadius)
        lineTo(size.width, curveRadius)
        close()
    }
    return Outline.Generic(path = path)
}
