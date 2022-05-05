package fr.polyflix.search.infrastructure.repository.elasticsearch

import fr.polyflix.search.infrastructure.repository.elasticsearch.document.VideoDocument
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository
import java.util.UUID

interface SpringElasticVideoRepository: ElasticsearchRepository<VideoDocument, UUID>