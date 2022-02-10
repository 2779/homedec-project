package Forms;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import Dbms.UserTable;
import HelperPack.Center;

public class Delete extends JFrame{
	Toolkit kit=Toolkit.getDefaultToolkit();
	Login l;
	public Delete() {
		UserTable ut=new UserTable();
		setTitle(" Delete Form");
		setLayout(null);
		setSize(500,500);
		setLocation(Center.getCenter(kit.getScreenSize(), getSize()));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setIconImage(kit.createImage("E:\\eclipse\\ProjectForm\\src\\img\\icon2.png"));
		setResizable(false);
		Container panel=getContentPane();
		panel.setLayout(null);
		Font f=new Font(Font.SANS_SERIF,Font.BOLD,15);
		
		JLabel email=new JLabel();
		email.setForeground(Color.red);
		email.setFont(f);
		email.setHorizontalAlignment(JLabel.CENTER);
		email.setVerticalAlignment(JLabel.CENTER);
		email.setSize(300,20);
		email.setLocation(100,180);
		JButton b=new JButton("Confirm");
		b.setEnabled(false);
		b.setSize(100,30);
		b.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ut.query("delete from users where email='"+l.EMAIL_ID+"'");
				System.exit(EXIT_ON_CLOSE);
			}
		});
		Checkbox cb=new Checkbox();
		cb.setSize(20,20);
		cb.setLocation(100,100);
		JLabel l1=new JLabel("I agree to delete");
		l1.setHorizontalAlignment(JLabel.CENTER);
		l1.setVerticalAlignment(JLabel.CENTER);
		l1.setForeground(Color.DARK_GRAY);
		l1.setSize(250,20);
		JLabel l2=new JLabel("my account from this application");
		l2.setSize(250,20);
		l2.setForeground(Color.DARK_GRAY);
		l2.setHorizontalAlignment(JLabel.CENTER);
		l2.setVerticalAlignment(JLabel.CENTER);
		panel.add(email);
		panel.add(l1);
		panel.add(l2);
		panel.add(cb);
		panel.add(b);
		
		setVisible(true);
		
		Center.align(cb);
		Center.align(b);
		Center.align(l1);
		b.setLocation(b.getX(),b.getY()+100);
		l1.setLocation(l1.getX(),l1.getY()+20);
		l2.setLocation(l1.getX(),l1.getY()+20);
		l=new Login();
		l.addWindowListener(new WindowAdapter() {
			public void windowClosed(WindowEvent e) {
				email.setText("Email: "+l.EMAIL_ID);
				b.setEnabled(true);
			}
		});
	}
	public static void main(String[] args) {
		new Delete();
	}
}
