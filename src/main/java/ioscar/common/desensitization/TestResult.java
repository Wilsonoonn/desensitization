package ioscar.common.desensitization;

import cn.com.icbf.framework.common.model.Result;
import ioscar.common.desensitization.annotation.DesensitizedAnnotation;
import ioscar.common.desensitization.entity.TestBaseKeyValueDto;
import ioscar.common.desensitization.entity.TestCaseDto;
import ioscar.common.desensitization.entity.TestContactDto;
import ioscar.common.desensitization.pojo.NormalDto;
import ioscar.common.desensitization.pojo.TestBaseCaseDto;
import ioscar.common.desensitization.strategys.TestStrategy;
import ioscar.common.desensitization.util.TestSensitiveProperty;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author WIlson
 * @version 1.0
 * @description
 * @createDate 2022/6/28
 */
@Component
public class TestResult {

    @DesensitizedAnnotation(strategy = TestStrategy.class, property = TestSensitiveProperty.class)
    public Result<List<TestCaseDto>> testEntities() {
        List<TestCaseDto> testEntityList = new ArrayList<>();
        testEntityList.add(TestCaseDto.builder().phone("13722225923").idCard("098765432112334518")
                .address("北京市朝阳区定福庄北街10号楼4单元601室").build());
        testEntityList.add(TestCaseDto.builder().phone("18611112344").idCard("098765432112334518")
                .address("北京市海淀区西土城路10号北京邮电大学英文地址").build());
        return Result.success(testEntityList);
    }

    @DesensitizedAnnotation(strategy = TestStrategy.class, property = TestSensitiveProperty.class)
    public Result<TestCaseDto> testEntitie() {
        TestCaseDto dto = TestCaseDto.builder().phone("13722225923").idCard("098765432112334518")
                .address("北京市西城区德胜门外大街77号(德胜国际)").build();

        return Result.success(dto);
    }

    @DesensitizedAnnotation(strategy = TestStrategy.class, property = TestSensitiveProperty.class)
    public String testString() {
        return "123";
    }

    @DesensitizedAnnotation(strategy = TestStrategy.class, property = TestSensitiveProperty.class)
    public Result<TestCaseDto> testResult() {

        List<TestCaseDto> buildList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            TestCaseDto b = TestCaseDto.builder().phone("13722225923").idCard("098765432112334518")
                    .address("北京市西城区德胜门外大街" + i + "号(德胜国际)").userName("王五").entityList(buildList).build();
            buildList.add(b);

        }

        TestCaseDto build1 = TestCaseDto.builder().phone("13722225923").idCard("098765432112334518")
                .address("北京市西城区德胜门外大街77号(德胜国际)").userName("王五").entityList(buildList).build();

        return Result.success(build1);
    }


    @DesensitizedAnnotation(strategy = TestStrategy.class, property = TestSensitiveProperty.class)
    public Result<TestContactDto> getTestContact() {

        TestContactDto dto = new TestContactDto();
        dto.setUserId(1222);
        dto.setContactTypeName("账号");
        dto.setContact("098765432112334518");
        dto.setOriContactTypeName("");

        TestContactDto dto1 = new TestContactDto();
        dto1.setUserId(0);
        dto1.setContactTypeName("账号222");
        dto1.setContact("098765432112334518");
        dto1.setOriContactTypeName("哈哈哈哈");
        dto1.setObj(dto);

        return Result.success(dto1);

    }


    @DesensitizedAnnotation(strategy = TestStrategy.class, property = TestSensitiveProperty.class)
    public Result<List<TestContactDto>> getTestContactList() {

        TestContactDto dto = new TestContactDto();
        dto.setUserId(1222);
        dto.setContact("098765432112334518");
        dto.setRelationName("亲戚");

        TestContactDto dto1 = new TestContactDto();
        dto1.setUserId(1222);
        dto1.setContactTypeName("账号");
        dto1.setContact("098765432112334518");
        dto1.setOriContactTypeName("账号22");
        dto1.setRelationName("亲戚");

        TestContactDto dto2 = new TestContactDto();
        dto2.setUserId(1222);
        dto2.setContactTypeName("姓名");
        dto2.setContact("张三");
        dto2.setOriContactTypeName("姓名2");
        dto2.setRelationName("本人");

        List list = new ArrayList();
        list.add(dto);
        list.add(dto1);
        list.add(dto2);

        return Result.success(list);

    }

    @DesensitizedAnnotation(strategy = TestStrategy.class, property = TestSensitiveProperty.class)
    public Result<TestBaseKeyValueDto> testKeyValue() {

        TestBaseKeyValueDto dto = new TestBaseKeyValueDto();

        dto.setKeyName("账号");
        dto.setKeyValue("098765432112334518");

        return Result.success(dto);

    }

    @DesensitizedAnnotation(strategy = TestStrategy.class, property = TestSensitiveProperty.class)
    public Result<List<TestBaseKeyValueDto>> testKeyValueList() {

        List<TestBaseKeyValueDto> list = new ArrayList<>();

        TestBaseKeyValueDto dto = new TestBaseKeyValueDto();
        dto.setKeyName("账号");
        dto.setKeyValue("098765432112334518");

        TestBaseKeyValueDto dto1 = new TestBaseKeyValueDto();
        dto1.setKeyName("手机号");
        dto1.setKeyValue("13722225923");

        list.add(dto);
        list.add(dto1);

        return Result.success(list);

    }


    @DesensitizedAnnotation(strategy = TestStrategy.class, property = TestSensitiveProperty.class)
    public Result<TestBaseCaseDto> testBaseCaseKeyValueList() {

        List<TestBaseKeyValueDto> list = new ArrayList<>();

        TestBaseKeyValueDto dto = new TestBaseKeyValueDto();
        dto.setKeyName("账号");
        dto.setKeyValue("098765432112334518");
        dto.setAccountId(3L);
        dto.setCustomerId(6L);

        TestBaseKeyValueDto dto1 = new TestBaseKeyValueDto();
        dto1.setKeyName("手机号");
        dto1.setKeyValue("13722225923");
        dto1.setAccountId(4L);
        dto1.setCustomerId(5L);

        list.add(dto);
        list.add(dto1);

        TestCaseDto b = new TestCaseDto();
        b.setAccountId(1L);
        b.setCustomerId(2L);
        b.setPhone("13722225923");
        b.setIdCard("1234111111334545567890");
        b.setAddress("北京市西城区德胜门外大街77号(德胜国际)");
        b.setUserName("王五");
        b.setEstBaseKeyValueDtos(list);

        return Result.success(b);

    }

    @DesensitizedAnnotation(strategy = TestStrategy.class, property = TestSensitiveProperty.class)
    public Result<NormalDto> normalTest() {

        NormalDto normalDto = new NormalDto();
        normalDto.setCustomerId(1L);
        normalDto.setAccountId(2L);
        normalDto.setSexType("男");
        normalDto.setAge(18);

        return Result.success(normalDto);
    }


}
