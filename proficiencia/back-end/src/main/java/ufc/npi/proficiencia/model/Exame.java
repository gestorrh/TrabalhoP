package ufc.npi.proficiencia.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import ufc.npi.proficiencia.model.enums.Fase;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Exame {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    private String titulo;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    @NotNull
    private LocalDateTime inicioInscricoes;

    @NotNull
    private LocalDateTime terminoInscricoes;

    private LocalDateTime dataHoraProva;

    private LocalDate dataResultado;

    private LocalDateTime inicioReavaliacao;

    private LocalDateTime terminoReavaliacao;

    @NotBlank
    private String localProva;

    @NotNull
    @Min(1)
    private Integer numeroVagas;

    @Column(nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private Fase fase;

    @OneToMany (mappedBy = "exame")
    private List<LocalProva> locais;

    @OneToMany(mappedBy = "exame")
    private List<Inscricao> inscricoes;

    @OneToMany(mappedBy = "exame")
    private List<Noticia> noticias;

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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getInicioInscricoes() {
        return inicioInscricoes;
    }

    public void setInicioInscricoes(LocalDateTime inicioInscricoes) {
        this.inicioInscricoes = inicioInscricoes;
    }

    public LocalDateTime getTerminoInscricoes() {
        return terminoInscricoes;
    }

    public void setTerminoInscricoes(LocalDateTime terminoInscricoes) {
        this.terminoInscricoes = terminoInscricoes;
    }

    public LocalDateTime getDataHoraProva() {
        return dataHoraProva;
    }

    public void setDataHoraProva(LocalDateTime dataHoraProva) {
        this.dataHoraProva = dataHoraProva;
    }

    public String getLocalProva() {
        return localProva;
    }

    public void setLocalProva(String localProva) {
        this.localProva = localProva;
    }

    public Fase getFase() {
        return fase;
    }

    public void setFase(Fase fase) {
        this.fase = fase;
    }

    public List<Inscricao> getInscricoes() {
        return inscricoes;
    }

    public void setInscricoes(List<Inscricao> inscricoes) {
        this.inscricoes = inscricoes;
    }

    public List<Noticia> getNoticias() {
        return noticias;
    }

    public void setNoticias(List<Noticia> noticias) {
        this.noticias = noticias;
    }

    public Integer getQuantidadeCandidatos() {
        return this.inscricoes.size();
    }

    public LocalDate getDataResultado() {
        return dataResultado;
    }

    public void setDataResultado(LocalDate dataResultado) {
        this.dataResultado = dataResultado;
    }


    public LocalDateTime getInicioReavaliacao() {
        return inicioReavaliacao;
    }

    public void setInicioReavaliacao(LocalDateTime inicioReavaliacao) {
        this.inicioReavaliacao = inicioReavaliacao;
    }

    public LocalDateTime getTerminoReavaliacao() {
        return terminoReavaliacao;
    }

    public void setTerminoReavaliacao(LocalDateTime terminoReavaliacao) {
        this.terminoReavaliacao = terminoReavaliacao;
    }

    public Integer getNumeroVagas() {
        return numeroVagas;
    }

    public void setNumeroVagas(Integer numeroVagas) {
        this.numeroVagas = numeroVagas;
    }

    public boolean inscricoesIsEmpty() {
        return this.inscricoes.isEmpty();
    }

    public List<LocalProva> getLocais() {
        return locais;
    }

    public void setLocais(List<LocalProva> locais) {
        this.locais = locais;
    }

}
