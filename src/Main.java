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
		//criarPessoas(base);
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
			
			ArrayList<ParGenes> parGenes =  new ArrayList<ParGenes>();
			parGenes.add(new ParGenes(new Gene("Olhos castanhos", true), new Gene("Olhos verdes", false)));
			parGenes.add(new ParGenes(new Gene("Cabelos castanhos", true), new Gene("Cabelos loiros", false)));
			pessoa.setParGenes(parGenes);
			base.add(pessoa);
		}
	}
	public static void criarPessoas(LinkedList<No> base) {
		No pessoa1 = new No();
		pessoa1.setNum(1);
		
		ArrayList<ParGenes> parGenes1 =  new ArrayList<ParGenes>();
		parGenes1.add(new ParGenes(new Gene("Olhos castanhos", true), new Gene("Olhos verdes", false)));
		parGenes1.add(new ParGenes(new Gene("Cabelos castanhos", true), new Gene("Cabelos castanhos", true)));
		pessoa1.setParGenes(parGenes1);
		base.add(pessoa1);
		
		No pessoa2 = new No();
		pessoa2.setNum(2);
		
		ArrayList<ParGenes> parGenes2 =  new ArrayList<ParGenes>();
		parGenes2.add(new ParGenes(new Gene("Olhos castanhos", true), new Gene("Olhos verdes", false)));
		parGenes2.add(new ParGenes(new Gene("Cabelos castanhos", true), new Gene("Cabelos loiros", false)));
		pessoa2.setParGenes(parGenes2);
		base.add(pessoa2);
		
		No pessoa3 = new No();
		pessoa3.setNum(3);
		
		ArrayList<ParGenes> parGenes3 =  new ArrayList<ParGenes>();
		parGenes3.add(new ParGenes(new Gene("Olhos castanhos", true), new Gene("Olhos verdes", false)));
		parGenes3.add(new ParGenes(new Gene("Cabelos castanhos", true), new Gene("Cabelos loiros", false)));
		pessoa3.setParGenes(parGenes3);
		base.add(pessoa3);
		
		No pessoa4 = new No();
		pessoa4.setNum(4);
		
		ArrayList<ParGenes> parGenes4 =  new ArrayList<ParGenes>();
		parGenes4.add(new ParGenes(new Gene("Olhos castanhos", true), new Gene("Olhos verdes", false)));
		parGenes4.add(new ParGenes(new Gene("Cabelos loiros", false), new Gene("Cabelos loiros", false)));
		pessoa4.setParGenes(parGenes4);
		base.add(pessoa4);
	}
	
	
	public static void testeProbabilidades(Arvore arvore) {
		LinkedList<LinkedList<GeneResutado>> resultados = arvore.probabilidades();
		
		for (LinkedList<GeneResutado> geneResutados : resultados) {
			for (GeneResutado geneResutado : geneResutados) {
				System.out.println(geneResutado.getDescricao() + " = " + geneResutado.getProbabilidade() + "%");
			}
			System.out.println("==================================");
		}
	}

}
