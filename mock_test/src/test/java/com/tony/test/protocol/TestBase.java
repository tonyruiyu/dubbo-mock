package com.tony.test.protocol;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.BooleanUtils;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.tony.test.mock.po.RegistryConfig;

@RunWith(SpringJUnit4ClassRunner.class) @ContextConfiguration(locations = "classpath:spring/applicationContext.xml") public class TestBase
        extends AbstractJUnit4SpringContextTests {
    static {
        System.setProperty("mb.environment", "product");
    }

    public void waitThread(int m) {
        try {
            Thread.sleep(m);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static Date getDate(String etime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return sdf.parse(etime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    ReferenceConfig<TestAbcService> reference = new ReferenceConfig<TestAbcService>(); // 该实例很重量，里面封装了所有与注册中心及服务提供方连接，请缓存

    public ReferenceConfig<TestAbcService> getRef(Class<TestAbcService> interfaceClass, RegistryConfig tempRegistry) {
        if (BooleanUtils.isTrue(reference.isInit())) {
            return reference;
        }
        reference.setInterface(interfaceClass); // 弱类型接口名 
        com.alibaba.dubbo.config.RegistryConfig rc = new com.alibaba.dubbo.config.RegistryConfig();
        rc.setProtocol(tempRegistry.getRegistryProtocol());
        rc.setAddress(tempRegistry.getRegistryAddress());
        rc.setTimeout(tempRegistry.getRegistryTimeout());
        reference.setRegistry(rc);
        reference.setApplication(new ApplicationConfig("test"));
        return reference;
    }
}
