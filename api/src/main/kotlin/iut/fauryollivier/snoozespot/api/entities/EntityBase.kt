package iut.fauryollivier.snoozespot.api.entities

import iut.fauryollivier.snoozespot.api.models.ModelBase

abstract class EntityBase {

    abstract fun toModel() : ModelBase
}