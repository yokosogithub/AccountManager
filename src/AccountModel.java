import java.sql.*;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.*;
public class AccountModel extends AbstractTableModel{
	protected Vector rowData,columnNames;//��������ݣ�������
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
	//��һ�����캯�������ڳ�ʼ�����ǵ�����ģ��
	
	public void init(String sql){
		//�м�
		columnNames=new Vector();//��������
		columnNames.add("�˺�");
		columnNames.add("����");
		columnNames.add("��ע");
		rowData=new Vector();//���Դ�Ŷ���
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
		//�����û������sql��䣬����������
	}
	
	//ͨ�����ݵ�sql������������ģ��
	//�õ����ж�����
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return this.columnNames.size();
	}

	
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return (String)this.columnNames.get(column);
	}

	//�õ����ж�����
	public int getRowCount() {
		// TODO Auto-generated method stub
		return this.rowData.size();
	}

	//�õ�ĳ��ĳ�е�����
	public Object getValueAt(int row, int column) {
		// TODO Auto-generated method stub
		return ((Vector)this.rowData.get(row)).get(column);
	}
	
}


