package conexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Acceso {

    public static Connection getConexion() {
    Connection cn = null;  // <-- INICIALIZA!
    try {
        System.out.println("Intentando conectar a la BD...");

        String url = "jdbc:mysql://buarfnvcold45c8vatsq-mysql.services.clever-cloud.com:3306/buarfnvcold45c8vatsq?useSSL=false";
        String user = "unxm4pumtkyd1f5l";
        String password = "BE1bLWhFcaZ3eL0A7Dxa";

        Class.forName("com.mysql.cj.jdbc.Driver");
        cn = DriverManager.getConnection(url, user, password);

        System.out.println("Conexión exitosa a Clever Cloud");
    } catch (Exception e) {
        System.err.println("ERROR CONEXIÓN BD: " + e.getMessage());
    }
    return cn;
}


    public static String ejecutar(String sql) {
        String msg = null;
        try {
            Connection cn = getConexion();
            if (cn == null) {
                msg = "No hay Conexion con la Base de Datos";
            } else {
                Statement st = cn.createStatement();
                st.executeUpdate(sql);
                cn.close();
            }
        } catch (SQLException e) {
            msg = e.getMessage();
        }
        return msg;
    }

    public static List listar(String sql) {
        List lista = new ArrayList();
        try {
            Connection cn = getConexion();
            if (cn == null) {
                return null;
            } else {
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(sql);
                ResultSetMetaData rm = rs.getMetaData();
                int numCol = rm.getColumnCount();
                while (rs.next()) {
                    Object[] fila = new Object[numCol];
                    for (int i = 0; i < numCol; i++) {
                        fila[i] = rs.getObject(i + 1);
                    }
                    lista.add(fila);
                }
                cn.close();
            }
        } catch (SQLException e) {
            return null;
        }
        return lista;
    }

    public static Object[] buscar(String sql) {
        List lista = listar(sql);
        if (lista != null && !lista.isEmpty()) {
            return (Object[]) lista.get(0);
        }
        return null;
    }

    public static String getNum(String sql) {
        String numGen = null;
        try {
            Connection cn = getConexion();
            if (cn == null) {
                return null;
            }

            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            String numObt = null;

            while (rs.next()) {
                numObt = rs.getString(1);
            }

            if (numObt != null && numObt.length() >= 2) {
                String parInt = numObt.substring(2);
                String parStr = numObt.substring(0, 1);
                String nueParInt = String.valueOf(Integer.parseInt(parInt) + 1);
                while (nueParInt.length() < 5) {
                    nueParInt = "0" + nueParInt;
                }
                numGen = parStr + nueParInt;
            }

            cn.close();

        } catch (SQLException e) {
            return null;
        }

        return numGen;
    }
}





