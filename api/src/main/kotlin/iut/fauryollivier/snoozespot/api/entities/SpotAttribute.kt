package iut.fauryollivier.snoozespot.api.entities

import iut.fauryollivier.snoozespot.api.models.SpotAttributeModel
import java.time.LocalDateTime

data class SpotAttribute(

    val id: Int,
    val name: String,
    val iconId: Int?,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime?,
    val deletedAt: LocalDateTime? = null

) : EntityBase() {

    override fun toModel(): SpotAttributeModel = SpotAttributeModel(
        id = this.id,
        name = this.name,
        iconId = this.iconId,
        createdAt = this.createdAt,
        updatedAt = this.updatedAt,
        deletedAt = this.deletedAt,
    )
}