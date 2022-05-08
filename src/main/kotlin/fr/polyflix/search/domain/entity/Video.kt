package fr.polyflix.search.domain.entity

import java.util.UUID

class Video(uuid: UUID?, val title: String?, val description: String?): Searchable(uuid) {
    override fun toString(): String {
        return "Video { id = $id, title = $title, description = $description }"
    }
}