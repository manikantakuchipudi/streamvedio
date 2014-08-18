/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.io.*;
import java.util.*;

import business.*;

public class BookRepository {

    public static Book getBook(String isbn, String filepath) {
        try {
            File file = new File(filepath);
            BufferedReader in = new BufferedReader(new FileReader(file));

            String line = in.readLine();
            while (line != null) {
                StringTokenizer t = new StringTokenizer(line, "|");
                String bookISBN = t.nextToken();
                if (isbn.equalsIgnoreCase(bookISBN)) {
                    String title = t.nextToken();
                    double price = Double.parseDouble(t.nextToken());
                    Book p = new Book();
                    p.setISBN(isbn);
                    p.setTitle(title);
                    p.setPrice(price);
                    in.close();
                    return p;
                }
                line = in.readLine();
            }
            in.close();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ArrayList<Book> getBooks(String filepath) {
        ArrayList<Book> books = new ArrayList<Book>();
        File file = new File(filepath);
        try {
            BufferedReader in = new BufferedReader(new FileReader(file));
            String line = in.readLine();
            while (line != null) {
                StringTokenizer t = new StringTokenizer(line, "|");
                String isbn = t.nextToken();
                String title = t.nextToken();
                String strPrice = t.nextToken();
                double price = Double.parseDouble(strPrice);
                Book p = new Book();
                p.setISBN(isbn);
                p.setTitle(title);
                p.setPrice(price);
                books.add(p);
                line = in.readLine();
            }
            in.close();
            return books;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
