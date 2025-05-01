package com.hrblizz.fileapi.controller

import com.hrblizz.fileapi.service.FileService
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class FilesController(
    private val fileService: FileService
) {

    @PostMapping(
        "/files/metas",
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun getFilesMeta(@RequestBody request: FilesMetaRequest): ResponseEntity<FilesMetaResponse> {
        val metas: Map<String, FileMetadataDto> = fileService.getFilesMeta(request.tokens)
        return ResponseEntity.ok(FilesMetaResponse(metas))
    }
}

data class FilesMetaRequest(val tokens: List<String>)

data class FilesMetaResponse(val files: Map<String, FileMetadataDto>)

data class FileMetadataDto(
    val token: String,
    val filename: String,
    val size: Long,
    val contentType: String,
    val createTime: String,
    val meta: Map<String, Any>
)
