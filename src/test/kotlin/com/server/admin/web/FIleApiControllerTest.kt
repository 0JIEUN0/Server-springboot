package com.server.admin.web

import com.server.admin.domain.files.Files
import com.server.admin.domain.files.FilesRepository
import com.server.admin.web.dto.FileSaveRequestDto
import com.server.admin.web.dto.FileUpdateRequestDto
import org.assertj.core.api.Assertions
import org.junit.After
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.ResponseEntity
import org.springframework.test.context.junit4.SpringRunner
import org.assertj.core.api.Assertions.assertThat;
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class FIleApiControllerTest {

    @LocalServerPort
    var port : Int = 0

    @Autowired
    lateinit var restTemplate : TestRestTemplate

    @Autowired
    lateinit var filesRepository: FilesRepository

    @After
    fun cleanUp() = filesRepository.deleteAll()

    @Test
    fun testSaveFile() {
        val fileName = "fileName"
        val fileType = "fileType"
        val nextToken = "token"
        val lastModifiedTime = "time"

        val requestDto = FileSaveRequestDto(
            fileName = fileName,
            fileType = fileType,
            nextToken = nextToken,
            lastModifiedTime = lastModifiedTime
        )

        val url = "http://localhost:$port/api/v1/files"

        //api request
        val responseEntity : ResponseEntity<Long> = restTemplate.postForEntity(url, requestDto, Long::class.java)

        //assert
        assertThat(responseEntity.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(responseEntity.body).isGreaterThan(0L)

        val result : Files = filesRepository.findAll().get(0)
        assertThat(result.fileName).isEqualTo(fileName)
        assertThat(result.fileType).isEqualTo(fileType)
        assertThat(result.nextToken).isEqualTo(nextToken)
        assertThat(result.lastModifiedTime).isEqualTo(lastModifiedTime)
    }

    @Test
    fun testUpdateFile(){
        val fileName = "fileName"
        val fileType = "fileType"
        val nextToken = "token"
        val lastModifiedTime = "time"

        val updataId: Long = filesRepository.save(
            Files(fileName = fileName, fileType = fileType, nextToken = nextToken, lastModifiedTime = lastModifiedTime)).id

        val requestDto = FileUpdateRequestDto(
            fileName = fileName,
            lastModifiedTime = lastModifiedTime
        )

        val url = "http://localhost:$port/api/v1/files/$updataId"

        //request
        var requestEntity : HttpEntity<FileUpdateRequestDto> = HttpEntity(requestDto)

        //response
        var responseEntity : ResponseEntity<Long> = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long::class.java)

        //assert
        assertThat(responseEntity.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(responseEntity.body).isGreaterThan(0L)
        println(responseEntity.body)

        val result : Files = filesRepository.findAll().get(0)
        assertThat(result.fileName).isEqualTo(fileName)
        assertThat(result.fileType).isEqualTo(fileType)
        assertThat(result.nextToken).isEqualTo(nextToken)
        assertThat(result.lastModifiedTime).isEqualTo(lastModifiedTime)

    }


}