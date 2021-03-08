package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
public class DatabaseHandler extends Configs {
   Connection dbConnection=null;
   public Connection getDbConnection() throws ClassNotFoundException,SQLException{
       String connectionString="jdbc:postgresql://"+host+":"+port+"/"+db_name;
       try {
           Class.forName("org.postgresql.Driver");

           // Establish the connection
           //connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "0000");
           dbConnection = DriverManager.getConnection("jdbc:postgresql://"+host+":"+port+"/"+db_name, username, password);

       }catch(Exception e) {
           System.out.println(e);
       }
       return dbConnection;
   }
   public void signUpUser(User user){
       String insert="INSERT INTO "+Const.USER_TABLE+"("+Const.USER_FIRSTNAME+","+ Const.USER_LASTNAME+","
               +Const.USER_USERNAME+","+Const.USER_PASSWORD+","+Const.USER_LOCATION+","+Const.USER_GENDER+")"+
               "VALUES(?,?,?,?,?,?)";
       try{
           PreparedStatement psSt=getDbConnection().prepareStatement(insert);
           psSt.setString(1,user.getFirstName());
           psSt.setString(2,user.getLastName());
           psSt.setString(3,user.getUserName());
           psSt.setString(4,user.getPassword());
           psSt.setString(5,user.getLocation());
           psSt.setString(6,user.getGender());
           psSt.executeUpdate();
       }catch (SQLException e){
           e.printStackTrace();
       }catch (ClassNotFoundException e){
           e.printStackTrace();
       }
   }
   public ResultSet getUser(User user){
       ResultSet resSet=null;

       String select = "SELECT * FROM " + Const.USER_TABLE + " WHERE " + Const.USER_USERNAME + "=? AND " +
               Const.USER_PASSWORD + "=?";

       try{
           PreparedStatement psSt=getDbConnection().prepareStatement(select);
           psSt.setString(1,user.getUserName());
           psSt.setString(2,user.getPassword());

           resSet=psSt.executeQuery();
       }catch (SQLException e){
           e.printStackTrace();
       }catch (ClassNotFoundException e){
           e.printStackTrace();
       }
       return resSet;
   }
}

