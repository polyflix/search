package fr.polyflix.search.infrastructure.messaging.kafka.consumer

import fr.polyflix.search.domain.repository.ModuleRepository
import fr.polyflix.search.infrastructure.messaging.kafka.event.ModuleEvent
import fr.polyflix.search.infrastructure.messaging.kafka.event.Trigger
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.support.Acknowledgment
import org.springframework.stereotype.Component

@Component
class KafkaModuleConsumer(private val moduleRepository: ModuleRepository) {
    private val logger = LoggerFactory.getLogger(javaClass)
    @KafkaListener(topics = ["\${spring.kafka.consumer.module.topic}"], containerFactory = "kafkaModuleContainerFactory")
    fun onMessage(event: ModuleEvent, ack: Acknowledgment) {
        logger.info("Received new event: $event")

        // Ignore draft elements and non public elements
        if (event.payload.draft == true || event.payload.visibility != "public") {
            logger.info("The module ${event.payload.id} isn't public or it is in draft state. Ignoring it.")
            return ack.acknowledge()
        }

        when (event.trigger) {
            Trigger.CREATE, Trigger.UPDATE -> moduleRepository.createOne(event.payload)
            Trigger.DELETE -> moduleRepository.deleteOne(event.payload.id!!)
        }
        ack.acknowledge()
    }
}