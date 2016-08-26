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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.event.ActionEvent;
import org.primefaces.context.RequestContext;

/**
 *
 * @author carlos.macedo
 */
@ManagedBean
public class DeviceOIDBean implements Serializable {

    private String descricao;
    private OID oid;
    private Map<String, String> oids = new HashMap<String, String>();

    @PostConstruct
    public void init() {
        oid = new OID();
        oids = new HashMap<String, String>();
        oids.put("New York", "New York");
        oids.put("London","London");
        oids.put("Paris","Paris");
        oids.put("Barcelona","Barcelona");
        oids.put("Istanbul","Istanbul");
        oids.put("Berlin","Berlin");
    }

    public void createNewOID(ActionEvent event) {
        System.out.println(oid.toString());
    }

      public List<String> completeOID(String query) {
        List<String> results = new ArrayList<String>();
        for (int i = 0; i < 10; i++) {
            results.add(query + i);
        }

        return results;
    }
      
    public String reinit() {
        oid = new OID();
        RequestContext.getCurrentInstance().update("");
        return null;
    }

    public OID getOid() {
        return oid;
    }

    public String getDescricao() {
        return descricao;
    }

    public Map<String, String> getOids() {
        return oids;
    }

}
