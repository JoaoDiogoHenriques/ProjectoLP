import java.util.ArrayList;
import java.util.LinkedList;

public class Arvore {
	private No raiz;
	private LinkedList<No> base;

	public Arvore() {

	}

	/**
	 *  Cria simulacao da base com genes escolhidos aleatoriamente
	 *
	 * @return raiz;
	 */
	public No simulacao() {
		if(base == null) {
			throw new IllegalArgumentException("Base is empty");
		}

		LinkedList<No> novaBase = (LinkedList<No>) base.clone();

		int tamanhoGeracao = novaBase.size();

		while (tamanhoGeracao != 1) {
			for (int i = 0; i < tamanhoGeracao / 2; i++) {
				No pessoa1 = novaBase.remove();
				No pessoa2 = novaBase.remove();

				No descendente = new No();

				ArrayList<ParGenes> parGenes = new ArrayList<>();

				for(int j = 0; j < pessoa1.getParGenes().size(); j++){
					ParGenes p = new ParGenes(pessoa1.getParGenes().get(i).escolher(), pessoa2.getParGenes().get(i).escolher());
					parGenes.add(p);
				}

				descendente.setParGenes(parGenes);

				descendente.setFilhoEsquerda(pessoa1);
				descendente.setFilhoDireita(pessoa2);
				descendente.setNum(pessoa1.getNum() + pessoa2.getNum());

				novaBase.add(descendente);
			}
			tamanhoGeracao = tamanhoGeracao / 2;
		}
		raiz = novaBase.remove();
		return raiz;
	}

	/**
	 *  adiciona a primeira geração de pessoas
	 *
	 * @param novaBase
	 */
	public void addBase(LinkedList<No> novaBase){
		if (!verificarIntegridade(novaBase)) {
			throw new IllegalArgumentException("Base tem de ser uma potencia de 2 maior que 1!");
		}

		if (!verificarGenes(novaBase)) {
			throw new IllegalArgumentException("Todos os N�s da base t�m de ter pelo menos um p�r de genes");
		}

		base = (LinkedList<No>) novaBase.clone();
	}
	/**
	 * Verifica se a base � v�lida para fazer a a �rvore, tem de ter um tamanho
	 * minimo de 2 e ser potencia de 2.
	 * 
	 * @param base da �rvore
	 * @return true - caso seja uma base v�lida
	 */
	private boolean verificarIntegridade(LinkedList<No> base) {
		return base.size() > 1 && ((base.size() & (base.size() - 1)) == 0);
	}

	/**
	 * Verifica se todos os n�s da base t�m genes para o sismeta.
	 * 
	 * @param base da �rvore
	 * @return true se todos os n�s tiverem pelo menos 2 genes
	 */
	private boolean verificarGenes(LinkedList<No> base) {
		for (No no : base) {
			if (no.getParGenes() == null || no.getParGenes().size() < 1) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Ve por gera��es, come�a pela raiz.
	 * 
	 * @return uma lista dos n�s
	 */
	public LinkedList<No> travessiaLargura() {
		LinkedList<No> visitar = new LinkedList<No>();
		LinkedList<No> visitados = new LinkedList<No>();
		if (raiz != null) {
			visitar.add(raiz);
		}
		while (!visitar.isEmpty()) {
			No atual = visitar.poll();
			visitados.add(atual);
			if (atual.getFilhoEsquerda() != null) {
				visitar.add(atual.getFilhoEsquerda());
			}
			if (atual.getFilhoDireita() != null) {
				visitar.add(atual.getFilhoDireita());
			}
		}
		return visitados;
	}

	
	public LinkedList<LinkedList<GeneResutado>> probabilidades() {
		LinkedList<No> combinacoes = todasCombinacoes();
		System.out.println("n� de combinacoes: " + combinacoes.size());
		
		LinkedList<LinkedList<GeneResutado>> todosResultados = new LinkedList<LinkedList<GeneResutado>>();
		for (int i = 0; i < combinacoes.getFirst().getParGenes().size(); i++) {
			LinkedList<GeneResutado> geneResutados = new LinkedList<GeneResutado>();
			for (int j = 0; j < combinacoes.size(); j++) {
				ParGenes parGene = combinacoes.get(j).getParGenes().get(i); //Vai a cada pessoa boscar um par
				
				if (parGene.dominante() == null) { // for false/false or true/true

					boolean existe = false;
					for (GeneResutado geneResultado : geneResutados) {
						if (geneResultado.getDescricao().equals(parGene.getGene1().getDescricao())) {
							geneResultado.adicionarMetadeNVezes();
							existe = true;
							break;
						}
					}
					if (!existe) {
						registar(parGene.getGene1(), false, geneResutados);
					} else {
						existe = false;
					}

					for (GeneResutado geneResultado : geneResutados) {
						if (geneResultado.getDescricao().equals(parGene.getGene2().getDescricao())) {
							geneResultado.adicionarMetadeNVezes();
							existe = true;
							break;
						}
					}
					if (!existe) {
						registar(parGene.getGene2(), false, geneResutados);
					}
				} else {
					boolean existe = false;
					for (GeneResutado geneResultado : geneResutados) {
						if (geneResultado.getDescricao().equals(parGene.dominante().getDescricao())) {
							geneResultado.adicionarNVezes();
							existe = true;
							break;
						}
					}
					if (!existe) {
						registar(parGene.dominante(), true, geneResutados);
					}
				}
			}
			todosResultados.add(geneResutados);
		}
		calcularProbabilidades(todosResultados);
		return todosResultados;
	}

	private LinkedList<No> todasCombinacoes() {	
		LinkedList<LinkedList<No>> todos = new LinkedList<>();
		LinkedList<No> baseClone = (LinkedList<No>) base.clone();
		
		System.out.println("Tamanho da base: " + baseClone.size());
		
		int tamanhoBase = baseClone.size();
		for(int i = 0; i < tamanhoBase; i++) {
			LinkedList<No> familiares = new LinkedList<No>();
			familiares.add(baseClone.remove());
			todos.add(familiares);
		}
		
		System.out.println("Tamanho dos todos: " + todos.size());
		
		while(todos.size() != 1) {
			for (int i = 0; i < todos.size()/2; i++) {
				todos.add(combinarPar(todos.remove(), todos.remove()));
			}
		}
		return todos.getFirst();
	}
	
	private LinkedList<No> combinarPar(LinkedList<No> f1, LinkedList<No> f2){
		LinkedList<No> novaFamilia = new LinkedList<No>();
		for (No pessoa1 : f1) {
			for (No pessoa2 : f2) {
				ArrayList<ParGenes> parGenes1 = new ArrayList<ParGenes>(); // Combina��es possiveis por par
				ArrayList<ParGenes> parGenes2 = new ArrayList<ParGenes>();
				ArrayList<ParGenes> parGenes3 = new ArrayList<ParGenes>();
				ArrayList<ParGenes> parGenes4 = new ArrayList<ParGenes>();

				for (int i = 0; i < pessoa1.getParGenes().size(); i++) {// por as combina��es possiveis

					ParGenes p1 = pessoa1.getParGenes().get(i);
					ParGenes p2 = pessoa2.getParGenes().get(i);

					// 4 pares diferentes vindo do memso par
					parGenes1.add(new ParGenes(p1.getGene1(), p2.getGene1()));
					parGenes2.add(new ParGenes(p1.getGene1(), p2.getGene2()));
					parGenes3.add(new ParGenes(p1.getGene2(), p2.getGene1()));
					parGenes4.add(new ParGenes(p1.getGene2(), p2.getGene2()));
				}
				novaFamilia.add(new No(null, null, parGenes1)); //Adiciona ao vizitar
				novaFamilia.add(new No(null, null, parGenes2));
				novaFamilia.add(new No(null, null, parGenes3));
				novaFamilia.add(new No(null, null, parGenes4));
			}
		}
		return novaFamilia;
	}

	private void registar(Gene gene, boolean dominante, LinkedList<GeneResutado> geneResutados) {
		GeneResutado geneResutado = new GeneResutado();
		geneResutado.setDescricao(gene.getDescricao());

		if (dominante) {
			geneResutado.adicionarNVezes();
		} else {
			geneResutado.adicionarMetadeNVezes();
		}
		geneResutados.add(geneResutado);
	}
	
	private void calcularProbabilidades(LinkedList<LinkedList<GeneResutado>> todosResultados) {
		
		LinkedList<Double> totais = new LinkedList<Double>();
		for(LinkedList<GeneResutado> geneResultados : todosResultados) {
			double total = 0;
			for (GeneResutado geneResutado : geneResultados) {
				total = geneResutado.getnVezes() + total;
			}
			totais.add(total);
		}
		
		for(int i = 0; i < todosResultados.size(); i++) {
			for (GeneResutado geneResutado : todosResultados.get(i)) {
				geneResutado.setProbabilidade((geneResutado.getnVezes()/totais.get(i))*100);
			}
		}
	}
}
