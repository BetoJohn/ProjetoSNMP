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
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.event.ActionEvent;

/**
 *
 * @author carlos.macedo
 */
@ManagedBean
public class DeviceOIDBean implements Serializable {

    private String descricao;
    private OID oid;

    @PostConstruct
    public void init() {
        oid = new OID();
    }

    public void createNewOID(ActionEvent event) {
        System.out.println(oid.toString());
    }

    public String reinit() {
        oid = new OID();
        return null;
    }

    public OID getOid() {
        return oid;
    }

    public String getDescricao() {
        return descricao;
    }

}
