package by.ssrlab.bible.db.objects.data

import by.ssrlab.bible.db.objects.BaseBibleData
import com.google.gson.annotations.SerializedName

data class Bible(

    @SerializedName("id")
    override val id: String,

    @SerializedName("nameLocal")
    override val name: String,

    @SerializedName("copyright")
    val author: String,

) : BaseBibleData