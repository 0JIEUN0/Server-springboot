package com.server.admin.service

import com.server.admin.domain.files.Files
import com.server.admin.domain.files.FilesRepository
import com.server.admin.web.dto.FileResponseDto
import com.server.admin.web.dto.FileSaveRequestDto
import com.server.admin.web.dto.FileUpdateRequestDto
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service
import java.lang.IllegalArgumentException
import javax.transaction.Transactional

@RequiredArgsConstructor
@Service
class FileService (val filesRepository: FilesRepository){

    @Transactional
    fun save(fileSaveRequestDto: FileSaveRequestDto) : Long{
        return filesRepository.save(fileSaveRequestDto.toEntity()).id
    }

    @Transactional
    fun update(id: Long, requestDto: FileUpdateRequestDto) : Long {
        val file: Files = filesRepository.findById(id)
            .orElseThrow{(IllegalArgumentException("해당 파일 없음 id="+id))}
        file.update(requestDto.fileName, requestDto.lastModifiedTime)

        return id
    }

    fun findById(id: Long): FileResponseDto {
        val file : Files = filesRepository.findById(id)
            .orElseThrow{(IllegalArgumentException("해당 파일 없음 id="+id))}
        return FileResponseDto(file)
    }

}