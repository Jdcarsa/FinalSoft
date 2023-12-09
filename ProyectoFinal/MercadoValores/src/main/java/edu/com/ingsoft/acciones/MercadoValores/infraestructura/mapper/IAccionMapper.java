package edu.com.ingsoft.acciones.MercadoValores.infraestructura.mapper;

import edu.com.ingsoft.acciones.MercadoValores.infraestructura.dto.AccionDTO;
import edu.com.ingsoft.acciones.MercadoValores.dominio.modelo.Accion;

import java.util.List;

public interface IAccionMapper {

    AccionDTO toDTO(Accion accion);

    Accion toDominio(AccionDTO accionDTO);

    List<Accion> toDominioList(List<AccionDTO> acciones);
}
