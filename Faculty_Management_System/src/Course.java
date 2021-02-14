import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Course {
	int course_id;
	String course_name;
	String course_type;
	String course_description;
	String course_year;
	String course_registration;
	
	public void addCourse(Course obj) throws Exception {
		Scanner sc=new Scanner(System.in);
		
		String url = "jdbc:mysql://localhost:3306/faculty?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String uname="root";
		String pass="";
		String query="insert into course_data (course_name,course_type,course_year,course_registration,course_description) values (?,?,?,?,?)";
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection(url,uname,pass);
		PreparedStatement st = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
		System.out.println("Enter Course Name:");
		obj.course_name=sc.next();
		st.setString(1, obj.course_name);
		System.out.println("Enter Course Type:");
		st.setString(2,sc.next());
		System.out.println("Enter Course Year");
		st.setString(3,sc.next());
		System.out.println("Enter Course Registration no");
		st.setString(4,sc.next());
		System.out.println("Describe Course\nEnter 'E' for Exit from describe");
		String s1,s2="";
		StringBuilder s4,s3=new StringBuilder();
		while(sc.hasNext()) {
			s1=sc.next();
			if(s1.equals("E"))
				break;
			s3.append(s1);
			s3.append(" ");
		}
		obj.course_registration=s3.toString();
		st.setString(5,obj.course_registration);
		st.executeUpdate();
		ResultSet rs = st.getGeneratedKeys();
		int id=0;
		if(rs.next())
		 id=rs.getInt(1);
		System.out.println(obj.course_name+" Added.....");
		System.out.println("Course ID is "+id);
		
		
	}
	public void deleteCourse(Course obj) throws Exception {
		Scanner sc=new Scanner(System.in);
		
		String url = "jdbc:mysql://localhost:3306/faculty?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String uname="root";
		String pass="";
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection(url,uname,pass);
		
		System.out.println("Enter Course ID for Delete");
		int id=Integer.parseInt(sc.next());
		sc.nextLine();
		String query="DELETE FROM `course_data` WHERE course_id = ?";
		PreparedStatement pst =con.prepareStatement(query);
		pst.setInt(1,id);
		int row = pst.executeUpdate();
		System.out.println("Deletion succesfull.....");

	}
	public void searchCourse(Course obj) throws Exception {
		Scanner sc=new Scanner(System.in);
		
		String url = "jdbc:mysql://localhost:3306/faculty?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String uname="root";
		String pass="";
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection(url,uname,pass);
		
		System.out.println("Enter Course ID for Search");
		int id=Integer.parseInt(sc.next());
		String query="SELECT * FROM `course_data` WHERE course_id = ?";
		PreparedStatement pst =con.prepareStatement(query);
		pst.setInt(1,id);
		ResultSet rs = pst.executeQuery();
		if(rs.next()){
			System.out.println("\t\tId\t\tName\t\ttype\t\tYear\t\tRegistration\t\tDiscription");
			System.out.println("\t\t"+rs.getString("course_id")+"\t\t"+rs.getString("course_name")+" "+"\t\t"+rs.getString("course_type")+"\t\t"+rs.getString("course_year")+"\t\t"+rs.getString("course_registration")+"\t\t"+rs.getString("course_description"));
		}
		else {
			System.out.println("Course Does not Exist!!!");
			return;
			}
	}
	public void editCourse(Course obj) throws Exception {
		Scanner sc=new Scanner(System.in);
		
		String url = "jdbc:mysql://localhost:3306/faculty?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String uname="root";
		String pass="";
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection(url,uname,pass);
		
		String query="UPDATE course_data SET course_name = ?, course_type = ? ,course_year = ? ,course_registration = ? ,course_description = ? WHERE course_id = ?";
		PreparedStatement st =con.prepareStatement(query);
		
		System.out.println("Enter Course ID");
		int id=Integer.parseInt(sc.next());
		System.out.println("Enter Course Name:");
		st.setString(1,sc.next());
		System.out.println("Enter Course Type:");
		st.setString(2,sc.next());
		System.out.println("Enter Course Year");
		st.setString(3,sc.next());
		System.out.println("Enter Course Registration no");
		st.setString(4,sc.next());
		System.out.println("Describe Course\nEnter 'E' for Exit from describe");
		String s1="";
		StringBuilder s3=new StringBuilder();
		while(sc.hasNext()) {
			s1=sc.next();
			if(s1.equals("E"))
				break;
			s3.append(s1);
			s3.append(" ");
		}
		obj.course_registration=s3.toString();
		st.setString(5,obj.course_registration);
		st.setInt(6, obj.course_id);
		 int row = st.executeUpdate();
		
		System.out.println("Information Updated....");
	}
}
