/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cinejunglaadmin.controlador;

import cinejunglaadmin.Hash;
import cinejunglaadmin.modelos.Modelo_admin_clientes;
import cinejunglaadmin.modelos.Modelo_admin_emp;
import cinejunglaadmin.modelos.Modelo_admin_funcion;
import cinejunglaadmin.modelos.Modelo_admin_prod;
import cinejunglaadmin.modelos.Modelo_admin_usuarios;
import cinejunglaadmin.modelos.Producto;
import cinejunglaadmin.modelos.Usuarios;
import cinejunglaadmin.modelos.clientes;
import cinejunglaadmin.modelos.empleados;
import cinejunglaadmin.vistas.Pane_admin;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author teban
 */
public class Controlador_admin {
    
    public Controlador_admin(Pane_admin panel_admin, Modelo_admin_prod model_admin, Producto prod,empleados emp,Modelo_admin_emp modelo_emp,Modelo_admin_usuarios modelo_user, Usuarios usr, Modelo_admin_clientes model_clientes,clientes cli, Modelo_admin_funcion model_fun){
        
        /*--------------------------Productos---------------------------------------*/
        panel_admin.setVisible(true);
        panel_admin.getBntRefrescarProd().addActionListener((e) -> {
            model_admin.Refrescar(panel_admin);
        });
        panel_admin.getBntRealizarProd().addActionListener((e) -> {
        if(panel_admin.getJrRegistarProd().isSelected()){
            prod.setID(Integer.parseInt(panel_admin.getTxt_idprod().getText().trim()));
            prod.setNombreString(panel_admin.getTxt_nombreprod().getText());
            prod.setPrecio(Integer.parseInt(panel_admin.getTxt_precioprod().getText().trim()));
           if(model_admin.Registar(panel_admin,prod)){
               JOptionPane.showMessageDialog(panel_admin, "Exito en el registro");
               reset_prod(panel_admin, model_admin, prod);
           } else {
               JOptionPane.showMessageDialog(panel_admin, "Fallo en el registro");
           }
        }else if(panel_admin.getJrActualizarProd().isSelected()){
            prod.setID(Integer.parseInt(panel_admin.getTxt_idprod().getText().trim()));
            prod.setNombreString(panel_admin.getTxt_nombreprod().getText());
            prod.setPrecio(Integer.parseInt(panel_admin.getTxt_precioprod().getText().trim()));
            if(model_admin.modificar(panel_admin,prod,panel_admin.getTxt_buscarProd().getText())){
               JOptionPane.showMessageDialog(panel_admin, "Exito en la Modificacion");
                reset_prod(panel_admin, model_admin, prod);
            }else{
                JOptionPane.showMessageDialog(panel_admin, "Fallo en el Modificacion");
            }
        }else if(panel_admin.getJrBorrarProd().isSelected()){
            prod.setID(Integer.parseInt(panel_admin.getTxt_idprod().getText().trim()));
            if(model_admin.Eliminar(panel_admin, prod)){
                JOptionPane.showMessageDialog(panel_admin, "Exito en la eliminacion");
                reset_prod(panel_admin, model_admin, prod);
            }else{
                 JOptionPane.showMessageDialog(panel_admin, "Fallo en la eliminacion");
            }
        }
        });
        panel_admin.getJtProductos().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
               model_admin.Transfer_data(panel_admin);
            }
        });
        panel_admin.getBntBuscarProd().addActionListener((e) -> {
            String filtro = "";
            filtro = panel_admin.getBox_filtroProd().getSelectedItem().toString();
            switch (filtro) {
                case "ID": filtro=filtro+" = "+panel_admin.getTxt_buscarProd().getText();;break;
                case "Nombre": filtro=filtro+" = "+"\""+panel_admin.getTxt_buscarProd().getText()+"\"";break;
                default:
                    throw new AssertionError();
            }
            if(model_admin.Buscar(panel_admin, filtro)){
               
            }else{
             
            }
            
        });
        /*--------------------------Productos---------------------------------------*/
        /*--------------------------Empleados---------------------------------------*/
         panel_admin.getBntGenCod().addActionListener((e) -> {
             
            emp.setNombre(panel_admin.getTxt_nombreemp().getText());
            emp.setApellido(panel_admin.getTxt_apellidoemp().getText());
            emp.setCedula(Integer.parseInt(panel_admin.getTxt_cedulaemp().getText().trim()));
            emp.setCargo(panel_admin.getTxt_cargoemp().getText());
            emp.setTelefono(panel_admin.getTxt_telefonoemp().getText().trim());
            emp.setFecha_inc(panel_admin.getTxt_fechaemp().getText());
            emp.setSalario(Integer.parseInt(panel_admin.getTxt_salarioemp().getText().trim()));
            emp.setMultiplex_ID(panel_admin.getBox_multiplexemp().getSelectedIndex()+1);
            Date date = new Date();
            emp.setCod_emp(modelo_emp.generar_cod(date,panel_admin.getBox_multiplexemp().getSelectedIndex()+1, emp.getCedula()));
            String NewCode = ""+emp.getCod_emp();
            panel_admin.getLb_cod().setText(NewCode);
        });
         
         
         panel_admin.getBntRealizaremp().addActionListener((e) -> {
        if(panel_admin.getRd_registraremp().isSelected()){
            emp.setCod_emp(Integer.parseInt(panel_admin.getLb_cod().getText()));
            emp.setCedula(Integer.parseInt(panel_admin.getTxt_cedulaemp().getText()));
            emp.setTelefono(panel_admin.getTxt_telefonoemp().getText());
            emp.setMultiplex_ID(panel_admin.getBox_multiplexemp().getSelectedIndex()+1);
            emp.setNombre(panel_admin.getTxt_nombreemp().getText());
            emp.setSalario(Integer.parseInt(panel_admin.getTxt_salarioemp().getText().trim()));
            emp.setApellido(panel_admin.getTxt_apellidoemp().getText());
            emp.setCargo(panel_admin.getTxt_cargoemp().getText());
            emp.setFecha_inc(panel_admin.getTxt_fechaemp().getText());
           if(modelo_emp.Registar(panel_admin,emp)){
               JOptionPane.showMessageDialog(panel_admin, "Exito en el registro");
               
               reset_emp(panel_admin, modelo_emp, emp);
           } else {
               JOptionPane.showMessageDialog(panel_admin, "Fallo en el registro");
           }
        }else if(panel_admin.getRb_actualizaremp().isSelected()){
            
            emp.setCedula(Integer.parseInt(panel_admin.getTxt_cedulaemp().getText()));
            emp.setTelefono(panel_admin.getTxt_telefonoemp().getText());
            emp.setMultiplex_ID(panel_admin.getBox_multiplexemp().getSelectedIndex()+1);
            emp.setNombre(panel_admin.getTxt_nombreemp().getText());
            emp.setApellido(panel_admin.getTxt_apellidoemp().getText());
            emp.setCargo(panel_admin.getTxt_cargoemp().getText());
            emp.setFecha_inc(panel_admin.getTxt_fechaemp().getText());
            emp.setSalario(Integer.parseInt(panel_admin.getTxt_salarioemp().getText()));
            
            if(modelo_emp.modificar(panel_admin,emp,panel_admin.getTxt_buscaremp().getText().trim())){
               JOptionPane.showMessageDialog(panel_admin, "Exito en la Modificacion");
               reset_emp(panel_admin, modelo_emp, emp);
            }else{
                JOptionPane.showMessageDialog(panel_admin, "Fallo en el Modificacion");
            }
        }else if(panel_admin.getRb_borraremp().isSelected()){
            emp.setCod_emp(Integer.parseInt(panel_admin.getLb_cod().getText()));
            if(modelo_emp.Eliminar(panel_admin, emp)){
                JOptionPane.showMessageDialog(panel_admin, "Exito en la eliminacion");
                reset_emp(panel_admin, modelo_emp, emp);
            }else{
                 JOptionPane.showMessageDialog(panel_admin, "Fallo en la eliminacion");
            }
        }
        });
         panel_admin.getJtEmpleados().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
               modelo_emp.Transfer_data(panel_admin);
            }
        });
         
         panel_admin.getBntRefrescaremp().addActionListener((e) -> {
             modelo_emp.Refrescar(panel_admin);
         });
         
         panel_admin.getBntBuscar().addActionListener((e) -> {
            String filtro = "";
            filtro = panel_admin.getBox_filtroemp().getSelectedItem().toString();
            switch (filtro) {
                case "Cod_emp": filtro=filtro+" = "+panel_admin.getTxt_buscaremp().getText();;break;
                case "Multiplex_ID": filtro=filtro+" = "+panel_admin.getTxt_buscaremp().getText();break;
                case "cedula": filtro=filtro+" = "+panel_admin.getTxt_buscaremp().getText();break;
                default:
                    throw new AssertionError();
            }
            if(modelo_emp.Buscar(panel_admin, filtro)){
                
            }
            
        });
      /*--------------------------Empleados---------------------------------------*/   
      /*--------------------------Usuarios---------------------------------------*/   
         panel_admin.getBntRefrescarusr().addActionListener((e) -> {
             modelo_user.Refrescar(panel_admin);
         });
         
         panel_admin.getBnt_Buscarusr().addActionListener((e) -> {
            String filtro = "";
            filtro = panel_admin.getBox_filtrousr().getSelectedItem().toString();
            switch (filtro) {
                case "usuario": filtro=filtro+" = "+panel_admin.getTxt_buscarusr().getText();;break;
                case "id_rango": filtro=filtro+" = "+panel_admin.getTxt_buscarusr().getText();break;
                default:
                    throw new AssertionError();
            }
             
            if(modelo_user.Buscar(panel_admin, filtro)){
               
            }else{
                JOptionPane.showMessageDialog(panel_admin, "No se encontro registro");
            }
             
             
         });
        panel_admin.getJtUsuarios().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
               modelo_user.Transfer_data(panel_admin);
            }
        });
        panel_admin.getBntRegistarusr().addActionListener((e) -> {
            
            modelo_user.Registar();
            reset_usr(panel_admin, modelo_user, usr);
        });
        
        panel_admin.getBntActualizarusr().addActionListener((e) -> {
            String pass = ""+panel_admin.getTxt_password().getText().trim();
            String newpass = Hash.sha1(pass);
            usr.setPassword(newpass);
            usr.setId_tipo(panel_admin.getBox_tipousr().getSelectedIndex());
            if(modelo_user.modificar(panel_admin, usr, panel_admin.getTxt_nombreurs().getText().trim())){
                JOptionPane.showMessageDialog(panel_admin, "Registro Modificado con exito");
                reset_usr(panel_admin, modelo_user, usr);
            }
        });
        panel_admin.getBntBorrarusr().addActionListener((e) -> {
            usr.setUsuario(Integer.parseInt(panel_admin.getTxt_nombreurs().getText().trim()));
           if(modelo_user.Eliminar(panel_admin, usr)){
               JOptionPane.showMessageDialog(panel_admin, "Registro eliminado con exito");
               reset_usr(panel_admin, modelo_user, usr);
           }
            
        });
        /*--------------------------Usuarios---------------------------------------*/  
        /*--------------------------Clientes---------------------------------------*/  
        panel_admin.getBntRefrescarcli().addActionListener((e) -> {
             model_clientes.Refrescar(panel_admin);
         });
        
         panel_admin.getBntBuscarcli().addActionListener((e) -> {
            String filtro = "";
            filtro = panel_admin.getBox_filtroCli().getSelectedItem().toString();
            switch (filtro) {
                case "ID": filtro=filtro+" = "+panel_admin.getTxt_buscarcli().getText();;break;
                case "Nombre": filtro=filtro+" = "+"\""+panel_admin.getTxt_buscarcli().getText()+"\"";break;
                default:
                    throw new AssertionError();
            }
            if(model_clientes.Buscar(panel_admin, filtro)){}else{
                JOptionPane.showMessageDialog(panel_admin, "No se encontro registro");
            }
         });
         
         
          panel_admin.getjButton1().addActionListener((e) -> {
        if(panel_admin.getRb_registarcli().isSelected()){
            cli.setNombre(panel_admin.getTxt_nombrecli().getText());
            cli.setApellido(panel_admin.getTxt_apellidocli().getText());
            cli.setEmail(panel_admin.getTxt_emailcli().getText());
            cli.setPuntos_acum(0);
           if(model_clientes.Registar(cli)){
               JOptionPane.showMessageDialog(panel_admin, "Exito en el registro");
               reset_cli(panel_admin, model_clientes, cli);
           } else {
               JOptionPane.showMessageDialog(panel_admin, "Fallo en el registro");
           }
        }else if(panel_admin.getRb_actualizarcli().isSelected()){
            cli.setNombre(panel_admin.getTxt_nombrecli().getText());
            cli.setApellido(panel_admin.getTxt_apellidocli().getText());
            cli.setEmail(panel_admin.getTxt_emailcli().getText());
            cli.setPuntos_acum(0);
            if(model_clientes.modificar(panel_admin,cli,panel_admin.getTxt_buscarcli().getText())){
               JOptionPane.showMessageDialog(panel_admin, "Exito en la Modificacion");
                reset_cli(panel_admin, model_clientes, cli);
            }else{
                JOptionPane.showMessageDialog(panel_admin, "Fallo en el Modificacion");
            }
        }else if(panel_admin.getRb_borrarcli().isSelected()){
            cli.setID(Integer.parseInt(panel_admin.getLb_resID().getText().trim()));
            if(model_clientes.Eliminar(panel_admin, cli)){
                JOptionPane.showMessageDialog(panel_admin, "Exito en la eliminacion");
                reset_cli(panel_admin, model_clientes, cli);
            }else{
                 JOptionPane.showMessageDialog(panel_admin, "Fallo en la eliminacion");
            }
        }
        });
          
         panel_admin.getJtClientes().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
               model_clientes.Transfer_data(panel_admin);
            }
        });
         /*--------------------------Clientes---------------------------------------*/  
         /*--------------------------Funciones---------------------------------------*/ 
         panel_admin.getBntRefrescarfun().addActionListener((e) -> {
             model_fun.Refrescar(panel_admin);
         });
         panel_admin.getJtFunciones().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
               model_fun.Transfer_data(panel_admin);
            }
        });
         panel_admin.getBntBuscarFun().addActionListener((e) -> {
            String filtro = "";
            filtro = panel_admin.getTxt_buscarFun().getText();
            
            if(model_fun.Buscar(panel_admin, filtro)){}else{
                JOptionPane.showMessageDialog(panel_admin, "No se encontro registro");
            }
         });
         panel_admin.getBntRealizarfun().addActionListener((e) -> {
        if(panel_admin.getRb_registarcli1().isSelected()){
            
           if(model_fun.Registar(panel_admin)){
               model_fun.Refrescar(panel_admin);
               JOptionPane.showMessageDialog(panel_admin, "Exito en el registro");
               
           } else {
               JOptionPane.showMessageDialog(panel_admin, "Fallo en el registro");
               reset_fun(panel_admin, model_fun);
           }
        }else if(panel_admin.getRb_actualizarcli1().isSelected()){
            
            if(model_fun.modificar(panel_admin,panel_admin.getLb_IDfun().getText())){
                model_fun.Refrescar(panel_admin);
               JOptionPane.showMessageDialog(panel_admin, "Exito en la Modificacion");
                reset_fun(panel_admin, model_fun);
            }else{
                JOptionPane.showMessageDialog(panel_admin, "Fallo en el Modificacion");
            }
        }else if(panel_admin.getRb_borrarcli1().isSelected()){
            
            if(model_fun.Eliminar(panel_admin)){
                model_fun.Refrescar(panel_admin);
                JOptionPane.showMessageDialog(panel_admin, "Exito en la eliminacion");
                reset_fun(panel_admin, model_fun);
            }else{
                 JOptionPane.showMessageDialog(panel_admin, "Fallo en la eliminacion");
            }
        }
        });
        /*--------------------------Funciones---------------------------------------*/ 
    }
     
     public void reset_fun(Pane_admin panel_admin, Modelo_admin_funcion model_fun){
        model_fun.Refrescar(panel_admin);
        model_fun.limpiar(panel_admin);
        
    }  
    
    
    
    public void reset_cli(Pane_admin panel_admin, Modelo_admin_clientes modelo_cli, clientes cli){
        modelo_cli.Refrescar(panel_admin);
        modelo_cli.limpiar(panel_admin);
        cli.reset();
    }  
    

    public void reset_usr(Pane_admin panel_admin, Modelo_admin_usuarios modelo_user, Usuarios usr){
    modelo_user.Refrescar(panel_admin);
    modelo_user.limpiar(panel_admin);
    usr.reset();
    }
            
             
    public void reset_prod(Pane_admin panel_admin, Modelo_admin_prod model_admin, Producto prod){
    model_admin.Refrescar(panel_admin);
    model_admin.limpiar(panel_admin);
    prod.reset();
    }
    
    public void reset_emp(Pane_admin panel_admin, Modelo_admin_emp model_emp, empleados emp){
    model_emp.Refrescar(panel_admin);
    model_emp.limpiar(panel_admin);
    emp.reset();
    }
    
    
}
