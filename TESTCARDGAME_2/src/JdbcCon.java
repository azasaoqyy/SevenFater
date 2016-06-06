import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class JdbcCon {
	  private Connection con = null; //Database objects 
	  //連接object 
	  private Statement stat = null; 
	  //執行,傳入之sql為完整字串 
	  private ResultSet rs = null; 
	  
	  private ResultSet rsTest = null;
	  //結果集 
	  private PreparedStatement pst = null; 
	  //執行,傳入之sql為預儲之字申,需要傳入變數之位置 
	  //先利用?來做標示 
	  
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
	      //註冊driver 
	      con = (Connection) DriverManager.getConnection( 
	      "jdbc:mysql://172.26.1.86/SevenFater?useUnicode=true&characterEncoding=Big5", 
	      "dick","1234567"); 
	      System.out.println("fuck!!Ya~"); 
	      //取得connection
	 
	//jdbc:mysql://localhost/test?useUnicode=true&characterEncoding=Big5
	//localhost是主機名,test是database名
	//useUnicode=true&characterEncoding=Big5使用的編碼 
	      
	    } 
	    catch(ClassNotFoundException e) 
	    { 
	      System.out.println("DriverClassNotFound :"+e.toString()); 
	    }//有可能會產生sqlexception 
	    catch(SQLException x) { 
	      System.out.println("Exception :"+x.toString()); 
	    } 
	    
	  } 
	  //建立table的方式 
	  //可以看看Statement的使用方式 
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
	  //新增資料 
	  //可以看看PrepareStatement的使用方式 
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
	  //刪除Table, 
	  //跟建立table很像 
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
	  //查詢資料 
	  //可以看看回傳結果集及取得資料方式 
	  
	  ///////////////登入並取得user_id///////////////
	  public int Login(int user_id)
	  { 
	    try 
	    { 
	      System.out.println(selectSQL); 
	      stat = (Statement) con.createStatement();
	      rsTest = stat.executeQuery(selectSQL);//rsTest測試RS是否有值
	      //System.out.println(rsTest.next()); 
	      if (rsTest.next()==true){	 //若有則進入進一步的存取資料階段    
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
	      rsTest = stat.executeQuery(SQL);//rsTest測試RS是否有值
	      //System.out.println(rsTest.next()); 
	      if (rsTest.next()==true){	 //若有則進入進一步的存取資料階段    
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
	      rsTest = stat.executeQuery(SQL);//rsTest測試RS是否有值
	      //System.out.println(rsTest.next()); 
	      if (rsTest.next()==true){	 //若有則進入進一步的存取資料階段    
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
	  
	  
	  //完整使用完資料庫後,記得要關閉所有Object 
	  //否則在等待Timeout時,可能會有Connection poor的狀況 
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


