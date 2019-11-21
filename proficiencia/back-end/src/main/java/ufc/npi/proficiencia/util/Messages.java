package ufc.npi.proficiencia.util;

public class Messages {

    public static final String ERRO_CPF_JA_CADASTRADO = "Já existe alguém cadastrado com este CPF";

    public static final String ERRO_USUARIO_NAO_ENCONTRADO = "O usuário não foi encontrado";

    public static final String ERRO_AUTOEXCLUSAO = "Você não pode se excluir do sistema";

    public static final String ERRO_USUARIO_INSCRICOES_ATIVAS = "O usuário não pode ser excluido, pois possui inscricoes no sistema";

    public static final String ERRO_PRECEDENCIA_DATAS_EXAME = "A precedência não foi obedecida nas datas do exame";

    public static final String ERRO_CONFLITO_PERIODO_INSCRICOES = "O período de inscrição informado está em conflito com a data de inscrições existentes";

    public static final String ERRO_QUANTIDADE_DE_VAGAS_INSUFUCIENTE = "O número de vagas não pode ser menor do que o número atual de inscrições";

    public static final String ERRO_EXAME_FINALIZADO = "O exame já foi finalizado";

    public static final String ERRO_EXAME_NAO_PODE_SER_EXCLUIDO = "O exame não pode ser excluído";

    public static final String ERRO_LOCAL_NAO_PODE_SER_EXCLUIDO = "O local de prova não pode ser excluído";

    public static final String ERRO_EXAME_FORA_DO_PERIODO_DE_INSCRICAO = "O exame está fora do período de inscrição";

    public static final String ERRO_EXAME_FORA_DO_PERIODO_DE_SELECAO = "O exame está fora do período de seleção";

    public static final String ERRO_USUARIO_JA_INSCRITO_NO_EXAME = "Você já está inscrito no neste exame";

    public static final String ERRO_QUANTIDADE_DE_INSCRITOS_ESGOTADA = "Esse exame já possui a quantidade máxima de inscritos";

    public static final String ERRO_LOCAL_OU_CAPACIDADE_INVALIDA =  "Esse local está com campos não preenchidos ou inválidos";

    public static final String ERRO_FASE_INVALIDA =  "Esse local não pode ser excluído";

    public static final String ERRO_ARQUIVO_INVALIDO = "Arquivo sem extensão ou formato inválido. São permitidos apenas arquivos no formato PDF.";

    public static final String ERRO_FASE_EXAME = "Erro ao alterar fase do exame.";

    public static final String ERRO_LOCAL_NAO_EXISTE_VAGAS = "Não existem mais vagas no local de prova existente no sistema";

    public static final String ERRO_CAPACIDADE_MAIOR_QUE_NUMERO_DE_VAGAS = "Não foi possível adicionar pois a capacidade total dos locais é maior do que o número de vagas";

    public static final String ERRO_EDITAR_LOCAL_PROVA = "Não foi possível editar pois a capacidade total dos locais é maior do que o número de vagas";

    public static final String ERRO_MOVER_CANDIDATO_CAPACIDADE_MAIOR_QUE_NUMERO_DE_VAGAS = "Não foi possível mover candidato pois foi atingida a capacidade total de vagas";

    public static final String ERRO_ALTERAR_FASE_EXAME = "Não é possível alterar a fase do exame! O exame não tem candidatos inscritos";

    public static final String ERRO_MESMO_LOCAL_DE_PROVA = "Candidato já alocado nesse local de prova";

    public static final String ERRO_ADICIONAR_ARQUIVO_FASE_EXAME = "Não é possível adicionar arquivos. O exame não está em fase de inscrição.";

    public static final String ERRO_ADICIONAR_ARQUIVO_STATUS_INSCRICAO_DEFERIDA = "Não é possível adicionar arquivos. A inscrição já foi deferida.";

    public static final String ERRO_ADICIONAR_ARQUIVO_STATUS_INSCRICAO_INDEFERIDA = "Não é possível adicionar arquivos. A inscrição já foi indeferida.";

    public static final String ERRO_EXCLUIR_ARQUIVO_FASE_EXAME = "Não é possível excluir arquivos. O exame não está em fase de inscrição.";

    public static final String ERRO_EXCLUIR_ARQUIVO_STATUS_INSCRICAO_DEFERIDA = "Não é possível excluir arquivos. A inscrição já foi deferida.";

    public static final String ERRO_EXCLUIR_ARQUIVO_STATUS_INSCRICAO_INDEFERIDA = "Não é possível excluir arquivos. A inscrição já foi indeferida.";

}
