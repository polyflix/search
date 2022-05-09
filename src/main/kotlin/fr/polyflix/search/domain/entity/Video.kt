package fr.polyflix.search.domain.entity

class Video(slug: String?, val title: String?, val description: String?): Searchable(slug) {
    override fun toString(): String {
        return "Video { id = $id, title = $title, description = $description }"
    }
}