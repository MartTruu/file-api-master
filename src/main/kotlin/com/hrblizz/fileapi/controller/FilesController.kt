package com.hrblizz.fileapi.controller

import com.hrblizz.fileapi.service.FileService
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
class FilesController(
    private val fileService: FileService
) {

    @PostMapping(
        "/files",
        consumes = [MediaType.MULTIPART_FORM_DATA_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun uploadFile(
        @RequestParam name: String,
        @RequestParam contentType: String,
        @RequestParam meta: String,
        @RequestParam source: String,
        @RequestParam(required = false) expireTime: String?,
        @RequestPart("content") content: MultipartFile
    ): ResponseEntity<UploadResponse> {
        val token = fileService.uploadFile(
            name = name,
            contentType = contentType,
            metaJson = meta,
            source = source,
            expireTime = expireTime,
            content = content
        )
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(UploadResponse(token))
    }

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

data class UploadResponse(val token: String)

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
