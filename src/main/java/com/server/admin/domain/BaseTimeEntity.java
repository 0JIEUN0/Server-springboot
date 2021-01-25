package com.server.admin.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass   //JPA entity 들이 이 BaseTimeEntity 를 상속하면 필드(createdDate, modifiedDate)들도 칼럼으로 인식하도록 함
@EntityListeners(AuditingEntityListener.class)  //Auditing 기능 포함시킴
public abstract class BaseTimeEntity {

    @CreatedDate
    private LocalDateTime createdDate;  //entity 가 생성되어 저장될 때 시간이 자동 저장됨

    @LastModifiedDate
    private LocalDateTime modifiedDate; //조회한 entity 의 값을 변경할 때 시간이 자동 저장됨
}
