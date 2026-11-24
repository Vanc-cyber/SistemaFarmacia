package Capa_Entidades;

public class Categoria {

    //Atributos 
    private int idCategoria;
    private String nombre;
    private String descripcion;

    //Constructor //
    public Categoria(int idCategoria, String nombre, String descripcion) {
        this.idCategoria = idCategoria;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    //Constructor vacío//
    public Categoria() {
        this.idCategoria = 0;
        this.nombre = "";
        this.descripcion = "";
    }

    //Métodos getter y setter
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    // Texto que se muestra en un JComboBox<Categoria>
    @Override
    public String toString() {
        return idCategoria + " - " + nombre;
    }

    // Dos categorías son iguales si tienen el mismo identificador
    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        return idCategoria == ((Categoria) obj).idCategoria;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(idCategoria);
    }

}