package admin;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JList;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class MasterPage extends JFrame {

	private JPanel orderstatPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MasterPage frame = new MasterPage();
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
	public String ordData[][] = new String[10][3];
	private String[] ordCol = {"번호    ","주문메뉴   ","포장여부"};

	MasterPage() {
		setBounds(100, 100, 648, 615);
		setTitle("롯데리아 학익점 주문확인 페이지");
		orderstatPane = new JPanel();
		orderstatPane.setBackground(Color.WHITE);
		orderstatPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(orderstatPane);
		orderstatPane.setLayout(null);
		
		
		JLabel lblNewLabel = new JLabel("Order stat");
		lblNewLabel.setFont(new Font("배달의민족 도현", Font.PLAIN, 30));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 0, 630, 54);
		orderstatPane.add(lblNewLabel);
		
		JButton OrdSelect = new JButton("\uD310\uB9E4\uB0B4\uC5ED\uC870\uD68C");
		OrdSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Order_result();
			}
		});
		OrdSelect.setBounds(473, 57, 123, 27);
		orderstatPane.add(OrdSelect);
		
		JButton LogoutBtn = new JButton("\uB85C\uADF8\uC544\uC6C3");
		LogoutBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new start();
				dispose();
			}
		});
		LogoutBtn.setBounds(473, 27, 123, 27);
		orderstatPane.add(LogoutBtn);
	}
}
