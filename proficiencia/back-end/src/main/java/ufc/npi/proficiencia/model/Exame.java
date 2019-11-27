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
    private Integer idColaborador;

    private String nomeExame;
    private LocalDate dataExame;
    private String Descricao;
    private int crmMedico;
    private String cid;
    private String AvaliacaoMedica;
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

    public Integer getIdColaborador() {
        return idColaborador;
    }

    public void setIdColaborador(Integer idColaborador) {
        this.idColaborador = idColaborador;
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
        return Descricao;
    }

    public void setDescricao(String descricao) {
        Descricao = descricao;
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

    public String getAvaliacaoMedica() {
        return AvaliacaoMedica;
    }

    public void setAvaliacaoMedica(String avaliacaoMedica) {
        AvaliacaoMedica = avaliacaoMedica;
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

}
