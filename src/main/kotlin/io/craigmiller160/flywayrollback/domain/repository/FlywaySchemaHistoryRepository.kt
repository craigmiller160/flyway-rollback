package io.craigmiller160.flywayrollback.domain.repository

import io.craigmiller160.flywayrollback.domain.entity.FlywaySchemaHistory
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.stereotype.Repository
import javax.transaction.Transactional

@Repository
interface FlywaySchemaHistoryRepository : JpaRepository<FlywaySchemaHistory, Int> {
    fun findByScript(script: String): FlywaySchemaHistory?
    @Transactional
    @Modifying(flushAutomatically = true, clearAutomatically = true)
    fun deleteByScript(script: String)
}