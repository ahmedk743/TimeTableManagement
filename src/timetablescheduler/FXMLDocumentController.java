/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetablescheduler;

import com.sun.javafx.logging.Logger;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author ideal
 */
public class FXMLDocumentController implements Initializable {
   
    //Main Time Table
    @FXML private TableView<TableEntries> tableView;
    @FXML private TableColumn<TableEntries, String> col_slot1;
    @FXML private TableColumn<TableEntries, String> col_slot2;
    @FXML private TableColumn<TableEntries, String> col_slot3;

    //FacultyTable
    @FXML private TableView<FacTable> fac_tableView;
    @FXML private TableColumn<FacTable, String> col_fac_name;
    @FXML private TableColumn<FacTable, String> col_fac_id;
    
    //DepartmentTable
    @FXML private TableView<DepTable> dep_tableView;
    @FXML private TableColumn<DepTable, String> col_dep_name;
    @FXML private TableColumn<DepTable, String> col_dep_id;
    @FXML private TableColumn<DepTable, String> col_dep_fac;
    
    //LecturerTable
    @FXML private TableView<LecTable> lec_tableView;
    @FXML private TableColumn<LecTable, String> col_lec_name;
    @FXML private TableColumn<LecTable, String> col_lec_id;
    @FXML private TableColumn<LecTable, String> col_lec_title;
    @FXML private TableColumn<LecTable, String> col_lec_dep;
    @FXML private TableColumn<LecTable, String> col_lec_fac;
    
    //CourseTable
    @FXML private TableView<CrsTable> crs_tableView;
    @FXML private TableColumn<CrsTable, String> col_crs_name;
    @FXML private TableColumn<CrsTable, String> col_crs_id;
    @FXML private TableColumn<CrsTable, String> col_crs_code;
    @FXML private TableColumn<CrsTable, String> col_crs_dep;
    @FXML private TableColumn<CrsTable, String> col_crs_fac;
    @FXML private TableColumn<CrsTable, String> col_crs_cred;
    @FXML private TableColumn<CrsTable, String> col_crs_lec;
    @FXML private TableColumn<CrsTable, String> col_crs_sect;
    
    //ClassTable
    @FXML private TableView<ClsTable> cls_tableView;
    @FXML private TableColumn<ClsTable, String> col_cls_crs;
    @FXML private TableColumn<ClsTable, String> col_cls_id;
    @FXML private TableColumn<ClsTable, String> col_cls_lab;
    @FXML private TableColumn<ClsTable, String> col_cls_batch;
    
    //ClassroomTable
    @FXML private TableView<ClsrmTable> clsrm_tableView;
    @FXML private TableColumn<ClsrmTable, String> col_clsrm_rm;
    @FXML private TableColumn<ClsrmTable, String> col_clsrm_id;
    @FXML private TableColumn<ClsrmTable, String> col_clsrm_lab;
    @FXML private TableColumn<ClsrmTable, String> col_clsrm_rnd;
    
    @FXML
    private ComboBox<String> lecturerCombo;
    @FXML
    private ComboBox<String> depCombo;
    @FXML
    private ComboBox<String> dayCombo;
    @FXML
    private ComboBox<String> courseCombo;
    @FXML
    private ComboBox<String> roomCombo;
    @FXML
    private ComboBox<String> timeCombo;
    @FXML
    private ComboBox<String> batchCombo;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        comboInitializers();
        updateTable();
        facultyTable();
        depTable();
        lecTable();
        crsTable();
        clsTable();
        clsrmTable();
       
    }  
    //Faculty Table
    public void facultyTable(){
        try {
            ObservableList<FacTable> oblistTab = FXCollections.observableArrayList();
            
            
            
            Connection con = (com.mysql.jdbc.Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/timetable","root","11794ec3");
            PreparedStatement pst = con.prepareStatement("select * from facultytab");
            ResultSet rs = pst.executeQuery();
            
            
            while(rs.next()){
                oblistTab.add(new FacTable(rs.getString("id"), rs.getString("name")));
            }
            col_fac_id.setCellValueFactory(new PropertyValueFactory<>("id"));
            col_fac_name.setCellValueFactory(new PropertyValueFactory<>("name"));

            fac_tableView.setItems(oblistTab);
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Department Table
    public void depTable(){
        try {
            ObservableList<DepTable> oblistTab = FXCollections.observableArrayList();
            
            
            
            Connection con = (com.mysql.jdbc.Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/timetable","root","11794ec3");
            PreparedStatement pst = con.prepareStatement("select * from deptable");
            ResultSet rs = pst.executeQuery();
            
            
            while(rs.next()){
                oblistTab.add(new DepTable(rs.getString("id"), rs.getString("name"), rs.getString("faculty")));
            }
            col_dep_id.setCellValueFactory(new PropertyValueFactory<>("id"));
            col_dep_name.setCellValueFactory(new PropertyValueFactory<>("name"));
            col_dep_fac.setCellValueFactory(new PropertyValueFactory<>("faculty"));

            dep_tableView.setItems(oblistTab);
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Lecturer Table
    public void lecTable(){
        try {
            ObservableList<LecTable> oblistTab = FXCollections.observableArrayList();
            
            
            
            Connection con = (com.mysql.jdbc.Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/timetable","root","11794ec3");
            PreparedStatement pst = con.prepareStatement("select * from lecturertab");
            ResultSet rs = pst.executeQuery();
            
            
            while(rs.next()){
                oblistTab.add(new LecTable(rs.getString("id"), rs.getString("title"), rs.getString("name"), rs.getString("department"), rs.getString("faculty")));
            }
            col_lec_id.setCellValueFactory(new PropertyValueFactory<>("id"));
            col_lec_title.setCellValueFactory(new PropertyValueFactory<>("title"));
            col_lec_name.setCellValueFactory(new PropertyValueFactory<>("name"));
            col_lec_dep.setCellValueFactory(new PropertyValueFactory<>("department"));
            col_lec_fac.setCellValueFactory(new PropertyValueFactory<>("faculty"));

            lec_tableView.setItems(oblistTab);
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Course Table
    public void crsTable(){
        try {
            ObservableList<CrsTable> oblistTab = FXCollections.observableArrayList();
            
            
            
            Connection con = (com.mysql.jdbc.Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/timetable","root","11794ec3");
            PreparedStatement pst = con.prepareStatement("select * from coursetab");
            ResultSet rs = pst.executeQuery();
            
            
            while(rs.next()){
                oblistTab.add(new CrsTable(rs.getString("id"), rs.getString("name"),
                rs.getString("code"), rs.getString("credits"), rs.getString("section"), rs.getString("lecturer"),
                rs.getString("department"), rs.getString("faculty")));
            }
            col_crs_id.setCellValueFactory(new PropertyValueFactory<>("id"));
            col_crs_name.setCellValueFactory(new PropertyValueFactory<>("name"));
            col_crs_code.setCellValueFactory(new PropertyValueFactory<>("code"));
            col_crs_cred.setCellValueFactory(new PropertyValueFactory<>("credits"));
            col_crs_sect.setCellValueFactory(new PropertyValueFactory<>("section"));
            col_crs_lec.setCellValueFactory(new PropertyValueFactory<>("lecturer"));
            col_crs_dep.setCellValueFactory(new PropertyValueFactory<>("department"));
            col_crs_fac.setCellValueFactory(new PropertyValueFactory<>("faculty"));
            
            crs_tableView.setItems(oblistTab);
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Class Table
    public void clsTable(){
        try {
            ObservableList<ClsTable> oblistTab = FXCollections.observableArrayList();
            
            
            
            Connection con = (com.mysql.jdbc.Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/timetable","root","11794ec3");
            PreparedStatement pst = con.prepareStatement("select * from classtab");
            ResultSet rs = pst.executeQuery();
            
            
            while(rs.next()){
                oblistTab.add(new ClsTable(rs.getString("id"), rs.getString("course"),
                rs.getString("lab"), rs.getString("batch")));
            }
            col_cls_id.setCellValueFactory(new PropertyValueFactory<>("id"));
            col_cls_crs.setCellValueFactory(new PropertyValueFactory<>("course"));
            col_cls_lab.setCellValueFactory(new PropertyValueFactory<>("lab"));
            col_cls_batch.setCellValueFactory(new PropertyValueFactory<>("batch"));

            cls_tableView.setItems(oblistTab);
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Classroom Table
    public void clsrmTable(){
        try {
            ObservableList<ClsrmTable> oblistTab = FXCollections.observableArrayList();
            
            
            
            Connection con = (com.mysql.jdbc.Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/timetable","root","11794ec3");
            PreparedStatement pst = con.prepareStatement("select * from classroom");
            ResultSet rs = pst.executeQuery();
            
            
            while(rs.next()){
                oblistTab.add(new ClsrmTable(rs.getString("id"), rs.getString("lab"),
                rs.getString("room"), rs.getString("rnd")));
            }
            col_clsrm_id.setCellValueFactory(new PropertyValueFactory<>("id"));
            col_clsrm_lab.setCellValueFactory(new PropertyValueFactory<>("lab"));
            col_clsrm_rm.setCellValueFactory(new PropertyValueFactory<>("room"));
            col_clsrm_rnd.setCellValueFactory(new PropertyValueFactory<>("rnd"));

            clsrm_tableView.setItems(oblistTab);
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateTable(){
        ObservableList<TableEntries> oblistTab = FXCollections.observableArrayList();

        
        try {
//          theQuery("truncate table scedulertab");
            Connection con = (com.mysql.jdbc.Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/timetable","root","11794ec3");
            PreparedStatement pst = con.prepareStatement("select * from scedulertab");
            ResultSet rs = pst.executeQuery();
            int monday = 0;            
            while(rs.next()){
                //combo data
                String slotValue = rs.getString("faculty")+"/"+rs.getString("department")
                     +"/"+rs.getString("batch")+" "+rs.getString("teacher")+" "
                     +rs.getString("course")+" Room"+rs.getString("room");
                
                
                //Monday
                if("Monday".equals(rs.getString("day"))){
                    
                    if("1".equals(rs.getString("slot"))){
                        oblistTab.add(new TableEntries(slotValue,"",""));
                    } else if("2".equals(rs.getString("slot"))){
                        oblistTab.add(new TableEntries("",slotValue,""));
                    }
                    else{
                        oblistTab.add(new TableEntries("","",slotValue));
                    }               
                }
                
                //Tuesday
                if("Tuesday".equals(rs.getString("day"))){
                    if("1".equals(rs.getString("slot"))){
                        oblistTab.add(new TableEntries(slotValue,"",""));
                    } else if("2".equals(rs.getString("slot"))){
                        oblistTab.add(new TableEntries("",slotValue,""));
                    }
                    else{
                        oblistTab.add(new TableEntries("","",slotValue));
                    }               
                }
                
                //Wednesday
                if("Wednesday".equals(rs.getString("day"))){
                    if("1".equals(rs.getString("slot"))){
                        oblistTab.add(new TableEntries(slotValue,"",""));
                    } else if("2".equals(rs.getString("slot"))){
                        oblistTab.add(new TableEntries("",slotValue,""));
                    }
                    else{
                        oblistTab.add(new TableEntries("","",slotValue));
                    }               
                }
                
                //Thursday
                if("Thursday".equals(rs.getString("day"))){
                    if("1".equals(rs.getString("slot"))){
                        oblistTab.add(new TableEntries(slotValue,"",""));
                    } else if("2".equals(rs.getString("slot"))){
                        oblistTab.add(new TableEntries("",slotValue,""));
                    }
                    else{
                        oblistTab.add(new TableEntries("","",slotValue));
                    }               
                }
                
                //Friday
                if("Friday".equals(rs.getString("day"))){
                    if("1".equals(rs.getString("slot"))){
                        oblistTab.add(new TableEntries(slotValue,"",""));
                    } else if("2".equals(rs.getString("slot"))){
                        oblistTab.add(new TableEntries("",slotValue,""));
                    }
                    else{
                        oblistTab.add(new TableEntries("","",slotValue));
                    }               
                }
                
            }
            
        col_slot1.setCellValueFactory(new PropertyValueFactory<>("slot1"));
        col_slot2.setCellValueFactory(new PropertyValueFactory<>("slot2"));
        col_slot3.setCellValueFactory(new PropertyValueFactory<>("slot3"));

        tableView.setItems(oblistTab);
                    
                    
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void deleteTable(ActionEvent event){
        try{
          theQuery("truncate table scedulertab");
          updateTable();
         }
         catch(Exception ex)
         {JOptionPane.showMessageDialog(null,ex.getMessage());}
    }
    public boolean verifyComboEntries(){
        try {
            Connection con = (com.mysql.jdbc.Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/timetable","root","11794ec3");
            PreparedStatement pst = con.prepareStatement("select * from scedulertab");
            ResultSet rs = pst.executeQuery(); 
            boolean daycheck = true, lectCheck = true, batchCheck = true, roomCheck = true;
            
            //manage slot value
                String time_slot = new String();
                if(null != timeCombo.getValue())switch (timeCombo.getValue()) {
                    case "8:30AM-10:30AM":
                        time_slot = "1";
                        break;
                    case "10:30AM-12:30PM":
                        time_slot = "2";
                        break;
                    default:
                        time_slot = "3";
                        break;
                }
            int i = 0;
            while(rs.next()){
                i++;
                
                
                if(rs.getString("teacher").equals(lecturerCombo.getValue())){
                    if(rs.getString("slot").equals(time_slot) && rs.getString("day").equals(dayCombo.getValue())){
                        //false
                        lectCheck = false;
                        break;
                    }
                }
                
                if(rs.getString("batch").equals(batchCombo.getValue())){
                    if(rs.getString("slot").equals(time_slot) && rs.getString("day").equals(dayCombo.getValue())){
                        //false
                        batchCheck = false;
                        break;
                    }
                }
                
                if(rs.getString("room").equals(roomCombo.getValue())){
                    if(rs.getString("slot").equals(time_slot) && rs.getString("day").equals(dayCombo.getValue())){
                        //false
                        roomCheck = false;
                        break;
                    }
                }
                
                                
            }
            //last 
            if(lectCheck == false){
                JOptionPane.showMessageDialog(null, i+" . Unable to Schedule Class\nThe Lecturer you entered is scheduled to another class in this time slot!");
            }
            if(batchCheck == false){
                JOptionPane.showMessageDialog(null, "Unable to Schedule Class\nThe Batch you entered is scheduled to another class in this time slot!");
            }
            if(roomCheck == false){
                JOptionPane.showMessageDialog(null, "Unable to Schedule Class\nThe Room you entered is scheduled to another class in this time slot!");
            }

            if(lectCheck == false || batchCheck == false || roomCheck == false) 
                return false;

            
            
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
    public void addToSchedulerTab(){
        if(verifyComboEntries()){
            String id = new String();    
            try{
                Connection con = (com.mysql.jdbc.Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/timetable","root","11794ec3");
                PreparedStatement pst = con.prepareStatement("select * from scedulertab");

                ResultSet rs = pst.executeQuery();
                if(rs.next()){
                    pst = con.prepareStatement("select max(id) from scedulertab");
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

                //manage slot value
                String time_slot = new String();
                if(null != timeCombo.getValue())switch (timeCombo.getValue()) {
                    case "8:30AM-10:30AM":
                        time_slot = "1";
                        break;
                    case "10:30AM-12:30PM":
                        time_slot = "2";
                        break;
                    default:
                        time_slot = "3";
                        break;
                }

                //execute query
                theQuery("insert into scedulertab(id, faculty, department, room,teacher, day,"
                        + " slot, course, batch) values('"+id
                        +"','"+facultyCombo.getValue()+"','"+depCombo.getValue()
                        +"','"+roomCombo.getValue()+"','"+lecturerCombo.getValue()+"','"
                        +dayCombo.getValue()+"','"+time_slot+
                        "','"+courseCombo.getValue()+"','"+batchCombo.getValue()+"')");
            }
            catch(SQLException | NumberFormatException ex){
                JOptionPane.showMessageDialog(null,ex.getMessage()+"saale");
            }
        }
    }

    @FXML
    public void scheduleIt(ActionEvent event){
        addToSchedulerTab();
        updateTable();
        
//        col_slot1 = new TableColumn("Name");
//        

    }
    public void comboInitializers(){
        try {
            ObservableList<String> oblist;
            ArrayList<String> listOfSomething = new ArrayList<>();
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/timetable","root","11794ec3");
            ResultSet rs;
            
            //facultycombo
            rs = con.createStatement().executeQuery("select name from facultytab");
            while (rs.next()) {
                listOfSomething.add(rs.getString("name"));
            } 
            oblist = FXCollections.observableArrayList(listOfSomething);
            facultyCombo.setItems(oblist);
            listOfSomething.clear();
            
//            //daycombo
            listOfSomething.add("Monday");
            listOfSomething.add("Tuesday");
            listOfSomething.add("Wednesday");
            listOfSomething.add("Thursday");
            listOfSomething.add("Friday");
            
            oblist = FXCollections.observableArrayList(listOfSomething);
            dayCombo.setItems(oblist);
            listOfSomething.clear();
            
            //depcombo
            rs = con.createStatement().executeQuery("select name from deptable");
            while (rs.next()) {
                listOfSomething.add(rs.getString("name"));
            } 
            oblist = FXCollections.observableArrayList(listOfSomething);
            depCombo.setItems(oblist);
            listOfSomething.clear();
            
            //lectcombo
            rs = con.createStatement().executeQuery("select name from lecturertab");
            while (rs.next()) {
                listOfSomething.add(rs.getString("name"));
            } 
            oblist = FXCollections.observableArrayList(listOfSomething);
            lecturerCombo.setItems(oblist);
            listOfSomething.clear();
            
            //coursecombo
            rs = con.createStatement().executeQuery("select name from coursetab");
            while (rs.next()) {
                listOfSomething.add(rs.getString("name"));
            } 
            oblist = FXCollections.observableArrayList(listOfSomething);
            courseCombo.setItems(oblist);
            listOfSomething.clear();
            
            //roomcombo
            rs = con.createStatement().executeQuery("select room from classroom");
            while (rs.next()) {
                listOfSomething.add(rs.getString("room"));
            } 
            oblist = FXCollections.observableArrayList(listOfSomething);
            roomCombo.setItems(oblist);
            listOfSomething.clear();
            
            //timeslotcombo
            listOfSomething.add("8:30AM-10:30AM");
            listOfSomething.add("10:30AM-12:30PM");
            listOfSomething.add("13:30PM-3:30PM");
            
            oblist = FXCollections.observableArrayList(listOfSomething);
            timeCombo.setItems(oblist);
            listOfSomething.clear();
            
            //batchcombo
            rs = con.createStatement().executeQuery("select batch from classtab");
            while (rs.next()) {
                listOfSomething.add(rs.getString("batch"));
            } 
            oblist = FXCollections.observableArrayList(listOfSomething);
            batchCombo.setItems(oblist);
            listOfSomething.clear();
//            
            
            
            
           } catch (SQLException ex) {
               JOptionPane.showMessageDialog(null,ex.getMessage());
//            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     @FXML
    private TextField faculty_id;

    @FXML
    private TextField faculty_name;
    
    
     @FXML
    private Button faculty;

    @FXML
    private Button dep;

    @FXML
    private Button lecturer;

    @FXML
    private Button course;

    @FXML
    private Button addclass;

    @FXML
    private Button classroom;
    
      @FXML
    private TextField dep_id;

    @FXML
    private TextField dep_name;

    @FXML
    private TextField dep_faculty;


    @FXML
    private TextField lecturer_id;

    @FXML
    private TextField lecturer_name;

    @FXML
    private TextField lecturer_dep;

    @FXML
    private TextField lecturer_faculty;

    @FXML
    private TextField lecturer_title;


    @FXML
    private TextField course_id;

    @FXML
    private TextField course_code;

    @FXML
    private TextField course_credits;

    @FXML
    private TextField course_section;

    @FXML
    private TextField course_name;

    @FXML
    private TextField course_dep;

    @FXML
    private TextField course_lecturer;

    @FXML
    private TextField course_faculty;

    @FXML
    private TextField class_id;

    @FXML
    private TextField class_lab;

    @FXML
    private TextField class_batch;

    @FXML
    private TextField class_course;


    @FXML
    private TextField classroom_id;

    @FXML
    private TextField classroom_room;

    @FXML
    private TextField classroom_rnd;

    @FXML
    private TextField classroom_lab;
    
    @FXML
    private Button del_faculty;
    @FXML
    private Button del_dep;
    @FXML
    private Button del_lect;
    @FXML
    private Button del_course;
    @FXML
    private Button del_class;
    @FXML
    private Button del_classroom;

    @FXML
    private ComboBox<String> facultyCombo;

    @FXML
    void class_del(ActionEvent event) {
        try{
          theQuery("delete from classtab where id = "+class_id.getText());
          clsTable();
         }
         catch(Exception ex)
         {JOptionPane.showMessageDialog(null,ex.getMessage());}
        comboInitializers();
    }

    @FXML
    void classroom_del(ActionEvent event) {
        try{
          theQuery("delete from classroom where id = "+classroom_id.getText());
          clsrmTable();
        }
         catch(Exception ex)
         {JOptionPane.showMessageDialog(null,ex.getMessage());}
        comboInitializers();
    }

    @FXML
    void course_del(ActionEvent event) {
        try{
          theQuery("delete from coursetab where id = "+course_id.getText());
          crsTable();
        }
         catch(Exception ex)
         {JOptionPane.showMessageDialog(null,ex.getMessage());}
        comboInitializers();
    }

    @FXML
    void dep_del(ActionEvent event) {
        try{
          theQuery("delete from deptable where id = "+dep_id.getText());
          depTable();
        }
         catch(Exception ex)
         {JOptionPane.showMessageDialog(null,ex.getMessage());}
        comboInitializers();
    }

    @FXML
    void faculty_del(ActionEvent event) {
        try{
          theQuery("delete from facultytab where id = "+faculty_id.getText());
          facultyTable();
        }
         catch(Exception ex)
         {JOptionPane.showMessageDialog(null,ex.getMessage());}
        comboInitializers();
    }

    @FXML
    void lect_del(ActionEvent event) {
        try{
          theQuery("delete from lecturertab where id = "+lecturer_id.getText());
          lecTable();
        }
         catch(Exception ex)
         {JOptionPane.showMessageDialog(null,ex.getMessage());}
        comboInitializers();
    }
    
    @FXML
    void add_class(ActionEvent event) {
        try{
            theQuery("insert into classtab(id, course, lab,batch) values('"
                    +class_id.getText()+"','"+class_course.getText()
                    +"','"+class_lab.getText()+"','"+class_batch.getText()+"')");
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null,ex.getMessage());
        }
        clsTable();
        comboInitializers();
    }

    @FXML
    void add_classroom(ActionEvent event) {
        try{
            theQuery("insert into classroom(id, lab,room, rnd) values('"
                    +classroom_id.getText()+"','"+classroom_lab.getText()
                    +"','"+classroom_room.getText()+"','"+classroom_rnd.getText()+"')");
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null,ex.getMessage());
        }
        clsrmTable();
        comboInitializers();
    }

    @FXML
    void add_course(ActionEvent event) {
        try{
            theQuery("insert into coursetab(id, name, code, credits,section, lecturer,"
                    + " department, faculty) values('"+course_id.getText()
                    +"','"+course_name.getText()+"','"+course_code.getText()
                    +"','"+course_credits.getText()+"','"+course_section.getText()+"','"
                    +course_lecturer.getText()+"','"+course_dep.getText()+
                    "','"+course_faculty.getText()+"')");
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null,ex.getMessage());
        }
        crsTable();
        comboInitializers();
    }

    @FXML
    void add_dep(ActionEvent event) {
        try{
            theQuery("insert into deptable(id, name, faculty) values('"+dep_id.getText()+"','"+dep_name.getText()+"','"+dep_faculty.getText()+"')");
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null,ex.getMessage());
        }
        depTable();
        comboInitializers();
    }

    @FXML
    void add_faculty(ActionEvent event) {
        try{
            theQuery("insert into facultytab(id, name) values('"+faculty_id.getText()+"','"+faculty_name.getText()+"')");
            facultyTable();
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null,ex.getMessage());
        }
        
        comboInitializers();
        
         
    }

    @FXML
    void add_lecturer(ActionEvent event) {
        try{
            theQuery("insert into lecturertab(id,title, name, department, faculty) values('"+lecturer_id.getText()
                    +"','"+lecturer_title.getText()+"','"+lecturer_name.getText()+"','"+lecturer_dep.getText()+"','"+lecturer_faculty.getText()+"')");
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null,ex.getMessage());
        }
        lecTable();
        comboInitializers();
    }
    public void theQuery(String query){
      Connection con = null;
      Statement st = null;
      try{
          con = DriverManager.getConnection("jdbc:mysql://localhost/timetable","root","11794ec3");
          st = con.createStatement();
          st.executeUpdate(query);
          JOptionPane.showMessageDialog(null,"Query Executed");
      }catch(Exception ex){
          JOptionPane.showMessageDialog(null,"QueryExecutionFailed!: "+ex.getMessage());
      }
      
  }
    
}
