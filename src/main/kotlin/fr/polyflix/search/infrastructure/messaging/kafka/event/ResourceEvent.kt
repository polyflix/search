package fr.polyflix.search.infrastructure.messaging.kafka.event

import fr.polyflix.search.domain.entity.Video

abstract class ResourceEvent(
    val trigger: Trigger
) { }

enum class Trigger {
    CREATE,
    DELETE,
    UPDATE,
}

