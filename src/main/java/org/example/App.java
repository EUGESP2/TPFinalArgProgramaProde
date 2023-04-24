package org.example;

import fixture.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Connection conexion = null;
        Statement consulta = null;
        try {
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/tp_integrador","root","AdminAdmin");
            System.out.println("Creating statement...");
            consulta = conexion.createStatement();
            String partidos;
           // String pronosticos;
            partidos = "SELECT id, ronda, equipo1, goles1, goles2,equipo2 FROM tp_integrador.resultados";
         //   pronosticos =  "SELECT participante, equipo1, gana1,empata,gana2, equipo2";
            //En la variable resultado obtendremos las distintas filas que nos devolvió la base.
           // ResultSet prode = consulta.executeQuery(pronosticos);
           ResultSet resultado = consulta.executeQuery(partidos);
         //   while (prode.next()){
          //      String nombre = prode.getString("participante");


           // }
            // Obtener las distintas filas de la consulta
            while (resultado.next()) {
                // Pbtener el valor de cada columna
                int id = resultado.getInt("id");
                int ronda = resultado.getInt("ronda");
                String equipo1 = resultado.getString("equipo1");
                int goles1 = resultado.getInt("goles1");
                int goles2 = resultado.getInt("" +
                        "goles2");
                String equipo2 = resultado.getString("equipo2");

                System.out.println("Mostrar los valores obtenidos");
                System.out.print("id: " + id);
                System.out.print("ronda: " + ronda);
                System.out.print(", Equipo1: " + equipo1);
                System.out.print(", Goles : " + goles1);
                System.out.println(", goles: " + goles2);
                System.out.print(", Equipo2: " + equipo2);

                System.out.println("     Muestra los pronosticos   ");
            }
            // Esto se utiliza par cerrar la conexión con la base de datos
            resultado.close();
            consulta.close();
            conexion.close();
        } catch (SQLException se) {
            // Execpción ante problemas de conexión
            se.printStackTrace();
        } finally {
            // Esta sentencia es para que ante un problema con la base igual se cierren las conexiones
            try {
                if (consulta != null)
                    consulta.close();
            } catch (SQLException se2) {
            }
            try {
                if (conexion != null)
                    conexion.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }

        //        "C:\\Users\\eugen\\Desktop\\curso java\\TPArgentinaPrograma\\ArchivosTP\\Resultados.csv" parameter 0
//        "C:\\Users\\eugen\\Desktop\\curso java\\TPArgentinaPrograma\\ArchivosTP\\Pronostico.csv" parameter 1
        String resultadosPartidos = "C:\\Users\\eugen\\Desktop\\curso java\\TPArgentinaProgramaFinal\\ArchivosTP\\Resultados.csv";
//
        String resultadosPronosticos = "C:\\Users\\eugen\\Desktop\\curso java\\TPArgentinaProgramaFinal\\ArchivosTP\\Pronostico.csv";
        // declaro las colecciones de objetos
        List<Pronostico> pronosticos = new ArrayList<>();
        List<Ronda> rondas = new ArrayList<>();
        List<String> data = new ArrayList<>();

        //  int puntos = 0;
        int rondaActual = 0;
        Ronda ronda = new Ronda();
        // levanto la info del archivo
        try {
            data = Files.readAllLines(Paths.get(resultadosPartidos));
        }catch (IOException e){
            e.printStackTrace();
        }


        // cantidad es la cantidad de lineas del archivo - el encabezado
        int cantidad = data.size() - 1;
        int cursor = 0;
        //recorro el contenido del archivo
        for (String fila : data) {
            //saleto el encabezado
            if (!fila.contains("Equipo1")) {
                String[] filaSplit = fila.split(","); // spliteo la fila

                cursor += 1;

                //la primera vez entra siempre
                //cambio de ronda
                if (rondaActual != Integer.parseInt(filaSplit[0])) // si rondaActual es != ronda
                {
                    if (rondaActual > 0)
                    {
                        rondas.add(ronda);  //cargo nueva ronda
                    }
                    rondaActual = Integer.parseInt(filaSplit[0]); // asigno a rondaActual el numero de ronda
                    ronda.setNumero(rondaActual);
                    ronda.vaciarPartidos();
                }




                Equipo equipo1 = new Equipo();
                Equipo equipo2 = new Equipo();

                int golesEquipo1 = 0;
                int golesEquipo2 = 0;




                equipo1.setNombreEquipo(filaSplit[1]);
                equipo1.setDescripcion("Seleccion");
                try {
                    golesEquipo1 = Integer.parseInt(filaSplit[2]);
                    golesEquipo2 = Integer.parseInt(filaSplit[3]);
                }
                catch (NumberFormatException e)
                {
                    e.printStackTrace();
                }
                equipo2.setNombreEquipo(filaSplit[4]);
                equipo2.setDescripcion("Seleccion");

                Partido partido = new Partido(equipo1, equipo2, golesEquipo1, golesEquipo2);

                //partidos.add(partido);

                ronda.addPartido(partido);

                if (cursor == cantidad)
                {
                    rondas.add(ronda); /// agrego una ronda a rondas (list)
                }

            }


        }
        data = new ArrayList<>();

        try {
            data = Files.readAllLines(Paths.get(resultadosPronosticos));
        }catch (IOException e){
            e.printStackTrace();
        }

        for (String fila : data ) {
            if (!fila.contains("Equipo1")) {
                String[] filaSplit = fila.split(",");

                Equipo equipoGanador = new Equipo();
                Persona persona = new Persona();

                Resultado resultado = null;



                persona.setNombre(filaSplit[0]);
                if (filaSplit[2].equals("x")) {
                    equipoGanador.setNombreEquipo(filaSplit[1]);
                    equipoGanador.setDescripcion("Seleccion");
                    resultado = Resultado.GANADOR;

                }
                if (filaSplit[3].equals("x")) {
                    equipoGanador.setNombreEquipo(filaSplit[1]);
                    equipoGanador.setDescripcion("Seleccion");
                    resultado = Resultado.EMPATE;
                }
                if (filaSplit[4].equals("x")) {
                    equipoGanador.setNombreEquipo(filaSplit[5]);
                    equipoGanador.setDescripcion("Seleccion");
                    resultado = Resultado.GANADOR;
                }

                Partido partido = new Partido();

                String equipo1Pronostico = filaSplit[1];
                String equipo2Pronostico = filaSplit[5];

                // recorro las rondas
                for (Ronda item: rondas) {
                    //recorro los partidos de esta ronda
                    int cantidadPartidos = item.getPartidos().size();
                    for (int i = 0; i < cantidadPartidos; i++) {
                        Partido itemPartido = item.getPartidos().get(i);
                        if (equipo1Pronostico.equals(itemPartido.equipo1.getNombreEquipo()) && equipo2Pronostico.equals(itemPartido.equipo2.getNombreEquipo())) {
                            partido = item.getPartidos().get(i);
                        }
                    }
                }

                Pronostico pronostico = new Pronostico(equipoGanador, resultado, persona, partido);
                pronosticos.add(pronostico);

                //puntos += pronostico.Puntos();





            }
        }
        // muestro todas las personas y sus puntos


        vistaPersona vista = new vistaPersona();
        String persona = "";

        int i = 0;
        //recorro los pronosticos
        for (Pronostico item: pronosticos) {
            i++;
            //la primera vez entro siempre
            //me si persona es la misma persona que la del pronostico
            if (!persona.equals(item.getPersona().getNombre()))
            {
                if (!persona.equals(""))
                {
                    if (i - 1 == vista.puntaje)
                    {
                        vista.puntaje = vista.puntaje + 5;
                    }
                    System.out.println(vista.nombre + " " + vista.puntaje);
                }
                persona = item.getPersona().getNombre();
                vista.nombre = persona;
                vista.puntaje = 0;
            }


            vista.puntaje += item.Puntos();

            if (i == pronosticos.size())
            {
                if (i - 1 == vista.puntaje)
                {
                    vista.puntaje = vista.puntaje + 5;
                }
                System.out.println(vista.nombre + " " + vista.puntaje);
            }

        }


    }

}

