package by.ssrlab.bible.db.objects.data

import android.os.Parcelable
import by.ssrlab.bible.db.objects.BaseBibleData
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Book(

    @SerializedName("id")
    override val id: String,

    @SerializedName("nameLong")
    override val name: String

) : BaseBibleData, Parcelable