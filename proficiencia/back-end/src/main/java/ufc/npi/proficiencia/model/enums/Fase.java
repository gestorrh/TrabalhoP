package ufc.npi.proficiencia.model.enums;

public enum Fase {


    INSCRICAO("Inscrição"), SELECAO("Seleção"), AVALIACAO("Avaliação"), FINALIZADO("Finalizado");

    private String descricao;

    Fase(String valor) {
        descricao = valor;
    }

    public String getDescricao() {
        return descricao;
    }

}
