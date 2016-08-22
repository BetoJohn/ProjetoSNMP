/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author carlos.macedo
 */
public class Message {

    public void addMessage(String status, String msg) {
        FacesMessage.Severity facesMessage = null;
        switch (status) {
            case "Success":
                facesMessage = FacesMessage.SEVERITY_INFO;
                return;
            case "Error":
                facesMessage = FacesMessage.SEVERITY_ERROR;
                return;
            case "Warn":
                facesMessage = FacesMessage.SEVERITY_WARN;
                return;
            case "Fatal":
                facesMessage = FacesMessage.SEVERITY_FATAL;
                return;
        }

        FacesMessage message = new FacesMessage(facesMessage, status, msg);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

}
