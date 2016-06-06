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
		frame.setTitle("七肥宅");
		frame.setResizable(false);
		frame.setBounds(500, 100, 864, 659);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("\u4E03\u80A5\u5B85");
		label.setFont(new Font("王漢宗中仿宋繁", Font.BOLD, 40));
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
					JOptionPane.showMessageDialog(frame,"帳號或密碼錯誤!!");
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
		frame.setTitle("七肥宅");
		frame.setResizable(false);
		frame.setBounds(500, 100, 864, 659);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setSize(1024, 800);
		frame.setVisible(true);
		
		setBG(); //調用背景方法
		/*Container c = frame.getContentPane(); //獲取JFrame面板
		JPanel jp = new JPanel(); //創建個JPanel
		jp.setOpaque(false); //把JPanel設置為透明 這樣就不會遮住後面的背景 這樣你就能在JPanel隨意加元件了
		c.add(jp);*/
		
		
		//JButton image = new JButton(new ImageIcon("fucku.png"));//可以用此函式抓出圖片
		
		//////////玩家金錢顯示////////////////////////////////////
		JLabel coin  = new JLabel ("目前金幣："+Integer.toString(user_coin));
		coin.setFont(new Font("微軟正黑體", Font.PLAIN, 26));
		coin.setBounds(75,50,279,75);
		frame.getContentPane().add(coin);
		//////////////////////////////////////////////////////
		
		//////////玩家卡包數量顯示////////////////////////////////////
		JLabel pack  = new JLabel ("目前卡包數量："+Integer.toString(user_pack));
		pack.setFont(new Font("微軟正黑體", Font.PLAIN, 26));
		pack.setBounds(75,100,279,75);
		frame.getContentPane().add(pack);
		//////////////////////////////////////////////////////
		
		
		//////////戰鬥按鈕//////////////////////////////////////
		JButton imgPlay = new JButton("戰鬥");
		imgPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//frame.setVisible(false);
				a= "456";
				frame.dispose();
				test t = new test(1);
				t.frame.setVisible(true);
			}
		});
		imgPlay.setFont(new Font("微軟正黑體", Font.PLAIN, 26));
		imgPlay.setBounds(352, 75, 279, 75);
		frame.getContentPane().add(imgPlay);
		////////////////////////////////////////////////////
		
		
		//////////編輯牌組//////////////////////////////////////
		JButton imgMyCard = new JButton("我的牌組");
		imgMyCard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(a);
			}
		});
		imgMyCard.setFont(new Font("微軟正黑體", Font.PLAIN, 26));
		imgMyCard.setBounds(352, 200, 279, 75);
		frame.getContentPane().add(imgMyCard);
		/////////////////////////////////////////////////////
		
		
		//////////玩家購買卡包////////////////////////////////////
		JButton imgBuyCard = new JButton("購買卡包");
		imgBuyCard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		imgBuyCard.setFont(new Font("微軟正黑體", Font.PLAIN, 26));
		imgBuyCard.setBounds(352, 325, 279, 75);
		frame.getContentPane().add(imgBuyCard);
		///////////////////////////////////////////////////////////
		
		
		///////////////玩家開卡包////////////////////////////////////////
		JButton imgOpenPack = new JButton("開卡包");
		imgOpenPack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		imgOpenPack.setFont(new Font("微軟正黑體", Font.PLAIN, 26));
		imgOpenPack.setBounds(352, 450, 279, 75);
		frame.getContentPane().add(imgOpenPack);
		//////////////////////////////////////////////////////////////
		
		
		//////////////////玩家離開遊戲/////////////////////////////////////
		JButton imgExit = new JButton("離開遊戲");
		
		imgExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		
		imgExit.setFont(new Font("微軟正黑體", Font.PLAIN, 26));
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
