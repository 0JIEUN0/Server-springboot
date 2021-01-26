package com.server.admin.domain.files

import javax.persistence.*

@Entity
class Files (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = Long.MAX_VALUE,

    @Column(length = 500, nullable = false)
    var fileName: String,

    @Column(length = 500, nullable = false)
    var fileType: String,

    @Column(length = 500, nullable = false)
    var nextToken: String,

    @Column(length = 500, nullable = false)
    var lastModifiedTime: String,

) {
    fun update(fileName: String, lastModifiedTime: String){
        this.fileName = fileName
        this.lastModifiedTime = lastModifiedTime
    }
}