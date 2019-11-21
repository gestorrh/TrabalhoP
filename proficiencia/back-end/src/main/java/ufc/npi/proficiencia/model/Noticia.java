package ufc.npi.proficiencia.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Noticia {

    @Id
    @SequenceGenerator(name = "noticia_id_seq", sequenceName = "noticia_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "noticia_id_seq")
    private Integer id;
    private String titulo;
    private String corpo;
    private Date dataHora;

    @ManyToOne
    private Exame exame;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCorpo() {
        return corpo;
    }

    public void setCorpo(String corpo) {
        this.corpo = corpo;
    }

    public Date getDataHora() {
        return dataHora;
    }

    public void setDataHora(Date dataHora) {
        this.dataHora = dataHora;
    }

    public Exame getExame() {
        return exame;
    }

    public void setExame(Exame exame) {
        this.exame = exame;
    }

}
