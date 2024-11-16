package com.example.androidcookbook.ui.nav

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController

inline fun <reified VM : ViewModel> NavController.getViewModel(factory: ViewModelProvider.Factory): VM {
    return ViewModelProvider(this.getBackStackEntry(this.graph.id), factory)[VM::class.java]
}