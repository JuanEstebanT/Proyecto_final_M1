/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cinejunglaadmin.controlador;

import cinejunglaadmin.Hash;
import cinejunglaadmin.modelos.Modelo_admin_clientes;
import cinejunglaadmin.modelos.Modelo_admin_emp;
import cinejunglaadmin.modelos.SqlUsuarios;
import cinejunglaadmin.modelos.Usuarios;
import cinejunglaadmin.modelos.Modelo_admin_prod;
import cinejunglaadmin.modelos.Modelo_admin_usuarios;
import cinejunglaadmin.modelos.Modelo_confiteria;
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
public class Controlador_principal {
        Login login = new Login();
        Producto prod = Producto.getInstance();
        empleados emp = empleados.getInstance();
        clientes cli = clientes.getInstance();
        Usuarios usr = Usuarios.getInstance();
        Pane_admin panel_admin = new Pane_admin();
        Confiteria panelConfiteria = new Confiteria();
        Modelo_admin_emp modelo_admin_emp = new Modelo_admin_emp();
        Modelo_admin_prod modelo_prod = new Modelo_admin_prod();
        Modelo_admin_usuarios model_usr = new Modelo_admin_usuarios();
        Modelo_admin_clientes model_clientes = new Modelo_admin_clientes();
        Modelo_confiteria modelo_confiteria = new Modelo_confiteria();
        Taquilla taquilla = new Taquilla();
        Controlador_login crlt = new Controlador_login(panel_admin,taquilla, login,modelo_prod,prod,modelo_admin_emp,emp,model_usr,usr,model_clientes,cli,panelConfiteria,modelo_confiteria);
}
