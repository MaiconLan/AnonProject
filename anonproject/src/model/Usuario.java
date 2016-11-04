package model;

public class Usuario {

	private Long idUsuario;
	private String usuario;
	private String senha;
	private String status;

	public Usuario() {
		super();
	}

	public Usuario(Long idUsuario, String usuario, String senha, String status) {
		super();
		this.idUsuario = idUsuario;
		this.usuario = usuario;
		this.senha = senha;
		this.status = status;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
