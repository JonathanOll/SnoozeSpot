package iut.fauryollivier.snoozespot.api.data

import kotlin.reflect.full.memberProperties
import kotlin.reflect.full.primaryConstructor

inline fun <reified T : Any, reified R : Any> T.mapToRoomModel(): R {
    val constructor = R::class.primaryConstructor!!
    val args = constructor.parameters.associateWith { param ->
        T::class.memberProperties.firstOrNull { it.name == param.name }?.getter?.call(this)
    }
    return constructor.callBy(args)
}