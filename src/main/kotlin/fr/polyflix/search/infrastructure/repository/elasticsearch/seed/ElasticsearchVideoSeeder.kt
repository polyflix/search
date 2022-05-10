package fr.polyflix.search.infrastructure.repository.elasticsearch.seed

import fr.polyflix.search.infrastructure.repository.elasticsearch.SpringElasticVideoRepository
import fr.polyflix.search.infrastructure.repository.elasticsearch.document.Indices
import fr.polyflix.search.infrastructure.repository.elasticsearch.document.VideoDocument
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class ElasticsearchVideoSeeder(
    @Value("\${seeders.enabled}") private val seedersEnabled: Boolean,
    private val repository: SpringElasticVideoRepository,
) : CommandLineRunner {
    private val logger = LoggerFactory.getLogger(javaClass)

    override fun run(vararg args: String?) {
        if (!seedersEnabled) return

        val seeds = listOf(
            VideoDocument("my-super-video", "My Super Video", "This is a super video !"),
            VideoDocument("video-de-polytech", "Video de Polytech", "Elle est pas mal cette video !"),
            VideoDocument("learn-spring-with-kotlin", "Learn Spring with Kotlin", "How to learn spring with kotlin"),
            VideoDocument("nest-is-deprecated", "Nest is deprecated", "It's a joke"),
            VideoDocument("la-bataille-des-dwarfs", "La bataille des dwarfs", "bizar")
        )

        logger.info("Cleaning index '${Indices.VIDEO}'")
        repository.deleteAll()
        logger.info("Seeding '${Indices.VIDEO}' with ${seeds.size} elements.")
        repository.saveAll(seeds)
    }
}