/*
 * Decompiled with CFR 0_118.
 */
package model;

public class Usuario {
    private Long idUsuario;
    private String nome;
    private String login;
    private String senha;
    private String status;
    private String cor;

    public Usuario() {
    }

    public Usuario(Long idUsuario, String login, String senha, String status, String cor, String nome) {
        this.idUsuario = idUsuario;
        this.login = login;
        this.senha = senha;
        this.status = status;
        this.cor = cor;
        this.nome = nome;
    }

    public Long getIdUsuario() {
        return this.idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getLogin() {
        return this.login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return this.senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCor() {
        return this.cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
