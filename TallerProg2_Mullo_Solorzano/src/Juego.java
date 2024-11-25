import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Juego {

    List<Personaje> listado;

    public Juego() {
        listado = new ArrayList<>();
        try {
            quemar(); // Cargar datos iniciales
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage());
        }
    }

    public boolean buscarPersonajePorNombre(String nombre) {
        for (Personaje x : listado) {
            if (x.getNombre().equals(nombre)) { // Cambiado == por .equals
                return true;
            }
        }
        return false;
    }

    public Personaje buscarPersonajePorID(int id) {
        if (listado.isEmpty()) {
            return null; // Lista vacía, no se puede buscar
        }

        if (id < listado.get(0).getId() || id > listado.get(listado.size() - 1).getId()) {
            return null; // ID fuera de rango
        }

        int inf = 0;
        int sup = listado.size() - 1;
        int centro;

        while (inf <= sup) {
            centro = (inf + sup) / 2;
            if (listado.get(centro).getId() == id) {
                return listado.get(centro);
            } else if (id > listado.get(centro).getId()) {
                inf = centro + 1;
            } else {
                sup = centro - 1;
            }
        }
        return null;
    }

    public boolean agregarPersonaje(Personaje nuevo) throws Exception {
        if (buscarPersonajePorID(nuevo.getId()) != null) {
            throw new Exception("El ID ya existe");
        } else {
            listado.add(nuevo);
            ordenar();
            return true;
        }
    }

    public void quemar() throws Exception {
        agregarPersonaje(new Personaje(1, "Link", "Arquero", 45, 300, "Épico"));
        agregarPersonaje(new Personaje(2, "Kratos", "Guerrero", 70, 500, "Legendario"));
        agregarPersonaje(new Personaje(3, "Aloy", "Arquero", 50, 320, "Épico"));
        agregarPersonaje(new Personaje(4, "Geralt", "Mago", 60, 400, "Raro"));
    }

    public void ordenar() {
        int j =0;
        Personaje aux;
        for(int p=1; p<listado.size(); p++){
            j = p-1;
            aux = listado.get(p);
            while (j>=0 && aux.getId()<listado.get(j).getId()){
                listado.set(j+1, listado.get(j));
                j--;

            }
            listado.set(j+1, aux);
        }
    }

    @Override
    public String toString() {
        return "Juego{" +
                "listado=" + listado +
                '}';
    }

    //    @Override
//    public String toString() {
//        if (listado.isEmpty()) {
//            return "No hay personajes registrados.";
//        }
//
//        StringBuilder resultado = new StringBuilder();
//        for (Personaje personaje : listado) {
//            resultado.append(personaje.toString());
//        }
//        return resultado.toString();
//    }
}
