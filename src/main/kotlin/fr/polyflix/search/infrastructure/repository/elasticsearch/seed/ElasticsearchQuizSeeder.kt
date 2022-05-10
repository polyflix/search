package fr.polyflix.search.infrastructure.repository.elasticsearch.seed

import fr.polyflix.search.infrastructure.repository.elasticsearch.SpringElasticQuizRepository
import fr.polyflix.search.infrastructure.repository.elasticsearch.document.Indices
import fr.polyflix.search.infrastructure.repository.elasticsearch.document.QuizDocument
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class ElasticsearchQuizSeeder(
    @Value("\${seeders.enabled}") private val seedersEnabled: Boolean,
    private val repository: SpringElasticQuizRepository
): CommandLineRunner {
    private val logger = LoggerFactory.getLogger(javaClass)

    override fun run(vararg args: String?) {
        if (!seedersEnabled) return

        val seeds = listOf(
            QuizDocument(UUID.randomUUID().toString(), "Quizz - Javascript"),
            QuizDocument(UUID.randomUUID().toString(), "Git - Fundamentals"),
            QuizDocument(UUID.randomUUID().toString(), "Fondamentaux Kotlin - Quizz n°1"),
            QuizDocument(UUID.randomUUID().toString(), "Polycloud - Quiz"),
            QuizDocument(UUID.randomUUID().toString(), "Test quiz")
        )

        logger.info("Cleaning index '${Indices.QUIZ}'")
        repository.deleteAll()
        logger.info("Seeding '${Indices.QUIZ}' with ${seeds.size} elements.")
        repository.saveAll(seeds)
    }
}