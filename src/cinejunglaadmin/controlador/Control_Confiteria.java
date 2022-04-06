/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cinejunglaadmin.controlador;

import cinejunglaadmin.modelos.Modelo_confiteria;
import cinejunglaadmin.modelos.Usuarios;
import cinejunglaadmin.modelos.clientes;
import cinejunglaadmin.vistas.Confiteria;
import javax.swing.JOptionPane;

/**
 *
 * @author teban
 */
public class Control_Confiteria {
    public Control_Confiteria(Confiteria Pane_confi,Modelo_confiteria model_confi,Usuarios usr){
        Pane_confi.setVisible(true);
        Pane_confi.getLb_vendedor().setText(""+usr.getUsuario());
        Pane_confi.getBnt_calcular().addActionListener((e) -> {
           
           int cont=0;
           
           if(Pane_confi.getCheckNachos().isSelected()){
           cont++;
           }
           if(Pane_confi.getCheckChocolatina().isSelected()){
           cont++;
           }
           if(Pane_confi.getCheckGaseosa().isSelected()){
           cont++;
           }
           if(Pane_confi.getCheckPalomitas().isSelected()){
           cont++;
           }
           if(Pane_confi.getCheckSandwichs().isSelected()){
           cont++;
           }
           if(Pane_confi.getCheckGomitas().isSelected()){
           cont++;
           }
           System.out.println(cont);
           String[] productos = new String[cont];
           int[] cant = new int[cont];
           int i = 0;
           if(Pane_confi.getCheckNachos().isSelected()){
           productos[i]="nachos";
           cant[i]= Integer.parseInt(Pane_confi.getSp_Nach().getValue().toString());
           i++;
           }
           if(Pane_confi.getCheckChocolatina().isSelected()){
           productos[i]="Chocolatina";
           cant[i]= Integer.parseInt(Pane_confi.getSp_choc().getValue().toString());
           i++;
           }
           if(Pane_confi.getCheckGaseosa().isSelected()){
           productos[i]="Gomitas";
           cant[i]= Integer.parseInt(Pane_confi.getSp_gas().getValue().toString());
           i++;
           }
           if(Pane_confi.getCheckPalomitas().isSelected()){
           productos[i]="Palomitas";
           cant[i]= Integer.parseInt(Pane_confi.getSp_Pal().getValue().toString());
           i++;
           }
           if(Pane_confi.getCheckSandwichs().isSelected()){
           productos[i]="sandwich";
           cant[i]= Integer.parseInt(Pane_confi.getSp_sandwich().getValue().toString());
           i++;
           }
           if(Pane_confi.getCheckGomitas().isSelected()){
           productos[i]="sandwich";
           cant[i]= Integer.parseInt(Pane_confi.getSp_gomi().getValue().toString());
           i++;
           }
           int Num_fact = model_confi.gen_numero();
           int preciototal = 0;
           
           for(int x=0;x<cont;x++){
              preciototal = preciototal+model_confi.cotizacion(productos[x], cant[x]);
           }
           Pane_confi.getLb_total().setText(""+preciototal);
           
            
        });
        
        Pane_confi.getBnt_pagar().addActionListener((e) -> {
            
            int cont=0;
           
           if(Pane_confi.getCheckNachos().isSelected()){
           cont++;
           }
           if(Pane_confi.getCheckChocolatina().isSelected()){
           cont++;
           }
           if(Pane_confi.getCheckGaseosa().isSelected()){
           cont++;
           }
           if(Pane_confi.getCheckPalomitas().isSelected()){
           cont++;
           }
           if(Pane_confi.getCheckSandwichs().isSelected()){
           cont++;
           }
           if(Pane_confi.getCheckGomitas().isSelected()){
           cont++;
           }
           System.out.println(cont);
           String[] productos = new String[cont];
           int[] cant = new int[cont];
           int i = 0;
           if(Pane_confi.getCheckNachos().isSelected()){
           productos[i]="nachos";
           cant[i]= Integer.parseInt(Pane_confi.getSp_Nach().getValue().toString());
           i++;
           }
           if(Pane_confi.getCheckChocolatina().isSelected()){
           productos[i]="Chocolatina";
           cant[i]= Integer.parseInt(Pane_confi.getSp_choc().getValue().toString());
           i++;
           }
           if(Pane_confi.getCheckGaseosa().isSelected()){
           productos[i]="Gomitas";
           cant[i]= Integer.parseInt(Pane_confi.getSp_gas().getValue().toString());
           i++;
           }
           if(Pane_confi.getCheckPalomitas().isSelected()){
           productos[i]="Palomitas";
           cant[i]= Integer.parseInt(Pane_confi.getSp_Pal().getValue().toString());
           i++;
           }
           if(Pane_confi.getCheckSandwichs().isSelected()){
           productos[i]="sandwich";
           cant[i]= Integer.parseInt(Pane_confi.getSp_sandwich().getValue().toString());
           i++;
           }
           if(Pane_confi.getCheckGomitas().isSelected()){
           productos[i]="sandwich";
           cant[i]= Integer.parseInt(Pane_confi.getSp_gomi().getValue().toString());
           i++;
           }
           int Num_fact = model_confi.gen_numero();
           
           
           for(int x=0;x<cont;x++){
             if(model_confi.facturar(productos[x], cant[x], Num_fact,Integer.parseInt(Pane_confi.getTxt_codcli().getText().trim()), Integer.parseInt(Pane_confi.getLb_vendedor().getText()))){
                 System.out.println("Facrura Generada");
             }
           }
            
            
            
        });
        
    
    }
    
}
