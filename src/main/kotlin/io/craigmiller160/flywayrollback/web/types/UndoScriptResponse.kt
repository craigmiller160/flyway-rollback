package io.craigmiller160.flywayrollback.web.types

data class UndoScriptResponse(
    val status: UndoStatus,
    val message: String? = null
)
