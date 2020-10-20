package com.kalashnyk.denys.defaultproject.presentation.activities.location

import android.location.Address

data class LocationModel(
    var address: Address,
    var decorate: Boolean
)