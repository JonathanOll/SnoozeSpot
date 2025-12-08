package iut.fauryollivier.snoozespot.api.models

import iut.fauryollivier.snoozespot.api.dtos.DTOBase
import iut.fauryollivier.snoozespot.api.entities.EntityBase

abstract class ModelBase {

    abstract fun toEntity() : EntityBase
    abstract fun toDTO(loadRelations: Boolean = false) : DTOBase
}