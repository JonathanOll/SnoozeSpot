package iut.fauryollivier.snoozespot.api.dtos

import iut.fauryollivier.snoozespot.api.models.ModelBase

abstract class DTOBase {

    abstract fun toModel(loadRelations: Boolean = false) : ModelBase
}