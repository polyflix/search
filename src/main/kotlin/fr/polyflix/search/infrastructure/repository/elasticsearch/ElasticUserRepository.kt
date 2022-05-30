package fr.polyflix.search.infrastructure.repository.elasticsearch

import fr.polyflix.search.domain.entity.User
import fr.polyflix.search.domain.repository.UserRepository
import fr.polyflix.search.infrastructure.repository.elasticsearch.document.UserDocument
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class ElasticUserRepository(private val repository: SpringElasticUserRepository): UserRepository {
    private val logger = LoggerFactory.getLogger(javaClass)

    override fun createOne(user: User) {
        val document = UserDocument.fromDomain(user)
        try {
            repository.save(document)
            logger.info("User document successfully indexed, id=${document.id}")
        } catch (e: Exception) {
            logger.error("Failed to save the document into Elasticsearch. Details: ${e.message}")
        }
    }

    override fun deleteOne(id: String) {
        try {
            repository.deleteById(id)
            logger.info("If it existed, the user document (id=$id) has been successfully deleted")
        } catch (e: Exception) {
            logger.error("Failed to delete the user document with id=${id}. Details: ${e.message}")
        }
    }
}