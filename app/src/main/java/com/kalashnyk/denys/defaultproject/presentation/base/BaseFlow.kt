package com.kalashnyk.denys.defaultproject.presentation.base

import com.kalashnyk.denys.defaultproject.presentation.navigation.fragment_navigator.model.Pages

interface BaseFlow {
     interface BaseListener {
         fun openScreen(page: Pages)
     }

    interface IBaseCallback
}
