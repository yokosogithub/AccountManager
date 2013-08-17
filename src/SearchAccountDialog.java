import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class SearchAccountDialog extends JDialog{
	protected JTextField IDText;
    protected String code;
    protected JButton searchButton;
    protected FunctionFrame frame;
    protected Container container;
    protected DataBaseOperate con;
    public SearchAccountDialog(FunctionFrame parentframe,DataBaseOperate m)
    {
        super(parentframe,"����һ���˺�",true);
        this.con=m;
    	Dimension screensize=Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(335,280);
        Dimension framesize=this.getSize();
        int x=(int)screensize.getWidth()/2-(int)framesize.getWidth()/2;
        int y=(int)screensize.getHeight()/2-(int)framesize.getHeight()/2;
        this.setLocation(x,y);
        container=this.getContentPane();
    	container.setLayout(null);
    	JLabel picLabel=new JLabel();
    	ImageIcon loginIcon=new ImageIcon("accountSearch.jpg");
    	picLabel.setIcon(loginIcon);
    	picLabel.setBounds(0, 0, 320, 250);
    	container.add(picLabel);
    	IDText=new JTextField(50);
    	picLabel.add(IDText);
    	IDText.setBounds(125, 105, 150, 30);
    	JButton searchButton=new JButton("��ʼ����");
    	searchButton.addActionListener(new SearchAccountListener());
    	searchButton.setBounds(110, 200, 120, 25);
    	searchButton.setBackground(Color.orange);
    	searchButton.setForeground(Color.BLACK);
    	picLabel.add(searchButton);
    	
    }
    
    public String getIDtext()
    {
    	return IDText.getText().trim();
    }
    
    class SearchAccountListener implements ActionListener
    {
		public void actionPerformed(ActionEvent e)
    	{
			if(IDText.getText().equals(""))
			{
				inputusernull();
			    
			}
			else if(IDText.getText().length()>50)
			{
				lengthtoolong();
			}
			else
			{
				if(con.SearchAccountInDB(IDText.getText()))
				{
					setVisible(false);
					Okmsg();
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
 	   String msg="�˺��Ѳ�ѯ��";
 	   String title="��ʾ";
 	   JOptionPane.showMessageDialog(container, msg,title,JOptionPane.INFORMATION_MESSAGE);
    }
     
    public void searchErr()
    {
    	String msg="��������˺Ų����ڻ��д�����������";
  	    String title="��ʾ";
  	    JOptionPane.showMessageDialog(container, msg,title,JOptionPane.ERROR_MESSAGE);
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

