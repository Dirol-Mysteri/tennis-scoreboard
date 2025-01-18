package org.example.tennisscoreboard.commons;

import org.example.tennisscoreboard.models.Match;
import org.example.tennisscoreboard.models.Player;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.lang.reflect.Proxy;

import static org.hibernate.cfg.JdbcSettings.*;
import static org.hibernate.cfg.SchemaToolingSettings.HBM2DDL_AUTO;

public class HibernateUtil {
    private static SessionFactory sessionFactory;

    static {
        try {
            sessionFactory = new Configuration()
                    .addAnnotatedClass(Player.class)
                    .addAnnotatedClass(Match.class)
                    .setProperty("hibernate.current_session_context_class", "thread")
                    .setProperty(JAKARTA_JDBC_DRIVER, "org.h2.Driver")
                    .setProperty(JAKARTA_JDBC_URL, "jdbc:h2:mem:testdb")
                    .setProperty(DIALECT, "org.hibernate.dialect.H2Dialect")
                    .setProperty(HBM2DDL_AUTO, "create")
                    .setProperty(SHOW_SQL, true)
                    .setProperty(FORMAT_SQL, true)
                    .setProperty(HIGHLIGHT_SQL, true)
                    .buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() {
        return sessionFactory.openSession();
    }

    public static Session getCurrentSession() {

        var session = (Session) Proxy.newProxyInstance(SessionFactory.class.getClassLoader(), new Class[]{Session.class}, (proxy, method, args1) ->
                method.invoke(sessionFactory.getCurrentSession(), args1)
        );

        return session;
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