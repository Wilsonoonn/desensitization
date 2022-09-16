脱敏规则使用说明
一、判断自己是否需要脱敏
如果查询的数据中包含以下字段，那就需要脱敏

案件信息包含：卡号、证件号、债务人姓名、账号、出生日期、外包序号
联系资料-资料类型包含： 手机号、固话、地址、邮箱、姓名、微信、出生日期、单位名称
催记信息-资料类型包含：手机号、固话、地址、邮箱、姓名、微信、出生日期、单位名称

如果有拿不准的，可以找我@王硕 确认。

二、使用限制

1、项目引用合规Client
<dependencies>
    <dependency>
        <groupId>ioscar.compliancesystem</groupId>
        <artifactId>compliance-client</artifactId>
        <version>1.0-SNAPSHOT</version>
    </dependency>
</dependencies>

2、每个项目引入合规依赖后默认对返回值进行切面脱敏。   

3、使用示例目前分为4种，其中3种为特殊处理， 根据你的返回值使用以下某一种，关注对应的即可

4、如果为案件详情的接口，请转到 案件详情特殊应用

规则项
规则说明
返回值示例
规则链接
普通规则
返回值为案件信息

规则1
特殊规则1
返回值为联系资料信息

规则2
特殊规则2
返回值为催记信息

规则3
特殊规则3
返回值为键值对
Dto { name:"卡号",   value:"12345678" }
或者
Dto{
 accountId: 
customerId:
List<KVDto>{
   name:
  value:
  },
  {
  name:
  value:
  }
}
规则4



三、具体使用示例


规则1-返回值为案件信息
保持你的返回对象继承DesensitizedBaseDto ，并且如果你的结果类中有和父类相同的字段，需要更改为父类字段


/**
 * 脱敏基础类型
 *
 * @author WIlson
 * @version 1.0
 * @description
 * @createDate 2022/7/15
 */
@ApiModel("脱敏基础类型")
public class DesensitizedBaseDto extends Basic implements IDesensitized {


    /**
     * 卡号
     */
    @ApiModelProperty("卡号")
    @DesensitizedFieldAnnotation(value = "卡号", sensitive = EntrustIdNumberDesensitized.class)
    private String entrustIdNumber;
    /**
     * 外包序号
     */
    @ApiModelProperty("外包序号")
    @DesensitizedFieldAnnotation(value = "外包序号", sensitive = EntrustNumberDesensitized.class)
    private String entrustNumber;

    /**
     * 证件号
     */
    @ApiModelProperty("证件号")
    @DesensitizedFieldAnnotation(value = "证件号", sensitive = IdNumberDesensitized.class)
    private String idNumber;
    /**
     * 账号
     */
    @ApiModelProperty("账号")
    @DesensitizedFieldAnnotation(value = "账号", sensitive = AccountNumberDesensitized.class)
    private String accountNumber;
    /**
     * 债务人姓名
     */
    @ApiModelProperty("债务人姓名")
    @DesensitizedFieldAnnotation(value = "姓名", sensitive = NameDesensitized.class)
    private String debtorName;


}




规则2-返回值为资料信息
保持你的返回对象继承BaseContactDto ，并且如果你的结果类中有和父类相同的字段，需要更改为父类字段
@ApiModel("脱敏基础资料类")
public class BaseContactDto extends DesensitizedBaseDto {

   /**
 * 姓名
 */
@ApiModelProperty("姓名")
@DesensitizedFieldAnnotation(value = "姓名", sensitive = ContactDesensitized.class)
private Object contactName;

/**
 * 资料内容
 */
@ApiModelProperty("资料内容")
@DesensitizedFieldAnnotation(value = "资料内容", sensitive = ContactDesensitized.class)
private Object contact;
/**
 * 资料类型(实际资料类型)
 */
@ApiModelProperty("资料类型(实际资料类型)")
private String contactTypeName;
/**
 * 原始资料类型
 */
@ApiModelProperty("原始资料类型")
private String oriContactTypeName;
/**
 * 关系
 */
@ApiModelProperty("关系")
private String relationName;
}


规则3-返回值为催记信息
保持你的返回对象继承BaseTelRecordDto ，并且如果你的结果类中有和父类相同的字段，需要更改为父类字段
@ApiModel("催记脱敏基础类型")
public class BaseTelRecordDto extends DesensitizedBaseDto {

/**
 * 姓名
 */
@ApiModelProperty("姓名")
@DesensitizedFieldAnnotation(value = "姓名", sensitive = TelRecordDesensitized.class)
private Object contactName;

    /**
     * 资料内容
     */
    @ApiModelProperty("资料内容")
    @DesensitizedFieldAnnotation(value = "资料内容", sensitive = TelRecordDesensitized.class)
    private Object contactContent;
    /**
     * 资料类型
     */
    @ApiModelProperty("资料类型")
    private String contactType;

    /**
     * 关系
     */
    @ApiModelProperty("关系")
    private String relationName;


    public String getContactContent() {
        return contactContent;
    }
    
    }


规则4-返回值为键值对
保持你的返回对象继承BaseKeyValueDto，并且将你的键值改为父类中的键值名称并赋值
@ApiModel("脱敏基础键值类型")
public class BaseKeyValueDto extends DesensitizedBaseDto {

    @ApiModelProperty("键")
    private String keyName;


    @ApiModelProperty("值")
    @DesensitizedFieldAnnotation(sensitive = KeyValueDesensitized.class)
    private Object keyValue;


}



四、案件详情特殊应用
案件详情可以对脱敏字段申请明文查看并且在脱敏过程中需要修改字段类型，在返回时需要区分案件详情与其他功能

使用示例
在controller层，返回方法添加注解 @ClearTextAnnotation
使用示例：
/**
 * 案件详情页根据案件id获取联系电话
 * hao
 *
 * @author: Mr Jia
 * @time: 2022/6/1 16:52
 */
@ApiOperation("案件详情页根据案件id获取联系电话")
@PostMapping("/getContactTelephoneByCaseId")
@ClearTextAnnotation
public Result<ContactFilterDto> getContactTelephoneByCaseId(@Validated @RequestBody ContactFilterParamBo paramBo) {
    return caseContactService.getContactTelephoneByCaseId(paramBo);
}


案件详情-案件信息
保持你的返回对象继承DynamicDesensitizedBaseDto，并且如果你的结果类中有和父类相同的字段，需要更改为父类字段
注：案件详情返回的字段脱敏过程中需要修改字段类型，所以字段默认都为Object类型
@ApiModel("脱敏基础类型-案件详情")
public class DynamicDesensitizedBaseDto extends Basic implements IDesensitized {

    /**
     * 卡号
     */
    @ApiModelProperty("卡号")
    @DesensitizedFieldAnnotation(value = "卡号", sensitive = EntrustIdNumberDesensitized.class)
    private Object entrustIdNumber;
    /**
     * 外包序号
     */
    @ApiModelProperty("外包序号")
    @DesensitizedFieldAnnotation(value = "外包序号", sensitive = EntrustNumberDesensitized.class)
    private Object entrustNumber;

    /**
     * 证件号
     */
    @ApiModelProperty("证件号")
    @DesensitizedFieldAnnotation(value = "证件号", sensitive = IdNumberDesensitized.class)
    private Object idNumber;
    /**
     * 账号
     */
    @ApiModelProperty("账号")
    @DesensitizedFieldAnnotation(value = "账号", sensitive = AccountNumberDesensitized.class)
    private Object accountNumber;
    /**
     * 债务人姓名
     */
    @ApiModelProperty("债务人姓名")
    @DesensitizedFieldAnnotation(value = "姓名", sensitive = NameDesensitized.class)
    private Object debtorName;
    
    }


案件详情-联系资料：
保持你的返回对象继承BaseContactDto ，并且如果你的结果类中有和父类相同的字段，需要更改为父类字段
@ApiModel("脱敏基础资料类")
public class BaseContactDto extends DesensitizedBaseDto {

 /**
 * 姓名
 */
@ApiModelProperty("姓名")
@DesensitizedFieldAnnotation(value = "姓名", sensitive = ContactDesensitized.class)
private Object contactName;

/**
 * 资料内容
 */
@ApiModelProperty("资料内容")
@DesensitizedFieldAnnotation(value = "资料内容", sensitive = ContactDesensitized.class)
private Object contact;
/**
 * 资料类型(实际资料类型)
 */
@ApiModelProperty("资料类型(实际资料类型)")
private String contactTypeName;
/**
 * 原始资料类型
 */
@ApiModelProperty("原始资料类型")
private String oriContactTypeName;
/**
 * 关系
 */
@ApiModelProperty("关系")
private String relationName;
}


案件详情-催记：
保持你的返回对象继承BaseTelRecordDto ，并且如果你的结果类中有和父类相同的字段，需要更改为父类字段
@ApiModel("催记脱敏基础类型")
public class BaseTelRecordDto extends DesensitizedBaseDto {

    /**
     * 资料内容
     */
    @ApiModelProperty("资料内容")
    @DesensitizedFieldAnnotation(value = "资料内容", sensitive = TelRecordDesensitized.class)
    private Object contactContent;
    /**
     * 资料类型
     */
    @ApiModelProperty("资料类型")
    private String contactType;

    /**
     * 关系
     */
    @ApiModelProperty("关系")
    private String relationName;


    public String getContactContent() {
        return contactContent;
    }
    
    }

五、数据导出特殊应用
数据导出不会经过定义的Response拦截器，需要在数据导出查询方法上添加方法注解
@DesensitizedAnnotation(strategy = ComplianceStrategy.class,using=UsingRuleType.OVERALL,property = SensitiveProperty.class)

导出数据-使用示例
@Service
@Slf4j
public class DorisCaseResultServiceImpl extends ServiceImpl<DorisCaseResultMapper, DorisCaseResult> implements DorisCaseResultService {
   
@Resource
private DorisCaseResultService dorisCaseResultService;
   
@Override
@DesensitizedAnnotation(strategy = ComplianceStrategy.class,using=UsingRuleType.OVERALL,property = SensitiveProperty.class)
public DorisCaseListResultDto exportCaseAccountPageApi(QueryTelCaseApiBo bo) {

    return dorisCaseResultService.getCaseAccountPageApi(bo);
  }
     
}


注：导出的查询方法与返回给前端的查询不能为同一个，否则会重复脱敏。

六、拓展应用
1、以上字段为合规要求脱敏的必须字段，如果你有自己的字段需要做特殊处理，可以在相应方法添加方法注解，在字段添加字段注解

方法注解
@DesensitizedAnnotation(strategy = ComplianceStrategy.class,using=UsingRuleType.OVERALL,property = SensitiveProperty.class)
- strategy（策略规则）：字段脱敏具体实施的规则，ComplianceStrategy.class是合规的策略规则，实现IStrategy接口可以自定义应用策略
- using（规则应用频次）:  分为三种，OVERALL（整体应用，默认），ROW（行应用），FIELD（字段应用）
- property（属性）：存储的是当前对象信息，可以在具体处理字段时应用，实现IBaseSensitiveProperty接口，可以自定义属性的实现方式与使用方式。


字段注解
@DesensitizedFieldAnnotation(value = "资料内容",sensitive = ContactDesensitized.class)
- sensitive（脱敏实现类）：可以针对不同的字段进行这个类的实现，实现ISensitive接口即可


使用限制
1、为避免不必要的返回值进行无效脱敏，需要给返回值做标记，目前的使用方式需要在返回值上实现空接口IDesensitized

2、如果在方法上加注解无效，需要查看添加注解的方法是不是内部调用，基于Spring AOP代理的机制，方法内部调用会导致注解无效（和事务传递的性质一样）
可以看文章了解：Aspect Oriented Programming with Spring
