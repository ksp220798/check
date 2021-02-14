import java.util.Date;
import java.util.Scanner;

public class Faculty_Main {
	public static void main(String args[])throws Exception {
		Scanner sc=new Scanner(System.in);
		Admin admin_obj=new Admin();
		Faculty faculty_obj=new Faculty();
		Student student_obj=new Student();
		Course course_obj=new Course();
		Leave leave_obj=new Leave();
		Salery salery_obj=new Salery();
		while(true) {
			System.out.println("\n------------------------------------------------------------------");
			System.out.print("                  Welcome To Faculty Managment");
			System.out.println("\n------------------------------------------------------------------");
			System.out.print("\t\t\tSelect your Role\n\n\t\t|\t1. Admin\t\t|\n\t\t|\t2. Faculty\t\t|\n\t\t|\t3. Exit\t\t\t|");
			System.out.println("\n------------------------------------------------------------------");
			int ch = 0;
			try {
				ch=Integer.parseInt(sc.next());
			}
			catch(Exception e) {
				System.out.println("Invaid Input");
			}
			switch(ch) {
			case 1:	System.out.println("\n-------------------Welcome To Admin's Portal-------------------");
					System.out.print("\t\t|\t1. Login\t\t|\n\t\t|\t2. Back\t\t\t|");
					System.out.println("\n------------------------------------------------------------------");
					int admin_choice=0;
					try {
						admin_choice=Integer.parseInt(sc.next());
					}
					catch(Exception e) {
						System.out.println("Invaid Input");
					}					
					sc.nextLine();
					if(admin_choice==3) {
						admin_obj.registerAdmin(admin_obj);
					}	
					else if(admin_choice==2) {
						break;
					}
					else if(admin_choice==1) {
						Boolean flag=admin_obj.loginAdmin(admin_obj);
						if(flag) {
							while(true) {
								System.out.println("\n------------------------Admin's Portal------------------------");
								System.out.print("\t\t\tSelect Module\n\n\t\t|\t1.Faculty Module\t\t|\n\t\t|\t2.Student Module\t\t|\n\t\t|\t3.Course Module\t\t\t|\n\t\t|\t4.Leave Module\t\t\t|\n\t\t|\t5.Salery Module\t\t\t|\n\t\t|\t6.Change Password\t\t|\n\t\t|\t7.Log Out\t\t\t|");
								System.out.println("\n------------------------------------------------------------------");
								try {
									admin_choice=Integer.parseInt(sc.next());
								}
								catch(Exception e) {
									System.out.println("Invaid Input");
									break;
								}
								
								sc.nextLine();
						
								if(admin_choice==1) {
									while(true) {
										System.out.println("\n------------------------Faculty Module------------------------");
										System.out.println("\t\t\tSelect Your Choice\n\n\t\t|\t1. Add New Faculty\t\t|\n\t\t|\t2.Edit Faculty\t\t\t|\n\t\t|\t3.Search Faculty\t\t|\n\t\t|\t4.Delete Faculty\t\t|\n\t\t|\t5.View All Facultys\t\t|\n\t\t|\t6.Go Back\t\t\t|");
										System.out.print("\n------------------------------------------------------------------");
										try {
											admin_choice=Integer.parseInt(sc.next());
										}
										catch(Exception e) {
											System.out.println("Invaid Input");
											break;
										}
										
										sc.nextLine();
										if(admin_choice==1) {
											faculty_obj.addFaculty(faculty_obj);
										}
										else if(admin_choice==2) {
											faculty_obj.editFaculty(faculty_obj);
										}
										else if(admin_choice==3) {
											faculty_obj.searchFaculty(faculty_obj);
										}
										else if(admin_choice==4) {
											faculty_obj.deleteFaculty(faculty_obj);
										}
										else if(admin_choice==6) {
											break;
										}
										else if(admin_choice==5) {
											faculty_obj.viewFaculty(faculty_obj);
										}
									}
								}
								else if(admin_choice==6) {
									admin_obj.changePassword();
								}
								else if(admin_choice==2) {
									while(true) {
										System.out.println("\n------------------------Student Module------------------------");
										System.out.print("\t\t\tSelect Your Choice\n\n\t\t|\t1. Add Student\t\t\t|\n\t\t|\t2.Edit Student\t\t\t|\n\t\t|\t3.Search Student\t\t|\n\t\t|\t4.Delete Student's\t\t|\n\t\t|\t5.View All Student\t\t|\n\t\t|\t6.Go Back\t\t\t|");
										System.out.print("\n------------------------------------------------------------------");
										try {
											admin_choice=Integer.parseInt(sc.next());
										}
										catch(Exception e) {
											System.out.println("Invaid Input");
											break;
										}
										
										sc.nextLine();
										if(admin_choice==1) {
											student_obj.addStudent(student_obj);
										}
										else if(admin_choice==2) {
										student_obj.editStudent(student_obj);
										}
										else if(admin_choice==3) {
											student_obj.searchStudent(student_obj);
										}
										else if(admin_choice==4) {
											student_obj.deleteStudent(student_obj);
										}
								
										else if(admin_choice==5) {
											student_obj.viewStudent(student_obj);
										}
										else if(admin_choice==7) {
											break;
										}
										else if(admin_choice==6) {
											break;
										}
									}
								}
								else if(admin_choice==3) {
									while(true) {
										System.out.println("\n------------------------Course Module------------------------");
										System.out.print("\t\t\tSelect Your Choice\n\n\t\t|\t1. Add Course\t\t\t|\n\t\t|\t2.Edit Course\t\t\t|\n\t\t|\t3.Search Course\t\t\t|\n\t\t|\t4.Delete Course\t\t\t|\n\t\t|\t5.Go Back\t\t\t|");
										System.out.print("\n------------------------------------------------------------------");
										try {
											admin_choice=Integer.parseInt(sc.next());
										}
										catch(Exception e) {
											System.out.println("Invaid Input");
											break;
										}
										
										sc.nextLine();
										if(admin_choice==1) {
											course_obj.addCourse(course_obj);
										}
										else if(admin_choice==2) {
											course_obj.editCourse(course_obj);
										}
										else if(admin_choice==3) {
											course_obj.searchCourse(course_obj);
										}
										else if(admin_choice==4) {
											course_obj.deleteCourse(course_obj);
										}
										else if(admin_choice==5) {
											break;
										}
									}
								}
								else if(admin_choice==4) {
									while(true) {
										System.out.println("\n------------------------Leave Module------------------------");
			
										System.out.print("\t\t\tSelect Your Choice\n\n\t\t|\t1.Leave Request\t\t\t|\n\t\t|\t2.Leave Status\t\t\t|\n\t\t|\t3.Go Back\t\t\t|");
										System.out.print("\n------------------------------------------------------------------");
										try {
											admin_choice=Integer.parseInt(sc.next());
										}
										catch(Exception e) {
											System.out.println("Invaid Input");
											break;
										}
										
										if(admin_choice==1) {
											leave_obj.requestLeave(leave_obj);
										}
										else if(admin_choice==2) {
											leave_obj.statusLeave();
										}
										else if(admin_choice==3) {
											break;
										}
									}
								}
								else if(admin_choice==5) {
									System.out.println("\n------------------------Salery Module------------------------");
							
									System.out.print("\t\t\tSelect Your Choice\n\n\t\t|\t1.Make Payment\t\t\t|\n\t\t|\t2.Go Back\t\t\t|");
									System.out.print("\n------------------------------------------------------------------");
									try {
										admin_choice=Integer.parseInt(sc.next());
									}
									catch(Exception e) {
										System.out.println("Invaid Input");
										break;
									}
									
									if(admin_choice==1) {
										Date date = new Date();
										String dt=date.toString();
										salery_obj.makePayment(dt);
									}
								}
								else if(admin_choice==7) {
									break;
								}
							}
						}
					}
					break;
				case 2:
					System.out.println("\n-------------------Welcome To Faculty Portal-------------------");
					System.out.print("\t\t|\t1. Register\t\t|\n\t\t|\t2. Login\t\t|\n\t\t|\t3. Go Back\t\t|");
					System.out.println("\n------------------------------------------------------------------");
					admin_choice=0;
					try {
						admin_choice=Integer.parseInt(sc.next());
					}
					catch(Exception e) {
						System.out.println("Invaid Input");
						break;
					}
					
					if(admin_choice==1) {
						faculty_obj.registerFaculty();
					}
					else if(admin_choice==2) {
						boolean flag=faculty_obj.loginFaculty();
						while(true) {
							if(flag) {
								System.out.println("\n------------------------Faculty Portal------------------------");
								System.out.print("\t\t\tSelect Module\n\n\t\t|\t1.Academics\t\t|\n\t\t|\t2.Leave\t\t\t|\n\t\t|\t3.Salery\t\t|\n\t\t|\t4.Change Password\t|\n\t\t|\t5.Log Out\t\t|");
								System.out.println("\n------------------------------------------------------------------");
								try {
									admin_choice=Integer.parseInt(sc.next());
								}
								catch(Exception e) {
									System.out.println("Invaid Input");
									break;
								}
								if(admin_choice==1) {
									System.out.print("\t\t\tSelect Your Choice\n\n\t\t|\t1. Course Enroll\t|\n\t\t|\t2. Go Back\t\t|");
									System.out.print("\n------------------------------------------------------------------");
									try {
										admin_choice=Integer.parseInt(sc.next());
									}
									catch(Exception e) {
										System.out.println("Invaid Input");
										break;
									}
									if(admin_choice==1) {
										faculty_obj.courseEnrollFaculty();
									}
								}
								else if(admin_choice==2) {
									System.out.print("\t\t\tSelect Your Choice\n\n\t\t|\t1. Apply for Leave\t|\n\t\t|\t2. Cancel Leave\t\t|\n\t\t|\t3. View Leave Status\t|\n\t\t|\t4. Go Back\t\t|");
									System.out.print("\n------------------------------------------------------------------");
									try {
										admin_choice=Integer.parseInt(sc.next());
									}
									catch(Exception e) {
										System.out.println("Invaid Input");
										break;
									}
									if(admin_choice==1) {
										leave_obj.addLeave();
									}
									else if(admin_choice==2) {
										leave_obj.deleteLeave();
									}
									else if(admin_choice==3) {
			    						leave_obj.viewLeave();
									}
								}
								else if(admin_choice==3) {
									salery_obj.viewSalery();
								}
								else if(admin_choice==4) {
									faculty_obj.updatePassword();
								}
								else {
									break;
								}
							}
							else {
								break;
							} 
						}
					}				
				}
			}
		}
}
