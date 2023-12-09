package edu.com.ingsoft.acciones.MercadoValores.servicio;



import edu.com.ingsoft.acciones.MercadoValores.modelo.Accion;

import java.util.HashMap;
import java.util.List;

public interface IAccionServicio {

    /**
     * @brief guarda la accion que es ingresada por json en base de datos
     * @param a, una entidad de clase accion
     * @return La accion registrada
     */
    public Accion guardarAccion(Accion a);

    /**
     * @brief obtiene todas las acciones que se encuetran en la base de datos
     * @return una lista con las acciones
     */
    public List<Accion> obtenerAcciones();

    /**
     * @brief Actualiza una accion por su id
     * @param id de la accion a actualizar
     * @param accionActualizada nueva informacion de la accion
     * @return la accion con la nueva informacion
     */
    public Accion actualizarAccion(Long id, Accion accionActualizada);

    /**
     * @brief Busca una accion por su id
     * @param id de la accion a buscar
     * @return la accion
     */
    public Accion obtenerAccionPorId(Long id);

    /**
     * @brief Busca una accion por su id y modifica su precio actual
     * @param id de la accion a modificar el precio
     * @param precio el nuevo precio de la accion
     * @return la accion con la nueva informacion
     */
    public Accion cambiarPrecioAccion(Long id,double precio);

    /**
     * @brief Busca una accion por su id y la elimina
     * @param id de la accion a eliminar
     */
    public void removerAccion(Long id);

    /**
     * @brief Recibe una accion y verifica si su precio paso los umbrales establecidos
     * y los modifica
     * @param a Accion a verificar los umbrales
     */
    public void cambiarUmbrales(Accion a);

    /**
     * @brief Aumenta el precio de cualquier accion de manera aletoria
     */
    public void aumentarPrecios();
}
