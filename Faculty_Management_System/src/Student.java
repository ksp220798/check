import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Student {
	int student_id;
	String student_firstname;
	String student_lastname;
	String student_mobile;
	String student_email;
	String student_address;

	
	public void addStudent(Student obj) throws Exception {
		
		String url = "jdbc:mysql://localhost:3306/faculty?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String uname="root";
		String pass="";
		String query="insert into student_data (student_firstname,student_lastname,student_mobile,student_email,student_address) values (?,?,?,?,?)";
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection(url,uname,pass);
		PreparedStatement st = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
		
		Scanner sc=new Scanner(System.in);
		
		System.out.println("Enter Student First Name:");
		obj.student_firstname=sc.next();
		st.setString(1,obj.student_firstname);
		System.out.println("Enter Student Last Name:");
		obj.student_lastname=sc.next();
		st.setString(2,obj.student_lastname);
		System.out.println("Enter Student Mobile:");
		obj.student_mobile=sc.next();
		st.setString(3,obj.student_mobile);
		System.out.println("Enter Student Email");
		obj.student_email=sc.next();
		st.setString(4,obj.student_email);
		System.out.println("Enter Student Address");
		obj.student_address=sc.next();
		st.setString(5,obj.student_address);
		
		st.executeUpdate();
		ResultSet rs = st.getGeneratedKeys();
		int id=0;
		if(rs.next())
		 id=rs.getInt(1);
		System.out.println(obj.student_firstname+" Added.....\n Student ID is "+id);
	}
	public void deleteStudent(Student obj) throws Exception {
		Scanner sc=new Scanner(System.in);
		
		String url = "jdbc:mysql://localhost:3306/faculty?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String uname="root";
		String pass="";
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection(url,uname,pass);
		
		System.out.println("Enter Student ID for delete");
		int id=Integer.parseInt(sc.next());
		sc.nextLine();
		String query="DELETE FROM `student_data` WHERE student_id = ?";
		PreparedStatement pst =con.prepareStatement(query);
		pst.setInt(1,id);
		 int row = pst.executeUpdate();
		System.out.println("Deletion succesfull.....");
	
	}
	public void searchStudent(Student obj) throws Exception {
		Scanner sc=new Scanner(System.in);
		
		String url = "jdbc:mysql://localhost:3306/faculty?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String uname="root";
		String pass="";
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection(url,uname,pass);
		
		System.out.println("Enter Faculty ID");
		int id=Integer.parseInt(sc.next());
		String query="SELECT * FROM `student_data` WHERE student_id = ?";
		PreparedStatement pst =con.prepareStatement(query);
		pst.setInt(1,id);
		ResultSet rs = pst.executeQuery();
		sc.nextLine();
		if(rs.next()){
			System.out.println("\t\tId\t\tName\t\tMobile\t\tEmail\t\tAdress");
			System.out.println("\t\t"+rs.getString("student_id")+"\t\t"+rs.getString("student_firstname")+" "+rs.getString("student_lastname")+"\t"+rs.getString("student_mobile")+"\t\t"+rs.getString("student_email")+rs.getString("student_address"));
			
		}
		else {
			System.out.println("Student Does not Found!!!");
			return;
			}
	}
	public void editStudent(Student obj) throws Exception {
		Scanner sc=new Scanner(System.in);
		
		String url = "jdbc:mysql://localhost:3306/faculty?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String uname="root";
		String pass="";
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection(url,uname,pass);
		
		System.out.println("Enter Student ID");
		int id=Integer.parseInt(sc.next());
		sc.nextLine();
		String query="UPDATE student_data SET student_firstname = ?, student_lastname = ? ,student_mobile = ? ,student_email = ? ,student_address=? WHERE student_id = ?";
		PreparedStatement st =con.prepareStatement(query);
		System.out.println("Enter studet First Name:");
		st.setString(1,sc.next());
		System.out.println("Enter Last Name:");
		st.setString(2,sc.next());
		System.out.println("Enter studet mobile number:");
		st.setString(3,sc.next());
		System.out.println("Enter studet email:");
		st.setString(4,sc.next());
		System.out.println("Enter studet Address:");
		st.setString(5,sc.next());
		st.setInt(6,id);
		 int row = st.executeUpdate();
		 System.out.println("UPDATE  succesfull.....");
	}
	public void viewStudent(Student faculty_obj) throws Exception {
		String url = "jdbc:mysql://localhost:3306/faculty?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String uname="root";
		String pass="";
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection(url,uname,pass);
		String query="SELECT * FROM `student_data";
		PreparedStatement pst =con.prepareStatement(query);
		ResultSet rs = pst.executeQuery();
		System.out.println("\t\t\t\tALL STUDENTS :\n");
		System.out.println("\t\tId\t\tName\t\tMobile\t\tEmail\t\t\tAdress");
		while(rs.next()) {
			System.out.println("\t\t"+rs.getInt("student_id")+"\t\t"+rs.getString("student_firstname")+" "+rs.getString("student_lastname")+"\t"+rs.getString("student_mobile")+"\t"+rs.getString("student_email")+"\t\t"+rs.getString("student_address"));
		}	
	}
}
