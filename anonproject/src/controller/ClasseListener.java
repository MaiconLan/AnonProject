/*
 * Decompiled with CFR 0_118.
 * 
 * Could not load the following classes:
 *  dao.UsuarioDAO
 *  javax.servlet.ServletContextEvent
 *  javax.servlet.ServletContextListener
 */
package controller;

import dao.UsuarioDAO;
import java.sql.SQLException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ClasseListener
implements ServletContextListener {
    public void contextInitialized(ServletContextEvent event) {
        UsuarioDAO dao = new UsuarioDAO();
        try {
            dao.inicializarStatus();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void contextDestroyed(ServletContextEvent arg0) {
    }
}
