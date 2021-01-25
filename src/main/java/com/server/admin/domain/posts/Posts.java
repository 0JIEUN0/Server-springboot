package com.server.admin.domain.posts;

import com.server.admin.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor // 기본 생성자 자동 추가
// 이 두개는 lombok 의 annotation -> 코드 단순화 하는 역할. 밑 보다 중요도 적음
// 코틀린 등 다른 언어로의 전환으로 롬복이 더이상 필요없을 경우 쉽게 삭제 가능!
// 중요도가 높을수록 class 선언에 가깝게.
@Entity //JPA 의 annotation. 테이블과 링크될 클래스임을 나타냄 . 이 클래스 이름을 _ 로 사용해서 table 이름으로 쓰는 경우 많음.
public class Posts extends BaseTimeEntity {
    //JPA 을 사용하면 DB 데이터에 작업할 경우 실제 쿼리를 날리기보다 이 Entity 클래스의 수정을 통해 작업

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;    // 해당 테이블의 PK 필드

    //이 클래스의 필드는 Column 으로 나타내지 않아도 모두 칼럼이 되지만, 기본값을 변경하고 싶으면 다음과 같이 사용.
    //문자열의 기본값은 VARCHAR(255).
    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder // 해당 클래스의 빌더 패턴 클래스 생성. 생성자 상단에 선언 시 생성자에 포함된 필드만 빌더에 포함
    public Posts(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }
}
