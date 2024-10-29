package practica1_ssii;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InterfazAscensor extends JFrame {
    private int numPisos = 5;
    private int numPersonas = 3;
    private PanelContenido panelIzquierdo;
    public JPanel panelDerecho;
    private Operaciones op;

    public InterfazAscensor() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 600); // Tamaño de la ventana
        this.setLayout(new BorderLayout());

        // Crear el panel izquierdo (ocupará el 75% del espacio)
        panelIzquierdo = new PanelContenido(
                "C:\\Users\\aaron\\OneDrive - Universitat de les Illes Balears\\Documents\\NetBeansProjects\\Practica1_SSII\\src\\practica1_ssii\\images\\fondo_ciudad.png",
                this.numPisos, this.numPersonas);
        panelIzquierdo.setBackground(Color.LIGHT_GRAY); // Solo para hacer visible el panel
        panelIzquierdo.setPreferredSize(new Dimension(600, 600)); // Define un tamaño inicial

        // Crear el panel derecho para las opciones (menú de 3 botones)
        panelDerecho = new JPanel();
        panelDerecho.setBackground(Color.DARK_GRAY); // Solo para hacer visible el panel
        panelDerecho.setLayout(new BoxLayout(panelDerecho, BoxLayout.Y_AXIS)); // Disposición vertical de las opciones
        panelDerecho.setPreferredSize(new Dimension(200, 600)); // Tamaño para el panel derecho

        ImageIcon imagenIcono = new ImageIcon(
                "C:\\Users\\aaron\\OneDrive - Universitat de les Illes Balears\\Documents\\NetBeansProjects\\Practica1_SSII\\src\\practica1_ssii\\images\\imagen_logo_ascensor.png"); // Cambia
                                                                                                                                                                                      // por
                                                                                                                                                                                      // la
                                                                                                                                                                                      // ruta
                                                                                                                                                                                      // de
                                                                                                                                                                                      // tu
                                                                                                                                                                                      // imagen
        Image imagenEscalada = imagenIcono.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH); // Redimensionar
        ImageIcon imagenRedimensionada = new ImageIcon(imagenEscalada);

        // Crear un JLabel para contener la imagen
        JLabel etiquetaImagen = new JLabel(imagenRedimensionada);
        etiquetaImagen.setAlignmentX(Component.CENTER_ALIGNMENT); // Centrar la imagen horizontalmente
        panelDerecho.add(etiquetaImagen);

        // Espacio debajo de la imagen
        panelDerecho.add(Box.createRigidArea(new Dimension(0, 20)));
        // Centrar botones verticalmente usando glue
        panelDerecho.add(Box.createVerticalGlue());
        op = new Operaciones(numPisos, this);
        // Crear los botones
        JButton opcion1 = new JButton("Iniciar");
        JButton opcion2 = new JButton("Seleccionar cantidad de pisos");
        JButton opcion3 = new JButton("Capacidad personas por pasillos");

        // Añadir ActionListeners a los botones para manejar eventos de clic
        opcion1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Botón Opción 1 presionado");
                op.execute();
                panelDerecho.repaint();
            }

        });

        opcion2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Mostrar cuadro emergente para ingresar el número
                String input = JOptionPane.showInputDialog(null, "Introduce un número:");

                try {
                    // Convertir el valor ingresado a un entero
                    if (input != null) {
                        numPisos = Integer.parseInt(input);
                        JOptionPane.showMessageDialog(null, "Número guardado: " + numPisos);
                        InterfazAscensor.this.panelIzquierdo.ChangeDimension(numPisos);
                        InterfazAscensor.this.op = new Operaciones(numPisos, InterfazAscensor.this);

                    }
                } catch (NumberFormatException ex) {
                    // Mostrar un mensaje de error si el valor no es un número válido
                    JOptionPane.showMessageDialog(null, "Por favor, introduce un número válido.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        opcion3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog(null, "Introduce un número de personas:");

                try {
                    // Convertir el valor ingresado a un entero
                    if (input != null) {
                        numPersonas = Integer.parseInt(input);
                        JOptionPane.showMessageDialog(null, "Número guardado: " + numPersonas);
                        InterfazAscensor.this.panelIzquierdo.ChangePersonDim(numPersonas);
                    }
                } catch (NumberFormatException ex) {
                    // Mostrar un mensaje de error si el valor no es un número válido
                    JOptionPane.showMessageDialog(null, "Por favor, introduce un número válido.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        // Hacer los botones más grandes
        Dimension buttonSize = new Dimension(150, 35); // Tamaño más grande para los botones
        opcion1.setPreferredSize(buttonSize);
        opcion2.setPreferredSize(buttonSize);
        opcion3.setPreferredSize(buttonSize);

        // Centrar los botones horizontalmente
        opcion1.setAlignmentX(Component.CENTER_ALIGNMENT);
        opcion2.setAlignmentX(Component.CENTER_ALIGNMENT);
        opcion3.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Añadir los botones al panel derecho
        panelDerecho.add(opcion1);
        panelDerecho.add(Box.createRigidArea(new Dimension(0, 20))); // Espacio entre botones
        panelDerecho.add(opcion2);
        panelDerecho.add(Box.createRigidArea(new Dimension(0, 20))); // Espacio entre botones
        panelDerecho.add(opcion3);

        // Centrar botones verticalmente usando glue
        panelDerecho.add(Box.createVerticalGlue());

        // Usamos un JSplitPane para dividir la ventana en 2 paneles, izquierdo y
        // derecho
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panelIzquierdo, panelDerecho);
        splitPane.setResizeWeight(0.85); // Ajusta la proporción entre los paneles (75% para el izquierdo, 25% para el
                                         // derecho)

        // Añadir el splitPane al marco principal
        this.add(splitPane, BorderLayout.CENTER);
        // Hacer visible la ventana
        this.setVisible(true);
    }
    public void ActualizarContadorPersonas(int num){
        panelIzquierdo.ActualizarContadorPersonas(num);
    }
    public void AbrirAscensor(int pos) {
        panelIzquierdo.AbrirAscensor(pos);
    }

    public void CerrarAscensor(int pos) {
        panelIzquierdo.CerrarAscensor(pos);
    }

    public void CambiarPosAscensor(int num, int y) {
        panelIzquierdo.CambiarPos(num, y);
    }

    public void AñadirPersona(int a) {
        panelIzquierdo.AñadirPersona(a);
    }

    public void AñadirPersonaSalida(int a) {
        panelIzquierdo.AñadirPersonaSalida(a);
    }

    public void EliminarPersona(int a) {
        panelIzquierdo.EliminarPersona(a);
    }

    public void EliminarPersonaSalida(int a) {
        panelIzquierdo.EliminarPersonaSalida(a);
    }

    public void CambioSentido(boolean sentido) {
        panelIzquierdo.CambioSentido(sentido);
    }

    public void ActualizarDestino(int num) {
        panelIzquierdo.ActualizarDestino(num);
    }

    @SuppressWarnings("unused")
    public static void main(String[] args) {
        InterfazAscensor interfaz = new InterfazAscensor();
    }
}
