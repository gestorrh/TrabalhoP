package ufc.npi.proficiencia.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ufc.npi.proficiencia.exception.ProficienciaException;
import ufc.npi.proficiencia.model.Exame;
import ufc.npi.proficiencia.model.enums.StatusExame;
import ufc.npi.proficiencia.repository.ExameRepository;
import ufc.npi.proficiencia.service.ExameService;
import ufc.npi.proficiencia.util.BeanUtil;
import ufc.npi.proficiencia.util.Messages;

import java.util.List;
import java.util.Map;

@Service
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class ExameServiceImpl implements ExameService {

    @Autowired
    private ExameRepository exameRepositorory;

    @Override
    public List<Map<String, Object>> buscarExameMedico(Integer medicoId) {
        return exameRepositorory.findAllExamesMedicoView(medicoId);
    }

    @Override
    public List<Map<String, Object>> buscarExameColaborador(Integer colaboradorId) {
        return exameRepositorory.findAllExamesColaboradorView(colaboradorId);
    }

    @Override
    public List<Map<String, Object>> findAll() {
        return exameRepositorory.findAllExamesView();
    }


//    @Override
//    public Map<String, Object> buscarExame(Integer id) {
//        return exameRepositorory.dadosExame(id);
//    }

//    @Override
//    public List<Map<String, Object>> buscarExames() {
//        return this.exameRepositorory.findAllExamesView();
//    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void cadastrarExame(Exame exame) throws ProficienciaException {

        exame.setStatusExame(StatusExame.ANDAMENTO);

        exameRepositorory.save(exame);
    }

    @Override
    public void deleteExame(Integer id, Integer userId) throws ProficienciaException {
        Exame usuario = this.exameRepositorory.findById(id).get();
        if (usuario.getId().equals(userId)) {
            throw new ProficienciaException(Messages.ERRO_AUTOEXCLUSAO);
        /*} else if (!(usuario.getInscricoes().isEmpty())) {
            throw new ProficienciaException(Messages.ERRO_USUARIO_INSCRICOES_ATIVAS);*/
        } else {
            this.exameRepositorory.deleteById(id);
        }

    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void atualizarExame(Exame exame) {
        Exame exameBd = exameRepositorory.getOne(exame.getId());
        BeanUtils.copyProperties(exame, exameBd, BeanUtil.getNullPropertyNames(exame));
        exameRepositorory.save(exameBd);

    }

}
