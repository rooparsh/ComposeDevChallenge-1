package com.example.androiddevchallenge.ui

sealed class Navigation(val title: String) {
    object Home : Navigation("Home")
    object Detail : Navigation("Details")
}
