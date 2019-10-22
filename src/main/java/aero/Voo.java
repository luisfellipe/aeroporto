package aero;

/**
 *
 * @author luis
 */
import dao.AssentoDAO;
import dao.AviaoDAO;
import dao.ReservaDAO;
import dao.VooDAO;
import java.util.ArrayList;

public class Voo {

    private int codVoo, codAviao;
    private String origem, destino, dataSaida, dataChegada;

    public Voo(int codvoo, int codaviao, String origem, String destino, String dataSaida, String dataChegada) {
        this.codVoo = codvoo;
        this.codAviao = codaviao;
        this.origem = origem;
        this.dataSaida = dataSaida;
        this.dataChegada = dataChegada;
        this.destino = destino;
    }
    

    public Aviao getAviao() {
        return new AviaoDAO().findById(this.codAviao);
    }

    public boolean addReserva(Reserva reserva) {
        this.getReservas().add(reserva);
        ReservaDAO rdao = new ReservaDAO();
        rdao.insert(reserva);
        return true;
    }

    /**
     * @return the codVoo
     */
    public int getCodVoo() {
        return codVoo;
    }

    /**
     * @return the origem
     */
    public String getOrigem() {
        return origem;
    }

    /**
     * @return a data de chegada ao destino
     */
    public String getDataChegada() {
        return dataChegada;
    }

    /**
     * @return the saida do voo
     */
    public String getDataSaida() {
        return dataSaida;
    }

    /**
     * @return the reservas
     */
    public ArrayList<Reserva> getReservas() {
        return new ReservaDAO().findAll(codVoo);
    }

    /*
     * quantidade de assentos livres
     * retorna 0 ou menor que zero se não existem mais assentos disponiveis
     */
    public int qtdAssLivres(int codvoo) {
       
        return new VooDAO().qtdAssentosLivres(codvoo);
    }
    
    public Assento getAssentoLivre() {
        ArrayList<Assento> assentos = new AssentoDAO().getAssentosAviao(codVoo);
        Assento ass = null;
        for (Assento a : assentos) {
            if (!a.isReservado()) {
                ass = a;
            }
        }
        return ass;
    }

    /**
     * @param codVoo the codVoo to set
     */
    public void setCodVoo(int codVoo) {
        this.codVoo = codVoo;
    }

    /**
     * @return the codAviao
     */
    public int getCodAviao() {
        return codAviao;
    }

    /**
     * @param codAviao the codAviao to set
     */
    public void setCodAviao(int codAviao) {
        this.codAviao = codAviao;
    }

    /**
     * @param origem the origem to set
     */
    public void setOrigem(String origem) {
        this.origem = origem;
    }

    /**
     * @return the destino
     */
    public String getDestino() {
        return destino;
    }

    /**
     * @param destino the destino to set
     */
    public void setDestino(String destino) {
        this.destino = destino;
    }

    /**
     * @param dataSaida the dataSaida to set
     */
    public void setDataSaida(String dataSaida) {
        this.dataSaida = dataSaida;
    }

    /**
     * @param dataChegada the dataChegada to set
     */
    public void setDataChegada(String dataChegada) {
        this.dataChegada = dataChegada;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Codigo: ").append(this.getCodVoo())
                .append("\tOrigem: ").append(this.getOrigem())
                .append("\tDestino: ").append(this.getDestino())
                .append("\tSaida: ").append(this.getDataSaida())
                .append("\tChegada: ").append(this.getDataChegada());
        return sb.toString();
    }
}
