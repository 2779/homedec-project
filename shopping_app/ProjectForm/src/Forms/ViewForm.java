package Forms;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;
import javax.swing.border.LineBorder;
import HelperPack.Center;

public class ViewForm extends JFrame{
	Toolkit kit=Toolkit.getDefaultToolkit();
	public ViewForm() {
		setTitle("Profile Details");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(400,450);
		setLocation(Center.getCenter(kit.getScreenSize(), getSize()));
		setIconImage(kit.createImage("E:\\eclipse\\ProjectForm\\src\\img\\icon2.png"));
		setResizable(false);
		Container co=getContentPane();
		co.setLayout(null);
		
		JPanel p=new JPanel(null);
		p.setSize(250,300);
		String[][] table= {
				{"First Name"   ,""},
				{"Last Name"    ,""},
				{"Email"        ,""},
				{"Age"		    ,""},
				{"Gender"       ,""},
				{"Date Of Birth",""},
				{"Mobile Number",""},
				{"City"         ,""}
		};
		String[] col= {"property","value"};
		JTable t=new JTable(table,col);
		t.setRowHeight(30);
		t.getColumnModel().getColumn(0).setPreferredWidth(100);
		t.getColumnModel().getColumn(1).setPreferredWidth(150);
		t.setBorder(new LineBorder(Color.GRAY,1));
		t.setSize(250,240);
		t.setEnabled(false);
		JButton b=new JButton("Edit Profile");
		b.setSize(100,30);
		b.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Update u=new Update();
				u.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosed(WindowEvent e) {
						String[][] table=u.ut.getResult
								("select firstname,lastname,email,age,gender,"
								+ "dateofbirth,mobilenumber,city from users where email='"
								+u.t3.getText()+"'", 8);
						for(int i=0;i<8;i++) t.setValueAt(table[0][i], i, 1);
						t.setValueAt((table[0][4].equals("M"))? "Male":"Female", 4,1 );
						t.setValueAt(table[0][5].split(" ")[0], 5,1 );
					}
				});
			}
		});
		p.add(t);
		p.add(b);
		b.setLocation(Center.getCenter(250, 60, 100, 30).x,270);
		co.add(p);
		setVisible(true);
		Center.align(p);
		Login l=new Login();
		l.addWindowListener(new WindowAdapter() {
			public void windowClosed(WindowEvent e) {
				String[][] table=l.ut.getResult(
						"select firstname,lastname,email,age,gender,dateofbirth"
						+ ",mobilenumber,city from users where email='"
						+l.EMAIL_ID+"'", 8);
				for(int i=0;i<8;i++) t.setValueAt(table[0][i], i, 1);
				t.setValueAt((table[0][4].equals("M"))? "Male":"Female", 4,1 );
				t.setValueAt(table[0][5].split(" ")[0], 5,1 );
			}
		});
	}
	public static void main(String[] args) {
		new ViewForm();
	}
}
