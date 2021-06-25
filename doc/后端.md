# Getting Started

java 1.8

springboot 2.4.5最新版

mysql8以上

因为springboot是2.4.x，所以Elasticsearch要7.9.3，Spring Data Elasticsearch要4.1.x

4.21:整合Swagger-UI

~~4.21:开始准备使用SpringSecurity和JWT实现登录注册~~

5.2: 加入druid连接池

5.2: 更换了图标


运行方法：

sql文件在文件夹里，改配置文件的数据库密码

maven方面clean然后compile

http://localhost:8090/swagger-ui.html  或者  http://localhost:8090/doc.html 为调试界面


5.3: 下一步我要集成ES搜索，这是非常难但是很值得学的

5.7: 搜索功能完成大部分了，已经加进去了，测试能跑，并且我使用了插件能同步mysql到ES中，等商品功能上线我就跟

5.11：搜索功能集成的差不多了，de了几天bug，基本功能上线

5.11：开始debug，message和order全是bug，交了就不管了？

5.13： de了所有的bug，现在能跑了

5·19：（Xiayu Version->FYL) 修改了mysql中nxx_carousel表的相关部分，修改了 carouselController.

注意：如果你不想安装ES，直接把IdleServiceImpl里的findIdleItem这个方法替换为原来备份的代码，就能跑了

5.21：后端可以结束了
