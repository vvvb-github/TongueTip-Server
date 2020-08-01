# 舌尖
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
