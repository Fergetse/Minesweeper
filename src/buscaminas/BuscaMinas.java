package buscaminas;

import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.UIManager;


public class BuscaMinas {
    public static void main(String[] args) {
        try{
            UIManager.setLookAndFeel(new FlatLightLaf());
        }catch(Exception e){
            e.printStackTrace();
        }
        new Tablero().setVisible(true);
    }
}
