package ufc.npi.proficiencia.model;


import javax.persistence.*;

@Entity
public class Arquivo {

    @Id
    @SequenceGenerator(name = "arquivo_id_seq", sequenceName = "arquivo_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "arquivo_id_seq")
    private Integer id;
    private String nome;
    @Column(columnDefinition = "text")
    private String observacao;


    public Arquivo( String objectName, String observacao) {
        this.nome = objectName;
        this.observacao = observacao;
    }

    public Arquivo(Integer idArquivo, String observacao, String objectName){
        this.id = idArquivo;
        this.nome = objectName;
        this.observacao = observacao;
    }


    public Arquivo(){

    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
}
