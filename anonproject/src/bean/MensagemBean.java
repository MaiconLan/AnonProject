package bean;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
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
	private static final long serialVersionUID = 1;
	private Usuario usuario;
	private Usuario server;
	private Mensagem mensagem;
	private List<Mensagem> lista;
	private MensagemDAO dao;
	private String cor;
	private boolean sound;

	@PostConstruct
	public void postConstruct() {
		this.dao = new MensagemDAO();
		this.mensagem = new Mensagem();
		this.usuario = new Usuario();
		this.server = new Usuario();
		this.server.setNome("Server");

		try {
			this.lista = this.dao.listarMensagem();
			FacesContext context = FacesContext.getCurrentInstance();
			HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
			this.usuario = (Usuario) session.getAttribute("usuarioLogado");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void atualizaMensagem() throws SQLException {
		boolean opt = this.dao.contaMensagem();
		if (opt) {
			this.lista = this.dao.listarMensagem();
			sound = true;
			// Toolkit.getDefaultToolkit().beep();
		} else {
			sound = false;
		}
	}

	public void enviarMensagem() throws SQLException {
		if (this.mensagem.getIdMensagem() == null) {
			comando();
		}
	}

	public void comando() throws SQLException {
		String comandos = "Comandos disponíveis são /comandos /clean /easteregg e /say";
		String ee = usuario.getNome() + " é " + easterEgg();

		if (this.mensagem.getMensagem().equalsIgnoreCase("/clean")) {
			this.dao.comandoClean();
			this.mensagem.setMensagem("Mensagens limpas por " + usuario.getNome());
			this.dao.enviaMensagem(this.mensagem, server);
		} else if (this.mensagem.getMensagem().equalsIgnoreCase("/easteregg")) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Easter Egg", ee));
		} else if (this.mensagem.getMensagem().toLowerCase().startsWith("/say ")) {
			this.mensagem.setMensagem(this.mensagem.getMensagem().substring(5));
			this.dao.enviaMensagem(this.mensagem, server);
		} else if (this.mensagem.getMensagem().toLowerCase().equalsIgnoreCase("/comandos")) {
			this.mensagem.setMensagem(comandos);
			this.dao.enviaMensagem(this.mensagem, server);
		} else {
			this.dao.enviaMensagem(this.mensagem, this.usuario);
		}
		this.mensagem = new Mensagem();
		this.lista = this.dao.listarMensagem();
	}

	public String easterEgg() {
		String[] str = new String[9];
		Random gerador = new Random();
		int ee = gerador.nextInt(9);

		str[0] = "Trabalhador";
		str[1] = "Honesto";
		str[2] = "Importante";
		str[3] = "Parceiro";
		str[4] = "Proativo";
		str[5] = "Esforçado";
		str[6] = "Inteligente";
		str[7] = "Amigo do Usuário";
		str[8] = "Pau pra toda obra";

		return str[ee];
	}

	public void onIdle() {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_WARN, "No activity.", "What are you doing over there?"));
	}

	public void onActive() {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Welcome Back", "Well, that's a long coffee break!"));
	}

	public Mensagem getMensagem() {
		return this.mensagem;
	}

	public void setMensagem(Mensagem mensagem) {
		this.mensagem = mensagem;
	}

	public List<Mensagem> getLista() {
		return this.lista;
	}

	public void setLista(List<Mensagem> lista) {
		this.lista = lista;
	}

	public MensagemDAO getDao() {
		return this.dao;
	}

	public void setDao(MensagemDAO dao) {
		this.dao = dao;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getCor() {
		return this.cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public boolean isSound() {
		return sound;
	}

	public void setSound(boolean sound) {
		this.sound = sound;
	}

	public Usuario getServer() {
		return server;
	}

	public void setServer(Usuario server) {
		this.server = server;
	}
}
