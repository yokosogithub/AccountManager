import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
public class EditCodeDialog extends JDialog{
	protected JPasswordField oldpswd;
    protected JPasswordField newpswd1;
    protected JPasswordField newpswd2;
    protected JButton okButton;
    protected LoginFrame frame;
    protected Container container;
    protected DataBaseOperate con;
    public EditCodeDialog(FunctionFrame parentframe,DataBaseOperate m)
    {
    	super(parentframe,"自定义密码设置",true);
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
    	ImageIcon loginIcon=new ImageIcon("PasswordSet.jpg");
        picLabel.setIcon(loginIcon);
        picLabel.setBounds(0, 0, 320, 300);
    	oldpswd=new JPasswordField(50);
    	oldpswd.setBounds(130, 105, 120, 25);
    	picLabel.add(oldpswd);
    	newpswd1=new JPasswordField(50);
    	newpswd1.setBounds(130, 150, 120,25);
    	picLabel.add(newpswd1);
    	newpswd2=new JPasswordField(50);
    	newpswd2.setBounds(130, 195, 120, 25);
    	picLabel.add(newpswd2);
    	JButton okButton=new JButton("确定");
    	okButton.addActionListener(new CodeSaveListener());
    	okButton.setBounds(125, 250, 80, 25);
    	okButton.setBackground(Color.orange);
    	okButton.setForeground(Color.BLACK);
    	picLabel.add(okButton);
    	container.add(picLabel);
    }
    
    class CodeSaveListener implements ActionListener
    {
    	protected String code;
		public void actionPerformed(ActionEvent e)
    	{
		  if(oldpswd.getText().equals("") || newpswd1.getText().equals(""))
		  {
			  inputusernull();
		  }
		  else if(oldpswd.getText().length()>50 || newpswd1.getText().length()>50)
		  {
			  lengthtoolong();
		  }
		  else
		  {
			  code=con.SearchCode();
			  if(code.equals(oldpswd.getText()))
			  {
				  if(newpswd1.getText().length()>=6)
				  {
					  if(newpswd1.getText().equals(newpswd2.getText()))
					  {
						  con.updateCodeDatainfo(code,newpswd1.getText());
				          Okmsg();
				          setVisible(false);
					  }
					  else
					  {
						  setErr();
					  }
				  }
				  else
				  {
					  codesizeErr();
				  }
			  }
			  else
			  {
				  oldCodeErr();
			  }
			   
		  }
		  
        }
    }
    
    public void Okmsg()
    {
 	   String msg="自定义密码设置成功";
 	   String title="提示";
 	   JOptionPane.showMessageDialog(container, msg,title,JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void setErr()
    {
 	   String msg="两次密码输入的不一样，请重新键入";
 	   String title="提示";
 	   JOptionPane.showMessageDialog(container, msg,title,JOptionPane.WARNING_MESSAGE);
    }
    
    public void oldCodeErr()
    {
       String msg="原始密码输入有错";
  	   String title="提示";
  	   JOptionPane.showMessageDialog(container, msg,title,JOptionPane.ERROR_MESSAGE);
    }
    
    public void codesizeErr()
    {
    	String msg="自定义密码至少有六位";
   	   String title="提示";
   	   JOptionPane.showMessageDialog(container, msg,title,JOptionPane.WARNING_MESSAGE);
    }
    
    public void inputusernull()
    {
 	   String msg="密码设置不能为空";
 	   String title="错误提示";
 	   JOptionPane.showMessageDialog(container, msg,title,JOptionPane.WARNING_MESSAGE);
    }
    
    public void lengthtoolong()
    {
       String msg="输入长度过长，请重新输入";
  	   String title="错误提示";
  	   JOptionPane.showMessageDialog(container, msg,title,JOptionPane.WARNING_MESSAGE);
    }
}
