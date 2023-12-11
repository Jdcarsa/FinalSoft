package edu.com.ingsoft.acciones.MercadoValores.infraestructura.servicio;


import edu.com.ingsoft.acciones.MercadoValores.dominio.modelo.Accion;
import edu.com.ingsoft.acciones.MercadoValores.infraestructura.excepciones.ResourceNotFoundException;
import edu.com.ingsoft.acciones.MercadoValores.infraestructura.dto.AccionDTO;
import edu.com.ingsoft.acciones.MercadoValores.dominio.repositorio.IAccionRepositorio;
import edu.com.ingsoft.acciones.MercadoValores.infraestructura.mapper.AccionMapperImp;
import edu.com.ingsoft.acciones.MercadoValores.infraestructura.mapper.IAccionMapper;
import edu.com.ingsoft.acciones.MercadoValores.infraestructura.rabbitService.RabbitNotificador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class AccionServicioImp implements IAccionServicio {

    @Autowired
    private IAccionRepositorio repositorio;

    private IAccionMapper mapper = new AccionMapperImp();

    private RabbitNotificador notificador = new RabbitNotificador();

    @Override
    public Accion guardarAccion(Accion a) throws Exception {
        aumentarPrecios();
        AccionDTO aDTO = mapper.toDTO(a);
        Accion accionGuardada = mapper.toDominio(repositorio.save(aDTO));
        return accionGuardada;
    }

    @Override
    public List<Accion> obtenerAcciones() throws Exception {
        aumentarPrecios();
        List<Accion> acciones = mapper.toDominioList(repositorio.findAll());
        return acciones;
    }

    @Override
    public Accion actualizarAccion(Long id, AccionDTO accionDTOActualizada) throws Exception {
        AccionDTO a = repositorio.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("Accion","id",id));
        a.setNombreAccion(accionDTOActualizada.getNombreAccion());
        a.setPrecioActual(accionDTOActualizada.getPrecioActual());
        a.setPrecioAnterior(accionDTOActualizada.getPrecioAnterior());
        a.setUmbralSuperior(accionDTOActualizada.getUmbralSuperior());
        a.setUmbralInferior(accionDTOActualizada.getUmbralInferior());
        aumentarPrecios();
        Accion accionActualizada = mapper.toDominio(repositorio.save(a));
        return accionActualizada;
    }

    @Override
    public Accion obtenerAccionPorId(Long id) throws Exception {
        AccionDTO a = repositorio.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("Accion","id",id));
        Accion accion = mapper.toDominio(a);
        aumentarPrecios();
        return accion;
    }


    @Override
    public void removerAccion(Long id) throws Exception {
        AccionDTO a = repositorio.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("Accion","id",id));
        repositorio.delete(a);
        aumentarPrecios();
    }

    @Override
    public Accion cambiarPrecioAccion(Long id, double precio) throws Exception {
        AccionDTO a = repositorio.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("Accion","id",id));
        Accion accion = mapper.toDominio(a);
        accion.setPrecioAnterior(accion.getPrecioActual());
        accion.setPrecioActual(precio);
        cambiarUmbrales(accion);
        return accion;
    }

    @Override
    public void cambiarUmbrales(Accion a) throws Exception {
        String ms;
        if(a.getPrecioActual() > a.getUmbralSuperior()){
            double aumentarUmbral = a.getUmbralSuperior()*0.5 + a.getUmbralSuperior();
            a.setUmbralSuperior(aumentarUmbral);
            ms = "La accion " +a.getNombreAccion() +
                    " ha rebasado su umbral superior , nuevo umbral : " +a.getUmbralSuperior() +
                    ", nuevo precio: " + a.getPrecioActual()+ ", precio anterior: " + a.getPrecioAnterior();
            notificador.notificar(ms);
            AccionDTO aDTO = mapper.toDTO(a);
            actualizarAccion(a.getIdAccion(),aDTO);
        } else if (a.getPrecioActual() < a.getUmbralInferior()) {
            double disminuirUmbral = a.getUmbralInferior()/20 + a.getUmbralInferior();
            a.setUmbralInferior(disminuirUmbral);
            ms = "La accion " +a.getNombreAccion() +
                    " ha rebasado su umbral inferior, nuevo umbral: " +a.getUmbralInferior() +
                    ", nuevo precio: " + a.getPrecioActual()+ ", precio anterior: " + a.getPrecioAnterior();
            notificador.notificar(ms);
            AccionDTO aDTO = mapper.toDTO(a);
            actualizarAccion(a.getIdAccion(),aDTO);
        }
    }

    @Override
    public void aumentarPrecios() throws Exception {
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
