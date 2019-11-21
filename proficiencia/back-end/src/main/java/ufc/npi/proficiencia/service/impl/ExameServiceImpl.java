package ufc.npi.proficiencia.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ufc.npi.proficiencia.exception.ProficienciaException;
import ufc.npi.proficiencia.model.Exame;
import ufc.npi.proficiencia.model.Inscricao;
import ufc.npi.proficiencia.model.enums.Fase;
import ufc.npi.proficiencia.model.enums.StatusAvaliacao;
import ufc.npi.proficiencia.repository.ExameRepository;
import ufc.npi.proficiencia.repository.InscricaoRepository;
import ufc.npi.proficiencia.service.ExameService;
import ufc.npi.proficiencia.util.Messages;

import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;
import java.util.List;
import java.util.Map;

@Service
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class ExameServiceImpl implements ExameService {

    @Autowired
    private ExameRepository exameRepository;

    @Autowired
    private InscricaoRepository inscricaoRepository;

    @Override
    public Map<String, Object> buscarExamePorId(Integer exameId) {
        return exameRepository.getExameById(exameId);
    }

    @Override
    public List<Map<String, Object>> buscarExamesCandidatoNaoInscrito(Integer candidatoId) {
        return this.exameRepository.findAllByFaseInscricaoCandidatoNaoInscrito(candidatoId, LocalDateTime.now());
    }

    @Override
    public List<Map<String, Object>> buscarExames() {
        return this.exameRepository.findAllCoordenadorView();
    }


    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Exame cadastrarExame(Exame exame) throws ProficienciaException {

        exame.setFase(Fase.INSCRICAO);

        validarDatas(exame);

        return exameRepository.save(exame);
    }

    private void validarDatas(Exame exame) throws ProficienciaException {
        if ((exame.getTerminoInscricoes() != null && exame.getInicioInscricoes() != null && exame.getTerminoInscricoes().isBefore(exame.getInicioInscricoes()))
                || (exame.getDataHoraProva() != null && exame.getTerminoInscricoes() != null && exame.getDataHoraProva().isBefore(exame.getTerminoInscricoes()))
                || (exame.getDataResultado() != null && exame.getDataHoraProva() != null && exame.getDataResultado().isBefore(exame.getDataHoraProva().toLocalDate()))
                || (exame.getInicioReavaliacao() != null && exame.getDataResultado() != null && exame.getInicioReavaliacao().isBefore(exame.getDataResultado().atStartOfDay()))
                || (exame.getTerminoReavaliacao() != null && exame.getInicioReavaliacao() != null && exame.getTerminoReavaliacao().isBefore(exame.getInicioReavaliacao()))) {
            throw new ProficienciaException(Messages.ERRO_PRECEDENCIA_DATAS_EXAME);
        }
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void atualizarExame(Exame exame) throws ProficienciaException {

        Exame exameBd = exameRepository.getOne(exame.getId());
        if(exameBd.getFase().equals(Fase.FINALIZADO)) {
            throw new ProficienciaException(Messages.ERRO_EXAME_FINALIZADO, HttpStatus.LOCKED);
        }

        Map<String, Object> map = inscricaoRepository.findQuantidadeInscricoesAndMaxInscricaoDataAndMinInscricaoDataByExameId(exame.getId());

        validarDatas(exame);

        exameBd.setTitulo(exame.getTitulo());
        exameBd.setDescricao(exame.getDescricao());

        if (exameBd.getFase().compareTo(Fase.INSCRICAO) < 1) {
            if ((Long) map.get("quantidadeInscricoes") > 0 && ((exame.getInicioInscricoes() != null && exame.getInicioInscricoes().isAfter((ChronoLocalDateTime) map.get("menorData"))) || (exame.getTerminoInscricoes() != null && exame.getTerminoInscricoes().isBefore((ChronoLocalDateTime) map.get("maiorData"))))) {
                throw new ProficienciaException(Messages.ERRO_CONFLITO_PERIODO_INSCRICOES);
            }

            exameBd.setInicioInscricoes(exame.getInicioInscricoes());
            exameBd.setTerminoInscricoes(exame.getTerminoInscricoes());
        }

        if(exameBd.getFase().compareTo(Fase.SELECAO) < 1) {
            if(exame.getNumeroVagas() < (Long) map.get("quantidadeInscricoes")) {
                throw new ProficienciaException(Messages.ERRO_QUANTIDADE_DE_VAGAS_INSUFUCIENTE);
            }

            exameBd.setNumeroVagas(exame.getNumeroVagas());
        }

        if(exameBd.getFase().compareTo(Fase.AVALIACAO) < 1) {

            exameBd.setLocalProva(exame.getLocalProva());
            exameBd.setDataHoraProva(exame.getDataHoraProva());
            exameBd.setDataResultado(exame.getDataResultado());
            exameBd.setInicioReavaliacao(exame.getInicioReavaliacao());
            exameBd.setTerminoReavaliacao(exame.getTerminoReavaliacao());
        }

        exameRepository.save(exameBd);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deleteExame(Integer id) throws ProficienciaException {
        List<Inscricao> inscricoes = this.inscricaoRepository.findByExameId(id);
        Exame exame = this.exameRepository.getOne(id);

        if (!exame.getFase().equals(Fase.INSCRICAO) || !inscricoes.isEmpty()) {
            throw new ProficienciaException(Messages.ERRO_EXAME_NAO_PODE_SER_EXCLUIDO);
        }

        this.inscricaoRepository.deleteAll(inscricoes);
        this.exameRepository.deleteById(id);
    }
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Exame encerrarFaseInscricao(Integer id) throws ProficienciaException {
        Exame exameIn = this.exameRepository.getOne(id);
        LocalDateTime agora = LocalDateTime.now();

        if (exameIn.inscricoesIsEmpty()) {
            throw new ProficienciaException(Messages.ERRO_ALTERAR_FASE_EXAME);
        }

        if (exameIn.getFase().equals(Fase.INSCRICAO)){

            if (agora.isBefore(exameIn.getTerminoInscricoes())) {
                exameIn.setTerminoInscricoes(agora);
            }

            exameIn.setFase(Fase.SELECAO);

            } else {
                throw new ProficienciaException(Messages.ERRO_FASE_EXAME);
            }
            atualizarExame(exameIn);
        return this.exameRepository.save(exameIn);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Exame encerrarFaseSelecao(Exame exame) throws ProficienciaException {
        Exame exameIn = this.exameRepository.getOne(exame.getId());

        if(exame.getDataHoraProva() != null) {
            exameIn.setDataHoraProva(exame.getDataHoraProva());
        }

        for (Inscricao insc: exameIn.getInscricoes()) {
            insc.setStatusAvaliacao(StatusAvaliacao.EM_AVALIACAO);
        }

        if (exameIn.getFase().equals(Fase.SELECAO)) {
            exameIn.setFase(Fase.AVALIACAO);
        } else {
            throw new ProficienciaException(Messages.ERRO_FASE_EXAME);
        }
        atualizarExame(exameIn);
        return this.exameRepository.save(exameIn);
    }

}
