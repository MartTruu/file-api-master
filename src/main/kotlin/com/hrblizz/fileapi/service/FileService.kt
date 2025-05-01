package com.hrblizz.fileapi.service

import com.hrblizz.fileapi.controller.FileMetadataDto

interface FileService {

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
