/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package business;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author ken
 */
public class Cart implements Serializable {
    private ArrayList<OrderItem> items;
    
    public Cart()
    {
        items = new ArrayList<OrderItem>();
    }
    
    public ArrayList<OrderItem> getItems()
    { 
        return items;
    }
    
    public int getCount()
    { 
        return items.size();
    }
    
    public void addItem(OrderItem item)
    {
        String isbn = item.getBook().getISBN();
        int quantity = item.getQuantity();
        for (int i = 0; i < items.size(); i++)
        {
            OrderItem orderItem = items.get(i);
            if (orderItem.getBook().getISBN().equals(isbn))
            {
                orderItem.setQuantity(quantity);
                return;
            }
        }
        items.add(item);
    }
    
    public void removeItem(OrderItem item)
    {
        String isbn = item.getBook().getISBN();
        for (int i = 0; i < items.size(); i++)
        {
            OrderItem orderItem = items.get(i);
            if (orderItem.getBook().getISBN().equals(isbn))
            {
                items.remove(i);
                return;
            }
        }
    }
}
