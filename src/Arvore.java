import java.util.LinkedList;

public class Arvore {
	private No raiz;
	
	public Arvore() {
		
	}
	
	/**
	 * Constroi a árvore atravez da base
	 * @param base para a árvore
	 */
	public void fazerArvore(LinkedList<No> base) {
		if(!verificarIntegridade(base)) {
			throw new IllegalArgumentException("Base tem de ser uma potencia de 2 maior que 1!");
		}
		
		int tamanhoGeracao = base.size();
		
		while(tamanhoGeracao != 1) {
			for(int i = 0; i < tamanhoGeracao/2; i++) {
				No pessoa1 = base.remove();
				No pessoa2 = base.remove();
				
				No descendente = new No();
				descendente.setFilhoEsquerda(pessoa1);
				descendente.setFilhoDireita(pessoa2);
				descendente.setNum(pessoa1.getNum() + pessoa2.getNum());
				
				base.add(descendente);
			}
			tamanhoGeracao = tamanhoGeracao/2;
		}
		raiz = base.remove();
	}
	
	/**
	 * Verifica se a base é válida para fazer a a árvore,
	 * tem de ter um tamanho minimo de 2 e ser potencia de 2
	 * @param base da árvore
	 * @return true - caso seja uma base válida
	 */
	private boolean verificarIntegridade(LinkedList<No> base) {
		return base.size() > 1 && ((base.size() & (base.size() - 1)) == 0);
	}
	
	/**
	 * Ve por gerações, começa pela raiz
	 * @return uma lista dos nós
	 */
	public LinkedList<No> travessiaLargura(){
		LinkedList<No> visitar = new LinkedList<No>();
		LinkedList<No> visitados = new LinkedList<No>();
		if(raiz != null) {
			visitar.add(raiz);
		}
		while(!visitar.isEmpty()) {
			No atual = visitar.poll();
			visitados.add(atual);
			if(atual.getFilhoEsquerda() != null) {
				visitar.add(atual.getFilhoEsquerda());
			}
			if(atual.getFilhoDireita() != null) {
				visitar.add(atual.getFilhoDireita());
			}
		}
		return visitados;
	}
	
}
