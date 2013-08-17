import java.math.BigInteger;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

public class DataBaseOperate implements Code,Account{
	/**
	 * �������ݿ�
	 * username���ڴ洢�û���Ϣ����Ϊname_�ı�usercodeΪ�û������룬
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
    		System.out.println("�Ҳ������ݿ���������");
    	}catch(SQLException e)
    	{
    		System.out.println("wrong");//�׳����ݿ������쳣
    	}
    	
	}
	
    public DataBaseOperate(String name,String code)
    {
    	this.username=name;
    	this.usercode=code;
    	try{
    		Class.forName(dbdriver);
    		System.out.println("driver success");
    		con=DriverManager.getConnection(dburl,"root","920628"); //��rootȨ�޽��룬�������ݿ�
    		System.out.println("Connection succes");
    	}catch(ClassNotFoundException e)
    	{
    		System.out.println("�Ҳ������ݿ���������");
    	}catch(SQLException e)
    	{
    		System.out.println("wrong");
    	}
    }
    
    public boolean generateAccountTable(String username)
    {
    	try{
    		Statement stmt=null;  //����Statement�����
    		stmt=con.createStatement();
    		String sql="Create table name_"+username+"("+"account varchar(50),password varchar(50),intro varchar(255)"+")"; //����
    		stmt.execute(sql);
            //ִ��SQL����
            System.out.println("��"+username+"�����ɹ�");
            stmt.close();
    	}catch(SQLException e1){
    		System.out.println("���ݿ���쳣,"+e1);
    		return false;
    	}
    	return true;
    }
    

    
    public boolean regeditAccount(String username,String usercode)  //ע��һ���˻�
    {
    	this.username=username;
    	this.usercode=usercode;
    	PreparedStatement pstmt=null; //PreparedStatementΪԤ����SQL��䣬�Ὣ�����SQL������벢�������ڴ��У�
    	try{
    		String insertCodeDataSql="insert into accountlist values(?,?)";
    		pstmt=con.prepareStatement(insertCodeDataSql,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
    		pstmt.setString(1,username);
    		pstmt.setString(2,usercode);//ʵ�ּ�¼ָ��Ķ�λ�ͶԽ���������޸�
    		pstmt.executeUpdate();
    	}catch(SQLException e1)
    	{
    		System.out.println("���ݿ���쳣,"+e1);
    		return false;
    	}finally{
    	    try{
    	    	pstmt.close();
    	    }catch(SQLException e){
    	    	System.out.println("�ڹر����ݿ�����ʱ�����˴���!"+e.getMessage());
    	    }
    	}
    	return true;
    }
    
    public boolean LoginOk()   //����½�Ƿ�ɹ�
    {
    	PreparedStatement pstmt=null;
    	ResultSet rs=null;//PreparedStatementΪԤ����SQL��䣬�Ὣ�����SQL������벢�������ڴ��У�
    	try{
    		String insertCodeDataSql="select * from accountlist where account=?";
    		pstmt=con.prepareStatement(insertCodeDataSql,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
    		pstmt.setString(1,username);//ʵ�ּ�¼ָ��Ķ�λ�ͶԽ���������޸�
    		rs=pstmt.executeQuery();
    		String code;
    		while(rs.next())
    		{
    			code=rs.getString("code");  //�ӽ�����л�ȡ�ֶ�code
    			if(code.equals(usercode))
    			{
    				return true;
    			}
    		}
    	}catch(SQLException e1)
    	{
    		System.out.println("���ݿ���쳣,"+e1);
    		return false;
    	}finally{
    	    try{
    	    	pstmt.close();
    	    }catch(SQLException e){
    	    	System.out.println("�ڹر����ݿ�����ʱ�����˴���!"+e.getMessage());
    	    }
    	}
    	return false;
    }
    public boolean SerachUserInDB()   //��ѯ��accountlist���Ƿ����û�����
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
    		System.out.println("���ݿ��ȡ�쳣"+e);
    		
    	}
		return false;
    }
    public boolean SearchUserInDB(String user)  //�����ݿ��в�ѯ��Ϊuser���û�
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
    		System.out.println("���ݿ��ȡ�쳣"+e);
    		
    	}
    	return false;
    }
    
    
    public boolean SearchCodeInDB()  //��ѯ��accountlist���Ƿ����������
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
    		System.out.println("���ݿ��ȡ�쳣"+e);
    		
    	}
		return false;
    }
    
    public boolean SearchAccountInDB(String i_account)  //�ڱ�name_****�в����˻�i_account
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
    		System.out.println("���ݿ��ȡ�쳣"+e);
    		
    	}
    	return false;
    }
    
    public boolean SearchInfoInDB(String i_info) //���Ը��û��������ı���ģ�����ұ�ע
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
    		System.out.println("���ݿ��ȡ�쳣"+e);
    		
    	}
    	return false;
    	
    }
    
    
    public String SearchCode()  //�����ݿ��еõ��û���½������
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
    		System.out.println("���ݿ��ȡ�쳣"+e);
    		
    	}
		return code;
    }
    
   
    	 
    public boolean updateCodeDatainfo(String old_code,String new_code) //�����Զ�������
    {
    	PreparedStatement pstmt=null; //PreparedStatementΪԤ����SQL��䣬�Ὣ�����SQL������벢�������ڴ��У�
    	try{
    		String insertCodeDataSql="update accountlist set code=? where code=?";
    		pstmt=con.prepareStatement(insertCodeDataSql,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
    		pstmt.setString(1,new_code);
    		pstmt.setString(2,old_code);//ʵ�ּ�¼ָ��Ķ�λ�ͶԽ���������޸�
    		pstmt.executeUpdate();
    	}catch(SQLException e1)
    	{
    		System.out.println("���ݿ���쳣,"+e1);
    		return false;
    	}finally{
    	    try{
    	    	pstmt.close();
    	    }catch(SQLException e){
    	    	System.out.println("�ڹر����ݿ�����ʱ�����˴���!"+e.getMessage());
    	    }
    	}
    	return true;
    }

	@Override
	public boolean addAccountListinfo(String i_account,String i_intro) //���һ���û����Ը��û������ı���
	{
		// TODO Auto-generated method stub
		String password;
		String password1;
		Compile com=new Compile();
		BigInteger step1;
		step1=com.add(com.transport(i_account),com.transport(SearchCode()));
		password=com.secCompile(step1);
		password1=com.thiCompile(password);
		PreparedStatement pstmt=null; //PreparedStatementΪԤ����SQL��䣬�Ὣ�����SQL������벢�������ڴ��У�
    	try{
    		String insertCodeDataSql="insert into name_"+username+" values(?,?,?)";
    		pstmt=con.prepareStatement(insertCodeDataSql,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
    		pstmt.setString(1,i_account);
    		pstmt.setString(2,password1);
    		pstmt.setString(3, i_intro);//ʵ�ּ�¼ָ��Ķ�λ�ͶԽ���������޸�
    		pstmt.executeUpdate();
    	}catch(SQLException e1)
    	{
    		System.out.println("���ݿ���쳣,"+e1);
    		return false;
    	}finally{
    	    try{
    	    	pstmt.close();
    	    }catch(SQLException e){
    	    	System.out.println("�ڹر����ݿ�����ʱ�����˴���!"+e.getMessage());
    	    }
    	}
    	return true;
	}

	@Override
	public boolean deleteAccountListinfo(String i_account) //���û���ɾ��һ���û� 
	{
		PreparedStatement pstmt=null; //PreparedStatementΪԤ����SQL��䣬�Ὣ�����SQL������벢�������ڴ��У�
    	try{
    		String insertCodeDataSql="delete from name_"+username+" where account=?";
    		pstmt=con.prepareStatement(insertCodeDataSql,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
    		pstmt.setString(1,i_account);//ʵ�ּ�¼ָ��Ķ�λ�ͶԽ���������޸�
    		pstmt.executeUpdate();
    	}catch(SQLException e1)
    	{
    		System.out.println("���ݿ���쳣,"+e1);
    		return false;
    	}finally{
    	    try{
    	    	pstmt.close();
    	    }catch(SQLException e){
    	    	System.out.println("�ڹر����ݿ�����ʱ�����˴���!"+e.getMessage());
    	    }
    	}
    	return true;// TODO Auto-generated method stub
	}
	

	@Override
	public boolean updateAccountListinfo(String old_account, String new_account,String new_intro) //�޸�һ���û�  
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
			return false;  //���˺Ų�����
		}
	}
	
	
	
 }

