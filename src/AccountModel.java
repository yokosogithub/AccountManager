import java.sql.*;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.*;
public class AccountModel extends AbstractTableModel{
	protected Vector rowData,columnNames;//存放行数据，列名；
	protected DataBaseOperate dbcon;
	public AccountModel(DataBaseOperate m)
	{
		this.dbcon=m;
		this.init("");
	}
	public AccountModel(DataBaseOperate m,String sql)
	{
	   this.dbcon=m;	
	   this.init(sql);
	}
	//做一个构造函数，用于初始化我们的数据模型
	
	public void init(String sql){
		//中间
		columnNames=new Vector();//设置列名
		columnNames.add("账号");
		columnNames.add("密码");
		columnNames.add("备注");
		rowData=new Vector();//可以存放多行
		ResultSet rs=null;
		PreparedStatement pstmt=null;
		try{
			if(sql.equals("")){
				sql="select * from name_"+dbcon.username;
			}
			pstmt=dbcon.con.prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			rs=pstmt.executeQuery();
			while(rs.next()){
				Vector hang=new Vector();
				hang.add(rs.getString("account"));
				hang.add(rs.getString("password"));
				hang.add(rs.getString("intro"));
				rowData.add(hang);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(rs!=null){
					rs.close();
					rs=null;
				}
				if(pstmt!=null){
					pstmt.close();
					pstmt=null;
				}
			} catch(Exception e){
				e.printStackTrace();
			}
		}
			
	}
	public void addStu(String sql){
		//根据用户输入的sql语句，完成添加任务
	}
	
	//通过传递的sql语句来获得数据模型
	//得到共有多少列
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return this.columnNames.size();
	}

	
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return (String)this.columnNames.get(column);
	}

	//得到共有多少行
	public int getRowCount() {
		// TODO Auto-generated method stub
		return this.rowData.size();
	}

	//得到某行某列的数据
	public Object getValueAt(int row, int column) {
		// TODO Auto-generated method stub
		return ((Vector)this.rowData.get(row)).get(column);
	}
	
}


