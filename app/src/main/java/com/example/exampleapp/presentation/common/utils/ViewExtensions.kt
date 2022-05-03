package com.example.exampleapp.presentation.common.utils

import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment

fun AppCompatActivity.findNavController(
    @IdRes navHostViewId: Int
): NavController {
    val navHostFragment =
        supportFragmentManager.findFragmentById(navHostViewId) as NavHostFragment

    return navHostFragment.navController
}