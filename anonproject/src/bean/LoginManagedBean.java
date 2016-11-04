package bean;

import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import dao.UsuarioDAO;
import model.Usuario;

@Named(value = "loginMb")
@SessionScoped
public class LoginManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private UsuarioDAO dao;
	private Usuario usuarioLogado;
	private String user;
	private String pass;
	private List<Usuario> usuariosLogados;
	private boolean logado = false;
	private boolean admin = false;

	@PostConstruct
	public void postConstruct() {
		usuariosLogados = new ArrayList<Usuario>();
	}
	
	public void isValido() throws SQLException, IOException {
		dao = new UsuarioDAO();
		usuarioLogado = new Usuario();
		FacesContext context = FacesContext.getCurrentInstance();

		try {
			logado = dao.isValido(user, pass);

			if (logado) {
				
				dao.statusUsuario(user, "Online");
				this.usuarioLogado = dao.informacaoUsuario(user);
				
				HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
				session.setAttribute("usuarioLogado", usuarioLogado);

				context.addMessage(null,
						new FacesMessage("Login efetuado com sucesso!", "Login efetuado com sucesso."));
				FacesContext.getCurrentInstance().getExternalContext().redirect("mensagem.xhtml");
			} else {
				dao.statusUsuario(user, "Offline");
				context.addMessage(null,
						new FacesMessage("Login e/ou senha incorretos!", "Login e/ou senha incorretos."));
			}
		} catch (Exception e) {
			context.addMessage(null,
					new FacesMessage("Não foi possível realizar o Login!", "Não foi possível realizar o Login." + e));
			e.printStackTrace();
		}

	}

	public List<Usuario> usuarioLogado() throws SQLException {
		this.usuariosLogados = dao.usuariosLogados();
		return this.usuariosLogados;
	}

	public void logout() throws IOException, SQLException {
		dao.statusUsuario(user, "Offline");
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.invalidate();

		fc.getExternalContext().redirect("login.xhtml");
	}

	public boolean isLogado() {
		return logado;
	}

	public boolean isAdmin() {
		return admin;
	}

	public Usuario getUsuario() {
		return this.usuarioLogado;
	}

	public void setUsuarioDAO(UsuarioDAO dao) {
		this.dao = dao;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public UsuarioDAO getDao() {
		return dao;
	}

	public void setDao(UsuarioDAO dao) {
		this.dao = dao;
	}

	public List<Usuario> getUsuarios() {
		return usuariosLogados;
	}

	public void setUsuarios(List<Usuario> usuariosLogados) {
		this.usuariosLogados = usuariosLogados;
	}

}
