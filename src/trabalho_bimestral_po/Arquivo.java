
package trabalho_bimestral_po;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Arquivo
{
    private String nomearquivo;
    private RandomAccessFile arquivo;
    private int comp, mov;
    int TL;

    public Arquivo(String nomearquivo) 
    {
        try
        {
            arquivo = new RandomAccessFile(nomearquivo, "rw");
            this.TL = filesize();
        } catch (IOException e)
        { }
    }
    
    public void copiaArquivo(RandomAccessFile arquivoOrigem)
    {
        try
        {
            Registro r = new Registro(' ');
            for(int i = 5;i>=0;i--)
            {
                  arquivoOrigem.seek(i * Registro.length());
                  r.leDoArq(arquivoOrigem);
                  r.gravaNoArq(arquivo);
            }
        } catch (IOException e)
        { }
    }
    
    public RandomAccessFile getFile() 
    {
        return arquivo;
    }
    
    public void truncate(long pos)
    {
        try
        {
            arquivo.setLength(pos * Registro.length());
        } catch (IOException exc)
        { }
    }
    
    public boolean eof()
    {
        boolean retorno = false;
        try
        {
            if (arquivo.getFilePointer() == arquivo.length())
                retorno = true;                               
        } catch (IOException e)
        { }
        return retorno;
    }
    public void seekArq(int pos) 
    {
        try
        {
            arquivo.seek(pos * Registro.length());
        } catch (IOException e)
        { }
    }
    
    public int filesize() 
    {
        
        try
        {
            return  (int)arquivo.length()/Registro.length();
        } catch (IOException e)
        {}
        return -1;
    }
    
    public void initComp()
    {
        this.comp = 0;
    }
    
    public void initMov()
    {
        this.mov = 0;
    }
    
    public int getComp()
    {
        return comp;
    }
    
    public int getMov() 
    {
        return mov;
    }
    
    public void insercaoDireta()
    {
        TL = filesize();
        int pos;
        Registro aux = new Registro();
        Registro reg = new Registro();
        for(int i=1;i<TL;i++)
        {
            pos = i;
            seekArq(i-1);
            reg.leDoArq(arquivo);
            aux.leDoArq(arquivo);
            while(pos>0 && aux.getNumero()<reg.getNumero())
            {
                seekArq(pos);
                reg.gravaNoArq(arquivo);
                pos--;
                if(pos>0)
                {
                    seekArq(pos-1);
                    reg.leDoArq(arquivo);
                }
            }
            seekArq(pos);
            aux.gravaNoArq(arquivo);
        }
        exibe();
    }
    
    public void selecaoDireta()
    {
        TL = filesize();
        Registro reg = new Registro();
        Registro reg2 = new Registro();
        int menor,posmenor;
        for(int i=0;i<TL-1;i++)
        {
            seekArq(i);
            reg.leDoArq(arquivo);
            menor = reg.getNumero();
            posmenor = i;
            
            for(int j=i+1;j<TL;j++)
            {
                seekArq(j);
                reg2.leDoArq(arquivo);
                if(reg2.getNumero()<menor)
                {
                    menor = reg2.getNumero();
                    posmenor = j;
                }
            }
            seekArq(posmenor);
            reg.gravaNoArq(arquivo);
            seekArq(i);
            reg.setNumero(menor);
            reg.gravaNoArq(arquivo);
            
        }
        
        
        
        exibe();
    }
    
    public void shake()
    {
        TL = filesize();
        int fim = TL-1;
        int inicio = 0;
        Registro regi = new Registro();
        Registro regi2 = new Registro();
        while(fim>inicio)
        {
            for(int i = inicio; i<fim;i++)
            {
                seekArq(i);
                regi.leDoArq(arquivo);
                regi2.leDoArq(arquivo);
                
                if(regi.getNumero()>regi2.getNumero())
                {
                    seekArq(i);
                    regi2.gravaNoArq(arquivo);
                    regi.gravaNoArq(arquivo);
                }
            }
            fim--;
            for(int i = fim; i>inicio;i--)
            {
                seekArq(i-1);
                regi.leDoArq(arquivo);
                regi2.leDoArq(arquivo);
                
                if(regi2.getNumero()<regi.getNumero())
                {
                    seekArq(i-1);
                    regi2.gravaNoArq(arquivo);
                    regi.gravaNoArq(arquivo);
                }
            }
            inicio++;
        }
        exibe();
    }
    
    public void bolha()
    {
        TL = filesize();
        int TL2 = TL;
        Registro regi = new Registro();
        Registro regi2 = new Registro();
        while(TL2 > 1)
        {
            for(int i =0; i<TL2 -1;i++)
            {
                seekArq(i);
                regi.leDoArq(arquivo);
                regi2.leDoArq(arquivo);
                if(regi.getNumero()>regi2.getNumero())
                {
                    seekArq(i);
                    regi2.gravaNoArq(arquivo);
                    regi.gravaNoArq(arquivo);
                }
            }
            TL2--;
        }
        exibe();
    }
    
    public int buscaBinaria(int chave,int TL)
    {
        int meio = TL/2;
        int i = 0;
        int j = TL-1;
        Registro reg= new Registro();
        seekArq(meio);
        reg.leDoArq(arquivo);
        comp+=3;
        while(i < j && chave != reg.getNumero())
        {
            comp++;
           if(chave>reg.getNumero())
               i = meio+1;
           else
               j=meio-1;
           meio = (i+j)/2;
           seekArq(meio);
           reg.leDoArq(arquivo);
        }
        seekArq(meio);
        reg.leDoArq(arquivo);
        comp++;
        if (chave > reg.getNumero())
            return meio+1;
        return meio;
    }
    
     public void insercaoBinaria()
    {
        Registro aux = new Registro();
        Registro reg = new Registro();
        
        comp = 0;
        mov = 0;
        int pos;
        for(int i=1;i<TL;i++)
        {
            seekArq(i);
            aux.leDoArq(arquivo);
            pos = buscaBinaria(aux.getNumero(),i);
            comp++;
            for(int j=i;j>pos;j--)
            {
                seekArq(j-1);
                reg.leDoArq(arquivo);
                reg.gravaNoArq(arquivo);
                mov++;
                comp++;
            }
            seekArq(pos);
            aux.gravaNoArq(arquivo);
            comp++;
        }
        exibe();
    }
     
     
    public void Heap()
    {
        TL = filesize();
        Registro FD = new Registro();
        Registro FE = new Registro();
        Registro paiR = new Registro();
        Registro posR = new Registro();
        Registro com = new Registro();
        Registro fim = new Registro();
        int fE,fD,posmaior;
        for(int TL2 = TL;TL2>1;TL2--)
        {
            for(int pai = TL2/2-1;pai>=0;pai--)
            {
                fE = pai+pai+1;
                fD = fE+1;
                posmaior = 0;
                if(fD<TL2-1)//tem dois filhos
                {
                    seekArq(fD);
                    FD.leDoArq(arquivo);
                    seekArq(fE);
                    FE.leDoArq(arquivo);
                    if(FE.getNumero()>FD.getNumero())
                    {
                        posmaior = fE;
                    }
                    else
                    {
                        posmaior = fD;
                    }
                }
                else
                {
                    posmaior = fE;
                }
                seekArq(pai);
                paiR.leDoArq(arquivo);
                seekArq(posmaior);
                posR.leDoArq(arquivo);
                if(posR.getNumero()>paiR.getNumero())
                {
                    seekArq(posmaior);
                    paiR.gravaNoArq(arquivo);
                    seekArq(pai);
                    posR.gravaNoArq(arquivo);
                }
            }
            seekArq(0);
            com.leDoArq(arquivo);
            seekArq(TL2-1);
            fim.leDoArq(arquivo);
            seekArq(0);
            fim.gravaNoArq(arquivo);
            seekArq(TL2-1);
            com.gravaNoArq(arquivo);
        }
        exibe();
    }
    
    public void Shell()
    {
        TL = filesize();
        Registro reg = new Registro();
        Registro reg2 = new Registro();
        Registro reg3 = new Registro();
        Registro reg4 = new Registro();
        for(int dist = 4; dist>0;dist = dist/2)
        {
            for(int i=0;i<dist;i++)
            {
                for(int j=i;j+dist<TL;j=j+dist)
                {
                   seekArq(j);
                   reg.leDoArq(arquivo);
                   seekArq(j+dist);
                   reg2.leDoArq(arquivo);
                   if(reg.getNumero()>reg2.getNumero())
                   {
                       seekArq(j+dist);
                       reg.gravaNoArq(arquivo);
                       seekArq(j);
                       reg2.gravaNoArq(arquivo);
                       int k = j;
                       if(k-dist>=0)
                       {
                           seekArq(k-dist);
                           reg4.leDoArq(arquivo);
                           seekArq(k);
                           reg3.leDoArq(arquivo);
                           for(k=j;k-dist>=0 && reg3.getNumero()<reg4.getNumero();k=k-dist)
                           {
                               seekArq(k);
                               reg4.gravaNoArq(arquivo);
                               seekArq(k-dist);
                               reg3.gravaNoArq(arquivo);
                               if(k-dist*2>=0)
                                {
                                    seekArq(k-dist*2);
                                    reg4.leDoArq(arquivo);
                                    seekArq(k-dist);
                                    reg3.leDoArq(arquivo);  
                                }
                           }
                       }
                   }
                }
            }
        }
        exibe();
    }
    
    public void QuickSemPivo()
    {
        QuickSP(0,TL-1);
        exibe();
    }
    public void QuickSP(int ini,int fim)
    {
        Registro reg = new Registro();
        Registro reg2 = new Registro();
        int i = ini;
        int j = fim;
        
        
        while(i<j)
        {
            seekArq(i);
            reg.leDoArq(arquivo);
            seekArq(j);
            reg2.leDoArq(arquivo);
            
            while(i<j && reg.getNumero()<=reg2.getNumero())
            {
                i++;
                seekArq(i);
                reg.leDoArq(arquivo);
            }
            if(i!=j)
            {
                seekArq(i);
                reg2.gravaNoArq(arquivo);
                seekArq(j);
                reg.gravaNoArq(arquivo);
            }

            while(i<j && reg2.getNumero()>=reg.getNumero())
            {
                j--;
                seekArq(j);
                reg2.leDoArq(arquivo);
            }
            if(i!=j)
            {
                seekArq(j);
                reg.gravaNoArq(arquivo);
                seekArq(i);
                reg2.gravaNoArq(arquivo);
            }

        }
        
        if(ini<i-1)
            QuickSP(ini, i-1);
        if(j+1<fim)
            QuickSP(j+1, fim);
    }
    
    
    public void QuickComPivo()
    {
        TL = filesize();
        QuickCP(0,TL-1);
        exibe();
    }
    public void QuickCP(int ini,int fim)
    {
        Registro reg = new Registro();
        Registro reg2 = new Registro();
        Registro pivo = new Registro();
        int i = ini;
        int j= fim;
        int valor = (ini+fim)/2;
        seekArq(valor);
        pivo.leDoArq(arquivo);
        while(i<j)
        {
            seekArq(i);
            reg.leDoArq(arquivo);
            while(reg.getNumero()<pivo.getNumero())
            {
                comp++;
                i++;
                seekArq(i);
                reg.leDoArq(arquivo);
            }
            seekArq(j);
            reg2.leDoArq(arquivo);
            while(reg2.getNumero()>pivo.getNumero())
            {
                comp++;
                j--;
                seekArq(j);
                reg2.leDoArq(arquivo);
            }
                seekArq(i);
                reg2.gravaNoArq(arquivo);
                seekArq(j);
                reg.gravaNoArq(arquivo);
                i++;
                j--;
                mov+=2;
        }
        if(ini<j)
            QuickCP(ini, j);
        if(i<fim)
            QuickCP(i, fim);
    }
    public void counting()
    {
        TL = filesize();
        int maior;
        int menor;
        int i;
        int pos;
        Registro reg = new Registro();
        seekArq(0);
        
        reg.leDoArq(arquivo);
        maior = reg.getNumero();
        menor = reg.getNumero();
        for(i =1;i<TL;i++)
        {
            reg.leDoArq(arquivo);
            if(maior<reg.getNumero())
                maior = reg.getNumero();
            if(menor>reg.getNumero())
                menor = reg.getNumero();
        }
        int aux[] = new int[maior+1];
        
        for(i=0;i<maior+1;i++)
            aux[i]=0;
        
        
        for(i=0;i<TL;i++)
        {
            seekArq(i);
            reg.leDoArq(arquivo);
            pos = reg.getNumero();
            aux[pos-menor] += 1;
        }
         
       
        for(i = 1;i<maior+1;i++)
        {
            aux[i] += aux[i-1];
        }
        int vet[] = new int[TL];
         
        for(i =0;i<TL;i++)
        {
           seekArq(i);
           reg.leDoArq(arquivo);
           pos = reg.getNumero();
           vet[aux[pos]-1] = pos;
        }
       
        seekArq(0);
         for(i =0;i<TL;i++)
         {
             reg.setNumero(vet[i]);
             reg.gravaNoArq(arquivo);
         }
        exibe();
    }
    
     
    public void bucket()
    {
       Registro reg1 = new Registro();
       ArrayList<ArrayList<Integer>> lista = new ArrayList<>();
       int divisor;
       int j;
       int vet[]= new int[TL];
       int maior,menor;
       
       for(int i = 0; i<10;i++)
       {
           lista.add(new ArrayList<>());
       }
       
       seekArq(0);
       reg1.leDoArq(arquivo);
       maior = reg1.getNumero();
       menor = reg1.getNumero();
       for(int i=1;i<TL;i++)
       {
           seekArq(i);
           reg1.leDoArq(arquivo);
           if(maior<reg1.getNumero())
               maior = reg1.getNumero();
           if(menor>reg1.getNumero())
               menor = reg1.getNumero();
       }
        divisor = (int)((maior+1)*100)/10;
       
       for(int i=0;i<TL;i++)
       {
           seekArq(i);
           reg1.leDoArq(arquivo);
           j = reg1.getNumero()/divisor;
           lista.get(j).add(reg1.getNumero());
       }
       for(int i = 0;i<10;i++)
       {     
           Collections.sort(lista.get(i));///////////////////////AAAAAAAAAAAAAAAAAAAAAAA//////////////////////////////////////////////////
       }
       int pos = 0;
       for(int i = 0;i<10;i++)
       {
          for(int x = 0;x<lista.get(i).size();x++)
          {
              vet[pos] = lista.get(i).get(x);
              pos++;
          }
       }
       for(int i=0;i<TL;i++)
       {
           seekArq(i);
           reg1.setNumero(vet[i]);
           reg1.gravaNoArq(arquivo);
       }
       exibe();
    }
    
    public void radix()
    {
       Registro reg1 = new Registro();
       ArrayList<ArrayList<Integer>> lista = new ArrayList<>();
       int num;
       int pos = 0;
       int maiorNum = 0;
       int vet[] = new int[TL];
       String numero;
       for(int i = 0; i<10;i++)
       {
           lista.add(new ArrayList<>());
       }
       for(int i = 0; i<TL;i++)
       {
           seekArq(i);
           reg1.leDoArq(arquivo);
           numero = ""+reg1.getNumero();
           if(i == 0)
               maiorNum = numero.length();
           if(maiorNum<numero.length())
               maiorNum = numero.length();
       }
       for(int i = 0; i<TL;i++)
        {
            seekArq(i);
            reg1.leDoArq(arquivo);
            numero = ""+reg1.getNumero();
            for(int j=numero.length();j<maiorNum;j++)
            {
                numero = "0"+numero;
            }
            num = Integer.parseInt(""+numero.charAt(pos));
            lista.get(num).add(Integer.parseInt(numero));
        }
       int lugar =0;
       for(int i=0;i<10;i++)
       {
           for(int x=0;x<lista.get(i).size();x++)
           {
               num = lista.get(i).get(x);
               vet[lugar] = num;
               lugar++;
           }
           lista.get(i).clear();
       }
       for(pos = 1;pos<maiorNum;pos++)
       {
          
            for(int i = 0; i<TL;i++)
            {
                numero = ""+vet[i];
                for(int j=numero.length();j<maiorNum;j++)
                {
                    numero = "0"+numero;
                }
                num = Integer.parseInt(""+numero.charAt(pos));
                lista.get(num).add(Integer.parseInt(numero));
            }
            lugar =0;
            for(int i=0;i<10;i++)
            {
                for(int x=0;x<lista.get(i).size();x++)
                {
                    num = lista.get(i).get(x);
                    vet[lugar] = num;
                    lugar++;
                }
                lista.get(i).clear();
            }
       }
       for(int i=0;i<TL;i++)
        {
            seekArq(i);
            reg1.setNumero(vet[i]);
            reg1.gravaNoArq(arquivo);
        }
       exibe();
    }
    
    public void gnome()
    {
        TL = filesize();
        Registro reg = new Registro();
        Registro reg2 = new Registro();
        seekArq(0);
        for(int gnomo=0;gnomo<TL-1;gnomo++)
        {
            seekArq(gnomo);
            reg.leDoArq(arquivo);
            reg2.leDoArq(arquivo);
            if(reg.getNumero()>reg2.getNumero())
            {
               seekArq(gnomo);
               reg2.gravaNoArq(arquivo);
               reg.gravaNoArq(arquivo);
               gnomo = -1;
            }
        }
        exibe();
    }
    public void Comb() 
    {   
        TL = filesize();
        Registro reg = new Registro();
        Registro reg2 = new Registro();
        seekArq(0);
        int gap = TL;
        
        while(gap>=1)
        {
            for(int i=0;i<TL;i++)
            {
                if(i+gap<TL)
                {
                    seekArq(i);
                    reg.leDoArq(arquivo);
                    seekArq(i+gap);
                    reg2.leDoArq(arquivo);
                    if(reg.getNumero()>reg2.getNumero())
                    {
                        seekArq(i);
                        reg2.gravaNoArq(arquivo);
                        seekArq(i+gap);
                        reg.gravaNoArq(arquivo);
                    }
                }
            }
            gap = (int) (gap/1.3);
        }
        exibe();
    }
    public void merge()
    {
        TL = filesize();
        Registro aux[] =new Registro [TL];
        for(int i=0;i<TL;i++)
            aux[i] = new Registro();
        Registro reg =new Registro();
        Registro reg2 =new Registro();
        mergesort(aux,0,TL-1,reg,reg2);
        exibe();
    }
    public void mergesort(Registro aux[],int esq,int dir,Registro reg,Registro reg2)
    {
        int meio;
        if(esq<dir)
        {
            meio = (esq+dir)/2;
            mergesort(aux,esq,meio,reg,reg2);
            mergesort(aux,meio+1,dir,reg,reg2);
            fusao(aux,esq,meio,meio+1,dir,reg,reg2);
        }
    }
    
    
    public void fusao(Registro aux[],int ini1,int fim1,int ini2,int fim2,Registro reg, Registro reg2)
    {
        int i=ini1,j=ini2,k=0;
        
        while(i<=fim1&&j<=fim2)
        {
            seekArq(i);
            reg.leDoArq(arquivo);
            seekArq(j);
            reg2.leDoArq(arquivo);
            if(reg.getNumero()<reg2.getNumero())
            {
                aux[k++].setReg(reg);
                i++;
            }
            else
            {
                aux[k++].setReg(reg2);
                j++;
            }   
        }
        while(i<=fim1)
        {
            seekArq(i);
            reg.leDoArq(arquivo);
            aux[k++].setReg(reg);
            i++;
        }
         while(j<=fim2)
        {
            seekArq(j);
            reg2.leDoArq(arquivo);
            aux[k++].setReg(reg2);
            j++;
        }
         for(i=0;i<k;i++)
         {
             seekArq(ini1+i);
             aux[i].gravaNoArq(arquivo);
         }
    }
   void insersaoTim(int esq, int dir) 
    { 
        int temp,j;
        Registro reg = new Registro();
        Registro reg2 = new Registro();
        comp++;
        for (int i = esq + 1; i <= dir; i++) 
        { 
            seekArq(i);
            reg.leDoArq(arquivo);
            temp = reg.getNumero();
            j = i - 1;
            seekArq(j);
            reg2.leDoArq(arquivo);
            comp+=3;
            while (reg2.getNumero() > temp && j >= esq) 
            {
                seekArq(j+1);
                reg2.gravaNoArq(arquivo);
                mov++;
                j--; 
                comp+=3;
            }
            seekArq(j+1);
            reg.gravaNoArq(arquivo);
            mov++;
            comp++;
        } 
    } 
    
    
   
   
    public void MergedoTim(int esq, int meio, int dir)  
    {  
        
        int tam = meio - esq + 1, tam2 = dir - meio;  
        Registro[] RegEsq = new Registro[tam]; 
        Registro[] RegDir = new Registro[tam2];
        Registro rt = new Registro();
        comp++;
        for (int x = 0; x < tam; x++)
        {
            seekArq(1+x);
            rt.leDoArq(arquivo);
            RegEsq[x] = rt;  
            comp++;
        }
        comp++;
        for (int x = 0; x < tam2; x++)
        {
            seekArq(meio+1+x);
            rt.leDoArq(arquivo);
           RegDir[x] = rt;  
            comp++;
        }
        int i = 0;  
        int j = 0;  
        int k = esq;  
       comp+=3;
        while (i < tam && j < tam2)  
        {  comp++;
            if (RegEsq[i].getNumero() <= RegDir[j].getNumero())  
            {  
                seekArq(k);
                RegEsq[i].gravaNoArq(arquivo);
                i++;  
            }  
            else
            {   seekArq(k);
                RegDir[j].gravaNoArq(arquivo);
                j++;  
            }  
            k++;  
            comp+=3;
        }  
        comp++;
        while (i < tam)  
        {  
            seekArq(k);
            RegEsq[i].gravaNoArq(arquivo);
            k++;  
            i++; 
            comp++;
        }  
        comp++;
        while (j < tam2)  
        { 
            seekArq(k);
            RegDir[j].gravaNoArq(arquivo); 
            k++;  
            j++;  
            comp++;
        }  
    }  
    

   public void Tim()
    {
        int n = TL,RUN = 32;
        int menor;
        comp++;
        for (int i = 0; i < n; i=i+RUN) 
        {
            comp++;
            if(i+31<n-1)
                menor=i+31;
            else
                menor=n-1;
             insersaoTim(i,menor);
             comp++;
        }
        comp++;
        for (int tam = RUN; tam < n; tam = 2*tam)  
        {  comp++;
            for (int esq = 0; esq < n; esq += 2*tam)  
            {  
                int meio = esq + tam - 1;  
                int dir = Math.min((esq + 2*tam - 1), (n-1));  
                MergedoTim(esq, meio, dir);  
                comp++;
            }  
            comp++;
        }  
    }
  
    public void geraArquivoOrdenado() 
    {
        
        for(int i=0;i<30;i++)
        {
            Registro r = new Registro(i);
            r.gravaNoArq(arquivo);
        }
        
    }
    
    public void geraArquivoReverso() 
    {
        for(int i=30;i>=0;i--)
        {
            Registro r = new Registro(i);
            r.gravaNoArq(arquivo);
        }
       
    }
    public void exibe()
    {
        Registro r = new Registro();
        seekArq(0);
        for(int i=0; i<=30;i++)
        {
            r.leDoArq(arquivo);
            System.out.println(r.getNumero());
        }
    }
    public void geraArquivoRandomico()
    {
        Random gerador = new Random();
        for(int i=0;i<30;i++)
        {
            Registro r = new Registro(gerador.nextInt(10));
            r.gravaNoArq(arquivo);
        }
    }
}
