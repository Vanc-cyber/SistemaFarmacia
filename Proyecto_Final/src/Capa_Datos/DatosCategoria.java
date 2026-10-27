
package Capa_Datos;

import Capa_Entidades.Categoria;
import java.util.ArrayList;

public class DatosCategoria {

    // Lista donde se almacenan las categorías
    private final ArrayList<Categoria> listaCategorias = new ArrayList<>();

    // Agrega una categoría a la lista
    public void agregarCategoria(Categoria categoria) {

        if (categoria == null) {
            throw new IllegalArgumentException(
                    "La categoría no puede ser null."
            );
        }

        listaCategorias.add(categoria);
    }

    // Busca una categoría por su ID
    public Categoria buscarCategoria(int idCategoria) {

        for (Categoria categoria : listaCategorias) {

            if (categoria != null
                    && categoria.getIdCategoria() == idCategoria) {

                return categoria;
            }
        }

        return null;
    }

    // Devuelve la lista de categorías registradas
    public ArrayList<Categoria> listarCategorias() {

        return new ArrayList<>(listaCategorias);
    }

    // Modifica una categoría existente
    public boolean modificarCategoria(Categoria categoria) {

        if (categoria == null) {
            return false;
        }

        for (int i = 0; i < listaCategorias.size(); i++) {

            Categoria actual = listaCategorias.get(i);

            if (actual != null
                    && actual.getIdCategoria()
                    == categoria.getIdCategoria()) {

                listaCategorias.set(i, categoria);
                return true;
            }
        }

        return false;
    }

    // Elimina una categoría por su ID
    public boolean eliminarCategoria(int idCategoria) {

        for (int i = 0; i < listaCategorias.size(); i++) {

            Categoria actual = listaCategorias.get(i);

            if (actual != null
                    && actual.getIdCategoria() == idCategoria) {

                listaCategorias.remove(i);
                return true;
            }
        }

        return false;
    }
}
