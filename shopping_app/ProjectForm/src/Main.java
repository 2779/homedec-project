import java.awt.*;
import java.awt.event.*;
import java.sql.Date;
import java.util.Calendar;
import java.util.regex.Pattern;
import javax.swing.*;
import Dbms.*;
import Forms.*;
import HelperPack.ImgPack;

public class Main {
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