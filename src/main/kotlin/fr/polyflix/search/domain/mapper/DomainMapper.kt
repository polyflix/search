package fr.polyflix.search.domain.mapper

interface DomainMapper<DomainEntity> {
    fun toDomain(): DomainEntity
}