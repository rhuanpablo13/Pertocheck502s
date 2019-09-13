/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import comunicacao.PCheck;
import java.util.HashMap;
import negocio.NCheque;
import utils.Erro;

/**
 *
 * @author rhuan
 */
public class CCheque {

    private PCheck pcheque = null;
    private final NCheque cheque;
    private static final HashMap<String, String> errosImpressao = new HashMap<>();
    
    
    public CCheque(NCheque cheque) {
        this.cheque = cheque;
    }
    

    public void iniciaImpressora() throws Erro {
        try {
            pcheque = new PCheck();
            pcheque.init();
        } catch (Erro ex) {
            throw ex;
        }
    }
    
    
    public void stopImpressora() {
        pcheque.stop();
    }
    
    
    public void ejetaCheque() throws Erro {
        if(pcheque != null) {
            pcheque = new PCheck();
        }
        
        try {
            pcheque.executaComando(">", 60000);
        } catch(Erro ex) {
            throw ex;
        }
    }
    
    
    
    public void alteraData() throws Erro {
        try {
            pcheque.executaComando(cheque.getData(), 1);
        } catch(Erro ex) {
            throw ex;
        }
    }



    public void alteraBeneficiario() throws Erro {
        try {
            pcheque.executaComando(cheque.getBeneficiario(), 1);
        } catch(Erro ex) {
            throw ex;
        }        
    }
    
    
    public void alteraCidade() throws Erro {
        try {
            pcheque.executaComando(cheque.getCidade(), 1);
        } catch(Erro ex) {
            throw ex;
        }        
    }


    public void leituraCheque() throws Erro {
        try {
            pcheque.executaComando("=", 50);
        } catch(Erro ex) {
            throw ex;
        }        
    }    
    
    
    public void preencheCheque() throws Erro {
        try {
            pcheque.executaComando(cheque.getValor(), 30);
        } catch(Erro ex) {
            throw ex;
        }        
    }
    
}
