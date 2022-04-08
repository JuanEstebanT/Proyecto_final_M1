/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cinejunglaadmin.controlador;

import cinejunglaadmin.modelos.Modelo_taquilla;
import cinejunglaadmin.vistas.Taquilla;

/**
 *
 * @author teban
 */
public class Controlador_taquilla {
    public Controlador_taquilla(Modelo_taquilla model_taquilla, Taquilla taquilla){
        taquilla.setVisible(true);
        taquilla.getBnt_ver().addActionListener((e) -> {
            model_taquilla.verificarsilla(taquilla);
        });
        
        taquilla.getBox_pel().addItemListener((e) -> {
            
            model_taquilla.actualizarFun(taquilla);
            
            
        });
        
        taquilla.getBnt_Pago().addActionListener((e) -> {
            
            model_taquilla.pagar_silla(taquilla);
        });
        
        
    }
}
