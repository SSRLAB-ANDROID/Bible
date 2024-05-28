package by.ssrlab.bible.db.objects

import com.google.gson.annotations.SerializedName

data class DataArrayWrapper <T: BaseBibleData> (

    @SerializedName("data")
    val data: ArrayList<T>
)
