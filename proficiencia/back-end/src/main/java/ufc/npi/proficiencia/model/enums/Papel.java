package ufc.npi.proficiencia.model.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Papel implements GrantedAuthority {
    CANDIDATO("DadosCandidato"), COORDENADOR("Coordenador"), SECRETARIO("Secretario"), EXAMINADOR("Examinador");
    private String descricao;

    Papel(String valor) {
        descricao = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public String getAuthority() {
        return this.toString();
    }

}
