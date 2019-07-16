package main;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.UIManager;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MidPage extends JFrame {

	private JPanel contentPane;
	private ResultSet rs = null;
	private Connection con;
	private Statement stmt;
	PreparedStatement st = null;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MidPage frame = new MidPage();
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
	public MidPage() {
		addWindowListener(new WindowAdapter() {
			@Override // DB 연동
			public void windowOpened(WindowEvent arg0) {
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
		// 카드,현금,쿠폰 선택화면 패널 설정 
		setBounds(100, 100, 619, 733);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(12, 24, 579, 653);
		contentPane.add(panel);
		panel.setLayout(null);
		
		//팝업 창 객체 생성
		JOptionPane aa = new JOptionPane();
		
		JButton btnNewButton = new JButton(""); 
		btnNewButton.addActionListener(new ActionListener() {//카드 클릭시 액션 설정 
			@Override
			public void actionPerformed(ActionEvent e) {
				//DB 쿼리문
				String sql = "update ordresult set 결제유형 = '카드' where 주문번호 = 99";
				int result = 0;
				try {
					st = con.prepareStatement(sql);
					result = st.executeUpdate();
					System.out.println("명령어성공!");
				} catch (Exception e1) {
					// TODO: handle exception
					e1.printStackTrace();
				}
				new LastPage(); //선택시 다음 페이지로 넘겨주는 설정 
				dispose(); // 현재의 프레임 종료  
			}
		});
		// 카드 버튼  설정 
		btnNewButton.setIcon(new ImageIcon("C:\\Users\\mansw\\Desktop\\image\\\uCE74\uB4DC\uACB0\uC81C.jpg"));
		btnNewButton.setBounds(0, 56, 580, 340);
		panel.add(btnNewButton);
		
		// 현금 버튼 생성 
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.addActionListener(new ActionListener() { // 현금 클릭시 액션 설정 
			@Override // DB 쿼리문
			public void actionPerformed(ActionEvent e) {
				String sql = "update ordresult set 결제유형 = '현금' where 주문번호 = 99";
				int result = 0;
				try {
					st = con.prepareStatement(sql);
					result = st.executeUpdate();
					System.out.println("명령어성공!");
				} catch (Exception e1) {
					// TODO: handle exception
					e1.printStackTrace();
				}
				// 선택시 팝업창으로 나타내주는 설정
				aa.showMessageDialog(null,"     현금결제는 카운터에서 주문바랍니다. \n 현금결제는 대기없이 바로 수령가능합니다.");
				aa.showMessageDialog(null, "주문프로그램을 종료합니다. \n카운터로 와주시길바랍니다.");
				System.exit(0); // 프로그램 종료 
			}
		});
		 
		// 현금  버튼  설정 
		btnNewButton_1.setIcon(new ImageIcon("C:\\Users\\mansw\\Desktop\\image\\\uD604\uAE08.jpg"));
		btnNewButton_1.setBounds(289, 393, 290, 257);
		panel.add(btnNewButton_1);
		
		JButton button = new JButton("");
		button.addActionListener(new ActionListener() { // 쿠폰 클릭 시 액션 설정 
			@Override // DB 쿼리문 
			public void actionPerformed(ActionEvent arg0) {
				String sql = "update ordresult set 결제유형 = '쿠폰' where 주문번호 = 99";
				int result = 0;
				try {
					st = con.prepareStatement(sql);
					result = st.executeUpdate();
					System.out.println("명령어성공!");
				} catch (Exception e1) {
					// TODO: handle exception
					e1.printStackTrace();
				}
				// 선택시 팝업창으로 나타내주는 설정 
				aa.showMessageDialog(null,"     쿠폰사용은 카운터에 문의바랍니다. \n 쿠폰사용은 대기없이 바로 수령가능합니다.");
				aa.showMessageDialog(null, "주문프로그램을 종료합니다. \n카운터로 와주시길바랍니다.");
				System.exit(0); // 프로그램 종료 
			}
		});
	   // 쿠폰 버튼  설정 
		button.setIcon(new ImageIcon("C:\\Users\\mansw\\Desktop\\image\\\uCFE0\uD3F0resize1.jpg"));
		button.setBounds(0, 393, 290, 257);
		panel.add(button);
		// 맨위에 라벨 설정
		JLabel lblNewLabel = new JLabel("\uACB0\uC81C\uBC29\uC2DD\uC744 \uC120\uD0DD\uD574 \uC8FC\uC138\uC694.");
		lblNewLabel.setFont(new Font("배달의민족 도현", Font.BOLD, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setBounds(0, 0, 579, 55);
		panel.add(lblNewLabel);
	}
}
