package main;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
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
import javax.swing.JTextArea;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class LastPage extends JFrame {

	private JPanel contentPane;
	JTextArea LP_ta;
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
					LastPage frame = new LastPage(); 
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
	LastPage() { //전체적인 패널 설정 
		setTitle("Lotte \uD559\uC775\uC810 \uC8FC\uBB38\uC2DC\uC2A4\uD15C");
		setBounds(650, 0, 625, 1020);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// 주문 내역 JTextArea 설정 
		LP_ta = new JTextArea();
		LP_ta.setEditable(false);
		LP_ta.setBackground(Color.WHITE);
		LP_ta.setBounds(14, 10, 290, 962);
		LP_ta.setText("        주문순번               주문물품                 가격\n\n");
		//팝업창 객체 생성 
		JOptionPane aa = new JOptionPane();
		contentPane.add(LP_ta);
		setVisible(true);
		
		addWindowListener(new WindowAdapter() {
			@Override  // DB연동 
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
					// DB 주문 내역 쿼리문 
					rs = stmt.executeQuery("select * from ORDRESULT where 주문번호 = 99");
					
					int i = 1;
					while (rs.next()) {// 주문내역 줄 간격 설정 
						switch (rs.getString(2)) {
						case "데리버거":
						case "치즈스틱":
						case "토네이도":
						case "감자튀김":
						case "오징어링":
						case "양념감자":
						case "치킨너겟":
							LP_ta.append("              " + i + "                   " + rs.getString(2)
									+ "                    " + rs.getString(3) + "\n");
							break;
						case "더블x2세트":
							LP_ta.append("              " + i + "                   " + rs.getString(2)
									+ "                " + rs.getString(3) + "\n");
							break;
						case "원조빅불세트":
						case "치즈버거세트":
							LP_ta.append("              " + i + "                   " + rs.getString(2) + "            "
									+ rs.getString(3) + "\n");
							break;
						case "T-REX세트":
							LP_ta.append("              " + i + "                   " + rs.getString(2)
									+ "                " + rs.getString(3) + "\n");
							break;
						case "한우연인팩":
							LP_ta.append("              " + i + "                   " + rs.getString(2)
									+ "                " + rs.getString(3) + "\n");
							break;
						case "핫크리스피":
						case "모짜더블팩":
						case "아메리카노":
							LP_ta.append("              " + i + "                   " + rs.getString(2)
									+ "                " + rs.getString(3) + "\n");
							break;
						case "해쉬":
							LP_ta.append("              " + i + "                   " + rs.getString(2)
									+ "                            " + rs.getString(3) + "\n");
							break;
						}
					}
				} catch (ClassNotFoundException e) {
					System.out.println("jdbc driver 로딩 실패");
				} catch (SQLException e) {
					System.out.println("오라클 연결 실패");
				} catch (Exception e) {
					System.err.println("보여 주기 에러");
				}
			}
		});
		// 오른쪽  패널 설정 
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(314, 10, 283, 962);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		//Step1.포장하기,매장식사_ 라벨 설정 
		JLabel step1Lbl = new JLabel("Step 1 \uD3EC\uC7A5\uC744 \uC120\uD0DD\uD558\uC138\uC694.");
		step1Lbl.setBounds(12, 10, 233, 15);
		step1Lbl.setBackground(Color.LIGHT_GRAY);
		panel_1.add(step1Lbl);
		//Step1.매장식사_ 버튼 설정 
		JButton storeBtn;
		storeBtn = new JButton("");
		//Step.2 매장식사 버튼 클릭 시 액션 설정

		storeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// DB쿼리문 
				String sql = "update ordresult set 포장여부 = 'N' where 주문번호 = 99";
				int result = 0;
				try {
					st = con.prepareStatement(sql);
					result = st.executeUpdate();
					System.out.println("명령어성공!");
				} catch (Exception e1) {
					// TODO: handle exception
					e1.printStackTrace();
				} 
				// 팝업창 내용 설정 
				aa.showMessageDialog(null, "\"매장\" 선택");
			}
		});
		//Step1.매장식사 버튼 설정 
		storeBtn.setBackground(Color.WHITE);
		storeBtn.setIcon(new ImageIcon("C:\\Users\\mansw\\Desktop\\image\\\uB2E4\uD68C\uC6A9\uAE30resize.png"));
		storeBtn.setBounds(12, 35, 117, 250);
		panel_1.add(storeBtn);
		
		//Step.2 할인,포인트,선택없음_ 라벨 설정 
		JLabel step2Lbl = new JLabel("Step 2 \uD560\uC778/\uC801\uB9BD\uC744 \uC120\uD0DD\uD558\uC138\uC694.");
		step2Lbl.setBackground(Color.LIGHT_GRAY);
		step2Lbl.setBounds(12, 324, 233, 35);
		panel_1.add(step2Lbl);
		
		//Step.2 할인 버튼 설정 
		JButton saleBtn = new JButton("");
		
		//Step.2 할인 버튼 클릭 시 액션 설정
		saleBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//DB쿼리문 
				String sql = "update ordresult set 제휴사할인 = 'Y', 포인트사용 = 'N' where 주문번호 = 99";
				int result = 0;
				try {
					st = con.prepareStatement(sql);
					result = st.executeUpdate();
					System.out.println("명령어성공!");
				} catch (Exception e1) {
					// TODO: handle exception
					e1.printStackTrace();
				}
				//팝업창 내용 설정 
				aa.showMessageDialog(null, "\"제휴사할인\" 선택\n제휴사할인 또는 포인트사용은 하나만 가능합니다.");
			}
		});
		//Step.2 포인트적립_버튼 설정 
		saleBtn.setBackground(Color.WHITE);
		saleBtn.setIcon(new ImageIcon("C:\\Users\\mansw\\Desktop\\image\\\uC81C\uD734\uC0AC\uD560\uC778.png"));
		saleBtn.setBounds(12, 369, 78, 193);
		panel_1.add(saleBtn);
		
		//Step2.포인트 적립_버튼 설정 
		JButton pointBtn = new JButton("");
		    //Step.2 포인트 적립_버튼 클릭 시 액션 설정
		pointBtn.addActionListener(new ActionListener() {  
			public void actionPerformed(ActionEvent e) {
				//DB쿼리문 
				String sql = "update ordresult set 제휴사할인 = 'N', 포인트사용 = 'Y' where 주문번호 = 99";
				int result = 0;
				try {
					st = con.prepareStatement(sql);
					result = st.executeUpdate();
					System.out.println("명령어성공!");
				} catch (Exception e1) {
					// TODO: handle exception
					e1.printStackTrace();
				}
				// 팝업창 내용 설정 
				aa.showMessageDialog(null, "\"포인트 사용 및 적립\" 선택\n제휴사할인 또는 포인트사용은 하나만 가능합니다.");
			}
		});
		

		pointBtn.setBackground(Color.WHITE);
		pointBtn.setIcon(new ImageIcon("C:\\Users\\mansw\\Desktop\\image\\\uD3EC\uC778\uD2B8\uC0AC\uC6A9\uC801\uB9BD.png"));
		pointBtn.setBounds(93, 369, 79, 193);
		panel_1.add(pointBtn);
		
		//Step2.선택없음_버튼설정 
		JButton notselectBtn = new JButton("");
		   //Step2.선택없음_버튼 클릭 시 액션 설정 
		notselectBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//DB쿼리문 
				String sql = "update ordresult set 제휴사할인 = 'N', 포인트사용 = 'N' where 주문번호 = 99";
				int result = 0;
				try {
					st = con.prepareStatement(sql);
					result = st.executeUpdate();
					System.out.println("명령어성공!");
				} catch (Exception e1) {
					// TODO: handle exception
					e1.printStackTrace();
				}
				//팝업창 설정 
				aa.showMessageDialog(null,"할인과 포인트를 사용하지않습니다.");
			}
		});
		//Step2.선택없음_버튼 설정 
		notselectBtn.setBackground(Color.WHITE);
		notselectBtn.setIcon(new ImageIcon("C:\\Users\\mansw\\Desktop\\image\\\uC120\uD0DD\uC5C6\uC74C.png"));
		notselectBtn.setBounds(173, 369, 78, 193);
		panel_1.add(notselectBtn);
		
		//Step3.결제하기,취소_라벨 설정  
		JLabel step3Lbl = new JLabel("Step 3 \uACB0\uC81C\uB97C \uD655\uC778/\uCDE8\uC18C\uD558\uC138\uC694.");
		step3Lbl.setBackground(Color.LIGHT_GRAY);
		step3Lbl.setBounds(12, 591, 233, 35);
		panel_1.add(step3Lbl);
		
		//Step3.결제하기_버튼 설정
		JButton PayConfirmBtn = new JButton("");
		//Step3.결제하기_버튼 클릭 시 액션 설정
		PayConfirmBtn.addActionListener(new ActionListener() {
			int ordNum = 19060101; // 주문번호
			@Override
			public void actionPerformed(ActionEvent e) {
				//DB쿼리문 
				String sql = "update ordresult set 주문번호 = " + ordNum + " where 주문번호 = 99";
				int result = 0;
				try {
					st = con.prepareStatement(sql);
					result = st.executeUpdate();
					System.out.println("명령어성공!");
				} catch (Exception e1) {
					// TODO: handle exception
					e1.printStackTrace();
				}
				ordNum++;
				aa.showMessageDialog(null, "            주문이 완료 되었습니다. \n음식이 준비가 되면 알려드리겠습니다.");
				// 팝업창 설정 내용 
				// 대기번호가 적힌 JFrame 호출
				
				System.exit(0); //프로그램 종료 
			}
		});
		//Step3.결제하기_버튼 설정
		PayConfirmBtn.setBackground(Color.WHITE);
		PayConfirmBtn.setIcon(new ImageIcon("C:\\Users\\mansw\\Desktop\\image\\\uACB0\uC81C\uD655\uC778resize.png"));
		PayConfirmBtn.setBounds(12, 636, 117, 296);
		panel_1.add(PayConfirmBtn);
		
		//Step3.포장하기_버튼 설정
		JButton takeoutBtn = new JButton("");
		//Step3.포장하기_버튼 클릭 시 액션 설정 
		takeoutBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// DB쿼리문 
				String sql = "update ordresult set 포장여부 = 'Y' where 주문번호 = 99";
				int result = 0;
				try {
					st = con.prepareStatement(sql);
					result = st.executeUpdate();
					System.out.println("명령어성공!");
				} catch (Exception e1) {
					// TODO: handle exception
					e1.printStackTrace();
				}
				//팝업창 내용 설정 
				aa.showMessageDialog(null, "\"포장\" 선택");
			}
		}); 
		//Step1.포장하기_버튼 설정
		takeoutBtn.setBackground(Color.WHITE);
		takeoutBtn.setIcon(new ImageIcon("C:\\Users\\mansw\\Desktop\\image\\\uD3EC\uC7A5resize.png"));
		takeoutBtn.setBounds(141, 35, 117, 250);
		panel_1.add(takeoutBtn);
		
		//Step3.취소하기_버튼 설정 
		JButton PayDiscardBtn = new JButton("");
			//Step3.취소하기_버튼 클릭 시 액션 설정 
		PayDiscardBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//DB쿼리문 
				String sql = "delete ordresult where 주문번호 = 99";
				int result = 0;
				try {
					st = con.prepareStatement(sql);
					result = st.executeUpdate();
					System.out.println("명령어성공!");
				} catch (Exception e1) {
					// TODO: handle exception
					e1.printStackTrace();
				}
				// 팝업창 내용 설정  
				aa.showMessageDialog(null, "주문이 취소되었습니다.");
				aa.showMessageDialog(null, "처음부터 다시 주문해주세요.");
				System.exit(0); // 프로그램 종료 
			}
		});
		//Step3.취소하기_버튼 설정 

		PayDiscardBtn.setBackground(Color.WHITE);
		PayDiscardBtn.setIcon(new ImageIcon("C:\\Users\\mansw\\Desktop\\image\\\uACB0\uC81C\uCDE8\uC18Cresize.png"));
		PayDiscardBtn.setBounds(134, 636, 117, 296);
		panel_1.add(PayDiscardBtn);

	}
}
