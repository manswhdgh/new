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
			@Override // DB ����
			public void windowOpened(WindowEvent arg0) {
				String driver = "oracle.jdbc.driver.OracleDriver";
				String url = "jdbc:oracle:thin:@localhost:1521:xe";
				String user = "lotte";
				String password = "lotte";
				try {
					Class.forName(driver);
					System.out.println("jdbc driver �ε� ����");
					con = DriverManager.getConnection(url, user, password);
					stmt = con.createStatement();  
					System.out.println("����Ŭ ���� ����");
				} catch (ClassNotFoundException aa) {
					System.out.println("jdbc driver �ε� ����");
				} catch (SQLException aa) {
					System.out.println("����Ŭ ���� ����");
				}
			}
		}); 
		// ī��,����,���� ����ȭ�� �г� ���� 
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
		
		//�˾� â ��ü ����
		JOptionPane aa = new JOptionPane();
		
		JButton btnNewButton = new JButton(""); 
		btnNewButton.addActionListener(new ActionListener() {//ī�� Ŭ���� �׼� ���� 
			@Override
			public void actionPerformed(ActionEvent e) {
				//DB ������
				String sql = "update ordresult set �������� = 'ī��' where �ֹ���ȣ = 99";
				int result = 0;
				try {
					st = con.prepareStatement(sql);
					result = st.executeUpdate();
					System.out.println("��ɾ��!");
				} catch (Exception e1) {
					// TODO: handle exception
					e1.printStackTrace();
				}
				new LastPage(); //���ý� ���� �������� �Ѱ��ִ� ���� 
				dispose(); // ������ ������ ����  
			}
		});
		// ī�� ��ư  ���� 
		btnNewButton.setIcon(new ImageIcon("C:\\Users\\mansw\\Desktop\\image\\\uCE74\uB4DC\uACB0\uC81C.jpg"));
		btnNewButton.setBounds(0, 56, 580, 340);
		panel.add(btnNewButton);
		
		// ���� ��ư ���� 
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.addActionListener(new ActionListener() { // ���� Ŭ���� �׼� ���� 
			@Override // DB ������
			public void actionPerformed(ActionEvent e) {
				String sql = "update ordresult set �������� = '����' where �ֹ���ȣ = 99";
				int result = 0;
				try {
					st = con.prepareStatement(sql);
					result = st.executeUpdate();
					System.out.println("��ɾ��!");
				} catch (Exception e1) {
					// TODO: handle exception
					e1.printStackTrace();
				}
				// ���ý� �˾�â���� ��Ÿ���ִ� ����
				aa.showMessageDialog(null,"     ���ݰ����� ī���Ϳ��� �ֹ��ٶ��ϴ�. \n ���ݰ����� ������ �ٷ� ���ɰ����մϴ�.");
				aa.showMessageDialog(null, "�ֹ����α׷��� �����մϴ�. \nī���ͷ� ���ֽñ�ٶ��ϴ�.");
				System.exit(0); // ���α׷� ���� 
			}
		});
		 
		// ����  ��ư  ���� 
		btnNewButton_1.setIcon(new ImageIcon("C:\\Users\\mansw\\Desktop\\image\\\uD604\uAE08.jpg"));
		btnNewButton_1.setBounds(289, 393, 290, 257);
		panel.add(btnNewButton_1);
		
		JButton button = new JButton("");
		button.addActionListener(new ActionListener() { // ���� Ŭ�� �� �׼� ���� 
			@Override // DB ������ 
			public void actionPerformed(ActionEvent arg0) {
				String sql = "update ordresult set �������� = '����' where �ֹ���ȣ = 99";
				int result = 0;
				try {
					st = con.prepareStatement(sql);
					result = st.executeUpdate();
					System.out.println("��ɾ��!");
				} catch (Exception e1) {
					// TODO: handle exception
					e1.printStackTrace();
				}
				// ���ý� �˾�â���� ��Ÿ���ִ� ���� 
				aa.showMessageDialog(null,"     ��������� ī���Ϳ� ���ǹٶ��ϴ�. \n ��������� ������ �ٷ� ���ɰ����մϴ�.");
				aa.showMessageDialog(null, "�ֹ����α׷��� �����մϴ�. \nī���ͷ� ���ֽñ�ٶ��ϴ�.");
				System.exit(0); // ���α׷� ���� 
			}
		});
	   // ���� ��ư  ���� 
		button.setIcon(new ImageIcon("C:\\Users\\mansw\\Desktop\\image\\\uCFE0\uD3F0resize1.jpg"));
		button.setBounds(0, 393, 290, 257);
		panel.add(button);
		// ������ �� ����
		JLabel lblNewLabel = new JLabel("\uACB0\uC81C\uBC29\uC2DD\uC744 \uC120\uD0DD\uD574 \uC8FC\uC138\uC694.");
		lblNewLabel.setFont(new Font("����ǹ��� ����", Font.BOLD, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setBounds(0, 0, 579, 55);
		panel.add(lblNewLabel);
	}
}
