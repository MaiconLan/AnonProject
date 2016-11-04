package dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Usuario;
import controller.ConnectionFactory;

public class UsuarioDAO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public void statusUsuario(String user, String status) throws SQLException{
		Connection con = ConnectionFactory.getConnection();
		String sql = "UPDATE usuario SET cod_status=? WHERE nom_usuario=?;";

		PreparedStatement stmt = con.prepareStatement(sql);

		stmt.setString(1, status);
		stmt.setString(2, user);
		
		stmt.execute();
		stmt.close();
		con.close();
	}
	
	public List<Usuario> usuariosLogados() throws SQLException{
		String sql = "SELECT cod_usuario, nom_usuario, cod_status FROM usuario ORDER BY cod_status DESC;";

		Connection connection = ConnectionFactory.getConnection();
		List<Usuario> usuarios = new ArrayList<Usuario>();

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Usuario usuario = new Usuario();
				
				usuario.setIdUsuario(rs.getLong("cod_usuario"));
				usuario.setUsuario(rs.getString("nom_usuario"));
				usuario.setStatus(rs.getString("cod_status"));

				usuarios.add(usuario);
			}

			stmt.execute();
			stmt.close();
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return usuarios;
	}
	
	public void inserirUsuario(Usuario usuario) throws SQLException {

		Connection con = ConnectionFactory.getConnection();
		String sql = "INSERT INTO usuario(nom_usuario, nom_senha) VALUES (?, ?);";

		PreparedStatement stmt = con.prepareStatement(sql);

		stmt.setString(1, usuario.getUsuario());
		stmt.setString(2, usuario.getSenha());
		
		stmt.execute();
		stmt.close();
		con.close();
	}

	public List<Usuario> listarUsuario() throws SQLException {
		String sql = "SELECT cod_usuario, nom_usuario, cod_status FROM usuario";

		Connection connection = ConnectionFactory.getConnection();
		List<Usuario> usuarios = new ArrayList<Usuario>();

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Usuario usuario = new Usuario();
				
				usuario.setIdUsuario(rs.getLong("cod_usuario"));
				usuario.setUsuario(rs.getString("nom_usuario"));
				usuario.setStatus(rs.getString("cod_status"));

				usuarios.add(usuario);
			}

			stmt.execute();
			stmt.close();
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return usuarios;
	}
	
	public Usuario informacaoUsuario(String user) throws SQLException {

		Usuario usuario = new Usuario();
		Connection con = ConnectionFactory.getConnection();
		String sql = "SELECT cod_usuario, nom_usuario, cod_status FROM usuario WHERE nom_usuario=?";

		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, user);
		ResultSet rs = stmt.executeQuery();

		List<Usuario> usuarios = new ArrayList<>();
		
		while (rs.next()) {

			usuario.setIdUsuario(rs.getLong("cod_usuario"));
			usuario.setUsuario(rs.getString("nom_usuario"));
			usuario.setStatus(rs.getString("cod_status"));

			usuarios.add(usuario);
		}

		stmt.execute();
		stmt.close();
		con.close();
		
		return usuario;
	}

	public boolean isValido(String user, String pass) throws SQLException {
		int num = 0;
		boolean logged = false;
		Connection con = ConnectionFactory.getConnection();

		String sql = "SELECT COUNT(*) AS qtd FROM usuario WHERE nom_usuario=? AND nom_senha=?;";
		PreparedStatement stmt = con.prepareStatement(sql);

		stmt.setString(1, user);
		stmt.setString(2, pass);

		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			num = rs.getInt("qtd");
			if (num == 1) {
				logged = true;
			} else {
				logged = false;
			}
		}

		stmt.execute();
		stmt.close();
		con.close();

		return logged;
	}
	
}
