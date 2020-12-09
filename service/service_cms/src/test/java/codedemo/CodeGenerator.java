package codedemo;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.Test;

/** 代码生成器
 * @author bing  @create 2020/12/2-11:56 上午
 */
public class CodeGenerator {

    @Test
    public void run() {
        // 创建 generator 对象
        AutoGenerator autoGenerator = new AutoGenerator();
        // 数据源
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL);
        dataSourceConfig.setDriverName("com.mysql.cj.jdbc.Driver");
        dataSourceConfig.setUrl("jdbc:mysql://localhost:3306/guli?serverTimezone=GMT%2B8");
        dataSourceConfig.setUsername("root");
        dataSourceConfig.setPassword("Bing_yu2001");
        autoGenerator.setDataSource(dataSourceConfig);
        // 全局配置
        GlobalConfig globalConfig = new GlobalConfig();
        // globalConfig.setOutputDir("/Users/bing/Code/idea/guli/guli_parent/service/service_edu" + "/src/main/java");
        globalConfig.setOutputDir(System.getProperty("user.dir")+"/src/main/java");//指定代码生成存放位置
        globalConfig.setOpen(false); // 创建完成不用打开文件夹
        globalConfig.setAuthor("Bing"); // 作者，不写用当前计算机用户名
        globalConfig.setServiceName("%sService"); // 去掉 I
        globalConfig.setIdType(IdType.ASSIGN_ID);// id 主键策略
        globalConfig.setDateType(DateType.ONLY_DATE); // 定义生成的实体类中日期类型
        globalConfig.setSwagger2(true);// 开启Swaggers模式
        autoGenerator.setGlobalConfig(globalConfig);
        // 包信息
        PackageConfig packageConfig = new PackageConfig();
        //包  com.atguigu.eduservice
        packageConfig.setParent("com.atguigu"); // 父包
        packageConfig.setModuleName("educms"); // 父包下新建 generator 包,不配置为根目录下
        //包  com.atguigu.eduservice.controller
        packageConfig.setController("controller");
        packageConfig.setService("service");
        packageConfig.setMapper("mapper");
        packageConfig.setEntity("entity");
        autoGenerator.setPackageInfo(packageConfig);
        // 配置策略
        StrategyConfig strategyConfig = new StrategyConfig();
         strategyConfig.setInclude("crm_banner"); // 生成多张表格式
        strategyConfig.setEntityLombokModel(true); // 自动添加 Lombok 注解
        strategyConfig.setNaming(NamingStrategy.underline_to_camel); // 表字段下划线转驼峰
        strategyConfig.setColumnNaming(NamingStrategy.underline_to_camel); // 表字段下划线转驼峰
        autoGenerator.setStrategy(strategyConfig);

        autoGenerator.execute();
    }
}