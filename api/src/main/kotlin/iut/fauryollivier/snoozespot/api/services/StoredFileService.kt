package iut.fauryollivier.snoozespot.api.services

import io.ktor.http.content.*
import iut.fauryollivier.snoozespot.api.dtos.StoredFileDTO
import iut.fauryollivier.snoozespot.api.entities.StoredFile
import iut.fauryollivier.snoozespot.api.enums.StoredFileType
import iut.fauryollivier.snoozespot.api.enums.StoredFileUsage
import iut.fauryollivier.snoozespot.api.repositories.StoredFileRepository
import java.util.UUID
import kotlin.uuid.Uuid

class StoredFileService(private val storedFileRepository: StoredFileRepository) {

    fun saveFile(inputFile: PartData.FileItem, description: String, type: StoredFileType, usage: StoredFileUsage): Result<StoredFile> {
        val idResult = storedFileRepository.saveFile(inputFile, description, type, usage)
        if(idResult.isFailure) {
            return Result.failure(Exception("Could not save file"))
        }
        val fileResult = storedFileRepository.getFileById(idResult.getOrThrow())
        if(fileResult.isFailure) {
            return Result.failure(Exception("File saved but could not be retrieved"))
        }
        return Result.success(fileResult.getOrThrow())
    }

    fun changeFileDescription(fileUUID: UUID, newDescription: String): Result<Unit> {
        return storedFileRepository.changeFileDescription(fileUUID, newDescription)
    }
}