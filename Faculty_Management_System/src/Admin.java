import java.util.Scanner;
import java.sql.*;

public class Admin {
	int admin_id;
	String admin_username;
	String admin_firstname;
	String admin_lastname;
	String admin_password;
	
	Faculty faculty_obj=new Faculty();
	

	public void registerAdmin(Admin obj)throws Exception {
		
		String url = "jdbc:mysql://localhost:3306/faculty?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String uname="root";
		String pass="";
		String query="insert into admin (firstname,lastname,username,password) values (?,?,?,?)";
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection(url,uname,pass);
		PreparedStatement st = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
		
		Scanner sc=new Scanner(System.in);
		st.setInt(1,obj.admin_id);
		System.out.println("Enter your First Name:");
		obj.admin_firstname=sc.next();
		st.setString(1,obj.admin_firstname);
		System.out.println("Enter your Last Name:");
		obj.admin_lastname=sc.next();
		st.setString(2,obj.admin_lastname);
		System.out.println("Enter your User Name:");
		obj.admin_username=sc.next();
		st.setString(3,obj.admin_username);
		System.out.println("Enter password:");
		obj.admin_password=sc.next();
		st.setString(4,obj.admin_password);
		st.executeUpdate();
		
		ResultSet rs = st.getGeneratedKeys();
		rs.next();
		int id=rs.getInt(1);
		System.out.println("Registration succesfull.....\n Your ID is "+id);
	}
	public Boolean loginAdmin(Admin obj) throws Exception {
		Scanner sc=new Scanner(System.in);
		int flag=0;
		String name = null;
		
		String url = "jdbc:mysql://localhost:3306/faculty?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String uname="root";
		String pass="";
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection(url,uname,pass);
		System.out.println("Enter your User Name:");
		String username2=sc.next();
		System.out.println("Enter your Password:");
		String password2=sc.next();
		String query="SELECT * FROM `admin` WHERE username = ? AND password = ? ";
		PreparedStatement pst =con.prepareStatement(query);
		pst.setString(1,username2);
		pst.setString(2, password2);
		ResultSet rs = pst.executeQuery();
		if(rs.next()){
			String sname=rs.getString("firstname");
			System.out.println("  "+sname);
			name=sname;
		}
		else {
			System.out.println("Username or password is Incorrect");
			return false;
			}
		System.out.println("Login succesfull.....");
		System.out.println("\nWelcome Back '"+name+"'");
		return true;
	}
	void changePassword() throws Exception {
		Scanner sc=new Scanner(System.in);

		String url = "jdbc:mysql://localhost:3306/faculty?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String uname="root";
		String pass="";
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection(url,uname,pass);
		System.out.println("Enter your Old Password:");
		String password2=sc.next();
		String query="SELECT * FROM `admin` WHERE password = ? ";
		PreparedStatement pst =con.prepareStatement(query);
		pst.setString(1, password2);
		ResultSet rs = pst.executeQuery();
		if(rs.next()) {
			//query="UPDATE admin SET password = ? ";
			query="UPDATE admin SET password = MD5(?) WHERE id = 123461";
			pst =con.prepareStatement(query);
			System.out.print("Enter your New passsword");
			pst.setString(1, sc.next());
			pst.executeUpdate();
			System.out.println("Password Updated");
		}
		else {
			System.out.println("Entered Password is Incorrect");
		}
	}
}