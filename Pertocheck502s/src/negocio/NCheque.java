/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

/**
 *
 * @author rhuan
 */
public class NCheque {

    
        /**
     * ********** Regras *********
     * 
     * -> Letras sempre maiúsculas e sem acentuações especiais
     * -> Deve ser concatenado o simbolo '%' antes do nome
     * -> Ex: %JOSE DA SILVA
     */
    private String beneficiario;
    
    
    
    

    /**
     * ********** Regras *********
     * 
     * -> Letras sempre maiúsculas e sem acentuações especiais
     * -> Deve ser concatenado o simbolo '#' antes do nome da cidade
     * -> Ex: #SAO PAULO
     */
    private String cidade;


    
    
    
    
    /**
     * ********** Regras *********
     * -> A impressora aceita dois padrões de data: (ddmmaa) e (ddmmaaaa)
     * -> Até o momento (30/11/2017) a impressora está configurada para imprimir 
     * no padrão (ddmmaa).
     * -> A impressora deve estar configurada para aceitar um dos modelos de data aceitos
     * -> Deve ser concatenado o simbolo '!' antes da data
     * -> Ex: !301117
     */
    private String data;
    
    
    
    

    /**
     * ********** Regras *********
     * 
     * -> O padrão para o valor é: (ex:000000012555) 12bytes
     * -> Deve ser concatenado o simbolo ';' antes do valor
     * -> Deve ser concatenado o paramentro de modo de funcionamento depois do simbolo
     * -> (Opcional*) Deve ser concatenado o número do banco depois do parametro
     *  de funcionamento dependendo do tipo do parametro. 
     * 
     * -> DEVE-SE CONDULTAR O CONJUNTO DE COMANDOS DO MANUAL TÉCNICO
     * 
     * -> Ex: ;2 -> '2' - Paramentro de funcionamento (Preenchimento e leitura de caracteres magnetizáveis CMC7)
     * -> Ex: ;2000000012555     -> sem o número do banco
     * -> Ex: ;2000000012555123  -> com o número do banco
     */
    private String valor;



    
    
    
    
    public NCheque() {
    }

    
    @Override
    public String toString() {
        return "NCheque{" + "beneficiario=" + beneficiario + ", cidade=" + cidade + ", data=" + data + ", valor=" + valor + '}';
    }



    /**
     * @param beneficiario the beneficiario to set
     */
    public void setBeneficiario(String beneficiario) {
        this.beneficiario = "%" + beneficiario.toUpperCase();
    }


    /**
     * @param cidade the cidade to set
     */
    public void setCidade(String cidade) {
        this.cidade = "#" + cidade.toUpperCase();
    }


    /**
     * @param data the data to set
     */
    public void setData(String data) {
        this.data = "!" + data.toUpperCase();
    }
    
    
    /**
     * @param valor
     * @param operacao
     * @param nrBanco
     */
    public void setValor(String valor, String operacao, String nrBanco) {
        this.valor = ";" + operacao + valor + nrBanco;
    }
    
    
    
    /**
     * @param valor
     * @param operacao
     */
    public void setValor(String valor, String operacao) {
        this.valor = ";" + operacao + valor;
    }

    


    
    /**
     * @return the beneficiario
     */
    public String getBeneficiario() {
        return beneficiario;
    }
    
    /**
     * @return the cidade
     */
    public String getCidade() {
        return cidade;
    }

    /**
     * @return the data
     */
    public String getData() {
        return data;
    }

    /**
     * @return the valor
     */
    public String getValor() {
        return valor;
    }
    
    
}
