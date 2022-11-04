package io.craigmiller160.flywayrollback.domain.repository

import io.craigmiller160.flywayrollback.domain.entity.FlywaySchemaHistory
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface FlywaySchemaHistoryRepository : JpaRepository<FlywaySchemaHistory, Int>