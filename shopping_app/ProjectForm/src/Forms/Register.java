package Forms;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.regex.Pattern;
import javax.swing.*;
import javax.swing.event.*;

import Dbms.UserTable;
import HelperPack.*;

public class Register extends JFrame{
	Toolkit kit=Toolkit.getDefaultToolkit();
	JButton b1=null;
	ArrayList<String> emails;
	public static final int bool_f=0,bool_l=1,bool_e=2,bool_p=3,bool_cp=4,bool_g=5,bool_m=6,bool_c=7;
	boolean[] bool={false,false,false,false,false,false,false,false};
	public Register() {
		UserTable ut=new UserTable();
		b1=new JButton("Register");
		b1.setEnabled(false);
		setTitle(" Registation Form");
		setLayout(null);
		setSize(490,580);
		setLocation(Center.getCenter(kit.getScreenSize(), getSize()));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setIconImage(kit.createImage("E:\\eclipse\\ProjectForm\\src\\img\\icon2.png"));
		setResizable(false);
		Container panel=getContentPane();
		panel.setLayout(null);
		
		JLabel title=new JLabel("Registration Form",SwingConstants.CENTER);
		title.setSize(160,40);
		title.setFont(new Font(Font.SANS_SERIF,Font.BOLD,18));
		title.setLocation(160,20);
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
		t1.getDocument().addDocumentListener(new TextListener() {
			@Override
			public void update(DocumentEvent e) {
				if(t1.getText().length()<1) bool[bool_f]=false;
				else bool[bool_f]=true;
				b1Render();
			}
		});
		JLabel l2=new JLabel("Last Name");
		l2.setLocation(80,120);
		l2.setSize(100,20);
		JTextField t2=new JTextField(20);
		t2.setLocation(200,120);
		t2.setSize(200,20);
		t2.getDocument().addDocumentListener(new TextListener() {
			@Override
			public void update(DocumentEvent e) {
				if(t2.getText().length()<1) bool[bool_l]=false;
				else bool[bool_l]=true;
				b1Render();
			}
		});
		t2.addActionListener(trans);
		JLabel l3=new JLabel("E-mail");
		l3.setLocation(80,160);
		l3.setSize(100,20);
		JTextField t3=new JTextField(20);
		t3.setLocation(200,160);
		t3.setSize(200,20);
		t3.addActionListener(trans);
		t3.setForeground(Color.RED);
		t3.getDocument().addDocumentListener(new TextListener() {
			@Override
			public void update(DocumentEvent e) {
				bool[bool_e]=isEmail(t3.getText());
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
		JLabel l4=new JLabel("Create Password");
		l4.setLocation(80,200);
		l4.setSize(100,20);
		JPasswordField t4=new JPasswordField(20);
		t4.setEchoChar('*');
		t4.setToolTipText("<html><p width=\"200\">"
							+"password should<br>"
							+"&emsp*have atleast 8 charcters<br>"
							+"&emsp*contain a uppercase letter<br>"
							+"&emsp*contain a special character<br>"
							+"&emsp*contain a number"
							+"</p></html>");
		t4.setLocation(200,200);
		t4.setSize(200,20);
		t4.setForeground(Color.RED);
		t4.getDocument().addDocumentListener(new TextListener() {
			@Override
			public void update(DocumentEvent e) {
				int passcount=isPassword(t4.getText());
				Graphics g=panel.getGraphics();
				g.setColor(Color.GRAY);
				int start=201;
				g.fillRoundRect(start+0, 222, 45, 4, 2, 2);
				g.fillRoundRect(start+50, 222, 45, 4, 2, 2);
				g.fillRoundRect(start+100, 222, 45, 4, 2, 2);
				g.fillRoundRect(start+150, 222, 45, 4, 2, 2);
				
				g.setColor(new Color(0,255,127));
				for(int i=0;i<passcount;i++) {
					g.fillRoundRect(start+50*i, 222, 45, 4, 2, 2);
				}
				
				bool[bool_p]=(passcount==4)? true:false;
				if(bool[bool_p]) t4.setForeground(Color.black);
				else t4.setForeground(Color.RED);
				b1Render();
			}
		});
		t4.addActionListener(trans);
		JLabel l5=new JLabel("Confirm Password");
		l5.setLocation(80,240);
		l5.setSize(120,20);
		JPasswordField t5=new JPasswordField(20);
		t5.setLocation(200,240);
		t5.setSize(200,20);
		t5.setEchoChar('*');
		t5.getDocument().addDocumentListener(new TextListener() {
			@Override
			public void update(DocumentEvent e) {
				int passcount=isPassword(t5.getText());
				Graphics g=panel.getGraphics();
				g.setColor(Color.GRAY);
				int start=201;
				g.fillRoundRect(start+0, 262, 45, 4, 2, 2);
				g.fillRoundRect(start+50, 262, 45, 4, 2, 2);
				g.fillRoundRect(start+100, 262, 45, 4, 2, 2);
				g.fillRoundRect(start+150, 262, 45, 4, 2, 2);
				
				g.setColor(new Color(0,255,127));
				for(int i=0;i<passcount;i++) {
					g.fillRoundRect(start+50*i, 262, 45, 4, 2, 2);
				}
				bool[bool_cp]=t4.getText().equals(t5.getText());
				if(bool[bool_cp]) t5.setForeground(Color.black);
				else t5.setForeground(Color.RED);
				b1Render();
			}
		});
		t5.addActionListener(trans);
		JLabel l6=new JLabel("Gender");
		l6.setLocation(80,280);
		l6.setSize(100,20);
		
		JRadioButton rb1=new JRadioButton("Male      ");
		rb1.setLocation(195,280);
		rb1.setSize(100,20);
		JRadioButton rb2=new JRadioButton("Female ");
		rb2.setLocation(195,305);
		rb2.setSize(100,20);
		ButtonGroup rbm=new ButtonGroup();
		rb1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				bool[bool_g]=true;
				b1Render();
			}
		});
		rb1.addActionListener(trans);
		rb2.addActionListener(rb1.getActionListeners()[0]);
		rb2.addActionListener(trans);
		rbm.add(rb1);
		rbm.add(rb2);
		
		JLabel l7=new JLabel("Date of birth");
		l7.setLocation(80,340);
		l7.setSize(100,20);
		
		Integer[] nums=new Integer[31];
		for(int i=0;i<nums.length;i++) nums[i]=i+1;
		JComboBox<Integer> t7a=new JComboBox<Integer>(nums);
		t7a.setLocation(200,340);
		t7a.setSize(40,20);
		t7a.addActionListener(trans);
		
		String[] months= {"JAN","FEB","MAR","APR","MAY","JUN","JUL","AUG","SEP","OCT","NOV","DEC"};
		JComboBox<String> t7b=new JComboBox<String>(months);
		t7b.setLocation(250,340);
		t7b.setSize(60,20);
		t7b.addActionListener(trans);
		
		Integer[] years=new Integer[100];
		int year=Calendar.getInstance().get(Calendar.YEAR);
		for(int i=year-99,ind=0;i<=year;i++,ind++) years[ind]=i;
		JComboBox<Integer> t7c=new JComboBox<Integer>(years);
		t7c.setLocation(320,340);
		t7c.setSize(55,20);
		t7c.setSelectedIndex(99);
		t7c.addActionListener(trans);
		
		JLabel l8=new JLabel("Mobile Number");
		l8.setLocation(80,380);
		l8.setSize(100,20);
		
		JTextField t8=new JTextField(20);
		t8.setLocation(200,380);
		t8.setSize(130,20);
		t8.getDocument().addDocumentListener(new TextListener() {
			@Override
			public void update(DocumentEvent e) {
				bool[bool_m]=isMobileNumber(t8.getText());
				b1Render();
				if(bool[bool_m]) t8.setForeground(Color.BLACK);
				else t8.setForeground(Color.RED);
			}
		});
		t8.addActionListener(trans);
		
		JLabel l9=new JLabel("City");
		l9.setLocation(80,420);
		l9.setSize(100,20);
		
		String[] cities= {"--- select ---","Chennai","Coimbatore","Tiruppur","Salem","Erode","Tiruchi","Madurai"};
		JComboBox<String> t9=new JComboBox<String>(cities);
		t9.setLocation(200,420);
		t9.setSize(100,20);
		t9.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				bool[bool_c]=(t9.getSelectedIndex()==0)? false : true;
				b1Render();
				t9.transferFocus();
			}
		});
		
		b1.setSize(100, 25);
		b1.setLocation(280,480);
		JButton b2=new JButton("Reset");
		b2.setSize(100, 25);
		b2.setLocation(100,480);
		b1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String[] arr= {t1.getText(),t2.getText(),t5.getText(),t3.getText(),(year-years[t7c.getSelectedIndex()])+"",(rb1.isSelected())? "M":"F",t8.getText(),t9.getSelectedItem().toString(),t7a.getSelectedItem().toString()+" "+t7b.getSelectedItem().toString()+" "+t7c.getSelectedItem().toString() };
				new UserTable().insert(arr);
				System.exit(EXIT_ON_CLOSE);
			}
		});
		b2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				t1.setText("");
				t2.setText("");
				t3.setText("");
				t4.setText("");
				t5.setText("");
				t7a.setSelectedIndex(0);
				t7b.setSelectedIndex(0);
				t7c.setSelectedIndex(99);
				t8.setText("");
				t9.setSelectedIndex(0);
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
		panel.add(l5);
		panel.add(t5);
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
		emails=ut.getColumn("select email from Users");
	}
	protected static boolean isMobileNumber(String text) {
		Pattern mn=Pattern.compile("[0-9]{10}");
		return mn.matcher(text).matches();
	}
	protected static int isPassword(String text) {
		boolean[] p= {(text.length()>7)? true:false,Pattern.compile("[!@#$%^&*_]").matcher(text).find(),Pattern.compile("[A-Z]").matcher(text).find(),Pattern.compile("[0-9]").matcher(text).find()};
		int count=0;
		for(boolean i:p) if(i) count++;
		return count;
	}
	private void b1Render(){
		System.out.println();
		boolean t=true;
		for(boolean i: bool) { 
			if(!i) {
				t=false;
				break;
			}
		}
		b1.setEnabled(t);
	}
	protected static boolean isEmail(String text) {
		Pattern mn=Pattern.compile("[0-9a-zA-Z]+[@][a-zA-Z]+[.].+");
		return mn.matcher(text).matches();
	}
	public static interface TextListener extends DocumentListener {
	    void update(DocumentEvent e);
	    @Override
	    default void insertUpdate(DocumentEvent e) {
	        update(e);
	    }
	    @Override
	    default void removeUpdate(DocumentEvent e) {
	        update(e);
	    }
	    @Override
	    default void changedUpdate(DocumentEvent e) {
	        update(e);
	    }
	}
	public static void main(String[] args) {
		new Register();
	}
}