# LightSpeed-IDEA-Plugin
可以直接生成BO的Service接口、ServiceImpl、Controller

#   打JAR包
1.右键工程
2.底部有一个"Prepare Plugin Module...."

#   使用
1.  BO中按alt+insert，选择Generate Suit
2.  BO所在包中会生成该BO的三件套
3.  自行将Service、Controller移动到他们应该在的包下（暂时没想到其他好的方法~）

#   使用
0.  模板文件编写，模板文件名称需要规范（注意大小写）
    1.  Controller模板文件：名称结尾Controller
    2.  Service接口模板文件：名称结尾ServiceInterface
    3.  Service实现累模板文件：名称结尾ServiceImpl
    4.  Dao实现模板文件：名称结尾DaoImpl
    5.  插件会自动创建包所在的文件夹
1.  File -> Settings -> Other Settings -> LightSpeed 中进行各层包的配置
2.  BO中按alt+insert，选择Generate Suit