import javax.swing.*;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
public class SetUserDialog extends JDialog{
	protected JTextField IDText;
	protected JPasswordField CodeText1;
	protected JPasswordField CodeText2;
	protected JTextField Intro;
    protected String code;
    protected JButton okButton;
    protected FunctionFrame frame;
    protected Container container;
    protected DataBaseOperate con;
    protected int sureData=0;
    public SetUserDialog(LoginFrame parentframe,DataBaseOperate m)
    {
        super(parentframe,"注册用户",true);
        this.con=m;
    	Dimension screensize=Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(385,400);
        Dimension framesize=this.getSize();
        int x=(int)screensize.getWidth()/2-(int)framesize.getWidth()/2;
        int y=(int)screensize.getHeight()/2-(int)framesize.getHeight()/2;
        this.setLocation(x,y);
        container=this.getContentPane();
    	container.setLayout(null);
    	JLabel picLabel=new JLabel();
        ImageIcon loginIcon=new ImageIcon("SetUser.jpg");
        picLabel.setIcon(loginIcon);
        picLabel.setBounds(0, 0, 369, 369);
        container.add(picLabel);
    	IDText=new JTextField(50);
    	IDText.setBounds(155, 125, 120, 25);
    	picLabel.add(IDText);
    	CodeText1=new JPasswordField(50);
    	CodeText1.setBounds(155, 175, 120, 25);
    	picLabel.add(CodeText1);
    	CodeText2=new JPasswordField(50);
    	picLabel.add(CodeText2);
    	CodeText2.setBounds(155, 225, 120, 25);
    	JButton okButton=new JButton("确定");
    	okButton.addActionListener(new AddAccountListener());
    	okButton.setBounds(130, 280, 80, 25);
    	okButton.setBackground(Color.orange);
    	okButton.setForeground(Color.BLACK);
    	picLabel.add(okButton);
    	
    }
    
    class AddAccountListener implements ActionListener
    {
		public void actionPerformed(ActionEvent e)
    	{
			if(IDText.getText().equals(""))
			{
				inputusernull();
			}
			else if(CodeText1.getText().equals(""))
			{
				inputcodenull();
			}
			else if(CodeText1.getText().length()<6)
			{
				codeTip();
			}
			else if(IDText.getText().length()>50 || CodeText1.getText().length()>50)
			{
				lengthtoolong();
			}
			else 
			{
				if(CodeText1.getText().equals(CodeText2.getText()) )
				  {
					  if(!con.SearchUserInDB(IDText.getText()))
					  {
						  boolean rs;
						  rs=con.regeditAccount(IDText.getText(), CodeText1.getText());
						  con.generateAccountTable(IDText.getText());
						  if(rs==true)
						  {
							  setVisible(false);
							  Okmsg();
							  sureData=1;
						  }
					  }
					  else
					  {
						  searchErr();
					  }
				  }
				  else
				  {
					  setErr();
				  }
			}
			
        }
    }
    
    public void Okmsg()
    {
 	   String msg="用户注册成功";
 	   String title="提示";
 	   JOptionPane.showMessageDialog(container, msg,title,JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void setErr()
    {
 	   String msg="两次密码输入的不一样，请重新输入";
 	   String title="提示";
 	   JOptionPane.showMessageDialog(container, msg,title,JOptionPane.ERROR_MESSAGE);
    }
    
    public void searchErr()
    {
    	String msg="你输入的用户名已经存在，请重新输入";
  	    String title="提示";
  	    JOptionPane.showMessageDialog(container, msg,title,JOptionPane.ERROR_MESSAGE);
    }
    
    public void inputusernull()
    {
 	   String msg="用户名不能为空";
 	   String title="错误提示";
 	   JOptionPane.showMessageDialog(container, msg,title,JOptionPane.ERROR_MESSAGE);
    }
    
    public void inputcodenull()
    {
 	   String msg="密码不能为空";
 	   String title="错误提示";
 	   JOptionPane.showMessageDialog(container, msg,title,JOptionPane.ERROR_MESSAGE);
    }
    
    public void codeTip()
    {
       String msg="密码长度小于6，请重新输入";
  	   String title="错误提示";
  	   JOptionPane.showMessageDialog(container, msg,title,JOptionPane.ERROR_MESSAGE);
    }
    
    public void lengthtoolong()
    {
       String msg="输入长度过长，请重新输入";
  	   String title="错误提示";
  	   JOptionPane.showMessageDialog(container, msg,title,JOptionPane.ERROR_MESSAGE);
    }
    
}

