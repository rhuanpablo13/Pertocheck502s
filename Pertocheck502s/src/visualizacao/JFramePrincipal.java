/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package visualizacao;

import controle.CArquivoToCheque;
import controle.CCheque;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import negocio.NCheque;
import utils.Alert;
import utils.Configuracoes;
import utils.Erro;
import utils.Log;
import utils.UArquivos;

/**
 *
 * @author rhuan
 */
public class JFramePrincipal extends javax.swing.JFrame {

    
    public JFramePrincipal() {
        initComponents();
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFramePrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        stCaminhoArquivo = new javax.swing.JTextField();
        btSelecionarArquivo = new javax.swing.JButton();
        boSuporte = new javax.swing.JCheckBox();
        btImprimir = new javax.swing.JButton();
        btHelp = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel1.setText("Impressora de Cheques PertoCheck 502s");

        stCaminhoArquivo.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N

        btSelecionarArquivo.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        btSelecionarArquivo.setText("Selecionar");

        boSuporte.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        boSuporte.setText("Suporte");
        boSuporte.setToolTipText("Selecionar apenas para suporte da impressora");

        btImprimir.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        btImprimir.setText("Imprimir");

        btHelp.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        btHelp.setText("Help");

        jLabel2.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jLabel2.setText("versão 2.0");

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(109, 109, 109)
                        .add(jLabel1))
                    .add(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .add(jLabel2)
                        .add(314, 314, 314)
                        .add(btHelp)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(boSuporte)))
                .add(0, 0, Short.MAX_VALUE))
            .add(jPanel1Layout.createSequentialGroup()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(65, 65, 65)
                        .add(stCaminhoArquivo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 281, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(btSelecionarArquivo))
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(192, 192, 192)
                        .add(btImprimir)))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(10, 10, 10)
                .add(jLabel1)
                .add(41, 41, 41)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(stCaminhoArquivo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 23, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(btSelecionarArquivo))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(btImprimir)
                        .add(62, 62, 62))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(btHelp)
                            .add(boSuporte)
                            .add(jLabel2))
                        .addContainerGap())))
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(2, 2, 2)
                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {


        java.awt.EventQueue.invokeLater(() -> {
            JFramePrincipal frame = new JFramePrincipal();
            frame.init();
            frame.setVisible(true);
        });
    }

    
    public void init() {
        
        Log.getInstance().appendLog("\n\n============ Iniciando a Pertochek =============\n");
        Log.getInstance().appendLog("Abre janela para escolher arquivo");
        btSelecionarArquivo.addActionListener((ActionEvent e) -> {
            
            try {
                String caminho = getDiretorioPadrao();
                FileChooser fc = new FileChooser();
                
                if (caminho == null) {
                    caminho = fc.chooser();
                } else {
                    caminho = fc.chooser(caminho);
                }
                
                if (caminho == null) {
                    JOptionPane.showConfirmDialog(this, "Nenhum arquivo selecionado");
                    return;
                }
                stCaminhoArquivo.setText(caminho);
                
                //recupera os dados do arquivo de cheque
                CArquivoToCheque arquivoToCheque = new CArquivoToCheque(caminho);

                //Preenche um objeto NCheque com os dados vindos do arquivo
                NCheque cheque = arquivoToCheque.getChequePreenchido();

                // Mandou imprimir
                btImprimir.addActionListener((ActionEvent e2) -> {
                    
                    try {
                        //executa o método de impressão
                        imprimeCheque(cheque);
                    } catch (Erro ex) {
                        new Alert().show(ex.print(), true);
                        stCaminhoArquivo.setText("");
                    }
                    
                });
                
            } catch (Erro ex) {
                new Alert().show(ex.print(), true);
                stCaminhoArquivo.setText("");
            }
            
        });
        
        
        btHelp.addActionListener((ActionEvent e) -> {
            openHelp();
        });
    }
    

    
    
    public void openHelp() {
        if (Desktop.isDesktopSupported()) {
            try {
                String inputPdf = "Help.pdf";
                Path tempOutput = Files.createTempFile("TempManual", ".pdf");
                tempOutput.toFile().deleteOnExit();
                InputStream is = JFramePrincipal.class.getResourceAsStream(inputPdf);
                Files.copy(is, tempOutput, StandardCopyOption.REPLACE_EXISTING);
                Desktop.getDesktop().open(tempOutput.toFile());
            }   catch (IOException ex) {
                new Alert().show("Arquivo Help.pdf não encontrado");
            }
        }
    }
    
 
    public void imprimeCheque(NCheque cheque) throws Erro {
        
        CCheque ccheque = new CCheque(cheque);
        try {
            
            Log.getInstance().appendLog("Solicitando a inserção do cheque na impressora.");
            if (Alert.confirm(this, "Confirma a impressão de cheque?")) {
                
                Log.getInstance().appendLog("Iniciando a impressora");
                ccheque.iniciaImpressora();

                preencheCampos(ccheque);
                
                ccheque.leituraCheque();
                Log.getInstance().appendLog("Realizando leitura do cheque.");
                
                ccheque.preencheCheque();
                Log.getInstance().appendLog("Cheque preenchido: " + cheque.toString());
                
            } else {
                System.exit(0);
            }
            
            ccheque.stopImpressora();
            Log.getInstance().appendLog("Encerrando comunicação com a impressora.");
            showDebug();
            
        } catch (Erro ex) {
            showDebug();
            ccheque.stopImpressora();
            throw ex;
        }
        
    }
    
    
    private void showDebug() {
        if (boSuporte.isSelected()) {
            JFrameSaida saida = new JFrameSaida();
            String log = carregaArquivoLog();
            if (log != null) {
                saida.getfSaida().setText(log);
            } else {
                saida.getfSaida().setText("Sem dados de log");
            }
            saida.setVisible(true);
        }
    }
    
    private void preencheCampos(CCheque ccheque) throws Erro {
        try {
            ccheque.alteraData();
            ccheque.alteraBeneficiario();
            ccheque.alteraCidade();
        } catch (Exception ex) {
            throw ex;
        }
    }
    
    /**
     * Retorna o diretório default onde o usuário quer selecionar o arquivo
     * @return String
     * @throws Exception 
     */
    private String getDiretorioPadrao() throws Erro {
        try {
            Configuracoes config = new Configuracoes();
            List<String> cfs = config.lerConfiguracoesArquivo();
            if(cfs.size() < 3) {
                return null;
            }
            return cfs.get(2);
        } catch (Exception ex) {
            throw ex;
        }
    }
    
    
    private String carregaArquivoLog() {
        try {
            List<String> log = UArquivos.ler(Log.path);
            String retorno = "";
            return log.stream().map((aux) -> aux+"\n").reduce(retorno, String::concat);
        } catch(IOException e) {
        }
        return null;
    }



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox boSuporte;
    private javax.swing.JButton btHelp;
    private javax.swing.JButton btImprimir;
    private javax.swing.JButton btSelecionarArquivo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField stCaminhoArquivo;
    // End of variables declaration//GEN-END:variables

}
