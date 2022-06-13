package fr.polyflix.search.infrastructure.repository.elasticsearch

import fr.polyflix.search.domain.entity.Course
import fr.polyflix.search.domain.repository.CourseRepository
import fr.polyflix.search.infrastructure.repository.elasticsearch.document.CourseDocument
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class ElasticCourseRepository(private val repository: SpringElasticCourseRepository): CourseRepository {
    private val logger = LoggerFactory.getLogger(javaClass)

    override fun createOne(course: Course) {
        val document = CourseDocument.fromDomain(course)
        try {
            repository.save(document)
            logger.info("Course document successfully indexed, id=${document.id}")
        } catch (e: Exception) {
            logger.error("Failed to save the document into Elasticsearch. Details: ${e.message}")
        }
    }

    override fun deleteOne(id: String) {
        try {
            repository.deleteById(id)
            logger.info("If it existed, the course document (id=$id) has been successfully deleted")
        } catch (e: Exception) {
            logger.error("Failed to delete the course document with id=${id}. Details: ${e.message}")
        }
    }
}