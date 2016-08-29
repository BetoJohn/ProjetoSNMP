/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.snmp.DAO;

import br.com.snmp.model.Device;
import br.com.snmp.model.OID;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author carlos.macedo
 */
public class SnmpDAO {

    private static List<Device> listDevice;
    private static List<OID> listOID;
    private static SnmpDAO dao;

    private SnmpDAO() {
    }

    public static SnmpDAO getInstance() {
        if (dao == null) {
            dao = new SnmpDAO();
            listDevice = new ArrayList<>();
            listOID = new ArrayList<OID>();
        }
        return dao;
    }
    
    public void saveDevice(Device dev) {
        listDevice.add(dev);
    }
    public List<Device> getAllDevices(){
        return listDevice;
    }

    public void saveOID(OID oid) {
        listOID.add(oid);
    }
    public List<OID> getAllOID(){
        return listOID;
    }

    public List<Device> getDescOID() {
        return listDevice;
    }


}
