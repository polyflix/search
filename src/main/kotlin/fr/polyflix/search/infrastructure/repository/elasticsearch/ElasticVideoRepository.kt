package fr.polyflix.search.infrastructure.repository.elasticsearch

import fr.polyflix.search.domain.entity.Video
import fr.polyflix.search.domain.repository.VideoRepository
import fr.polyflix.search.infrastructure.repository.elasticsearch.document.VideoDocument
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class ElasticVideoRepository(private val repository: SpringElasticVideoRepository): VideoRepository {
    private val logger = LoggerFactory.getLogger(ElasticVideoRepository::class.java)

    /**
     * Save a video into Elasticsearch.
     * If something went wrong, it will simply log an error.
     */
    override fun createOne(video: Video) {
        val document = VideoDocument.fromDomain(video)
        try {
            repository.save(document)
            logger.info("Video document successfully indexed, id=${document.slug}, title=${document.title}")
        } catch (e: Exception) {
            logger.error("Failed to save the document into Elasticsearch. Details: ${e.message}")
        }
    }

    /**
     * Delete a video into Elasticsearch.
     * If something went wrong, the error will be logged.
     */
    override fun deleteOne(id: String) {
        try {
            repository.deleteById(id)
            logger.info("If it existed, the video document (id=$id) has been successfully deleted")
        } catch (e: Exception) {
            logger.error("Failed to delete the video document with id=${id}. Details: ${e.message}")
        }
    }
}