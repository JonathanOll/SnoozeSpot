package iut.fauryollivier.snoozespot.api.dtos

import iut.fauryollivier.snoozespot.api.entities.EntityBase

abstract class DTOBase {

    abstract fun toEntity(): EntityBase
}