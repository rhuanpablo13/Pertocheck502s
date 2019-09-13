/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comunicacao;

/**
 *
 * @author rhuan
 */
public class Protocolo {
    
    private static byte[] buffer;
    private static int contador;
    private int passo;
    private boolean ok;
    private byte bcc;
    private int maximo;
    private final byte STX = 2;
    private final byte ETX = 3;

    
    public void montaCmd(String paramString) {
        int i = 0;
        int j = paramString.length();
        buffer = new byte[j + 3];


        buffer[(i++)] = 2;
        bcc = 2;
        for (int m = 0; m < j; m++) {
            int k = (byte)paramString.charAt(m);
            buffer[(i++)] = (byte) k;
            bcc = ((byte)(bcc ^ k));
        }
        buffer[(i++)] = 3;
        bcc = ((byte)(bcc ^ 0x3));
        buffer[(i++)] = bcc;
    }

    
    
    public byte[] getBuffer() {
        return buffer;
    }

    
    
    public void iniciaRecepcao(int paramInt) {
        buffer = new byte[paramInt];
        maximo = paramInt;
        zerarRecepcao();
    }

    public void zerarRecepcao() {
        passo = 0;
        contador = 0;
        ok = false;
    }    

    public boolean acrescentaByte(byte paramByte) {
        
        boolean bool = false;

        if (contador == maximo) {
            return true;
        }
        
        switch (passo) {
        case 0: 
            if (paramByte == 2) {
                buffer[(contador++)] = paramByte;
                bcc = paramByte;
                passo += 1;
            }
            break;
            
        case 1: 
            buffer[(contador++)] = paramByte;
            bcc = ((byte)(bcc ^ paramByte));
            if (paramByte == 3) {
                passo += 1;
            
            } else if (paramByte == 2) {
                contador = 0;
                buffer[(contador++)] = paramByte;
            }
            break;
            
        case 2: 
          buffer[(contador++)] = paramByte;
          if (paramByte == bcc) {
            ok = true;
          }
          bool = true;
        }
        return bool;
    }

    
    
    public boolean recepcaoOk() {
        return ok;
    }

    
    
    /**
     * Retorna: !000, #000, $000, =000, etc
     * Retorna: !001, #012, $001, =002, etc
     * etc, etc...
     * @return String 
     */
    public static String getResposta() {
        return new String(buffer, 1, contador - 3);
    }


    public String HexToString(byte paramByte) {
        String str = "00" + Integer.toHexString(paramByte);
        return str.substring(str.length() - 2);
    }
    
}
