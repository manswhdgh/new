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
	LastPage() { //��ü���� �г� ���� 
		setTitle("Lotte \uD559\uC775\uC810 \uC8FC\uBB38\uC2DC\uC2A4\uD15C");
		setBounds(650, 0, 625, 1020);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// �ֹ� ���� JTextArea ���� 
		LP_ta = new JTextArea();
		LP_ta.setEditable(false);
		LP_ta.setBackground(Color.WHITE);
		LP_ta.setBounds(14, 10, 290, 962);
		LP_ta.setText("        �ֹ�����               �ֹ���ǰ                 ����\n\n");
		//�˾�â ��ü ���� 
		JOptionPane aa = new JOptionPane();
		contentPane.add(LP_ta);
		setVisible(true);
		
		addWindowListener(new WindowAdapter() {
			@Override  // DB���� 
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
					// DB �ֹ� ���� ������ 
					rs = stmt.executeQuery("select * from ORDRESULT where �ֹ���ȣ = 99");
					
					int i = 1;
					while (rs.next()) {// �ֹ����� �� ���� ���� 
						switch (rs.getString(2)) {
						case "��������":
						case "ġ�ƽ":
						case "����̵�":
						case "����Ƣ��":
						case "��¡�":
						case "��䰨��":
						case "ġŲ�ʰ�":
							LP_ta.append("              " + i + "                   " + rs.getString(2)
									+ "                    " + rs.getString(3) + "\n");
							break;
						case "����x2��Ʈ":
							LP_ta.append("              " + i + "                   " + rs.getString(2)
									+ "                " + rs.getString(3) + "\n");
							break;
						case "������Ҽ�Ʈ":
						case "ġ����ż�Ʈ":
							LP_ta.append("              " + i + "                   " + rs.getString(2) + "            "
									+ rs.getString(3) + "\n");
							break;
						case "T-REX��Ʈ":
							LP_ta.append("              " + i + "                   " + rs.getString(2)
									+ "                " + rs.getString(3) + "\n");
							break;
						case "�ѿ쿬����":
							LP_ta.append("              " + i + "                   " + rs.getString(2)
									+ "                " + rs.getString(3) + "\n");
							break;
						case "��ũ������":
						case "��¥������":
						case "�Ƹ޸�ī��":
							LP_ta.append("              " + i + "                   " + rs.getString(2)
									+ "                " + rs.getString(3) + "\n");
							break;
						case "�ؽ�":
							LP_ta.append("              " + i + "                   " + rs.getString(2)
									+ "                            " + rs.getString(3) + "\n");
							break;
						}
					}
				} catch (ClassNotFoundException e) {
					System.out.println("jdbc driver �ε� ����");
				} catch (SQLException e) {
					System.out.println("����Ŭ ���� ����");
				} catch (Exception e) {
					System.err.println("���� �ֱ� ����");
				}
			}
		});
		// ������  �г� ���� 
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(314, 10, 283, 962);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		//Step1.�����ϱ�,����Ļ�_ �� ���� 
		JLabel step1Lbl = new JLabel("Step 1 \uD3EC\uC7A5\uC744 \uC120\uD0DD\uD558\uC138\uC694.");
		step1Lbl.setBounds(12, 10, 233, 15);
		step1Lbl.setBackground(Color.LIGHT_GRAY);
		panel_1.add(step1Lbl);
		//Step1.����Ļ�_ ��ư ���� 
		JButton storeBtn;
		storeBtn = new JButton("");
		//Step.2 ����Ļ� ��ư Ŭ�� �� �׼� ����

		storeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// DB������ 
				String sql = "update ordresult set ���忩�� = 'N' where �ֹ���ȣ = 99";
				int result = 0;
				try {
					st = con.prepareStatement(sql);
					result = st.executeUpdate();
					System.out.println("��ɾ��!");
				} catch (Exception e1) {
					// TODO: handle exception
					e1.printStackTrace();
				} 
				// �˾�â ���� ���� 
				aa.showMessageDialog(null, "\"����\" ����");
			}
		});
		//Step1.����Ļ� ��ư ���� 
		storeBtn.setBackground(Color.WHITE);
		storeBtn.setIcon(new ImageIcon("C:\\Users\\mansw\\Desktop\\image\\\uB2E4\uD68C\uC6A9\uAE30resize.png"));
		storeBtn.setBounds(12, 35, 117, 250);
		panel_1.add(storeBtn);
		
		//Step.2 ����,����Ʈ,���þ���_ �� ���� 
		JLabel step2Lbl = new JLabel("Step 2 \uD560\uC778/\uC801\uB9BD\uC744 \uC120\uD0DD\uD558\uC138\uC694.");
		step2Lbl.setBackground(Color.LIGHT_GRAY);
		step2Lbl.setBounds(12, 324, 233, 35);
		panel_1.add(step2Lbl);
		
		//Step.2 ���� ��ư ���� 
		JButton saleBtn = new JButton("");
		
		//Step.2 ���� ��ư Ŭ�� �� �׼� ����
		saleBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//DB������ 
				String sql = "update ordresult set ���޻����� = 'Y', ����Ʈ��� = 'N' where �ֹ���ȣ = 99";
				int result = 0;
				try {
					st = con.prepareStatement(sql);
					result = st.executeUpdate();
					System.out.println("��ɾ��!");
				} catch (Exception e1) {
					// TODO: handle exception
					e1.printStackTrace();
				}
				//�˾�â ���� ���� 
				aa.showMessageDialog(null, "\"���޻�����\" ����\n���޻����� �Ǵ� ����Ʈ����� �ϳ��� �����մϴ�.");
			}
		});
		//Step.2 ����Ʈ����_��ư ���� 
		saleBtn.setBackground(Color.WHITE);
		saleBtn.setIcon(new ImageIcon("C:\\Users\\mansw\\Desktop\\image\\\uC81C\uD734\uC0AC\uD560\uC778.png"));
		saleBtn.setBounds(12, 369, 78, 193);
		panel_1.add(saleBtn);
		
		//Step2.����Ʈ ����_��ư ���� 
		JButton pointBtn = new JButton("");
		    //Step.2 ����Ʈ ����_��ư Ŭ�� �� �׼� ����
		pointBtn.addActionListener(new ActionListener() {  
			public void actionPerformed(ActionEvent e) {
				//DB������ 
				String sql = "update ordresult set ���޻����� = 'N', ����Ʈ��� = 'Y' where �ֹ���ȣ = 99";
				int result = 0;
				try {
					st = con.prepareStatement(sql);
					result = st.executeUpdate();
					System.out.println("��ɾ��!");
				} catch (Exception e1) {
					// TODO: handle exception
					e1.printStackTrace();
				}
				// �˾�â ���� ���� 
				aa.showMessageDialog(null, "\"����Ʈ ��� �� ����\" ����\n���޻����� �Ǵ� ����Ʈ����� �ϳ��� �����մϴ�.");
			}
		});
		

		pointBtn.setBackground(Color.WHITE);
		pointBtn.setIcon(new ImageIcon("C:\\Users\\mansw\\Desktop\\image\\\uD3EC\uC778\uD2B8\uC0AC\uC6A9\uC801\uB9BD.png"));
		pointBtn.setBounds(93, 369, 79, 193);
		panel_1.add(pointBtn);
		
		//Step2.���þ���_��ư���� 
		JButton notselectBtn = new JButton("");
		   //Step2.���þ���_��ư Ŭ�� �� �׼� ���� 
		notselectBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//DB������ 
				String sql = "update ordresult set ���޻����� = 'N', ����Ʈ��� = 'N' where �ֹ���ȣ = 99";
				int result = 0;
				try {
					st = con.prepareStatement(sql);
					result = st.executeUpdate();
					System.out.println("��ɾ��!");
				} catch (Exception e1) {
					// TODO: handle exception
					e1.printStackTrace();
				}
				//�˾�â ���� 
				aa.showMessageDialog(null,"���ΰ� ����Ʈ�� ��������ʽ��ϴ�.");
			}
		});
		//Step2.���þ���_��ư ���� 
		notselectBtn.setBackground(Color.WHITE);
		notselectBtn.setIcon(new ImageIcon("C:\\Users\\mansw\\Desktop\\image\\\uC120\uD0DD\uC5C6\uC74C.png"));
		notselectBtn.setBounds(173, 369, 78, 193);
		panel_1.add(notselectBtn);
		
		//Step3.�����ϱ�,���_�� ����  
		JLabel step3Lbl = new JLabel("Step 3 \uACB0\uC81C\uB97C \uD655\uC778/\uCDE8\uC18C\uD558\uC138\uC694.");
		step3Lbl.setBackground(Color.LIGHT_GRAY);
		step3Lbl.setBounds(12, 591, 233, 35);
		panel_1.add(step3Lbl);
		
		//Step3.�����ϱ�_��ư ����
		JButton PayConfirmBtn = new JButton("");
		//Step3.�����ϱ�_��ư Ŭ�� �� �׼� ����
		PayConfirmBtn.addActionListener(new ActionListener() {
			int ordNum = 19060101; // �ֹ���ȣ
			@Override
			public void actionPerformed(ActionEvent e) {
				//DB������ 
				String sql = "update ordresult set �ֹ���ȣ = " + ordNum + " where �ֹ���ȣ = 99";
				int result = 0;
				try {
					st = con.prepareStatement(sql);
					result = st.executeUpdate();
					System.out.println("��ɾ��!");
				} catch (Exception e1) {
					// TODO: handle exception
					e1.printStackTrace();
				}
				ordNum++;
				aa.showMessageDialog(null, "            �ֹ��� �Ϸ� �Ǿ����ϴ�. \n������ �غ� �Ǹ� �˷��帮�ڽ��ϴ�.");
				// �˾�â ���� ���� 
				// ����ȣ�� ���� JFrame ȣ��
				
				System.exit(0); //���α׷� ���� 
			}
		});
		//Step3.�����ϱ�_��ư ����
		PayConfirmBtn.setBackground(Color.WHITE);
		PayConfirmBtn.setIcon(new ImageIcon("C:\\Users\\mansw\\Desktop\\image\\\uACB0\uC81C\uD655\uC778resize.png"));
		PayConfirmBtn.setBounds(12, 636, 117, 296);
		panel_1.add(PayConfirmBtn);
		
		//Step3.�����ϱ�_��ư ����
		JButton takeoutBtn = new JButton("");
		//Step3.�����ϱ�_��ư Ŭ�� �� �׼� ���� 
		takeoutBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// DB������ 
				String sql = "update ordresult set ���忩�� = 'Y' where �ֹ���ȣ = 99";
				int result = 0;
				try {
					st = con.prepareStatement(sql);
					result = st.executeUpdate();
					System.out.println("��ɾ��!");
				} catch (Exception e1) {
					// TODO: handle exception
					e1.printStackTrace();
				}
				//�˾�â ���� ���� 
				aa.showMessageDialog(null, "\"����\" ����");
			}
		}); 
		//Step1.�����ϱ�_��ư ����
		takeoutBtn.setBackground(Color.WHITE);
		takeoutBtn.setIcon(new ImageIcon("C:\\Users\\mansw\\Desktop\\image\\\uD3EC\uC7A5resize.png"));
		takeoutBtn.setBounds(141, 35, 117, 250);
		panel_1.add(takeoutBtn);
		
		//Step3.����ϱ�_��ư ���� 
		JButton PayDiscardBtn = new JButton("");
			//Step3.����ϱ�_��ư Ŭ�� �� �׼� ���� 
		PayDiscardBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//DB������ 
				String sql = "delete ordresult where �ֹ���ȣ = 99";
				int result = 0;
				try {
					st = con.prepareStatement(sql);
					result = st.executeUpdate();
					System.out.println("��ɾ��!");
				} catch (Exception e1) {
					// TODO: handle exception
					e1.printStackTrace();
				}
				// �˾�â ���� ����  
				aa.showMessageDialog(null, "�ֹ��� ��ҵǾ����ϴ�.");
				aa.showMessageDialog(null, "ó������ �ٽ� �ֹ����ּ���.");
				System.exit(0); // ���α׷� ���� 
			}
		});
		//Step3.����ϱ�_��ư ���� 

		PayDiscardBtn.setBackground(Color.WHITE);
		PayDiscardBtn.setIcon(new ImageIcon("C:\\Users\\mansw\\Desktop\\image\\\uACB0\uC81C\uCDE8\uC18Cresize.png"));
		PayDiscardBtn.setBounds(134, 636, 117, 296);
		panel_1.add(PayDiscardBtn);

	}
}
