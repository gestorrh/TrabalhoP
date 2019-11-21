package ufc.npi.proficiencia.model.enums;

public enum StatusInscricao {
    INSCRITO("Inscrito"), DEFERIDO("Deferido"), INDEFERIDO("Indeferido");
    private String descricao;

    StatusInscricao(String valor) {
        descricao = valor;
    }

    public String getDescricao() {
        return descricao;
    }

}
