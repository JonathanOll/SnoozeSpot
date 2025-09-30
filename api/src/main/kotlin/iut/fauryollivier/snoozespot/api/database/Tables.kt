package iut.fauryollivier.snoozespot.api.database

import io.ktor.server.application.Application
import org.jetbrains.exposed.v1.core.Table
import org.jetbrains.exposed.v1.core.dao.id.IntIdTable
import org.jetbrains.exposed.v1.javatime.CurrentDateTime
import org.jetbrains.exposed.v1.javatime.datetime
import org.jetbrains.exposed.v1.jdbc.Database
import org.jetbrains.exposed.v1.jdbc.SchemaUtils
import org.jetbrains.exposed.v1.jdbc.transactions.transaction

object Tables {

    object Users : Table("user") {
        val id = integer("id").autoIncrement()
        val username = varchar("username", 50).uniqueIndex()
        val email = varchar("email", 320).uniqueIndex()
        val password = varchar("password", 64)
        val profilePictureId = integer("profile_picture_id").references(Files.id).nullable()
        val roleId = integer("role_id").references(Roles.id)
        val karma = integer("karma").default(0)
        val canConnect = integer("can_connect").default(1) // Boolean as Int
        val createdAt = datetime("created_at").defaultExpression(CurrentDateTime)
        val deletedAt = datetime("deleted_at").nullable()

        override val primaryKey = PrimaryKey(id)
    }

    object Roles : IntIdTable("role") {
        val name = varchar("name", 50).uniqueIndex()
        val description = varchar("description", 255).nullable()

    }

    object Permissions : Table("permission") {
        val id = integer("id").autoIncrement()
        val name = varchar("name", 50).uniqueIndex()
        val description = varchar("description", 255).nullable()

        override val primaryKey = PrimaryKey(id)
    }

    object RolePermissions : Table("role_permission") {
        val roleId = integer("role_id").references(Roles.id)
        val permissionId = integer("permission_id").references(Permissions.id)

        override val primaryKey = PrimaryKey(roleId, permissionId)
    }

    object Files : Table("file") {
        val id = integer("id").autoIncrement()
        val name = varchar("name", 255)
        val path = varchar("path", 512).uniqueIndex()
        val type = varchar("type", 50)
        val canBeUsed = integer("can_be_used").default(1) // Boolean as Int
        val createdAt = datetime("created_at").defaultExpression(CurrentDateTime)
        val deletedAt = datetime("deleted_at").nullable()

        override val primaryKey = PrimaryKey(id)
    }

    object SpotAttributes : Table("spot_attribute") {
        val id = integer("id").autoIncrement()
        val name = varchar("name", 50).uniqueIndex()
        val iconId = integer("icon_id").references(Files.id).nullable()

        override val primaryKey = PrimaryKey(id)
    }

    object SpotAttributeLinks : Table("spot_attribute_link") {
        val spotId = integer("spot_id").references(Spots.id)
        val attributeId = integer("attribute_id").references(SpotAttributes.id)

        override val primaryKey = PrimaryKey(spotId, attributeId)
    }

    object Spots : Table("spot") {
        val id = integer("id").autoIncrement()
        val creatorId = integer("creator_id").references(Users.id)
        val name = varchar("name", 255)
        val description = varchar("description", 1000)
        val latitude = double("latitude")
        val longitude = double("longitude")
        val canBeDisplayed = integer("can_be_displayed").default(1) // Boolean as Int
        val createdAt = datetime("created_at").defaultExpression(CurrentDateTime)
        val deletedAt = datetime("deleted_at").nullable()

        override val primaryKey = PrimaryKey(id)
    }

    object SpotPictures : Table("spot_picture") {
        val spotId = integer("spot_id").references(Spots.id)
        val fileId = integer("file_id").references(Files.id)

        override val primaryKey = PrimaryKey(spotId, fileId)
    }

    object SpotComments : Table("spot_comment") {
        val id = integer("id").autoIncrement()
        val userId = integer("user_id").references(Users.id)
        val spotId = integer("spot_id").references(Spots.id)
        val description = varchar("description", 2000)
        val rating = integer("rating")
        val createdAt = datetime("created_at").defaultExpression(CurrentDateTime)
        val deletedAt = datetime("deleted_at").nullable()

        override val primaryKey = PrimaryKey(id)
    }

    object SpotLikes : Table("spot_like") {
        val userId = integer("user_id").references(Users.id)
        val spotId = integer("spot_id").references(Spots.id)

        override val primaryKey = PrimaryKey(userId, spotId)
    }

    object Posts : Table("post") {
        val id = integer("id").autoIncrement()
        val userId = integer("user_id").references(Users.id)
        val content = varchar("content", 5000)
        val likeCount = integer("like_count").default(0)
        val canBeDisplayed = integer("can_be_displayed").default(1)
        val createdAt = datetime("created_at").defaultExpression(CurrentDateTime)
        val deletedAt = datetime("deleted_at").nullable()

        override val primaryKey = PrimaryKey(id)
    }

    object PostPictures : Table("post_picture") {
        val postId = integer("post_id").references(Posts.id)
        val fileId = integer("file_id").references(Files.id)

        override val primaryKey = PrimaryKey(postId, fileId)
    }

    object PostComments : Table("post_comment") {
        val id = integer("id").autoIncrement()
        val userId = integer("user_id").references(Users.id)
        val postId = integer("post_id").references(Posts.id)
        val content = varchar("content", 2000)
        val createdAt = datetime("created_at").defaultExpression(CurrentDateTime)
        val deletedAt = datetime("deleted_at").nullable()

        override val primaryKey = PrimaryKey(id)
    }

    object PostLikes : Table("post_like") {
        val userId = integer("user_id").references(Users.id)
        val postId = integer("post_id").references(Posts.id)

        override val primaryKey = PrimaryKey(userId, postId)
    }

    object Following : Table("following") {
        val followerId = integer("follower_id").references(Users.id)
        val followedId = integer("followed_id").references(Users.id)

        override val primaryKey = PrimaryKey(followerId, followedId)
    }

    val all = listOf(
        Users,
        Roles,
        Permissions,
        RolePermissions,
        Files,
        SpotAttributes,
        SpotAttributeLinks,
        Spots,
        SpotPictures,
        SpotComments,
        SpotLikes,
        Posts,
        PostPictures,
        PostComments,
        PostLikes,
        Following
    )
}