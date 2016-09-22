/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.snmp.service;

import br.com.snmp.BO.SnmpBO;
import br.com.snmp.model.Device;
import br.com.snmp.model.ResultSnmp;
import br.com.snmp.model.ReturnSnmp;
import br.com.snmp.util.Util;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.Target;
import org.snmp4j.TransportMapping;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.Address;
import org.snmp4j.smi.GenericAddress;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.VariableBinding;
import org.snmp4j.transport.DefaultUdpTransportMapping;

/**
 *
 * @author carlos.macedo
 */
public class SnmpService {
    
    private Snmp snmp = null;
    private String address = null;
    private Date date;
    private SimpleDateFormat sdf;

    /**
     * Constructor
     *
     * @param ipDevice
     * @param portInicial
     * @param portFinal
     */
//    public SnmpService(String ipDevice, int portInicial, int portFinal) {
//        this.ipDevice = ipDevice;
//        this.portInicial = portInicial;
//        this.portFinal = portFinal;
//    }
    public void getValueSnmp(String path) throws Exception {
        try {
            List<ReturnSnmp> mainList = new ArrayList<>();
            
            for (Device device : SnmpBO.getInstance().getAllDevices()) {
                int portInicial = device.getPortInicial();
                int portFinal = device.getPortFinal();
                
                if (portFinal > portInicial) {
                    ReturnSnmp rs = new ReturnSnmp();
                    rs.setId(device.getId());
                    date = new Date();
                    sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");                    
                    rs.setTime(sdf.format(date));
                    rs.setIpDevice(device.getIp());
                    List<ResultSnmp> listResult = new ArrayList<>();
                    for (int i = portInicial; i <= portFinal; i++) {
                        
                        try {
                            address = "udp:" + device.getIp() + "/161";
                            start();
                            String sysDescr = getAsString(new OID("1.3.6.1.2.1.2.2.1.8." + i));
                            ResultSnmp result = new ResultSnmp();
                            result.setPort(i);
                            
                            switch (sysDescr) {
                                case "1":
                                    result.setValue(Integer.parseInt(sysDescr));
                                    result.setStatus("UP");
                                    break;
                                case "2":
                                    result.setValue(Integer.parseInt(sysDescr));
                                    result.setStatus("DOWN");
                                    break;
                                case "noSuchInstance":
                                    result.setValue(3);
                                    result.setStatus("NOSUCHINSTANCE");
                                    break;
                            }

//                            if (sysDescr.equalsIgnoreCase("1")) {
//                                result.setValue(Integer.parseInt(sysDescr));
//                                result.setStatus("UP");
//                            } else if (sysDescr.equalsIgnoreCase("2")) {
//                                result.setValue(Integer.parseInt(sysDescr));
//                                result.setStatus("DOWN");
//                            }
                            listResult.add(result);
                            
                        } catch (Exception ex) {
                            Logger.getLogger(SnmpService.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                    }
                    rs.setResult(listResult);
                    mainList.add(rs);
                    
                } else {
                    Logger.getLogger(SnmpService.class.getName()).log(Level.INFO, "A porta final deve ser maior que a porta inicial", "");
                }
            }
            
            Util u = new Util();
            u.writerJson(mainList, path);
        } catch (IOException ex) {
            Logger.getLogger(SnmpService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    /**
     * Start the Snmp session. If you forget the listen() method you will not
     * get any answers because the communication is asynchronous and the
     * listen() method listens for answers.
     *
     * @throws IOException
     */
    private void start() throws IOException {
        TransportMapping transport = new DefaultUdpTransportMapping();
        snmp = new Snmp(transport);
// Do not forget this line!
        transport.listen();
    }

    /**
     * Method which takes a single OID and returns the response from the agent
     * as a String.
     *
     * @param oid
     * @return
     * @throws IOException
     */
    public String getAsString(OID oid) throws IOException {
        ResponseEvent event = get(new OID[]{oid});
        return event.getResponse().get(0).getVariable().toString();
    }

    /**
     * This method is capable of handling multiple OIDs
     *
     * @param oids
     * @return
     * @throws IOException
     */
    public ResponseEvent get(OID oids[]) throws IOException {
        PDU pdu = new PDU();
        for (OID oid : oids) {
            pdu.add(new VariableBinding(oid));
        }
        pdu.setType(PDU.GET);
        ResponseEvent event = snmp.send(pdu, getTarget(), null);
        if (event != null) {
            return event;
        }
        throw new RuntimeException("GET timed out");
    }

    /**
     * This method returns a Target, which contains information about where the
     * data should be fetched and how.
     *
     * @return
     */
    private Target getTarget() {
        Address targetAddress = GenericAddress.parse(address);
        CommunityTarget target = new CommunityTarget();
        target.setCommunity(new OctetString("public"));
        target.setAddress(targetAddress);
        target.setRetries(2);
        target.setTimeout(1500);
        target.setVersion(SnmpConstants.version2c);
        return target;
    }
    
}
