package aero;

/**
 *
 * @author luis
 */
import dao.AviaoDAO;
import dao.ReservaDAO;
import java.util.ArrayList;

public class Voo {

    private int codigo, codAviao;
    private String origem, destino, dataSaida, dataChegada;
    private ArrayList<Reserva> reservas;

    public Voo(int codigo, int codaviao, String origem, String destino, String dataSaida, String dataChegada, ArrayList<Reserva> reservas) {
        this.codigo = codigo;
        this.codAviao = codaviao;
        this.origem = origem;
        this.dataSaida = dataSaida;
        this.dataChegada = dataChegada;
        this.destino = destino;
        this.reservas = reservas;
    }
    
    public Voo(int codaviao, String origem, String destino, String dataSaida, String dataChegada) {
        this.codAviao = codaviao;
        this.origem = origem;
        this.dataSaida = dataSaida;
        this.dataChegada = dataChegada;
        this.destino = destino;
        this.reservas = new ArrayList<>();
    }

    public Aviao getAviao() {
        AviaoDAO avdao = new AviaoDAO();
        return avdao.findById(this.codAviao);
    }

    public boolean addReserva(Reserva reserva) {
        this.getReservas().add(reserva);
        ReservaDAO rdao = new ReservaDAO();
        rdao.insert(reserva);
        return true;
    }

    /**
     * @return the codigo
     */
    public int getCodigo() {
        return codigo;
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
        return reservas;
    }

    /*
     * quantidade de assentos livres
     * retorna 0 ou menor que zero se não existem mais assentos disponiveis
     */
    public int qtdAssLivres() {
        AviaoDAO avdao = new AviaoDAO();
        Aviao aviao = avdao.findById(this.getCodAviao());
        return aviao.qtdAssentos() - this.getReservas().size();
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
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

    /**
     * @param reservas the reservas to set
     */
    public void setReservas(ArrayList<Reserva> reservas) {
        this.reservas = reservas;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Codigo: ").append(this.getCodigo())
                .append("\tOrigem: ").append(this.getOrigem())
                .append("\tDestino: ").append(this.getDestino())
                .append("\tSaida: ").append(this.getDataSaida())
                .append("\tChegada: ").append(this.getDataChegada());
        return sb.toString();
    }
}
