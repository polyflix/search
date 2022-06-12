package fr.polyflix.search.infrastructure.repository.elasticsearch.seed

import fr.polyflix.search.infrastructure.repository.elasticsearch.SpringElasticVideoRepository
import fr.polyflix.search.infrastructure.repository.elasticsearch.document.Indices
import fr.polyflix.search.infrastructure.repository.elasticsearch.document.VideoDocument
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class ElasticsearchVideoSeeder(
    @Value("\${seeders.enabled}") private val seedersEnabled: Boolean,
    private val repository: SpringElasticVideoRepository,
) : CommandLineRunner {
    private val logger = LoggerFactory.getLogger(javaClass)

    override fun run(vararg args: String?) {
        if (!seedersEnabled) return

        val seeds = listOf(
            VideoDocument(UUID.fromString("cd2b69dd-5b86-457a-9752-248a3b4eba20").toString(),"my-super-video", "My Super Video", "This is a super video !", "https://avatars.githubusercontent.com/u/47056759?v=4"),
            VideoDocument(UUID.fromString("b7fb6562-434d-497c-9ff1-052b49721abb").toString(), "video-de-polytech", "Video de Polytech", "Elle est pas mal cette video !", "https://avatars.githubusercontent.com/u/47056759?v=4"),
            VideoDocument(UUID.fromString("43d5d1bb-145d-49eb-89f1-5cc484f564f1").toString(), "learn-spring-with-kotlin", "Learn Spring with Kotlin", "How to learn spring with kotlin", "https://avatars.githubusercontent.com/u/47056759?v=4"),
            VideoDocument(UUID.fromString("41445cf4-236b-451f-9257-946559125e13").toString(), "nest-is-deprecated", "Nest is deprecated", "It's a joke", "https://avatars.githubusercontent.com/u/47056759?v=4"),
            VideoDocument(UUID.fromString("8e731ee4-8746-4d02-b177-9bd9b705e9d7").toString(), "la-bataille-des-dwarfs", "La bataille des dwarfs", "bizar", "https://avatars.githubusercontent.com/u/47056759?v=4")
        )

        logger.info("Cleaning index '${Indices.VIDEO}'")
        repository.deleteAll()
        logger.info("Seeding '${Indices.VIDEO}' with ${seeds.size} elements.")
        repository.saveAll(seeds)
    }
}