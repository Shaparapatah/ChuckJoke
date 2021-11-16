package com.shaparapatah.chuckjoke

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Joke(val joke: String) : Parcelable
