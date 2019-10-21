package aero;

/**
 *
 * @author luis
 */
import dao.AssentoDAO;
import java.util.ArrayList;

public class Aviao {

    private int codigo, qtdAssentos;
    private String marca, modelo;
    private ArrayList<Assento> assentos;

    public Aviao(int codigo, String marca, String modelo, int qtdAssentos) {
        this.codigo = codigo;
        this.marca = marca;
        this.modelo = modelo;
        this.qtdAssentos = qtdAssentos;
    }

    public Aviao(int codigo, String marca, String modelo, int qtdAssentos, ArrayList<Assento> assentos) {
        this.codigo = codigo;
        this.marca = marca;
        this.modelo = modelo;
        this.qtdAssentos = qtdAssentos;
        this.assentos = assentos;
    }

    public int getCod() {
        return codigo;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public int qtdAssentos() {
        return qtdAssentos;
    }

    public Assento getAssentoLivre(int codVoo) {
        ArrayList<Assento> assentos = new AssentoDAO().getAssentosAviao(codigo, codVoo);
        for (Assento a : assentos) {
            if (!a.isReservado()) {
                return a;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Codigo: ").append(codigo)
                .append("\tMarca: ").append(marca)
                .append("\tModelo: ").append(modelo)
                .append("\tAssentos(qtd): ").append(qtdAssentos);
        return sb.toString();
    }
}
