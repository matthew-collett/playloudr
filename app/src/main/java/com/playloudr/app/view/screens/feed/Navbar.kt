//package com.playloudr.app.view.screens.feed
//
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.material.BottomNavigation
//import androidx.compose.material.BottomNavigationItem
//import androidx.compose.material.Icon
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.AccountCircle
//import androidx.compose.material.icons.filled.AddCircleOutline
//import androidx.compose.material.icons.filled.Home
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.tooling.preview.Preview
//
//@Composable
//fun BottomNavBar() {
//    var selectedTab by remember { mutableStateOf(0) }
//
//    BottomNavigation(
//        backgroundColor = Color.White,
//        contentColor = Color.Black,
//        modifier = Modifier.fillMaxWidth()
//    ) {
//        BottomNavigationItem(
//            icon = { Icon(imageVector = Icons.Default.Home, contentDescription = "Home") },
//            selected = selectedTab == 0,
//            onClick = { selectedTab = 0 }
//        )
//
//        BottomNavigationItem(
//            icon = { Icon(imageVector = Icons.Default.AddCircleOutline, contentDescription = "Post") },  // Changed to AddCircleOutline
//            selected = selectedTab == 1,
//            onClick = { selectedTab = 1 }
//        )
//
//        BottomNavigationItem(
//            icon = { Icon(imageVector = Icons.Default.AccountCircle, contentDescription = "View Account") },
//            selected = selectedTab == 2,
//            onClick = { selectedTab = 2 }
//        )
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun PreviewBottomNavBar() {
//    Box(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(Color(0xFFF2F2F2)),
//        contentAlignment = Alignment.BottomCenter
//    ) {
//        BottomNavBar()
//    }
//}
