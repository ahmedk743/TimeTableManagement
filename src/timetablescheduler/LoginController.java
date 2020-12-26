/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetablescheduler;

import com.mysql.jdbc.Connection;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javax.swing.JOptionPane;
import timetablescheduler.SignUpController;

/**
 * FXML Controller class
 *
 * @author ideal
 */
public class LoginController implements Initializable {

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    @FXML
    private AnchorPane loginPane;
    @FXML
    private TextField txtname;

    @FXML
    private PasswordField txtpass;

    @FXML
    private Button btnok;
    private Connection con = null;
    private PreparedStatement pst;
    ResultSet rs;
    
    @FXML
    void login(ActionEvent event) {
        String uname = txtname.getText();
        String pw = txtpass.getText();
        if((uname.equals("")) || pw.equals("")){
            JOptionPane.showMessageDialog(null, "Username and/or Password Field is\nPlease Enter!");
        } else {
            
        try{  
            java.sql.Connection con = (com.mysql.jdbc.Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/timetable","root","11794ec3");
//          
            pst = con.prepareStatement("select * from loginsaved where username=? and password=?");
            pst.setString(1, uname);
            pst.setString(2, pw);
            rs = pst.executeQuery();
            
            if(rs.next()){
                JOptionPane.showMessageDialog(null, "Login Successful!");
                try {
                    AnchorPane pane = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
                    loginPane.getChildren().setAll(pane);
                } catch (IOException ex) {
                    Logger.getLogger(SignUpController.class.getName()).log(Level.SEVERE, null, ex);
                } 
            } else {
                JOptionPane.showMessageDialog(null, "Login Failed!\nYour Account doesn't Exist\nGo to SignUp page to Create new one.");
                txtname.setText("");
                txtpass.setText("");
                txtname.requestFocus();
                txtpass.requestFocus();
            }
            
            //con.close();  
        }catch(Exception e){ System.out.println(e);}
        
        
      }
        
    }
    
}
