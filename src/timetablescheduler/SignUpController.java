/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetablescheduler;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author ideal
 */
public class SignUpController implements Initializable  {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private AnchorPane rootPane;
    
    @FXML
    private TextField username;

    @FXML
    private TextField password;

    @FXML
    private Button singup;

    @FXML
    void loadLoginFXML(ActionEvent event) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("Login.fxml"));
            rootPane.getChildren().setAll(pane);
        } catch (IOException ex) {
            Logger.getLogger(SignUpController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void signUp(ActionEvent event) {
        String id = new String();
        try {
            Connection con = (com.mysql.jdbc.Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/timetable","root","11794ec3");
            PreparedStatement pst = con.prepareStatement("select * from loginsaved");
            
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                pst = con.prepareStatement("select max(id) from loginsaved");
                rs = pst.executeQuery();
                rs.next();
                
                if("".equals(rs.getString("max(id)"))){
                    id = "0";
                }
                else{
                    int val = Integer.parseInt(rs.getString("max(id)"));
                    val++;
                    id = Integer.toString(val);
                }
            }
            else id = "0";
            theQuery("insert into loginsaved(id, username, password) values('"+
                    id+"','"
                    +username.getText()+"','"+password.getText()+"')");
        } catch (SQLException ex) {
            Logger.getLogger(SignUpController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void theQuery(String query){
      Connection con = null;
      Statement st = null;
      try{
          con = DriverManager.getConnection("jdbc:mysql://localhost/timetable","root","11794ec3");
          st = con.createStatement();
          st.executeUpdate(query);
          JOptionPane.showMessageDialog(null,"Your Account Has been Saved!\nYou can now Login from Login Page.");
      }catch(Exception ex){
          JOptionPane.showMessageDialog(null,"Not:"+ex.getMessage());
      }
      
  }
    
}
