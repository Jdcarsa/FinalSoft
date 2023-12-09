package edu.com.ingsoft.acciones.MercadoValores.infraestructura.mapper;

import edu.com.ingsoft.acciones.MercadoValores.infraestructura.dto.AccionDTO;
import edu.com.ingsoft.acciones.MercadoValores.dominio.modelo.Accion;

public interface IAccionMapper {

    AccionDTO toDTO(Accion accion);

    Accion toDominio(AccionDTO accionDTO);
}
