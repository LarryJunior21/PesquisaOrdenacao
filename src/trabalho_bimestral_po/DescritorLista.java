package trabalho_bimestral_po;


public class DescritorLista
{
    private Lista Inicio;
    private Lista Fim;

    public DescritorLista()
    {
        Inicio = Fim = null;
    }

    public Lista getInicio() {
        return Inicio;
    }

    public void setInicio(Lista Inicio) {
        this.Inicio = Inicio;
    }

    public Lista getFim() {
        return Fim;
    }

    public void setFim(Lista Fim) {
        this.Fim = Fim;
    }
    
    public void insereInicio(Lista L)
    {
        if(Inicio == null)
        {
            Inicio = Fim = L;
        }
        else
        {
            L.setProx(Inicio);
            Inicio.setAnt(L);
            this.setInicio(L);
        }
    }
    
    public void insereFim(Lista L)
    {
        if(Inicio == null)
        {
            Inicio = Fim = L;
        }
        {
            L.setAnt(Fim);
            Fim.setProx(L);
            this.setFim(L);
        }
    }
    
    public int TL(Lista F) /// RETORNA A POSIÇÃO DO NO NA LISTA
    {
        int i = 0;
        
        Lista aux = Inicio;
        
        for(;aux != null && aux != F.getProx(); i++, aux = aux.getProx());
        
        return i;
    }
    
    public int TL(Lista ini, Lista fim) /// RETORNA A DISTANCIA ENTRE DOIS ELEMENTOS DA LISTA
    {
        int i = 0;
        
        Lista aux = ini;
        
        for(;aux != fim; i++, aux = aux.getProx());
        
        return i;
    }
    
    public Lista seekPont(Lista L, int pos) /// MOVE O PONTEIRO POS POSIÇÕES A PARTIR DA POSIÇÃO ATUAL, SE CHEGAR AO FINAL RETORNA NULL
    {
        if(pos > 0)
            for(int i = 0; L != null && i < pos; i++)
                L =  L.getProx();
        else
            for(; L != null && pos < 0; pos++)
                L =  L.getAnt();
            
        return L;
    }
    
    public int getPos(Lista L) /// RETORNA A POSIÇÃO DO ELEMENTO NA LISTA COMO UM INT
    {
        int i = 0;
        Lista aux = Inicio;
        
        for(; aux != L; i++, aux = aux.getProx());
        
        return i;
    }
    
    public Lista intToLista(int i) //RETORNA O NO DO NUMERO ENVIADO EX= i=3 RETORNA O TERCEIRO NÓ
    {
        Lista aux = Inicio;
        
        for (int j = 0; j < i; j++) 
        {
            aux = aux.getProx();
        }
        
        return aux;
    }
    
     public Lista intToListaE(int i,DescritorLista L)
    {
        Lista aux = L.getInicio();
        
        for (int j = 0; j < i; j++) 
        {
            aux = aux.getProx();
        }
        
        return aux;
    }
    
    
    
     public int buscaBinaria(int chave,Lista i)
    {
        Lista inicio = Inicio, fim = i, aux = Inicio;
        Lista meio = seekPont(aux, TL(inicio, fim)/2);
        
        while(inicio.getInfo() < fim.getInfo() && chave >= meio.getInfo())
        {
            if(chave > meio.getInfo())
                inicio = meio.getProx();
            else
                fim = meio.getAnt();
            meio = seekPont(aux, (getPos(inicio) + getPos(fim))/2);
        }
       
        
      
            return TL(meio);
     
    }
    
    
    public void exibeLista()
    {
        Lista aux = Inicio;
        if(aux == null)
            System.out.println("Lista vazia...");
        else
        while(aux != null)
        {
            System.out.println(aux.getInfo());
            aux = aux.getProx();
        }
    }
    
    public void insercaoDiretaLista()
       {
           Lista pi = Inicio.getProx(),ppos;
           int aux;
           
           while(pi != null)
           {
               aux=pi.getInfo();
               ppos=pi;
               while(ppos != Inicio && aux < ppos.getAnt().getInfo())
               {
                   ppos.setInfo(ppos.getAnt().getInfo());
                   ppos = ppos.getAnt();
               }
               ppos.setInfo(aux);
               pi=pi.getProx();
           }
       }
       
       public void selecaoDiretaLista()
       {
           Lista L = Inicio, posMenor, M;
           int menor;
           
           while(L.getProx() != null)
           {
               M = L.getProx();
               posMenor = L;
               menor = L.getInfo();
               while(M != null)
               {
                   if(M.getInfo() < menor)
                   {
                       menor = M.getInfo();
                       posMenor = M;
                   }
                   M = M.getProx();
               }
               posMenor.setInfo(L.getInfo());
               L.setInfo(menor);
               L = L.getProx();
           }
       }
       
       public void insercaoBinaria()
       {
           Lista aux, i, j;
           int aux2;
           int pos;
           
           for(i = Inicio.getProx(); i != null; i = i.getProx())
           {
                aux = i;
               
                pos = buscaBinaria(aux.getInfo(),i);
                
                if(pos !=-1)
                {
                   
                    aux2=aux.getInfo();
                    
                    for(j = i; TL(j) > pos; j = j.getAnt())
                        j.setInfo(j.getAnt().getInfo());
                    intToLista(pos-1).setInfo(aux2);
                    
                }
           }
       }
       
       public void bolha()
       {
           Lista TL2 = Fim;
           int aux;
           
           while(TL2 != Inicio)
           {
               for(Lista I = Inicio; I != TL2; I = I.getProx())
               {
                   if(I.getInfo() > I.getProx().getInfo())
                   {
                       aux = I.getInfo();
                       I.setInfo(I.getProx().getInfo());
                       I.getProx().setInfo(aux);
                   }
               }
               TL2 = TL2.getAnt();
           }
       }
       
      
       
       public void shake()
       {
           Lista fim = Fim;
           Lista ini = Inicio;
           Lista i;
           int aux;
           
           while(ini.getProx() != fim)
           {
               for(i = ini; i != fim; i = i.getProx())
               {
                   if(i.getInfo() > i.getProx().getInfo())
                   {
                       aux = i.getInfo();
                       i.setInfo(i.getProx().getInfo());
                       i.getProx().setInfo(aux);
                   }
               }
               
               fim = fim.getAnt();
               
               for(i = fim; i != ini.getProx(); i= i.getAnt())
               {
                   if(i.getInfo() < i.getAnt().getInfo())
                   {
                       aux = i.getInfo();
                       i.setInfo(i.getAnt().getInfo());
                       i.getAnt().setInfo(aux);
                   }
               }
               
               ini = ini.getProx();
           }
       }
       
       
       
       public void Shell()
       {    
         int dist;
         int aux;
           for (dist = 4; dist > 0; dist /= 2) 
           { 
        
              for (int i = 0; i < dist; i ++) 
              { 
                
                  for (int j = i; j+dist<TL(Fim);j=j+dist) 
                  {
                  
                      if(intToLista(j).getInfo()>intToLista(j+dist).getInfo())
                      {
                        
                        
                            aux = intToLista(j).getInfo();
                         intToLista(j).setInfo(intToLista(j+dist).getInfo());
                         intToLista(j+dist).setInfo(aux);
                      
                          
                          
                      }
                      for(int k =j+1;k-dist>=0 && intToLista(k-dist).getInfo()>intToLista(k).getInfo();k= k-dist)
                      {
                        
                            aux = intToLista(k).getInfo();
                         intToLista(k).setInfo(intToLista(k-dist).getInfo());
                         intToLista(k-dist).setInfo(aux);
                       
                      }
                  }
              } 
            } 
        }
       
       
       
       public void Heap()
       {
           Lista TL2=Fim;
           int pai,fd,fe,maior,aux;
           
           while(TL2.getAnt().getAnt()!=null)
           {
               pai=TL(TL2)/2-1;
                while(pai>=0)
                {
                    fe=2*pai+1;
                    fd=2*pai+2;
                    if(fd<TL(TL2)&& intToLista(fd).getInfo() > intToLista(fe).getInfo())
                        maior =fd;
                    else
                        maior = fe;
                    
                    if(intToLista(maior).getInfo()>intToLista(pai).getInfo())
                    {
                        aux = intToLista(maior).getInfo();
                         intToLista(maior).setInfo(intToLista(pai).getInfo());
                         intToLista(pai).setInfo(aux);
                        
                    }
                    pai--;
                }
                
                         aux = intToLista(0).getInfo();
                         intToLista(0).setInfo(intToLista(TL(TL2)-1).getInfo());
                         intToLista(TL(TL2)-1).setInfo(aux);
               
               
               TL2 = TL2.getAnt();
           }
       }
       
       public void QuickSemPivo()
       {
           QuickSP(Inicio,Fim);
       }
       
       public void QuickSP( Lista inicio,Lista finaal)
       {
           Lista ini = inicio,fim=finaal;
           int aux;
           
           while(TL(ini)<TL(fim))
           {
               while(TL(ini)<TL(fim)&& ini.getInfo()<=fim.getInfo())
                   ini=ini.getProx();
               
                         aux = ini.getInfo();
                         ini.setInfo(fim.getInfo());
                         fim.setInfo(aux);
                         
                while(TL(ini)<TL(fim)&& fim.getInfo()>=ini.getInfo())
                   fim=fim.getAnt();
               
                         aux = ini.getInfo();
                         ini.setInfo(fim.getInfo());
                         fim.setInfo(aux);
                         
               
           }
           
           if(TL(inicio)<TL(ini)-1)
               QuickSP(inicio, ini.getAnt());
           if(TL(fim)+1<TL(finaal))
               QuickSP(fim.getProx(), finaal);
       }
       
       
        public void QuickComPivo()
       {
           QuickCP(Inicio,Fim);
       }
       
       public void QuickCP( Lista inicio,Lista finaal)
       {
           Lista ini = inicio,fim=finaal,pivo = intToLista((TL(inicio)+TL(finaal))/2);
           int aux;
         
       
           while(TL(ini)<=TL(fim))
           {
               while(ini.getInfo()<pivo.getInfo())
                   ini=ini.getProx();
   
                while( fim.getInfo()>pivo.getInfo())
                   fim=fim.getAnt();
               
                if(TL(ini)<=TL(fim))
                {
                     aux = ini.getInfo();
                         ini.setInfo(fim.getInfo());
                         fim.setInfo(aux);
                          ini=ini.getProx();
                          fim=fim.getAnt();
                         
                }
                        
                         
               
           }
        
           if(TL(inicio)<TL(fim))
               QuickCP(inicio, ini.getAnt());
           if(TL(ini)<TL(finaal))
               QuickCP(fim.getProx(), finaal);
       }
       
       
       public void MergeUM()
       {
           int seq=1;
           DescritorLista L1 =new DescritorLista();
           DescritorLista L2 =new DescritorLista();
           while(seq<TL(Fim))
           {
               particao(L1,L2);
               fusao(L1,L2,seq);
               seq=seq*2;
           }    
       }
       
       public void particao(DescritorLista L1,DescritorLista L2)
       {
              int meio =TL(Fim)/2;
              L1.setInicio(null);
              L2.setInicio(null);
           for(int i=0;i<meio;i++)
           {
               L1.insereFim(new Lista(intToLista(i).getInfo()));
                L2.insereFim(new Lista(intToLista(meio+i).getInfo()));     
           }
       }
       public void fusao(DescritorLista L1,DescritorLista L2,int seq)
       {    
           int auxS=seq,k=0,i=0,j=0;
           while(k<TL(Fim))
           {
               while(i<seq && j<seq)
                   if( intToListaE(i, L1).getInfo()<intToListaE(j, L2).getInfo())
                        intToLista(k++).setInfo(intToListaE(i++, L1).getInfo());   
                   else
                   intToLista(k++).setInfo(intToListaE(j++, L2).getInfo()); 
               while(i<seq)
                         intToLista(k++).setInfo(intToListaE(i++, L1).getInfo());
               while(j<seq)
                        intToLista(k++).setInfo(intToListaE(j++, L2).getInfo());
               seq= seq+auxS;
              
               
           }
        }
       
       
       public void MergeDois()
       {
           DescritorLista L = new DescritorLista();
           L.setInicio(null);
              
           for(int i=0;i<TL(Fim);i++)
               L.insereFim(new Lista(intToLista(i).getInfo()));
              
           mergeS(L,0,TL(Fim)-1);
       }

    public void mergeS(DescritorLista L, int esq, int dir) 
    {
        int meio;
        
        if(esq<dir)
        {
            meio=(esq+dir)/2;
            mergeS(L,esq,meio);
            mergeS(L,meio+1,dir);
            fusãoS(L,esq,meio,meio+1,dir);
        }
        
    }

    private void fusãoS(DescritorLista L, int ini1, int fim1, int ini2, int fim2)
    {
        int i = ini1,j=ini2,k=0;
        
        while(i<=fim1&&j<=fim2)
            if(intToLista(i).getInfo() < intToLista(j).getInfo())
                intToListaE(k++,L).setInfo(intToLista(i++).getInfo()); 
            else
                 intToListaE(k++,L).setInfo(intToLista(j++).getInfo()); 
         while(i<=fim1)
                        intToListaE(k++,L).setInfo(intToLista(i++).getInfo());
         while(j<=fim2)
                        intToListaE(k++,L).setInfo(intToLista(j++).getInfo());
        for(i=0;i<k;i++)
            intToLista(ini1+i).setInfo(intToListaE(i,L).getInfo());
    }
    
    public int AchaMaior()
    {
        int i=0,maior=0;
        while(i<TL(Fim))
        {
            if(intToLista(i).getInfo()>maior)
                maior=intToLista(i).getInfo();
            i++;
        }
            return maior;
    }
    
    public void Counting()
    {
        DescritorLista L1 = new DescritorLista();
        DescritorLista LCop = new DescritorLista();
        
            for(int i=0;i<TL(Fim);i++)
               LCop.insereFim(new Lista(intToLista(i).getInfo()));
        
        int i=0,j=0,k=0,w=0;
        
          while(i<AchaMaior())
        {
            L1.insereFim(new Lista(0));
            i++;
        }
          
          while(j<TL(Fim))
          {
              intToListaE(intToLista(j).getInfo()-1, L1).setInfo((intToListaE(intToLista(j).getInfo()-1, L1).getInfo())+1);
              j++;
          }  
         
          
           while(k<AchaMaior()-1)
           {
               intToListaE(k+1, L1).setInfo(intToListaE(k, L1).getInfo()+intToListaE(k+1, L1).getInfo());
               k++;
           }
            j=0;
          while(j<AchaMaior())//todos -1 pq o "vetor" começa de 0
          {
               intToListaE(j, L1).setInfo(intToListaE(j, L1).getInfo()-1);    
              j++;
          }
           while(w< TL(Fim))
           {
               intToLista(intToListaE( intToListaE(w, LCop).getInfo()-1,L1).getInfo()).setInfo( intToListaE(w, LCop).getInfo());
               intToListaE( intToListaE(w, LCop).getInfo()-1,L1).setInfo(intToListaE( intToListaE(w, LCop).getInfo()-1,L1).getInfo()-1);//caso tenha valores iguais
               //System.out.println( "AAAAAAAAAAA"+ intToListaE( intToListaE(w, LCop).getInfo()-1,L1).getInfo());
               
               
               w++;
           }
          
        
    }
       
    
    
    public int vetToInt(int[] A,int cont)
    {
        int result=0;
        int Grr;
        
        for(int i=0;i<cont;i++)
        {
            Grr= (int)Math.pow(10,i);
            result= result + (A[i]*(Grr));     
        }
        return result;
    }
   
    
   
    public void Bucket()
    {
        int[] vet = new int[TL(Fim)];
        int maior_val = 0;
        Lista aux = Inicio;
        int tam = TL(Fim);
        
        for(int i = 0; i < tam; i++, aux = aux.getProx())
        {
            vet[i] = aux.getInfo();
            if(aux.getInfo() > maior_val)
                maior_val = aux.getInfo();
        }
        
        int[][] buckets = new int[4][tam];
        int[] tls = new int[4];
        
        for(int i = 0; i < 4; i++)
            tls[i] = 0;
        
        for(int i = 0; i < tam; i++)
        {
            if(vet[i] < maior_val * 0.25)
                buckets[0][tls[0]++] = vet[i];
            else if(vet[i] < maior_val * 0.5)
                buckets[1][tls[1]++] = vet[i];
            else if(vet[i] < maior_val * 0.75)
                buckets[2][tls[2]++] = vet[i];
            else
                buckets[3][tls[3]++] = vet[i];    
        }
        
        for(int i = 0; i < 4; i++)
        {
            for(int j = 0; j < tls[i] - 1; j++)
            {
                if(buckets[i][j] > buckets[i][j + 1])
                {
                    int a = buckets[i][j];
                    buckets[i][j] = buckets[i][j + 1];
                    buckets[i][j + 1] = a;
                }
            }
        }
        
        int tl = 0;
        
        for(int i = 0; i < 4; i++)
        {
            for(int j = 0; j < tls[i]; j++)
            {
                vet[tl++] = buckets[i][j];
            }
        }
        
        aux = Inicio;
        
        for(int i = 0; i < tam; i++, aux = aux.getProx())
        {
            aux.setInfo(vet[i]);
        }
    }
    
    public void Radix()
    {
       
        int[][] VV = new int[TL(Fim)][100];
         
            DescritorLista LCop = new DescritorLista();
        
            for(int i=0;i<TL(Fim);i++)
               LCop.insereFim(new Lista(intToLista(i).getInfo()));
            
             int[] vet = new int[100];
                 int f=0;
                 int a;
                 int cont=0;
                 int maiorCont=0;
           for(int w=0;w<TL(Fim);w++)//preeche o vetor de vetores
           {
               a=intToListaE(w, LCop).getInfo();
               cont=0;
               f=0;
                     while(a>0)//intToVet
                      {
                        vet[f]=a%10;
                        a=a/10;  
                        f++;    
                        cont++;
                     }
                    for(int j=0;j<cont;j++)
                    { 
                        VV[w][j]=vet[j];
                    }
                    if(cont>maiorCont)
                        maiorCont=cont; 
           }   
        int t=0;
            for(int h=0;h<maiorCont;h++)  
            {
               t=0; 
                
                    for(int p=0;p<=9;p++)
                    {
                         for(int s=0;s<TL(Fim);s++)
                         {
                            if(VV[s][h]==p)  
                            {
                                intToLista(t).setInfo(vetToInt(VV[s], maiorCont));
                                t++;
                            }
                         }   
                    }
///////////////////////////////////////////////////////////////////////////////
LCop.setInicio(null);
 VV = new int[TL(Fim)][100];
vet = new int[100];
      for(int i=0;i<TL(Fim);i++)
               LCop.insereFim(new Lista(intToLista(i).getInfo()));
                 f=0;
                 cont=0; 
           for(int w=0;w<TL(Fim);w++)//preeche o vetor de vetores
           {
               a=intToListaE(w, LCop).getInfo();
               cont=0;
               f=0;
                     while(a>0)//intToVet
                      {
                        vet[f]=a%10;
                        a=a/10;  
                        f++;    
                        cont++;
                     }
                    for(int j=0;j<cont;j++)
                    { 
                        VV[w][j]=vet[j];
                    }           
           }    
//////////////////////////////////////////////////////////////////////////////        
            }
          }
    
    
    public void Gnome()
    {
        int aux;
        for(int i=0;i<TL(Fim)-1;i++)
        {
            if(intToLista(i).getInfo()>intToLista(i).getProx().getInfo())
            {
                  aux = intToLista(i).getInfo();
                         intToLista(i).setInfo(intToLista(i).getProx().getInfo());
                         intToLista(i).getProx().setInfo(aux);
                         i=-1;
            }
        }
    }
    
    
    
    
    public void Comb()
    {
        int gap= TL(Fim),aux;
        while(gap>=1)
        {   
            for (int i = 0; i < TL(Fim); i++) 
            {
                if(i+gap<TL(Fim))
                {
                    if(intToLista(i).getInfo() > intToLista(i+gap).getInfo())
                    {
                        aux = intToLista(i).getInfo();
                         intToLista(i).setInfo(intToLista(i+gap).getInfo());
                         intToLista(i+gap).setInfo(aux);
                    } 
                }   
            }     
            gap =(int)(gap/1.3);
        }
    }
    
    void insertionSortdoTim( int left, int right) 
{ 
    int temp,j;
    for (int i = left + 1; i <= right; i++) 
    { 
         temp = intToLista(i).getInfo(); 
        j = i - 1; 
        while (intToLista(j).getInfo() > temp && j >= left) 
        { 
           
            intToLista(j+1).setInfo(intToLista(j).getInfo());
            j--; 
        }   
        intToLista(j+1).setInfo(temp);
    } 
} 
    
    
    public void MergedoTim(int l, int m, int r)  
    {  
        
        int len1 = m - l + 1, len2 = r - m;  
        int[] left = new int[len1]; 
        int[] right = new int[len2];  
        for (int x = 0; x < len1; x++) 
            left[x] = intToLista(l+x).getInfo();  
      
        for (int x = 0; x < len2; x++)  
            right[x] =intToLista(m + 1 + x).getInfo();  
        
        int i = 0;  
        int j = 0;  
        int k = l;  
       
        while (i < len1 && j < len2)  
        {  
            if (left[i] <= right[j])  
            {  
                intToLista(k).setInfo(left[i]); 
                i++;  
            }  
            else
            {  
                  intToLista(k).setInfo(right[j]); 
              
                j++;  
            }  
            k++;  
        }  
 
        while (i < len1)  
        {  
             intToLista(k).setInfo(left[i]); 
            
            k++;  
            i++;  
        }  
   
        while (j < len2)  
        { 
             intToLista(k).setInfo(right[j]); 
              
            k++;  
            j++;  
        }  
    }  
    
    
    
    public void Tim()
    {
        
        
        int n = TL(Fim),RUN = 32;
        int menor;
        
        for (int i = 0; i < n; i=i+RUN) 
        {
            if(i+31<n-1)
                menor=i+31;
            else
                menor=n-1;
             insertionSortdoTim(i,menor);
        }
           
           
        for (int size = RUN; size < n; size = 2*size)  
        {  
           
            for (int left = 0; left < n; left += 2*size)  
            {  
               
                int mid = left + size - 1;  
                int right = Math.min((left + 2*size - 1), (n-1));  
        
               
                MergedoTim( left, mid, right);  
            }  
        }  
        
        
    }
    
}

