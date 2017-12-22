package sleep.quality.support;

import java.sql.*;
import javax.swing.*;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import extensions.sensor;


public class DatabaseConnection {
    public Connection conn=null;
   /* public static Connection ConnectDB(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/qualitysleep","root","1593572846");
            System.out.println("Connecting Database");
            return conn;
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
            return null;
        }
    }
    */
    public void ConnectDB() throws SQLException{
        String url = "jdbc:mysql://localhost:3306/qualitysleep";
        String username = "root";
        String password = "1593572846";
        System.out.println("Connecting database...");
        this.conn = DriverManager.getConnection(url, username, password); 
    }
    
    public boolean girisKontrol(String id,String pw) throws SQLException, ClassNotFoundException{
        ConnectDB();
        Statement stmt = conn.createStatement();
        ResultSet rs;
        rs = stmt.executeQuery("SELECT * FROM qualitysleep.user");
        
        while (rs.next()) {
        String k = rs.getString("Username");
        String p = rs.getString("Password");
        if(k.equals(id) && p.equals(pw)){
            return true;
        }
        }
        return false;
        
        
    }
    
    
    
    
    
    
    public void saveToSQL(String heat,String airQuality,String humidity) throws SQLException{
            ConnectDB();
          PreparedStatement ps = conn.prepareStatement("insert into data(Heat, AirQuality, Humidity ) values(?,?,?)");
            ps.setString(1, heat);
            ps.setString(2, airQuality);
            ps.setString(3, humidity);
            ps.executeUpdate();
        
        
    }
    
    public void resetData (int all) throws SQLException{
        ConnectDB();
       String query = "delete from data where id = ?";
      PreparedStatement ps = conn.prepareStatement(query);
      ps.setInt(1, all);
        ps.executeUpdate();
    }
    
    
   public List<sensor> veri_listele() throws SQLException{
        ConnectDB();
        List<sensor> gelen = new ArrayList<>();
     
        sensor veriler ;
        Statement stmt;
        try {
            stmt = conn.createStatement();
            ResultSet rs;
            rs = stmt.executeQuery("SELECT d.`Heat`, d.`Humidity`, d.`AirQuality` FROM `data` d;");
           
            while (rs.next()) {
                System.out.println(rs.getString("Heat"));
                System.out.println(rs.getString("Humidity"));
                System.out.println(rs.getString("AirQuality"));
                veriler = new sensor();
                veriler.setHeat(rs.getString("Heat"));
                veriler.setHumidity(rs.getString("Humidity"));
                veriler.setAirQuality(rs.getString("AirQuality"));
                
                    gelen.add(veriler);
                
            }
            

            
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return gelen;
    }
   
    public List<sensor> getResult() throws SQLException{
       ConnectDB();
        List<sensor> gelen = new ArrayList<>();
     
        sensor veriler ;
        Statement stmt;
        try {
            stmt = conn.createStatement();
            ResultSet rs;
            rs = stmt.executeQuery("SELECT d.`Heat`, d.`Humidity`, d.`AirQuality` FROM `data` d;");
           
            while (rs.next()) {
                veriler = new sensor();
                veriler.setHeat(rs.getString("Heat"));
                veriler.setHumidity(rs.getString("Humidity"));
                veriler.setAirQuality(rs.getString("AirQuality"));
                
                    gelen.add(veriler);
                
            }
            

            
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return gelen;
        }
}