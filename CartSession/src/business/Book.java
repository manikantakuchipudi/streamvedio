/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package business;
import java.io.Serializable;
import java.text.NumberFormat;

/**
 *
 * @author ken
 */
public class Book implements Serializable {
    private String isbn;
    private String title;
    private double price;
    
    public Book()
    {
        isbn = "";
        title = "";
        price = 0;
    }

    /**
     * @return the isbn
     */
    public String getISBN() {
        return isbn;
    }

    /**
     * @param isbn the isbn to set
     */
    public void setISBN(String isbn) {
        this.isbn = isbn;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }
    
    public String getPriceCurrencyFormat()
    {
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        return currency.format(price);
    } 
}
