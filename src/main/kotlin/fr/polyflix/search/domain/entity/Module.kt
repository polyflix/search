package fr.polyflix.search.domain.entity

class Module(
    id: String?,
    val name: String?,
    val slug: String?,
    val description: String?,
    val visibility: String? = null,
    val draft: Boolean? = null
) : Searchable(id, Module::class.simpleName?.lowercase()) {
    override fun toString(): String {
        return "Module { id = $id, name = $name, slug = $slug, description = $description, visibility = $visibility, draft = $draft }"
    }
}