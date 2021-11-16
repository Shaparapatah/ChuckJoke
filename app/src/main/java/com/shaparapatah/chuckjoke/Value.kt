package com.shaparapatah.chuckjoke

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ServerResponse(
    val type: String,
    val value: List<OneJoke>
) : Parcelable

@Parcelize
data class OneJoke(
    val id: Long,
    val joke: String,
    val categories: List<String>
) : Parcelable
