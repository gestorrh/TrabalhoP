package ufc.npi.proficiencia.model.enums;

public enum StatusExame {


    FINALIZADO("Finalizado"), ANDAMENTO("Andamento");

    private String descricao;

    StatusExame(String valor) {
        descricao = valor;
    }

    public String getDescricao() {
        return descricao;
    }

}