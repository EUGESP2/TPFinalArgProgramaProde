package fixture;

public class Pronostico {
    private Equipo equipoGanador;
    private Resultado resultado;
    private Persona persona;
    private Partido partido;

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }




    public Pronostico(Equipo equipoGanador, Resultado resultado, Persona persona, Partido partido) {
        this.persona = persona;
        this.partido = partido;
        this.equipoGanador = equipoGanador;
        this.resultado = resultado;
//        IdPronostico = idPronostico;
    }

    public Partido getPartido() {
        return partido;
    }

    public void setPartido(Partido partido) {
        this.partido = partido;
    }

    public Equipo
    getEquipoGanador() {
        return equipoGanador;
    }

    public void setEquipoGanador(Equipo equipoGanador) {
        this.equipoGanador = equipoGanador;
    }

    public Resultado getResultado() {
        return resultado;
    }

    public void setResultado(Resultado resultado) {
        this.resultado = resultado;
    }



    public int Puntos() {


        if (partido.Resultado(getEquipoGanador()).equals(getResultado()))
        {
            return 1;
        }
        else
        {

            return 0;
        }
    }

}
