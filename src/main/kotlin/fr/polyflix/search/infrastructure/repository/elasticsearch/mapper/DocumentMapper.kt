package fr.polyflix.search.infrastructure.repository.elasticsearch.mapper

interface DocumentMapper<in DomainEntity, out Document> {
    fun toDocument(entity: DomainEntity): Document
}