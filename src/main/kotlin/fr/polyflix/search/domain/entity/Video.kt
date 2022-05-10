package fr.polyflix.search.domain.entity

class Video(slug: String?, val title: String?, val description: String?): Searchable(slug, Video::class.simpleName?.lowercase()) {
    override fun toString(): String {
        return "Video { id = $id, title = $title, description = $description }"
    }
}