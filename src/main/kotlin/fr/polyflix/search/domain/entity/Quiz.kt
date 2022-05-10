package fr.polyflix.search.domain.entity

class Quiz(id: String?, val name: String?): Searchable(id, Quiz::class.simpleName?.lowercase()) {
    override fun toString(): String {
        return "Quiz { id = $id, name = $name }"
    }
}