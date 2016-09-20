/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.snmp.bean;

import br.com.snmp.BO.SnmpBO;
import br.com.snmp.model.Device;
import br.com.snmp.model.OID;
import com.lowagie.text.Cell;
import com.lowagie.text.Row;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.validation.constraints.Min;
import org.apache.jasper.tagplugins.jstl.ForEach;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.event.CloseEvent;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author carlos.macedo
 */
@ManagedBean(name = "deviceSnmpBean")
@SessionScoped
//@RequestScoped
public class DeviceBean implements Serializable {

    private Device device;
    private OID oid;
    private List<Device> listDevices;
    private Device selectedDevice;
    private List<Device> filteredDevices;
    private String filterBy;
    private String search;

    @PostConstruct
    public void init() {
        device = new Device();
        oid = new OID();
        device.setOid(oid);

        listDevices = SnmpBO.getInstance().getAllDevices();
    }

    public String reinit() {
        device = new Device();
        oid = new OID();
        device.setOid(oid);
        return null;
    }

    public void createNewDevice(ActionEvent event) {
        SnmpBO.getInstance().saveDevice(device);
        System.out.println("createNewDevice DeviceBean: " + device.toString() + " - " + oid.toString());
        RequestContext.getCurrentInstance().update("form:devices");
    }

    public void editDevice() {
        System.out.println("Device Edit: " + device.toString());
        RequestContext.getCurrentInstance().execute("PF('deviceDialogEditar').hide()");
    }

    public void excluirDevice() {
        System.out.println("Device: " + selectedDevice.getIdentificacao());
        RequestContext.getCurrentInstance().execute("PF('deviceDialogExcluir').hide()");
    }

    public void createNewOID(ActionEvent event) {
        SnmpBO.getInstance().saveOID(oid);
        System.out.println(device.getOid().toString());
    }

    public List<String> completeOID(String query) {
        List<String> results = new ArrayList<String>();
        for (OID result : SnmpBO.getInstance().getAllOID()) {
            results.add(result.getDescricao());
        }
        return results;
    }

    public void selectedDeviceDoDevice() {
        setOid(selectedDevice.getOid());
        setDevice(selectedDevice);
        //device = selectedDevice;
        device.setOid(oid);
    }

    public void onRowSelect(SelectEvent eventSelect) {
        setOid(selectedDevice.getOid());
        setDevice(selectedDevice);
        //device = selectedDevice;
        device.setOid(oid);
//        FacesMessage msg;
//        msg = new FacesMessage("Device Selected", " " + ((Device) eventSelect.getObject()).getIdentificacao());
//        FacesContext.getCurrentInstance().addMessage(null, msg);
        System.out.println("Device Selected: " + ((Device) eventSelect.getObject()).getIdentificacao());

    }

    public void onRowEdit(RowEditEvent event) {
//        FacesMessage msg;
//        msg = new FacesMessage("Device Selected", " " + ((Device) event.getObject()).getIdentificacao());
//        FacesContext.getCurrentInstance().addMessage(null, msg);
        System.out.println("Device Selected" + ((Device) event.getObject()).getIdentificacao());

    }

    public void onRowUnselect(UnselectEvent event) {
        FacesMessage msg = new FacesMessage("Device Unselected", " " + ((Device) event.getObject()).getIdentificacao());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void dialogViewEditDevice() {
        Map<String, Object> options = new HashMap<String, Object>();
        options.put("resizable", false);
        options.put("modal", true);
        options.put("responsive", true);
//        options.put("contentHeight", 540);
//        options.put("contentWidth", 560);
        RequestContext.getCurrentInstance().openDialog("Telas/dialogEditDevice", options, null);

    }

    public List getListFilter() {
        List<String> filters = new ArrayList<>();
        filters.add("Id");
        filters.add("Identificação");
        filters.add("Versão");
        filters.add("Comunidade");
        filters.add("Ip");
        return filters;
    }

    public void getByIdentificacao() {
        device = new Device();
        String filter = filterBy;
        device.setIdentificacao(search);
        SnmpBO.getInstance().getByIdentificacao(device);
        device = new Device();
    }

    public void refreshTable() {
        //SnmpBO.getInstance().refreshTable();
        RequestContext.getCurrentInstance().update("form:devices");
    }

    public List<Device> getListDevices() {
        listDevices = SnmpBO.getInstance().getAllDevices();
        return listDevices;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public OID getOid() {
        return oid;
    }

    public void setOid(OID oid) {
        this.oid = oid;
    }

    public Device getSelectedDevice() {
        return selectedDevice;
    }

    public void setSelectedDevice(Device selectedDevice) {
        this.selectedDevice = selectedDevice;
    }

    public List<Device> getFilteredDevices() {
        return filteredDevices;
    }

    public void setFilteredDevices(List<Device> filteredDevices) {
        this.filteredDevices = filteredDevices;
    }

    public String getFilterBy() {
        return filterBy;
    }

    public void setFilterBy(String filterBy) {
        this.filterBy = filterBy;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

}
