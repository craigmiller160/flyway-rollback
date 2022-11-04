package io.craigmiller160.flywayrollback.web.controller

import io.craigmiller160.flywayrollback.web.types.UndoRequest
import io.craigmiller160.flywayrollback.web.types.UndoResponse
import io.craigmiller160.flywayrollback.web.types.UndoScriptResponse
import io.craigmiller160.flywayrollback.web.types.UndoStatus
import org.springframework.core.io.ClassPathResource
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.sql.DataSource

@RestController
@RequestMapping("/flyway")
class FlywayController(
    private val dataSource: DataSource
) {
    @PostMapping("/undo")
    fun undo(@RequestBody request: UndoRequest): UndoResponse {
        val (validScripts, invalidScripts) = request.scripts
            .map { it to ClassPathResource("db/migration/$it") }
            .partition { it.second.exists() }
        return UndoResponse(
            scripts = validScripts.associate { it.first to UndoScriptResponse(UndoStatus.SUCCESS) } +
                invalidScripts.associate { it.first to UndoScriptResponse(UndoStatus.FAILURE, "Invalid script path") }
        )
    }
}