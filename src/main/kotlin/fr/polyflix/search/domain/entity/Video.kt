package fr.polyflix.search.domain.entity

import java.util.UUID

class Video(uuid: UUID, val title: String): Searchable(uuid)