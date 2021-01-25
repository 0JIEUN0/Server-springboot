package com.server.admin.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import com.server.admin.web.HelloController;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


//JUnit : Unit 테스트를 도와주는 프레임 워크 중 하나. (java)
@RunWith(SpringRunner.class) //테스트를 진행할 때 JUnit 에 내장된 실행자 외에 다른 실행자를 실행시킴
@WebMvcTest(controllers = HelloController.class) //여러 spring annotation 중 Web(SpringMVC) 에 집중할 수 있는.
public class HelloControllerTest {

    @Autowired  //spring 이 관리하는 Bean 을 주입 받음
    private MockMvc mvc;    //웹 API 를 테스트할 때 사용

    @Test   //
    public void returnHello() throws Exception {
        String hello = "hello";
        mvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string(hello));
    }

    @Test
    public void returnHelloDto() throws Exception {
        String name = "hello";
        int amount = 1000;

        mvc.perform(
                get("/hello/dto")
                        .param("name", name)
                        .param("amount", String.valueOf(amount)))
                //param 은 String 만 가능.
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.amount", is(amount)));
        //jsonPath:: json 응답값을 필드별로 검증할 수 있는 메소드
        //$ 을 기준으로 필드명을 명시.
    }

}
