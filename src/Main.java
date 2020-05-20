import java.util.ArrayList;
import java.util.LinkedList;

public class Main {

	public static void main(String[] args) {
		Arvore arvore =  new Arvore();
		
		testeFazerArvore(arvore);
		testeProbabilidades(arvore);
		
	}
	
	public static void testeFazerArvore(Arvore arvore) {
		LinkedList<No> base =  new LinkedList<No>();
		
		criarPessoas(base, 2);
		
		/*
		Resultado esperado com 8:
		1 2 3 4 5 6 7 8
		3 7 11 15
		10 26
		36
		*/
		
		arvore.fazerArvore(base);
		System.out.println(arvore.travessiaLargura());
	}
	
	public static void criarPessoas(LinkedList<No> base, int nPessoas) {
		for(int i = 1; i <= nPessoas; i++) {
			No pessoa = new No();
			pessoa.setNum(i);
			
			ArrayList<Gene> genes =  new ArrayList<Gene>();
			genes.add(new Gene("Olhos castanhos", true));
			genes.add(new Gene("Olhos castanhos", true));
			
			pessoa.setGenes(genes);
			base.add(pessoa);
		}
	}
	
	/*
	 *
	public static Gene getGene() {
		ArrayList<String> genes = new ArrayList<String>();
		genes.add("Olhos azuis");
		genes.add("Olhos verdes");
		genes.add("Olhos castanhos");
	}
	*/
	
	public static void testeProbabilidades(Arvore arvore) {
		arvore.probabilidades();
	}

}
