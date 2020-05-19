import java.util.ArrayList;

public class No {
    private No filhoDireita;
    private No filhoEsquerda;
    private ArrayList<Gene> genes;
    private int num; //For debugging

    public No(){
    	
    }
    
    public No(No filhoDireita, No filhoEsquerda, ArrayList<Gene> genes){
        this.filhoDireita = filhoDireita;
        this. filhoEsquerda = filhoEsquerda;
        this.genes = genes;
    }

	public No getFilhoDireita() {
		return filhoDireita;
	}

	public void setFilhoDireita(No filhoDireita) {
		this.filhoDireita = filhoDireita;
	}

	public No getFilhoEsquerda() {
		return filhoEsquerda;
	}

	public void setFilhoEsquerda(No filhoEsquerda) {
		this.filhoEsquerda = filhoEsquerda;
	}

	public ArrayList<Gene> getGenes() {
		return genes;
	}

	public void setGenes(ArrayList<Gene> genes) {
		this.genes = genes;
	}

	//For debugging
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	@Override
	public String toString() {
		return "No [num=" + num + "]";
	}
}
