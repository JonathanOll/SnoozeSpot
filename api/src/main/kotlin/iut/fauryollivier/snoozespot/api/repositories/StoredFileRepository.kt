package iut.fauryollivier.snoozespot.api.repositories

import io.ktor.http.content.*
import io.ktor.utils.io.jvm.javaio.*
import iut.fauryollivier.snoozespot.api.database.Tables
import iut.fauryollivier.snoozespot.api.database.Tables.Files.description
import iut.fauryollivier.snoozespot.api.entities.StoredFile
import iut.fauryollivier.snoozespot.api.enums.StoredFileType
import iut.fauryollivier.snoozespot.api.enums.StoredFileUsage
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.experimental.suspendedTransactionAsync
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import java.time.LocalDateTime
import java.util.*
import kotlin.io.path.Path

class StoredFileRepository(private val uploadDir: String) : RepositoryBase() {

    init {
        Files.createDirectories(Paths.get(uploadDir))
    }

    override fun ResultRow.toEntity(
        loadRelations: Boolean
    ): StoredFile {

        return StoredFile(
            id = this[Tables.Files.id].value,
            uuid = this[Tables.Files.uuid],
            description = this[Tables.Files.description],
            path = this[Tables.Files.path],
            type = StoredFileType.fromInt( this[Tables.Files.type]) ?: StoredFileType.UNKNOW,
            usage = StoredFileUsage.fromInt(this[Tables.Files.usage]) ?: StoredFileUsage.UNKNOW,
            createdAt = this[Tables.Files.createdAt],
            deletedAt = this[Tables.Files.deletedAt]
        )
    }

    suspend fun getFileById(id: Int): Result<StoredFile> {
        val file = suspendedTransactionAsync {
            Tables.Files.select { Tables.Files.id eq id }
                .map { it.toEntity(false) }
                .singleOrNull()
        }.await()
        if ( file == null) {
            return Result.failure(Exception("File not found"))
        }
        return Result.success(file)
    }

    suspend fun saveFile(inputFile: PartData.FileItem, description: String, type: StoredFileType, usage: StoredFileUsage): Result<Int> {
        if(inputFile.originalFileName.isNullOrBlank()) {
            return Result.failure(Exception("File must have a name or extension"))
        }

        val uuid = UUID.randomUUID()
        val fileExtension = File(inputFile.originalFileName!!).extension
        val uniqueFileName = "${uuid}.$fileExtension"

        val filePath = Path(uploadDir, StoredFileUsage.getPathForUsage(usage), StoredFileType.getPathForType(type))
        Files.createDirectories(filePath)

        val file = File(filePath.toFile(), uniqueFileName)

        file.outputStream().buffered().use { outputStream ->
            inputFile.provider().toInputStream().use { inputStream ->
                inputStream.copyTo(outputStream)
            }
            outputStream.flush()
        }

        val storedFileId = suspendedTransactionAsync {
            Tables.Files.insertAndGetId {
                it[Tables.Files.uuid] = uuid
                it[Tables.Files.description] = description
                it[Tables.Files.path] = file.path
                it[Tables.Files.type] = type.value
                it[Tables.Files.usage] = usage.value
                it[Tables.Files.canBeUsed] = 1
                it[Tables.Files.createdAt] = LocalDateTime.now()
                it[Tables.Files.deletedAt] = null
            }
        }.await()

        return Result.success(storedFileId.value)
    }

    suspend fun changeFileDescription(uuid: UUID, newDescription: String): Result<Unit> {
        val updatedRows = suspendedTransactionAsync {
            Tables.Files.update({ Tables.Files.uuid eq uuid }) {
                it[description] = newDescription
            }
        }.await()

        return if (updatedRows > 0) {
            Result.success(Unit)
        } else {
            Result.failure(Exception("File not found or description unchanged"))
        }
    }

    fun getFilesBySpotId(spotId: Int): List<StoredFile> {
        val files = transaction {
            (Tables.SpotPictures innerJoin Tables.Files)
                .select { Tables.SpotPictures.spotId eq spotId }
                .map { it.toEntity(false) }
        }

        return files
    }

    fun getFilesByPostId(postId: Int): List<StoredFile> {
        val files = transaction {
            (Tables.PostPictures innerJoin Tables.Files)
                .select { Tables.PostPictures.postId eq postId }
                .map { it.toEntity(false) }
        }

        return files
    }


}