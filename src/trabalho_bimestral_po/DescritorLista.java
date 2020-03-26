package trabalho_bimestral_po;

public class DescritorLista {

    private No Inicio;
    private No Fim;

    public DescritorLista() {
        Inicio = Fim = null;
    }

    public No getInicio() {
        return Inicio;
    }

    public void setInicio(No Inicio) {
        this.Inicio = Inicio;
    }

    public No getFim() {
        return Fim;
    }

    public void setFim(No Fim) {
        this.Fim = Fim;
    }

    public void insereInicio(No L) {
        if (Inicio == null) {
            Inicio = Fim = L;
        } else {
            L.setProx(Inicio);
            Inicio.setAnt(L);
            this.setInicio(L);
        }
    }

    public void insereFim(No L) {
        if (Inicio == null) {
            Inicio = Fim = L;
        }
        {
            L.setAnt(Fim);
            Fim.setProx(L);
            this.setFim(L);
        }
    }

    public int TL(No F) /// RETORNA A POSIÇÃO DO NO NA LISTA
    {
        int i = 0;

        No aux = Inicio;

        for (; aux != null && aux != F.getProx(); i++, aux = aux.getProx());

        return i;
    }

    public int TL(No ini, No fim) /// RETORNA A DISTANCIA ENTRE DOIS ELEMENTOS DA LISTA
    {
        int i = 0;

        No aux = ini;

        for (; aux != fim; i++, aux = aux.getProx());

        return i;
    }

    public No seekPont(No L, int pos) /// MOVE O PONTEIRO POS POSIÇÕES A PARTIR DA POSIÇÃO ATUAL, SE CHEGAR AO FINAL RETORNA NULL
    {
        if (pos > 0) {
            for (int i = 0; L != null && i < pos; i++) {
                L = L.getProx();
            }
        } else {
            for (; L != null && pos < 0; pos++) {
                L = L.getAnt();
            }
        }

        return L;
    }

    public int getPos(No L) /// RETORNA A POSIÇÃO DO ELEMENTO NA LISTA COMO UM INT
    {
        int i = 0;
        No aux = Inicio;

        for (; aux != L; i++, aux = aux.getProx());

        return i;
    }

    public No intToNo(int i) //RETORNA O NO DO NUMERO ENVIADO EX= i=3 RETORNA O TERCEIRO NÓ
    {
        No aux = Inicio;

        for (int j = 0; j < i; j++) {
            aux = aux.getProx();
        }

        return aux;
    }

    public No intToNoE(int i, DescritorLista L) {
        No aux = L.getInicio();

        for (int j = 0; j < i; j++) {
            aux = aux.getProx();
        }

        return aux;
    }

    public int buscaBinaria(int chave, No i) {
        No inicio = Inicio, fim = i, aux = Inicio;
        No meio = seekPont(aux, TL(inicio, fim) / 2);

        while (inicio.getInfo() < fim.getInfo() && chave >= meio.getInfo()) {
            if (chave > meio.getInfo()) {
                inicio = meio.getProx();
            } else {
                fim = meio.getAnt();
            }
            meio = seekPont(aux, (getPos(inicio) + getPos(fim)) / 2);
        }
        return TL(meio);
    }

    public void exibeNo() {
        No aux = Inicio;
        if (aux == null) {
            System.out.println("No vazia...");
        } else {
            while (aux != null) {
                System.out.println(aux.getInfo());
                aux = aux.getProx();
            }
        }
    }

    public void insercaoDiretaNo() {
        No pi = Inicio.getProx(), ppos;
        int aux;

        while (pi != null) {
            aux = pi.getInfo();
            ppos = pi;
            while (ppos != Inicio && aux < ppos.getAnt().getInfo()) {
                ppos.setInfo(ppos.getAnt().getInfo());
                ppos = ppos.getAnt();
            }
            ppos.setInfo(aux);
            pi = pi.getProx();
        }
    }

    public void selecaoDiretaNo() {
        No L = Inicio, posMenor, M;
        int menor;

        while (L.getProx() != null) {
            M = L.getProx();
            posMenor = L;
            menor = L.getInfo();
            while (M != null) {
                if (M.getInfo() < menor) {
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

    public void insercaoBinaria() {
        No aux, i, j;
        int aux2;
        int pos;

        for (i = Inicio.getProx(); i != null; i = i.getProx()) {
            aux = i;

            pos = buscaBinaria(aux.getInfo(), i);

            if (pos != -1) {

                aux2 = aux.getInfo();

                for (j = i; TL(j) > pos; j = j.getAnt()) {
                    j.setInfo(j.getAnt().getInfo());
                }
                intToNo(pos - 1).setInfo(aux2);

            }
        }
    }

    public void bolha() {
        No TL2 = Fim;
        int aux;

        while (TL2 != Inicio) {
            for (No I = Inicio; I != TL2; I = I.getProx()) {
                if (I.getInfo() > I.getProx().getInfo()) {
                    aux = I.getInfo();
                    I.setInfo(I.getProx().getInfo());
                    I.getProx().setInfo(aux);
                }
            }
            TL2 = TL2.getAnt();
        }
    }

    public void shake() {
        No fim = Fim;
        No ini = Inicio;
        No i;
        int aux;

        while (ini.getProx() != fim) {
            for (i = ini; i != null && i != fim; i = i.getProx()) {
                if (i.getInfo() > i.getProx().getInfo()) {
                    permuta(i.getProx(), i);
                    
                }
            }

            if(ini.getProx() != fim.getAnt())
            fim = fim.getAnt();
            
            for (i = fim; i != ini ; i = i.getAnt()) {
                if (i.getInfo() < i.getAnt().getInfo()) {
                    permuta(i.getAnt(), i);
                }
            }
            
            ini = ini.getProx();
        }
    }

    public void Shell() {
        int dist;
        int aux;
        for (dist = 4; dist > 0; dist /= 2) {

            for (int i = 0; i < dist; i++) {

                for (int j = i; j + dist < TL(Fim); j = j + dist) {

                    if (intToNo(j).getInfo() > intToNo(j + dist).getInfo()) {

                        aux = intToNo(j).getInfo();
                        intToNo(j).setInfo(intToNo(j + dist).getInfo());
                        intToNo(j + dist).setInfo(aux);

                    }
                    for (int k = j + 1; k - dist >= 0 && intToNo(k - dist).getInfo() > intToNo(k).getInfo(); k = k - dist) {

                        aux = intToNo(k).getInfo();
                        intToNo(k).setInfo(intToNo(k - dist).getInfo());
                        intToNo(k - dist).setInfo(aux);

                    }
                }
            }
        }
    }

    public int getIndex() {
        int i = 0;
        No aux = Inicio;

        while (aux != null) {
            aux = aux.getProx();
            i++;
        }

        return i;
    }

    private void permuta(No um, No dois) {
        int temp = um.getInfo();
        um.setInfo(dois.getInfo());
        dois.setInfo(temp);
    }

    void heapSort() {
        int FE;
        int FD;
        int pai;
        int TL2 = getIndex();
        No nFE;
        No nFD;
        No npai;
        No maiorFilho;
        No auxFim = Fim;

        while (TL2 > 1) {
            for (pai = TL2 / 2 - 1; pai >= 0; pai--) {
                FE = 2 * pai + 1;
                FD = 2 * pai + 2;

                nFE = intToNo(FE);
                nFD = intToNo(FD);
                npai = intToNo(pai);

                if (FD < TL2 && nFD.getInfo() > nFE.getInfo()) {
                    maiorFilho = nFD;
                } else {
                    maiorFilho = nFE;
                }

                if (npai.getInfo() < maiorFilho.getInfo()) {
                    permuta(npai, maiorFilho);
                }
            }

            permuta(Inicio, auxFim);
            auxFim = auxFim.getAnt();

            TL2--;
        }
    }

    public void QuickSemPivo() {
        QuickSP(Inicio, Fim);
    }

    public void QuickSP(No inicio, No finaal) {
        No ini = inicio, fim = finaal;
        int aux;

        while (TL(ini) < TL(fim)) {
            while (TL(ini) < TL(fim) && ini.getInfo() <= fim.getInfo()) {
                ini = ini.getProx();
            }

            aux = ini.getInfo();
            ini.setInfo(fim.getInfo());
            fim.setInfo(aux);

            while (TL(ini) < TL(fim) && fim.getInfo() >= ini.getInfo()) {
                fim = fim.getAnt();
            }

            aux = ini.getInfo();
            ini.setInfo(fim.getInfo());
            fim.setInfo(aux);

        }

        if (TL(inicio) < TL(ini) - 1) {
            QuickSP(inicio, ini.getAnt());
        }
        if (TL(fim) + 1 < TL(finaal)) {
            QuickSP(fim.getProx(), finaal);
        }
    }

    public void QuickComPivo() {
        QuickCP(Inicio, Fim);
    }

    public void QuickCP(No inicio, No finaal) {
        No ini = inicio, fim = finaal, pivo = intToNo((TL(inicio) + TL(finaal)) / 2);
        int aux;

        while (TL(ini) <= TL(fim)) {
            while (ini.getInfo() < pivo.getInfo()) {
                ini = ini.getProx();
            }

            while (fim.getInfo() > pivo.getInfo()) {
                fim = fim.getAnt();
            }

            if (TL(ini) <= TL(fim)) {
                aux = ini.getInfo();
                ini.setInfo(fim.getInfo());
                fim.setInfo(aux);
                ini = ini.getProx();
                fim = fim.getAnt();

            }

        }

        if (TL(inicio) < TL(fim)) {
            QuickCP(inicio, ini.getAnt());
        }
        if (TL(ini) < TL(finaal)) {
            QuickCP(fim.getProx(), finaal);
        }
    }

    public void MergeUM() {
        int seq = 1;
        DescritorLista L1 = new DescritorLista();
        DescritorLista L2 = new DescritorLista();
        while (seq < TL(Fim)) {
            particao(L1, L2);
            fusao(L1, L2, seq);
            seq = seq * 2;
        }
    }

    public void particao(DescritorLista L1, DescritorLista L2) {
        int meio = TL(Fim) / 2;
        L1.setInicio(null);
        L2.setInicio(null);
        for (int i = 0; i < meio; i++) {
            L1.insereFim(new No(intToNo(i).getInfo()));
            L2.insereFim(new No(intToNo(meio + i).getInfo()));
        }
    }

    public void fusao(DescritorLista L1, DescritorLista L2, int seq) {
        int auxS = seq, k = 0, i = 0, j = 0;
        while (k < TL(Fim)) {
            while (i < seq && j < seq) {
                if (intToNoE(i, L1).getInfo() < intToNoE(j, L2).getInfo()) {
                    intToNo(k++).setInfo(intToNoE(i++, L1).getInfo());
                } else {
                    intToNo(k++).setInfo(intToNoE(j++, L2).getInfo());
                }
            }
            while (i < seq) {
                intToNo(k++).setInfo(intToNoE(i++, L1).getInfo());
            }
            while (j < seq) {
                intToNo(k++).setInfo(intToNoE(j++, L2).getInfo());
            }
            seq = seq + auxS;

        }
    }

    public void MergeDois() {
        DescritorLista L = new DescritorLista();
        L.setInicio(null);

        for (int i = 0; i < TL(Fim); i++) {
            L.insereFim(new No(intToNo(i).getInfo()));
        }

        mergeS(L, 0, TL(Fim) - 1);
    }

    public void mergeS(DescritorLista L, int esq, int dir) {
        int meio;

        if (esq < dir) {
            meio = (esq + dir) / 2;
            mergeS(L, esq, meio);
            mergeS(L, meio + 1, dir);
            fusãoS(L, esq, meio, meio + 1, dir);
        }

    }

    private void fusãoS(DescritorLista L, int ini1, int fim1, int ini2, int fim2) {
        int i = ini1, j = ini2, k = 0;

        while (i <= fim1 && j <= fim2) {
            if (intToNo(i).getInfo() < intToNo(j).getInfo()) {
                intToNoE(k++, L).setInfo(intToNo(i++).getInfo());
            } else {
                intToNoE(k++, L).setInfo(intToNo(j++).getInfo());
            }
        }
        while (i <= fim1) {
            intToNoE(k++, L).setInfo(intToNo(i++).getInfo());
        }
        while (j <= fim2) {
            intToNoE(k++, L).setInfo(intToNo(j++).getInfo());
        }
        for (i = 0; i < k; i++) {
            intToNo(ini1 + i).setInfo(intToNoE(i, L).getInfo());
        }
    }

    public int AchaMaior() {
        int i = 0, maior = 0;
        while (i < TL(Fim)) {
            if (intToNo(i).getInfo() > maior) {
                maior = intToNo(i).getInfo();
            }
            i++;
        }
        return maior;
    }

    public void Counting() {
        DescritorLista L1 = new DescritorLista();
        DescritorLista LCop = new DescritorLista();

        for (int i = 0; i < TL(Fim); i++) {
            LCop.insereFim(new No(intToNo(i).getInfo()));
        }

        int i = 0, j = 0, k = 0, w = 0;

        while (i < AchaMaior()) {
            L1.insereFim(new No(0));
            i++;
        }

        while (j < TL(Fim)) {
            intToNoE(intToNo(j).getInfo() - 1, L1).setInfo((intToNoE(intToNo(j).getInfo() - 1, L1).getInfo()) + 1);
            j++;
        }

        while (k < AchaMaior() - 1) {
            intToNoE(k + 1, L1).setInfo(intToNoE(k, L1).getInfo() + intToNoE(k + 1, L1).getInfo());
            k++;
        }
        j = 0;
        while (j < AchaMaior())//todos -1 pq o "vetor" começa de 0
        {
            intToNoE(j, L1).setInfo(intToNoE(j, L1).getInfo() - 1);
            j++;
        }
        while (w < TL(Fim)) {
            intToNo(intToNoE(intToNoE(w, LCop).getInfo() - 1, L1).getInfo()).setInfo(intToNoE(w, LCop).getInfo());
            intToNoE(intToNoE(w, LCop).getInfo() - 1, L1).setInfo(intToNoE(intToNoE(w, LCop).getInfo() - 1, L1).getInfo() - 1);//caso tenha valores iguais
            //System.out.println( "AAAAAAAAAAA"+ intToNoE( intToNoE(w, LCop).getInfo()-1,L1).getInfo());

            w++;
        }

    }

    public int vetToInt(int[] A, int cont) {
        int result = 0;
        int Grr;

        for (int i = 0; i < cont; i++) {
            Grr = (int) Math.pow(10, i);
            result = result + (A[i] * (Grr));
        }
        return result;
    }

    public void Bucket() {
        int[] vet = new int[TL(Fim)];
        int maior_val = 0;
        No aux = Inicio;
        int tam = TL(Fim);

        for (int i = 0; i < tam; i++, aux = aux.getProx()) {
            vet[i] = aux.getInfo();
            if (aux.getInfo() > maior_val) {
                maior_val = aux.getInfo();
            }
        }

        int[][] buckets = new int[4][tam];
        int[] tls = new int[4];

        for (int i = 0; i < 4; i++) {
            tls[i] = 0;
        }

        for (int i = 0; i < tam; i++) {
            if (vet[i] < maior_val * 0.25) {
                buckets[0][tls[0]++] = vet[i];
            } else if (vet[i] < maior_val * 0.5) {
                buckets[1][tls[1]++] = vet[i];
            } else if (vet[i] < maior_val * 0.75) {
                buckets[2][tls[2]++] = vet[i];
            } else {
                buckets[3][tls[3]++] = vet[i];
            }
        }

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < tls[i] - 1; j++) {
                if (buckets[i][j] > buckets[i][j + 1]) {
                    int a = buckets[i][j];
                    buckets[i][j] = buckets[i][j + 1];
                    buckets[i][j + 1] = a;
                }
            }
        }

        int tl = 0;

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < tls[i]; j++) {
                vet[tl++] = buckets[i][j];
            }
        }

        aux = Inicio;

        for (int i = 0; i < tam; i++, aux = aux.getProx()) {
            aux.setInfo(vet[i]);
        }
    }

    public void Radix() {

        int[][] VV = new int[TL(Fim)][100];

        DescritorLista LCop = new DescritorLista();

        for (int i = 0; i < TL(Fim); i++) {
            LCop.insereFim(new No(intToNo(i).getInfo()));
        }

        int[] vet = new int[100];
        int f = 0;
        int a;
        int cont = 0;
        int maiorCont = 0;
        for (int w = 0; w < TL(Fim); w++)//preeche o vetor de vetores
        {
            a = intToNoE(w, LCop).getInfo();
            cont = 0;
            f = 0;
            while (a > 0)//intToVet
            {
                vet[f] = a % 10;
                a = a / 10;
                f++;
                cont++;
            }
            for (int j = 0; j < cont; j++) {
                VV[w][j] = vet[j];
            }
            if (cont > maiorCont) {
                maiorCont = cont;
            }
        }
        int t = 0;
        for (int h = 0; h < maiorCont; h++) {
            t = 0;

            for (int p = 0; p <= 9; p++) {
                for (int s = 0; s < TL(Fim); s++) {
                    if (VV[s][h] == p) {
                        intToNo(t).setInfo(vetToInt(VV[s], maiorCont));
                        t++;
                    }
                }
            }
///////////////////////////////////////////////////////////////////////////////
            LCop.setInicio(null);
            VV = new int[TL(Fim)][100];
            vet = new int[100];
            for (int i = 0; i < TL(Fim); i++) {
                LCop.insereFim(new No(intToNo(i).getInfo()));
            }
            f = 0;
            cont = 0;
            for (int w = 0; w < TL(Fim); w++)//preeche o vetor de vetores
            {
                a = intToNoE(w, LCop).getInfo();
                cont = 0;
                f = 0;
                while (a > 0)//intToVet
                {
                    vet[f] = a % 10;
                    a = a / 10;
                    f++;
                    cont++;
                }
                for (int j = 0; j < cont; j++) {
                    VV[w][j] = vet[j];
                }
            }
//////////////////////////////////////////////////////////////////////////////        
        }
    }

    public void Gnome() {
        int aux;
        for (int i = 0; i < TL(Fim) - 1; i++) {
            if (intToNo(i).getInfo() > intToNo(i).getProx().getInfo()) {
                aux = intToNo(i).getInfo();
                intToNo(i).setInfo(intToNo(i).getProx().getInfo());
                intToNo(i).getProx().setInfo(aux);
                i = -1;
            }
        }
    }

    public void Comb() {
        int gap = TL(Fim), aux;
        while (gap >= 1) {
            for (int i = 0; i < TL(Fim); i++) {
                if (i + gap < TL(Fim)) {
                    if (intToNo(i).getInfo() > intToNo(i + gap).getInfo()) {
                        aux = intToNo(i).getInfo();
                        intToNo(i).setInfo(intToNo(i + gap).getInfo());
                        intToNo(i + gap).setInfo(aux);
                    }
                }
            }
            gap = (int) (gap / 1.3);
        }
    }

    void insertionSortdoTim(int left, int right) {
        int temp, j;
        for (int i = left + 1; i <= right; i++) {
            temp = intToNo(i).getInfo();
            j = i - 1;
            while (intToNo(j).getInfo() > temp && j >= left) {

                intToNo(j + 1).setInfo(intToNo(j).getInfo());
                j--;
            }
            intToNo(j + 1).setInfo(temp);
        }
    }

    public void MergedoTim(int l, int m, int r) {

        int len1 = m - l + 1, len2 = r - m;
        int[] left = new int[len1];
        int[] right = new int[len2];
        for (int x = 0; x < len1; x++) {
            left[x] = intToNo(l + x).getInfo();
        }

        for (int x = 0; x < len2; x++) {
            right[x] = intToNo(m + 1 + x).getInfo();
        }

        int i = 0;
        int j = 0;
        int k = l;

        while (i < len1 && j < len2) {
            if (left[i] <= right[j]) {
                intToNo(k).setInfo(left[i]);
                i++;
            } else {
                intToNo(k).setInfo(right[j]);

                j++;
            }
            k++;
        }

        while (i < len1) {
            intToNo(k).setInfo(left[i]);

            k++;
            i++;
        }

        while (j < len2) {
            intToNo(k).setInfo(right[j]);

            k++;
            j++;
        }
    }

    public void Tim() {

        int n = TL(Fim), RUN = 32;
        int menor;

        for (int i = 0; i < n; i = i + RUN) {
            if (i + 31 < n - 1) {
                menor = i + 31;
            } else {
                menor = n - 1;
            }
            insertionSortdoTim(i, menor);
        }

        for (int size = RUN; size < n; size = 2 * size) {

            for (int left = 0; left < n; left += 2 * size) {

                int mid = left + size - 1;
                int right = Math.min((left + 2 * size - 1), (n - 1));

                MergedoTim(left, mid, right);
            }
        }

    }

}
