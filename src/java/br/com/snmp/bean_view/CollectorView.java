/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.snmp.bean_view;

/**
 *
 * @author carlos.macedo
 */
import br.com.snmp.model.Book;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
 
@ManagedBean
@ViewScoped
public class CollectorView implements Serializable {
     
    private Book book;
     
    private List<Book> books;
     
    @PostConstruct
    public void init() {
        book = new Book();
        books = new ArrayList<Book>();
         
    }
     
    public void createNew() {
        if(books.contains(book)) {
            FacesMessage msg = new FacesMessage("Dublicated", "This book has already been added");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } 
        else {
            books.add(book);
            book = new Book();
        }
    }
     
    public String reinit() {
        book = new Book();
         
        return null;
    }
 
    public Book getBook() {
        return book;
    }
 
    public List<Book> getBooks() {
        return books;
    }
}