package com.hrblizz.fileapi.controller

import com.hrblizz.fileapi.controller.exception.NotFoundException
import com.hrblizz.fileapi.service.FileService
import org.springframework.core.io.ByteArrayResource
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class FileController(
    private val fileService: FileService
) {

    @GetMapping("/file/{token}")
    fun downloadFile(@PathVariable token: String): ResponseEntity<ByteArrayResource> {
        val file = fileService.getFile(token)
            ?: throw NotFoundException("File with token '$token' not found")

        val resource = ByteArrayResource(file.content)
        val headers = HttpHeaders().apply {
            add("X-Filename", file.filename)
            add("X-Filesize", file.size.toString())
            add("X-CreateTime", file.createTime)
            contentType = MediaType.parseMediaType(file.contentType)
        }

        return ResponseEntity.ok()
            .headers(headers)
            .body(resource)
    }

    @DeleteMapping("/file/{token}")
    fun deleteFile(@PathVariable token: String): ResponseEntity<Void> {
        fileService.deleteFile(token)
        return ResponseEntity.noContent().build()
    }
}
