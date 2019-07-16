package main;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.event.WindowFocusListener;

public class Order extends JFrame {

	int count = 0; 
	String show = "";
	private Connection con;
	private Statement stmt;
	PreparedStatement st = null;
	
	public Order() {
		// 메뉴 화면 프레임 설정
		JFrame frmLotte1 = new JFrame("햄버거 자동 판매기");
		frmLotte1.addWindowFocusListener(new WindowFocusListener() {
			public void windowGainedFocus(WindowEvent arg0) {
			}
			public void windowLostFocus(WindowEvent arg0) {
			}
		});
		// DB연동
			frmLotte1.addWindowListener(new WindowAdapter() {
			@Override
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
		frmLotte1.setTitle("Lotte \uD559\uC775\uC810 \uC8FC\uBB38\uC2DC\uC2A4\uD15C");
		frmLotte1.setBounds(0, 0, 625, 1020);
		frmLotte1.setBackground(Color.black);
		// 폰트
		Font font = new Font(Font.SANS_SERIF, Font.PLAIN, 15);
		
		// 메뉴화면 패널 설정
		Panel pNorth = new Panel();
		pNorth.setBackground(new Color(255, 255, 215));
		pNorth.setLayout(null);
		pNorth.setSize(0, 1000);
		pNorth.setFont(font);

		// 배열(메뉴,가격)설정 부분
		String menu[] = { "데리버거", "더블x2세트", "T-REX세트", "핫크리스피", "한우연인팩", "치즈버거세트", "원조빅불세트", "모짜더블팩", "치즈스틱", "아메리카노",
				"토네이도", "감자튀김", "해쉬", "오징어링", "양념감자", "치킨너겟" };
		int price[] = { 4800, 6800, 7500, 8000, 9500, 6500, 6000, 7500, 2000, 2000, 1500, 1500, 1000, 2500, 1000,
				1000 };
		JButton bt[] = new JButton[menu.length];
		TextField suja[] = new TextField[menu.length];//숫자 Text
		Button minus[] = new Button[menu.length]; // 마이너스 버튼
		Button plus[] = new Button[menu.length]; //플러스 버튼
		JButton ok[] = new JButton[menu.length];//확인버튼
		Label l[] = new Label[menu.length]; // 메뉴가격 
		Label p[] = new Label[menu.length];// 메뉴이름
		ImageIcon icon[] = new ImageIcon[menu.length]; // 이미지 
		OrderViewer ordv = new OrderViewer(); // 주문내역 페이지 
		LastPage lp = new LastPage(); // 결제 페이지 
		lp.setVisible(false);  
		
		// 버튼 설정 
		for (int i = 0; i < menu.length; i++) {
			// 햄버거 버튼  설정
			bt[i] = new JButton(menu[i]);
			if (i < 4) {
				bt[i].setBounds(25 + i * 150, 25, 100, 100);
			} else if (i < 8) {
				bt[i].setBounds(25 + (i - 4) * 150, 265, 100, 100);
			} else if (i < 12) {
				bt[i].setBounds(25 + (i - 8) * 150, 510, 100, 100);
			} else {
				bt[i].setBounds(25 + (i - 12) * 150, 750, 100, 100);
			}

			icon[i] = new ImageIcon(i + ".png");

			bt[i].setIcon(icon[i]);

			// txt 버튼  
			suja[i] = new TextField("0");
			suja[i].setBackground(Color.white);
			suja[i].setEditable(true);
			suja[i].setBounds(bt[i].getX() + 30, bt[i].getY() + 160, 40, 20);

			// "-" 버튼 
			minus[i] = new Button("-");
			minus[i].setBounds(bt[i].getX(), suja[i].getY(), 20, 20);
			minus[i].setEnabled(true);

			// "+" 버튼 
			plus[i] = new Button("+");
			plus[i].setBounds(bt[i].getX() + (100 - 20), suja[i].getY(), 20, 20);
			plus[i].setEnabled(true);

			// 메뉴이름 

			p[i] = new Label(menu[i], Label.CENTER);
			p[i].setBounds(bt[i].getX() + 0, suja[i].getY() - 45, 100, 20);

			// 메뉴가격 
			l[i] = new Label(price[i] + "원");
			l[i].setBounds(bt[i].getX() + 25, suja[i].getY() - 25, 100, 20);

			// 확인버튼
			ok[i] = new JButton("확인");
			ok[i].setBounds(bt[i].getX(), suja[i].getY() + 30, 100, 20);
			ok[i].setEnabled(false);
			pNorth.add(bt[i]);
			pNorth.add(suja[i]);
			pNorth.add(minus[i]);
			pNorth.add(plus[i]);
			pNorth.add(l[i]);
			pNorth.add(ok[i]);
			pNorth.add(p[i]);
		}
		// 햄버거 아이콘 이미지 추가
		for (int i = 0; i < menu.length; i++) {
			bt[i].setIcon(new ImageIcon("C:\\Users\\mansw\\Desktop\\image\\" + i + ".JPG"));
		}

		// 컴포넌트 
		frmLotte1.getContentPane().add(pNorth);
		frmLotte1.setVisible(true);
		
		
		// 버튼 클릭 시 이벤트  
		for (int i = 0; i < menu.length; i++) {
			int j = i;

			// 햄버거 버튼 이벤트
			bt[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					minus[j].setEnabled(true);
					plus[j].setEnabled(true);
					bt[j].setEnabled(false);
					ok[j].setEnabled(true);
					count = 0;
					System.out.println(bt[j].getActionCommand());
				}
			});
			// "-" 버튼 이벤트
			minus[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (count > 0) {
						count = count - 1;
						suja[j].setText(count + "");
						ok[j].setEnabled(true);
					} else {
						minus[j].setEnabled(false);
					}
				}
			});
			// "+" 버튼 이벤트
			plus[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					count = count + 1;
					suja[j].setText(count + "");
					ok[j].setEnabled(true);
					if (count > 0) {
						minus[j].setEnabled(true);
					}
				}
			});
			// 확인 버튼 이벤트
			int val = 99;
			ok[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					show = bt[j].getActionCommand();
					// DB쿼리문	
					String sql = "insert into ordresult(주문번호,메뉴,수량,가격) values('" + val + "','" + show + "','" + count + "','"
							+ price[j] * count + "')";
					int cntbtn = count;
					int ordnum = 1;
					
					// 주문내역 줄 간격  설정
					switch (show) {
					case "데리버거":
					case "치즈스틱":
					case "토네이도":
					case "감자튀김":
					case "오징어링":
					case "양념감자":
					case "치킨너겟":
						ordv.ta.append(" " + show + "        " + price[j] + "         " + count + "        "
								+ price[j] * count + "원" + "\n");
						break;
					case "더블x2세트":
						ordv.ta.append(" " + show + "      " + price[j] + "         " + count + "        "
								+ price[j] * count + "원" + "\n");
						break;
					case "원조빅불세트":
					case "치즈버거세트":
						ordv.ta.append(" " + show + "    " + price[j] + "         " + count + "        "
								+ price[j] * count + "원" + "\n");
						break;
					case "T-REX세트":
						ordv.ta.append(" " + show + "       " + price[j] + "         " + count + "        "
								+ price[j] * count + "원" + "\n");
						break;

					case "한우연인팩":
						ordv.ta.append(" " + show + "     " + price[j] + "         " + count + "        "
								+ price[j] * count + "원" + "\n");
						break;
					case "핫크리스피":
					case "모짜더블팩":
					case "아메리카노":
						ordv.ta.append(" " + show + "      " + price[j] + "         " + count + "        "
								+ price[j] * count + "원" + "\n");
						break;
					case "해쉬":
						ordv.ta.append(" " + show + "            " + price[j] + "         " + count + "        "
								+ price[j] * count + "원" + "\n");
						break;
					}
					int val = 19060101;
					int result = 0;
					try {
						st = con.prepareStatement(sql);
						result = st.executeUpdate();
						System.out.println("명령어성공!");
					} catch (Exception e1) {
						// TODO: handle exception
						e1.printStackTrace();
					}
					ok[j].setEnabled(false);
				}
			});

			// 끄기
			frmLotte1.addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent e) {
					frmLotte1.dispose();
				}
			});
		}
	}

	// 메인
	public static void main(String[] args) {
		new Order();
	}
}
