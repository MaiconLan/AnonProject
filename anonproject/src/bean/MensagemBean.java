package bean;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.servlet.http.HttpSession;

import dao.MensagemDAO;
import model.Mensagem;
import model.Usuario;

@ManagedBean(name = "mensagemBean")
@ViewScoped
public class MensagemBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Usuario usuario;
	private Mensagem mensagem;
	private List<Mensagem> lista;
	private MensagemDAO dao;

	@PostConstruct
	public void postConstruct() {
		this.dao = new MensagemDAO();
		this.mensagem = new Mensagem();
		this.usuario = new Usuario();

		try {
			this.lista = dao.listarMensagem();
			FacesContext context = FacesContext.getCurrentInstance();
			HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
			usuario = (Usuario) session.getAttribute("usuarioLogado");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void atualizaMensagem() throws SQLException {
		boolean opt = dao.contaMensagem();
		
		if (opt)
			this.lista = dao.listarMensagem();
	}

	public void enviarMensagem() {
		try {
			if (this.mensagem.getIdMensagem() == null) {
				this.dao.enviaMensagem(mensagem, usuario);
				mensagem = new Mensagem();
				atualizaMensagem();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Mensagem getMensagem() {
		return mensagem;
	}

	public void setMensagem(Mensagem mensagem) {
		this.mensagem = mensagem;
	}

	public List<Mensagem> getLista() {
		return lista;
	}

	public void setLista(List<Mensagem> lista) {
		this.lista = lista;
	}

	public MensagemDAO getDao() {
		return dao;
	}

	public void setDao(MensagemDAO dao) {
		this.dao = dao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
