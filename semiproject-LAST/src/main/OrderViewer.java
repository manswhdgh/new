package main;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class OrderViewer extends JFrame {
	TextArea ta;  
	int count = 0;
    String show = "";
    
    public OrderViewer() {   // �ֹ�����������   
        // �ֹ����� ������ ����
    	JFrame frmLotte = new JFrame("�ܹ��� �ڵ� �Ǹű�");
        frmLotte.setTitle("Lotte \uD559\uC775\uC810 \uC8FC\uBB38\uC2DC\uC2A4\uD15C");
        frmLotte.setBounds(650, 0, 625, 1000);
        frmLotte.setBackground(Color.black);
        // ��Ʈ
        Font font = new Font(Font.SANS_SERIF, Font.PLAIN, 15);
        Font font1 = new Font(Font.MONOSPACED, Font.BOLD, 22);
        
        // �ֹ����� TextArea ����
        ta = new TextArea("", 0, 0, TextArea.SCROLLBARS_VERTICAL_ONLY);
        ta.setText("   ��ǰ��        �ܰ�        ����        �հ�\n\n");
        ta.setBackground(Color.white);
        ta.setEditable(false);
        ta.setFont(font1);
        
        // �ֹ����� �ϴ� ��  ����
        Panel pSouth = new Panel();
        pSouth.setFont(font);
        pSouth.setBackground(new Color(255, 255, 215));
        Button bt1 = new Button("����");
        
        // �ֹ����� ��ư Ŭ�� �� �̺�Ʈ ����
        bt1.addActionListener(new ActionListener() { 
        	public void actionPerformed(ActionEvent e) {
            	MidPage mp = new MidPage();
            	mp.setVisible(true);
            	LastPage lp = new LastPage();
            	lp.setVisible(false);
            	frmLotte.setVisible(false);
        	}
        });
        // ������ư
        pSouth.add(bt1);
        
        // ������Ʈ
        frmLotte.getContentPane().add(ta, BorderLayout.CENTER);
        frmLotte.getContentPane().add(pSouth, BorderLayout.SOUTH);
        frmLotte.setVisible(true);

    }
    // ����
    public static void main(String[] args) {
        new OrderViewer();
    }
}
 
