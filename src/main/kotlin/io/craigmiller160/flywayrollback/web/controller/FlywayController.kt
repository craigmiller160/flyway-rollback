package io.craigmiller160.flywayrollback.web.controller

import io.craigmiller160.flywayrollback.domain.repository.FlywaySchemaHistoryRepository
import io.craigmiller160.flywayrollback.web.types.UndoRequest
import org.springframework.core.io.ClassPathResource
import org.springframework.http.HttpStatus
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.lang.RuntimeException
import java.nio.charset.StandardCharsets
import java.sql.Connection
import javax.sql.DataSource
import javax.transaction.Transactional

@RestController
@RequestMapping("/flyway")
class FlywayController(
    private val dataSource: DataSource,
    private val flywaySchemaHistoryRepository: FlywaySchemaHistoryRepository
) {
    @PostMapping("/undo")
    @Transactional
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun undo(@RequestBody request: UndoRequest) {
        dataSource.connection.use { conn ->
            conn.autoCommit = false
            request.scripts.forEach { findAndExecuteRollbackScript(conn, it) }
            conn.commit()
        }
    }

    // TODO validate transactional nature of all this
    private fun findAndExecuteRollbackScript(connection: Connection, script: String) {
        val record = flywaySchemaHistoryRepository.findByScript(script) ?: throw RuntimeException("Script was never executed")

        val undoScript = script.replace(Regex("^V"), "U")
        val resource = ClassPathResource("db/migration/$undoScript")
        if (!resource.exists()) {
            throw RuntimeException("Undo script does not exist on classpath: $undoScript")
        }

        ResourceDatabasePopulator(false, false, StandardCharsets.UTF_8.name(), resource)
            .populate(connection)
        flywaySchemaHistoryRepository.deleteByScript(script)
    }
}