package fr.polyflix.search.infrastructure.messaging.kafka.consumer

import fr.polyflix.search.domain.repository.UserRepository
import fr.polyflix.search.infrastructure.messaging.kafka.event.Trigger
import fr.polyflix.search.infrastructure.messaging.kafka.event.UserEvent
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.support.Acknowledgment
import org.springframework.stereotype.Component

@Component
class KafkaUserConsumer(private val userRepository: UserRepository) {
    private val logger = LoggerFactory.getLogger(javaClass)

    @KafkaListener(topics = ["\${spring.kafka.consumer.user.topic}"], containerFactory = "kafkaUserContainerFactory")
    fun onMessage(event: UserEvent, ack: Acknowledgment) {
        logger.info("Received new event: $event")

        when (event.trigger) {
            Trigger.CREATE, Trigger.UPDATE -> userRepository.createOne(event.payload)
            Trigger.DELETE -> userRepository.deleteOne(event.payload.id!!)
        }
        ack.acknowledge()
    }
}