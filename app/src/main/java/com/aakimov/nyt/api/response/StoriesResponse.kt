package com.aakimov.nyt.api.response

import com.google.gson.annotations.SerializedName

data class NytResponse<T>(
        @SerializedName("status") val status: String,
        @SerializedName("copyright") val copyright: String,
        @SerializedName("section") val section: String,
        @SerializedName("last_updated") val lastUpdated: String,
        @SerializedName("num_results") val numResults: Int,
        @SerializedName("results") val data: T
)

data class StoryResponse(
        @SerializedName("section") val section: String,
        @SerializedName("subsection") val subsection: String,
        @SerializedName("title") val title: String,
        @SerializedName("abstract") val abstract: String,
        @SerializedName("url") val url: String,
        @SerializedName("byline") val byline: String,
        @SerializedName("item_type") val itemType: String,
        @SerializedName("updated_date") val updatedDate: String,
        @SerializedName("created_date") val createdDate: String,
        @SerializedName("published_date") val publishedDate: String,
        @SerializedName("material_type_facet") val materialTypeFacet: String,
        @SerializedName("kicker") val kicker: String,
        @SerializedName("des_facet") val desFacet: List<String>,
        @SerializedName("org_facet") val orgFacet: List<String>,
        @SerializedName("per_facet") val perFacet: List<String>,
        @SerializedName("geo_facet") val geoFacet: List<String>,
        @SerializedName("multimedia") val multimedia: List<MultimediaResponse>,
        @SerializedName("short_url") val shortUrl: String?
)

data class MultimediaResponse(
        @SerializedName("url") val url: String,
        @SerializedName("format") val format: String,
        @SerializedName("height") val height: Int,
        @SerializedName("width") val width: Int,
        @SerializedName("type") val type: String,
        @SerializedName("subtype") val subtype: String,
        @SerializedName("caption") val caption: String,
        @SerializedName("copyright") val copyright: String
)