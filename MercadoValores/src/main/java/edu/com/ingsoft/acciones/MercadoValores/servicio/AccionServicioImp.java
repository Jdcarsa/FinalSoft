package edu.com.ingsoft.acciones.MercadoValores.servicio;


import edu.com.ingsoft.acciones.MercadoValores.excepciones.ResourceNotFoundException;
import edu.com.ingsoft.acciones.MercadoValores.modelo.Accion;
import edu.com.ingsoft.acciones.MercadoValores.repositorio.IAccionRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

@Service
public class AccionServicioImp implements IAccionServicio {

    @Autowired
    private IAccionRepositorio repositorio;

    @Override
    public Accion guardarAccion(Accion a) {
        aumentarPrecios();
        return repositorio.save(a);
    }

    @Override
    public List<Accion> obtenerAcciones() {
        aumentarPrecios();
        return repositorio.findAll();
    }

    @Override
    public Accion actualizarAccion(Long id, Accion accionActualizada) {
        Accion a = repositorio.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("Accion","id",id));
        a.setNombreAccion(accionActualizada.getNombreAccion());
        a.setPrecioActual(accionActualizada.getPrecioActual());
        a.setPrecioAnterior(accionActualizada.getPrecioAnterior());
        a.setUmbralSuperior(a.getUmbralSuperior());
        a.setUmbralInferior(a.getUmbralInferior());
        aumentarPrecios();

        return repositorio.save(a);
    }

    @Override
    public Accion obtenerAccionPorId(Long id) {
        Accion a = repositorio.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("Accion","id",id));
        aumentarPrecios();

        return a;
    }


    @Override
    public void removerAccion(Long id) {
        Accion a = repositorio.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("Accion","id",id));
        repositorio.delete(a);
        aumentarPrecios();

    }

    @Override
    public Accion cambiarPrecioAccion(Long id, double precio){
        Accion a = repositorio.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("Accion","id",id));
        a.setPrecioAnterior(a.getPrecioActual());
        a.setPrecioActual(precio);
        cambiarUmbrales(a);
        return a;
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
            Accion a = repositorio.findAll().get(numeroAleatorio);
            double aumento = a.getPrecioActual() * 0.2 + a.getPrecioActual();
            a.setPrecioAnterior(a.getPrecioActual());
            a.setPrecioActual(aumento);
            cambiarUmbrales(a);
        }
    }
}
