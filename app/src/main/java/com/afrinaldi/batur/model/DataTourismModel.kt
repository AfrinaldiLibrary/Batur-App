package com.afrinaldi.batur.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataTourismModel(
    var title: String,
    var description: String,
    var image: Int,
    var location: String,
    var upload: String,
    var source: String
) : Parcelable