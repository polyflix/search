package fr.polyflix.search.infrastructure.repository.elasticsearch

import fr.polyflix.search.domain.entity.Quiz
import fr.polyflix.search.domain.repository.QuizRepository
import fr.polyflix.search.infrastructure.repository.elasticsearch.mapper.QuizDocumentMapper
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.util.*

@Component
class ElasticQuizRepository(private val repository: SpringElasticQuizRepository, private val mapper: QuizDocumentMapper): QuizRepository {
    private val logger = LoggerFactory.getLogger(ElasticQuizRepository::class.java)

    /**
     * Save a quiz into Elasticsearch.
     * If something went wrong, it will simply log an error.
     */
    override fun createOne(quiz: Quiz) {
        val document = mapper.toDocument(quiz)
        try {
            repository.save(document)
            logger.info("Quiz document successfully indexed, id=${document.id}, name=${document.name}")
        } catch (e: Exception) {
            logger.error("Failed to save the document into Elasticsearch. Details: ${e.message}")
        }
    }

    /**
     * Delete a quiz into Elasticsearch.
     * If something went wrong, the error will be logged.
     */
    override fun deleteOne(id: UUID) {
        try {
            repository.deleteById(id)
            logger.info("If it existed, the video document (id=$id) has been successfully deleted")
        } catch (e: Exception) {
            logger.error("Failed to delete the video document with id=${id}. Details: ${e.message}")
        }
    }
}