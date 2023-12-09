package edu.com.ingsoft.acciones.MercadoValores.controladorTest;


import edu.com.ingsoft.acciones.MercadoValores.aplicacion.CRUD.AccionCRUD;
import edu.com.ingsoft.acciones.MercadoValores.infraestructura.dto.AccionDTO;
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

        when(servicioImp.guardarAccion(accionDTO)).thenReturn(accionDTO);

        ResponseEntity<AccionDTO> response = controlador.guardar(accionDTO);

        assertEquals(response.getStatusCode(), HttpStatus.CREATED);
        assertEquals(response.getBody(), accionDTO);
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
        when(servicioImp.obtenerAcciones()).thenReturn(acciones);

        ResponseEntity<List<AccionDTO>> response = controlador.obtenerAcciones();

        assertEquals(response.getStatusCode(), HttpStatus.FOUND);
        assertEquals(response.getBody(), acciones);
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

        when(servicioImp.obtenerAccionPorId(id)).thenReturn(accionDTO);

        ResponseEntity<AccionDTO> response = controlador.obtenerAccion(id);

        assertEquals(response.getStatusCode(), HttpStatus.FOUND);
        assertEquals(response.getBody(), accionDTO);
    }

    @Test
    public void testActualizarAccion()throws  Exception{
        Long id = 1L;
        AccionDTO accionDTO11 = new AccionDTO("Totto",7000,0,28000,1000);
        AccionDTO accionDTO12 = new AccionDTO("Bata",7000,0,28000,1000);
        when(servicioImp.actualizarAccion(id, accionDTO12)).thenReturn(accionDTO12);

        ResponseEntity<AccionDTO> response = controlador.actualizar(accionDTO12,id);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody(), accionDTO12);
    }

    @Test
    public void testActualizarPrecioAccion()throws  Exception{
        Long id = 1L;
        AccionDTO accionDTO = new AccionDTO("Totto",7000,0,28000,1000);
        when(servicioImp.cambiarPrecioAccion(id,8000)).thenReturn(accionDTO);

        ResponseEntity<AccionDTO> response = controlador.actualizarPrecio(8000,id);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody(), accionDTO);
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
