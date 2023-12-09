package edu.com.ingsoft.acciones.MercadoValores.infraestructura.servicio;


import edu.com.ingsoft.acciones.MercadoValores.infraestructura.excepciones.ResourceNotFoundException;
import edu.com.ingsoft.acciones.MercadoValores.infraestructura.dto.AccionDTO;
import edu.com.ingsoft.acciones.MercadoValores.dominio.repositorio.IAccionRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class AccionServicioImp implements IAccionServicio {

    @Autowired
    private IAccionRepositorio repositorio;

    @Override
    public AccionDTO guardarAccion(AccionDTO a) {
        aumentarPrecios();
        return repositorio.save(a);
    }

    @Override
    public List<AccionDTO> obtenerAcciones() {
        aumentarPrecios();
        return repositorio.findAll();
    }

    @Override
    public AccionDTO actualizarAccion(Long id, AccionDTO accionDTOActualizada) {
        AccionDTO a = repositorio.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("Accion","id",id));
        a.setNombreAccion(accionDTOActualizada.getNombreAccion());
        a.setPrecioActual(accionDTOActualizada.getPrecioActual());
        a.setPrecioAnterior(accionDTOActualizada.getPrecioAnterior());
        a.setUmbralSuperior(a.getUmbralSuperior());
        a.setUmbralInferior(a.getUmbralInferior());
        aumentarPrecios();

        return repositorio.save(a);
    }

    @Override
    public AccionDTO obtenerAccionPorId(Long id) {
        AccionDTO a = repositorio.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("Accion","id",id));
        aumentarPrecios();

        return a;
    }


    @Override
    public void removerAccion(Long id) {
        AccionDTO a = repositorio.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("Accion","id",id));
        repositorio.delete(a);
        aumentarPrecios();

    }

    @Override
    public AccionDTO cambiarPrecioAccion(Long id, double precio){
        AccionDTO a = repositorio.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("Accion","id",id));
        a.setPrecioAnterior(a.getPrecioActual());
        a.setPrecioActual(precio);
        cambiarUmbrales(a);
        return a;
    }

    @Override
    public void cambiarUmbrales(AccionDTO a){
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
            double aumento = a.getPrecioActual() * 0.2 + a.getPrecioActual();
            a.setPrecioAnterior(a.getPrecioActual());
            a.setPrecioActual(aumento);
            cambiarUmbrales(a);
        }
    }
}
