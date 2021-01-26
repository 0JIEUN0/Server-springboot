package com.server.admin.web

import com.server.admin.service.FileService
import com.server.admin.web.dto.FileResponseDto
import com.server.admin.web.dto.FileSaveRequestDto
import com.server.admin.web.dto.FileUpdateRequestDto
import lombok.RequiredArgsConstructor
import org.springframework.web.bind.annotation.*

@RequiredArgsConstructor
@RestController
class FilesApiController (val fileService: FileService){

    @PostMapping("/api/v1/files")
    fun save(@RequestBody fileSaveRequestDto: FileSaveRequestDto) : Long {
        return fileService.save(fileSaveRequestDto)
    }

    @PutMapping("api/v1/files/{id}")
    fun update(@PathVariable id: Long, @RequestBody requestDto: FileUpdateRequestDto) : Long{
        return fileService.update(id, requestDto)
    }

    @GetMapping("api/v1/files/{id}")
    fun findById(@PathVariable id: Long) : FileResponseDto{
        return fileService.findById(id)
    }

}