/*
 * Decompiled with CFR 0_118.
 * 
 * Could not load the following classes:
 *  dao.UsuarioDAO
 *  javax.enterprise.context.SessionScoped
 *  javax.faces.application.FacesMessage
 *  javax.faces.context.ExternalContext
 *  javax.faces.context.FacesContext
 *  javax.inject.Named
 *  javax.servlet.http.HttpSession
 *  model.Usuario
 */
package bean;

import dao.UsuarioDAO;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import model.Usuario;

@Named(value="loginMb")
@SessionScoped
public class LoginManagedBean
implements Serializable {
    private static final long serialVersionUID = 1;
    private UsuarioDAO dao;
    private Usuario usuarioLogado;
    private String user;
    private String pass;
    private boolean checkStatus;
    private String cor;
    private List<Usuario> usuariosLogados;
    private boolean logado = false;
    private boolean admin = false;

    @PostConstruct
    public void postConstruct() throws SQLException {
        this.usuariosLogados = new ArrayList<Usuario>();
    }

    public void statusUsuario() throws SQLException {
        if (this.checkStatus) {
            this.dao.alteraStatusUsuario(this.usuarioLogado.getLogin(), "Online");
        } else {
            this.dao.alteraStatusUsuario(this.usuarioLogado.getLogin(), "Ausente");
        }
    }

    public void alteraNome() throws SQLException {
        this.dao.alteraNomeUsuario(this.usuarioLogado.getLogin(), this.usuarioLogado.getNome());
    }

    public void isValido() throws SQLException, IOException {
        this.dao = new UsuarioDAO();
        this.usuarioLogado = new Usuario();
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            this.logado = this.dao.isValido(this.user, this.pass);
            if (this.logado) {
                this.dao.alteraStatusUsuario(this.user, "Online");
                this.usuarioLogado = this.dao.informacaoUsuario(this.user);
                HttpSession session = (HttpSession)context.getExternalContext().getSession(false);
                session.setAttribute("usuarioLogado", (Object)this.usuarioLogado);
                context.addMessage(null, new FacesMessage("Login efetuado com sucesso!", "Login efetuado com sucesso."));
                FacesContext.getCurrentInstance().getExternalContext().redirect("mensagem.xhtml");
            } else {
                this.dao.alteraStatusUsuario(this.user, "Offline");
                context.addMessage(null, new FacesMessage("Login e/ou senha incorretos!", "Login e/ou senha incorretos."));
            }
        }
        catch (Exception e) {
            context.addMessage(null, new FacesMessage("N\u00e3o foi poss\u00edvel realizar o Login!", "N\u00e3o foi poss\u00edvel realizar o Login." + e));
            e.printStackTrace();
        }
    }

    public void atualizaUsuarios() throws SQLException {
        this.usuariosLogados = this.dao.usuariosLogados();
    }

    public void logout() throws IOException, SQLException {
        this.dao.alteraStatusUsuario(this.user, "Offline");
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession)fc.getExternalContext().getSession(false);
        session.invalidate();
        fc.getExternalContext().redirect("login.xhtml");
    }

    public boolean isLogado() {
        return this.logado;
    }

    public boolean isAdmin() {
        return this.admin;
    }

    public Usuario getUsuario() {
        return this.usuarioLogado;
    }

    public void setUsuarioDAO(UsuarioDAO dao) {
        this.dao = dao;
    }

    public String getUser() {
        return this.user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return this.pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public UsuarioDAO getDao() {
        return this.dao;
    }

    public void setDao(UsuarioDAO dao) {
        this.dao = dao;
    }

    public List<Usuario> getUsuariosLogados() {
        return this.usuariosLogados;
    }

    public void setUsuariosLogados(List<Usuario> usuariosLogados) {
        this.usuariosLogados = usuariosLogados;
    }

    public String getCor() {
        return this.cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public boolean isCheckStatus() {
        return this.checkStatus;
    }

    public void setCheckStatus(boolean checkStatus) {
        this.checkStatus = checkStatus;
    }
}
