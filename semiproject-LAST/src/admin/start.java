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
		 // �α��� â �гμ��� 
		JPanel idPanel = new JPanel();
		JPanel passPanel = new JPanel();
		tf = new JTextField(12);
		pf = new JPasswordField(10);
		loginText.setForeground(Color.RED);
		//�α��� â ���̵�,��й�ȣ �� ���� 
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
		//������Ʈ ���� 
		setLayout(new FlowLayout());

		setTitle("�Ե����� ������ �ֹ�����");
		setSize(300, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setVisible(true);
	}

	@Override // �α��� Ŭ���� �̺�Ʈ ���� 
	public void actionPerformed(ActionEvent e) { 
		if (e.getSource() == login) {
			try {
				if (id.equals(tf.getText()) && password.equals(pf.getText()))
					isLogin = true;
				else
					isLogin = false;
				if (isLogin) {
					loginText.setText("�α��εǾ����ϴ�. �����ڴ� �ȳ��ϼ���.");// �α��� ������ ������ ��ƾ
					new Order_result();
					dispose();
				} else {
					loginText.setText("ID �Ǵ� password�� �߸��Ǿ����ϴ�.");// �α��� ���н� ��� ����
				}
			} catch (Exception e1) {// Ư���� ����ϰ�� ����� ��������ǿ���ó����
				System.out.println("false");
			}
		}
	}

	public static void main(String[] args) {
		// �α���â�� ���� ���� �޼ҵ�
		start my = new start();
	}
}
