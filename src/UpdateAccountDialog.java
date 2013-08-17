import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class UpdateAccountDialog extends JDialog{
	protected JTextField oldIDText;
	protected JTextField newIDText;
	protected JTextArea Intro;
    protected String code;
    protected JButton okButton;
    protected FunctionFrame frame;
    protected Container container;
    protected DataBaseOperate con;
    public UpdateAccountDialog(FunctionFrame parentframe,DataBaseOperate m)
    {
        super(parentframe,"修改一个账号",true);
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
    	ImageIcon loginIcon=new ImageIcon("editAccount.jpg");
        picLabel.setIcon(loginIcon);
        picLabel.setBounds(0, 0, 320, 300);
    	oldIDText=new JTextField(50);
    	picLabel.add(oldIDText);
    	oldIDText.setBounds(125, 90, 120, 25);
    	newIDText=new JTextField(50);
    	picLabel.add(newIDText);
    	newIDText.setBounds(125, 135, 120,25);
    	Intro=new JTextArea(3,40);
    	picLabel.add(Intro);
    	Intro.setBounds(125, 180, 130, 40);
    	JButton okButton=new JButton("确定");
    	okButton.addActionListener(new UpdateAccountListener());
    	okButton.setBounds(130, 240, 80, 25);
    	okButton.setBackground(Color.orange);
    	okButton.setForeground(Color.BLACK);
    	picLabel.add(okButton);
    	container.add(picLabel);
   }
    
    class UpdateAccountListener implements ActionListener
    {
		public void actionPerformed(ActionEvent e)
    	{
			if(oldIDText.getText().equals("") || newIDText.getText().equals(""))
			{
				inputusernull();
			}
			else if(newIDText.getText().length()>=50)
			{
				lengthtoolong();
			}
			else
			{
				if(con.SearchAccountInDB(oldIDText.getText()))
				{
					boolean rs;
					rs=con.updateAccountListinfo(oldIDText.getText(), newIDText.getText(),Intro.getText());
					if(rs==true)
					{
						setVisible(false);
						Okmsg();
					}
				}
				else
				{
					searchErr();
				}
			}
	     }
    }
    
    public void Okmsg()
    {
 	   String msg="账号修改成功";
 	   String title="提示";
 	   JOptionPane.showMessageDialog(container, msg,title,JOptionPane.INFORMATION_MESSAGE);
    }
     
    public void searchErr()
    {
    	String msg="你输入的账号不存在或有错，请重新输入";
  	    String title="提示";
  	    JOptionPane.showMessageDialog(container, msg,title,JOptionPane.WARNING_MESSAGE);
    }
    
    public void inputusernull()
    {
 	   String msg="账号不能为空";
 	   String title="错误提示";
 	   JOptionPane.showMessageDialog(container, msg,title,JOptionPane.WARNING_MESSAGE);
    }
    
    public void lengthtoolong()
    {
       String msg="账号长度过长，请重新输入";
  	   String title="错误提示";
  	   JOptionPane.showMessageDialog(container, msg,title,JOptionPane.WARNING_MESSAGE);
    }
}
