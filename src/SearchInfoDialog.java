import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class SearchInfoDialog extends JDialog{
	protected JTextArea InfoText;
    protected String code;
    protected JButton searchButton;
    protected FunctionFrame frame;
    protected Container container;
    protected DataBaseOperate con;
    public SearchInfoDialog(FunctionFrame parentframe,DataBaseOperate m)
    {
        super(parentframe,"查找一个账号",true);
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
    	ImageIcon loginIcon=new ImageIcon("infoSearch.jpg");
    	picLabel.setBounds(0, 0, 320, 250);
    	picLabel.setIcon(loginIcon);
    	InfoText=new JTextArea(3,8);
    	InfoText.setBounds(125, 105, 150, 50);
    	picLabel.add(InfoText);
    	JButton searchButton=new JButton("开始检索");
    	searchButton.addActionListener(new SearchAccountListener());
    	searchButton.setBounds(110, 200, 120, 25);
    	searchButton.setBackground(Color.orange);
    	searchButton.setForeground(Color.BLACK);
    	picLabel.add(searchButton);
    	container.add(picLabel);
    	
    }
    
    public String getInfotext()
    {
    	return InfoText.getText();
    }
    
    class SearchAccountListener implements ActionListener
    {
		public void actionPerformed(ActionEvent e)
    	{
			if(InfoText.getText().equals(""))
			{
				inputusernull();
			    
			}
			else if(InfoText.getText().length()>255)
			{
				lengthtoolong();
			}
			else
			{
				if(con.SearchInfoInDB(InfoText.getText()))
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
 	   String msg="账号已查询到";
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
       String msg="输入长度过长，请重新输入";
  	   String title="错误提示";
  	   JOptionPane.showMessageDialog(container, msg,title,JOptionPane.WARNING_MESSAGE);
    }
}
