package fr.polyflix.search

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan
class SearchMicroservice

fun main(args: Array<String>) {
	runApplication<SearchMicroservice>(*args)
}
