package com.hrblizz.fileapi.data.entities

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.Instant
import java.util.UUID

@Document(collection = "files")
data class FileEntity(
    @Id
    val token: String = UUID.randomUUID().toString(),
    var filename: String = "",
    var contentType: String = "",
    var content: ByteArray = ByteArray(0),
    var size: Long = 0,
    var meta: Map<String, Any> = emptyMap(),
    var source: String = "",
    val createTime: String = Instant.now().toString()
)
