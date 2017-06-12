
# DUBBO MOCK SERVER 使用说明


## 配置注册中心
协议|地址|超时时间(毫秒)
----|----|---
zookeeper|127.0.0.1:2181,127.0.0.1:2182,127.0.0.1:2183|500000


## 配置协议
协议|端口
---|---
dubbo|8835


## 配置DUBBO服务

### 配置服务基础信息

配置项|值
---|---
服务名称|com.tony.stock.facade.StockCoreAPiService
注册中心id|1
协议id|1
应用名称|库存系统
Group|online
Version|1.0
服务状态|running
超时时间|5000
重试次数|0

### 服务Mock规则

#### 服务方法规则配置
服务ID|方法名称|条件脚本|返回报文
---|---|---|---|---
1|pushOnlineStock|any|json
1|testMethod|arg.warehId=='HQ01W500'|json

* 条件脚本 `any` `void` `true` 匹配任意参数
* 条件脚本 `args` 为入参数组的对象，可以同步`args[0]` `args[1]`这种方式获取参数对象，并且可以通过`.`来调用符合JAVABEAN规范的属性
* 条件脚本`arg`对象是`args`数组累的第一个对象
* 返回报文`json`格式


```
{'message':'messagezry','par':[{'name':'zxc','age':11, class:'com.tony.test.protocol.Par'}]}
```
