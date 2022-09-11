package repository;

import model.Bookings;
import model.Client;
import model.Hotel;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionManager {

    private static SessionFactory factory;


    public static SessionFactory getFactory(){
        try {
            factory = new Configuration()
                    .configure("hibernate.cfg.xml")
                    .addAnnotatedClass(Client.class)
                    .addAnnotatedClass(Hotel.class)
                    .addAnnotatedClass(Bookings.class)
                    .buildSessionFactory();
        } catch (Exception e) {
            System.out.println(e.getClass() + " : " + e.getMessage());
        }
        return factory;
    }

}
