package fr.polyflix.search

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SearchMicroservice

fun main(args: Array<String>) {
	runApplication<SearchMicroservice>(*args)
}
