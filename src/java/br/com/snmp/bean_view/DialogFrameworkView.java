/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.snmp.bean_view;

import java.util.HashMap;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import org.primefaces.context.RequestContext;

/**
 *
 * @author carlos.macedo
 */
@ManagedBean(name = "dialogView")
public class DialogFrameworkView {

    public void dialogView() {
        Map<String, Object> options = new HashMap<String, Object>();
        options.put("resizable", false);
//        options.put("draggable", false);
        options.put("modal", true);
//        options.put("closeOnEscape", true);
        options.put("responsive", true);
//       options.put("width", 600);
//        options.put("height", 600);
        RequestContext.getCurrentInstance().openDialog("dialogCadastroDevice", options, null);
    }
}
