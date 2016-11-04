package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Mensagem;
import model.Usuario;
import controller.ConnectionFactory;

public class MensagemDAO {

	public void enviaMensagem(Mensagem msg, Usuario usu) throws SQLException {

		Connection con = ConnectionFactory.getConnection();
		String sql = "INSERT INTO mensagem(nom_mensagem, nom_usuario, data_envio) VALUES (?, ?, ?);";

		PreparedStatement stmt = con.prepareStatement(sql);

		stmt.setString(1, msg.getMensagem());
		stmt.setString(2, usu.getUsuario());
		stmt.setTimestamp(3, getCurrentTimeStamp());

		stmt.execute();
		stmt.close();
		con.close();
	}

	public List<Mensagem> listarMensagem() throws SQLException {
		String sql = "SELECT cod_mensagem, nom_mensagem, nom_usuario, pg_catalog.time(data_envio) AS hora_envio, date(data_envio) AS data_envio FROM mensagem ORDER BY data_envio DESC, hora_envio DESC";

		Connection connection = ConnectionFactory.getConnection();
		List<Mensagem> mensagens = new ArrayList<Mensagem>();

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Mensagem mensagem = new Mensagem();
				mensagem.setIdMensagem(rs.getLong("cod_mensagem"));
				mensagem.setMensagem(rs.getString("nom_mensagem"));
				mensagem.setUsuario(rs.getString("nom_usuario"));
				mensagem.setDataEnvio(rs.getDate("data_envio"));
				mensagem.setHoraEnvio(rs.getTime("hora_envio"));

				mensagens.add(mensagem);
			}

			stmt.execute();
			stmt.close();
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mensagens;
	}

	int count = 0;
	int novoCount = 0;

	public boolean contaMensagem() throws SQLException {
		boolean novaMsg = false;
		String sql = "SELECT count(*) AS count FROM mensagem;";

		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = con.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			count = rs.getInt("count");
			if (count != novoCount) {
				novaMsg = true;
			} else {
				novaMsg = false;
			}
		}
		stmt.execute();
		stmt.close();
		con.close();
		
		novoCount = count;
		
		return novaMsg;
	}

	private static java.sql.Timestamp getCurrentTimeStamp() {

		java.util.Date today = new java.util.Date();
		return new java.sql.Timestamp(today.getTime());
	}
}
