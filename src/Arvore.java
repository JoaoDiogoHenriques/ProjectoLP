import java.util.ArrayList;
import java.util.LinkedList;

public class Arvore {
	private No raiz;
	private LinkedList<No> base;

	public Arvore() {

	}

	public LinkedList<No> getBase() {
		return base;
	}

	/**
	 * adiciona a primeira geração de pessoas
	 *
	 * @param novaBase
	 */
	public void addBase(LinkedList<No> novaBase) {
		if (!verificarIntegridade(novaBase)) {
			throw new IllegalArgumentException("Base tem de ser uma potencia de 2 maior que 1!");
		}

		if (!verificarGenes(novaBase)) {
			throw new IllegalArgumentException("Todos as pessoas da base tem de ter pelo menos um par de genes!");
		}

		// Falta verificar os pares
		if (!verificarSincroniaGenes(novaBase)) {
			throw new IllegalArgumentException("Os pares de genes tem de estar sincronizados!");
		}

		base = (LinkedList<No>) novaBase.clone();
	}

	/**
	 * Cria simulação da base com genes escolhidos aleatoriamente
	 *
	 * @return raiz;
	 */
	public No simulacao() {
		if (base == null) {
			throw new IllegalArgumentException("Base is empty");
		}

		LinkedList<No> novaBase = (LinkedList<No>) base.clone();

		int tamanhoGeracao = novaBase.size();

		while (tamanhoGeracao != 1) {
			for (int i = 0; i < tamanhoGeracao / 2; i++) {
				No pessoa1 = novaBase.remove();
				No pessoa2 = novaBase.remove();

				ArrayList<ParGenes> parGenes = new ArrayList<>();

				for (int j = 0; j < pessoa1.getParGenes().size(); j++) {
					ParGenes parGenesP1= pessoa1.getParGenes().get(j);
					ParGenes parGenesP2 = pessoa2.getParGenes().get(j);
					parGenes.add(new ParGenes(parGenesP1.escolher(), parGenesP2.escolher(), parGenesP1.getTipo()));
				}
				novaBase.add(new No(pessoa1, pessoa2, parGenes));
			}
			tamanhoGeracao = tamanhoGeracao / 2;
		}
		raiz = novaBase.remove();
		return raiz;
	}

	/**
	 * Verifica se a base e valida para fazer a arvore, tem de ter um tamanho mínimo
	 * de 2 e ser potencia de 2.
	 * 
	 * @param base da arvore
	 * @return true - caso seja uma base valida
	 */
	private boolean verificarIntegridade(LinkedList<No> base) {
		return base.size() > 1 && ((base.size() & (base.size() - 1)) == 0);
	}

	/**
	 * Verifica se todos os nos da base tem genes para o sistema.
	 * 
	 * @param base da arvore
	 * @return true se todos os nós tiverem pelo menos 2 genes
	 */
	private boolean verificarGenes(LinkedList<No> base) {
		for (No no : base) {
			if (no.getParGenes() == null || no.getParGenes().size() < 1) {
				return false;
			}
		}
		return true;
	}

	private boolean verificarSincroniaGenes(LinkedList<No> base) {
		for(No pessoa : base) {
			for(No outraPessoa : base) {
				for(int i = 0; i < pessoa.getParGenes().size(); i++) {
					if(!pessoa.getParGenes().get(i).getTipo().equals(outraPessoa.getParGenes().get(i).getTipo())) {
						return false;
					}
				}
			}
		}
		return true;
	}

	/**
	 * Ver por gerações, começa pela raiz.
	 * 
	 * @return uma lista dos nos
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

	/**
	 * Ver por Pre-Ordem
	 * 
	 * @return uma lista dos nos
	 */
	public LinkedList<No> preOrder() {
		if (raiz == null) {
			return null;
		}
		return preOrder(raiz);
	}

	private LinkedList<No> preOrder(No atual) {
		LinkedList<No> visitados = new LinkedList<No>();
		if (atual != null) {
			visitados.add(atual);
		}

		if (atual.getFilhoEsquerda() != null) {
			visitados.addAll(preOrder(atual.getFilhoEsquerda()));
		}

		if (atual.getFilhoDireita() != null) {
			visitados.addAll(preOrder(atual.getFilhoDireita()));
		}
		return visitados;
	}

	/**
	 * Ver por Post-Ordem
	 * 
	 * @return uma lista dos nos
	 */
	public LinkedList<No> postOrder() {
		if (raiz == null) {
			return null;
		}
		return postOrder(raiz);
	}

	private LinkedList<No> postOrder(No actual) {
		LinkedList<No> visitados = new LinkedList<No>();

		if (actual.getFilhoEsquerda() != null) {
			visitados.addAll(postOrder(actual.getFilhoEsquerda()));
		}

		if (actual.getFilhoDireita() != null) {
			visitados.addAll(postOrder(actual.getFilhoDireita()));
		}
		visitados.add(actual);
		return visitados;
	}

	/**
	 * Calcula as probabilidades de uma pessoa ter uma certa característica.
	 * 
	 * @return lista com os resultados
	 */
	public LinkedList<LinkedList<GeneResutado>> probabilidades() {
		LinkedList<No> combinacoes = todasCombinacoes();

		LinkedList<LinkedList<GeneResutado>> todosResultados = new LinkedList<LinkedList<GeneResutado>>();
		for (int i = 0; i < combinacoes.getFirst().getParGenes().size(); i++) {
			LinkedList<GeneResutado> geneResutados = new LinkedList<GeneResutado>();
			for (int j = 0; j < combinacoes.size(); j++) {
				ParGenes parGene = combinacoes.get(j).getParGenes().get(i); // Vai a cada pessoa buscar um par

				if (parGene.dominante() == null) { // Se for false/false or true/true

					boolean existe = false;
					for (GeneResutado geneResultado : geneResutados) {
						if (geneResultado.getDescricao().equals(parGene.getGene1().getDescricao())) {
							geneResultado.adicionarMetadeNVezes();
							existe = true;
							break;
						}
					}
					if (!existe) {
						registar(parGene.getGene1(), false, geneResutados, parGene.getTipo());
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
						registar(parGene.getGene2(), false, geneResutados, parGene.getTipo());
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
						registar(parGene.dominante(), true, geneResutados, parGene.getTipo());
					}
				}
			}
			todosResultados.add(geneResutados);
		}
		calcularProbabilidades(todosResultados);
		return todosResultados;
	}

	/**
	 * Combina todos os pares de pessoas, resultando em 4 possibilidades por par que
	 * por sua vez vão combinar com outras 4 possibilidades de outro par, vão gerar
	 * 64 outras possibilidades e o processo repete-se até esgotar as gerações
	 * 
	 * @return uma lista com todas as combinações possíveis
	 */
	private LinkedList<No> todasCombinacoes() {
		LinkedList<LinkedList<No>> todos = new LinkedList<>();
		LinkedList<No> baseClone = (LinkedList<No>) base.clone();

		int tamanhoBase = baseClone.size();
		for (int i = 0; i < tamanhoBase; i++) {
			LinkedList<No> familiares = new LinkedList<No>();
			familiares.add(baseClone.remove());
			todos.add(familiares);
		}

		while (todos.size() != 1) {
			for (int i = 0; i < todos.size() / 2; i++) {
				todos.add(combinarPar(todos.remove(), todos.remove()));
			}
		}
		return todos.getFirst();
	}

	/**
	 * Pega num conjunto faz todas as combinações possíveis com o outro conjunto,
	 * retorna o resultado
	 * 
	 * @param f1, conjunto de alternativas
	 * @param f2, conjunto de alternativas
	 * @return a combinação dos dois conjuntos
	 */
	private LinkedList<No> combinarPar(LinkedList<No> f1, LinkedList<No> f2) {
		LinkedList<No> novaCombinacao = new LinkedList<No>();
		for (No pessoa1 : f1) {
			for (No pessoa2 : f2) {
				// Combinações possíveis por par
				ArrayList<ParGenes> parGenes1 = new ArrayList<ParGenes>();
				ArrayList<ParGenes> parGenes2 = new ArrayList<ParGenes>();
				ArrayList<ParGenes> parGenes3 = new ArrayList<ParGenes>();
				ArrayList<ParGenes> parGenes4 = new ArrayList<ParGenes>();

				for (int i = 0; i < pessoa1.getParGenes().size(); i++) {// por as combinacoes possiveis

					ParGenes p1 = pessoa1.getParGenes().get(i);
					ParGenes p2 = pessoa2.getParGenes().get(i);

					// 4 pares diferentes vindo do mesmo par
					parGenes1.add(new ParGenes(p1.getGene1(), p2.getGene1(), p1.getTipo()));
					parGenes2.add(new ParGenes(p1.getGene1(), p2.getGene2(), p1.getTipo()));
					parGenes3.add(new ParGenes(p1.getGene2(), p2.getGene1(), p1.getTipo()));
					parGenes4.add(new ParGenes(p1.getGene2(), p2.getGene2(), p1.getTipo()));
				}
				// Adiciona ao novaCombinacao
				novaCombinacao.add(new No(null, null, parGenes1));
				novaCombinacao.add(new No(null, null, parGenes2));
				novaCombinacao.add(new No(null, null, parGenes3));
				novaCombinacao.add(new No(null, null, parGenes4));
			}
		}
		return novaCombinacao;
	}

	/**
	 * Regista o par de genes
	 * 
	 * @param gene,          gene a ser inserido
	 * @param dominante,     se e dominante
	 * @param geneResutados, lista de resultados
	 */
	private void registar(Gene gene, boolean dominante, LinkedList<GeneResutado> geneResutados, String tipo) {
		GeneResutado geneResutado = new GeneResutado();
		geneResutado.setTipo(tipo);
		geneResutado.setDescricao(gene.getDescricao());

		if (dominante) {
			geneResutado.adicionarNVezes();
		} else {
			geneResutado.adicionarMetadeNVezes();
		}
		geneResutados.add(geneResutado);
	}

	/**
	 * Calcula a probabilidade fazendo (casos favoráveis/ casos possíveis)*100.
	 * Regista o resultado dentro da classe GeneResultados
	 * 
	 * @param todosResultados, recebe todos os resultados
	 */
	private void calcularProbabilidades(LinkedList<LinkedList<GeneResutado>> todosResultados) {

		LinkedList<Double> totais = new LinkedList<Double>();
		for (LinkedList<GeneResutado> geneResultados : todosResultados) {
			double total = 0;
			for (GeneResutado geneResutado : geneResultados) {
				total = geneResutado.getnVezes() + total;
			}
			totais.add(total);
		}

		for (int i = 0; i < todosResultados.size(); i++) {
			for (GeneResutado geneResutado : todosResultados.get(i)) {
				geneResutado.setProbabilidade((geneResutado.getnVezes() / totais.get(i)) * 100);
			}
		}
	}
}
