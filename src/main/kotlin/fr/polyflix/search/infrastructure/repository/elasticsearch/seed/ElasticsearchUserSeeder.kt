package fr.polyflix.search.infrastructure.repository.elasticsearch.seed

import fr.polyflix.search.infrastructure.repository.elasticsearch.SpringElasticUserRepository
import fr.polyflix.search.infrastructure.repository.elasticsearch.document.Indices
import fr.polyflix.search.infrastructure.repository.elasticsearch.document.NestedRole
import fr.polyflix.search.infrastructure.repository.elasticsearch.document.UserDocument
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class ElasticsearchUserSeeder(
    @Value("\${seeders.enabled}") private val seedersEnabled: Boolean,
    private val repository: SpringElasticUserRepository
) : CommandLineRunner {
    private val logger = LoggerFactory.getLogger(javaClass)

    override fun run(vararg args: String?) {
        if (!seedersEnabled) return

        val seeds = listOf(
            UserDocument(
                UUID.randomUUID().toString(),
                "Jean",
                "Claudus",
                "jclaudus",
                "https://i.skyrock.net/5257/17165257/pics/472998088_small.jpg",
                "jeanclaudus@yopmail.com",
                listOf(
                    NestedRole("MEMBER")
                )
            ),
            UserDocument(
                UUID.randomUUID().toString(),
                "Michel",
                "Dupont",
                "mdupont",
                "https://i.skyrock.net/5257/17165257/pics/472998088_small.jpg",
                "micheldupont@yopmail.com",
                listOf(
                    NestedRole("CONTRIBUTOR")
                )
            )
        )

        logger.info("Cleaning index '${Indices.USER}'")
        repository.deleteAll()
        logger.info("Seeding '${Indices.USER}' with ${seeds.size} elements.")
        repository.saveAll(seeds)
    }
}