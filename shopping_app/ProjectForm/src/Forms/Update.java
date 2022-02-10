package Forms;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import Dbms.UserTable;
import HelperPack.Center;

public class Update extends JFrame{
	Toolkit kit=Toolkit.getDefaultToolkit();
	JButton b1=null;
	private static final int bool_f=0,bool_l=1,bool_e=2,bool_m=3,bool_c=4,bool_a=5;
	private boolean[] bool={false,false,false,false,false,false};
	UserTable ut=new UserTable();
	JTextField t3;
	String prev=null;
	ArrayList<String> emails=null;
	public Update() {
		emails= ut.getColumn("select email from users");
		setTitle("Edit Profile");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(500,550);
		setLocation(Center.getCenter(kit.getScreenSize(), getSize()));
		setIconImage(kit.createImage("E:\\eclipse\\ProjectForm\\src\\img\\icon2.png"));
		setResizable(false);
		Container panel=getContentPane();
		panel.setLayout(null);
		//title of form
		JLabel title=new JLabel("Edit Profile",SwingConstants.CENTER);
		title.setSize(160,40);
		title.setFont(new Font(Font.SANS_SERIF,Font.BOLD,18));
		title.setLocation(170,20);
		//first name
		JLabel l1=new JLabel("First Name");
		l1.setLocation(80,80);
		l1.setSize(100,20);
		JTextField t1=new JTextField(20);
		t1.setLocation(200,80);
		t1.setSize(200,20);
		ActionListener trans = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				((Component)e.getSource()).transferFocus();
			}
		};
		t1.addActionListener(trans);
		t1.getDocument().addDocumentListener(new Register.TextListener() {
			@Override
			public void update(DocumentEvent e) {
				if(t1.getText().length()<1) bool[bool_f]=false;
				else bool[bool_f]=true;
				b1Render();
			}
		});
		//last name
		JLabel l2=new JLabel("Last Name");
		l2.setLocation(80,120);
		l2.setSize(100,20);
		JTextField t2=new JTextField(20);
		t2.setLocation(200,120);
		t2.setSize(200,20);
		t2.getDocument().addDocumentListener(new Register.TextListener() {
			@Override
			public void update(DocumentEvent e) {
				if(t2.getText().length()<1) bool[bool_l]=false;
				else bool[bool_l]=true;
				b1Render();
			}
		});
		t2.addActionListener(trans);
		//email
		JLabel l3=new JLabel("E-mail");
		l3.setLocation(80,160);
		l3.setSize(100,20);
		t3=new JTextField(20);
		t3.setLocation(200,160);
		t3.setSize(200,20);
		t3.addActionListener(trans);
		t3.setForeground(Color.RED);
		t3.getDocument().addDocumentListener(new Register.TextListener() {
			@Override
			public void update(DocumentEvent e) {
				bool[bool_e]=Register.isEmail(t3.getText());
				if(bool[bool_e]) {
					if(emails.contains(t3.getText())) {
						JOptionPane.showMessageDialog(t3, "Mail ID already exist", "Error", JOptionPane.ERROR_MESSAGE);
						t3.setForeground(Color.RED);
						bool[bool_e]=false;
					}
					else {
						t3.setForeground(Color.black);
					}
				}
				else t3.setForeground(Color.RED);
				b1Render();
			}
		});
		//age
		JLabel l4=new JLabel("Age");
		l4.setLocation(80, 200);
		l4.setSize(100,20);
		JTextField t4=new JTextField(20);
		t4.setLocation(200,200);
		t4.setSize(50,20);
		t4.addActionListener(trans);
		t4.getDocument().addDocumentListener(new Register.TextListener() {
			@Override
			public void update(DocumentEvent e) {
				bool[bool_a]=t4.getText().length()<4 && t4.getText().length()>0;
				System.out.println(bool[bool_a]);
				if(bool[bool_a]) t4.setForeground(Color.black);
				else t4.setForeground(Color.red);
				b1Render();
			}
		});
		//gender
		JLabel l6=new JLabel("Gender");
		l6.setLocation(80,240);
		l6.setSize(100,20);
		JRadioButton rb1=new JRadioButton("Male      ");
		rb1.setLocation(195,240);
		rb1.setSize(100,20);
		JRadioButton rb2=new JRadioButton("Female ");
		rb2.setLocation(195,265);
		rb2.setSize(100,20);
		ButtonGroup rbm=new ButtonGroup();
		rbm.add(rb1);
		rbm.add(rb2);
		//date of birth
		JLabel l7=new JLabel("Date of birth");
		l7.setLocation(80,300);
		l7.setSize(100,20);
		//date
		Integer[] nums=new Integer[31];
		for(int i=0;i<nums.length;i++) nums[i]=i+1;
		JComboBox<Integer> t7a=new JComboBox<Integer>(nums);
		t7a.setLocation(200,300);
		t7a.setSize(40,20);
		t7a.addActionListener(trans);
		//month
		String[] months= {"JAN","FEB","MAR","APR","MAY","JUN","JUL","AUG","SEP","OCT","NOV","DEC"};
		JComboBox<String> t7b=new JComboBox<String>(months);
		t7b.setLocation(250,300);
		t7b.setSize(60,20);
		t7b.addActionListener(trans);
		//year
		Integer[] years=new Integer[100];
		int year=Calendar.getInstance().get(Calendar.YEAR);
		for(int i=year-99,ind=0;i<=year;i++,ind++) years[ind]=i;
		JComboBox<Integer> t7c=new JComboBox<Integer>(years);
		t7c.setLocation(320,300);
		t7c.setSize(55,20);
		t7c.setSelectedIndex(99);
		t7c.addActionListener(trans);
		//mobile number
		JLabel l8=new JLabel("Mobile Number");
		l8.setLocation(80,340);
		l8.setSize(100,20);
		JTextField t8=new JTextField(20);
		t8.setLocation(200,340);
		t8.setSize(130,20);
		t8.getDocument().addDocumentListener(new Register.TextListener() {
			@Override
			public void update(DocumentEvent e) {
				bool[bool_m]=Register.isMobileNumber(t8.getText());
				b1Render();
				if(bool[bool_m]) t8.setForeground(Color.BLACK);
				else t8.setForeground(Color.RED);
			}
		});
		t8.addActionListener(trans);
		//city
		JLabel l9=new JLabel("City");
		l9.setLocation(80,380);
		l9.setSize(100,20);
		String[] cities= {"--- select ---","Chennai","Coimbatore","Tiruppur","Salem","Erode","Tiruchi","Madurai"};
		JComboBox<String> t9=new JComboBox<String>(cities);
		t9.setLocation(200,380);
		t9.setSize(100,20);
		t9.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				bool[bool_c]=(t9.getSelectedIndex()==0)? false : true;
				b1Render();
				t9.transferFocus();
			}
		});
		//reset password
		JButton b2=new JButton("Reset Password");
		b2.setSize(140, 25);
		b2.setLocation(260,440);
		//submit button
		b1=new JButton("Edit");
		b1.setSize(100, 25);
		b1.setLocation(80,440);
		b1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String[] arr= {t1.getText(),t2.getText(),t3.getText(),t4.getText(),
						(rb1.isSelected())? "M":"F",t8.getText(),t9.getSelectedItem().toString(),
						t7a.getSelectedItem().toString()+" "+t7b.getSelectedItem().toString()+" "+t7c.getSelectedItem().toString(),prev };
				ut.update(arr);
				dispose();
			}
		});
		b2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ForgotPassword fp=new ForgotPassword();
				fp.t1.setText(t3.getText());
			}
		});
		
		panel.add(title);
		panel.add(l1);
		panel.add(t1);
		panel.add(l2);
		panel.add(t2);
		panel.add(l3);
		panel.add(t3);
		panel.add(l4);
		panel.add(t4);
		panel.add(l6);
		panel.add(rb1);
		panel.add(rb2);
		panel.add(l7);
		panel.add(t7a);
		panel.add(t7b);
		panel.add(t7c);
		panel.add(l8);
		panel.add(t8);
		panel.add(l9);
		panel.add(t9);
		panel.add(b1);
		panel.add(b2);
		
		setVisible(true);
		Login l=new Login();
		l.addWindowListener(new WindowAdapter() {
			public void windowClosed(WindowEvent e) {
				String[][] table=l.ut.getResult("select firstname,lastname,email,age,gender"
						+",dateofbirth,mobilenumber,city from users where email='"+l.EMAIL_ID+"'", 8);
				t1.setText(table[0][0]);
				t2.setText(table[0][1]);
				emails.remove(table[0][2]);
				prev=table[0][2];
				t3.setText(table[0][2]);
				t4.setText(table[0][3]);
				if(table[0][4].equals("M")) rb1.setSelected(true); else rb2.setSelected(true);
				t7a.setSelectedItem(Integer.parseInt( table[0][5].substring(8,10)));
				t7b.setSelectedIndex(Integer.parseInt( table[0][5].substring(5, 7))-1);
				t7c.setSelectedItem(Integer.parseInt( table[0][5].substring(0, 4)));
				t8.setText(table[0][6]);
				t9.setSelectedItem(table[0][7]);
				b1.setEnabled(true);
			}
		});
	}
	private void b1Render(){
		boolean t=true;
		for(boolean i: bool) { 
			if(!i) {
				t=false;
				break;
			}
		}
		System.out.println(Arrays.toString(bool));
		b1.setEnabled(t);
	}

	public static void main(String[] args) {
		new Update();
	}
}