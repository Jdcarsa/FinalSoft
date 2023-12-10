package edu.com.ingsoft.acciones.MercadoValores.infraestructura.servicio;



import edu.com.ingsoft.acciones.MercadoValores.dominio.modelo.Accion;
import edu.com.ingsoft.acciones.MercadoValores.infraestructura.dto.AccionDTO;

import java.util.List;

public interface IAccionServicio {

    /**
     * @brief guarda la accion que es ingresada por json en base de datos
     * @param a, una entidad de clase accion
     * @return La accion registrada
     */
    public Accion guardarAccion(Accion a) throws Exception;

    /**
     * @brief obtiene todas las acciones que se encuetran en la base de datos
     * @return una lista con las acciones
     */
    public List<Accion> obtenerAcciones() throws Exception;

    /**
     * @brief Actualiza una accion por su id
     * @param id de la accion a actualizar
     * @param accionDTOActualizada nueva informacion de la accion
     * @return la accion con la nueva informacion
     */
    public Accion actualizarAccion(Long id, AccionDTO accionDTOActualizada) throws Exception;

    /**
     * @brief Busca una accion por su id
     * @param id de la accion a buscar
     * @return la accion
     */
    public Accion obtenerAccionPorId(Long id) throws Exception;

    /**
     * @brief Busca una accion por su id y modifica su precio actual
     * @param id de la accion a modificar el precio
     * @param precio el nuevo precio de la accion
     * @return la accion con la nueva informacion
     */
    public Accion cambiarPrecioAccion(Long id, double precio) throws Exception;

    /**
     * @brief Busca una accion por su id y la elimina
     * @param id de la accion a eliminar
     */
    public void removerAccion(Long id) throws Exception;

    /**
     * @brief Recibe una accion y verifica si su precio paso los umbrales establecidos
     * y los modifica
     * @param a Accion a verificar los umbrales
     */
    public void cambiarUmbrales(Accion a) throws Exception;

    /**
     * @brief Aumenta el precio de cualquier accion de manera aletoria
     */
    public void aumentarPrecios() throws Exception;
}
