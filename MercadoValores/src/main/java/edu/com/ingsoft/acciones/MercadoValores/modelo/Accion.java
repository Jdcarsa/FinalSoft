package edu.com.ingsoft.acciones.MercadoValores.modelo;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "acciones")
public class Accion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAccion;

    @NotNull
    @Column(name = "nombreAccion", nullable = false)
    private String nombreAccion;

    @NotNull
    @Column(name = "precioActual", nullable = false)
    @DecimalMin(value = "0.0", inclusive = false, message = "El precio debe ser mayor que 0")
    private double precioActual;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false, message = "El precio anterior debe ser mayor que 0")
    @Column(name = "precioAnterior", nullable = false)
    private double precioAnterior;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false, message = "El umbral superior debe ser mayor que 0")
    @Column(name = "umbralSuperior", nullable = false)
    private double umbralSuperior;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false, message = "El umbral inferior debe ser mayor que 0")
    @Column(name = "umbralInferior", nullable = false)
    private double umbralInferior;

    public Accion(){

    }
    public Accion(String nombreAccion, double precioActual, double precioAnterior,
                  double umbralSuperior, double umbralInferior) {
        this.nombreAccion = nombreAccion;
        this.precioActual = precioActual;
        this.precioAnterior = precioAnterior;
        this.umbralSuperior = umbralSuperior;
        this.umbralInferior = umbralInferior;
    }

    public Long getIdAccion() {
        return idAccion;
    }

    public void setIdAccion(Long idAccion) {
        this.idAccion = idAccion;
    }

    public String getNombreAccion() {
        return nombreAccion;
    }

    public void setNombreAccion(String nombreAccion) {
        this.nombreAccion = nombreAccion;
    }

    public double getPrecioActual() {
        return precioActual;
    }

    public void setPrecioActual(double precioActual) {
        this.precioActual = precioActual;
    }

    public double getPrecioAnterior() {
        return precioAnterior;
    }

    public void setPrecioAnterior(double precioAnterior) {
        this.precioAnterior = precioAnterior;
    }

    public double getUmbralSuperior() {
        return umbralSuperior;
    }

    public void setUmbralSuperior(double umbralSuperior) {
        this.umbralSuperior = umbralSuperior;
    }

    public double getUmbralInferior() {
        return umbralInferior;
    }

    public void setUmbralInferior(double umbralInferior) {
        this.umbralInferior = umbralInferior;
    }
}
