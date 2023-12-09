package edu.com.ingsoft.acciones.MercadoValores.controladorTest;


import edu.com.ingsoft.acciones.MercadoValores.controlador.AccionControlador;
import edu.com.ingsoft.acciones.MercadoValores.modelo.Accion;
import edu.com.ingsoft.acciones.MercadoValores.servicio.AccionServicioImp;
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

public class AccionControladorTest {

    private MockMvc mockMvc;

    @InjectMocks
    private AccionControlador controlador;

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
        Accion accion = new Accion("Totto",7000,0,28000,1000);

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
        Accion accion1 = new Accion("Totto",7000,0,28000,1000);
        Accion accion2 = new Accion("Bata",7000,0,28000,1000);
        List<Accion> acciones = Arrays.asList(accion1, accion2);
        when(servicioImp.obtenerAcciones()).thenReturn(acciones);

        ResponseEntity<List<Accion>> response = controlador.obtenerAcciones();

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
        Accion accion = new Accion("Totto",7000,0,28000,1000);

        when(servicioImp.obtenerAccionPorId(id)).thenReturn(accion);

        ResponseEntity<Accion> response = controlador.obtenerAccion(id);

        assertEquals(response.getStatusCode(), HttpStatus.FOUND);
        assertEquals(response.getBody(), accion);
    }

    @Test
    public void testActualizarAccion()throws  Exception{
        Long id = 1L;
        Accion accion1 = new Accion("Totto",7000,0,28000,1000);
        Accion accion2 = new Accion("Bata",7000,0,28000,1000);
        when(servicioImp.actualizarAccion(id,accion2)).thenReturn(accion2);

        ResponseEntity<Accion> response = controlador.actualizar(accion2,id);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody(), accion2);
    }

    @Test
    public void testActualizarPrecioAccion()throws  Exception{
        Long id = 1L;
        Accion accion = new Accion("Totto",7000,0,28000,1000);
        when(servicioImp.cambiarPrecioAccion(id,8000)).thenReturn(accion);

        ResponseEntity<Accion> response = controlador.actualizarPrecio(8000,id);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody(), accion);
    }

    @Test
    public void testEliminarAccion()throws  Exception{
        Long id = 1L;
        Accion accion = new Accion("Totto",7000,0,28000,1000);

        ResponseEntity<String> response = controlador.borrar(id);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody(), "ACCION "+id+" ELIMINADA");
    }

}
