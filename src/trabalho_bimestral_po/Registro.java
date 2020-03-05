package trabalho_bimestral_po;

import java.io.IOException;
import java.io.RandomAccessFile;

public class Registro
{

    public final int tf = 1022;
    private int numero; //4 bytes
    private char lixo[] = new char[tf]; //2044 bytes

    public Registro(int numero)
    {
        this.numero = numero;
        for (int i = 0; i < tf; i++)
        {
            lixo[i] = 'X';
        }
    }
    public Registro()
    {
        
    }
    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getNumero()
    {
        return numero;
    }
    public void setReg(Registro r)
    {
        this.numero = r.numero;
        this.lixo = r.lixo;
    }
    
    public void gravaNoArq(RandomAccessFile arquivo)
    {
        try
        {
            
            arquivo.writeInt(numero);
            for (int i = 0; i < tf; i++)
            {
                arquivo.writeChar(lixo[i]);
            }
        } catch (IOException e)
        {
        }
    }

    public void leDoArq(RandomAccessFile arquivo)
    {
        try
        {
            this.numero = arquivo.readInt();
            for (int i = 0; i < tf; i++)
            {
                this.lixo[i] = arquivo.readChar();
            }
        } catch (IOException e)
        {
        }
    }

    static int length()
    {
        return 2048;
    }

}
