import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class SetAccountDialog extends JDialog{
	protected JTextField IDText1;
	protected JTextField IDText2;
	protected JTextArea Intro;
    protected String code;
    protected JButton okButton;
    protected FunctionFrame frame;
    protected Container container;
    protected DataBaseOperate con;
    public SetAccountDialog(FunctionFrame parentframe,DataBaseOperate m)
    {
        super(parentframe,"����һ���˺�",true);
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
    	ImageIcon loginIcon=new ImageIcon("addAccount.jpg");
        picLabel.setIcon(loginIcon);
        picLabel.setBounds(0, 0, 320, 300);
    	IDText1=new JTextField(50);
    	IDText1.setBounds(125, 110, 120, 25);
    	picLabel.add(IDText1);
    	IDText2=new JTextField(50);
    	IDText2.setBounds(125, 145, 120,25);
    	picLabel.add(IDText2);
    	Intro=new JTextArea(3,40);
    	Intro.setBounds(125, 180, 130, 40);
    	picLabel.add(Intro);
    	JButton okButton=new JButton("ȷ��");
    	okButton.addActionListener(new AddAccountListener());
    	okButton.setBounds(130, 240, 80, 25);
    	okButton.setBackground(Color.orange);
    	okButton.setForeground(Color.BLACK);
    	picLabel.add(okButton);
    	container.add(picLabel);
    }
    
    class AddAccountListener implements ActionListener
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
			else if(Intro.getText().length()>255)
			{
				lengthtoolong();
			}
			else
			{
				if(IDText1.getText().equals(IDText2.getText()))
				  {
					  if(!con.SearchAccountInDB(IDText1.getText()))
					  {
						  boolean rs;
						  rs=con.addAccountListinfo(IDText1.getText(),Intro.getText());
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
				  else
				  {
					  setErr();
				  }
	        }
			}
			
    }
    
    public void Okmsg()
    {
 	   String msg="�˺���ӳɹ�";
 	   String title="��ʾ";
 	   JOptionPane.showMessageDialog(container, msg,title,JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void setErr()
    {
 	   String msg="�����˺�����Ĳ�һ��������������";
 	   String title="��ʾ";
 	   JOptionPane.showMessageDialog(container, msg,title,JOptionPane.WARNING_MESSAGE);
    }
    
    public void searchErr()
    {
    	String msg="��������˺��Ѿ����ڣ�����������";
  	    String title="��ʾ";
  	    JOptionPane.showMessageDialog(container, msg,title,JOptionPane.WARNING_MESSAGE);
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
