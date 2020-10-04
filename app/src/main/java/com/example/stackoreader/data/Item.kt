package com.example.stackoreader.data


import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Item(
    @SerializedName("accepted_answer_id")
    val acceptedAnswerId: Int,
    @SerializedName("answer_count")
    val answerCount: Int,
    @SerializedName("content_license")
    val contentLicense: String?,
    @SerializedName("creation_date")
    val creationDate: Int,
    @SerializedName("is_answered")
    val isAnswered: Boolean,
    @SerializedName("last_activity_date")
    val lastActivityDate: Int,
    @SerializedName("last_edit_date")
    val lastEditDate: Int,
    @SerializedName("link")
    val link: String?,
    @SerializedName("owner")
    val owner: Owner?,
    @SerializedName("question_id")
    val questionId: Int,
    @SerializedName("score")
    val score: Int,
    @SerializedName("tags")
    val tags: List<String>?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("view_count")
    val viewCount: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readByte() != 0.toByte(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readParcelable(Owner::class.java.classLoader),
        parcel.readInt(),
        parcel.readInt(),
        parcel.createStringArrayList(),
        parcel.readString(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(acceptedAnswerId)
        parcel.writeInt(answerCount)
        parcel.writeString(contentLicense)
        parcel.writeInt(creationDate)
        parcel.writeByte(if (isAnswered) 1 else 0)
        parcel.writeInt(lastActivityDate)
        parcel.writeInt(lastEditDate)
        parcel.writeString(link)
        parcel.writeParcelable(owner, flags)
        parcel.writeInt(questionId)
        parcel.writeInt(score)
        parcel.writeStringList(tags)
        parcel.writeString(title)
        parcel.writeInt(viewCount)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Item> {
        override fun createFromParcel(parcel: Parcel): Item {
            return Item(parcel)
        }

        override fun newArray(size: Int): Array<Item?> {
            return arrayOfNulls(size)
        }
    }
}