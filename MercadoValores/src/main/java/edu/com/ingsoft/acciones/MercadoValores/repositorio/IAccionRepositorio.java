package edu.com.ingsoft.acciones.MercadoValores.repositorio;



import edu.com.ingsoft.acciones.MercadoValores.modelo.Accion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAccionRepositorio extends JpaRepository<Accion,Long> {
}
