package ar.edu.iua.info3;

public class Casos {

    private boolean infectado;
    private boolean fallecido;
    private int edad;
    private String provincia;
    private boolean cuidadoIntensivo;
    private String fechaCuidadoIntensivo;

    public boolean isInfectado() {
        return infectado;
    }

    public void setInfectado(boolean infectado) {
        this.infectado = infectado;
    }

    public boolean isFallecido() {
        return fallecido;
    }

    public void setFallecido(boolean fallecido) {
        this.fallecido = fallecido;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public boolean isCuidadoIntensivo() {
        return cuidadoIntensivo;
    }

    public void setCuidadoIntensivo(boolean cuidadoIntensivo) {
        this.cuidadoIntensivo = cuidadoIntensivo;
    }

    public String getFechaCuidadoIntensivo() {
        return fechaCuidadoIntensivo;
    }

    public void setFechaCuidadoIntensivo(String fechaCuidadoIntensivo) {
        this.fechaCuidadoIntensivo = fechaCuidadoIntensivo;
    }
}
