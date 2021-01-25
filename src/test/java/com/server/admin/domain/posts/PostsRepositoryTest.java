package com.server.admin.domain.posts;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest //별다른 설정 없이 이걸 사용하면 H2 데이터베이스를 자동으로 실행해줌.
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @After // JUnit 에서 단위 테스트가 끝날 때마다 수행되는 메소드 지정.
    // 보통 테스트 간 데이터 침범 막기 위해 사용. H2 에 데이터가 남아 있지 않도로고 관리.
    public void cleanup(){
        postsRepository.deleteAll();
    }

    @Test
    public void postSave_select() {
        //given
        String title = "테스트 게시글";
        String content = "테스트 본문";

        postsRepository.save(Posts.builder()    //id 값이 있다면 update, 없다면 insert 쿼리가 실행됨
                .title(title)
                .content(content)
                .author("server@gamil.com")
                .build());

        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);

    }

    @Test
    public void BaseTimeEntity_register(){
        //given
        LocalDateTime now = LocalDateTime.of(2021,1,24,0,0,0);
        postsRepository.save(Posts.builder()
                .title("title")
                .content("content")
                .author("author")
                .build()
        );

        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);

        System.out.println(">>>>>>>>>> createDate= " + posts.getCreatedDate() +", modifiedDate="+ posts.getModifiedDate());
        assertThat(posts.getCreatedDate()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);
    }
}
