import java.util.ArrayList;

public class No {
	private No filhoEsquerda;
    private No filhoDireita;
    private ArrayList<ParGenes> pares;
    private int num;

    public No(){
    	
    }
    
    public No(No filhoEsquerda, No filhoDireita, ArrayList<ParGenes> pares, int num){
        this.filhoDireita = filhoDireita;
        this. filhoEsquerda = filhoEsquerda;
        this.pares = pares;
        this.num = num;
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

	public ArrayList<ParGenes> getParGenes() {
		return pares;
	}

	public void setParGenes(ArrayList<ParGenes> pares) {
		this.pares = pares;
	}
	
	public int getNum() {
		return num;
	}

	@Override
	public String toString() {
		//return "Pessoa [pares=" + pares + " num=" + num + "]";
		return "Pessoa [num=" + num + "]";
	}
}
