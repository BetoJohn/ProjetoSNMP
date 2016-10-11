/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.snmp.util;

import br.com.snmp.model.DataSnmp;
import br.com.snmp.model.ReturnSnmp;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Path;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
    
     public boolean fileExists(String path) throws FileNotFoundException {
        file = new File(path + "/SnmpResult/file.json");
        boolean exists = file.exists();
        return exists;
    }

    public void writerJson(List<ReturnSnmp> rs, String path) throws IOException {

        File file = new File(path + "/SnmpResult");
         BlowFish bf = new BlowFish();
        if (!file.exists()) {
            file.mkdir();
        }
        if (file.exists()) {
            Gson gson = new Gson();
            // converte objetos Java para JSON e retorna JSON como String
            String json = gson.toJson(rs);
             String jsonCript = bf.cript(json);
            try {
                //Escreve Json convertido em arquivo chamado "file.json"
                buffWrite = new BufferedWriter(new FileWriter(path + "/SnmpResult/file.json"));
                buffWrite.write(jsonCript);
                buffWrite.close();

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        System.out.println("Path File: " + file.getAbsolutePath());

    }

    public List<ReturnSnmp> readerJson(String path) throws IOException {
        Gson gson = new Gson();
         BlowFish bf = new BlowFish();
        try {
            buffReader = new BufferedReader(new FileReader(path + "/SnmpResult/file.json"));
            //Converte String JSON para objeto Java
           // ReturnSnmp obj = gson.fromJson(buffReader, ReturnSnmp.class);

            Type listType = new TypeToken<ArrayList<ReturnSnmp>>() {
            }.getType();

            //------------------------------------------------------------
                StringBuilder builder = new StringBuilder();
                String aux = "";
                while ((aux = buffReader.readLine()) != null) {
                    builder.append(aux);
                }
                String text = builder.toString();
                String jsonDeCript = bf.decript(text);
                //------------------------------------------------------------
            List<ReturnSnmp> founderList = gson.fromJson(jsonDeCript, listType);
            return founderList;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
     
    /***
     * 
     * @param l1 dados da consulta snmp
     * @param l2 dados salvos no banco
     * @return uma lista com os valores que sofreram alteração
     * @throws IOException 
     */
    public List<DataSnmp> compareList(List<DataSnmp> l1, List<DataSnmp> l2) throws IOException {
        Util util = new Util();     
        //Dessa forma consigo criar uma lista com os valores 
        List<DataSnmp> valuesUpdated = new ArrayList<>();
        boolean resultValue;
        //resultado1.size() == resultado2.size() &&
        if (l1 != null && l2 != null) {
            // make a copy of the list so the original list is not changed, and remove() is supported
            ArrayList<DataSnmp> cp = new ArrayList<>(l1);
            for (DataSnmp value : l2) {
                if (!cp.remove(value)) {
                    valuesUpdated.add(value);
                    resultValue = false;
                }
            }
            resultValue = cp.isEmpty();
        } else {
            return new ArrayList<>();
        }

        return valuesUpdated;
    }

    public Timestamp dateToTimestamp(Date date) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            String dataFormat = sdf.format(date.getTime());
            Timestamp timeStampDate = convertStringToTimestamp(dataFormat);
            return timeStampDate;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public Timestamp convertStringToTimestamp(String str_date) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date date = sdf.parse(str_date);
            Timestamp timeStampDate = new Timestamp(date.getTime());
            return timeStampDate;
        } catch (ParseException e) {
            System.out.println("Exception :" + e);
            return null;
        }
    }

    public Timestamp getDateTimestamp() {
        try {
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            String dataFormat = sdf.format(date.getTime());
            Timestamp timeStampDate = convertStringToTimestamp(dataFormat);
            return timeStampDate;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
