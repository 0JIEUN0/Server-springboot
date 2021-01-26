package com.server.admin.service

import com.server.admin.domain.files.Files
import com.server.admin.domain.files.FilesRepository
import com.server.admin.web.dto.FileResponseDto
import com.server.admin.web.dto.FileSaveRequestDto
import com.server.admin.web.dto.FileUpdateRequestDto
import org.junit.After
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.assertj.core.api.Assertions.assertThat

@RunWith(SpringRunner::class)
@SpringBootTest
class FileServiceTest {

    @Autowired
    lateinit var filesRepository: FilesRepository

    @Autowired
    lateinit var service: FileService

    @After
    fun cleanUp() = filesRepository.deleteAll()

    @Test
    fun testSaveFile(){
        //given
        val fileName = "fileName"
        val fileType = "fileType"
        val nextToken = "token"
        val lastModifiedTime = "time"


        val id: Long = service.save(fileSaveRequestDto =
            FileSaveRequestDto(
                fileName = fileName,
                fileType = fileType,
                nextToken = nextToken,
                lastModifiedTime = lastModifiedTime
        ))

        val result: Files = filesRepository.findById(id).get()

        //assert
        assertThat(result.fileName).isEqualTo(fileName)
        assertThat(result.fileType).isEqualTo(fileType)
        assertThat(result.nextToken).isEqualTo(nextToken)
        assertThat(result.lastModifiedTime).isEqualTo(lastModifiedTime)

    }

    @Test
    fun testFileUpdate(){
        val fileName = "fileName"
        val fileType = "fileType"
        val nextToken = "token"
        val lastModifiedTime = "time"

        val id: Long = service.save(fileSaveRequestDto =
        FileSaveRequestDto(
            fileName = fileName,
            fileType = fileType,
            nextToken = nextToken,
            lastModifiedTime = lastModifiedTime
        ))

        val newName = "newName"
        val newTime = "newTime"
        val newId = service.update(id = id, requestDto =
            FileUpdateRequestDto(newName, newTime)
        )

        val result: Files = filesRepository.findById(newId).get()

        //assert
        assertThat(result.id).isEqualTo(id)
        assertThat(result.id).isEqualTo(newId)
        assertThat(result.fileName).isEqualTo(newName)
        assertThat(result.fileType).isEqualTo(fileType)
        assertThat(result.nextToken).isEqualTo(nextToken)
        assertThat(result.lastModifiedTime).isEqualTo(newTime)

    }
}