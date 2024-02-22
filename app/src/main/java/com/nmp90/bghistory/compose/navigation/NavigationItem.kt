package com.nmp90.bghistory.compose.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.icons.rounded.List
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavigationItem(var route: String, val icon: ImageVector?, var title: String) {
    object Periods : NavigationItem("Periods", Icons.Rounded.Home, "Периоди")
    object Events : NavigationItem("Events", Icons.Rounded.List, "Събития")
    object Capitals : NavigationItem("Capitals", Icons.Rounded.Info, "Столици")
}