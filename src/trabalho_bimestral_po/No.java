package trabalho_bimestral_po;

public class No
{
    private No Ant;
    private No Prox;
    private int Info;

    public No(No Ant, No Prox, int Info)
    {
        this.Ant = Ant;
        this.Prox = Prox;
        this.Info = Info;
    }
    
    public No(int Info)
    {
        this.Ant = this.Prox = null;
        this.Info = Info;
    }
    
    public No getAnt()
    {
        return Ant;
    }

    public void setAnt(No Ant)
    {
        this.Ant = Ant;
    }

    public No getProx()
    {
        return Prox;
    }

    public void setProx(No Prox)
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
