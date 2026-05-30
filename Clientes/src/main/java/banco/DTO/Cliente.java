package banco;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public class Cliente
{
    private Long id;
    private String nombres;
    private String apellidos;
    private String direccion;
    private String fechaNacimiento;
    private String telefono;
    private String identificacion;
    private Integer tipoIdentificacion;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getNombres()
    {
        return nombres;
    }

    public void setNombres(String nombres)
    {
        this.nombres = nombres;
    }

    public String getApellidos()
    {
        return apellidos;
    }

    public void setApellidos(String apellidos)
    {
        this.apellidos = apellidos;
    }

    public String getDireccion()
    {
        return direccion;
    }

    public void setDireccion(String direccion)
    {
        this.direccion = direccion;
    }

    public String getFechaNacimiento()
    {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento)
    {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getTelefono()
    {
        return telefono;
    }

    public void setTelefono(String telefono)
    {
        this.telefono = telefono;
    }

    public String getIdentificacion()
    {
        return identificacion;
    }

    public void setIdentificacion(String identificacion)
    {
        this.identificacion = identificacion;
    }

    public Integer getTipoIdentificacion()
    {
        return tipoIdentificacion;
    }

    public void setTipoIdentificacion(Integer tipoIdentificacion)
    {
        this.tipoIdentificacion = tipoIdentificacion;
    }
}
