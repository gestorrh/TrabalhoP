package ufc.npi.proficiencia.model.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Papel implements GrantedAuthority {
    COLABORADOR("colaborador"), GESTOR("gestor"), MEDICO("medico");
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
