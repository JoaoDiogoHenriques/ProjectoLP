import java.util.ArrayList;

public class No {
    private No filhoDireita;
    private No filhoEsquerda;
    private ArrayList<ParGenes> pares;
    private int num; //For debugging

    public No(){
    	
    }
    
    public No(No filhoDireita, No filhoEsquerda, ArrayList<ParGenes> pares){
        this.filhoDireita = filhoDireita;
        this. filhoEsquerda = filhoEsquerda;
        this.pares = pares;
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
