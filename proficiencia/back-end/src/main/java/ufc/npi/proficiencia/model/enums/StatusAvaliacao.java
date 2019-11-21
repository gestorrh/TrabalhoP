package ufc.npi.proficiencia.model.enums;

public enum StatusAvaliacao {
    EM_AVALIACAO("Em_Avaliação"), APROVADO("Aprovado"), REPROVADO("Reprovado");
    private String descricao;

    StatusAvaliacao(String valor) {
        descricao = valor;
    }

    public String getDescricao() {
        return descricao;
    }
}
