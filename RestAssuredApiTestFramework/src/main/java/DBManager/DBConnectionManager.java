package DBManager;
import static io.restassured.RestAssured.*;
import static  org.hamcrest.Matchers.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.sql.Statement;


	
	

	public class DBConnectionManager {
	// Connection object
	static Connection con = null;
	// Statement object
	private static Statement stmt;
	// Constant for Database URL
	public static String DB_URL = "jdbc:mysql://103.4.235.55:3306/dojo";
	//Database Username
	public static String DB_USER = "root";
	// Database Password
	public static String DB_PASSWORD = "Dojo1@1234";

	@BeforeTest
	public void setUp() throws Exception {
	try{
	// Database connection
	String dbClass = "com.mysql.cj.jdbc.Driver";
	Class.forName(dbClass).newInstance();
	// Get connection to DB
	Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
	// Statement object to send the SQL statement to the Database
	stmt = con.createStatement();
	}
	catch (Exception e)
	{
	e.printStackTrace();
	}
	}

	@Test
	public void test() {
	try{
	String query = "select * from classes where class_id = 441";
	// Get the contents of userinfo table from DB
	ResultSet res = stmt.executeQuery(query);
	// Print the result untill all the records are printed
	// res.next() returns true if there is any next record else returns false
	while (res.next())
	{
	System.out.print(res.getString(1));
	System.out.print(" " + res.getString(2));
	System.out.print(" " + res.getString(3));
	System.out.println(" " + res.getString(4));
	}
	}
	catch(Exception e)
	{
	e.printStackTrace();
	}
	}

	@AfterTest
	public void tearDown() throws Exception {
	// Close DB connection
	if (con != null) {
	con.close();
	}
	}
	}

////	  
////         public static void main(String args[]){
//		    // Declaration of the variables
//		    
//		    private final String url = "jdbc:mysql://13.232.250.155:3306/i";
//		    private final String user = "root";
//		    private final String password = "root";
//		    public static String fname = null;
//
//		    // Method to initalize connection to the database and execute query
//		    
//		    public void connect() {
//
//		        try {
//		            Connection conn = DriverManager.getConnection(url, user, password);
//		            Class.forName("com.mysql.jdbc.Driver").newInstance();
//
//		            {
//		                if (conn != null) {
//
//		                    PreparedStatement pst = conn.prepareStatement("select * from tenantAPI_classes where class_id=3");
//		                    ResultSet rs = pst.executeQuery();
//		                    {
//		                        while (rs.next()) {
//
//		                            fname = rs.getString("class_name");
//		                            System.out.println("The value from the table is : "+fname);
//		                        }
//		                    }
//
//		                } else
//		                    System.out.println("Failed to connect");
//		            }
//
//		        } catch (SQLException e) {
//		            System.out.println(e.getMessage());
//		        } catch (InstantiationException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} catch (IllegalAccessException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} catch (ClassNotFoundException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//		    }
//
//		    // Main method and Rest Assured Code
//		    
//		    public static void main(String[] args) {
//		        DBConnectionManager app = new DBConnectionManager();
//		        app.connect();      
//		        given().when().get("https://preprod.cleverground.com/institude/1/branch/1/classes").then().body("response[0].class_name", equalToIgnoringCase(fname));
//		        System.out.println("Execution Successful");
//		    }
//
//		
//         }
//
////}
