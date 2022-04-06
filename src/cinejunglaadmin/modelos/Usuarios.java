/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cinejunglaadmin.modelos;

/**
 *
 * @author teban
 */
public class Usuarios implements Restaurar{
    
    private static Usuarios usr;
    private Usuarios(int usuario,String password,String last_session,int id_rango,String cargo){
        this.usuario = usuario;
        this.password = password;
        this.last_session = last_session;
        this.id_rango = id_rango;
        this.cargo = cargo;
    }
    
    private int usuario;
    private String password;
    private String last_session;
    private int id_rango;
    private String cargo;
    
    public static Usuarios getInstance(){
        if(usr==null){
            usr= new Usuarios(0, "", "", 0, "");
        }
    return usr;
    }
    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public int getUsuario() {
        return usuario;
    }

    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLast_session() {
        return last_session;
    }

    public void setLast_session(String last_session) {
        this.last_session = last_session;
    }

    public int getId_tipo() {
        return id_rango;
    }

    public void setId_tipo(int id_tipo) {
        this.id_rango = id_tipo;
    }
    
     public void reset(){
        setCargo("");
        setId_tipo(0);
        setPassword("");
        setLast_session("");
        setPassword("");
    }
}
