package io.craigmiller160.flywayrollback.domain.entity

import java.time.ZonedDateTime
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "flyway_schema_history")
data class FlywaySchemaHistory(
    @Id
    val installedRank: Int,
    val version: String,
    val description: String,
    val type: String,
    val script: String,
    val checksum: Int,
    val installedBy: String,
    val installedOn: ZonedDateTime,
    val executionTime: Int,
    val success: Boolean
)