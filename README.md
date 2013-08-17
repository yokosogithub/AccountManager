AccountManager
==============

项目名称：AccoutManager
需求分析：用户现在对应各种网站和游戏有很多账号，对应有很多密码，本软件帮你解决记密码的问题，
只用一个账号和登陆密码自动为你加密生成该账号对应的密码，不用再为密码多而愁了，也保护了你账号的安全。
项目所用技术：Java+mySql(JDBC)+SWING+DES加密
概要设计：
1.根据用户注册名在MySql中建立一张以该用户名为名称的信息表，包括账号，密码和备注，并将该用户名和登陆密码建立另一张表，实现登陆验证功能。
2.用Java SWT 和PS设计登陆界面，主窗口，增加，删除，修改，查找用户界面等GUI。。
3.用JDBC和SQL语句实现Database操作类，实现数据与数据库的交互。
4.整体融合，构成可运行的应用程序