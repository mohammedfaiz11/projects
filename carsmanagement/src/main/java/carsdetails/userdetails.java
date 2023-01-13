package carsdetails;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;
import javax.persistence.*;

@Entity
@Table
public class userdetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int uid;
	private String uname,mobile,email,password;

	public userdetails() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	public userdetails(int uid, String uname, String mobile, String email, String password) {
		super();
		this.uid = uid;
		this.uname = uname;
		this.mobile = mobile;
		this.email = email;
		this.password = password;
	}



	public int getUid() {
		return uid;
	}



	public void setUid(int uid) {
		this.uid = uid;
	}



	public String getUname() {
		return uname;
	}



	public void setUname(String uname) {
		this.uname = uname;
	}



	public String getMobile() {
		return mobile;
	}



	public void setMobile(String mobile) {
		this.mobile = mobile;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public boolean checkuser(String usn, String up) throws SQLException
	{
		String url="jdbc:mysql://localhost:3306/cricketdb? autoReconnect=true & useSSL=false", un="root", pass="kashif464";
		//Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection(url,un,pass);
		//Creating connection
		Statement st = con.createStatement();
		con.setAutoCommit(false);
		String ueml="",upass="";
		int uid=0;
		
		//String sqlid=;
		ResultSet rs1 = st.executeQuery("select uid from userdetails where email='"+usn+"'");
		while(rs1.next())
		{
			uid=rs1.getInt(1);
		}
		
		//String sqleml=;
		ResultSet rs2 = st.executeQuery("select email from userdetails where uid="+uid);
		while(rs2.next())
		{
			ueml=rs2.getString(1);
		}
		
		//String sqlpass=;
		ResultSet rs3 = st.executeQuery("select password from userdetails where uid="+uid);
		while(rs3.next())
		{
			upass=rs3.getString(1);
		}
		ueml=ueml.trim();
		upass=upass.trim();
		
		if(ueml.equals(usn) && upass.equals(up))
			return true;
		else
			return false;
	}
}
