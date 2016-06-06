import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class JdbcCon {
	  private Connection con = null; //Database objects 
	  //�s��object 
	  private Statement stat = null; 
	  //����,�ǤJ��sql������r�� 
	  private ResultSet rs = null; 
	  
	  private ResultSet rsTest = null;
	  //���G�� 
	  private PreparedStatement pst = null; 
	  //����,�ǤJ��sql���w�x���r��,�ݭn�ǤJ�ܼƤ���m 
	  //���Q��?�Ӱ��Х� 
	  
	  private String username,password;
	  
	  private String dropdbSQL = "DROP TABLE User "; 
	  
	  private String createdbSQL = "CREATE TABLE what ( id  INTEGER , name  VARCHAR(20) , passwd  VARCHAR(20))"; 
	  
	  private String insertdbSQL = "insert into User(id,name,passwd) " + "select ifNULL(max(id),0)+1,?,? FROM User"; 
	  
	  private String selectSQL; 
	  
	  public JdbcCon( String usernameX,String passwordX) 
	  { 
		username = usernameX;
		password = passwordX;
		selectSQL = "select user_id from User where username = '"+username+"' and password = '"+password+"';";
	    try { 
	      Class.forName("com.mysql.jdbc.Driver"); 
	      //���Udriver 
	      con = (Connection) DriverManager.getConnection( 
	      "jdbc:mysql://172.26.1.86/SevenFater?useUnicode=true&characterEncoding=Big5", 
	      "dick","1234567"); 
	      System.out.println("fuck!!Ya~"); 
	      //���oconnection
	 
	//jdbc:mysql://localhost/test?useUnicode=true&characterEncoding=Big5
	//localhost�O�D���W,test�Odatabase�W
	//useUnicode=true&characterEncoding=Big5�ϥΪ��s�X 
	      
	    } 
	    catch(ClassNotFoundException e) 
	    { 
	      System.out.println("DriverClassNotFound :"+e.toString()); 
	    }//���i��|����sqlexception 
	    catch(SQLException x) { 
	      System.out.println("Exception :"+x.toString()); 
	    } 
	    
	  } 
	  //�إ�table���覡 
	  //�i�H�ݬ�Statement���ϥΤ覡 
	  public void createTable() 
	  { 
	    try 
	    { 
	      stat = (Statement) con.createStatement(); 
	      stat.executeUpdate(createdbSQL); 
	    } 
	    catch(SQLException e) 
	    { 
	      System.out.println("CreateDB Exception :" + e.toString()); 
	    } 
	    finally 
	    { 
	      Close(); 
	    } 
	  } 
	  //�s�W��� 
	  //�i�H�ݬ�PrepareStatement���ϥΤ覡 
	  public void insertTable( String name,String passwd) 
	  { 
	    try 
	    { 
	      pst = (PreparedStatement) con.prepareStatement(insertdbSQL); 
	      
	      pst.setString(1, name); 
	      pst.setString(2, passwd); 
	      pst.executeUpdate(); 
	    } 
	    catch(SQLException e) 
	    { 
	      System.out.println("InsertDB Exception :" + e.toString()); 
	    } 
	    finally 
	    { 
	      Close(); 
	    } 
	  } 
	  //�R��Table, 
	  //��إ�table�ܹ� 
	  public void dropTable() 
	  { 
	    try 
	    { 
	      stat = (Statement) con.createStatement(); 
	      stat.executeUpdate(dropdbSQL); 
	    } 
	    catch(SQLException e) 
	    { 
	      System.out.println("DropDB Exception :" + e.toString()); 
	    } 
	    finally 
	    { 
	      Close(); 
	    } 
	  } 
	  //�d�߸�� 
	  //�i�H�ݬݦ^�ǵ��G���Ψ��o��Ƥ覡 
	  
	  ///////////////�n�J�è��ouser_id///////////////
	  public int Login(int user_id)
	  { 
	    try 
	    { 
	      System.out.println(selectSQL); 
	      stat = (Statement) con.createStatement();
	      rsTest = stat.executeQuery(selectSQL);//rsTest����RS�O�_����
	      //System.out.println(rsTest.next()); 
	      if (rsTest.next()==true){	 //�Y���h�i�J�i�@�B���s����ƶ��q    
	    	  rs = stat.executeQuery(selectSQL); 
	    	  //System.out.println("ID\t\tName\t\tPASSWORD"); 
	    	  while(rs.next()) 
	    	  { 
	    		  user_id = rs.getInt("user_id");
	    		  return user_id;
	    	  } 
	      }
	      else{
	    	  	  return 0;
	      }
	    } 
	    catch(SQLException e) 
	    { 
	      System.out.println("DropDB Exception :" + e.toString()); 
	    } 
	    finally 
	    { 
	      Close(); 
	      System.out.println("already close!");
	    }
	    return 0;   
	  } 
	  //////////////////////////////////////////////
	  
	  public int getCoin(int user_id) 
	  { 
	    try 
	    { 
	      String SQL = "select coin from User where user_id = '"+user_id+"';";
	      System.out.println(SQL); 
	      stat = (Statement) con.createStatement();
	      rsTest = stat.executeQuery(SQL);//rsTest����RS�O�_����
	      //System.out.println(rsTest.next()); 
	      if (rsTest.next()==true){	 //�Y���h�i�J�i�@�B���s����ƶ��q    
	    	  rs = stat.executeQuery(SQL); 
	    	  //System.out.println("ID\t\tName\t\tPASSWORD"); 
	    	  while(rs.next()) 
	    	  {
	    		  int coin = 0;
	    		  coin = rs.getInt("coin");
	    		  return coin;
	    	  } 
	      }
	      else{
	    	  	  return 0;
	      }
	    } 
	    catch(SQLException e) 
	    { 
	      System.out.println("DropDB Exception :" + e.toString()); 
	    } 
	    finally 
	    { 
	      Close(); 
	      System.out.println("already close!"); 
	    }
	    return 0; 
	    
	  } 
	  
	  public int getPack(int user_id) 
	  { 
	    try 
	    { 
	      String SQL = "select pack_number from User where user_id = '"+user_id+"';";
	      System.out.println(SQL); 
	      stat = (Statement) con.createStatement();
	      rsTest = stat.executeQuery(SQL);//rsTest����RS�O�_����
	      //System.out.println(rsTest.next()); 
	      if (rsTest.next()==true){	 //�Y���h�i�J�i�@�B���s����ƶ��q    
	    	  rs = stat.executeQuery(SQL); 
	    	  //System.out.println("ID\t\tName\t\tPASSWORD"); 
	    	  while(rs.next()) 
	    	  {
	    		  int pack_number = 0;
	    		  pack_number = rs.getInt("pack_number");
	    		  return pack_number;
	    	  } 
	      }
	      else{
	    	  	  return 0;
	      }
	    } 
	    catch(SQLException e) 
	    { 
	      System.out.println("DropDB Exception :" + e.toString()); 
	    } 
	    finally 
	    { 
	      Close(); 
	      System.out.println("already close!"); 
	    }
	    return 0; 
	    
	  }
	  
	  
	  //����ϥΧ���Ʈw��,�O�o�n�����Ҧ�Object 
	  //�_�h�b����Timeout��,�i��|��Connection poor�����p 
	  private void Close() 
	  { 
	    try 
	    { 
	      if(rs!=null) 
	      { 
	        rs.close(); 
	        rs = null; 
	      } 
	      if(stat!=null) 
	      { 
	        stat.close(); 
	        stat = null; 
	      } 
	      if(pst!=null) 
	      { 
	        pst.close(); 
	        pst = null; 
	      } 
	    } 
	    catch(SQLException e) 
	    { 
	      System.out.println("Close Exception :" + e.toString()); 
	    } 
	  } 
	  
}


