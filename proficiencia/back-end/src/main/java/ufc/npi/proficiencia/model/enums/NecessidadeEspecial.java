package ufc.npi.proficiencia.model.enums;

public enum NecessidadeEspecial {
    FISICA("Física"), AUDITIVA("Auditiva"), VISUAL("Visual"), MENTAL("Mental"), MULTIPLA("Multipla");
    private String descricao;

    private NecessidadeEspecial(String valor) {
        descricao = valor;
    }

    public String getDescricao() {
        return descricao;
    }
}
