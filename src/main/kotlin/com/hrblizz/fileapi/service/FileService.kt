package com.hrblizz.fileapi.service

import org.springframework.web.multipart.MultipartFile
import com.hrblizz.fileapi.controller.FileMetadataDto

interface FileService {

    fun uploadFile(
        name: String,
        contentType: String,
        metaJson: String,
        source: String,
        expireTime: String?,
        content: MultipartFile
    ): String

    fun getFilesMeta(tokens: List<String>): Map<String, FileMetadataDto>

    fun getFile(token: String): FileData?

}

data class FileData(
    val content: ByteArray,
    val filename: String,
    val contentType: String,
    val size: Long,
    val createTime: String
)
