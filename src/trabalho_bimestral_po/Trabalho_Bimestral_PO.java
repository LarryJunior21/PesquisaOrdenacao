package trabalho_bimestral_po;


public class Trabalho_Bimestral_PO 
{
    
    
    public static void geraTabela() {
        Arquivo arqOrd = new Arquivo("teste.txt"), arqRev = new Arquivo("randomicosss.txt"), arqRand = new Arquivo("randomico.txt");
        double tini, tfim, ttotalO, ttotalRev, ttotalRand;
        int compO, movO, compRev, movRev, compRand, movRand;
        //arqOrd.geraArquivoOrdenado();
        arqRev.geraArquivoReverso();
        //arqRand.geraArquivoRandomico();

        // arqOrd.exibe();
        // System.out.println("\n");
        // arqRand.exibe();
        // System.out.println("\n");
//        arqRev.exibe();
//        System.out.println("\n");
//        arqRev.QuickComPivo();
//        //... Insercao Direta ...
//        //Arquivo Ordenado
//        arqOrd.initComp();
//        arqOrd.initMov();
//        tini = System.currentTimeMillis(); //método para pegar a hora atual em milisegundos
//        arqOrd.insercaoDireta();
//        tfim = System.currentTimeMillis(); //método para pegar a hora atual em milisegundos
//        compO = arqOrd.getComp();
//        movO = arqOrd.getMov();
//        ttotalO = tfim - tini;
        //Arquivo Reverso
            //para arqRev para preservar o original
            arqRev.initComp();
            arqRev.initMov();
            tini = System.currentTimeMillis();
            arqRev.QuickComPivo();
            tfim = System.currentTimeMillis();
            ttotalRev = tfim - tini;
            compRev = arqRev.getComp();
            movRev = arqRev.getMov();

            System.out.println("COMP PROG:" + compRev + " | COMP EQUA:" + (int)Math.pow(2, 30 * Math.log(30)) + " | MOV PROG:" + movRev + " | MOV EQUA: " + " | Tempo:" + ttotalRev);
//        //Arquivo Randomico
//        auxRand.copiaArquivo(arqRand.getFile()); //faz uma cópia do arquivo de arqRand
//        //para auxRand para preservar o original
//        auxRand.initComp();
//        auxRand.initMov();
//        tini = System.currentTimeMillis();
//        auxRand.insercaoDireta();
//        tfim = System.currentTimeMillis();
//        ttotalRand = tfim - tini;
//        compRand = auxRand.getComp();
//        movRand = auxRand.getMov();
//        //grava na tabela informacoes os dados extraídos das execucoes do método
//        //Insercao Direta
//        gravaLinhaTabela(compO,
//                calculaCompInsDir(filesize()),
//                movO,
//                calculaMovInsDir(filesize()),
//                ttotalO, //tempo execução no arquivo Ordenado já convertido
//        );
    }
    
 public static void main(String args[])
    {
        geraTabela();
        /*Principal p = new Principal();
        p.geraTabela();*/
//        DescritorLista D = new DescritorLista();
//
//        D.insereInicio(new No(3));
//        D.insereInicio(new No(0));
//        D.insereInicio(new No(1));
//        D.insereInicio(new No(8));
//        D.insereInicio(new No(7));
//        D.insereInicio(new No(2));
//        D.insereInicio(new No(5));
//        D.insereFim(new No(4));
//        D.insereFim(new No(9));
//        D.insereFim(new No(6));
//        D.insereInicio(new No(30));
//        //  for(int i = 1024; i > 0; i--)
//        //   D.insereFim(new No(i));
//        System.out.println("ANTES");
//        D.exibeNo();
//        
//        
//        //D.insercaoDiretaNo();     //FUNCIONA 
//        //D.insercaoBinaria();      //NAO FUNCIONA 
//        //D.bolha();                //FUNCIONA 
//        //D.selecaoDiretaNo();      //FUNCIONA
//        //D.shake();                //FUNCIONA
//        //D.Shell();                //FUNCIONA
//        //D.heapSort();             //FUNCIONA
//        //D.QuickSemPivo();         //FUNCIONA 
//        //D.QuickComPivo();         //NAO FUNCIONA
//        //D.MergeUM();              //FUNCIONA ?   Lembrete: A quantidade de numeros no vetor tem q ser 2^X ex: 2^3=8 elementos
//        //D.MergeDois();            //FUNCIONA
//        //D.Counting();             //FUNCIONA   
//        //D.Bucket();               //NAO FUNCIONA
//        //D.Radix();                //FUNCIONA
//        //D.Comb();                 //FUNCIONA
//        //D.Gnome();                //FUNCIONA
//        //D.Tim();                  //FUNCIONA
//        
//        System.out.println("DEPOIS");
//        D.exibeNo();
    }
}
