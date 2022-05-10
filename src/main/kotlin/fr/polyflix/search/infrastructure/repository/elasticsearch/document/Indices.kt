package fr.polyflix.search.infrastructure.repository.elasticsearch.document

object Indices {
    private const val PREFIX = "polyflix.searchable"
    const val VIDEO = "${PREFIX}.videos"
    const val QUIZ = "${PREFIX}.quizzes"

    fun coordinates(): String {
        return "${PREFIX}.*"
    }
}