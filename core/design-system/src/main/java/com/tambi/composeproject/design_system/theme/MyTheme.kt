package com.tambi.composeproject.design_system.theme

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalMinimumInteractiveComponentEnforcement
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun MyTheme(content: @Composable () -> Unit) {
    CompositionLocalProvider(
        // Disable minimum size force for interactive component
        LocalMinimumInteractiveComponentEnforcement provides false,
        // Option for disable font scale
        LocalDensity provides Density(LocalDensity.current.density, 1f),
    ) {
        MaterialTheme(content = content)
    }
}
