package edu.com.ingsoft.acciones.MercadoValores.infraestructura.rabbitService;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.net.URLDecoder;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import edu.com.ingsoft.rabbitmqservice.notificador.INotificador;

public class Manager {

    private Properties serviceProperties;

    private static Manager instance;
    private static final String FILE_NAME = "application.properties";
    private Manager(){
        serviceProperties = new Properties();
    }

    public static Manager getInstance(){
        return  instance;
    }


    public static void init(String basePath) throws Exception {

        instance = new Manager();
        instance.loadProperties(basePath);
        if (instance.serviceProperties.isEmpty()) {
            throw new Exception("Could not initialize plugins");
        }

    }
    public INotificador getNotificador(String property) {


        if (!serviceProperties.containsKey(property)) {
            return null;
        }

        INotificador conector = null;
        //Obtener el nombre de la clase del conector.
        String conectorClassName = serviceProperties.getProperty(property);
        System.out.println(conectorClassName);

        try {

            //Obtener una referencia al tipo de la clase del conector.
            Class<?> conectorClass = Class.forName(conectorClassName);
            if (conectorClass != null) {

                //Crear un nuevo objeto del conector.
                Object conectorObject = conectorClass.getDeclaredConstructor().newInstance();

                if (conectorObject instanceof INotificador) {
                    conector = (INotificador) conectorObject;
                }
            }

        } catch (ClassNotFoundException | IllegalAccessException | IllegalArgumentException | InstantiationException | NoSuchMethodException | SecurityException |
                 InvocationTargetException ex) {
            Logger.getLogger("Error notificador").log(Level.SEVERE, "Error al ejecutar la aplicación", ex);
        }

        return  conector;
    }

    private void loadProperties(String basePath){


        try {
            String filePath = basePath+FILE_NAME;
            filePath = URLDecoder.decode(filePath, "UTF-8");
            try (FileInputStream stream = new FileInputStream(filePath)) {

                serviceProperties.load(stream);

            } catch (IOException ex) {
                Logger.getLogger("DeliveryPluginManager").log(Level.SEVERE, "Error al ejecutar la aplicación", ex);
            }

        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Manager.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
