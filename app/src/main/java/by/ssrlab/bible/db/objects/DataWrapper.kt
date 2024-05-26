package by.ssrlab.bible.db.objects

import com.google.gson.annotations.SerializedName

data class DataWrapper <T : BaseBibleData> (

    @SerializedName("data")
    val data: T
)
