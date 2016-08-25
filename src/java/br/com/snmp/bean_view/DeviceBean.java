/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.snmp.bean_view;

import br.com.snmp.model.Device;
import br.com.snmp.model.OID;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.primefaces.context.RequestContext;

/**
 *
 * @author carlos.macedo
 */
@ManagedBean
public class DeviceBean {

    private String identificacao;
    private String versao;
    private OID oid;
    private String comunidade;
    private String ip;

    private Device device;
    private List<Device> listDevices;

    @PostConstruct
    public void init() {
        device = new Device();
        listDevices = new ArrayList<Device>();
    }

    public void createNew(ActionEvent event) {
        device.setIdentificacao(identificacao);
        device.setVersao(versao);
//        device.setComunidade(comunidade);
//        device.setIp(ip);
//        device.setOid(oid);

//        if (listDevices.contains(device)) {
//            FacesMessage msg = new FacesMessage("Dublicated", "This device has already been added");
//            FacesContext.getCurrentInstance().addMessage(null, msg);
//        } else {
        if (listDevices == null) 
            listDevices = new ArrayList<Device>();
        listDevices.add(device);

//        }
    }

    public String reinit() {
        device = new Device();
        return null;
    }

    public List<Device> getListDevices() {
        return listDevices;
    }

    public String getIdentificacao() {
        return identificacao;
    }

    public void setIdentificacao(String identificacao) {
        this.identificacao = identificacao;
    }

    public String getVersao() {
        return versao;
    }

    public void setVersao(String versao) {
        this.versao = versao;
    }

    public OID getOid() {
        return oid;
    }

    public void setOid(OID oid) {
        this.oid = oid;
    }

    public String getComunidade() {
        return comunidade;
    }

    public void setComunidade(String comunidade) {
        this.comunidade = comunidade;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

}
