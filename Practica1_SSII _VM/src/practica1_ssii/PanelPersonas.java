package practica1_ssii;
import javax.swing.*;
import java.awt.*;

public class PanelPersonas extends JPanel {
    private casilla[] []casillas;
    private boolean personaInside;
    public PanelPersonas(int filas, int columnas,boolean persona) {
        this.casillas=new casilla[filas][columnas];
        this.personaInside=persona;
        // Usar GridLayout para organizar las cajas en filas y columnas
        setLayout(new GridLayout(filas, columnas, 2, 2));  // 10 px de espacio entre filas y columnas
        
        // Añadir las cajas
        int alturaCaja = 300 / (filas+columnas); // Suponiendo que la altura total del panel es 300
        
        for (int i = 0; i < filas; i++) {
            for(int j=0;j<columnas;j++){
            casilla caja = new casilla("C:\\Users\\aaron\\OneDrive - Universitat de les Illes Balears\\Documents\\NetBeansProjects\\Practica1_SSII\\src\\practica1_ssii\\images\\pared.jpg",this.personaInside);
            caja.setBackground(Color.PINK);  // Cambiar el color de la caja
            caja.setBorder(BorderFactory.createLineBorder(Color.BLACK));  // Borde para distinguir
            caja.setPreferredSize(new Dimension(alturaCaja,alturaCaja));
            add(caja);  // Añadir cada caja al panel
            this.casillas[i][j]=caja;
            }
        }
    }
    public void AñadirPersona(int a){
        boolean find=false;
        for (int i = 0; i < this.casillas[0].length && !find; i++) {
            if (!this.casillas[a][i].isPersona()) {
                this.casillas[a][i].AddPersona();
                this.casillas[a][i].revalidate();
                this.casillas[a][i].repaint();
                find = true; // Aquí encuentra a una persona y cambia el estado de find
            }
        }
    }
    public void EliminarPersona(int a){
        boolean find=false;
        for (int i = 0; i < this.casillas[0].length&&!find; i++) {
            //Eliminamos a todas las personas de una fila
            if ((this.casillas[a][i].isPersona())) {
                if((this.casillas[a][i].isPersona()&&(i==this.casillas[0].length-1))){
                    find=true;
                }else if((this.casillas[a][i].isPersona()&&!this.casillas[a][i+1].isPersona())){
                    find=true;
                }
                this.casillas[a][i].DeletePersona();
                this.casillas[a][i].revalidate();
                this.casillas[a][i].repaint();
            }
        }
    }
    

}
