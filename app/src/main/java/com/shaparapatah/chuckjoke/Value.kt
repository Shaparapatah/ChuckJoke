package com.shaparapatah.chuckjoke

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Value(
    val joke: String
) : Parcelable