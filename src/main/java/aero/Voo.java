package aero;

/**
 *
 * @author luis
 */
import dao.AviaoDAO;
import dao.ReservaDAO;
import java.util.List;

public class Voo {

    private int codigo, codAviao;
    private String origem, destino, dataSaida, dataChegada;
    private List<Reserva> reservas;

    public Voo(int codigo, int codaviao, String origem, String destino, String dataSaida, String dataChegada) {
        this.codigo = codigo;
        this.origem = origem;
        this.dataSaida = dataSaida;
        this.dataChegada = dataChegada;
        this.destino = destino;
        ReservaDAO rdao = new ReservaDAO();
        reservas = rdao.findAll();
    }

    public Aviao getAviao() {
        AviaoDAO avdao = new AviaoDAO();
        Aviao aviao;
        aviao = avdao.findById(getCodAviao());
        return aviao;
    }

    public boolean setReserva(Reserva reserva) {
        getReservas().add(reserva);
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
    public List<Reserva> getReservas() {
        return reservas;
    }

    /*
     * quantidade de assentos livres
     * retorna 0 ou menor que zero se não existem mais assentos disponiveis
     */
    public int qtdAssLivres() {
        AviaoDAO avdao = new AviaoDAO();
        Aviao aviao;
        aviao = avdao.findById(getCodAviao());
        return aviao.qtdAssentos() - getReservas().size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Codigo: ").append(this.getCodigo())
                .append("Origem: ").append(this.getOrigem())
                .append(" Destino: ").append(this.getDestino())
                .append(" Saida: ").append(this.getDataSaida())
                .append(" Chegada: ").append(this.getDataChegada());
        return sb.toString();
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
    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }

}
