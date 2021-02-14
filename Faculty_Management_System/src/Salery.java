import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Scanner;

public class Salery {
	int salery;
	String salery_status;
	Date date;
	
	
	
	
	
	void makePayment(String date) throws Exception  {
		String url = "jdbc:mysql://localhost:3306/faculty?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String uname="root";
		String pass="";
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection(url,uname,pass);
		
		String query="UPDATE salery SET salery_date = ? ,salery_status= ? WHERE salery_status=?";
		PreparedStatement st =con.prepareStatement(query);
		st.setString(1, date);
		st.setString(2,"Paid");
		st.setString(3,"NULL");
		st.executeUpdate();
		System.out.println("Payment Made for All Faculty");
	}
	void viewSalery() throws Exception {
		
		int flag=0;
		Scanner sc=new Scanner(System.in);
		String url = "jdbc:mysql://localhost:3306/faculty?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String uname="root";
		String pass="";
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection(url,uname,pass);
		String query="SELECT * FROM salery WHERE faculty_id= ? AND salery_status= ?";
		PreparedStatement pst =con.prepareStatement(query);
		System.out.println("Enetr Your ID");
		int id=Integer.parseInt(sc.next());
		pst.setInt(1,id);
		pst.setString(2, "Paid");
		ResultSet rs = pst.executeQuery();
		
		System.out.println("\t\t\t\tSalery Payment Status :\n");
		
		while(rs.next()) {
			System.out.println("\t\tSalery Id\tName\t\tSalery Status\t\tAmount\t\tPayement Made on");
			System.out.println("\t\t"+rs.getInt("salery_id")+"\t\t"+rs.getString("faculty_firstname")+" "+rs.getString("faculty_lastname")+"\t"+rs.getString("salery_status")+"\t\t\t"+rs.getString("salery_amount")+"\t\t"+rs.getString("salery_date"));
			flag=1;
		}
		if(flag!=1)
		System.out.println("\t\t\t!!!Your Payement is Not Done yet!!!");
		
	}
}
