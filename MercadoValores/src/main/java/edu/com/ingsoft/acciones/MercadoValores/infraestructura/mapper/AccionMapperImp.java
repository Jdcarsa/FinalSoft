package edu.com.ingsoft.acciones.MercadoValores.infraestructura.mapper;

import edu.com.ingsoft.acciones.MercadoValores.dominio.modelo.Accion;
import edu.com.ingsoft.acciones.MercadoValores.infraestructura.dto.AccionDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class AccionMapperImp implements IAccionMapper{

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public AccionDTO toDTO(Accion accion) {
        AccionDTO accionDTO = modelMapper.map(accion,AccionDTO.class);
        return accionDTO;
    }

    @Override
    public Accion toDominio(AccionDTO accionDTO) {
        Accion accion = modelMapper.map(accionDTO,Accion.class);
        return accion;
    }
}
