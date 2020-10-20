package com.kalashnyk.denys.defaultproject.presentation.navigation.fragment_navigator.model

import android.os.Parcel
import android.os.Parcelable

class PayloadSearch(val id: String = "",
                    val placeName: String = "",
                    val lang: String = "",
                    val currency: String = "",
                    val pageNavigationItem: PageNavigationItem? = null,
                    val fromDeepLink: Boolean = false
) : PayloadObject {

    constructor(parcel: Parcel) : this(
        id = parcel.readString(),
        placeName = parcel.readString(),
        lang = parcel.readString(),
        currency = parcel.readString(),
        pageNavigationItem = parcel.readParcelable(PageNavigationItem::class.java.classLoader),
        fromDeepLink = parcel.readByte() != 0.toByte()
    )

    fun isFromDeepLink(): Boolean {
        return fromDeepLink
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(placeName)
        parcel.writeString(lang)
        parcel.writeString(currency)
        parcel.writeParcelable(pageNavigationItem, flags)
        parcel.writeByte(if (fromDeepLink) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PayloadSearch> {
        override fun createFromParcel(parcel: Parcel): PayloadSearch {
            return PayloadSearch(parcel)
        }

        override fun newArray(size: Int): Array<PayloadSearch?> {
            return arrayOfNulls(size)
        }
    }


}