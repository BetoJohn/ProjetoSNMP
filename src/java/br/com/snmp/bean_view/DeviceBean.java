/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.snmp.bean_view;

import br.com.snmp.model.Device;
import br.com.snmp.model.OID;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.validation.constraints.Min;
import org.primefaces.context.RequestContext;

/**
 *
 * @author carlos.macedo
 */
@ManagedBean
public class DeviceBean implements Serializable {

    private Device device;
    private OID oid;
    private List<Device> listDevices;
   

    @PostConstruct
    public void init() {
        device = new Device();
        oid = new OID();
        device.setOid(oid);
        
        //listDevices = new ArrayList<Device>();

    }

    public String reinit() {
        device = new Device();
        oid = new OID();
        device.setOid(oid);
        return null;
    }

    public void createNew(ActionEvent event) {

        System.out.println(device.toString() + " - " + oid.toString());

//        device.setComunidade(comunidade);
//        device.setIp(ip);
//        device.setOid(oid);
//        if (listDevices.contains(device)) {
//            FacesMessage msg = new FacesMessage("Dublicated", "This device has already been added");
//            FacesContext.getCurrentInstance().addMessage(null, msg);
//        } else {
        if (listDevices == null) {
            listDevices = new ArrayList<Device>();
        }
        listDevices.add(device);

//        }
    }

    public void createNewOID(ActionEvent event) {
        //descOID.add(device.getOid().getDescricao());
        System.out.println(device.getOid().toString());
    }

    public List<String> completeOID(String query) {
       List<String> results = new ArrayList<String>();
        results.add("3562451252");
        results.add("587576868");
        results.add("35624989689435152");
        results.add("3562452542152");
        results.add("3562452435152");

        return results;
    }

    public List<Device> getListDevices() {
        return listDevices;
    }

//    public List<String> getDescOID() {
//        return descOID;
//    }
    

    public Device getDevice() {
        return device;
    }

    public OID getOid() {
        return oid;
    }

}
