/*
 * Decompiled with CFR 0_118.
 * 
 * Could not load the following classes:
 *  dao.MensagemDAO
 *  javax.faces.bean.ManagedBean
 *  javax.faces.context.ExternalContext
 *  javax.faces.context.FacesContext
 *  javax.faces.view.ViewScoped
 *  javax.servlet.http.HttpSession
 *  model.Mensagem
 *  model.Usuario
 */
package bean;

import dao.MensagemDAO;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.servlet.http.HttpSession;
import model.Mensagem;
import model.Usuario;

@ManagedBean(name="mensagemBean")
@ViewScoped
public class MensagemBean
implements Serializable {
    private static final long serialVersionUID = 1;
    private Usuario usuario;
    private Mensagem mensagem;
    private List<Mensagem> lista;
    private MensagemDAO dao;
    private String cor;

    @PostConstruct
    public void postConstruct() {
        this.dao = new MensagemDAO();
        this.mensagem = new Mensagem();
        this.usuario = new Usuario();
        try {
            this.lista = this.dao.listarMensagem();
            FacesContext context = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession)context.getExternalContext().getSession(false);
            this.usuario = (Usuario)session.getAttribute("usuarioLogado");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizaMensagem() throws SQLException {
        boolean opt = this.dao.contaMensagem();
        if (opt) {
            this.lista = this.dao.listarMensagem();
        }
    }

    public void enviarMensagem() {
        try {
            if (this.mensagem.getIdMensagem() == null) {
                this.dao.enviaMensagem(this.mensagem, this.usuario);
                this.mensagem = new Mensagem();
                this.atualizaMensagem();
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
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
}
