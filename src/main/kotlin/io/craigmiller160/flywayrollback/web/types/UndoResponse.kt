package io.craigmiller160.flywayrollback.web.types

data class UndoResponse(
    val scripts: Map<String,UndoScriptResponse>
)