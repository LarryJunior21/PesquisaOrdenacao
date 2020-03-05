package trabalho_bimestral_po;

/**
 *
 * @author Aluno
 */
public class Lista
{
    private Lista Ant;
    private Lista Prox;
    private int Info;

    public Lista(Lista Ant, Lista Prox, int Info)
    {
        this.Ant = Ant;
        this.Prox = Prox;
        this.Info = Info;
    }
    
    public Lista(int Info)
    {
        this.Ant = this.Prox = null;
        this.Info = Info;
    }
    
    public Lista getAnt()
    {
        return Ant;
    }

    public void setAnt(Lista Ant)
    {
        this.Ant = Ant;
    }

    public Lista getProx()
    {
        return Prox;
    }

    public void setProx(Lista Prox)
    {
        this.Prox = Prox;
    }

    public int getInfo()
    {
        return Info;
    }

    public void setInfo(int Info)
    {
        this.Info = Info;
    }
}
