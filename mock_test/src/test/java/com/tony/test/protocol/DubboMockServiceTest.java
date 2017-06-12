package com.tony.test.protocol;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import com.alibaba.dubbo.config.ReferenceConfig;
import com.google.common.collect.Lists;
import com.tony.test.mock.auto.mapper.MockServiceMapper;
import com.tony.test.mock.auto.mapper.ProtocolConfigMapper;
import com.tony.test.mock.auto.mapper.RegistryConfigMapper;
import com.tony.test.mock.auto.mapper.ServiceMethedRuleMapper;
import com.tony.test.mock.po.MockService;
import com.tony.test.mock.po.MockServiceExample;
import com.tony.test.mock.po.ProtocolConfig;
import com.tony.test.mock.po.RegistryConfig;
import com.tony.test.mock.po.ServiceMethedRule;
import com.tony.test.mock.po.ServiceMethedRuleExample;
import com.tony.test.protocol.MockServer;

public class DubboMockServiceTest extends TestBase {

    @Resource MockServer    dubboMockServer;

    ProtocolConfigMapper    protocolConfigMapper;

    RegistryConfigMapper    registryConfigMapper;

    ServiceMethedRuleMapper serviceMethedRuleMapper;

    MockServiceMapper       mockServiceMapper;

    ProtocolConfig          protocolConfig = new ProtocolConfig();

    RegistryConfig          registryConfig = new RegistryConfig();

    int                     serviceId      = 1;

    Class<TestAbcService>   interfaceClass = TestAbcService.class;

    List<ServiceMethedRule> smritmes       = Lists.newArrayList();

    @Before
    public void init() {

        mockProtocolConfig();

        mockRegistryConfig();

        mockServiceMethodRule();

        MockService mockService = mockService();

        //mock select all
        when(mockServiceMapper.selectByExample(any(MockServiceExample.class))).thenReturn(Lists.newArrayList(mockService));

        checkAndStartServer();
    }

    @After
    public void destroy() {
        dubboMockServer.stop();
    }

    @Test
    public void testReturnListService() throws InterruptedException {
        checkAndStartServer();
        TestAbcService tas = getService();
        Par par = new Par();
        par.setAge(190);
        par.setName("xxx");
        List<Par> pars = tas.getList(par);
        assertEquals(pars.size(), 1);
        assertEquals(pars.get(0).getName(), "zxc");
        assertEquals(pars.get(0).getAge(), (Integer)11);
    }

    @Test
    public void testDefaultReturnListService() throws InterruptedException {
        checkAndStartServer();
        TestAbcService tas = getService();
        Par par = new Par();
        par.setAge(190);
        par.setName("xxx3111");
        List<Par> pars = tas.getList(par);
        assertNull(pars);
    }

    @Test
    public void testObjectResult() {
        checkAndStartServer();
        TestAbcService tas = getService();
        TestReturnObject obj = tas.getObject();
        assertEquals(obj.getMessage(), "messagezry");
        assertEquals(obj.getPar().getName(), "zxc");
    }

    @Test
    public void testObjectListResult() {
        checkAndStartServer();
        TestAbcService tas = getService();
        TestReturnObjectList obj = tas.getObjectList();
        assertEquals(obj.getMessage(), "messagezry");
        assertEquals(obj.getPar().size(), 1);
        assertEquals(obj.getPar().get(0).getName(), "zxc");
    }

    @Test
    public void testStringResult() {
        checkAndStartServer();
        TestAbcService tas = getService();
        String obj = tas.testReturnString("abc");
        assertEquals(obj, "str");
        obj = tas.testReturnString("abc1");
        assertNull(obj);
    }

    @Test
    public void testIntResult() {
        checkAndStartServer();
        TestAbcService tas = getService();
        int s = tas.testReturnInt();
        assertEquals(s, 1);
    }

    @Test
    public void testIntegerResult() {
        checkAndStartServer();
        TestAbcService tas = getService();
        Integer s = tas.testReturnInteger(1);
        assertEquals(s, (Integer) 3);
        s = tas.testReturnInteger(3);
        assertNull(s);
    }

    @Test
    public void testReturnMap() {
        checkAndStartServer();
        TestAbcService tas = getService();
        Map<String, Par> res = tas.testReturnMap();
        assertEquals(res.get("1").getAge(), (Integer)11);
    }

    @Test
    public void testVoid() {
        checkAndStartServer();
        TestAbcService tas = getService();
        tas.testVoid();
    }

    @Test(expected = IllegalStateException.class)
    public void stopServer() {
        checkAndStartServer();
        assertTrue(dubboMockServer.isexport(serviceId));
        dubboMockServer.stopService(serviceId);
        assertFalse(dubboMockServer.isexport(serviceId));
        TestAbcService tas = getService();
        tas.testVoid();
    }

    @Test
    public void reloadServer() {
        checkAndStartServer();
        assertTrue(dubboMockServer.isexport(serviceId));

        ServiceMethedRule smr = new ServiceMethedRule();
        smr.setId(8);
        smr.setServiceId(serviceId);
        smr.setMethodName("getList");
        smr.setWhenScript("arg.name=='xxx1'");
        smr.setReturnMessage("[{'name':'zxc1','age':12,'class':'com.tony.test.protocol.Par'}]");
        smritmes.add(smr);

        dubboMockServer.reloadService(serviceId);
        assertTrue(dubboMockServer.isexport(serviceId));

        TestAbcService tas = getService();
        Par par = new Par();
        par.setAge(190);
        par.setName("xxx1");
        List<Par> pars = tas.getList(par);
        assertEquals(pars.size(), 1);
        assertEquals(pars.get(0).getName(), "zxc1");
        assertEquals(pars.get(0).getAge(), (Integer)12);
    }

    @Test
    public void testStartStop() {
        checkAndStartServer();
        dubboMockServer.stop();
        assertFalse(dubboMockServer.isexport(serviceId));
        dubboMockServer.start();
        assertTrue(dubboMockServer.isexport(serviceId));
    }
    @Test
    public void testRandomObjectList() {
        checkAndStartServer();
        TestAbcService tas = getService();
        TestReturnObjectList x = tas.getRandomObjectList();
        System.out.println( x);
    }
    

    private MockService mockService() {
        mockServiceMapper = mock(MockServiceMapper.class);
        MockService mockService = new MockService();
        mockService.setApplicationName("xx");
        mockService.setTimeout(5000000);
        mockService.setRetries(0);
        mockService.setId(serviceId);
        mockService.setProtocolId(serviceId);
        mockService.setRegistryId(serviceId);
        mockService.setServiceInterface("com.tony.test.protocol.TestAbcService");
        mockService.setServiceStatus("start");
        when(mockServiceMapper.selectByPrimaryKey(serviceId)).thenReturn(mockService);
        ReflectionTestUtils.setField(dubboMockServer, "mockServiceMapper", mockServiceMapper);
        return mockService;
    }

    private void mockServiceMethodRule() {
        serviceMethedRuleMapper = mock(ServiceMethedRuleMapper.class);
        ServiceMethedRule smr = new ServiceMethedRule();
        smr.setId(8);
        smr.setServiceId(serviceId);
        smr.setMethodName("getList");
        smr.setWhenScript("arg.name=='xxx'");
        smr.setReturnMessage("[{'name':'zxc','age':11,class:'com.tony.test.protocol.Par'}]");
        smritmes.add(smr);

        smr = new ServiceMethedRule();
        smr.setId(9);
        smr.setServiceId(serviceId);
        smr.setMethodName("getObject");
        smr.setWhenScript("true");
        smr.setReturnMessage("{'message':'messagezry','par':{'name':'zxc','age':11}}");
        smritmes.add(smr);

        smr = new ServiceMethedRule();
        smr.setId(10);
        smr.setServiceId(serviceId);
        smr.setMethodName("getObjectList");
        smr.setWhenScript("any");//true or void
        smr.setReturnMessage("{'message':'messagezry','par':[{'name':'zxc','age':11, class:'com.tony.test.protocol.Par'}]}");
        smritmes.add(smr);
        
        
        smr = new ServiceMethedRule();
        smr.setId(10);
        smr.setServiceId(serviceId);
        smr.setMethodName("getRandomObjectList");
        smr.setWhenScript("any");//true or void
        smr.setReturnMessage("{'message':'messagezry','par':[{'name':'zxc','age':${return _random}12, class:'com.tony.test.protocol.Par'}]}");
        smritmes.add(smr);


        smr = new ServiceMethedRule();
        smr.setId(12);
        smr.setServiceId(serviceId);
        smr.setMethodName("testReturnInt");
        smr.setWhenScript("void");//true or void
        smr.setReturnMessage("1");
        smritmes.add(smr);

        smr = new ServiceMethedRule();
        smr.setId(13);
        smr.setServiceId(serviceId);
        smr.setMethodName("testReturnInteger");
        smr.setWhenScript("arg==1");//true or void
        smr.setReturnMessage("3");
        smritmes.add(smr);

        smr = new ServiceMethedRule();
        smr.setId(14);
        smr.setServiceId(serviceId);
        smr.setMethodName("testReturnString");
        smr.setWhenScript("arg=='abc'");//true or void
        smr.setReturnMessage("'str'");
        smritmes.add(smr);

        smr = new ServiceMethedRule();
        smr.setId(14);
        smr.setServiceId(serviceId);
        smr.setMethodName("testReturnMap");
        smr.setWhenScript("any");//true or void
        smr.setReturnMessage("{'1':{'name':'zxc','age':11}}");
        smritmes.add(smr);

        when(serviceMethedRuleMapper.selectByExampleWithBLOBs(any(ServiceMethedRuleExample.class))).thenReturn(smritmes);
        ReflectionTestUtils.setField(dubboMockServer, "serviceMethedRuleMapper", serviceMethedRuleMapper);
    }

    private void mockRegistryConfig() {
        registryConfigMapper = mock(RegistryConfigMapper.class);
        registryConfig.setId(serviceId);
        registryConfig.setRegistryAddress("10.100.22.8:2181");
        registryConfig.setRegistryProtocol("zookeeper");
        registryConfig.setRegistryTimeout(50000);
        when(registryConfigMapper.selectByPrimaryKey(serviceId)).thenReturn(registryConfig);
        ReflectionTestUtils.setField(dubboMockServer, "registryConfigMapper", registryConfigMapper);
    }

    private void mockProtocolConfig() {
        protocolConfig.setId(serviceId);
        protocolConfig.setProtocolName("dubbo");
        protocolConfig.setProtocolPort(8878);
        protocolConfigMapper = mock(ProtocolConfigMapper.class);
        when(protocolConfigMapper.selectByPrimaryKey(serviceId)).thenReturn(protocolConfig);
        ReflectionTestUtils.setField(dubboMockServer, "protocolConfigMapper", protocolConfigMapper);
    }

    private TestAbcService getService() {
        RegistryConfig tempRegistry = registryConfigMapper.selectByPrimaryKey(serviceId);
        ReferenceConfig<TestAbcService> reference = getRef(interfaceClass, tempRegistry);
        TestAbcService tas = reference.get();
        return tas;
    }

    private void checkAndStartServer() {
        if (!dubboMockServer.isexport(serviceId)) {
            dubboMockServer.startService(1);
        }
    }
}
