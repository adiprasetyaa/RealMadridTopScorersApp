package com.example.myviewandviewgroup.myconstraintview.myrecycleview.realmadridtopscorer

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Player(
    val name:String,
    val total_goals: String,
    val total_matches: String,
    val years: String,
    val photo: Int,
    val description: String
) : Parcelable

