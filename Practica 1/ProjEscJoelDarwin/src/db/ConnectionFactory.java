package db;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ConnectionFactory {

    // Darwin esto es un map donde cuando lea el properties guardara los credenciales de las diferentes bases de datos y tecnologias
    private static final Map<String, ConnectionProvider> providers = new HashMap<>();

    // Esto una vez se encienda la app se iniciara para leer el propperties y ver que bases de datos tenemos
    static {
        try {
            InputStream input = ConnectionFactory.class
                    .getClassLoader()
                    .getResourceAsStream("resources/properties/db.properties");

            if (input == null) {
                throw new RuntimeException("No se encontró db.properties");
            }

            Properties prop = new Properties();
            prop.load(input);

            // MYSQL aqui añadimos la base de datos msql en el map para cuando queramos usarla
            providers.put("mysql", new MySQLConnection(
                    prop.getProperty("db.mysql.url"),
                    prop.getProperty("db.mysql.user"),
                    prop.getProperty("db.mysql.password")
            ));

            // POSTGRES aqui la posgres que no la tenemos ni creada ni la usaremos de momento
            providers.put("postgres", new PostgresConnection(
                    prop.getProperty("db.postgres.url"),
                    prop.getProperty("db.postgres.user"),
                    prop.getProperty("db.postgres.password")
            ));

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error cargando configuración de BD");
        }
    }

    //Este es el metodo que cuando utilizemos la interfaz ConnectionProvider nos dejara elegir cual base de datos queremos usar
    /**
     * @param type String  indicamos la tecnologia/SGBD que queramos acceder
     * @return Devuelve el objeto ConnectionProvider
     **/
    public static ConnectionProvider getProvider(String type) {

        ConnectionProvider provider = providers.get(type);  // Aqui le metemos del map los credenciales escogidos

        if (provider == null) {
            throw new RuntimeException("Tipo de BD no soportado: " + type); //en caso que no los encuentre o no esten implementados salta este error
        }

        return provider; // devolvemos el provedor que usara el provider para hacer la conexion
    }
}