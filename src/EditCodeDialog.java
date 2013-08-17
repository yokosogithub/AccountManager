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
    	super(parentframe,"�Զ�����������",true);
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
    	JButton okButton=new JButton("ȷ��");
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
 	   String msg="�Զ����������óɹ�";
 	   String title="��ʾ";
 	   JOptionPane.showMessageDialog(container, msg,title,JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void setErr()
    {
 	   String msg="������������Ĳ�һ���������¼���";
 	   String title="��ʾ";
 	   JOptionPane.showMessageDialog(container, msg,title,JOptionPane.WARNING_MESSAGE);
    }
    
    public void oldCodeErr()
    {
       String msg="ԭʼ���������д�";
  	   String title="��ʾ";
  	   JOptionPane.showMessageDialog(container, msg,title,JOptionPane.ERROR_MESSAGE);
    }
    
    public void codesizeErr()
    {
    	String msg="�Զ���������������λ";
   	   String title="��ʾ";
   	   JOptionPane.showMessageDialog(container, msg,title,JOptionPane.WARNING_MESSAGE);
    }
    
    public void inputusernull()
    {
 	   String msg="�������ò���Ϊ��";
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
