package com.product.acc.domain.app.service.test;

import com.product.acc.domain.app.model.TestInfo;
import com.product.acc.domain.app.repository.testinfo.TestInfoRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * QRcode Service Implements
 * @author matsuoka
 * @version $Revision$
 */
@Service
@Transactional
@RequiredArgsConstructor
class TestServiceImpl implements TestService {

    @NonNull
    private final TestInfoRepository testInfoRepository;

    @Override
    public TestInfo getTestInfo() {
        TestInfo test = new TestInfo();
        test.setId(1);
        test.setCol1("aab");
//        return test;
        return testInfoRepository.getTest();
    }
}
