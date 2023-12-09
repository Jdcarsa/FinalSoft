package edu.com.ingsoft.acciones.MercadoValores.infraestructura.servicio;


import edu.com.ingsoft.acciones.MercadoValores.dominio.modelo.Accion;
import edu.com.ingsoft.acciones.MercadoValores.infraestructura.excepciones.ResourceNotFoundException;
import edu.com.ingsoft.acciones.MercadoValores.infraestructura.dto.AccionDTO;
import edu.com.ingsoft.acciones.MercadoValores.dominio.repositorio.IAccionRepositorio;
import edu.com.ingsoft.acciones.MercadoValores.infraestructura.mapper.AccionMapperImp;
import edu.com.ingsoft.acciones.MercadoValores.infraestructura.mapper.IAccionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class AccionServicioImp implements IAccionServicio {

    @Autowired
    private IAccionRepositorio repositorio;

    private IAccionMapper mapper = new AccionMapperImp();

    @Override
    public Accion guardarAccion(Accion a) {
        aumentarPrecios();
        AccionDTO aDTO = mapper.toDTO(a);
        Accion accionGuardada = mapper.toDominio(repositorio.save(aDTO));
        return accionGuardada;
    }

    @Override
    public List<Accion> obtenerAcciones() {
        aumentarPrecios();
        List<Accion> acciones = mapper.toDominioList(repositorio.findAll());
        return acciones;
    }

    @Override
    public Accion actualizarAccion(Long id, AccionDTO accionDTOActualizada) {
        AccionDTO a = repositorio.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("Accion","id",id));
        a.setNombreAccion(accionDTOActualizada.getNombreAccion());
        a.setPrecioActual(accionDTOActualizada.getPrecioActual());
        a.setPrecioAnterior(accionDTOActualizada.getPrecioAnterior());
        a.setUmbralSuperior(a.getUmbralSuperior());
        a.setUmbralInferior(a.getUmbralInferior());
        aumentarPrecios();
        Accion accionActualizada = mapper.toDominio(repositorio.save(a));
        return accionActualizada;
    }

    @Override
    public Accion obtenerAccionPorId(Long id) {
        AccionDTO a = repositorio.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("Accion","id",id));
        Accion accion = mapper.toDominio(a);
        aumentarPrecios();
        return accion;
    }


    @Override
    public void removerAccion(Long id) {
        AccionDTO a = repositorio.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("Accion","id",id));
        repositorio.delete(a);
        aumentarPrecios();

    }

    @Override
    public Accion cambiarPrecioAccion(Long id, double precio){
        AccionDTO a = repositorio.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("Accion","id",id));
        Accion accion = mapper.toDominio(a);
        accion.setPrecioAnterior(accion.getPrecioActual());
        accion.setPrecioActual(precio);
        cambiarUmbrales(accion);
        return accion;
    }

    @Override
    public void cambiarUmbrales(Accion a){
        if(a.getPrecioActual() > a.getUmbralSuperior()){
            double aumentarUmbral = a.getUmbralSuperior()*0.5 + a.getUmbralSuperior();
            a.setUmbralSuperior(aumentarUmbral);

        } else if (a.getPrecioActual() < a.getUmbralInferior()) {
            double disminuirUmbral = a.getUmbralInferior()/20 + a.getUmbralInferior();
            a.setUmbralInferior(disminuirUmbral);
        }
    }

    @Override
    public void aumentarPrecios(){
        if(!repositorio.findAll().isEmpty()) {
            Random rand = new Random();
            int numeroAleatorio = rand.nextInt(0, repositorio.findAll().size());
            AccionDTO a = repositorio.findAll().get(numeroAleatorio);
            Accion accion = mapper.toDominio(a);
            double aumento = accion.getPrecioActual() * 0.2 + accion.getPrecioActual();
            accion.setPrecioAnterior(accion.getPrecioActual());
            accion.setPrecioActual(aumento);
            cambiarUmbrales(accion);
        }
    }
}