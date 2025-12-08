package iut.fauryollivier.snoozespot.api.entities

import iut.fauryollivier.snoozespot.api.model.ModelBase

abstract class EntityBase {

    abstract fun toModel() : ModelBase
}