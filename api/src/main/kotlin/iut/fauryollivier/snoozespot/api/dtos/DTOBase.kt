package iut.fauryollivier.snoozespot.api.dtos

import iut.fauryollivier.snoozespot.api.model.ModelBase

abstract class DTOBase {

    abstract fun toModel() : ModelBase
}