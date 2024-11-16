package com.example.androidcookbook.data.containers

import com.example.androidcookbook.data.repositories.Repository

interface AppContainer {
    val repository: Repository
}