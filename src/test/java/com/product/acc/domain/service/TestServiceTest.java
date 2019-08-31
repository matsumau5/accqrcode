package com.product.acc.domain.service;

import com.product.acc.domain.app.model.TestInfo;
import com.product.acc.domain.app.repository.testinfo.TestInfoRepository;
import com.product.acc.domain.app.service.test.TestService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestServiceTest {

    @Inject
    private TestService testService;

    @MockBean
    private TestInfoRepository testInfoRepository;

    @Test
    public void テストサービスのテスト() throws Exception {
        TestInfo info = new TestInfo();
        info.setId(1);
        when(testInfoRepository.getTest()).thenReturn(info);
        assertEquals(info, testService.getTestInfo());
    }
}
