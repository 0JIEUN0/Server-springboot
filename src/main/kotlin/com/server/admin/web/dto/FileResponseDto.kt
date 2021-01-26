package com.server.admin.web.dto

import com.server.admin.domain.files.Files

class FileResponseDto (
    var id: Long = 0,
    var fileName: String,
    var fileType: String,
    var nextToken: String,
    var lastModifiedTime: String,
) {

    constructor(entity: Files) : this (
        id = entity.id,
        fileName = entity.fileName,
        fileType = entity.fileType,
        nextToken = entity.nextToken,
        lastModifiedTime = entity.lastModifiedTime
    )

    fun toEntity() : Files {
        return Files(
            id = this.id,
            fileName = this.fileName,
            fileType = this.fileType,
            nextToken = this.nextToken,
            lastModifiedTime = this.lastModifiedTime
        )
    }
}