
# DUBBO MOCK SERVER 使用说明

DUBBO MOCK SERVER 是一个基于web的dubbo服务的mock平台，可以通过可视化界面，采用groovy脚本语言动态配置mock规则，并且提供了对groovy脚本配置是否正确的自我检查测试的功能。


## 选择数据库数据库按照路径(如果有需要)

更新mock_web模块resources中`db-connection.properties`
```
mysql.url=jdbc:sqlite:sql.db
```

## 发布文件
执行mvn命令
```
mvn package -DskipTests=true
```
发布mock_web.war

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
---|---|---|---
1|pushOnlineStock|any|json
1|testMethod|arg.warehId=='HQ01W500'|json

* 条件脚本 `any` `void` `true` 匹配任意参数
* 条件脚本 `args` 为入参数组的对象，可以同步`args[0]` `args[1]`这种方式获取参数对象，并且可以通过`.`来调用符合JAVABEAN规范的属性
* 条件脚本`arg`对象是`args`数组累的第一个对象
* 返回报文`json`格式


```
{'message':'messagezry','par':[{'name':'zxc','age':11, class:'com.tony.test.protocol.Par'}]}
```


## 配置图解
### 服务清单
![服务清单](http://i4.piimg.com/597491/7fa077907536622f.png)

### 服务详情
![服务详情](https://raw.githubusercontent.com/tonyruiyu/tony-res-pic/master/pic/mock_service.png)

### 添加注册中心
![服务详情](https://raw.githubusercontent.com/tonyruiyu/tony-res-pic/master/pic/add_reg.png)

### 添加协议
![服务详情](https://raw.githubusercontent.com/tonyruiyu/tony-res-pic/master/pic/add_proto.png)

### 添加方法mock规则
![服务详情](https://raw.githubusercontent.com/tonyruiyu/tony-res-pic/master/pic/add_method_rule.png)

### 选择规则进行测试
![服务详情](https://raw.githubusercontent.com/tonyruiyu/tony-res-pic/master/pic/mock_test.png)

### 测试详情
![服务详情](https://raw.githubusercontent.com/tonyruiyu/tony-res-pic/master/pic/mock_test_info.png)

### 测试保存测试数据
![服务详情](https://raw.githubusercontent.com/tonyruiyu/tony-res-pic/master/pic/save_mock_test.png)

## 感谢
`特别感谢彬少（5491541@qq.com）对本项目的大力支持`

