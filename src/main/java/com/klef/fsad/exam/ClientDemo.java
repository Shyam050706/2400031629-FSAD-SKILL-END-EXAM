package com.klef.fsad.exam;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;

import java.util.Date;

public class ClientDemo 
{
    public static void main(String[] args) 
    {
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");

        SessionFactory sf = cfg.buildSessionFactory();
        Session s = sf.openSession();
        Transaction tx = s.beginTransaction();

        // I. Insert
        Inventory obj = new Inventory();
        obj.setName("Laptop");
        obj.setDescription("Dell i5");
        obj.setDate(new Date());
        obj.setStatus("Available");

        s.save(obj);

        System.out.println("Inserted Successfully");

        // II. Delete (by ID)
        int deleteId = obj.getId(); // deleting same inserted

        Inventory delObj = s.get(Inventory.class, deleteId);
        if(delObj != null)
        {
          s.delete(delObj);
         System.out.println("Deleted Successfully");
        }

        tx.commit();

        s.close();
        sf.close();
    }
}