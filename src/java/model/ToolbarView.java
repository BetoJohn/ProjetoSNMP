/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author carlos.macedo
 */

import javax.faces.bean.ManagedBean;

@ManagedBean
public class ToolbarView {
    private Message msg;
    public void save() {
        msg = new Message();
        msg.addMessage("Success", "Data saved");
    }
     
    public void update() {
        msg = new Message();
        msg.addMessage("Fatal", "Data updated");
    }
     
    public void delete() {
        msg = new Message();
        msg.addMessage("Error", "Data deleted");
    }
     
   
}
