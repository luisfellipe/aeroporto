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
        aviao = avdao.findById(codAviao);
        return aviao;
    }

    public boolean setReserva(Reserva reserva) {
        reservas.add(reserva);
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
        aviao = avdao.findById(codAviao);
        return aviao.qtdAssentos() - reservas.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Codigo: ").append(this.codigo)
                .append("Origem: ").append(this.origem)
                .append(" Destino: ").append(this.destino)
                .append(" Saida: ").append(this.dataSaida)
                .append(" Chegada: ").append(this.dataChegada);
        return sb.toString();
    }

}
