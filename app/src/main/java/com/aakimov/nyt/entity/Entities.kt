package com.aakimov.nyt.entity

import android.arch.persistence.room.*
import com.aakimov.nyt.storage.Converters
import java.util.*


class Story {
    @Embedded
    var story: PlainStory? = null

    @Relation(parentColumn = "guid", entityColumn = "storyId")
    var multimedia: List<Multimedia> = arrayListOf()
}

@Entity(tableName = "story")
data class PlainStory(
        @PrimaryKey
        @ColumnInfo(name = "guid") var guid: String,
        @ColumnInfo(name = "section") val section: String,
        @ColumnInfo(name = "subsection") val subsection: String,
        @ColumnInfo(name = "title") val title: String,
        @ColumnInfo(name = "abstract") val abstract: String,
        @ColumnInfo(name = "url") val url: String,
        @ColumnInfo(name = "byline") val byline: String,
        @ColumnInfo(name = "item_type") val itemType: String,
        @TypeConverters(Converters::class)
        @ColumnInfo(name = "updated_date") val updatedDate: Date,
        @TypeConverters(Converters::class)
        @ColumnInfo(name = "created_date") val createdDate: Date,
        @TypeConverters(Converters::class)
        @ColumnInfo(name = "published_date") val publishedDate: Date,
        @ColumnInfo(name = "material_type_facet") val materialTypeFacet: String,
        @ColumnInfo(name = "kicker") val kicker: String,
        @ColumnInfo(name = "des_facet") val desFacet: List<String>,
        @ColumnInfo(name = "org_facet") val orgFacet: List<String>,
        @ColumnInfo(name = "per_facet") val perFacet: List<String>,
        @ColumnInfo(name = "geo_facet") val geoFacet: List<String>,
        @ColumnInfo(name = "multimedia") val multimedia: List<Multimedia>,
        @ColumnInfo(name = "short_url") val shortUrl: String
)

@Entity(tableName = "multimedia",
        foreignKeys = [(ForeignKey(entity = PlainStory::class,
                parentColumns = ["guid"],
                childColumns = ["storyId"],
                onDelete = ForeignKey.CASCADE,
                onUpdate = ForeignKey.CASCADE,
                deferred = true))]
)
data class Multimedia(
        @ColumnInfo(name = "id")
        @PrimaryKey(autoGenerate = true) var id: Long,
        val storyId: String,
        val url: String,
        val format: String,
        val height: Int,
        val width: Int,
        val type: String,
        val subtype: String,
        val caption: String,
        val copyright: String
)