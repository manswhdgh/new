package admin;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class start extends JFrame implements ActionListener {
	private String id = "lotte";
	private String password = "lotte";
	private JTextField tf;
	private JPasswordField pf;
	private JButton login;
	JLabel loginText = new JLabel();
	private boolean isLogin = false;

	public start() {
		 // 로그인 창 패널설정 
		JPanel idPanel = new JPanel();
		JPanel passPanel = new JPanel();
		tf = new JTextField(12);
		pf = new JPasswordField(10);
		loginText.setForeground(Color.RED);
		//로그인 창 아이디,비밀번호 라벨 설정 
		JLabel idLabel = new JLabel("ID : ");
		JLabel passLabel = new JLabel("PASSWORD : ");
		login = new JButton("LOGIN");
		login.addActionListener(this);

		idPanel.add(idLabel);
		idPanel.add(tf);

		passPanel.add(passLabel);
		passPanel.add(pf);

		this.add(idPanel);
		this.add(passPanel);
		this.add(login);
		this.add(loginText);
		//컴포넌트 설정 
		setLayout(new FlowLayout());

		setTitle("롯데리아 학익점 주문관리");
		setSize(300, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setVisible(true);
	}

	@Override // 로그인 클릭시 이벤트 설정 
	public void actionPerformed(ActionEvent e) { 
		if (e.getSource() == login) {
			try {
				if (id.equals(tf.getText()) && password.equals(pf.getText()))
					isLogin = true;
				else
					isLogin = false;
				if (isLogin) {
					loginText.setText("로그인되었습니다. 관리자님 안녕하세요.");// 로그인 성공시 실행할 루틴
					new Order_result();
					dispose();
				} else {
					loginText.setText("ID 또는 password가 잘못되었습니다.");// 로그인 실패시 띄울 문장
				}
			} catch (Exception e1) {// 특수한 경우일경우 실행될 사용자정의예외처리문
				System.out.println("false");
			}
		}
	}

	public static void main(String[] args) {
		// 로그인창을 띄우는 메인 메소드
		start my = new start();
	}
}
