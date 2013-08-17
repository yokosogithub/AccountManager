import java.math.BigInteger;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

public class DataBaseOperate implements Code,Account{
	/**
	 * 连接数据库
	 * username用于存储用户信息的名为name_的表，usercode为用户的密码，
	 */
	String dbdriver="com.mysql.jdbc.Driver";
	String dburl="jdbc:mysql://localhost:3306/accountmanager";
        String username;
	String usercode;
	Connection con=null;
	public DataBaseOperate()
	{
		try{
    		Class.forName(dbdriver);
    		System.out.println("driver success");
    		con=DriverManager.getConnection(dburl,"root","920628");
    		System.out.println("Connection succes");
    	}catch(ClassNotFoundException e)
    	{
    		System.out.println("找不到数据库驱动程序");
    	}catch(SQLException e)
    	{
    		System.out.println("wrong");//抛出数据库连接异常
    	}
    	
	}
	
    public DataBaseOperate(String name,String code)
    {
    	this.username=name;
    	this.usercode=code;
    	try{
    		Class.forName(dbdriver);
    		System.out.println("driver success");
    		con=DriverManager.getConnection(dburl,"root","920628"); //用root权限进入，创立数据库
    		System.out.println("Connection succes");
    	}catch(ClassNotFoundException e)
    	{
    		System.out.println("找不到数据库驱动程序");
    	}catch(SQLException e)
    	{
    		System.out.println("wrong");
    	}
    }
    
    public boolean generateAccountTable(String username)
    {
    	try{
    		Statement stmt=null;  //建立Statement类对象
    		stmt=con.createStatement();
    		String sql="Create table name_"+username+"("+"account varchar(50),password varchar(50),intro varchar(255)"+")"; //建表
    		stmt.execute(sql);
            //执行SQL命令
            System.out.println("表"+username+"创建成功");
            stmt.close();
    	}catch(SQLException e1){
    		System.out.println("数据库读异常,"+e1);
    		return false;
    	}
    	return true;
    }
    

    
    public boolean regeditAccount(String username,String usercode)  //注册一个账户
    {
    	this.username=username;
    	this.usercode=usercode;
    	PreparedStatement pstmt=null; //PreparedStatement为预编译SQL语句，会将传入的SQL命令编译并保存在内存中，
    	try{
    		String insertCodeDataSql="insert into accountlist values(?,?)";
    		pstmt=con.prepareStatement(insertCodeDataSql,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
    		pstmt.setString(1,username);
    		pstmt.setString(2,usercode);//实现记录指针的定位和对结果集进行修改
    		pstmt.executeUpdate();
    	}catch(SQLException e1)
    	{
    		System.out.println("数据库读异常,"+e1);
    		return false;
    	}finally{
    	    try{
    	    	pstmt.close();
    	    }catch(SQLException e){
    	    	System.out.println("在关闭数据库连接时出现了错误!"+e.getMessage());
    	    }
    	}
    	return true;
    }
    
    public boolean LoginOk()   //检查登陆是否成功
    {
    	PreparedStatement pstmt=null;
    	ResultSet rs=null;//PreparedStatement为预编译SQL语句，会将传入的SQL命令编译并保存在内存中，
    	try{
    		String insertCodeDataSql="select * from accountlist where account=?";
    		pstmt=con.prepareStatement(insertCodeDataSql,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
    		pstmt.setString(1,username);//实现记录指针的定位和对结果集进行修改
    		rs=pstmt.executeQuery();
    		String code;
    		while(rs.next())
    		{
    			code=rs.getString("code");  //从结果集中获取字段code
    			if(code.equals(usercode))
    			{
    				return true;
    			}
    		}
    	}catch(SQLException e1)
    	{
    		System.out.println("数据库读异常,"+e1);
    		return false;
    	}finally{
    	    try{
    	    	pstmt.close();
    	    }catch(SQLException e){
    	    	System.out.println("在关闭数据库连接时出现了错误!"+e.getMessage());
    	    }
    	}
    	return false;
    }
    public boolean SerachUserInDB()   //查询表accountlist中是否有用户存在
    {
    	Statement stmt=null;
    	ResultSet rs=null;
    	try{
    		stmt=con.createStatement();
    		String sql="SELECT * from accountlist";
    		rs=stmt.executeQuery(sql);
    	    if(rs.next())
    	    {
    	    	return true;
    	    }
    	rs.close();
    	stmt.close();
    	}catch(SQLException e)
    	{
    		System.out.println("数据库读取异常"+e);
    		
    	}
		return false;
    }
    public boolean SearchUserInDB(String user)  //在数据库中查询名为user的用户
    {
    	Statement stmt=null;
    	ResultSet rs=null;
    	try{
    		stmt=con.createStatement();
    		String sql="SELECT account from accountlist";
    		rs=stmt.executeQuery(sql);
    		String getUser;
    	    while(rs.next())
    	    {
    	    	getUser=rs.getString("account");
    	    	if(getUser.equals(user))
    	    	{
    	    		return true;
    	    	}
    	    }
    	rs.close();
    	stmt.close();
    	}catch(SQLException e)
    	{
    		System.out.println("数据库读取异常"+e);
    		
    	}
    	return false;
    }
    
    
    public boolean SearchCodeInDB()  //查询表accountlist中是否有密码存在
    {
    	PreparedStatement pstmt=null;
    	ResultSet rs=null;
    	try{
    		
    		String sql="SELECT code from accountlist";
    		pstmt=con.prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
    		rs=pstmt.executeQuery();
    	    if(rs.next())
    	    {
    	    	return true;
    	    }
    	rs.close();
    	pstmt.close();
    	}catch(SQLException e)
    	{
    		System.out.println("数据库读取异常"+e);
    		
    	}
		return false;
    }
    
    public boolean SearchAccountInDB(String i_account)  //在表name_****中查找账户i_account
    {
    	PreparedStatement pstmt=null;
    	ResultSet rs=null;
    	try{
    		String sql="SELECT account from name_"+username;
    		pstmt=con.prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
    		rs=pstmt.executeQuery();
    		String getAccount;
    	    while(rs.next())
    	    {
    	    	getAccount=rs.getString("account");
    	    	if(getAccount.equals(i_account))
    	    	{
    	    		return true;
    	    	}
    	    }
    	rs.close();
    	pstmt.close();
    	}catch(SQLException e)
    	{
    		System.out.println("数据库读取异常"+e);
    		
    	}
    	return false;
    }
    
    public boolean SearchInfoInDB(String i_info) //在以该用户名命名的表中模糊查找备注
    {
    	PreparedStatement pstmt=null;
    	ResultSet rs=null;
    	try{
    		String sql="select intro from name_"+username+" WHERE intro LIKE '"+"%"+i_info+"%'";
    		pstmt=con.prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
    		rs=pstmt.executeQuery();
    	    if(rs.next())
    	    {
    	       return true;
    	    }
    	rs.close();
    	pstmt.close();
    	}catch(SQLException e)
    	{
    		System.out.println("数据库读取异常"+e);
    		
    	}
    	return false;
    	
    }
    
    
    public String SearchCode()  //从数据库中得到用户登陆的密码
    {
    	Statement stmt=null;
    	ResultSet rs=null;
    	String code = null;
    	try{
    		String sql="SELECT code from accountlist";
    		stmt=con.createStatement();
    		rs=stmt.executeQuery(sql);
    	    while(rs.next())
    	    {
    	    	code=rs.getString("code");
    	    }
    	System.out.println(code);
    	rs.close();
    	stmt.close();
    	}catch(SQLException e)
    	{
    		System.out.println("数据库读取异常"+e);
    		
    	}
		return code;
    }
    
   
    	 
    public boolean updateCodeDatainfo(String old_code,String new_code) //更新自定义密码
    {
    	PreparedStatement pstmt=null; //PreparedStatement为预编译SQL语句，会将传入的SQL命令编译并保存在内存中，
    	try{
    		String insertCodeDataSql="update accountlist set code=? where code=?";
    		pstmt=con.prepareStatement(insertCodeDataSql,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
    		pstmt.setString(1,new_code);
    		pstmt.setString(2,old_code);//实现记录指针的定位和对结果集进行修改
    		pstmt.executeUpdate();
    	}catch(SQLException e1)
    	{
    		System.out.println("数据库读异常,"+e1);
    		return false;
    	}finally{
    	    try{
    	    	pstmt.close();
    	    }catch(SQLException e){
    	    	System.out.println("在关闭数据库连接时出现了错误!"+e.getMessage());
    	    }
    	}
    	return true;
    }

	@Override
	public boolean addAccountListinfo(String i_account,String i_intro) //添加一个用户到以该用户命名的表中
	{
		// TODO Auto-generated method stub
		String password;
		String password1;
		Compile com=new Compile();
		BigInteger step1;
		step1=com.add(com.transport(i_account),com.transport(SearchCode()));
		password=com.secCompile(step1);
		password1=com.thiCompile(password);
		PreparedStatement pstmt=null; //PreparedStatement为预编译SQL语句，会将传入的SQL命令编译并保存在内存中，
    	try{
    		String insertCodeDataSql="insert into name_"+username+" values(?,?,?)";
    		pstmt=con.prepareStatement(insertCodeDataSql,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
    		pstmt.setString(1,i_account);
    		pstmt.setString(2,password1);
    		pstmt.setString(3, i_intro);//实现记录指针的定位和对结果集进行修改
    		pstmt.executeUpdate();
    	}catch(SQLException e1)
    	{
    		System.out.println("数据库读异常,"+e1);
    		return false;
    	}finally{
    	    try{
    	    	pstmt.close();
    	    }catch(SQLException e){
    	    	System.out.println("在关闭数据库连接时出现了错误!"+e.getMessage());
    	    }
    	}
    	return true;
	}

	@Override
	public boolean deleteAccountListinfo(String i_account) //以用户名删除一个用户 
	{
		PreparedStatement pstmt=null; //PreparedStatement为预编译SQL语句，会将传入的SQL命令编译并保存在内存中，
    	try{
    		String insertCodeDataSql="delete from name_"+username+" where account=?";
    		pstmt=con.prepareStatement(insertCodeDataSql,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
    		pstmt.setString(1,i_account);//实现记录指针的定位和对结果集进行修改
    		pstmt.executeUpdate();
    	}catch(SQLException e1)
    	{
    		System.out.println("数据库读异常,"+e1);
    		return false;
    	}finally{
    	    try{
    	    	pstmt.close();
    	    }catch(SQLException e){
    	    	System.out.println("在关闭数据库连接时出现了错误!"+e.getMessage());
    	    }
    	}
    	return true;// TODO Auto-generated method stub
	}
	

	@Override
	public boolean updateAccountListinfo(String old_account, String new_account,String new_intro) //修改一个用户  
	{
		// TODO Auto-generated method stub
		if(SearchAccountInDB(old_account))
		{
			deleteAccountListinfo(old_account);
			addAccountListinfo(new_account,new_intro);
			return true;
		}
		else
		{
			return false;  //旧账号不存在
		}
	}
	
	
	
 }

