/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aero;

/**
 *
 * @author luis
 */
import java.util.ArrayList;

public class Voo {

    private int codigo;
    private String origem, destino, data, saida, chegada;
    private Aviao aviao;
    private ArrayList<Reserva> reservas;

    public Voo(int codigo, String origem, String destino, String data, String saida, String chegada) {
        this.codigo = codigo;
        this.origem = origem;
        this.data = data;
        this.saida = saida;
        this.chegada = chegada;
        this.destino = destino;
    }

    public void setAviao(Aviao aviao) {
        this.aviao = aviao;
    }

    public Aviao getAviao() {
        return aviao;
    }

    public boolean setReserva(Reserva reserva) {

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
     * @return the data
     */
    public String getData() {
        return data;
    }

    /**
     * @return the saida
     */
    public String getSaida() {
        return saida;
    }

    /**
     * @return the chegada
     */
    public String getChegada() {
        return chegada;
    }

    /**
     * @return the reservas
     */
    public ArrayList<Reserva> getReservas() {
        return reservas;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Codigo: ").append(this.codigo)
                .append("Origem: ").append(this.origem)
                .append(" Destino: ").append(this.destino)
                .append(" Data: ").append(this.data)
                .append(" Saida: ").append(this.saida)
                .append(" Chegada: ").append(this.chegada);
        return sb.toString();
    }

}
