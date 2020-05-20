import java.util.ArrayList;
import java.util.LinkedList;

public class Arvore {
	private No raiz;
	private LinkedList<No> base;

	public Arvore() {

	}

	/**
	 * Constroi a árvore atravez da base.
	 * 
	 * @param novaBase para a árvore
	 */
	public void fazerArvore(LinkedList<No> novaBase) {
		if (!verificarIntegridade(novaBase)) {
			throw new IllegalArgumentException("Base tem de ser uma potencia de 2 maior que 1!");
		}

		if (!verificarGenes(novaBase)) {
			throw new IllegalArgumentException("Todos os Nós da base têm de ter pelo menos um pár de genes");
		}

		int tamanhoGeracao = novaBase.size();

		base = (LinkedList<No>) novaBase.clone();

		while (tamanhoGeracao != 1) {
			for (int i = 0; i < tamanhoGeracao / 2; i++) {
				No pessoa1 = novaBase.remove();
				No pessoa2 = novaBase.remove();

				No descendente = new No();
				descendente.setFilhoEsquerda(pessoa1);
				descendente.setFilhoDireita(pessoa2);
				descendente.setNum(pessoa1.getNum() + pessoa2.getNum());

				novaBase.add(descendente);
			}
			tamanhoGeracao = tamanhoGeracao / 2;
		}
		raiz = novaBase.remove();
	}

	/**
	 * Verifica se a base é válida para fazer a a árvore, tem de ter um tamanho
	 * minimo de 2 e ser potencia de 2.
	 * 
	 * @param base da árvore
	 * @return true - caso seja uma base válida
	 */
	private boolean verificarIntegridade(LinkedList<No> base) {
		return base.size() > 1 && ((base.size() & (base.size() - 1)) == 0);
	}

	/**
	 * Verifica se todos os nós da base têm genes para o sismeta.
	 * 
	 * @param base da árvore
	 * @return true se todos os nós tiverem pelo menos 2 genes
	 */
	private boolean verificarGenes(LinkedList<No> base) {
		for (No no : base) {
			if (no.getGenes() == null || no.getGenes().size() < 2) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Ve por gerações, começa pela raiz.
	 * 
	 * @return uma lista dos nós
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

	// TODO: verificar se é dominante, comparado au outro gene
	public void probabilidades() {
		LinkedList<No> combinacoes = todasCombinacoes();
		LinkedList<String> vistos = new LinkedList<String>();
		LinkedList<Integer> ocurrencias = new LinkedList<Integer>();
		
		System.out.println("nº de combinacoes: " + combinacoes.size());

		for (No pessoa : combinacoes) { // vai a cada pessoa
			for (Gene gene : pessoa.getGenes()) { // vai a cada gene
				boolean existe = false;
				for (int i = 0; i < vistos.size(); i++) {
					String informacaoGene = vistos.get(i);
					if (gene.getDescricao().equals(informacaoGene)) {
						Integer ocorrencia = ocurrencias.get(i);
						ocorrencia++;
						ocurrencias.set(i, ocorrencia);
						existe = true;
						System.out.println("Existiu");
					}
				}
				if (!existe) {
					System.out.println("Não existiu");
					vistos.add(gene.getDescricao());
					ocurrencias.add(1);
				}
			}
		}
		for (int i = 0; i < vistos.size(); i++) {
			System.out.println(vistos.get(i) + ": " + ocurrencias.get(i));
		}
	}

	private LinkedList<No> todasCombinacoes() {
		LinkedList<No> faltaVizitar = (LinkedList<No>) base.clone();
		int tamanhoGeracao = faltaVizitar.size();

		while (tamanhoGeracao != 1) {
			for (int i = 0; i < tamanhoGeracao / 2; i++) {
				todasCombinacoes(faltaVizitar);
			}
			tamanhoGeracao = tamanhoGeracao / 2;
		}
		return faltaVizitar;
	}

	private void todasCombinacoes(LinkedList<No> faltaVizitar) {
		int tamanhoGeracao = faltaVizitar.size();

		for (int i = 0; i < tamanhoGeracao / 2; i++) {
			No pessoa1 = faltaVizitar.remove();
			No pessoa2 = faltaVizitar.remove();

			for (Gene p1 : pessoa1.getGenes()) {
				for (Gene p2 : pessoa2.getGenes()) {
					ArrayList<Gene> genes = new ArrayList<Gene>();
					genes.add(p1);
					genes.add(p2);
					No combinacao = new No();
					combinacao.setGenes(genes);
					faltaVizitar.add(combinacao);
				}
			}
		}
	}
}
