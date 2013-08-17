import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class DeleAccountDialog extends JDialog{
	protected JTextField IDText1;
	protected JTextField IDText2;
    protected String code;
    protected JButton okButton;
    protected FunctionFrame frame;
    protected Container container;
    protected DataBaseOperate con;
    public DeleAccountDialog(FunctionFrame parentframe,DataBaseOperate m)
    {
        super(parentframe,"ɾ��һ���˺�",true);
        this.con=m;
    	Dimension screensize=Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(335,325);
        Dimension framesize=this.getSize();
        int x=(int)screensize.getWidth()/2-(int)framesize.getWidth()/2;
        int y=(int)screensize.getHeight()/2-(int)framesize.getHeight()/2;
        this.setLocation(x,y);
        container=this.getContentPane();
    	container.setLayout(null);
    	JLabel picLabel=new JLabel();
    	ImageIcon loginIcon=new ImageIcon("deleteAccount.jpg");
        picLabel.setIcon(loginIcon);
        picLabel.setBounds(0, 0, 320, 300);
    	IDText1=new JTextField(50);
    	IDText1.setBounds(125, 110, 120, 25);
    	picLabel.add(IDText1);
    	IDText2=new JTextField(50);
    	picLabel.add(IDText2);
    	IDText2.setBounds(125, 155, 120, 25);
    	JButton okButton=new JButton("ȷ��");
    	okButton.addActionListener(new DeleAccountListener());
    	okButton.setBounds(130, 240, 80, 25);
    	okButton.setBackground(Color.orange);
    	okButton.setForeground(Color.BLACK);
    	picLabel.add(okButton);
    	container.add(picLabel);
    }
    
    class DeleAccountListener implements ActionListener
    {
		public void actionPerformed(ActionEvent e)
    	{
			if(IDText1.getText().equals(""))
			{
				inputusernull();
			}
			else if(IDText1.getText().length()>50)
			{
				lengthtoolong();
			}
			else
			{
				int res;
				res=deleSure();
				if(res==0)   //�����ǰ�ť
				{
					 if(IDText1.getText().equals(IDText2.getText()))
					  {
						  if(con.SearchAccountInDB(IDText1.getText()))
						  {
							  boolean rs;
							  rs=con.deleteAccountListinfo(IDText1.getText());
							  if(rs==true)
							  {
								  setVisible(false);
								  Okmsg();
							  }
						  }
						  else
						  {
							  deleteErr();
						  }
					  }
					  else
					  {
						  setErr();
					  }
				}
				else
				{
					return;
				}
			}
    	}
    }
    
    public void Okmsg()
    {
 	   String msg="�˺�ɾ���ɹ�";
 	   String title="��ʾ";
 	   JOptionPane.showMessageDialog(container, msg,title,JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void setErr()
    {
 	   String msg="�����˺�����Ĳ�һ��������������";
 	   String title="��ʾ";
 	   JOptionPane.showMessageDialog(container, msg,title,JOptionPane.WARNING_MESSAGE);
    }
    
    public void deleteErr()
    {
    	String msg="��Ҫɾ�����˺Ų����ڣ�����������";
  	    String title="��ʾ";
  	    JOptionPane.showMessageDialog(container, msg,title,JOptionPane.WARNING_MESSAGE);
    }
    
    public int deleSure()
    {
    	int result = JOptionPane.showConfirmDialog(container, "�Ƿ����Ҫɾ�����˺ţ�","��ʾ��Ϣ",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
        return result;
    }
    
    public void inputusernull()
    {
 	   String msg="�˺Ų���Ϊ��";
 	   String title="������ʾ";
 	   JOptionPane.showMessageDialog(container, msg,title,JOptionPane.WARNING_MESSAGE);
    }
    
    public void lengthtoolong()
    {
       String msg="���볤�ȹ���������������";
  	   String title="������ʾ";
  	   JOptionPane.showMessageDialog(container, msg,title,JOptionPane.WARNING_MESSAGE);
    }
}

