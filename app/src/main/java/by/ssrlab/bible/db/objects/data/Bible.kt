package by.ssrlab.bible.db.objects.data

import android.os.Parcelable
import by.ssrlab.bible.db.objects.BaseBibleData
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Bible(

    @SerializedName("id")
    override val id: String,

    @SerializedName("nameLocal")
    override val name: String,

    @SerializedName("copyright")
    val author: String,

) : BaseBibleData, Parcelable