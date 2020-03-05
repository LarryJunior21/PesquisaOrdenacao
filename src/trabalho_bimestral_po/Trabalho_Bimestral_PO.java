package trabalho_bimestral_po;


public class Trabalho_Bimestral_PO 
{
    Arquivo arqOrd, arqRev, arqRand, auxRev, auxRand;
    /*public void geraTabela()
    {
        arqOrd.geraArquivoOrdenado();
        arqRev.geraArquivoReverso();
        arqRand.geraArquivoRandomico();
        //... Insercao Direta ...
        //Arquivo Ordenado
        arqOrd.initComp();
        arqOrd.initMov();
        tini = System.currentTimeMillis(); //método para pegar a hora atual em milisegundos
        arqOrd.isercaoDireta();
        tfim = System.currentTimeMillis(); //método para pegar a hora atual em milisegundos
        compO = arqOrd.getComp();
        movO = arqOrd.getMov();
        ttotalO = tfim - tini;
        //Arquivo Reverso
        auxRev.copiaArquivo(arqRev.getFile()); //faz uma cópia do arquivo de arqRev
        //para auxRev para preservar o original
        auxRev.initComp();
        auxRev.initMov();
        tini = System.currentTimeMillis();
        auxRev.isercaoDireta();
        tfim = System.currentTimeMillis();
        ttotalRev = tfim - tini;
        compRev = auxRev.getComp();
        movRev = auxRev.getMov();
        //Arquivo Randomico
        auxRand.copiaArquivo(arqRand.getFile()); //faz uma cópia do arquivo de arqRand
        //para auxRand para preservar o original
        auxRand.initComp();
        auxRand.initMov();
        tini = System.currentTimeMillis();
        4
        auxRand.isercaoDireta();
        tfim = System.currentTimeMillis();
        ttotalRand = tfim - tini;
        compRand = auxRand.getComp();
        movRand = auxRand.getMov();
        //grava na tabela informacoes os dados extraídos das execucoes do método
        //Insercao Direta
        gravaLinhaTabela(compO,
                calculaCompInsDir(filesize()),
                movO,
                calculaMovInsDir(filesize()),
                ttotalO, //tempo execução no arquivo Ordenado já convertido
 //para segundos
    

    ...
)
 //... fim Insercao Direta
 //e assim continua para os outros métodos de ordenacao!!!
 }*/
    
 public static void main(String args[])
    {
        /*Principal p = new Principal();
        p.geraTabela();*/
        DescritorLista D = new DescritorLista();
        
        D.insereInicio(new Lista(4));
       D.insereInicio(new Lista(5));
       D.insereInicio(new Lista(2));
        D.insereInicio(new Lista(1));
        D.insereInicio(new Lista(1));
   //D.insereInicio(new Lista(1));
       D.insereInicio(new Lista(3));
   //  D.insereFim(new Lista(606));
      D.insereFim(new Lista(20));
       D.insereFim(new Lista(50));
       // D.insereFim(new Lista(211));
      // D.insereFim(new Lista(30));
          //  for(int i = 1024; i > 0; i--)
             //   D.insereFim(new Lista(i));
        System.out.println("ANTES");
        D.exibeLista();
        
        
        //D.insercaoDiretaLista();  //FUNCIONA 
        //D.insercaoBinaria();      //FUNCIONA 
        //D.bolha();                //FUNCIONA 
        //D.selecaoDiretaLista();   //FUNCIONA
        //D.shake();                //FUNCIONA
        //D.Shell();                //FUNCIONA
        //D.Heap();                 //FUNCIONA
        //D.QuickSemPivo();         //FUNCIONA 
        //D.MergeUM();              //FUNCIONA   Lembrete: A quantidade de numeros no vetor tem q ser 2^X ex: 2^3=8 elementos
        //D.MergeDois();            //FUNCIONA
        //D.Counting();             //FUNCIONA   
        //D.Bucket();               //FUNCIONA
        //D.Radix();                //FUNCIONA
        //D.Comb();                 //FUNCIONA
        //D.Gnome();                //FUNCIONA
        //D.Tim();                  //FUNCIONA
       

        System.out.println("DEPOIS");
        D.exibeLista();
        
       
       
    }
}
