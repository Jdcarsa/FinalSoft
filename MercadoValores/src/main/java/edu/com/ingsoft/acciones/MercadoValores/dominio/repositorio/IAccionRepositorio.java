package edu.com.ingsoft.acciones.MercadoValores.dominio.repositorio;



import edu.com.ingsoft.acciones.MercadoValores.infraestructura.dto.AccionDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAccionRepositorio extends JpaRepository<AccionDTO,Long> {
}
