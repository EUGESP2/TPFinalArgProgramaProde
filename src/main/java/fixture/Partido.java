package fixture;

public class Partido {

    public Partido()
    {

    }

    public Partido(Equipo equipo1, Equipo equipo2, int golesEquipo1, int golesEquipo2) {


        this.equipo1 = equipo1;
        this.equipo2 = equipo2;
        this.golesEquipo1 = golesEquipo1;
        this.golesEquipo2 = golesEquipo2;
    }
    public int ronda;
    public Equipo equipo1;
    public Equipo equipo2;

    public int golesEquipo1;
    public int golesEquipo2;




    public Equipo getEquipo1() {
        return equipo1;
    }

    public void setEquipo1(Equipo equipo1) {
        this.equipo1 = equipo1;
    }

    public Equipo getEquipo2() {
        return equipo2;
    }

    public void setEquipo2(Equipo equipo2) {
        this.equipo2 = equipo2;
    }

    public int getGolesEquipo1() {
        return golesEquipo1;
    }

    public void setGolesEquipo1(int golesEquipo1) {
        this.golesEquipo1 = golesEquipo1;
    }


    public int getGolesEquipo2() {
        return golesEquipo2;
    }

    public void setGolesEquipo2(int golesEquipo2) {
        this.golesEquipo2 = golesEquipo2;
    }

    public Resultado Resultado(Equipo equipo)
    {
        if (this.equipo1.getNombreEquipo().equals(equipo.getNombreEquipo()))
        {
            if (golesEquipo1 > golesEquipo2)
            {
                return Resultado.GANADOR;
            }
            else if (golesEquipo1 == golesEquipo2)
            {
                return Resultado.EMPATE;
            }
            else
            {
                return Resultado.PERDEDOR;
            }
        }

        if (this.equipo2.getNombreEquipo().equals(equipo.getNombreEquipo()))
        {
            if (golesEquipo2 > golesEquipo1)
            {
                return Resultado.GANADOR;
            }
            else if (golesEquipo1 == golesEquipo2)
            {
                return Resultado.EMPATE;
            }
            else

                return Resultado.PERDEDOR;
        }
        return null;
    }



}

