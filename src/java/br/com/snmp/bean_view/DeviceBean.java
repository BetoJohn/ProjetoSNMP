/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.snmp.bean_view;

import javax.faces.bean.ManagedBean;
import org.primefaces.context.RequestContext;

/**
 *
 * @author carlos.macedo
 */
@ManagedBean(name ="device")
public class DeviceBean {
    
    public void teste(){
         RequestContext.getCurrentInstance().closeDialog("dialogCadastroDevice");
    }
}
