package ioscar.common.desensitization;

import cn.com.icbf.framework.common.model.Result;
import com.alibaba.fastjson.JSON;
import ioscar.common.desensitization.entity.TestCaseDto;
import ioscar.common.desensitization.entity.TestContactDto;
import ioscar.common.desensitization.pojo.NormalDto;
import ioscar.common.desensitization.pojo.TestBaseCaseDto;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
@EnableAspectJAutoProxy(proxyTargetClass = true)
class DesensitizationApplicationTests {

    @Resource
    private TestResult testResult;


    @Test
    void contextLoads() {
    }

    @Test
    public void testAspectList() {
        Result<List<TestCaseDto>> listResult = testResult.testEntities();
        System.out.println(JSON.toJSONString(listResult));
    }

    @Test
    public void testAspect() {
        // testResult.testEntitie();
//        testResult.testString();
        testResult.testResult();
    }

    @Test
    public void testContact() {

        testResult.getTestContact();
    }

    @Test
    public void testContactList() {

        Result<List<TestContactDto>> testContactList = testResult.getTestContactList();
        System.out.println(JSON.toJSONString(testContactList));
    }

    @Test
    public void testKeyValue() {

        //testResult.testKeyValue();
        //testResult.testKeyValueList();
        Result<TestBaseCaseDto> testBaseCaseDtoResult = testResult.testBaseCaseKeyValueList();
        System.out.println(JSON.toJSONString(testBaseCaseDtoResult));
    }

    @Test
    public void normalTest() {
        Result<NormalDto> normalDtoResult = testResult.normalTest();
        System.out.println(JSON.toJSONString(normalDtoResult));
    }

}
