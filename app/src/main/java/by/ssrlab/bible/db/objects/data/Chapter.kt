package by.ssrlab.bible.db.objects.data

import by.ssrlab.bible.db.objects.BaseBibleData
import com.google.gson.annotations.SerializedName

data class Chapter(

    @SerializedName("id")
    override val id: String,

    @SerializedName("reference")
    override val name: String

) : BaseBibleData