package io.craigmiller160.flywayrollback.domain.entity

import java.util.UUID
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "people")
data class Person(
    @Id
    val id: UUID = UUID.randomUUID(),
    val name: String
)
