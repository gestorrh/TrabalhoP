package ufc.npi.proficiencia.model;

import ufc.npi.proficiencia.model.enums.StatusAvaliacao;
import ufc.npi.proficiencia.model.enums.StatusInscricao;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Inscricao {

    @Id
    @SequenceGenerator(name = "inscricao_id_seq", sequenceName = "inscricao_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "inscricao_id_seq")
    private Integer id;

    @Enumerated(EnumType.STRING)
    private StatusInscricao statusInscricao;

    private String observacaoStatusInscricao;

    @OneToMany(cascade = CascadeType.MERGE)
    private List<Arquivo> arquivos;

    private String programaPos;
    private String areaConcentracao;
    private String linhaPesquisa;
    private String instituicao;
    private Integer prioridade;
    private String formacao;

    @ManyToOne
    private Usuario usuario;

    @ManyToOne
    private Exame exame;

    @ManyToOne
    private LocalProva localProva;

    private Double notaAvaliacao;
    private Double notaReavaliacao;
    private Boolean reavaliacaoSolicitada;

    @Enumerated(EnumType.STRING)
    private StatusAvaliacao statusAvaliacao;

    private LocalDateTime dataHoraInscricao;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public StatusInscricao getStatusInscricao() {
        return statusInscricao;
    }

    public void setStatusInscricao(StatusInscricao statusInscricao) {
        this.statusInscricao = statusInscricao;
    }

    public List<Arquivo> getArquivos() {
        return arquivos;
    }

    public void setArquivos(List<Arquivo> arquivos) {
        this.arquivos = arquivos;
    }

    public String getProgramaPos() {
        return programaPos;
    }

    public void setProgramaPos(String programaPos) {
        this.programaPos = programaPos;
    }

    public String getAreaConcentracao() {
        return areaConcentracao;
    }

    public void setAreaConcentracao(String areaConcentracao) {
        this.areaConcentracao = areaConcentracao;
    }

    public String getLinhaPesquisa() {
        return linhaPesquisa;
    }

    public void setLinhaPesquisa(String linhaPesquisa) {
        this.linhaPesquisa = linhaPesquisa;
    }

    public String getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(String instituicao) {
        this.instituicao = instituicao;
    }

    public String getFormacao() {
        return formacao;
    }

    public void setFormacao(String formacao) {
        this.formacao = formacao;
    }

    public Integer getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(Integer prioridade) {
        this.prioridade = prioridade;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Exame getExame() {
        return exame;
    }

    public void setExame(Exame exame) {
        this.exame = exame;
    }

    public Double getNotaAvaliacao() {
        return notaAvaliacao;
    }

    public void setNotaAvaliacao(Double notaAvaliacao) {
        this.notaAvaliacao = notaAvaliacao;
    }

    public Double getNotaReavaliacao() {
        return notaReavaliacao;
    }

    public void setNotaReavaliacao(Double notaReavaliacao) {
        this.notaReavaliacao = notaReavaliacao;
    }

    public Boolean getReavaliacaoSolicitada() {
        return reavaliacaoSolicitada;
    }

    public void setReavaliacaoSolicitada(Boolean reavaliacaoSolicitada) {
        this.reavaliacaoSolicitada = reavaliacaoSolicitada;
    }

    public StatusAvaliacao getStatusAvaliacao() {
        return statusAvaliacao;
    }

    public void setStatusAvaliacao(StatusAvaliacao statusAvaliacao) {
        this.statusAvaliacao = statusAvaliacao;
    }

    public LocalDateTime getDataHoraInscricao() {
        return dataHoraInscricao;
    }

    public String getObservacaoStatusInscricao() {
        return observacaoStatusInscricao;
    }

    public void setObservacaoStatusInscricao(String observacaoStatusInscricao) {
        this.observacaoStatusInscricao = observacaoStatusInscricao;
    }

    public void setDataHoraInscricao(LocalDateTime dataHoraInscricao) {
        this.dataHoraInscricao = dataHoraInscricao;
    }

    public LocalProva getLocalProva() {
        return localProva;
    }

    public void setLocalProva(LocalProva localProva) {
        this.localProva = localProva;
    }

}
