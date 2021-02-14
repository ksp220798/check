import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Faculty extends Leave {
	int faculty_id;
	String faculty_firstname;
	String faculty_lastname;
	String faculty_type;
	String faculty_room;
	String faculty_username;
	String faculty_password;
	String faculty_mobile;
	
	public void addFaculty(Faculty obj) throws Exception {
		
		String url = "jdbc:mysql://localhost:3306/faculty?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String uname="root";
		String pass="";
		String query="insert into faculty_data (faculty_firstname,faculty_lastname,faculty_type,faculty_room,faculty_mobile,casual_leave,medical_leave) values (?,?,?,?,?,?,?)";
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection(url,uname,pass);
		PreparedStatement st =con.prepareStatement(query);
		
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter Faculty First Name:");
		obj.faculty_firstname=sc.next();
		st.setString(1,obj.faculty_firstname);
		System.out.println("Enter Last Name:");
		obj.faculty_lastname=sc.next();
		st.setString(2,obj.faculty_lastname);
		System.out.println("Enter Faculty Type:\n\t1. Permanent\t\t2. Adhoc");
		int type=Integer.parseInt(sc.next());
		if(type==1) {
			obj.faculty_type="Permanent";
		}
		else if(type==2) {
			obj.faculty_type="Adhoc";
		}
		st.setString(3,obj.faculty_type);
		System.out.println("Enter Faculty Room:");
		st.setString(4,sc.next());
		System.out.println("Enter Faculty Mobile Number");
		st.setString(5, sc.next());
		st.setInt(6,14);
		st.setInt(7,10);
		st.executeUpdate();
		
		query ="SELECT faculty_id FROM faculty_data  \r\n" + 
				"ORDER BY  faculty_id DESC  \r\n" + 
				"LIMIT 1; ";
		st =con.prepareStatement(query);
		ResultSet rs = st.executeQuery();
		rs.next();
		int id=rs.getInt(1);
		System.out.println(obj.faculty_firstname+" Added.....\n Faculty ID is "+id);
		
		 query="insert into salery (faculty_id,faculty_firstname,faculty_lastname,salery_amount,salery_status) values (?,?,?,?,?)";
		 Class.forName("com.mysql.cj.jdbc.Driver");
		 st =con.prepareStatement(query);
		 st.setInt(1, id);
		 st.setString(2,obj.faculty_firstname);
		 st.setString(3,obj.faculty_lastname);
		 if(obj.faculty_type=="Permanent") {
			st.setInt(4, 50000);
		 }
		 else if(obj.faculty_type=="Adhoc") {
			st.setInt(4, 35000);
		 }
		 st.setString(5,"NULL");
		 st.executeUpdate();
	}
	public void deleteFaculty(Faculty obj) throws Exception {
		Scanner sc=new Scanner(System.in);
		
		String url = "jdbc:mysql://localhost:3306/faculty?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String uname="root";
		String pass="";
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection(url,uname,pass);
		
		System.out.println("Enter Faculty ID:");
		int facultyId=Integer.parseInt(sc.next());
		String query="DELETE FROM `faculty_data` WHERE faculty_id = ?";
		PreparedStatement pst =con.prepareStatement(query);
		pst.setInt(1,facultyId);
		 int row = pst.executeUpdate();
		System.out.println("Deletion succesfull.....");
	}
	public void searchFaculty(Faculty obj) throws Exception {
		Scanner sc=new Scanner(System.in);
		
		String url = "jdbc:mysql://localhost:3306/faculty?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String uname="root";
		String pass="";
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection(url,uname,pass);
		
		System.out.println("Enter Faculty ID:");
		int facultyId=Integer.parseInt(sc.next());
		String query="SELECT * FROM `faculty_data` WHERE faculty_id = ?";
		PreparedStatement pst =con.prepareStatement(query);
		pst.setInt(1,facultyId);
		ResultSet rs = pst.executeQuery();
		if(rs.next()){
			System.out.println("\t\tId\t\tName\t\tType\t\tMobile No.\t\tFaculty Room");
			System.out.println("\t\t"+rs.getString("faculty_id")+"\t\t"+rs.getString("faculty_firstname")+" "+rs.getString("faculty_lastname")+"\t"+rs.getString("faculty_type")+"\t\t"+rs.getString("faculty_mobile")+"\t\t"+rs.getString("faculty_room"));
			
		}
		else {
			System.out.println("User Does not Found!!!");
			return;
			}
	}
	public void editFaculty(Faculty obj) throws Exception {
		Scanner sc=new Scanner(System.in);
		
		String url = "jdbc:mysql://localhost:3306/faculty?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String uname="root";
		String pass="";
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection(url,uname,pass);
		
		System.out.println("Enter Faculty ID:");
		int facultyId=Integer.parseInt(sc.next());
		String query="UPDATE faculty_data SET faculty_firstname = ?, faculty_lastname = ? ,faculty_type = ? ,faculty_room = ? WHERE faculty_id = ?";
		PreparedStatement st =con.prepareStatement(query);
		System.out.println("Enter Faculty First Name:");
		st.setString(1,sc.next());
		System.out.println("Enter Last Name:");
		st.setString(2,sc.next());
		System.out.println("Enter Faculty Type:");
		st.setString(3,sc.next());
		System.out.println("Enter Faculty Room:");
		st.setString(4,sc.next());
		st.setInt(5,facultyId);
		 int row = st.executeUpdate();
		System.out.println("UPDATE  succesfull.....");
		
	}
	
	public void viewFaculty(Faculty faculty_obj) throws Exception {
		String url = "jdbc:mysql://localhost:3306/faculty?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String uname="root";
		String pass="";
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection(url,uname,pass);
		String query="SELECT * FROM `faculty_data";
		PreparedStatement pst =con.prepareStatement(query);
		ResultSet rs = pst.executeQuery();
		System.out.println("\t\t\t\tALL FACULTIES :\n");
		System.out.println("\t\tId\t\tName\t\tType\t\tFaculty Room");
		while(rs.next()) {
			System.out.println("\t\t"+rs.getString("faculty_id")+"\t\t"+rs.getString("faculty_firstname")+" "+rs.getString("faculty_lastname")+"\t"+rs.getString("faculty_type")+"\t\t"+rs.getString("faculty_room"));
		}	
	}
	public void registerFaculty() throws Exception {
		Scanner sc=new Scanner(System.in);
		String url = "jdbc:mysql://localhost:3306/faculty?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String uname="root";
		String pass="";
		String query="select * FROM faculty_data WHERE faculty_id =?";
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection(url,uname,pass);
		PreparedStatement st =con.prepareStatement(query);
		System.out.println("Enter Your ID:");
		int id= Integer.parseInt(sc.next());
		st.setInt(1, id);
		ResultSet rs = st.executeQuery();
		if(rs.next()) {
			System.out.println("\t\t\t\tInformation :\n");
			System.out.println("\t\tId\t\tName\t\tType\t\tFaculty Room");
			System.out.println("\t\t"+rs.getString("faculty_id")+"\t\t"+rs.getString("faculty_firstname")+" "+rs.getString("faculty_lastname")+"\t"+rs.getString("faculty_type")+"\t\t"+rs.getString("faculty_room"));
			
			query="UPDATE faculty_data SET faculty_username = ?, faculty_password = ? WHERE faculty_id = ?";
			st =con.prepareStatement(query);
			st.setInt(3, id);
			System.out.println("Enter your User Name:");
			st.setString(1,sc.next());
			System.out.println("Enter password:");
			st.setString(2,sc.next());
			st.executeUpdate();
			System.out.println("Registration succesfull.....\n Your ID is "+id);
		}
		else {
			System.out.println("Your are not Registered Contact to Admin");
		}
	}
	public Boolean loginFaculty() throws Exception {
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
		String query="SELECT * FROM `faculty_data` WHERE faculty_username = ? AND faculty_password = ? ";
		PreparedStatement pst =con.prepareStatement(query);
		pst.setString(1,username2);
		pst.setString(2, password2);
		ResultSet rs = pst.executeQuery();
		if(rs.next()){
			String sname=rs.getString("faculty_firstname");
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
	void courseEnrollFaculty() throws Exception {
		
		Scanner sc=new Scanner(System.in);
		String url = "jdbc:mysql://localhost:3306/faculty?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String uname="root";
		String pass="";
		System.out.println("Enter your ID ");
		int id=Integer.parseInt(sc.next()),sn=1;
		String query="SELECT * FROM course_data";
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection(url,uname,pass);
		PreparedStatement st =con.prepareStatement(query);
	
		System.out.println("\t\t\t\tAll Avilable Courses :\n");
		System.out.println("\t\tS.N.\t\tCourse Id\t\tCourse Name\tCourse Type");
		ResultSet rs = st.executeQuery();
		while(rs.next()) {
			System.out.println("\t\t"+sn+"\t\t"+rs.getString("course_id")+"\t\t\t"+rs.getString("course_name")+"\t\t "+rs.getString("course_type"));
			sn++;
		}
		System.out.println("Type Course ID for Enrollment");
		int c_id=Integer.parseInt(sc.next());
		query="SELECT * FROM course_data Where course_id=?";
		st =con.prepareStatement(query);
		st.setInt(1, c_id);
		rs = st.executeQuery();
		rs.next();
		String query2="UPDATE faculty_data SET faculty_course = ? WHERE faculty_id =?";
		st =con.prepareStatement(query2);
		st.setString(1, rs.getString("course_name"));
		st.setInt(2,id);
		st.executeUpdate();
	}
	void updatePassword() throws Exception {
		Scanner sc=new Scanner(System.in);

		String url = "jdbc:mysql://localhost:3306/faculty?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String uname="root";
		String pass="";
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection(url,uname,pass);
		System.out.println("Enter your Old Password:");
		String password2=sc.next();
		String query="SELECT * FROM `faculty_data` WHERE faculty_password = ? ";
		PreparedStatement pst =con.prepareStatement(query);
		pst.setString(1, password2);
		ResultSet rs = pst.executeQuery();
		if(rs.next()) {
			query="UPDATE faculty_data SET faculty_password = ? ";
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
