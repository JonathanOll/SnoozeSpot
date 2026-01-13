package iut.fauryollivier.snoozespot.api.entities

import iut.fauryollivier.snoozespot.api.dtos.DTOBase

abstract class EntityBase {

    abstract fun toDTO(): DTOBase
}