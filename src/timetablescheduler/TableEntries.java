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
public class TableEntries {
    String slot1, slot2, slot3;

    public TableEntries(String slot1, String slot2, String slot3) {
        this.slot1 = slot1;
        this.slot2 = slot2;
        this.slot3 = slot3;
    }

    public String getSlot1() {
        return slot1;
    }

    public void setSlot1(String slot1) {
        this.slot1 = slot1;
    }

    public String getSlot2() {
        return slot2;
    }

    public void setSlot2(String slot2) {
        this.slot2 = slot2;
    }

    public String getSlot3() {
        return slot3;
    }

    public void setSlot3(String slot3) {
        this.slot3 = slot3;
    }
    
}
