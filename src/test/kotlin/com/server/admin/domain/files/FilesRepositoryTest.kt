package com.server.admin.domain.files

import kotlinx.coroutines.newFixedThreadPoolContext
import org.junit.After
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.assertj.core.api.Assertions.assertThat
import javax.annotation.Resource

@RunWith(SpringRunner::class)
@SpringBootTest
class FilesRepositoryTest {

    @Autowired
    private lateinit var filesRepository: FilesRepository

    @After
    fun cleanUp() = filesRepository.deleteAll()

    @Test
    fun filesRepoTest(){
        //given
        val fileName = "filename"
        val fileType = "fileType"
        val nextToken = "nextToken"
        val lastModifiedTime = "time"

        filesRepository.save(Files(
            fileName = fileName, fileType = fileType, nextToken = nextToken, lastModifiedTime = lastModifiedTime
        ))

        var filesList : List<Files> = filesRepository.findAll()

        val file: Files = filesList.get(0)
        assertThat(file.fileName).isEqualTo(fileName)
        assertThat(file.fileType).isEqualTo(fileType)
        assertThat(file.nextToken).isEqualTo(nextToken)
        assertThat(file.lastModifiedTime).isEqualTo(lastModifiedTime)
    }


}