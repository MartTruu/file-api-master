package com.hrblizz.fileapi.service.impl

import com.fasterxml.jackson.module.kotlin.readValue
import com.hrblizz.fileapi.controller.FileMetadataDto
import com.hrblizz.fileapi.controller.exception.NotFoundException
import com.hrblizz.fileapi.data.entities.FileEntity
import com.hrblizz.fileapi.data.repository.FileEntityRepository
import com.hrblizz.fileapi.service.FileData
import com.hrblizz.fileapi.service.FileService
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile
import java.time.Instant
import java.util.*

@Service
class FileServiceImpl(
    private val repository: FileEntityRepository,
    private val objectMapper: ObjectMapper
) : FileService {

    @Transactional
    override fun uploadFile(
        name: String,
        contentType: String,
        metaJson: String,
        source: String,
        expireTime: String?,
        content: MultipartFile
    ): String {
        val token = UUID.randomUUID().toString()
        val meta: Map<String, Any> = objectMapper.readValue(metaJson)
        val now = Instant.now()

        val entity = FileEntity(
            token       = token,
            filename    = name,
            contentType = contentType,
            content     = content.bytes,
            size        = content.size,
            meta        = meta,
            source      = source,
            createTime  = now.toString()
        )
        repository.save(entity)
        return token
    }

    @Transactional(readOnly = true)
    override fun getFilesMeta(tokens: List<String>): Map<String, FileMetadataDto> {
        val list = repository.findAllByTokenIn(tokens)
        return list.associate { ent ->
            ent.token to FileMetadataDto(
                token       = ent.token,
                filename    = ent.filename,
                size        = ent.size,
                contentType = ent.contentType,
                createTime  = ent.createTime,
                meta        = ent.meta
            )
        }
    }

    @Transactional(readOnly = true)
    override fun getFile(token: String): FileData? {
        val ent = repository.findByToken(token) ?: return null
        return FileData(
            content     = ent.content,
            filename    = ent.filename,
            contentType = ent.contentType,
            size        = ent.size,
            createTime  = ent.createTime
        )
    }

    @Transactional
    override fun deleteFile(token: String) {
        val ent = repository.findByToken(token)
            ?: throw NotFoundException("File with token '$token' not found")
        repository.delete(ent)
    }
}
