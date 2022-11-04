package io.craigmiller160.flywayrollback.web.controller

import io.craigmiller160.flywayrollback.domain.entity.Person
import io.craigmiller160.flywayrollback.domain.repository.PersonRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/people")
class PersonController(
    private val repo: PersonRepository
) {
    @GetMapping
    fun getAll(): List<Person> = repo.findAll()
}