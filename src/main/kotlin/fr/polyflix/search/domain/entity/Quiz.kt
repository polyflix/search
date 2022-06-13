package fr.polyflix.search.domain.entity

class Quiz(
    id: String?,
    val name: String?,
    val visibility: String? = null,
    val draft: Boolean? = null
): Searchable(id, Quiz::class.simpleName?.lowercase()) {
    override fun toString(): String {
        return "Quiz { id = $id, name = $name, visibility = $visibility, draft = $draft }"
    }
}