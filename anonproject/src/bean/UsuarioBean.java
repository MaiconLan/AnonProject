package bean;

import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import dao.UsuarioDAO;
import model.Usuario;

@ManagedBean(name = "userBean")
@ViewScoped
public class UsuarioBean implements Serializable {
	private static final long serialVersionUID = 1;
	private Usuario usuario;
	private List<Usuario> lista;
	private UsuarioDAO dao;
	private String pass;

	@PostConstruct
	public void postConstruct() {
		this.dao = new UsuarioDAO();
		this.usuario = new Usuario();
	}

	public void salvar() {
		try {
			if (this.usuario.getIdUsuario() == null) {
				this.dao.inserirUsuario(this.usuario);
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage("Cadastrado com sucesso", "Seu registro foi cadastrado"));
				FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
			}
			this.usuario = new Usuario();
			this.lista = this.dao.listarUsuario();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void carregar(Long id) throws SQLException {
		this.dao.listarUsuario();
	}

	public static long getSerialversionuid() {
		return 1;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getLista() {
		return this.lista;
	}

	public void setLista(List<Usuario> lista) {
		this.lista = lista;
	}

	public UsuarioDAO getDao() {
		return this.dao;
	}

	public void setDao(UsuarioDAO dao) {
		this.dao = dao;
	}

	public String getPass() {
		return this.pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}
}
