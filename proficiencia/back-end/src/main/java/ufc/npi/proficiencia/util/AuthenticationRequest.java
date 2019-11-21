package ufc.npi.proficiencia.util;

import java.io.Serializable;


public class AuthenticationRequest implements Serializable {

    private String cpf;
    private String password;

    public AuthenticationRequest() {
    }

    public AuthenticationRequest(String cpf, String password) {
        this.cpf = cpf;
        this.password = password;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
