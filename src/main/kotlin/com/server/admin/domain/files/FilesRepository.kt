package com.server.admin.domain.files

import org.springframework.data.jpa.repository.JpaRepository

interface FilesRepository : JpaRepository<Files, Long> {
}