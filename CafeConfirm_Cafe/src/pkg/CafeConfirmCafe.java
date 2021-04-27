package pkg;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class CafeConfirmCafe extends JFrame {
	
	private CafeConfirmCafe gui = this;
	private Client client;
	private Timer timer;
	private TimerTask timerTask;
	private String CafeName;
	
	private JPanel screenAccess;
	private JButton btnAccess;
	private JTextField editTxtIPAddress;
	private JTextField editTxtPortNumber;
	private JLabel lblIPAddress;
	private JLabel lblPortNumber;
	private JLabel lblTitle;
	private JLabel lblWelcomeToUs;
	
	private JPanel screenLogin;
	private JButton btnLogin;
	private JButton btnJoin;
	private JTextField editTxtCafeName;
	private JPasswordField editTxtPassword;
	private JLabel lblCafeName;
	private JLabel lblPassword;
	
	private JPanel screenJoin;
	private JTextField editTxtCafeName_Join;
	private JPasswordField editTxtPassword_Join;
	private JTextField editTxtMaxNum_Join;
	private JTextField editTxtLat_Join;
	private JTextField editTxtLng_Join;
	private JLabel lblCafeName_Join;
	private JLabel lblPassword_Join;
	private JLabel lblMaxNum_Join;
	private JLabel lblLat_Join;
	private JLabel lblLng_Join;
	
	private JButton btnReset;
	private JButton btnJoinNow;
	
	private JPanel screenSendCafeInfo;
	private JButton btnStart;
	private JButton btnStop;
	private JLabel lblLoginInfo;
	
	public CafeConfirmCafe(){
		CafeName = "";
		client = new Client(gui);
		
		setTitle("카페 관리 프로그램");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 500);
		addWindowListener( new WindowAdapter() {
			public void windowClosing(WindowEvent we){
				exit();
			}
		});
		
		initializeTimer();
		initialize1();
		initialize2();
		initialize3();
		initialize4();
	}
	
	/*
	public static void main(String[] args) {
		Client client = new Client();
    	client.setIPAddress(IPAddress);
    	client.setPortNumber(portNumber);
    	client.run();
    }
    */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CafeConfirmCafe frame = new CafeConfirmCafe();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private void initialize1(){
		screenAccess = new JPanel();
		
		setContentPane(screenAccess);
		screenAccess.setVisible(true);
		
		screenAccess.setBackground(new Color(204, 255, 255));
		screenAccess.setLayout(null);
		
		btnAccess = new JButton("\uC811\uC18D");
		btnAccess.setFont(new Font("돋움", Font.BOLD, 15));
		btnAccess.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {  // 서버한테 접속하는 버튼을 클릭했을 경우
				if(editTxtIPAddress.getText().equals("")){  // IP주소를 누락했을 경우
					JOptionPane.showMessageDialog(gui, "IP주소를 입력해 주세요.", "Error...", JOptionPane.ERROR_MESSAGE);
				}
				else if(editTxtPortNumber.getText().equals("")){  // 포트번호를 누락했을 경우
					JOptionPane.showMessageDialog(gui, "포트번호를 입력해 주세요.", "Error...", JOptionPane.ERROR_MESSAGE);
				}
				else if(!isInteger(editTxtPortNumber.getText())){  // 포트번호가 유효한 자연수가 아닌 경우
					JOptionPane.showMessageDialog(gui, "포트번호는 유효한 자연수이여야 합니다.", "Error...", JOptionPane.ERROR_MESSAGE);
				}
				else if(hasBackSpace(editTxtIPAddress.getText()) || hasBackSpace(editTxtPortNumber.getText())){  // 포트번호가 유효한 자연수가 아닌 경우
					notifyNoBackSpace();
				}
				else{  // 서버에 접속 시도
					setContentPane(screenLogin);
					screenAccess.setVisible(false);
					screenLogin.setVisible(true);
					
					client.ready(editTxtIPAddress.getText(), Integer.parseInt(editTxtPortNumber.getText()));
					client.start();
				}
			}
		});
		btnAccess.setBounds(12, 209, 462, 29);
		screenAccess.add(btnAccess);
		
		editTxtIPAddress = new JTextField();
		editTxtIPAddress.setFont(new Font("돋움", Font.PLAIN, 15));
		editTxtIPAddress.setBounds(96, 116, 378, 27);
		editTxtIPAddress.setColumns(10);
		editTxtIPAddress.setText("192.168.0.3");
		screenAccess.add(editTxtIPAddress);
		
		editTxtPortNumber = new JTextField();
		editTxtPortNumber.setFont(new Font("돋움", Font.PLAIN, 15));
		editTxtPortNumber.setColumns(10);
		editTxtPortNumber.setBounds(96, 153, 378, 27);
		editTxtPortNumber.setText("1234");
		screenAccess.add(editTxtPortNumber);
		
		lblIPAddress = new JLabel("IP\uC8FC\uC18C:");
		lblIPAddress.setFont(new Font("돋움", Font.PLAIN, 15));
		lblIPAddress.setBounds(12, 115, 72, 27);
		screenAccess.add(lblIPAddress);
		
		lblPortNumber = new JLabel("\uD3EC\uD2B8\uBC88\uD638:");
		lblPortNumber.setFont(new Font("돋움", Font.PLAIN, 15));
		lblPortNumber.setBounds(12, 152, 72, 27);
		screenAccess.add(lblPortNumber);
		
		lblTitle = new JLabel("\uCE74\uD398 \uAD00\uB9AC \uD504\uB85C\uADF8\uB7A8");
		lblTitle.setFont(new Font("돋움", Font.BOLD, 45));
		lblTitle.setForeground(Color.BLUE);
		lblTitle.setBounds(12, 36, 462, 61);
		screenAccess.add(lblTitle);
		
		lblWelcomeToUs = new JLabel("Welcome to US~!!");
		lblWelcomeToUs.setFont(new Font("돋움", Font.PLAIN, 20));
		lblWelcomeToUs.setForeground(Color.RED);
		lblWelcomeToUs.setBounds(12, 10, 462, 29);
		screenAccess.add(lblWelcomeToUs);
	}
	
	private void initialize2(){
		screenLogin = new JPanel();
		
		//setContentPane(screenLogin);
		//screenLogin.setVisible(true);
		screenLogin.setVisible(false);
		
		screenLogin.setBackground(new Color(204, 255, 255));
		screenLogin.setLayout(null);
		
		btnLogin = new JButton("\uB85C\uADF8\uC778");
		btnLogin.setFont(new Font("돋움", Font.BOLD, 15));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(editTxtCafeName.getText().equals("")){
					JOptionPane.showMessageDialog(gui, "카페명을 입력해 주세요.", "Error...", JOptionPane.ERROR_MESSAGE);
				}
				else if(editTxtPassword.getPassword().length <= 0){
					JOptionPane.showMessageDialog(gui, "비밀번호를 입력해 주세요.", "Error...", JOptionPane.ERROR_MESSAGE);
				}
				else if(hasBackSpace(editTxtCafeName.getText()) || hasBackSpace(String.valueOf(editTxtPassword.getPassword()))) {
					notifyNoBackSpace();
				}
				else{
					CafeName = editTxtCafeName.getText();
					lblLoginInfo.setText("카페명: " + CafeName);
					
					setContentPane(screenSendCafeInfo);
					screenLogin.setVisible(false);
					screenSendCafeInfo.setVisible(true);
					
					client.sender.sendMessage("login " + editTxtCafeName.getText() + " " + String.valueOf(editTxtPassword.getPassword()));
				}
			}
		});
		btnLogin.setBounds(12, 209, 462, 29);
		screenLogin.add(btnLogin);
		
		btnJoin = new JButton("\uD68C\uC6D0\uAC00\uC785");
		btnJoin.setFont(new Font("돋움", Font.BOLD, 15));
		btnJoin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setContentPane(screenJoin);
				screenLogin.setVisible(false);
				screenJoin.setVisible(true);
			}
		});
		btnJoin.setBounds(12, 248, 462, 29);
		screenLogin.add(btnJoin);
		
		editTxtCafeName = new JTextField();
		editTxtCafeName.setFont(new Font("돋움", Font.PLAIN, 15));
		editTxtCafeName.setBounds(96, 116, 378, 27);
		screenLogin.add(editTxtCafeName);
		editTxtCafeName.setColumns(10);
		
		editTxtPassword = new JPasswordField();
		editTxtPassword.setFont(new Font("돋움", Font.PLAIN, 15));
		editTxtPassword.setColumns(10);
		editTxtPassword.setBounds(96, 153, 378, 27);
		screenLogin.add(editTxtPassword);
		
		lblCafeName = new JLabel("\uCE74\uD398\uBA85: ");
		lblCafeName.setFont(new Font("돋움", Font.PLAIN, 15));
		lblCafeName.setBounds(12, 115, 72, 27);
		screenLogin.add(lblCafeName);
		
		lblPassword = new JLabel("\uBE44\uBC00\uBC88\uD638: ");
		lblPassword.setFont(new Font("돋움", Font.PLAIN, 15));
		lblPassword.setBounds(12, 152, 72, 27);
		screenLogin.add(lblPassword);
	}
	
	private void initialize3(){
		screenSendCafeInfo = new JPanel();
		
		//setContentPane(screenSendCafeInfo);
		//screenSendCafeInfo.setVisible(true);
		screenSendCafeInfo.setVisible(false);
		
		screenSendCafeInfo.setBackground(new Color(204, 255, 255));
		screenSendCafeInfo.setLayout(null);
		
		btnStart = new JButton("START");
		btnStart.setFont(new Font("돋움", Font.BOLD, 15));
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				timer.schedule(timerTask, 1000, 10000);
			}
		});
		btnStart.setBounds(12, 209, 462, 29);
		screenSendCafeInfo.add(btnStart);
		
		btnStop = new JButton("STOP");
		btnStop.setFont(new Font("돋움", Font.BOLD, 15));
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				timer.cancel();
				client.sender.sendMessage("setNumOfPeople " + CafeName + " -1");
				initializeTimer();
			}
		});
		btnStop.setBounds(12, 248, 462, 29);
		screenSendCafeInfo.add(btnStop);
		
		lblLoginInfo = new JLabel("카페명: " + CafeName);
		lblLoginInfo.setFont(new Font("돋움", Font.PLAIN, 15));
		lblLoginInfo.setBounds(12, 424, 462, 29);
		screenSendCafeInfo.add(lblLoginInfo);
	}
	
	private void initialize4(){
		screenJoin = new JPanel();
		
		//setContentPane(screenSendCafeInfo);
		//screenSendCafeInfo.setVisible(true);
		screenJoin.setVisible(false);
		
		screenJoin.setBackground(new Color(204, 255, 255));
		screenJoin.setLayout(null);
		
		editTxtCafeName_Join = new JTextField();
		editTxtCafeName_Join.setFont(new Font("돋움", Font.PLAIN, 15));
		editTxtCafeName_Join.setBounds(115, 116, 359, 27);
		screenJoin.add(editTxtCafeName_Join);
		editTxtCafeName_Join.setColumns(10);
		
		lblCafeName_Join = new JLabel("\uCE74\uD398\uBA85: ");
		lblCafeName_Join.setFont(new Font("돋움", Font.PLAIN, 15));
		lblCafeName_Join.setBounds(12, 115, 98, 27);
		screenJoin.add(lblCafeName_Join);
		
		lblPassword_Join = new JLabel("\uBE44\uBC00\uBC88\uD638:");
		lblPassword_Join.setFont(new Font("돋움", Font.PLAIN, 15));
		lblPassword_Join.setBounds(12, 152, 98, 27);
		screenJoin.add(lblPassword_Join);
		
		lblMaxNum_Join = new JLabel("\uCD5C\uB300 \uC778\uAD6C \uC218:");
		lblMaxNum_Join.setFont(new Font("돋움", Font.PLAIN, 15));
		lblMaxNum_Join.setBounds(12, 190, 98, 27);
		screenJoin.add(lblMaxNum_Join);
		
		editTxtMaxNum_Join = new JTextField();
		editTxtMaxNum_Join.setFont(new Font("돋움", Font.PLAIN, 15));
		editTxtMaxNum_Join.setColumns(10);
		editTxtMaxNum_Join.setBounds(115, 191, 359, 27);
		screenJoin.add(editTxtMaxNum_Join);
		
		lblLat_Join = new JLabel("위도:");
		lblLat_Join.setFont(new Font("돋움", Font.PLAIN, 15));
		lblLat_Join.setBounds(12, 227, 98, 27);
		screenJoin.add(lblLat_Join);
		
		editTxtLat_Join = new JTextField();
		editTxtLat_Join.setFont(new Font("돋움", Font.PLAIN, 15));
		editTxtLat_Join.setColumns(10);
		editTxtLat_Join.setBounds(115, 228, 359, 27);
		screenJoin.add(editTxtLat_Join);
		
		lblLng_Join = new JLabel("경도:");
		lblLng_Join.setFont(new Font("돋움", Font.PLAIN, 15));
		lblLng_Join.setBounds(12, 264, 98, 27);
		screenJoin.add(lblLng_Join);
		
		editTxtLng_Join = new JTextField();
		editTxtLng_Join.setFont(new Font("돋움", Font.PLAIN, 15));
		editTxtLng_Join.setColumns(10);
		editTxtLng_Join.setBounds(115, 265, 359, 27);
		screenJoin.add(editTxtLng_Join);
		
		btnReset = new JButton("\uCD08\uAE30\uD654");
		btnReset.setFont(new Font("돋움", Font.BOLD, 15));
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editTxtCafeName_Join.setText("");
				editTxtPassword_Join.setText("");
				editTxtMaxNum_Join.setText("");
				editTxtLat_Join.setText("");
				editTxtLng_Join.setText("");
			}
		});
		btnReset.setBounds(12, 301, 462, 29);
		screenJoin.add(btnReset);
		
		btnJoinNow = new JButton("\uD68C\uC6D0\uAC00\uC785");
		btnJoinNow.setFont(new Font("돋움", Font.BOLD, 15));
		btnJoinNow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(editTxtCafeName_Join.getText().equals("")){
					JOptionPane.showMessageDialog(gui, "카페명을 입력해 주세요.", "Error...", JOptionPane.ERROR_MESSAGE);
				}
				else if(editTxtPassword_Join.getPassword().length <= 0){
					JOptionPane.showMessageDialog(gui, "비밀번호를 입력해 주세요.", "Error...", JOptionPane.ERROR_MESSAGE);
				}
				else if(editTxtMaxNum_Join.getText().equals("")){
					JOptionPane.showMessageDialog(gui, "최대 인구 수를 입력해 주세요.", "Error...", JOptionPane.ERROR_MESSAGE);
				}
				else if(editTxtLat_Join.getText().equals("") || editTxtLng_Join.getText().equals("")){
					JOptionPane.showMessageDialog(gui, "Lat 또는 Lng을 입력해 주세요.", "Error...", JOptionPane.ERROR_MESSAGE);
				}
				else if(hasBackSpace(editTxtCafeName_Join.getText()) || hasBackSpace(editTxtMaxNum_Join.getText()) || hasBackSpace(editTxtLat_Join.getText())
						|| hasBackSpace(editTxtLng_Join.getText()) || hasBackSpace(String.valueOf(editTxtPassword_Join.getPassword()))){
					notifyNoBackSpace();
				}
				else{
					setContentPane(screenLogin);
					screenJoin.setVisible(false);
					screenLogin.setVisible(true);
					
					client.sender.sendMessage("join " + editTxtCafeName_Join.getText() + " " + String.valueOf(editTxtPassword_Join.getPassword())
						+ " " + editTxtMaxNum_Join.getText() + " " + editTxtLat_Join.getText() + " " + editTxtLng_Join.getText());
				}
			}
		});
		btnJoinNow.setBounds(12, 340, 462, 29);
		screenJoin.add(btnJoinNow);
		
		editTxtPassword_Join = new JPasswordField();
		editTxtPassword_Join.setFont(new Font("돋움", Font.PLAIN, 15));
		editTxtPassword_Join.setBounds(115, 153, 359, 27);
		screenJoin.add(editTxtPassword_Join);
	}
	
	public void initializeTimer() {
		timer = new Timer();
		timerTask = new TimerTask() {
			@Override
			public void run() {
				PythonExecuter pythonExecuter = new PythonExecuter();
		    	String[] arguments = new String[0];
		    	 
		    	String order = "setNumOfPeople " + CafeName + " ";
		    	order += pythonExecuter.executePython("C:/Projects/JAVA Projects/CafeConfirmCafe", "FR_CamImage.py", arguments);
		    	client.sender.sendMessage(order);
			}
		};
	}
	
	public void notifyNoBackSpace() {
		JOptionPane.showMessageDialog(gui, "띄어쓰기는 어떠한 경우에도 허용되지 않습니다.", "Error...", JOptionPane.ERROR_MESSAGE);
	}
	
	public boolean isInteger(String strnum){
		if(strnum.length() == 1 && strnum.charAt(0) == '0')
			return false;
		
		for(int c=0; c<strnum.length(); c++){
			if(!(strnum.charAt(c) >= 48 && strnum.charAt(c) <= 57))
				return false;    
		}
		
		return true;
	}
	
	public boolean hasBackSpace(String str){
		if(str.equals(""))
			return true;
		
		for(int i=0; i<str.length(); i++){
			if(str.charAt(i) == ' ')
				return true;    
		}
		
		return false;
	}
	
	public void exit(){
		if(client.isConnected())
			client.sender.sendMessage("disconnect");
			
		System.exit(0);
	}
}