package io.craigmiller160.flywayrollback.domain.repository

import io.craigmiller160.flywayrollback.domain.entity.Person
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface PersonRepository : JpaRepository<Person, UUID>