package fr.polyflix.search.infrastructure.messaging.kafka.event

import fr.polyflix.search.domain.entity.Video

class VideoEvent(
    id: String,
    trigger: Trigger,
    val fields: Video
) : ResourceEvent(id, trigger) {
    override fun toString(): String {
        return "VideoEvent { trigger = $trigger, id = $id, fields = $fields }"
    }
}
