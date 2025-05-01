package com.hrblizz.fileapi.data.repository

import com.hrblizz.fileapi.data.entities.FileEntity
import org.springframework.data.mongodb.repository.MongoRepository

interface FileEntityRepository : MongoRepository<FileEntity, String> {
    fun findByToken(token: String): FileEntity?
    fun findAllByTokenIn(tokens: List<String>): List<FileEntity>
}
