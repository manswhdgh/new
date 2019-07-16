package admin;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;

import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Order_result extends JFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Order_result frame = new Order_result();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	private JTable table;
	private JScrollPane jp = new JScrollPane();
	private String[] col = {"주문번호    ","메뉴   ","가격         ","결제유형      ","포인트사용", "포장여부", "제휴사할인"};
	private String[][] data=new String[100][7];
	private ResultSet rs=null;
	private Connection con;
	private Statement stmt;
	public Order_result() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				String driver = "oracle.jdbc.driver.OracleDriver";
				String url = "jdbc:oracle:thin:@localhost:1521:xe";
				String user = "lotte";
				String password = "lotte";
				try {
					Class.forName(driver);
					System.out.println("jdbc driver 로딩 성공");
					con = DriverManager.getConnection(url, user, password);
					stmt = con.createStatement();
					System.out.println("오라클 연결 성공");
				} catch (ClassNotFoundException aa) {
					System.out.println("jdbc driver 로딩 실패");
				} catch (SQLException aa) {
					System.out.println("오라클 연결 실패");
				}
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 500);
		getContentPane().setLayout(null);
		
		JPanel result_panel = new JPanel();
		result_panel.setBackground(Color.WHITE);
		result_panel.setBounds(0, 0, 533, 753);
		getContentPane().add(result_panel);
		result_panel.setLayout(null);
		
		JLabel label = new JLabel("\uD310\uB9E4 \uB0B4\uC5ED");
		label.setBounds(14, 12, 468, 44);
		label.setFont(new Font("배달의민족 도현", Font.PLAIN, 30));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		result_panel.add(label);
		
		JButton btnNewButton = new JButton("\uD310\uB9E4\uB0B4\uC5ED \uC870\uD68C");
		btnNewButton.setBounds(14, 714, 128, 27);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		result_panel.add(btnNewButton);
		
		JButton btnExport = new JButton("\uC678\uBD80\uB85C \uC800\uC7A5");
		btnExport.setBounds(156, 714, 107, 27);
		btnExport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		result_panel.add(btnExport);
		
		JButton button = new JButton("\uB3CC\uC544\uAC00\uAE30");
		button.setBounds(277, 714, 105, 27);
		result_panel.add(button);
		
		table = new JTable(data,col);
		table.setShowGrid(false);
		jp=new JScrollPane(table);
		jp.setBounds(0, 97, 482, 356);
		result_panel.add(jp);
		/* 테이블 컬럼값 가운데 정렬 */
		// DefaultTableCellHeaderRenderer 생성 (가운데 정렬을 위한)
		DefaultTableCellRenderer tScheduleCellRenderer = new DefaultTableCellRenderer();

		// DefaultTableCellHeaderRenderer의 정렬을 가운데 정렬로 지정
		tScheduleCellRenderer.setHorizontalAlignment(SwingConstants.CENTER);

		// 정렬할 테이블의 ColumnModel을 가져옴
		TableColumnModel tcmSchedule = table.getColumnModel();

		// 반복문을 이용하여 테이블을 가운데 정렬로 지정
		for (int i = 0; i < tcmSchedule.getColumnCount(); i++) {
			tcmSchedule.getColumn(i).setCellRenderer(tScheduleCellRenderer);
		}
		
		JButton btnNewButton_2 = new JButton("\uC870\uD68C");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showTable();
			}
		});
		btnNewButton_2.setBounds(14, 68, 105, 27);
		result_panel.add(btnNewButton_2);
		
		JButton button_1 = new JButton("\uB85C\uADF8\uC544\uC6C3");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new start();
				dispose();
			}
		});
		button_1.setBounds(126, 68, 105, 27);
		result_panel.add(button_1);
		jp.addMouseListener(new MouseAdapter(){});   
		  
		  Container cp = this.getContentPane();
		  cp.setLayout(new GridLayout());
		  
		   
		  setDefaultCloseOperation(DISPOSE_ON_CLOSE);  
		  setBounds(100,100,500,500);
		  setVisible(true);
		  
	}
		 public Statement getStatement()
		 {
		  return stmt;
		 }
		 
		 //select문을 화면에 Export하기위한 JTable 행,열 생성
		 public void showTable(){
			 table.removeAll();
			 try{
				 for(int i = 0 ; i <100 ; i++){
					 for(int j = 0 ; j <  5;j++){
						 data[i][j]="";
					 }
				 }
				 rs=stmt.executeQuery("select * from ORDRESULT");
				 int i = 0;   
				 while(rs.next()){
					 data[i][0]=rs.getString(1);
					 data[i][1]=rs.getString(2);
					 data[i][2]=rs.getString(3);
					 data[i][3]=rs.getString(4);
					 data[i][4]=rs.getString(5);
					 data[i][5]=rs.getString(7);
					 data[i][6]=rs.getString(9);
					 i++;
				 }
				 table.repaint();
			 }catch(Exception e){
				 System.err.println("보여 주기 에러");
			 }
		 }
}
