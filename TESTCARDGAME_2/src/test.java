import java.awt.Container;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

import com.mysql.jdbc.Connection;
import javax.swing.JTextField;

public class test {
	static int user_id = 0; 
	static int user_coin = 0;
	static int user_pack = 0;
	static String a = "123";
	private JFrame frame;
	private JTextField usernameT;
	private JTextField passwordT;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
						test window = new test(0);
						window.frame.setVisible(true);
					}
					/*changeFrame =1;
					test window = new test(whatFrame);
					window.frame.setVisible(true);*/
					//initTest window2 = new initTest();
					//window2.setVisible(true);
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public test(int stat) {
		if (stat==0)
			initialize();
		else if (stat ==1)
			mainSelection();
		else{		
		}
	}

	/**
	 * Initialize the contents of the frame.
	 * @wbp.parser.entryPoint
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("�C�Φv");
		frame.setResizable(false);
		frame.setBounds(500, 100, 864, 659);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("\u4E03\u80A5\u5B85");
		label.setFont(new Font("���~�v���駺�c", Font.BOLD, 40));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(379, 73, 250, 86);
		frame.getContentPane().add(label);
		
		usernameT = new JTextField();
		usernameT.setBounds(401, 215, 212, 32);
		frame.getContentPane().add(usernameT);
		usernameT.setColumns(10);
		
		passwordT = new JPasswordField();		
		passwordT.setBounds(401, 302, 212, 32);
		frame.getContentPane().add(passwordT);
		passwordT.setColumns(10);
		
		JButton btnNewButton = new JButton("\u767B\u5165");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//passwordT.setText(usernameT.getText());
				JdbcCon user = new JdbcCon(usernameT.getText(),passwordT.getText());
				user_id = user.Login(user_id);
				if (user_id !=0){
					user_coin  = user.getCoin(user_id);
					user_pack = user.getPack(user_id);
					test t = new test(1);
					t.frame.setVisible(true);
					frame.dispose();
				}
				else{
					JOptionPane.showMessageDialog(frame,"�b���αK�X���~!!");
				}
				
			}
		});
		btnNewButton.setBounds(445, 401, 127, 41);
		frame.getContentPane().add(btnNewButton);
		
		JButton button = new JButton("\u8A3B\u518A\u5E33\u865F");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();		
			}
		});
		button.setBounds(445, 481, 127, 41);
		frame.getContentPane().add(button);
		frame.setSize(1024, 800);
		frame.setVisible(true);
		setBG();
	}
	
	private void mainSelection(){
		frame = new JFrame();
		frame.setTitle("�C�Φv");
		frame.setResizable(false);
		frame.setBounds(500, 100, 864, 659);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setSize(1024, 800);
		frame.setVisible(true);
		
		setBG(); //�եέI����k
		/*Container c = frame.getContentPane(); //���JFrame���O
		JPanel jp = new JPanel(); //�Ыح�JPanel
		jp.setOpaque(false); //��JPanel�]�m���z�� �o�˴N���|�B��᭱���I�� �o�˧A�N��bJPanel�H�N�[����F
		c.add(jp);*/
		
		
		//JButton image = new JButton(new ImageIcon("fucku.png"));//�i�H�Φ��禡��X�Ϥ�
		
		//////////���a�������////////////////////////////////////
		JLabel coin  = new JLabel ("�ثe�����G"+Integer.toString(user_coin));
		coin.setFont(new Font("�L�n������", Font.PLAIN, 26));
		coin.setBounds(75,50,279,75);
		frame.getContentPane().add(coin);
		//////////////////////////////////////////////////////
		
		//////////���a�d�]�ƶq���////////////////////////////////////
		JLabel pack  = new JLabel ("�ثe�d�]�ƶq�G"+Integer.toString(user_pack));
		pack.setFont(new Font("�L�n������", Font.PLAIN, 26));
		pack.setBounds(75,100,279,75);
		frame.getContentPane().add(pack);
		//////////////////////////////////////////////////////
		
		
		//////////�԰����s//////////////////////////////////////
		JButton imgPlay = new JButton("�԰�");
		imgPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//frame.setVisible(false);
				a= "456";
				frame.dispose();
				test t = new test(1);
				t.frame.setVisible(true);
			}
		});
		imgPlay.setFont(new Font("�L�n������", Font.PLAIN, 26));
		imgPlay.setBounds(352, 75, 279, 75);
		frame.getContentPane().add(imgPlay);
		////////////////////////////////////////////////////
		
		
		//////////�s��P��//////////////////////////////////////
		JButton imgMyCard = new JButton("�ڪ��P��");
		imgMyCard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(a);
			}
		});
		imgMyCard.setFont(new Font("�L�n������", Font.PLAIN, 26));
		imgMyCard.setBounds(352, 200, 279, 75);
		frame.getContentPane().add(imgMyCard);
		/////////////////////////////////////////////////////
		
		
		//////////���a�ʶR�d�]////////////////////////////////////
		JButton imgBuyCard = new JButton("�ʶR�d�]");
		imgBuyCard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		imgBuyCard.setFont(new Font("�L�n������", Font.PLAIN, 26));
		imgBuyCard.setBounds(352, 325, 279, 75);
		frame.getContentPane().add(imgBuyCard);
		///////////////////////////////////////////////////////////
		
		
		///////////////���a�}�d�]////////////////////////////////////////
		JButton imgOpenPack = new JButton("�}�d�]");
		imgOpenPack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		imgOpenPack.setFont(new Font("�L�n������", Font.PLAIN, 26));
		imgOpenPack.setBounds(352, 450, 279, 75);
		frame.getContentPane().add(imgOpenPack);
		//////////////////////////////////////////////////////////////
		
		
		//////////////////���a���}�C��/////////////////////////////////////
		JButton imgExit = new JButton("���}�C��");
		
		imgExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		
		imgExit.setFont(new Font("�L�n������", Font.PLAIN, 26));
		imgExit.setBounds(352, 575, 279, 75);
		frame.getContentPane().add(imgExit);
		///////////////////////////////////////////////////////////////
		
		
	}
	
	public void setBG(){
		((JPanel)frame.getContentPane()).setOpaque(false);
		ImageIcon img = new ImageIcon("bkTest.jpg");
		JLabel background = new JLabel(img);
		frame.getLayeredPane().add(background, new Integer(Integer.MIN_VALUE));
		background.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
		}
}
