package com.robotutor.deviceService.controllers.view

import com.robotutor.deviceService.models.*
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class FeedRequest(
    @field:NotBlank(message = "Name is required")
    @field:Size(min = 4, max = 20, message = "Name should not be less than 4 char or more than 20 char")
    val name: String,
    @field:NotBlank(message = "type is required")
    val type: FeedType,
    @field:NotBlank(message = "BoardId is required")
    val boardId: BoardId,
)

data class FeedView(
    val feedId: FeedId,
    val boardId: BoardId,
    val premisesId: PremisesId,
    val name: String,
    val type: FeedType,
    val value: Number,
) {
    companion object {
        fun from(feed: Feed): FeedView {
            return FeedView(
                feedId = feed.feedId,
                boardId = feed.boardId,
                premisesId = feed.premisesId,
                name = feed.name,
                type = feed.type,
                value = feed.value
            )
        }
    }
}
