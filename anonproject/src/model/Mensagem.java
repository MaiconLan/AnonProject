package model;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

public class Mensagem {

	private Long idMensagem;
	private String mensagem;
	private String usuario;
	private String senha;
	private Date dataEnvio;
	private Time HoraEnvio;

	public Mensagem() {

	}

	public Mensagem(Long idMensagem, String mensagem, String usuario, String senha, Date dataEnvio, Time horaEnvio) {
		super();
		this.idMensagem = idMensagem;
		this.mensagem = mensagem;
		this.usuario = usuario;
		this.senha = senha;
		this.dataEnvio = dataEnvio;
		HoraEnvio = horaEnvio;
	}

	public Long getIdMensagem() {
		return idMensagem;
	}

	public void setIdMensagem(Long idMensagem) {
		this.idMensagem = idMensagem;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Date getDataEnvio() {
		return dataEnvio;
	}

	public void setDataEnvio(Date dataEnvio) {
		this.dataEnvio = dataEnvio;
	}

	public Time getHoraEnvio() {
		return HoraEnvio;
	}

	public void setHoraEnvio(Time horaEnvio) {
		HoraEnvio = horaEnvio;
	}

}