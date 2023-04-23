package fixture;
import java.util.ArrayList;
import java.util.List;

public class Ronda {

    private int numero;
    private List<Partido> partidos = new ArrayList<>();

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public List<Partido> getPartidos() {
        return partidos;
    }

    public void setPartidos(List<Partido> partidos) {
        this.partidos = partidos;
    }

    public void addPartido(Partido partidos) {
        this.partidos.add(partidos);
    }

    public void vaciarPartidos() {
        this.partidos.clear();
    }
}
