package org.example.tennisscoreboard.commons;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import static org.hibernate.cfg.JdbcSettings.*;
import static org.hibernate.cfg.SchemaToolingSettings.JAKARTA_HBM2DDL_DATABASE_ACTION;

public class HibernateUtil {
    private static SessionFactory sessionFactory;

    static {
        try {
            sessionFactory = new Configuration()
                    .setProperty(JAKARTA_JDBC_URL, "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1")
                    .setProperty(DIALECT, "org.hibernate.dialect.H2Dialect")
                    .setProperty(JAKARTA_HBM2DDL_DATABASE_ACTION, "create-drop")
                    .setProperty(SHOW_SQL, true)
                    .setProperty(FORMAT_SQL, true)
                    .setProperty(HIGHLIGHT_SQL, true)
                    .buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    public static void closeSessionFactory() {
        if (sessionFactory != null) {
            try {
                sessionFactory.close();
                sessionFactory = null;
            } catch (Exception e) {
                System.err.println("Error closing SessionFactory:" + e.getMessage());
            }
        }
    }


}