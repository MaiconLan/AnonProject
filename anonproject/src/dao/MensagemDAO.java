/*
 * Decompiled with CFR 0_118.
 * 
 * Could not load the following classes:
 *  controller.ConnectionFactory
 *  model.Mensagem
 *  model.Usuario
 */
package dao;

import controller.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Mensagem;
import model.Usuario;

public class MensagemDAO {
    int count = 0;
    int novoCount = 0;

    public void enviaMensagem(Mensagem msg, Usuario usu) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        String sql = "INSERT INTO mensagem(nom_mensagem, nom_nome, data_envio) VALUES (?, ?, ?);";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, msg.getMensagem());
        stmt.setString(2, usu.getNome());
        stmt.setTimestamp(3, MensagemDAO.getCurrentTimeStamp());
        stmt.execute();
        stmt.close();
        con.close();
    }

    public List<Mensagem> listarMensagem() throws SQLException {
        String sql = "SELECT cod_mensagem, nom_mensagem, nom_nome, pg_catalog.time(data_envio) AS hora_envio, date(data_envio) AS data_envio FROM mensagem ORDER BY data_envio DESC, hora_envio DESC";
        Connection connection = ConnectionFactory.getConnection();
        ArrayList<Mensagem> mensagens = new ArrayList<Mensagem>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Mensagem mensagem = new Mensagem();
                mensagem.setIdMensagem(Long.valueOf(rs.getLong("cod_mensagem")));
                mensagem.setMensagem(rs.getString("nom_mensagem"));
                mensagem.setNomeUsuario(rs.getString("nom_nome"));
                mensagem.setDataEnvio((Date)rs.getDate("data_envio"));
                mensagem.setHoraEnvio(rs.getTime("hora_envio"));
                mensagens.add(mensagem);
            }
            stmt.execute();
            stmt.close();
            connection.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return mensagens;
    }

    public boolean contaMensagem() throws SQLException {
        boolean novaMsg = false;
        String sql = "SELECT count(*) AS count FROM mensagem;";
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = con.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            this.count = rs.getInt("count");
            novaMsg = this.count != this.novoCount;
        }
        stmt.execute();
        stmt.close();
        con.close();
        this.novoCount = this.count;
        return novaMsg;
    }

    private static Timestamp getCurrentTimeStamp() {
        Date today = new Date();
        return new Timestamp(today.getTime());
    }
}
