/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetablescheduler;

/**
 *
 * @author ideal
 */
public class ClsrmTable {
    String id, lab, room, rnd;

    public ClsrmTable(String id, String lab, String room, String rnd) {
        this.id = id;
        this.lab = lab;
        this.room = room;
        this.rnd = rnd;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLab() {
        return lab;
    }

    public void setLab(String lab) {
        this.lab = lab;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getRnd() {
        return rnd;
    }

    public void setRnd(String rnd) {
        this.rnd = rnd;
    }
    
}
