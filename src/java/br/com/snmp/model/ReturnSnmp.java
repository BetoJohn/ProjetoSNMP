/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.snmp.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author carlos.macedo
 */
public class ReturnSnmp implements Serializable{
    private int id;
    private String time;
    private String ipDevice;
    private List<ResultSnmp> result;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
   
    public String getIpDevice() {
        return ipDevice;
    }

    public void setIpDevice(String ipDevice) {
        this.ipDevice = ipDevice;
    }

    public List<ResultSnmp> getResult() {
        return result;
    }

    public void setResult(List<ResultSnmp> result) {
        this.result = result;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    
    
    
    
}
