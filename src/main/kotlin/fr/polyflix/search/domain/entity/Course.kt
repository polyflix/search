package fr.polyflix.search.domain.entity

class Course(id: String?, val name: String?, val slug: String?, val shortDescription: String?): Searchable(id, Course::class.simpleName?.lowercase()) {
    override fun toString(): String {
        return "Course { id = $id, name = $name, slug = $slug, shortDescription = $shortDescription }"
    }
}