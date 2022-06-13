package fr.polyflix.search.infrastructure.messaging.kafka.event

import fr.polyflix.search.domain.entity.Module

class ModuleEvent(
    trigger: Trigger,
    val payload: Module
) : ResourceEvent(trigger) {
    override fun toString(): String {
        return "ModuleEvent { trigger = $trigger, payload = $payload }"
    }
}

