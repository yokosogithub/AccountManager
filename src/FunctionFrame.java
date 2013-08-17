import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
public class FunctionFrame extends JFrame{
   protected JTabbedPane tabbedPane;
   protected DataBaseOperate con;
   protected SetAccountDialog addAccount;
   protected DeleAccountDialog deleAccount;
   protected UpdateAccountDialog updateAccount;
   protected SearchAccountDialog searchAccount;
   protected SearchInfoDialog searchInfo;
   protected EditCodeDialog editCode;
   protected Container container;
   protected JTable jt;
   protected JScrollPane jsp;
   protected AccountModel sm;
   public FunctionFrame(DataBaseOperate m)
   {
	   this.con=m;
	   this.setTitle("欢迎使用账号管理系统");
	   container=this.getContentPane();
	   container.setLayout(new BorderLayout());
	   tabbedPane=new JTabbedPane();
	   container.add(BorderLayout.CENTER,tabbedPane);//设置选项卡窗格的位置
	   /*建立菜单*/
	   sm=new AccountModel(m);
	   jt=new JTable(sm);
	   jsp=new JScrollPane(jt);
	   this.add(jsp);
	   JMenuBar menuBar=new JMenuBar();
	   buildMainMenu(menuBar);
	   this.setJMenuBar(menuBar);
	   Dimension screensize=Toolkit.getDefaultToolkit().getScreenSize();
       this.setSize(600,450);
       Dimension framesize=this.getSize();
       int x=(int)screensize.getWidth()/2-(int)framesize.getWidth()/2;
       int y=(int)screensize.getHeight()/2-(int)framesize.getHeight()/2;
       this.setLocation(x,y);
       this.addWindowListener(new WindowCloser());//为窗口增加监听，关闭窗口
   }
   
   public void buildMainMenu(JMenuBar menuBar)
   {
	   JMenu accountMenu=new JMenu("账号(A)");
	   ImageIcon loginIcon=new ImageIcon("myaccount.png");
	   accountMenu.setIcon(loginIcon);
	   accountMenu.setMnemonic(KeyEvent.VK_A);
	   JMenuItem showMenuItem=new JMenuItem("显示全部");
	   showMenuItem.addActionListener(new ShowActionListener());
	   showMenuItem.setIcon(new ImageIcon("showall.png"));
	   JMenuItem addMenuItem=new JMenuItem("增加");
	   addMenuItem.addActionListener(new AddActionListener());
	   addMenuItem.setIcon(new ImageIcon("plus.png"));
	   JMenuItem deleMenuItem=new JMenuItem("删除");
	   deleMenuItem.addActionListener(new DeleActionListener());
	   deleMenuItem.setIcon(new ImageIcon("delete.png"));
	   JMenuItem updateMenuItem=new JMenuItem("修改");
	   updateMenuItem.addActionListener(new UpdateActionListener());
	   updateMenuItem.setIcon(new ImageIcon("edit.png"));
	   JMenuItem exitMenuItem=new JMenuItem("退出");
	   exitMenuItem.setIcon(new ImageIcon("exit.png"));
	   exitMenuItem.addActionListener(new ExitActionListener());
	   accountMenu.add(showMenuItem);
	   accountMenu.add(addMenuItem);
	   accountMenu.add(deleMenuItem);
	   accountMenu.add(updateMenuItem);
	   accountMenu.add(exitMenuItem);
	   menuBar.add(accountMenu);
	   setupAccountSearchMenu(menuBar);
	   setupLookAndFeelMenu(menuBar);
	   setupSetMenu(menuBar);
	   JMenu helpMenu=new JMenu("帮助(H)");
	   ImageIcon Help=new ImageIcon("help.png");
	   helpMenu.setIcon(Help);
	   helpMenu.setMnemonic(KeyEvent.VK_H);
	   JMenuItem aboutMenuItem=new JMenuItem("关于");
	   aboutMenuItem.addActionListener(new AboutActionListener());
	   aboutMenuItem.setIcon(new ImageIcon("about.png"));
	   helpMenu.add(aboutMenuItem);
	   menuBar.add(helpMenu);
   }
   
   protected void setupAccountSearchMenu(JMenuBar menuBar)
   {
	   JMenu searchMenu=new JMenu("检索(s)");
	   searchMenu.setMnemonic(KeyEvent.VK_S);
	   ImageIcon search=new ImageIcon("search.png");
	   searchMenu.setIcon(search);
	   JMenuItem searchMenuItem=new JMenuItem("通过账号检索");
	   searchMenuItem.addActionListener(new SearchActionListener());
	   searchMenuItem.setIcon(new ImageIcon("account.png"));
	   JMenuItem IntroSearchMenuItem=new JMenuItem("通过备注检索");
	   IntroSearchMenuItem.addActionListener(new IntroActionListener());
	   IntroSearchMenuItem.setIcon(new ImageIcon("info.png"));
	   searchMenu.add(searchMenuItem);
	   searchMenu.add(IntroSearchMenuItem);
	   menuBar.add(searchMenu);
   }
   
   
   protected void setupLookAndFeelMenu(JMenuBar menuBar)
   {
	   UIManager.LookAndFeelInfo[] lookAndFeelInfo=UIManager.getInstalledLookAndFeels();
	   JMenu lookAndFeelMenu=new JMenu("外观(S)");
	   lookAndFeelMenu.setMnemonic(KeyEvent.VK_S);
	   ImageIcon LookAndFeel=new ImageIcon("drawings.png");
	   lookAndFeelMenu.setIcon(LookAndFeel);
	   JMenuItem anItem=null;
	   LookAndFeelListener myListener=new LookAndFeelListener();
	   try{
		   for(int i=0;i<lookAndFeelInfo.length;i++)
		   {
			   anItem=new JMenuItem(lookAndFeelInfo[i].getName()+"外观");
			   anItem.setActionCommand(lookAndFeelInfo[i].getClassName());
			   anItem.addActionListener(myListener);
			   anItem.setIcon(new ImageIcon("lookandfeel.png"));
			   lookAndFeelMenu.add(anItem);
		   }
	   } catch(Exception e)
	   {
		   e.printStackTrace();
	   }
	   menuBar.add(lookAndFeelMenu);
   }
   
   protected void setupSetMenu(JMenuBar menuBar)
   {
	   JMenu sysSetMenu=new JMenu("设置(U)");
	   sysSetMenu.setMnemonic(KeyEvent.VK_U);
	   ImageIcon SYSset=new ImageIcon("settings.png");
	   sysSetMenu.setIcon(SYSset);
	   JMenuItem EditCodeMenuItem=new JMenuItem("自定义密码修改");
	   EditCodeMenuItem.addActionListener(new editCodeActionListener());
	   EditCodeMenuItem.setIcon(new ImageIcon("password.png"));
	   sysSetMenu.add(EditCodeMenuItem);
	   menuBar.add(sysSetMenu);
   }
   public void exitSystem()
   {
	   setVisible(false);
	   dispose();
	   System.exit(0);
   }
   
   class WindowCloser extends WindowAdapter
   {
	   public void windowClosing(WindowEvent e)
	   {
		   exitSystem();
	   }
   }
   
   class ShowActionListener implements ActionListener
   {
	   public void actionPerformed(ActionEvent event)
	   {
		   sm=new AccountModel(con);
		   jt.setModel(sm);
	   }
   }
   
   class LookAndFeelListener implements ActionListener
   {
	   public void actionPerformed(ActionEvent event)
	   {
		   String className=event.getActionCommand();
		   try{
			   UIManager.setLookAndFeel(className);
			   SwingUtilities.updateComponentTreeUI(FunctionFrame.this);
		   }catch(Exception e)
		   {
			   e.printStackTrace();
		   }
		   
	   }
   }
  
   class AddActionListener implements ActionListener
   {
	   public void actionPerformed(ActionEvent event)
	   {
		   AddAccount();
		   sm=new AccountModel(con);
		   jt.setModel(sm);
	   }
   }
   
   public void AddAccount()
   {
	   addAccount=new SetAccountDialog(this,con);
	   addAccount.setVisible(true);
	   
   }
   
   class DeleActionListener implements ActionListener
   {
	   public void actionPerformed(ActionEvent event)
	   {
		   DeleAccount();
		   sm=new AccountModel(con);
		   jt.setModel(sm);
	   }
   }
   
   public void DeleAccount()
   {
	   deleAccount=new DeleAccountDialog(this,con);
	   deleAccount.setVisible(true);
   }
   
   class UpdateActionListener implements ActionListener
   {
	   public void actionPerformed(ActionEvent event)
	   {
		   UpdateAccount();
		   sm=new AccountModel(con);
		   jt.setModel(sm);
	   }
   }
   
   public void UpdateAccount()
   {
	   updateAccount=new UpdateAccountDialog(this,con);
	   updateAccount.setVisible(true);
   }
   
   class ExitActionListener implements ActionListener
   {
	   public void actionPerformed(ActionEvent event)
	   {
		   exitSystem();
	   }
   }
   
   class SearchActionListener implements ActionListener
   {
	  public void actionPerformed(ActionEvent e) 
	  {
		  SearchAccount();
		  String tempaccount=searchAccount.getIDtext().trim();
		  String sql="select * from name_"+con.username+" WHERE account LIKE '"+"%"+tempaccount+"%'";
		  sm=new AccountModel(con,sql);
		  jt.setModel(sm);
		  
	  }
   }
   
   public void SearchAccount()
   {
	   searchAccount=new SearchAccountDialog(this,con);
	   searchAccount.setVisible(true);
   }
   
   class IntroActionListener implements ActionListener
   {
	   public void actionPerformed(ActionEvent e)
	   {
		   SearchInfo();
		   String tempinfo=searchInfo.getInfotext().trim(); //去掉字符串首尾空格  防止不必要的空格导致错误
		   String sql="select * from name_"+con.username+" WHERE intro LIKE '"+"%"+tempinfo+"%'";
		   sm=new AccountModel(con,sql);
		   jt.setModel(sm);
	   }
   }
   
   public void SearchInfo()
   {
	   searchInfo=new SearchInfoDialog(this,con);
	   searchInfo.setVisible(true);
   }
   
      
   
   class AboutActionListener implements ActionListener
   {
	   public void actionPerformed(ActionEvent e)
	   {
		   String msg="JAVA 账号管理管理精灵  V1.0 \n定期清理cookies可以避免木马程序在不知不觉中盗取密码\n账号与自定义密码长度相当可以提高加密效果\n不使用剪贴板输入密码会更加安全\n制作者:谢鹏  金文成 \n 联系方式:448142893@qq.com \n欢迎交流";
		   String title="关于我们";
		   JOptionPane.showMessageDialog(container, msg,title,JOptionPane.INFORMATION_MESSAGE);
	   }
   }
   
   class editCodeActionListener implements ActionListener
   {
	   public void actionPerformed(ActionEvent e)
	   {
		   editCode();
		   sm=new AccountModel(con);
		   jt.setModel(sm);
	   }
   }
   
   public void editCode()
   {
	   editCode=new EditCodeDialog(this,con);
	   editCode.setVisible(true);
	   
   }
   

}
