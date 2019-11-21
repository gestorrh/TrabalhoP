package ufc.npi.proficiencia.service;

import ufc.npi.proficiencia.exception.ProficienciaException;
import ufc.npi.proficiencia.model.Arquivo;


import java.io.InputStream;
import java.util.List;
import java.util.Map;


public interface ArquivoService {

    Arquivo salvarArquivo(String observacao, String objectName, InputStream stream, long size, String contentType) throws ProficienciaException;

    boolean deletarArquivo(Integer idDocumento) throws ProficienciaException;

    void atualizarArquivo(Integer idDocumento, String observacao, String objectName, InputStream stream, long size, String contentType) throws ProficienciaException;

    Arquivo buscarUmArquivo(Integer id);

    InputStream buscarArquivoDownload(Integer id) throws ProficienciaException;

    List<Map<String, Object>> buscarArquivosPorInscricao(Integer id);
}