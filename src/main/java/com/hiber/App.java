package com.hiber;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class App {
    public static void main(String[] args) {
        
        // 1. Create Configuration and build SessionFactory
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        // Ensure the entity is recognized
        cfg.addAnnotatedClass(Product.class);
        
        SessionFactory factory = cfg.buildSessionFactory();

        // 2. Create a Product object using your updated field names
        Product p = new Product();
        p.setId(101);
        p.setName("Keyboard");
        p.setPrice(1500.0);

        // 3. Open Session and Start Transaction
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        try {
            // 4. Save the product to the database
            session.save(p);
            
            // 5. Commit the transaction
            tx.commit();
            System.out.println("Product saved successfully to the 'f1' database!");
            
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            // 6. Close resources
            session.close();
            factory.close();
        }
    }
}