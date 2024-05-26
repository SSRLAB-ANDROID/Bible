package by.ssrlab.bible.db.objects.data

import by.ssrlab.bible.db.objects.BaseBibleData
import com.google.gson.annotations.SerializedName

data class Book(

    @SerializedName("id")
    override val id: String,

    @SerializedName("nameLong")
    override val name: String

) : BaseBibleData