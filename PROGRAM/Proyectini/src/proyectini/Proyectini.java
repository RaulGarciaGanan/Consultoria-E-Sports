/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectini;

import MisVentanas.*;

/**
 *
 * @author Aitor Alday
 */
public class Proyectini {

    public static DLogin dL;
    public static VPrincipal vP;
    public static void main(String[] args) {
        dL = new DLogin(vP, true);
        dL.setVisible(true);
        
    }
    
}
