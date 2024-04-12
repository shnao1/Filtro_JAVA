package entity;

public class Contratacion {

    private int id;
    private String fechaAplicacion;
    private String estado;
    private String salario;

    public Contratacion(String fechaAplicacion, String estado, String salario) {
        this.id = id;
        this.fechaAplicacion = fechaAplicacion;
        this.estado = estado;
        this.salario = salario;
    }

    public Contratacion() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFechaAplicacion() {
        return fechaAplicacion;
    }

    public void setFechaAplicacion(String fechaAplicacion) {
        this.fechaAplicacion = fechaAplicacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getSalario() {
        return salario;
    }

    public void setSalario(String salario) {
        this.salario = salario;
    }

    @Override
    public String toString() {
        return "Contratacion{" +
                "id=" + id +
                ", fechaAplicacion='" + fechaAplicacion + '\'' +
                ", estado='" + estado + '\'' +
                ", salario='" + salario + '\'' +
                '}';
    }
}
