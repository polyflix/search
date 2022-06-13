package fr.polyflix.search.infrastructure.repository.elasticsearch

import fr.polyflix.search.domain.entity.Module
import fr.polyflix.search.domain.repository.ModuleRepository
import fr.polyflix.search.infrastructure.repository.elasticsearch.document.ModuleDocument
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class ElasticModuleRepository(private val repository: SpringElasticModuleRepository): ModuleRepository {
    private val logger = LoggerFactory.getLogger(javaClass)

    override fun createOne(module: Module) {
        val document = ModuleDocument.fromDomain(module)
        try {
            repository.save(document)
            logger.info("Module document successfully indexed, id=${document.id}")
        } catch (e: Exception) {
            logger.error("Failed to save the document into Elasticsearch. Details: ${e.message}")
        }
    }

    override fun deleteOne(id: String) {
        try {
            repository.deleteById(id)
            logger.info("If it existed, the module document (id=$id) has been successfully deleted")
        } catch (e: Exception) {
            logger.error("Failed to delete the module document with id=${id}. Details: ${e.message}")
        }
    }

}