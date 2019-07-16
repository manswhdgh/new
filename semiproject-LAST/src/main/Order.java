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
		// �޴� ȭ�� ������ ����
		JFrame frmLotte1 = new JFrame("�ܹ��� �ڵ� �Ǹű�");
		frmLotte1.addWindowFocusListener(new WindowFocusListener() {
			public void windowGainedFocus(WindowEvent arg0) {
			}
			public void windowLostFocus(WindowEvent arg0) {
			}
		});
		// DB����
			frmLotte1.addWindowListener(new WindowAdapter() {
			@Override
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
		frmLotte1.setTitle("Lotte \uD559\uC775\uC810 \uC8FC\uBB38\uC2DC\uC2A4\uD15C");
		frmLotte1.setBounds(0, 0, 625, 1020);
		frmLotte1.setBackground(Color.black);
		// ��Ʈ
		Font font = new Font(Font.SANS_SERIF, Font.PLAIN, 15);
		
		// �޴�ȭ�� �г� ����
		Panel pNorth = new Panel();
		pNorth.setBackground(new Color(255, 255, 215));
		pNorth.setLayout(null);
		pNorth.setSize(0, 1000);
		pNorth.setFont(font);

		// �迭(�޴�,����)���� �κ�
		String menu[] = { "��������", "����x2��Ʈ", "T-REX��Ʈ", "��ũ������", "�ѿ쿬����", "ġ����ż�Ʈ", "������Ҽ�Ʈ", "��¥������", "ġ�ƽ", "�Ƹ޸�ī��",
				"����̵�", "����Ƣ��", "�ؽ�", "��¡�", "��䰨��", "ġŲ�ʰ�" };
		int price[] = { 4800, 6800, 7500, 8000, 9500, 6500, 6000, 7500, 2000, 2000, 1500, 1500, 1000, 2500, 1000,
				1000 };
		JButton bt[] = new JButton[menu.length];
		TextField suja[] = new TextField[menu.length];//���� Text
		Button minus[] = new Button[menu.length]; // ���̳ʽ� ��ư
		Button plus[] = new Button[menu.length]; //�÷��� ��ư
		JButton ok[] = new JButton[menu.length];//Ȯ�ι�ư
		Label l[] = new Label[menu.length]; // �޴����� 
		Label p[] = new Label[menu.length];// �޴��̸�
		ImageIcon icon[] = new ImageIcon[menu.length]; // �̹��� 
		OrderViewer ordv = new OrderViewer(); // �ֹ����� ������ 
		LastPage lp = new LastPage(); // ���� ������ 
		lp.setVisible(false);  
		
		// ��ư ���� 
		for (int i = 0; i < menu.length; i++) {
			// �ܹ��� ��ư  ����
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

			// txt ��ư  
			suja[i] = new TextField("0");
			suja[i].setBackground(Color.white);
			suja[i].setEditable(true);
			suja[i].setBounds(bt[i].getX() + 30, bt[i].getY() + 160, 40, 20);

			// "-" ��ư 
			minus[i] = new Button("-");
			minus[i].setBounds(bt[i].getX(), suja[i].getY(), 20, 20);
			minus[i].setEnabled(true);

			// "+" ��ư 
			plus[i] = new Button("+");
			plus[i].setBounds(bt[i].getX() + (100 - 20), suja[i].getY(), 20, 20);
			plus[i].setEnabled(true);

			// �޴��̸� 

			p[i] = new Label(menu[i], Label.CENTER);
			p[i].setBounds(bt[i].getX() + 0, suja[i].getY() - 45, 100, 20);

			// �޴����� 
			l[i] = new Label(price[i] + "��");
			l[i].setBounds(bt[i].getX() + 25, suja[i].getY() - 25, 100, 20);

			// Ȯ�ι�ư
			ok[i] = new JButton("Ȯ��");
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
		// �ܹ��� ������ �̹��� �߰�
		for (int i = 0; i < menu.length; i++) {
			bt[i].setIcon(new ImageIcon("C:\\Users\\mansw\\Desktop\\image\\" + i + ".JPG"));
		}

		// ������Ʈ 
		frmLotte1.getContentPane().add(pNorth);
		frmLotte1.setVisible(true);
		
		
		// ��ư Ŭ�� �� �̺�Ʈ  
		for (int i = 0; i < menu.length; i++) {
			int j = i;

			// �ܹ��� ��ư �̺�Ʈ
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
			// "-" ��ư �̺�Ʈ
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
			// "+" ��ư �̺�Ʈ
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
			// Ȯ�� ��ư �̺�Ʈ
			int val = 99;
			ok[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					show = bt[j].getActionCommand();
					// DB������	
					String sql = "insert into ordresult(�ֹ���ȣ,�޴�,����,����) values('" + val + "','" + show + "','" + count + "','"
							+ price[j] * count + "')";
					int cntbtn = count;
					int ordnum = 1;
					
					// �ֹ����� �� ����  ����
					switch (show) {
					case "��������":
					case "ġ�ƽ":
					case "����̵�":
					case "����Ƣ��":
					case "��¡�":
					case "��䰨��":
					case "ġŲ�ʰ�":
						ordv.ta.append(" " + show + "        " + price[j] + "         " + count + "        "
								+ price[j] * count + "��" + "\n");
						break;
					case "����x2��Ʈ":
						ordv.ta.append(" " + show + "      " + price[j] + "         " + count + "        "
								+ price[j] * count + "��" + "\n");
						break;
					case "������Ҽ�Ʈ":
					case "ġ����ż�Ʈ":
						ordv.ta.append(" " + show + "    " + price[j] + "         " + count + "        "
								+ price[j] * count + "��" + "\n");
						break;
					case "T-REX��Ʈ":
						ordv.ta.append(" " + show + "       " + price[j] + "         " + count + "        "
								+ price[j] * count + "��" + "\n");
						break;

					case "�ѿ쿬����":
						ordv.ta.append(" " + show + "     " + price[j] + "         " + count + "        "
								+ price[j] * count + "��" + "\n");
						break;
					case "��ũ������":
					case "��¥������":
					case "�Ƹ޸�ī��":
						ordv.ta.append(" " + show + "      " + price[j] + "         " + count + "        "
								+ price[j] * count + "��" + "\n");
						break;
					case "�ؽ�":
						ordv.ta.append(" " + show + "            " + price[j] + "         " + count + "        "
								+ price[j] * count + "��" + "\n");
						break;
					}
					int val = 19060101;
					int result = 0;
					try {
						st = con.prepareStatement(sql);
						result = st.executeUpdate();
						System.out.println("��ɾ��!");
					} catch (Exception e1) {
						// TODO: handle exception
						e1.printStackTrace();
					}
					ok[j].setEnabled(false);
				}
			});

			// ����
			frmLotte1.addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent e) {
					frmLotte1.dispose();
				}
			});
		}
	}

	// ����
	public static void main(String[] args) {
		new Order();
	}
}
