package Dbms;

import java.security.spec.*;
import java.sql.*;
import java.util.*;
import javax.crypto.*;
import javax.crypto.spec.*;

public class UserTable extends dbms{
	public UserTable() {
		super("skcet","java");
	}
	
	public String hashp(String s) {
		byte[] salt= {95, 80, 32, 93, 22, -33, 45, -38, -73, 98, 60, -91, -117, -11, 52, 29};
		KeySpec spec = new PBEKeySpec(s.toCharArray(), salt, 65536, 128);
		SecretKeyFactory f;
		byte[] hash=null;
		try {
			f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
			hash = f.generateSecret(spec).getEncoded();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Base64.Encoder enc = Base64.getEncoder();
		return enc.encodeToString(hash);
	}
	
	public boolean isEmailThere(String email) {
		boolean isthere=false;
		try {
			isthere=queryWithResult("select email from users where email='"+email+"'").next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isthere;
	}
	
	public void insert(String[] arr) {
		try {
			PreparedStatement st= conn.prepareStatement("insert into Users values(person.nextval,?,?,?,?,?,?,?,?,TO_DATE(?,'DD MON YYYY'))");
			st.setString(1,arr[0]);       			  //first name
			st.setString(2,arr[1]);       			  //last name
			st.setString(3,hashp(arr[2]));			  //password
			st.setString(4,arr[3]);       			  //email
			st.setInt(   5,Integer.parseInt(arr[4])); //age
			st.setString(6,arr[5]);      			  //gender
			st.setString(7,arr[6]);       			  //mobile number
			st.setString(8,arr[7]);       			  //city
			st.setString(9,arr[8]);                   //DOB
			st.execute();
			System.out.println("inserted successfully");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void update(String[] arr) {
		try {
			PreparedStatement st= conn.prepareStatement("update Users set firstname=?,lastname=?,email=?,age=?,gender=?,mobilenumber=?,city=?,dateofbirth=TO_DATE(?,'DD MON YYYY') where email=?");
			st.setString(1,arr[0]);       			  //first name
			st.setString(2,arr[1]);       			  //last name
			st.setString(3,arr[2]);       			  //email
			st.setInt(   4,Integer.parseInt(arr[3])); //age
			st.setString(5,arr[4]);      			  //gender
			st.setString(6,arr[5]);       			  //mobile number
			st.setString(7,arr[6]);       			  //city
			st.setString(8,arr[7]);                   //DOB
			st.setString(9,arr[8]);
			st.execute();
			System.out.println("inserted successfully");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}