package dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import controller.ClasseListener;
import model.Usuario;

public class UsuarioDAO implements Serializable {
	private static final long serialVersionUID = 1;
	ClasseListener cl = new ClasseListener();
	Connection con = cl.conectar();

	public void alteraStatusUsuario(String user, String status) throws SQLException {
		String sql = "UPDATE usuario SET cod_status=? WHERE nom_login=?;";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, status);
		stmt.setString(2, user);
		stmt.execute();
		stmt.close();
	}

	public void alteraUsuario(Usuario usuarioLogado, Usuario newUsuario) throws SQLException {
		String sql = "UPDATE usuario SET nom_nome=?, nom_senha=? WHERE nom_login=? and nom_senha=?;";

		PreparedStatement stmt = con.prepareStatement(sql);

		stmt.setString(1, newUsuario.getNome());
		stmt.setString(2, newUsuario.getSenha());
		stmt.setString(3, usuarioLogado.getLogin());
		stmt.setString(4, usuarioLogado.getSenha());
		stmt.execute();
		stmt.close();
	}

	public void inicializarStatus() throws SQLException {
		String sql = "UPDATE usuario SET cod_status='Offline'";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.execute();
		stmt.close();
	}

	public List<Usuario> usuariosLogados() throws SQLException {
		String sql = "SELECT cod_usuario, nom_nome, nom_login, cod_status FROM usuario where cod_status<>'Offline' ORDER BY cod_status DESC;";
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Usuario usuario = new Usuario();
				usuario.setNome(rs.getString("nom_nome"));
				usuario.setIdUsuario(Long.valueOf(rs.getLong("cod_usuario")));
				usuario.setLogin(rs.getString("nom_login"));
				usuario.setStatus(rs.getString("cod_status"));
				if (usuario.getStatus().equals("Online")) {
					usuario.setCor("Green");
				} else if (usuario.getStatus().equals("Ausente")) {
					usuario.setCor("Orange");
				}
				usuarios.add(usuario);
			}
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return usuarios;
	}

	public void inserirUsuario(Usuario usuario) throws SQLException {
		String sql = "INSERT INTO usuario(nom_nome, nom_login, nom_senha) VALUES (?, ?, ?);";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, usuario.getNome());
		stmt.setString(2, usuario.getLogin());
		stmt.setString(3, usuario.getSenha());
		stmt.execute();
		stmt.close();
	}

	public List<Usuario> listarUsuario() throws SQLException {
		String sql = "SELECT cod_usuario, nom_nome, nom_login, cod_status FROM usuario";
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Usuario usuario = new Usuario();
				usuario.setNome(rs.getString("nom_nome"));
				usuario.setIdUsuario(Long.valueOf(rs.getLong("cod_usuario")));
				usuario.setLogin(rs.getString("nom_login"));
				usuario.setStatus(rs.getString("cod_status"));
				usuarios.add(usuario);
			}
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return usuarios;
	}

	public Usuario informacaoUsuario(String user) throws SQLException {
		Usuario usuario = new Usuario();
		String sql = "SELECT cod_usuario, nom_login, nom_nome, cod_status FROM usuario WHERE nom_login=?";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, user);
		ResultSet rs = stmt.executeQuery();
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
		while (rs.next()) {
			usuario.setIdUsuario(Long.valueOf(rs.getLong("cod_usuario")));
			usuario.setLogin(rs.getString("nom_login"));
			usuario.setNome(rs.getString("nom_nome"));
			usuario.setStatus(rs.getString("cod_status"));
			usuarios.add(usuario);
		}
		stmt.execute();
		stmt.close();
		return usuario;
	}

	public boolean isValido(String user, String pass) throws SQLException {
		int num = 0;
		boolean logged = false;
		String sql = "SELECT COUNT(*) AS qtd FROM usuario WHERE nom_login=? AND nom_senha=?;";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, user);
		stmt.setString(2, pass);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			num = rs.getInt("qtd");
			logged = num == 1;
		}
		stmt.execute();
		stmt.close();
		return logged;
	}
}
