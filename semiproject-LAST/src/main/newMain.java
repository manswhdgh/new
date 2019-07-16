package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class newMain extends JFrame {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					newMain frame2 = new newMain();
					frame2.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public newMain() { // 맨 처음 메인 화면 설정 
		setTitle("\uB86F\uB370\uB9AC\uC544 \uD559\uC775\uC810");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel MainContainer = new JPanel();
        setContentPane(MainContainer);
        
        JButton OpenWindow = new JButton("");
        OpenWindow.setBackground(Color.WHITE);
        OpenWindow.setIcon(new ImageIcon("C:\\Users\\mansw\\Desktop\\ICIA\\\uC870\uBCC4\uACFC\uC81C\\2\uCC28"
        		+ " \uC790\uBC14 \uC870\uBCC4\uACFC\uC81C(\uD568\uACBD\uC2DD, \uC720\uC9C0\uAD8C,"
        		+ " \uBB38\uC885\uD638)\\1\uBC88\uCC3D \uBC84\uD2BC.png"));
        OpenWindow.setBounds(316, 458, 366, 170);
        
        OpenWindow.addActionListener(new ActionListener() {
            // 만들어진 버튼 "새 창 띄우기"에 버튼이 눌러지면 발생하는 행동을 정의
            @Override
            public void actionPerformed(ActionEvent e) {
            	new Order();
            	// 클래스 newWindow를 새로 만들어낸다
            	dispose();
            }
        });
        MainContainer.setLayout(null);
        
        MainContainer.add(OpenWindow);
        //라벨 설정 
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon("C:\\Users\\mansw\\Desktop\\ICIA\\\uC870\uBCC4\uACFC\uC81C\\2\uCC28 "
        		+ "\uC790\uBC14 \uC870\uBCC4\uACFC\uC81C(\uD568\uACBD\uC2DD, \uC720\uC9C0\uAD8C, "
        		+ "\uBB38\uC885\uD638)\\1\uBC88\uCC3D.jpg"));
        lblNewLabel.setBounds(0, 0, 977, 687);
        MainContainer.add(lblNewLabel);
        
        setSize(983,722);
        setResizable(false);
        setVisible(true);
    }
}
