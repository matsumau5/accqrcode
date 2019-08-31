package com.product.acc.web.controller;

import com.product.acc.domain.app.model.TestInfo;
import com.product.acc.domain.app.repository.testinfo.TestInfoRepository;
import com.product.acc.domain.app.service.test.TestService;
import com.product.acc.web.TestController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@WebMvcTest(TestController.class)
public class TestControllerTest {



    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TestService testService;

    @MockBean
    private TestInfoRepository testInfoRepository;

    @Test
    public void テストコントローラのテスト() throws Exception {
        TestInfo info = new TestInfo();
        info.setId(1);
        when(testService.getTestInfo()).thenReturn(info);
        this.mockMvc.perform(get("/api/test"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(info.getId())));
    }
}
