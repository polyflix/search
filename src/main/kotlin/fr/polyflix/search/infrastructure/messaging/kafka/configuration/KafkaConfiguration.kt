package fr.polyflix.search.infrastructure.messaging.kafka.configuration

import fr.polyflix.search.infrastructure.messaging.kafka.configuration.consumer.ConsumerProps
import fr.polyflix.search.infrastructure.messaging.kafka.event.VideoEvent
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.listener.ContainerProperties
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer
import org.springframework.kafka.support.serializer.JsonDeserializer

@EnableKafka
@Configuration
class KafkaConfiguration(private val kafkaProperties: KafkaConfigurationProperties) {

    @Bean
    fun kafkaVideoContainerFactory(): ConcurrentKafkaListenerContainerFactory<String, VideoEvent> {
        val factory = ConcurrentKafkaListenerContainerFactory<String, VideoEvent>()

        // Set the concurrency of the consumer
        factory.setConcurrency(kafkaProperties.consumer.video.concurrency)
        factory.setCommonErrorHandler(KafkaConsumerErrorHandler())

        factory.consumerFactory = createConsumerFactory<VideoEvent>(kafkaProperties.consumer.video)
        factory.containerProperties.ackMode = ContainerProperties.AckMode.MANUAL_IMMEDIATE
        factory.containerProperties.isSyncCommits = true

        return factory
    }

    private inline fun <reified T> createConsumerFactory(consumerProps: ConsumerProps): ConsumerFactory<String, T> {
        val props: MutableMap<String, Any> = HashMap()

        props[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG] = kafkaProperties.bootstrapServers
        props[ConsumerConfig.GROUP_ID_CONFIG] = consumerProps.groupId
        props[ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java
        props[ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG] = ErrorHandlingDeserializer::class.java

        return DefaultKafkaConsumerFactory(
            props,
            StringDeserializer(),
            ErrorHandlingDeserializer(JsonDeserializer(T::class.java, false))
        )
    }
}