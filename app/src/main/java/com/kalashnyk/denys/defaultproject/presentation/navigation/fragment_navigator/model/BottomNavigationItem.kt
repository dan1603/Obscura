package com.kalashnyk.denys.defaultproject.presentation.navigation.fragment_navigator.model

import android.os.Parcel
import android.os.Parcelable

class BottomNavigationItem(
        var iconName: String,
        var titleIcon: String,
        var destination: String,
        var isDefault: Boolean,
        var list: List<BottomNavigationItem>?
) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readByte() != 0.toByte(),
            parcel.createTypedArrayList(CREATOR))

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(iconName)
        parcel.writeString(titleIcon)
        parcel.writeString(destination)
        parcel.writeByte(if (isDefault) 1 else 0)
        parcel.writeTypedList(list)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BottomNavigationItem> {
        override fun createFromParcel(parcel: Parcel): BottomNavigationItem {
            return BottomNavigationItem(parcel)
        }

        override fun newArray(size: Int): Array<BottomNavigationItem?> {
            return arrayOfNulls(size)
        }
    }
}