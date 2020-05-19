import java.util.ArrayList;

public class No {
    private No filhoDireita;
    private No filhoEsquerda;
    private ArrayList<Gene> genes = new ArrayList<Gene>();

    public No(No filhoDireita, No filhoEsquerda, ArrayList<Gene> genes){
        this.filhoDireita = filhoDireita;
        this. filhoEsquerda = filhoEsquerda;
        this.genes = genes;
    }

    public No getFilhoDireita() {
        return filhoDireita;
    }

    public No getFilhoEsquerda() {
        return filhoEsquerda;
    }

    public ArrayList<Gene> getGenes() {
        return genes;
    }
}
