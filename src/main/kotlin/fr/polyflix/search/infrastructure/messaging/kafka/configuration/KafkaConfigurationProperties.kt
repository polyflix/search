package fr.polyflix.search.infrastructure.messaging.kafka.configuration

import fr.polyflix.search.infrastructure.messaging.kafka.configuration.consumer.Consumer
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties("spring.kafka")
data class KafkaConfigurationProperties(
    val bootstrapServers: List<String>,
    val consumer: Consumer
)
