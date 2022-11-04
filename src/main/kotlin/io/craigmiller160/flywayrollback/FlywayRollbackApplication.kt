package io.craigmiller160.flywayrollback

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class FlywayRollbackApplication

fun main(args: Array<String>) {
	runApplication<FlywayRollbackApplication>(*args)
}
