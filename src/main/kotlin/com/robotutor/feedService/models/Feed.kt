package com.robotutor.feedService.models

import com.robotutor.feedService.controllers.view.FeedRequest
import com.robotutor.iot.utils.models.PremisesData
import com.robotutor.iot.utils.models.UserData
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.TypeAlias
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

const val FEED_COLLECTION = "feeds"

@TypeAlias("Feed")
@Document(FEED_COLLECTION)
data class Feed(
    @Id
    var id: ObjectId? = null,
    @Indexed(unique = true)
    val feedId: FeedId,
    val boardId: BoardId,
    val premisesId: PremisesId,
    var name: String,
    val type: FeedType,
    var value: Number,
    val createdBy: String,
    val createdAt: LocalDateTime = LocalDateTime.now()
) {
    fun updateName(name: String): Feed {
        this.name = name
        return this
    }

    fun updateValue(value: Number): Feed {
        this.value = value
        return this
    }

    companion object {
        fun from(feedId: FeedId, feedRequest: FeedRequest, userData: UserData, premisesData: PremisesData): Feed {
            return Feed(
                feedId = feedId,
                boardId = feedRequest.boardId,
                premisesId = premisesData.premisesId,
                name = feedRequest.name,
                type = feedRequest.type,
                value = 0,
                createdBy = userData.userId
            )
        }
    }
}

enum class FeedType {
    INPUT,
    OUTPUT
}

typealias FeedId = String
