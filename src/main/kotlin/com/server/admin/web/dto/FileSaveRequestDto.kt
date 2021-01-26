package com.server.admin.web.dto

import com.server.admin.domain.files.Files

class FileSaveRequestDto (
    var id: Long = 0,
    var fileName: String,
    var fileType: String,
    var nextToken: String,
    var lastModifiedTime: String
){

    fun toEntity(): Files {
        return Files(
            id = this.id,
            fileName = this.fileName,
            fileType = fileType,
            nextToken = nextToken,
            lastModifiedTime = lastModifiedTime
        )
    }
}