/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.snmp.model;

import java.io.Serializable;

/**
 *
 * @author carlos.macedo
 */
public class OID implements Serializable{
    private int id;
    private String descricao;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
   

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

  

    

    @Override
    public String toString() {
        return " OID Descrição: "+descricao; //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
