package admin;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
public class SwingJDBCOracleEmp extends JFrame{
 private Connection con;
 private Statement stmt; 
 
 private String url = "jdbc:oracle:thin:@localhost:1521:xe";
 private String ID = "lotte";
 private String PW = "lotte";
 
 private ResultSet rs=null;
 
 private JButton[] btn = new JButton[5];
 private JLabel[] label = new JLabel[8];
 private JTextField[] text = new JTextField[8];
 private JTable table;
 private JScrollPane jp = new JScrollPane();
 private JPanel p[]  = new JPanel[4];
 private JPanel p2  = new JPanel();
 
 private String[][] data=new String[100][8];
 private String[] col = {"empno    ","ename   ","job         ","mgr      ","hiredate",
 "sal        ","comm     ","deptno   "};
 private String[] string = {"","","","","","","",""};
 
 
 public SwingJDBCOracleEmp()
 {
  try{
   Class.forName("oracle.jdbc.driver.OracleDriver");   // JDBC ���   
   DriverManager.setLoginTimeout(60);
   con = DriverManager.getConnection(url, ID, PW);    // DB�� ����
   stmt = con.createStatement();  
  }
  catch(Exception ex)
  {
   System.err.println(ex);
  }  
  
 
  JPanel p1 = new JPanel();
  
  p1.setLayout(new GridLayout(5,1));
  
  JPanel p3 = new JPanel();
  
  btn[0] = new JButton("����");
  btn[1] = new JButton("���");
  btn[2] = new JButton("�Է�");
  btn[3] = new JButton("����");
  btn[4] = new JButton("����");
  
  for( int i= 0 ; i < 5 ; i++)
  {
   btn[i].addActionListener(action);
   p3.add(btn[i]);
  }
  
  for (int i = 0; i< 8; i++) {
   label[i] = new JLabel(col[i]);
     }
     for (int i = 0; i< 8; i++) {
   text[i] = new JTextField(15);
     }
     for ( int i = 0 ; i < 4 ; i++)
     {
      p[i] = new JPanel();
     }
     
     int j= -1;
     for( int i = 0 ; i < 8 ; i++)
     {
      
      if( i%2==0)
      {
       j++;
      }
      p[j].add(label[i]);
      p[j].add(text[i]);
     }
     
     p1.add(p3);
     p1.add(p[0]);
     p1.add(p[1]);
     p1.add(p[2]);
     p1.add(p[3]);
  
  table = new JTable(data,col);
  jp=new JScrollPane(table);
  jp.addMouseListener(new MouseAdapter(){});   
  
  Container cp = this.getContentPane();
  cp.setLayout(new GridLayout(2,1));
  cp.add(p1);
  cp.add(jp);
  
   
  setDefaultCloseOperation(DISPOSE_ON_CLOSE);  
  setBounds(100,100,500,500);
  setVisible(true);
  showTable();
 }
 public Statement getStatement()
 {
  return stmt;
 }
 
 
 public void showTable()
 {
  table.removeAll();
  try{
   for(int i = 0 ; i <100 ; i++)
   {
    for(int j = 0 ; j <  8;j++)
    {
     data[i][j]="";
    }
   }
   rs=stmt.executeQuery("select * from emp");
   int i=0;   
   while(rs.next())
   {
    data[i][0]=rs.getString(1);
    data[i][1]=rs.getString(2);
    data[i][2]=rs.getString(3);
    data[i][3]=rs.getString(4);
    data[i][4]=rs.getString(5);
    data[i][5]=rs.getString(6);
    data[i][6]=rs.getString(7);    
    data[i++][7]=rs.getString(8);
   }
   table.repaint();
  }
  catch(Exception e)
  {
   System.err.println("���� �ֱ� ����");
  }
 }
 
 ActionBtn action = new ActionBtn();
 
 class ActionBtn extends Exception implements ActionListener
 {
  public void actionPerformed(ActionEvent e)
  {
   if(e.getActionCommand().equals("���"))
   {    
    showTable();
   }
   else if(e.getActionCommand().equals("����"))
   {
    int row=table.getSelectedRow(); 
    /////////////////////
    // �ؽ�Ʈ �ʵ忡 �޾ƿ� ������ �����ֱ�
    for( int i= 0 ; i < 8 ;i++)
    {
     text[i].setText(data[row][i]);
    }    
   }
   else if(e.getActionCommand().equals("�Է�"))
   {
    try{
     for(int i = 0 ; i < 8; i++)
     {
      if( text[i].getText().equals(""))
      {
       if( i==0 || i==3||i==5||i==6||i==7)
        string[i]="\'\'";
       else
        string[i]="";
      }
      else
       string[i]=text[i].getText();
     }
     stmt.executeQuery(
     "insert into emp values(" + string[0]+ ",'" +
     string[1]+ "' ,'" +string[2]+ "'," +string[3]+ ",'" +
     string[4]+ "'," +string[5]+ "," +string[6]+ "," +
     string[7]+ ")");
     stmt.executeQuery("commit");
    }
    catch(Exception ex)
    {
     System.err.println("�Է� ����");
    }
    ////////////////////
    //ȭ�鿡 ����ϱ�
    showTable();
    for( int i = 0 ; i < 8 ; i++)
    {
     text[i].setText("");
    }
   }
   else if(e.getActionCommand().equals("����"))
   {
    try{
     stmt.executeQuery(
     "update emp set" + " empno = " +text[0].getText()+ " ,ename='" +
     text[1].getText()+ "',job='" +text[2].getText()+ "',mgr=" +text[3].getText()+ ",hiredate='" +
     text[4].getText()+ "',sal=" +text[5].getText()+ ",comm=" +text[6].getText()+ ",deptno =" +
     text[7].getText()+"where empno = "+text[0].getText());
     stmt.executeQuery("commit");
    }
    catch(Exception ex)
    {
     System.err.println("���� ����");
    }
    ////////////////////
    //ȭ�鿡 ����ϱ�
    showTable();
    for( int i = 0 ; i < 8 ; i++)
    {
     text[i].setText("");
    }
   }
   else if(e.getActionCommand().equals("����"))
   {
    try{
     System.out.println (text[0].getText());
     stmt.executeQuery("delete from emp where" + " empno = " +text[0].getText());
     stmt.executeQuery("commit");
    }
    catch(Exception ex)
    {
     System.err.println("���� ����");
    }
    ////////////////////
    //ȭ�鿡 ����ϱ�
    showTable();
    for( int i = 0 ; i < 8 ; i++)
    {
     text[i].setText("");
    }
   }
  }
 }
 
 public static void main(String[] args)  {
  SwingJDBCOracleEmp db = new SwingJDBCOracleEmp();
 }
 
 
}


