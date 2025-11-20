package iut.fauryollivier.snoozespot.api.repositories

import iut.fauryollivier.snoozespot.api.entities.EntityBase
import org.jetbrains.exposed.sql.ResultRow

abstract class RepositoryBase {
    abstract fun ResultRow.toEntity(loadRelations: Boolean = false): EntityBase;
}