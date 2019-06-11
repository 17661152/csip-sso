# xunmei-wms-api

统一认证平台API

技术架构：
-----------------------------------

#### 后端
- 基础框架：Spring Boot 2.1.4.RELEASE

- 持久层框架：Mybatis-plus_3.1.0  [https://mp.baomidou.com](https://mp.baomidou.com)

- 安全框架：kisso [https://gitee.com/baomidou/kisso](https://gitee.com/baomidou/kisso)

- 数据库连接池： HikariCP

- 缓存框架：redis

- 日志打印：logback

- 其他：hutool，easypoi，Swagger-ui，redisson 等。


#### 开发环境

- 语言：Java 8

- IDE(JAVA)： Eclipse安装lombok插件 或者 IDEA

- 依赖管理：Maven

- 数据库：MySQL5.7 

- 缓存：Redis

- MybatisX 快速开发插件：打开 IDEA，进入 File -> Settings -> Plugins -> Browse Repositories，输入 mybatisx 搜索并安装。[https://mp.baomidou.com/guide/mybatisx-idea-plugin.html](https://mp.baomidou.com/guide/mybatisx-idea-plugin.html)

- 代码生成器：mybatis-generator [http://112.94.2.30:13000/zengqiming/mybatis-generator](http://112.94.2.30:13000/zengqiming/mybatis-generator)

#### 初始账号/密码

- 默认登录账号： admin/123456abc
