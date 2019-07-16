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
import javax.swing.JSeparator;
import javax.swing.JList;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import main.Order;
import main.OrderViewer;

public class Order_Confirm extends JFrame {
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Order_Confirm frame = new Order_Confirm();
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
	
	Order_Confirm() {
		setBounds(100, 100, 648, 615);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Order stat");
		lblNewLabel.setFont(new Font("배달의민족 도현", Font.PLAIN, 30));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 0, 630, 54);
		contentPane.add(lblNewLabel);
		
		JButton OrdSelect = new JButton("\uD310\uB9E4\uB0B4\uC5ED\uC870\uD68C");
		OrdSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Order_result();
			}
		});
		OrdSelect.setBounds(473, 57, 123, 27);
		contentPane.add(OrdSelect);
		
		JButton LogoutBtn = new JButton("\uB85C\uADF8\uC544\uC6C3");
		LogoutBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new start();
				dispose();
			}
		});
		LogoutBtn.setBounds(473, 27, 123, 27);
		contentPane.add(LogoutBtn);
		setVisible(true);
	}
}
