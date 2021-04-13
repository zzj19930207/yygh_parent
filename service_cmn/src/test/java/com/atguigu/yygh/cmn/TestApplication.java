package com.atguigu.yygh.cmn;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
@RunWith(SpringRunner.class)
//@ActiveProfiles("dev")
public class TestApplication {
    @Test
    public void Mes(){

//      MySql
        String Url = "jdbc:mysql://localhost:3306/yygh_cmn?useUnicode=true&characterEncoding=utf-8&useSSL=true&serverTimezone=UTC";
        String username = "root";
        String password = "root";
        String tableName = "Dict";
        String driverName = "com.mysql.cj.jdbc.Driver";
        testGenarator(Url,username,password,tableName,driverName);
    }


//    @Test
    public void testGenarator(String Url,String username,String password,String tableName ,String driverName){
        //构建一个代码自动给生成器对象
        AutoGenerator mpg = new AutoGenerator();
        //配置策略
        //1、全局配置
        GlobalConfig gc = new GlobalConfig();
//        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir("E:\\idea_workspace_github\\yygh_parent\\service_cmn\\src\\main\\java");  //设置项目生成路径
        gc.setAuthor("zzj");	//设置作者姓名
        gc.setOpen(false);
        gc.setFileOverride(true);  //是否覆盖
//        gc.setServiceName("%sService");     //去除service的I前缀（如果不设置service前面将会多一个I）
//        gc.setIdType(IdType.ID_WORKER);
        gc.setDateType(DateType.ONLY_DATE);
        gc.setSwagger2(true);
        mpg.setGlobalConfig(gc);

        //2、配置数据源
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(Url);
        dsc.setDriverName(driverName);
        dsc.setUsername(username);
        dsc.setPassword(password);
        dsc.setDbType(DbType.MYSQL);
        //如果oracle数据库 有多schema 切记要在这里配置 否则无法生成代码
//        dsc.setSchemaName("CUX");
        mpg.setDataSource(dsc);

        //3、配置包
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.atguigu.yygh.cmn");	 //设置生成在哪个父包下
        pc.setEntity("entity");	//设置实体类包名
        pc.setMapper("mapper");
        pc.setService("service");
        pc.setController("controller");
        mpg.setPackageInfo(pc);

        //4、策略配置
        StrategyConfig strategy = new StrategyConfig();
        //strategy.setInclude(new String[] { "city", "comment","group","group_apply", "group_member", "like", "order", "permission", "read", "role", "role_permission"});    //设置要映射的表(可包含多个)
        strategy.setInclude(new String[] {tableName});	//也可设置不需要映射的表
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        //strategy.setLogicDeleteFieldName("deleted");   //设置逻辑删除字段
        TableFill createTime = new TableFill("create_time", FieldFill.INSERT);
        TableFill updateTime = new TableFill("update_time", FieldFill.INSERT_UPDATE);
        ArrayList<TableFill> tableFills = new ArrayList<>();
        tableFills.add(createTime);
        tableFills.add(updateTime);
        strategy.setTableFillList(tableFills);
        //乐观锁
//        strategy.setVersionFieldName("version");
        strategy.setRestControllerStyle(true);
        strategy.setControllerMappingHyphenStyle(true);
        mpg.setStrategy(strategy);

        //执行
        mpg.execute();
    }


}
