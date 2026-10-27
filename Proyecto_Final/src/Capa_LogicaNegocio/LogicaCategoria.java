
package Capa_LogicaNegocio;

import Capa_Datos.DatosCategoria;
import Capa_Entidades.Categoria;
import java.util.ArrayList;

public class LogicaCategoria {

    // Acceso a la capa de datos de categorías
    private final DatosCategoria datosCategoria;

    // Constructor por defecto
    public LogicaCategoria() {
        this(new DatosCategoria());
    }

    // Constructor que recibe la capa de datos
    public LogicaCategoria(DatosCategoria datosCategoria) {

        if (datosCategoria == null) {
            throw new IllegalArgumentException(
                    "DatosCategoria es obligatorio."
            );
        }

        this.datosCategoria = datosCategoria;
    }

    // Registra una categoría después de validarla
    public void registrarCategoria(Categoria categoria) {

        if (categoria == null) {
            throw new IllegalArgumentException(
                    "Debe indicar una categoría."
            );
        }

        if (categoria.getIdCategoria() <= 0) {
            throw new IllegalArgumentException(
                    "El identificador de la categoría debe ser mayor que cero."
            );
        }

        requerido(
                categoria.getNombre(),
                "El nombre de la categoría es obligatorio."
        );

        categoria.setNombre(
                categoria.getNombre().trim()
        );

        if (categoria.getDescripcion() != null) {
            categoria.setDescripcion(
                    categoria.getDescripcion().trim()
            );
        }

        if (datosCategoria.buscarCategoria(
                categoria.getIdCategoria()) != null) {

            throw new IllegalArgumentException(
                    "Ya existe una categoría con ese identificador."
            );
        }

        datosCategoria.agregarCategoria(categoria);
    }

    // Busca una categoría por su ID
    public Categoria buscarCategoria(int idCategoria) {

        if (idCategoria <= 0) {
            throw new IllegalArgumentException(
                    "El identificador de la categoría debe ser mayor que cero."
            );
        }

        return datosCategoria.buscarCategoria(idCategoria);
    }

    // Devuelve las categorías registradas
    public ArrayList<Categoria> listarCategorias() {

        return datosCategoria.listarCategorias();
    }

    // Modifica una categoría existente
    public boolean modificarCategoria(Categoria categoria) {

        if (categoria == null) {
            throw new IllegalArgumentException(
                    "Debe indicar una categoría."
            );
        }

        if (categoria.getIdCategoria() <= 0) {
            throw new IllegalArgumentException(
                    "El identificador de la categoría debe ser mayor que cero."
            );
        }

        requerido(
                categoria.getNombre(),
                "El nombre de la categoría es obligatorio."
        );

        Categoria categoriaExistente =
                datosCategoria.buscarCategoria(
                        categoria.getIdCategoria()
                );

        if (categoriaExistente == null) {
            throw new IllegalArgumentException(
                    "La categoría que desea modificar no existe."
            );
        }

        categoria.setNombre(
                categoria.getNombre().trim()
        );

        if (categoria.getDescripcion() != null) {
            categoria.setDescripcion(
                    categoria.getDescripcion().trim()
            );
        }

        return datosCategoria.modificarCategoria(categoria);
    }

    // Elimina una categoría por su ID
    public boolean eliminarCategoria(int idCategoria) {

        if (idCategoria <= 0) {
            throw new IllegalArgumentException(
                    "El identificador de la categoría debe ser mayor que cero."
            );
        }

        Categoria categoria =
                datosCategoria.buscarCategoria(idCategoria);

        if (categoria == null) {
            throw new IllegalArgumentException(
                    "La categoría que desea eliminar no existe."
            );
        }

        return datosCategoria.eliminarCategoria(idCategoria);
    }

    // Valida que un texto no sea nulo ni vacío
    private void requerido(String valor, String mensaje) {

        if (valor == null || valor.trim().isEmpty()) {
            throw new IllegalArgumentException(mensaje);
        }
    }
}
