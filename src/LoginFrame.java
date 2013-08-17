import java.awt.*;
import javax.swing.*;

import java.awt.event.*;
import java.sql.*;
public class LoginFrame extends JFrame {
	protected JTextField userFieldText;
	protected JPasswordField pswdText;
	protected Container container;
	public DataBaseOperate dbConnection;
	protected boolean Visible=false;
	protected SetUserDialog setuser;
	protected int regedit=0;
	protected JButton okButton;
	protected JButton regeditButton;
	protected static ImageIcon[] loading;
    public LoginFrame()
    {
    	this.setTitle("�û���¼");
    	container=this.getContentPane();
    	container.setLayout(null);
    	JLabel picLabel=new JLabel();
        ImageIcon loginIcon=new ImageIcon("Login.jpg");
        picLabel.setIcon(loginIcon);
        picLabel.setBounds(0, 0, 420, 240);
        container.add(picLabel);
    	JLabel userIDLabel=new JLabel("�˺�",JLabel.CENTER);
    	userIDLabel.setBounds(10, 120, 50, 25);
    	userIDLabel.setForeground(Color.WHITE);
    	picLabel.add(userIDLabel);
    	userFieldText=new JTextField(20);
    	userFieldText.setBounds(55, 120, 120, 25);
    	picLabel.add(userFieldText);
    	regeditButton=new JButton("ע�����˺�");
    	regeditButton.addActionListener(new LoginActionListener());
    	regeditButton.setBounds(100, 165, 100, 25);
    	regeditButton.setBackground(Color.orange);
    	regeditButton.setForeground(Color.BLACK);
    	picLabel.add(regeditButton);
    	regeditButton.addActionListener(new RegeditActionListener());
    	JLabel userCodeLabel=new JLabel("����",JLabel.CENTER);
    	userCodeLabel.setBounds(200, 120, 50, 25);
    	userCodeLabel.setForeground(Color.WHITE);
    	picLabel.add(userCodeLabel);
    	pswdText=new JPasswordField(20);
    	pswdText.setBounds(245, 120, 120, 25);
    	picLabel.add(pswdText);
    	okButton=new JButton("��½");
    	okButton.addActionListener(new LoginActionListener());
    	okButton.setBounds(225, 165, 80, 25);
    	okButton.setBackground(Color.orange);
    	okButton.setForeground(Color.BLACK);
    	picLabel.add(okButton);
    	Toolkit kit = Toolkit.getDefaultToolkit();
    	Dimension screenSize = kit.getScreenSize();
    	Toolkit kit1= Toolkit.getDefaultToolkit();
    	Dimension screenSize1 = kit1.getScreenSize();
    	int screenHeight = screenSize1.height;
    	int screenWidth = screenSize1.width;
        this.setSize(430,275);
    	this.setLocation((screenWidth - 400) / 2, (screenHeight - 150) / 2);
    	this.setVisible(true);
    }
  
    class LoginActionListener implements ActionListener
    {
		public void actionPerformed(ActionEvent e)
    	{
           if(regedit==0)  //���ݿ⺬������һ���û�
           {
        	   if(userFieldText.getText().equals(""))
               {
            	   inputusernull();
               }
               else if(pswdText.getText().equals(""))
               {
            	   inputcodenull();
               }
               else
               {
            	   dbConnection=new DataBaseOperate(userFieldText.getText(),pswdText.getText());
            	   if(dbConnection.SerachUserInDB())
                   {
                	   if(!dbConnection.LoginOk())
                       {
                    	   loginerr();
                       }
                       else
                       {
                    	   setVisible(false);
                    	   loginok();
                    	   FunctionFrame frame=new FunctionFrame(dbConnection);
                    	   frame.setVisible(true);
                       }
                   }
                   else
                   {
                	   loginnull();
                	   nulluser();
                	   if(setuser.sureData==1)
               		   {
                		 regedit=1;
               			 setVisible(false);
                    	 FunctionFrame frame=new FunctionFrame(dbConnection);
                    	 frame.setVisible(true);
               		   }
                   }
                   
               }
           }
           
           
        }
    }
    
    class RegeditActionListener implements ActionListener
    {
    	public void actionPerformed(ActionEvent e)
    	{
    		dbConnection=new DataBaseOperate();
    		nulluser();
    		if(setuser.sureData==1)
    		{
    			regedit=1;
    			setVisible(false);
         	    FunctionFrame frame=new FunctionFrame(dbConnection);
         	    frame.setVisible(true);
    		}
    		
    	}
    }
   
    public void loginok()
   {
	   String msg="��½�ɹ�";
	   String title="��½��ʾ";
	   JOptionPane.showMessageDialog(container, msg,title,JOptionPane.INFORMATION_MESSAGE);
   }
   
   public void loginerr()
   {
	   String msg="�˺Ż������д�!����������";
	   String title="������ʾ";
	   JOptionPane.showMessageDialog(container, msg,title,JOptionPane.ERROR_MESSAGE);
   }
   
   public void loginnull()
   {
	   String msg="�״�ʹ��ϵͳ����ע���û�";
	   String title="������ʾ";
	   JOptionPane.showMessageDialog(container, msg,title,JOptionPane.ERROR_MESSAGE);
   }
   
   public void inputusernull()
   {
	   String msg="�û�������Ϊ��";
	   String title="������ʾ";
	   JOptionPane.showMessageDialog(container, msg,title,JOptionPane.ERROR_MESSAGE);
   }
   
   public void inputcodenull()
   {
	   String msg="���벻��Ϊ��";
	   String title="������ʾ";
	   JOptionPane.showMessageDialog(container, msg,title,JOptionPane.ERROR_MESSAGE);
   }
   
   public void nulluser()
   {
	   setuser=new SetUserDialog(this,dbConnection);
	   setuser.setVisible(true);
   }
   
  
   
   public static void main(String[] args)
   {
	   LoginFrame login=new LoginFrame();
	   
   }
 
}
