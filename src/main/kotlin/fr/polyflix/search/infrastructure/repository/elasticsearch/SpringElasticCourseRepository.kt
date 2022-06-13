package fr.polyflix.search.infrastructure.repository.elasticsearch

import fr.polyflix.search.infrastructure.repository.elasticsearch.document.CourseDocument
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository

interface SpringElasticCourseRepository: ElasticsearchRepository<CourseDocument, String>