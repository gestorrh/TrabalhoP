package ufc.npi.proficiencia.model;


import ufc.npi.proficiencia.model.enums.StatusExame;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Exame {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "colaborador_id")
    private Usuario colaborador;

    @ManyToOne
    @JoinColumn(name = "medico_id")
    private Usuario medico;

    private String nomeExame;
    private LocalDate dataExame;
    private String descricao;
    private int crmMedico;
    private String cid;
    private String avaliacaoMedica;
    private LocalDate diaProximoExame;
    private int diasAfastamento;

    @Enumerated(EnumType.STRING)
    private StatusExame statusExame;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomeExame() {
        return nomeExame;
    }

    public void setNomeExame(String nomeExame) {
        this.nomeExame = nomeExame;
    }

    public LocalDate getDataExame() {
        return dataExame;
    }

    public void setDataExame(LocalDate dataExame) {
        this.dataExame = dataExame;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getAvaliacaoMedica() {
        return avaliacaoMedica;
    }

    public void setAvaliacaoMedica(String avaliacaoMedica) {
        this.avaliacaoMedica = avaliacaoMedica;
    }

    public int getCrmMedico() {
        return crmMedico;
    }

    public void setCrmMedico(int crmMedico) {
        this.crmMedico = crmMedico;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public LocalDate getDiaProximoExame() {
        return diaProximoExame;
    }

    public void setDiaProximoExame(LocalDate diaProximoExame) {
        this.diaProximoExame = diaProximoExame;
    }

    public int getDiasAfastamento() {
        return diasAfastamento;
    }

    public void setDiasAfastamento(int diasAfastamento) {
        this.diasAfastamento = diasAfastamento;
    }

    public StatusExame getStatusExame() {
        return statusExame;
    }

    public void setStatusExame(StatusExame statusExame) {
        this.statusExame = statusExame;
    }

    public Usuario getColaborador() {
        return colaborador;
    }

    public void setColaborador(Usuario colaborador) {
        this.colaborador = colaborador;
    }

    public Usuario getMedico() {
        return medico;
    }

    public void setMedico(Usuario medico) {
        this.medico = medico;
    }


}
