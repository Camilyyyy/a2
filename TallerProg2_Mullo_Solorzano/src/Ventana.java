import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ventana {
    private JPanel principal;
    private JTabbedPane tabbedPane1;
    private JTextField txtId;
    private JTextField txtNombre;
    private JComboBox cboClase;
    private JTextField txtNivel;
    private JTextField txtPoder;
    private JComboBox cboRareza;
    private JButton btnRegistrar;
    private JTextArea txtPersonajes;
    private JTextField txtBuscarPorNombre;
    private JButton buscarPorNombreButton;
    private JTextField txtBuscarPorID;
    private JButton buscarPorIdButton;
    private JTextArea txtMostrar;
    private Juego juego = new Juego();

    public Ventana(){
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(txtId.getText());
                String nombre = txtNombre.getText();
                String clase = cboClase.getSelectedItem().toString();
                int nivel = Integer.parseInt(txtNivel.getText());
                int poder = Integer.parseInt(txtPoder.getText());
                String rareza =cboRareza.getSelectedItem().toString();
                try {
                    juego.agregarPersonaje(new Personaje(id, nombre, clase, nivel, poder, rareza));
                    JOptionPane.showMessageDialog(null, "Personaje agregado");
                } catch (Exception exception){
                    JOptionPane.showMessageDialog(null, exception.getMessage());
                }
                txtPersonajes.setText(juego.toString());


            }
        });
        buscarPorNombreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = txtBuscarPorNombre.getText();
                StringBuilder resultados = new StringBuilder();

                for (Personaje x : juego.listado) {
                    if (x.getNombre().equalsIgnoreCase(nombre)) {
                        resultados.append(x.toString()).append("\n");
                    }
                }

                if (resultados.length() > 0) {
                    txtMostrar.setText(resultados.toString());
                } else {
                    txtMostrar.setText("No se encontró ningún personaje con el nombre: " + nombre);
                }
            }
        });

        buscarPorIdButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(txtBuscarPorID.getText());
                    Personaje personaje = juego.buscarPersonajePorID(id);
                    if (personaje != null) {
                        txtMostrar.setText(personaje.toString());
                    } else {
                        JOptionPane.showMessageDialog(null, "No se encontró un personaje con el ID: " + id);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "El ID debe ser un número entero.");
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Ventana");
        frame.setContentPane(new Ventana().principal);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
