package fr.polyflix.search.infrastructure.messaging.kafka.event

abstract class ResourceEvent(
    val id: String,
    val trigger: Trigger,
)

enum class Trigger {
    CREATE,
    DELETE,
    UPDATE,
}

