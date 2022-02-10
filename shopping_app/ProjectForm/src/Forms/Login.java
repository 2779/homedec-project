package Forms;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import Dbms.UserTable;
import HelperPack.*;

public class Login extends JFrame{
	Toolkit kit=Toolkit.getDefaultToolkit();
	
	public String EMAIL_ID=null;
	public boolean LOGIN_SUCCESS=false;
	
	public UserTable ut;
	public Login() {
		ut=new UserTable();
		setTitle("Login Page");
		setSize(400,450);
		setLocation(Center.getCenter(kit.getScreenSize(), getSize()));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setIconImage(kit.createImage("E:\\eclipse\\ProjectForm\\src\\img\\icon2.png"));
		setResizable(false);
		Container panel=getContentPane();
		panel.setLayout(new GridLayout(3,1));
		
		JLabel img=new JLabel(new ImageIcon(ImgPack.getImage("E:\\eclipse\\ProjectForm\\src\\img\\icon2.png",50,50)));
		img.setSize(50,50);
		panel.add(new Holder(null).add(img));
		
		JLabel emailLabel=new JLabel("E-mail");
		emailLabel.setSize(100,20);
		emailLabel.setLocation(50,20);
		JLabel etick=new JLabel();
		etick.setSize(20,20);
		etick.setLocation(340,20);
		JTextField emailInput=new JTextField(20);
		emailInput.setSize(200,20);
		emailInput.setLocation(135,20);
		emailInput.setSelectionColor(Color.RED);
		emailInput.getDocument().addDocumentListener(new Register.TextListener() {
			@Override
			public void update(DocumentEvent e) {
				String s=emailInput.getText();
				if(Register.isEmail(s)) {
					if(ut.isEmailThere(s)) {
						etick.setIcon(new ImageIcon(ImgPack.getImage("E:\\eclipse\\ProjectForm\\src\\img\\tick.gif",16,16)));
						emailInput.transferFocus();
					}
					else {
						etick.setIcon(null);
					}
				}
			}
		});
		JPanel emailPanel=new Holder(null).add(emailLabel,emailInput,etick);

		JLabel passwordLabel=new JLabel("password");
		passwordLabel.setSize(100,20);
		passwordLabel.setLocation(50,20);
		JPasswordField passwordInput=new JPasswordField(20);
		passwordInput.setSize(200,20);
		passwordInput.setLocation(135,20);
		passwordInput.setEchoChar('*');
		passwordInput.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				UserTable userInfo=new UserTable();
				String email=emailInput.getText();
				ArrayList<String> Email= userInfo.getColumn("select email from Users");
				if(Email.contains(email)) {
					String password=userInfo.getColumn("select password from Users where email='"+email+"'").get(0);
					if(password.equals(userInfo.hashp(passwordInput.getText()))) {
						EMAIL_ID=email;
						LOGIN_SUCCESS=true;
						dispose();
					}
					else {
						JOptionPane.showMessageDialog(passwordInput, "Incorrect Password", "Error in Login", JOptionPane.ERROR_MESSAGE);
					}
				}
				else {
					JOptionPane.showMessageDialog(emailInput, "Email ID not Found", "Error in Login", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		JPanel passwordPanel=new Holder(null).add(passwordLabel,passwordInput);
		panel.add(new Holder(new GridLayout(2,1)).add(emailPanel,passwordPanel));
		
		JLabel forgotPassword=new JLabel("Forgot your password?");
		forgotPassword.setForeground(Color.BLUE);
		forgotPassword.setCursor(new Cursor(Cursor.HAND_CURSOR));
		forgotPassword.setSize(180,30);
		forgotPassword.setFont(new Font(Font.SANS_SERIF , Font.PLAIN , 14));
		forgotPassword.setLocation(50,20);
		JPanel forgotPasswordPanel=new Holder(null).add(forgotPassword);
		forgotPasswordPanel.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {}
			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}
			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseClicked(MouseEvent e) {
				ForgotPassword fp= new ForgotPassword();
				fp.t1.setText(emailInput.getText());
			}
		});
		JButton login=new JButton("Login");
		login.setSize(100,30);
		login.setCursor(new Cursor(Cursor.HAND_CURSOR));
		login.setLocation(50,0);
		JButton register=new JButton("Register");
		register.setSize(100,30);
		register.setLocation(230,0);
		register.setCursor(new Cursor(Cursor.HAND_CURSOR));
		JPanel buttonPanel= new Holder(null).add(login,register);
		
		login.addActionListener(passwordInput.getActionListeners()[0]);
		register.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new Register();
			}
		});
		
		panel.add(new Holder(new GridLayout(2,1)).add(forgotPasswordPanel,buttonPanel));
		
		setVisible(true);
		Center.align(img);
	}
	public static void main(String[] args) {
		Login l=new Login();
		l.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				JOptionPane.showMessageDialog(l, "Login Successful");
			}
		});
	}
}