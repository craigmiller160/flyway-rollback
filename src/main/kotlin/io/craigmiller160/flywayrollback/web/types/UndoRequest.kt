package io.craigmiller160.flywayrollback.web.types

data class UndoRequest(
    val scripts: List<String>
)
