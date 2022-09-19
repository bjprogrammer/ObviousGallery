package com.app.obvious.model

import android.os.Parcel
import androidx.databinding.BaseObservable
import android.os.Parcelable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import com.google.gson.annotations.SerializedName

class Image internal constructor(src: Parcel) : BaseObservable(), Parcelable {
    private var copyright: String?
    private var date: String?
    private var explanation: String?
    private var hdurl: String?
    private var url: String?
    private var title: String?

    @SerializedName("service_version")
    private var version: String?

    @SerializedName("media_type")
    private var type: String?

    @Bindable
    fun getCopyright(): String? = copyright

    fun setCopyright(copyright: String?) {
        this.copyright = copyright
        notifyPropertyChanged(BR.copyright)
    }

    @Bindable
    fun getDate(): String? = date

    fun setDate(date: String?) {
        this.date = date
        notifyPropertyChanged(BR.date)
    }

    @Bindable
    fun getExplanation(): String? = explanation

    fun setExplanation(explanation: String?) {
        this.explanation = explanation
        notifyPropertyChanged(BR.explanation)
    }

    @Bindable
    fun getHdurl(): String? = hdurl

    fun setHdurl(hdurl: String?) {
        this.hdurl = hdurl
        notifyPropertyChanged(BR.hdurl)
    }

    @Bindable
    fun getUrl(): String? = url

    fun setUrl(url: String?) {
        this.url = url
        notifyPropertyChanged(BR.url)
    }

    @Bindable
    fun getType(): String? = type

    fun setType(type: String?) {
        this.type = type
        notifyPropertyChanged(BR.type)
    }

    @Bindable
    fun getVersion(): String? = version

    fun setVersion(version: String?) {
        this.version = version
        notifyPropertyChanged(BR.version)
    }

    @Bindable
    fun getTitle(): String? = title

    fun setTitle(title: String?) {
        this.title = title
        notifyPropertyChanged(BR.title)
    }

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.apply {
            writeString(copyright)
            writeString(type)
            writeString(date)
            writeString(explanation)
            writeString(hdurl)
            writeString(url)
            writeString(version)
            writeString(title)
        }
    }

    init {
        src.apply {
            copyright = readString()
            type = readString()
            date = readString()
            explanation = readString()
            hdurl = readString()
            url = readString()
            version = readString()
            title = readString()
        }
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Image> = object : Parcelable.Creator<Image> {
            override fun createFromParcel(parcel: Parcel): Image {
                return Image(parcel)
            }

            override fun newArray(size: Int): Array<Image?> {
                return arrayOfNulls(size)
            }
        }
    }
}