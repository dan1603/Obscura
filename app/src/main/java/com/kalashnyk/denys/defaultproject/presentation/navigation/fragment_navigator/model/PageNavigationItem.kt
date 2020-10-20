package com.kalashnyk.denys.defaultproject.presentation.navigation.fragment_navigator.model

import android.os.Parcel
import android.os.Parcelable

class PageNavigationItem(
        val destination: Pages,
        //this is allowed to be changed
        var payload: PayloadObject = Payload(),
        val listOfMoreItems: List<BottomNavigationItem> = ArrayList()) : Parcelable {

    constructor(parcel: Parcel) : this(
            Pages.valueOf(parcel.readString().toUpperCase()),
            parcel.readParcelable(PayloadObject::class.java.classLoader),
            parcel.createTypedArrayList(BottomNavigationItem))

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(this.destination.text)
        parcel.writeParcelable(payload, flags)
        parcel.writeTypedList(listOfMoreItems)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PageNavigationItem> {
        override fun createFromParcel(parcel: Parcel): PageNavigationItem {
            return PageNavigationItem(parcel)
        }

        override fun newArray(size: Int): Array<PageNavigationItem?> {
            return arrayOfNulls(size)
        }
    }

}