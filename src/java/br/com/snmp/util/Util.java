/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.snmp.util;

import br.com.snmp.model.ReturnSnmp;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

/**
 *
 * @author carlos.macedo
 */
public class Util {

    private BufferedWriter buffWrite;
    private BufferedReader buffReader;
    private File file;

    public void leitor(String path) throws IOException {

        BufferedReader buffRead = new BufferedReader(new FileReader(path + "/TestesWebDir/TestesWeb.txt"));
        String linha = "";
        while (true) {
            if (linha != null) {
                System.out.println(linha);

            } else {
                break;
            }
            linha = buffRead.readLine();
        }
        buffRead.close();
    }

    public void escritor(StringBuffer sb, String path) throws IOException {

        File file = new File(path + "/SnmpResult");
        if (!file.exists()) {
            file.mkdir();
        } else {
            buffWrite = new BufferedWriter(new FileWriter(path + "/SnmpResult/file.json"));
            buffWrite.append(sb);
            buffWrite.close();
        }
        System.out.println("Path File: " + file.getAbsolutePath());

//        boolean success = (new File("teste_dir")).mkdirs();
//        file = new File("teste_dir");
//        if (file.exists()) {
//            buffWrite = new BufferedWriter(new FileWriter("teste_dir/TestesWeb.txt"));
//            buffWrite.append(sb);
//            buffWrite.close();
//        }
    }

    public void writerJson(List<ReturnSnmp> rs, String path) throws IOException {

        File file = new File(path + "/SnmpResult");
        if (!file.exists()) {
            file.mkdir();
        }
        if (file.exists()) {
            Gson gson = new Gson();
            // converte objetos Java para JSON e retorna JSON como String
            String json = gson.toJson(rs);
            try {
                //Escreve Json convertido em arquivo chamado "file.json"
                buffWrite = new BufferedWriter(new FileWriter(path + "/SnmpResult/file.json"));
                buffWrite.write(json);
                buffWrite.close();

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        System.out.println("Path File: " + file.getAbsolutePath());

    }

    public List<ReturnSnmp> readerJson(String path) throws IOException {
        Gson gson = new Gson();
        try {
            buffReader = new BufferedReader(new FileReader(path + "/SnmpResult/file.json"));
            //Converte String JSON para objeto Java
           // ReturnSnmp obj = gson.fromJson(buffReader, ReturnSnmp.class);

            Type listType = new TypeToken<ArrayList<ReturnSnmp>>() {
            }.getType();

            List<ReturnSnmp> founderList = gson.fromJson(buffReader, listType);
            return founderList;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
