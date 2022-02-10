package Forms;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import Dbms.UserTable;
import HelperPack.Center;
import HelperPack.ImgPack;

public class ForgotPassword extends JFrame{
	Toolkit kit=Toolkit.getDefaultToolkit();
	JButton b1;
	JTextField t1;
	public static final int bool_e=0,bool_p=1,bool_cp=2;
	boolean[] bool={false,false,false};
	public ForgotPassword() {
		UserTable ut=new UserTable();
		setTitle("Reset Password");
		setLayout(null);
		setSize(450,350);
		setLocation(Center.getCenter(kit.getScreenSize(), getSize()));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setIconImage(kit.createImage("E:\\eclipse\\ProjectForm\\src\\img\\icon2.png"));
		setResizable(false);
		Container panel=getContentPane();
		panel.setLayout(null);
		Font f=new Font(Font.SANS_SERIF,Font.BOLD,15);
		
		JLabel title=new JLabel("Reset Password",SwingConstants.CENTER);
		title.setSize(200,40);
		title.setHorizontalAlignment(JLabel.CENTER);
		title.setVerticalAlignment(JLabel.CENTER);
		title.setFont(new Font(Font.SANS_SERIF,Font.BOLD,18));
		title.setLocation(125,20);
		ActionListener trans = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				((Component)e.getSource()).transferFocus();
			}
		};
		JLabel l1=new JLabel("E-mail");
		l1.setLocation(50,100);
		l1.setSize(100,20);
		JLabel etick=new JLabel();
		etick.setSize(20,20);
		etick.setLocation(375,100);
		t1=new JTextField(20);
		t1.setLocation(170,100);
		t1.setSize(200,20);
		t1.addActionListener(trans);
		t1.getDocument().addDocumentListener(new Register.TextListener() {
			@Override
			public void update(DocumentEvent e) {
				bool[bool_e]=Register.isEmail(t1.getText());
				String s=t1.getText();
				if(Register.isEmail(s)) {
					if(ut.isEmailThere(s)) {
						etick.setIcon(new ImageIcon(ImgPack.getImage("E:\\eclipse\\ProjectForm\\src\\img\\tick.gif",16,16)));
						t1.transferFocus();
					}
					else {
						etick.setIcon(null);
					}
				}
				b1Render();
			}
		});
		JLabel l2=new JLabel("Create Password");
		l2.setLocation(50,140);
		l2.setSize(100,20);
		JPasswordField t2=new JPasswordField(20);
		t2.setEchoChar('*');
		t2.setToolTipText("<html><p width=\"200\">"
							+"password should<br>"
							+"&emsp*have atleast 8 charcters<br>"
							+"&emsp*contain a uppercase letter<br>"
							+"&emsp*contain a special character<br>"
							+"&emsp*contain a number"
							+"</p></html>");
		t2.setLocation(170,140);
		t2.setSize(200,20);
		t2.setForeground(Color.RED);
		t2.getDocument().addDocumentListener(new Register.TextListener() {
			@Override
			public void update(DocumentEvent e) {
				int passcount=Register.isPassword(t2.getText());
				Graphics g=panel.getGraphics();
				g.setColor(Color.GRAY);
				int start=171;
				g.fillRoundRect(start+0, 162, 45, 4, 2, 2);
				g.fillRoundRect(start+50, 162, 45, 4, 2, 2);
				g.fillRoundRect(start+100, 162, 45, 4, 2, 2);
				g.fillRoundRect(start+150, 162, 45, 4, 2, 2);
				
				g.setColor(new Color(0,255,127));
				for(int i=0;i<passcount;i++) {
					g.fillRoundRect(start+50*i, 162, 45, 4, 2, 2);
				}
				
				bool[bool_p]=(passcount==4)? true:false;
				if(bool[bool_p]) t2.setForeground(Color.black);
				else t2.setForeground(Color.RED);
				b1Render();
			}
		});
		t2.addActionListener(trans);
		JLabel l3=new JLabel("Confirm Password");
		l3.setLocation(50,180);
		l3.setSize(120,20);
		JPasswordField t3=new JPasswordField(20);
		t3.setLocation(170,180);
		t3.setSize(200,20);
		t3.setEchoChar('*');
		t3.getDocument().addDocumentListener(new Register.TextListener() {
			@Override
			public void update(DocumentEvent e) {
				int passcount=Register.isPassword(t3.getText());
				Graphics g=panel.getGraphics();
				g.setColor(Color.GRAY);
				int start=171;
				g.fillRoundRect(start+0, 202, 45, 4, 2, 2);
				g.fillRoundRect(start+50, 202, 45, 4, 2, 2);
				g.fillRoundRect(start+100, 202, 45, 4, 2, 2);
				g.fillRoundRect(start+150, 202, 45, 4, 2, 2);
				
				g.setColor(new Color(0,255,127));
				for(int i=0;i<passcount;i++) {
					g.fillRoundRect(start+50*i, 202, 45, 4, 2, 2);
				}
				bool[bool_cp]=t2.getText().equals(t3.getText());
				if(bool[bool_cp]) t3.setForeground(Color.black);
				else t3.setForeground(Color.RED);
				b1Render();
			}
		});
		t3.addActionListener(trans);
		b1=new JButton("Reset");
		b1.setEnabled(false);
		b1.setSize(100,30);
		b1.setLocation(170,240);
		b1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ut.query("update users set password='"+ut.hashp(t3.getText())+"'  where email='"+t1.getText()+"'");
				dispose();
			}
		});
		
		panel.add(title);
		panel.add(l1);
		panel.add(t1);
		panel.add(etick);
		panel.add(l2);
		panel.add(t2);
		panel.add(l3);
		panel.add(t3);
		panel.add(b1);
		
		setVisible(true);
	}
	protected void b1Render() {
		boolean t=true;
		for(boolean i: bool) { 
			if(!i) {
				t=false;
				break;
			}
		}
		b1.setEnabled(t);
	}
	
}