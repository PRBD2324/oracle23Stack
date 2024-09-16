package sol;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import bd.AbstractDBManager;
import bd.ConnectionManager;
import bd.ConstantesConexion;
import debug.Debug;
import model.ExcepcionDeAplicacion;
import model.LineaEnRealizacion;
import model.PedidoEnRealizacion;

public class GestorBD {

    // Constantes
    private static final String URL = getPropiedad("url");
    private static final String USR = getPropiedad("user");
    private static final String PWD = getPropiedad("password");

    // Métodos
    public static String getPropiedad(String clave) {
        String valor = null;
        try {
            Properties props = new Properties();
            InputStream prIS = GestorBD.class.getResourceAsStream("/conexion.properties");
            props.load(prIS);
            valor = props.getProperty(clave);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return valor;
    }



    public boolean isFestivo(Calendar fecha) throws ExcepcionDeAplicacion {
        boolean festivo = false;
        Connection con = null;
        try {
            con = DriverManager.getConnection(URL,USR,PWD);
    

            java.text.SimpleDateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd");
            String sql = "SELECT * FROM myUser.Festivo WHERE fecha ={d '"+df.format(fecha.getTime())+"'}";
            System.out.println(sql);
            Statement ps = con.createStatement();
            ResultSet res = ps.executeQuery(sql);
            festivo = res.next();
            Calendar c = Calendar.getInstance();
            Date d = new Date(c.getTimeInMillis());
            System.out.println(d);
            res.close();
            ps.close();
        } catch (SQLException ex) {
            throw new ExcepcionDeAplicacion(ex);
        } finally {
            if (con != null) {
                try {
                    ConnectionManager.returnConnection(con);
                } catch (SQLException ex1) {
                    throw new ExcepcionDeAplicacion(ex1);
                }
            }
        }
        return festivo;
    }


}
