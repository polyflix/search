package fr.polyflix.search.infrastructure.repository.elasticsearch

import fr.polyflix.search.infrastructure.repository.elasticsearch.document.QuizDocument
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository
import java.util.UUID

interface SpringElasticQuizRepository : ElasticsearchRepository<QuizDocument, UUID>