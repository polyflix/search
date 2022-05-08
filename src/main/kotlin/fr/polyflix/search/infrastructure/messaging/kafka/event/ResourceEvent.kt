package fr.polyflix.search.infrastructure.messaging.kafka.event

import java.util.UUID

abstract class ResourceEvent(
    val id: UUID,
    val trigger: Trigger,
)

enum class Trigger {
    CREATE,
    DELETE,
    UPDATE,
}

