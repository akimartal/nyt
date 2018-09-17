package com.aakimov.nyt.entity

import android.arch.persistence.room.*
import android.os.Parcelable
import com.aakimov.nyt.storage.DateConverter
import com.aakimov.nyt.storage.StringListConverter
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
class Story : Parcelable {
    var story: PlainStory = PlainStory()
    var multimedia: List<Multimedia> = arrayListOf()
}

@Parcelize
@Entity(tableName = "story", indices = [Index(value = ["guid"], unique = true)])
data class PlainStory(

        @PrimaryKey
        @ColumnInfo(name = "guid")
        var guid: String = "",
        @ColumnInfo(name = "section")
        var section: String = "",
        @ColumnInfo(name = "subsection")
        var subsection: String = "",
        @ColumnInfo(name = "title")
        var title: String = "",
        @ColumnInfo(name = "abstract")
        var abstract: String = "",
        @ColumnInfo(name = "url")
        var url: String = "",
        @ColumnInfo(name = "byline")
        var byline: String = "",
        @ColumnInfo(name = "item_type")
        var itemType: String = "",
        @TypeConverters(DateConverter::class)
        @ColumnInfo(name = "updated_date")
        var updatedDate: Date = Date(),
        @TypeConverters(DateConverter::class)
        @ColumnInfo(name = "created_date")
        var createdDate: Date = Date(),
        @TypeConverters(DateConverter::class)
        @ColumnInfo(name = "published_date")
        var publishedDate: Date = Date(),
        @ColumnInfo(name = "material_type_facet")
        var materialTypeFacet: String = "",
        @ColumnInfo(name = "kicker")
        var kicker: String = "",
        @TypeConverters(StringListConverter::class)
        @ColumnInfo(name = "des_facet")
        var desFacet: List<String> = emptyList(),
        @TypeConverters(StringListConverter::class)
        @ColumnInfo(name = "org_facet")
        var orgFacet: List<String> = emptyList(),
        @TypeConverters(StringListConverter::class)
        @ColumnInfo(name = "per_facet")
        var perFacet: List<String> = emptyList(),
        @TypeConverters(StringListConverter::class)
        @ColumnInfo(name = "geo_facet")
        var geoFacet: List<String> = emptyList(),
        @ColumnInfo(name = "short_url")
        var shortUrl: String? = "") : Parcelable

@Parcelize
@Entity(tableName = "multimedia",
        foreignKeys = [(ForeignKey(entity = PlainStory::class,
                parentColumns = ["guid"],
                childColumns = ["storyId"],
                onDelete = ForeignKey.CASCADE,
                onUpdate = ForeignKey.CASCADE,
                deferred = true))])
data class Multimedia(
        @ColumnInfo(name = "id")
        @PrimaryKey(autoGenerate = true) var id: Long,
        var storyId: String,
        var url: String,
        var format: String,
        var height: Int,
        var width: Int,
        var type: String,
        var subtype: String,
        var caption: String,
        var copyright: String
) : Parcelable
