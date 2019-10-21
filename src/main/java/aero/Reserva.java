package aero;

/**
 *
 * @author luis
 */

public class Reserva {
    
    private String cpf;
    private int codVoo, codAssento, codReserva;

    public Reserva(int codreserva, int codVoo, int codAssento, String cpf) {
        this.codReserva = codreserva;
        this.codAssento = codAssento;
        this.codVoo = codVoo;
        this.cpf = cpf;
        //função que gera codigo da reserva
        //codReserva = (codVoo * (1/codAssento)) * (codVoo) + codAssento;
    }

    public Reserva(int codVoo, int codAssento, String cpf) {
        this.codAssento = codAssento;
        this.codVoo = codVoo;
        this.cpf = cpf;
        //função que gera codigo da reserva
        //codReserva = (codVoo * (1/codAssento)) * (codVoo) + codAssento;
    }
    
    /**
     * @return the codReserva
     */
    public int getCodReserva() {
        return codReserva;
    }

    public String getCpf() {
            return cpf;
    }

    public int getCodVoo() {
            return codVoo;
    }
    /*
        retorna codigo do assento
    */
    public int getCodAssento() {
            return codAssento;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("CPF: ").append(cpf)
                .append("\tVoo: ").append(codVoo)
                .append("\tAssento: ").append(codAssento)
                .append("\tCodigo: ").append(getCodReserva());
        return sb.toString();
    }
}
