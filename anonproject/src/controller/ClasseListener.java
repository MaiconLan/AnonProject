package controller;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import dao.UsuarioDAO;

public class ClasseListener implements ServletContextListener {

	public Connection conectar() {
		Connection con = null;
		try {
			con = ConnectionFactory.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}

	public void contextInitialized(ServletContextEvent event) {
		UsuarioDAO dao = new UsuarioDAO();

		try {
			dao.inicializarStatus();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void contextDestroyed(ServletContextEvent arg0) {
	}
}
