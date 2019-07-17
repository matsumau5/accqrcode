package com.product.acc.web;

import com.product.acc.domain.app.model.TestInfo;
import com.product.acc.domain.app.service.test.TestService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
@RequiredArgsConstructor
public class TestController {

    @NonNull
    private TestService testService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public TestInfo getTestInfo(){
        //return new TestInfo();
        return testService.getTestInfo();
    }
}
