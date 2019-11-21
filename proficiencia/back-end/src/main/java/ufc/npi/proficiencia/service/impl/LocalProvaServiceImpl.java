package ufc.npi.proficiencia.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ufc.npi.proficiencia.exception.ProficienciaException;
import ufc.npi.proficiencia.model.Exame;
import ufc.npi.proficiencia.model.Inscricao;
import ufc.npi.proficiencia.model.LocalProva;
import ufc.npi.proficiencia.model.enums.Fase;

import ufc.npi.proficiencia.repository.InscricaoRepository;

import ufc.npi.proficiencia.repository.ExameRepository;

import ufc.npi.proficiencia.repository.LocalProvaRepository;
import ufc.npi.proficiencia.service.LocalProvaService;
import ufc.npi.proficiencia.util.Messages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class LocalProvaServiceImpl implements LocalProvaService {

    @Autowired
    private LocalProvaRepository localProvaRepository;
    @Autowired
    private InscricaoRepository inscricaoRepository;

    @Autowired
    private ExameRepository exameRepository;

    @Override
    public List<Map<String, Object>> findLocaisByIdExame(Integer exameId) {
        return this.localProvaRepository.findLocaisByIdExame(exameId);
    }

    @Override
    public Map<String, Object> findLocalProvaByExameId(Integer localprovaId) {

        Map map = new HashMap<>();
        map.put("localProva", localProvaRepository.findLocalProvaByExameId(localprovaId));
        map.put("exame", localProvaRepository.findInformacoesExameLocalProva(localprovaId));
        map.put("candidatos", localProvaRepository.findCandidatosAlocados(localprovaId));
        return map;
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void moverCandidatoLocalProva(Integer inscricaoId, Integer localProvaId) throws ProficienciaException {

        Inscricao inscricao = inscricaoRepository.getOne(inscricaoId);
        LocalProva localProva = localProvaRepository.getOne(localProvaId);
        localProva.getNome();
        int capacidade = localProva.getCapacidade();

        int qtdCandidatosAlocados = localProvaRepository.countCandidatosAlocados(localProva.getId());

        if(inscricao.getLocalProva().getId().equals(localProva.getId())) throw new ProficienciaException(Messages.ERRO_MESMO_LOCAL_DE_PROVA);

        if(qtdCandidatosAlocados < capacidade){

            inscricao.setLocalProva(localProva);
            inscricaoRepository.save(inscricao);

        }else{

          throw new ProficienciaException(Messages.ERRO_MOVER_CANDIDATO_CAPACIDADE_MAIOR_QUE_NUMERO_DE_VAGAS);
   }

        }


    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Map<String, Object> salvarLocalProva(LocalProva localProva, Exame exame) throws ProficienciaException {

        Integer capacidadeLocal = localProvaRepository.countCapacidadeLocais(exame.getId());

        if (capacidadeLocal == null) {
            capacidadeLocal = 0;
        }

        if (localProva.getNome() == null || localProva.getCapacidade() == null || localProva.getCapacidade() < 0) {
            throw new ProficienciaException(Messages.ERRO_LOCAL_OU_CAPACIDADE_INVALIDA);
        }
        if (exame.getFase().equals(Fase.FINALIZADO)) {
            throw new ProficienciaException(Messages.ERRO_FASE_INVALIDA);
        }
        if ((capacidadeLocal + localProva.getCapacidade()) > exame.getNumeroVagas()) {
            throw new ProficienciaException(Messages.ERRO_CAPACIDADE_MAIOR_QUE_NUMERO_DE_VAGAS);
        }

        localProva.setExame(exame);
        localProva = localProvaRepository.save(localProva);
        return localProvaRepository.findLocalById(localProva.getId());
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deleteLocalProva(Integer id) throws ProficienciaException {

        Exame exame = this.localProvaRepository.buscarExame(id);
        Integer qtdCandidatosAlocados = localProvaRepository.countCandidatosAlocados(id);

        if (qtdCandidatosAlocados > 0 || exame.getFase().equals(Fase.FINALIZADO)) {
            throw new ProficienciaException(Messages.ERRO_LOCAL_NAO_PODE_SER_EXCLUIDO);
        }

        this.localProvaRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Map<String, Object> atualizarLocal(LocalProva local) throws ProficienciaException {

        Exame exame = exameRepository.getOne(local.getExame().getId());
        Integer capacidadeLocal = localProvaRepository.countCapacidadeLocais(exame.getId());

        LocalProva localEdit = localProvaRepository.getOne(local.getId());

        if (exame.getFase().equals(Fase.FINALIZADO)) {
            throw new ProficienciaException(Messages.ERRO_FASE_INVALIDA);
        }

        if (localEdit.getNome() == null || localEdit.getCapacidade() == null || local.getCapacidade() < 0) {
            throw new ProficienciaException(Messages.ERRO_LOCAL_OU_CAPACIDADE_INVALIDA);
        }

        if (((capacidadeLocal - localEdit.getCapacidade()) + local.getCapacidade()) > exame.getNumeroVagas()) {
            throw new ProficienciaException(Messages.ERRO_EDITAR_LOCAL_PROVA);
        }

        localEdit.setCapacidade(local.getCapacidade());
        localEdit.setNome(local.getNome());

        localProvaRepository.save(localEdit);
        return localProvaRepository.findLocalById(localEdit.getId());

    }
}


