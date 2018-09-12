package com.aakimov.nyt.entity

import android.arch.persistence.room.*


class Story {
    @Embedded
    var story: PlainStory? = null

    @Relation(parentColumn = "guid", entityColumn = "storyId")
    var multimedia: List<Multimedia> = arrayListOf()
}

@Entity(tableName = "story")
//@TypeConverters(Converters::class)
data class PlainStory(
        @PrimaryKey
        @ColumnInfo(name = "guid") var guid: String = "",
        @ColumnInfo(name = "section") var section: String = "",
        @ColumnInfo(name = "subsection") var subsection: String = "",
        @ColumnInfo(name = "title") var title: String = "",
        @ColumnInfo(name = "abstract") var abstract: String = "",
        @ColumnInfo(name = "url") var url: String = "",
        @ColumnInfo(name = "byline") var byline: String = "",
        @ColumnInfo(name = "item_type") var itemType: String = "",
//        @ColumnInfo(name = "updated_date") var updatedDate: Date,
//        @ColumnInfo(name = "created_date") var createdDate: Date,
//        @ColumnInfo(name = "published_date") var publishedDate: Date,
        @ColumnInfo(name = "material_type_facet") var materialTypeFacet: String = "",
        @ColumnInfo(name = "kicker") var kicker: String = "",
//        @ColumnInfo(name = "des_facet") var desFacet: List<String>,
//        @ColumnInfo(name = "org_facet") var orgFacet: List<String>,
//        @ColumnInfo(name = "per_facet") var perFacet: List<String>,
//        @ColumnInfo(name = "geo_facet") var geoFacet: List<String>,
        @ColumnInfo(name = "short_url") var shortUrl: String? = ""
)

@Entity(tableName = "multimedia"
//        ,
//        foreignKeys = [(ForeignKey(entity = PlainStory::class,
//                parentColumns = ["guid"],
//                childColumns = ["storyId"],
//                onDelete = ForeignKey.CASCADE,
//                onUpdate = ForeignKey.CASCADE,
//                deferred = true))]
)
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
)