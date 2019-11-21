package ufc.npi.proficiencia.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import ufc.npi.proficiencia.exception.ProficienciaException;
import ufc.npi.proficiencia.model.Exame;
import ufc.npi.proficiencia.model.Inscricao;
import ufc.npi.proficiencia.model.Usuario;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface InscricaoController {

    @ApiOperation(
            value = "Análise da inscrição",
            notes = "Este controller recebe uma Inscrição e um Exame, onde a inscrição poderá ser deferida" +
                    "ou indeferida pelo coordenador"
    )
    ResponseEntity analisarInscricao(Inscricao inscricao, Exame exame) throws ProficienciaException;

    @ApiOperation(
            value = "Adicionar arquivo em uma inscrição",
            notes = "Este controller recebe um id referente a inscrição e adicionar um arquivo a essa inscricao " +
                    "relaciona o id do arquivo com o id da inscrição em uma tabela intermediária"
    )
    ResponseEntity adicionarArquivo(MultipartFile file, String observacao, Integer idInscricao) throws IOException, ProficienciaException;

    @ApiOperation(
            value = "Remover arquivo de uma inscrição",
            notes = "Este controller recebe um id referente ao arquivo e um id referente a inscrição " +
                    "e remove o relacionamento do arquivo na tabela intermediária 'inscricao_arquivos' " +
                    "em seguida apaga o arquivo do servidor e da tabela 'arquivo' "

    )
    ResponseEntity deletarArquivo(Integer idInscricao,Integer idArquivo) throws ProficienciaException;

    @ApiOperation(
            value = "Cadastra um usuário em um exame, além de atualizar informações do usuário",
            notes = "Este controller é responsável por atualizar as informações do usuário no sistema para depois inscrevê-lo em um exame"
    )
    ResponseEntity<Integer> realizarInscricao(Map<String, Object> inscricao, Usuario usuarioAut) throws ProficienciaException;

    @ApiOperation(
            value = "Listar as inscrições do candidato",
            notes = "Este controller é responsável por listar todas as inscrições realizadas pelo candidato"
    )
    ResponseEntity<List<Map<String, Object>>> listarInscricoesDoCandidato(Usuario usuario);

    @ApiOperation(
            value = "Listar as inscrições do candidato por ID",
            notes = "Este controller é responsável por listar todas as inscrições realizadas pelo candidato ao ser passado o ID do candidato"
    )
    ResponseEntity<List<Map<String, Object>>> listarInscricoesDoCandidatoPorId(Integer candidatoId);

    @ApiOperation(
            value = "Visualizar informacões do exame que o candidato está inscrito",
            notes = "Este controller é responsável por exibir as informações de um exame que o candidato está inscrito, ao ser passado o id da inscrição"
    )
    ResponseEntity<Map<String, Object>> informacaoInscricaoCandidato(Integer inscricaoId);

    @ApiOperation(
            value = "Listar detalhes do exame de um candidato",
            notes = "Este controller é responsável por detalhar o exame pertecente a um candidato na visão do coordenador"
    )
    ResponseEntity<Map<String, Object>> informacaoInscricaoCoordenador(Integer inscricaoId);

    @ApiOperation(
            value = "Listar as inscrições feitas em um determinado exame",
            notes = "Este controller é responsável por exibir as inscrições feitas em um exame, ao ser passado o id do exame"
    )
    ResponseEntity listarInscricoesPorExame(Integer id);
}
