package com.aakimov.nyt.bl

import com.aakimov.nyt.api.response.NytResponse
import com.aakimov.nyt.api.response.StoryResponse
import com.aakimov.nyt.entity.Multimedia
import com.aakimov.nyt.entity.PlainStory
import com.aakimov.nyt.entity.Story
import com.aakimov.nyt.util.DateTimeUtils
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.ObservableTransformer
import java.util.*

class StoriesTransformer : ObservableTransformer<NytResponse<List<StoryResponse>>, List<Story>> {
    override fun apply(upstream: Observable<NytResponse<List<StoryResponse>>>):
            ObservableSource<List<Story>> {
        return upstream.map { response -> response.data.map(this::toStory) }
    }

    private fun toStory(sr: StoryResponse): Story {
        val story = Story()
        val storyId = UUID.randomUUID().toString()
        val multimedia = sr.multimedia.map {
            Multimedia(0, storyId, it.url, it.format, it.height, it.width, it.type, it.subtype,
                    it.caption, it.copyright)
        }.toList()
        val plainStory = PlainStory(storyId,
                sr.section,
                sr.subsection,
                sr.title,
                sr.abstract,
                sr.url,
                sr.byline,
                sr.itemType,
                DateTimeUtils.fromBackendDateTime(sr.updatedDate),
                DateTimeUtils.fromBackendDateTime(sr.createdDate),
                DateTimeUtils.fromBackendDateTime(sr.publishedDate),
                sr.materialTypeFacet,
                sr.kicker,
                sr.desFacet,
                sr.orgFacet,
                sr.perFacet,
                sr.geoFacet,
                sr.shortUrl)
        story.story = plainStory
        story.multimedia = multimedia
        return story
    }
}