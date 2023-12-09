package edu.com.ingsoft.acciones.MercadoValores.controladorTest;


import edu.com.ingsoft.acciones.MercadoValores.aplicacion.CRUD.AccionCRUD;
import edu.com.ingsoft.acciones.MercadoValores.dominio.modelo.Accion;
import edu.com.ingsoft.acciones.MercadoValores.infraestructura.dto.AccionDTO;
import edu.com.ingsoft.acciones.MercadoValores.infraestructura.mapper.AccionMapperImp;
import edu.com.ingsoft.acciones.MercadoValores.infraestructura.mapper.IAccionMapper;
import edu.com.ingsoft.acciones.MercadoValores.infraestructura.servicio.AccionServicioImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class Accion1CRUDTest {

    private MockMvc mockMvc;

    @InjectMocks
    private AccionCRUD controlador;

    @Mock
    AccionServicioImp servicioImp;

    private IAccionMapper mapper = new AccionMapperImp();

    @BeforeEach
    public  void setUp() throws Exception{
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controlador).build();
    }

    /**
     * @brief Prueba unitaria para el metodo guardar accion
     * @throws Exception
     */
    @Test
    public void testAccionAgregada() throws Exception{
        AccionDTO accionDTO = new AccionDTO("Totto",7000,0,28000,1000);
        Accion accion = mapper.toDominio(accionDTO);
        when(servicioImp.guardarAccion(accion)).thenReturn(accion);

        ResponseEntity<Accion> response = controlador.guardar(accion);

        assertEquals(response.getStatusCode(), HttpStatus.CREATED);
        assertEquals(response.getBody(), accion);
    }

    /**
     * @brief Prueba unitaria para el metodo buscar acciones
     * @throws Exception
     */
    @Test
   public void testBuscarAcciones() throws  Exception{
        AccionDTO accionDTO11 = new AccionDTO("Totto",7000,0,28000,1000);
        AccionDTO accionDTO12 = new AccionDTO("Bata",7000,0,28000,1000);
        List<AccionDTO> acciones = Arrays.asList(accionDTO11, accionDTO12);
        List<Accion> accionList = mapper.toDominioList(acciones);
        when(servicioImp.obtenerAcciones()).thenReturn(accionList);

        ResponseEntity<List<Accion>> response = controlador.obtenerAcciones();

        assertEquals(response.getStatusCode(), HttpStatus.FOUND);
        assertEquals(response.getBody(), accionList);
    }


    /**
     * @brief Prueba unitaria para el metodo buscar accion
     * por id
     * @throws Exception
     */
    @Test
    public void testBuscarAccionId()throws  Exception{
        Long id = 1L;
        AccionDTO accionDTO = new AccionDTO("Totto",7000,0,28000,1000);
        Accion accion = mapper.toDominio(accionDTO);
        when(servicioImp.obtenerAccionPorId(id)).thenReturn(accion);

        ResponseEntity<Accion> response = controlador.obtenerAccion(id);

        assertEquals(response.getStatusCode(), HttpStatus.FOUND);
        assertEquals(response.getBody(), accion);
    }

    @Test
    public void testActualizarAccion()throws  Exception{
        Long id = 1L;
        AccionDTO accionDTO11 = new AccionDTO("Totto",7000,0,28000,1000);
        AccionDTO accionDTO12 = new AccionDTO("Bata",7000,0,28000,1000);
        Accion accion = mapper.toDominio(accionDTO12);
        when(servicioImp.actualizarAccion(id, accionDTO12)).thenReturn(accion);

        ResponseEntity<Accion> response = controlador.actualizar(accionDTO12,id);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody(), accion);
    }

    @Test
    public void testActualizarPrecioAccion()throws  Exception{
        Long id = 1L;
        AccionDTO accionDTO = new AccionDTO("Totto",7000,0,28000,1000);
        Accion accion = mapper.toDominio(accionDTO);
        when(servicioImp.cambiarPrecioAccion(id,8000)).thenReturn(accion);

        ResponseEntity<Accion> response = controlador.actualizarPrecio(8000,id);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody(), accion);
    }

    @Test
    public void testEliminarAccion()throws  Exception{
        Long id = 1L;
        AccionDTO accionDTO = new AccionDTO("Totto",7000,0,28000,1000);

        ResponseEntity<String> response = controlador.borrar(id);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody(), "ACCION "+id+" ELIMINADA");
    }

}
