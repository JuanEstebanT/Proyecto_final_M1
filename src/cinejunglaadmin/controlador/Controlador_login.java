/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cinejunglaadmin.controlador;

import cinejunglaadmin.Hash;
import cinejunglaadmin.modelos.Modelo_admin_clientes;
import cinejunglaadmin.modelos.Modelo_admin_emp;
import cinejunglaadmin.modelos.Modelo_admin_funcion;
import cinejunglaadmin.modelos.SqlUsuarios;
import cinejunglaadmin.modelos.Usuarios;
import cinejunglaadmin.modelos.Modelo_admin_prod;
import cinejunglaadmin.modelos.Modelo_admin_usuarios;
import cinejunglaadmin.modelos.Modelo_confiteria;
import cinejunglaadmin.modelos.Modelo_taquilla;
import cinejunglaadmin.modelos.Producto;
import cinejunglaadmin.modelos.clientes;
import cinejunglaadmin.modelos.empleados;
import cinejunglaadmin.vistas.Confiteria;
import cinejunglaadmin.vistas.Login;
import cinejunglaadmin.vistas.Pane_admin;
import cinejunglaadmin.vistas.Taquilla;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author teban
 */
public class Controlador_login {
    public Controlador_login(Pane_admin panel_admin,Taquilla taquilla,Login login,Modelo_admin_prod model_admin, Producto prod,Modelo_admin_emp modelo_emp, empleados emp,Modelo_admin_usuarios model_user,Usuarios usr,Modelo_admin_clientes model_clientes,clientes cli,Confiteria Pane_confi,Modelo_confiteria model_conf,Modelo_admin_funcion model_fun,Modelo_taquilla model_taquilla){
    login.setVisible(true); 
  
    login.getIngresar().addActionListener((e) -> {
           SqlUsuarios modsql = new SqlUsuarios();
           Usuarios mod = Usuarios.getInstance();
           String pass = new String(login.getTxt_password().getPassword());
           
           if(!login.getTxt_usuario().getText().equals("") && !pass.equals("")){
               Date date = new Date();
               DateFormat fecha = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
               String newpass = Hash.sha1(pass);
               mod.setUsuario(Integer.parseInt(login.getTxt_usuario().getText()));
               mod.setPassword(newpass);
               mod.setLast_session(fecha.format(date));
               if(modsql.login(mod)){
                   
                   JOptionPane.showMessageDialog(null, "Bienvenido");
                   login.dispose();
                   switch(mod.getId_tipo()){
                       case 0: Controlador_admin crtl = new Controlador_admin(panel_admin,model_admin,prod,emp,modelo_emp,model_user,usr,model_clientes,cli,model_fun); break;
                       case 1: Controlador_taquilla crtltaqui = new Controlador_taquilla(model_taquilla, taquilla);break;
                       case 2: Control_Confiteria crtlcon = new Control_Confiteria(Pane_confi,model_conf,usr);break;
                       case 3: break;
                       case 4: break;
                       
                   }
               }else{
                  JOptionPane.showMessageDialog(null, "ERROR DATOS");
               }
               
               
           }else{
            JOptionPane.showMessageDialog(null, " DATOS Vacios");
           }
           
        });
    
    }
    
}
