package entity;

public class Empresa {

    private int id;
    private String name;
    private String sector;
    private String ubicacion;
    private String contacto;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public Empresa(String name, String sector, String ubicacion, String contacto) {
        this.id = id;
        this.name = name;
        this.sector = sector;
        this.ubicacion = ubicacion;
        this.contacto = contacto;
    }

    public Empresa() {
    }

    @Override
    public String toString() {
        return "empresa{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sector='" + sector + '\'' +
                ", ubicacion='" + ubicacion + '\'' +
                ", contacto='" + contacto + '\'' +
                '}';
    }


}
