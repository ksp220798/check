import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Leave {
	int leave_id;
	int leave_emp_id;
	String leave_status;
	String leave_type;
	String leave_description;
	String leave_to;
	String leave_from;
	
	
	void setLeave(int id) throws Exception{
		String url = "jdbc:mysql://localhost:3306/faculty?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String uname="root";
		String pass="";
		Class.forName("com.mysql.cj.jdbc.Driver");
		String query="UPDATE leave_request SET medical_leave_availd = ?,casual_leave_availd = ? WHERE faculty_id = ?";
		Connection con=DriverManager.getConnection(url,uname,pass);
		PreparedStatement st =con.prepareStatement(query);
		st.setInt(3, id);
		st.setInt(1, 10);
		st.setInt(2, 14);
		st.executeUpdate();
		System.out.println("Leave updated in set leave "+id+" Currente" );
	}
	
	
	public void addLeave() throws Exception {
		Scanner sc=new Scanner(System.in);	
		System.out.println("Eneter your ID");
		int id=Integer.parseInt(sc.next());
		int flag=0;
		String url = "jdbc:mysql://localhost:3306/faculty?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String uname="root";
		String pass="";
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection(url,uname,pass);
		
		String query="SELECT * FROM faculty_data WHERE faculty_id = ?";
		String query2="insert into leave_request (faculty_id,faculty_firstname,faculty_lastname,faculty_type,leave_type,leave_status) values (?,?,?,?,?,?)";
		PreparedStatement pst =con.prepareStatement(query);
		PreparedStatement ust = con.prepareStatement(query2, Statement.RETURN_GENERATED_KEYS);
		pst.setInt(1,id);
		ResultSet rs = pst.executeQuery();
		if(rs.next()) {
			
			ust.setInt(1,rs.getInt("faculty_id"));
			ust.setString(2, rs.getString("faculty_firstname"));
			ust.setString(3, rs.getString("faculty_lastname"));
			ust.setString(4, rs.getString("faculty_type"));
			System.out.println("Choose Leave Type\n\t\t1. Casual Leave\t\t2. Medical Leave");
			int chLeave=Integer.parseInt(sc.next());
			
			if(chLeave==1) {
				ust.setString(5, "Casual Leave");
				flag=1;
			}
			else if(chLeave==2) {
				ust.setString(5, "Medical Leave");
				flag=2;
			}
			
			ust.setString(6,"NULL");
			ust.executeUpdate();
			ResultSet urs = ust.getGeneratedKeys();
			int  uid=0;
			if(urs.next())
			 uid=urs.getInt(1);
			
			
			query="SELECT * FROM faculty_data WHERE faculty_id = ?";
			pst =con.prepareStatement(query);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			rs.next();
			int c_leave=rs.getInt("casual_leave");
			int m_leave=rs.getInt("medical_leave");
			if(flag==1) {
				 query="UPDATE faculty_data SET casual_leave = ? WHERE faculty_id = ?";
				 pst =con.prepareStatement(query);
				 pst.setInt(1, c_leave-1);
				 pst.setInt(2,id);
				 c_leave--;
				 System.out.println("\n\t!!!Your "+c_leave+" Casual Leave Remaining!!!");
				 pst.executeUpdate();
			 }	
			 else if(flag==2) {
				 query="UPDATE faculty_data SET medical_leave = ? WHERE faculty_id = ?";
				 pst =con.prepareStatement(query);
				 pst.setInt(1, m_leave-1);
				 pst.setInt(2,id);
				 m_leave--;
				 System.out.println("\n\t!!!Your "+m_leave+" Medical Leave Remaining!!!");
				 pst.executeUpdate();
			 }
			System.out.println("Leave Applied");
			System.out.println("Your Leave ID is "+uid); 
		}
	}
	void statusLeave() throws Exception {
		String url = "jdbc:mysql://localhost:3306/faculty?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String uname="root";
		String pass="";
		Class.forName("com.mysql.cj.jdbc.Driver");
		String query="SELECT * FROM faculty_data";
		Connection con=DriverManager.getConnection(url,uname,pass);
		PreparedStatement st =con.prepareStatement(query);
		ResultSet rs = st.executeQuery();
		System.out.println("\t\t\t\tALL FACULTY LEAVE STATUS :\n");
		System.out.println("\t\tFaculty Id\t\tName\t\tCasual Leave\tMedical Leave");
		while(rs.next()) {
			System.out.println("\t\t"+rs.getInt("faculty_id")+"\t\t"+rs.getString("faculty_firstname")+" "+rs.getString("faculty_lastname")+"\t\t"+rs.getString("casual_leave")+"\t\t"+rs.getString("medical_leave"));
		}	
	}
	
	public void deleteLeave() throws Exception {
		Scanner sc=new Scanner(System.in);
		
		String url = "jdbc:mysql://localhost:3306/faculty?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String uname="root";
		String pass="";
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection(url,uname,pass);
		
		System.out.println("Enter Leave ID ");
		int id=Integer.parseInt(sc.next());
		String query="DELETE FROM `leave_request` WHERE leave_id = ?";
		PreparedStatement pst =con.prepareStatement(query);
		pst.setInt(1,id);
		 int row = pst.executeUpdate();
		System.out.println("Leave Canceled.....");
	}
	public void viewLeave() throws Exception {
		String url = "jdbc:mysql://localhost:3306/faculty?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String uname="root";
		String pass="";
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection(url,uname,pass);
		
		String query="SELECT * FROM leave_request";
		PreparedStatement pst =con.prepareStatement(query);
		ResultSet rs = pst.executeQuery();
		System.out.println("\t\t\t\tALL STATUS:\n");
		System.out.println("\t\tLeave Id\tName\t\tLeave Type\t\tLeave Status");
		while(rs.next()) {
			System.out.print("\t\t"+rs.getInt("leave_id")+"\t\t"+rs.getString("faculty_firstname")+" "+rs.getString("faculty_lastname")+"\t"+rs.getString("leave_type")+"\t\t");
			if(rs.getString("leave_status").equals("NULL")) {
				System.out.println("Not Viewed");
			}
			else {
				System.out.println(rs.getString("leave_status"));
			}
		}	
	}
	public void requestLeave(Leave obj) throws Exception {
		
		int flag=0,count=1;
		String url = "jdbc:mysql://localhost:3306/faculty?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String uname="root";
		String pass="";
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection(url,uname,pass);
		String query="SELECT * FROM leave_request WHERE leave_status = 'NULL'";
		PreparedStatement pst =con.prepareStatement(query);
		ResultSet rs = pst.executeQuery();
		
		Scanner sc=new Scanner(System.in);
		System.out.println("\n------------------------Leave Requests------------------------");
		while(true) {
			if(rs.next()) {
				System.out.println("\n\tS.N.\tFaculty Id"+"\tFaculty Name"+"\tFaculty Type"+"\tLeave Type");
				System.out.println("\t"+count+"\t"+rs.getInt("faculty_id")+"\t\t"+rs.getString("faculty_firstname")+" "+rs.getString("faculty_lastname")+"\t"+rs.getString("faculty_type")+"\t"+rs.getString("leave_type")+"\n");
				System.out.print("\tSelect Option:\n\t1.Accept Leave Request\t\t2.Discard Leave Request\t\t3.Go Back");
				int select=Integer.parseInt(sc.next());
				flag=1;
				count++;
				int id=rs.getInt("faculty_id");
				if(select==1) {
					query="UPDATE leave_request SET leave_status= 'Approved' WHERE faculty_id = ?";
					pst =con.prepareStatement(query);
					pst.setInt(1,id);
					pst.executeUpdate();
					System.out.println("\t!!! Leave Request Approved !!!");
				}
				else if(select==2) {
					query="UPDATE leave_request SET leave_status= 'Discarded' WHERE faculty_id = ?";
					pst =con.prepareStatement(query);
					pst.setInt(1,id);
					pst.executeUpdate();
			     	System.out.println("\t!!! Leave Request Discarded !!!");
				}
				else if(select==3) {
					break;
				}
			}
			else if(flag==0) {
				System.out.println("\n!!!There is No any Leave request!!!");
				return;
			}
			else {
				break;
			}
		}
		
	}
}
