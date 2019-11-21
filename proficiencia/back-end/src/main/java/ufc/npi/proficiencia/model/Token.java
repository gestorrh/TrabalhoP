package ufc.npi.proficiencia.model;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Entity
public class Token {

    @Id
    @SequenceGenerator(name = "token_id_seq", sequenceName = "token_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "token_id_seq")
    private Integer id;

    @ManyToOne
    private Usuario usuario;

    private String hash;
    private Date validade;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public Date getValidade() {
        return validade;
    }

    public void setValidade(Date validade) {
        this.validade = validade;
    }

    public void setValidade(int minutos) {
        Calendar now = Calendar.getInstance();
        now.add(Calendar.MINUTE, minutos);
        this.validade = now.getTime();
    }

    public boolean isExpired() {
        return new Date().after(this.validade);
    }

}
