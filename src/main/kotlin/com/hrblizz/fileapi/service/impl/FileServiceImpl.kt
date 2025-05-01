package com.hrblizz.fileapi.service.impl

import com.hrblizz.fileapi.controller.FileMetadataDto
import com.hrblizz.fileapi.data.repository.FileEntityRepository
import com.hrblizz.fileapi.service.FileData
import com.hrblizz.fileapi.service.FileService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class FileServiceImpl(
    private val repository: FileEntityRepository
) : FileService {

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

}
