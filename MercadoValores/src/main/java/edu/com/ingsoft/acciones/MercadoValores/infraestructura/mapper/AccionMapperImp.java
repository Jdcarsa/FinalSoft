package edu.com.ingsoft.acciones.MercadoValores.infraestructura.mapper;

import edu.com.ingsoft.acciones.MercadoValores.dominio.modelo.Accion;
import edu.com.ingsoft.acciones.MercadoValores.infraestructura.dto.AccionDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class AccionMapperImp implements IAccionMapper{

    @Autowired
    private ModelMapper modelMapper;

    public AccionMapperImp() {
        modelMapper = new ModelMapper();
    }

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

    @Override
    public List<Accion> toDominioList(List<AccionDTO> acciones) {
        return acciones.stream()
                .map(accion -> modelMapper.map(accion, Accion.class))
                .collect(Collectors.toList());
    }

}
