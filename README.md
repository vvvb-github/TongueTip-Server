# 舌尖
## 开发日志
### 7.21
- 新建项目并搭建项目结构，编写API文档
### 7.22
- 以请求模块为单位分工进入后端开发
### 7.23
- 继续进行后端开发
### 7.24
- 后端第一次整合
### 7.25
- 基本完成全部后端开发
### 7.26
- 进行微信支付模块开发
### 7.27
- 后端第二次整合，并进行整体测试
### 7.28
- 前后端和并进行测试，并修复bug
## 项目结构
    -- src
        - main
            - java
                - cn.com.seu.tonguetip
                    - server，项目主要代码包
                        - controller
                            Controller.java，各模块控制层，接收请求
                        - entity
                            Entity.java，实体类，用于保存各类数据库数据
                        - mapper
                            Mapper.java，各模块映射层，负责控制层与服务类之间的映射
                        - service
                            Service.java，各模块服务层，提供与数据库的交互服务
            - resources
                - mapper.server，各Mapper层文件配置
                - 其它的一些项目配置文件
        - test
            - java
                - cn.com.seu.tonguetip，测试类，作为程序入口