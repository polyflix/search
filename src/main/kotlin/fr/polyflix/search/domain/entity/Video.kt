package fr.polyflix.search.domain.entity

class Video(
    id: String?,
    val slug: String?,
    val title: String?,
    val description: String?,
    val thumbnail: String?,
    val visibility: String? = null,
    val draft: Boolean? = null
) :
    Searchable(id, Video::class.simpleName?.lowercase()) {
    override fun toString(): String {
        return "Video { id = $id, slug = $slug, title = $title, description = $description, thumbnail = $thumbnail, visibility = $visibility, draft = $draft }"
    }
}