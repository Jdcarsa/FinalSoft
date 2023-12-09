package edu.com.ingsoft.acciones.MercadoValores.infraestructura.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "acciones")
public class AccionDTO {

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

}
