package ufc.npi.proficiencia.service.impl;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ufc.npi.proficiencia.exception.ProficienciaException;
import ufc.npi.proficiencia.model.Arquivo;
import ufc.npi.proficiencia.repository.ArquivoRepository;
import ufc.npi.proficiencia.service.ArquivoService;
import ufc.npi.proficiencia.util.Messages;

import java.io.*;
import java.util.List;
import java.util.Map;

@Service
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class ArquivoServiceImpl implements ArquivoService {


    @Value("${minio.url}")
    private String minio;

    @Value("${minio.acessKey}")
    private String acessKey;

    @Value("${minio.secretKey}")
    private String secretKey;

    @Value("${minio.bucket}")
    private String bucketName;


    @Autowired
    private ArquivoRepository arquivoRepository;


    public ArquivoServiceImpl(){

    }

    public ArquivoServiceImpl(String url, String acessKey, String secretKey){
        this.minio = url;
        this.acessKey = acessKey;
        this.secretKey = secretKey;
    }

    @Override
    public Arquivo salvarArquivo(String objectName, String observacao, InputStream stream, long size, String contentType) throws ProficienciaException{

        if(!contentType.equals("application/pdf") || (objectName.length()<4) || !objectName.substring(objectName.length() - 4).equals(".pdf")) throw new ProficienciaException(Messages.ERRO_ARQUIVO_INVALIDO);
        Arquivo arquivo = arquivoRepository.save(new Arquivo(objectName, observacao));
        try {
            getMinioClient().putObject(bucketName, String.valueOf(arquivo.getId()), stream, size, null, null, contentType);
        } catch (Exception e) {
            throw new ProficienciaException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return arquivo;
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public boolean deletarArquivo(Integer idDocumento) throws ProficienciaException{

        Arquivo arquivo = arquivoRepository.findById(idDocumento).get();
        String doc = Integer.toString(arquivo.getId());

        try{
            getMinioClient().removeObject(bucketName, doc);
        }catch (Exception e){
            throw new ProficienciaException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        this.arquivoRepository.deleteById(idDocumento);
        return true;
    }

    @Override
    public void atualizarArquivo(Integer idDocumento, String observacao, String objectName, InputStream stream, long size, String contentType) throws ProficienciaException{

        Arquivo arquivo = arquivoRepository.save(new Arquivo(idDocumento, observacao, objectName));
        String documentoID = Integer.toString(arquivo.getId());

        try{
            getMinioClient().putObject(bucketName, documentoID, stream, size, contentType);
        }catch (Exception e){
            throw new ProficienciaException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public InputStream buscarArquivoDownload(Integer id) throws ProficienciaException{

        Arquivo arquivo = arquivoRepository.getOne(id);
        String idArquivo = Integer.toString(arquivo.getId());
        return downloadArquivo(idArquivo);
    }

    @Override
    public Arquivo buscarUmArquivo(Integer id) {
        return arquivoRepository.getOne(id);
    }

    @Override
    public List<Map<String, Object>> buscarArquivosPorInscricao(Integer id) {
        return this.arquivoRepository.exibirTodosArquivosPorInscricao(id);
    }

    //Minio
    private InputStream downloadArquivo(String objectName ) throws ProficienciaException{
        try{
            return getMinioClient().getObject(bucketName, objectName);
        }catch (Exception e){
            throw new ProficienciaException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    //Construtor MinioClient
    private MinioClient getMinioClient() throws ProficienciaException {
        try{
            return new MinioClient(minio, acessKey, secretKey, bucketName);
        }catch (Exception e){
            throw new ProficienciaException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

}
