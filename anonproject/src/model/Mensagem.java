package model;

import java.sql.Time;
import java.util.Date;

public class Mensagem {
	private Long idMensagem;
	private String mensagem;
	private String nomeUsuario;
	private String nomeDestinatario;
	private String senha;
	private Date dataEnvio;
	private Time HoraEnvio;

	public Mensagem() {
	}

	public Mensagem(Long idMensagem, String mensagem, String nomeUsuario, String senha, Date dataEnvio, Time horaEnvio,
			String nomeDestinatario) {
		this.idMensagem = idMensagem;
		this.mensagem = mensagem;
		this.nomeUsuario = nomeUsuario;
		this.senha = senha;
		this.dataEnvio = dataEnvio;
		this.HoraEnvio = horaEnvio;
		this.nomeDestinatario = nomeDestinatario;
	}

	public Long getIdMensagem() {
		return this.idMensagem;
	}

	public void setIdMensagem(Long idMensagem) {
		this.idMensagem = idMensagem;
	}

	public String getMensagem() {
		return this.mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getNomeUsuario() {
		return this.nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getSenha() {
		return this.senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Date getDataEnvio() {
		return this.dataEnvio;
	}

	public void setDataEnvio(Date dataEnvio) {
		this.dataEnvio = dataEnvio;
	}

	public Time getHoraEnvio() {
		return this.HoraEnvio;
	}

	public void setHoraEnvio(Time horaEnvio) {
		this.HoraEnvio = horaEnvio;
	}

	public String getNomeDestinatario() {
		return nomeDestinatario;
	}

	public void setNomeDestinatario(String nomeDestinatario) {
		this.nomeDestinatario = nomeDestinatario;
	}
}
