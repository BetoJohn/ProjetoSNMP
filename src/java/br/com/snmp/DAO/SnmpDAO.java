/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.snmp.DAO;

import br.com.snmp.model.Device;
import br.com.snmp.model.OID;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author carlos.macedo
 */
public class SnmpDAO extends DaoBase {

    private static List<Device> listDevice;
    private static List<OID> listOID;
    private static SnmpDAO dao;
    private static List<Device> persistDevice;

    public SnmpDAO(Connection con) {
        super(con);
    }
      
    public void insertDevice(Device dev) throws Exception {
        String sql = "INSERT INTO teste(\n"
                + "            nome, idade)\n"
                + "    VALUES (?, ?);";
        PreparedStatement ps = null;
        try {
            ps = createPreparedStatement(sql);
            ps.setObject(1, dev.getIp());
            ps.setObject(2, dev.getOid().getPortInicial());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
                ps.close();
            } catch (Exception e) {
            }
        }

    }
    public List<Device> getAllDevices(){
        listDevice = new ArrayList();
        Device dev = new Device();
        dev.setId(1);
        dev.setComunidade("alfa");
        dev.setIdentificacao("229829792");
        dev.setVersao("2.1");
        dev.setIp("10.104.1.111");
        OID oid = new OID();
        oid.setDescricao("132.1.1.431.3");
        oid.setPortInicial(1);
        oid.setPortFinal(3);
        dev.setOid(oid);
        listDevice.add(dev);

        dev = new Device();
        dev.setId(2);
        dev.setComunidade("alfa");
        dev.setIdentificacao("229829792");
        dev.setVersao("2.1");
        dev.setIp("10.104.1.112");
        oid = new OID();
        oid.setDescricao("132.1.1.431.3");
        oid.setPortInicial(1);
        oid.setPortFinal(6);
        dev.setOid(oid);
        listDevice.add(dev);
        
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
    
     public List<Device> getByIdentificacao(Device dev) {
        List<Device> devices = new ArrayList<>();
        for (Device device : listDevice) {
            if (device.getIdentificacao().contains(dev.getIdentificacao())) {
                devices.add(device);
            }
        }
        listDevice = new ArrayList<>();
        listDevice = devices;

        return devices;
    }

    public void refreshTable(){
        listDevice = persistDevice;
    }
    public static List<Device> getPersistDevice() {
        return persistDevice;
    }

    public static void setPersistDevice(List<Device> persistDevice) {
        SnmpDAO.persistDevice = persistDevice;
    }

    @Override
    public List resultSetToObject(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}
