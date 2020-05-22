
public class GeneResutado {
	private double nVezes = 0;
	private double probabilidade;
	private String descricao;
	
	public GeneResutado() {
		super();
	}

	public double getnVezes() {
		return nVezes;
	}

	public void setnVezes(double nVezes) {
		this.nVezes = nVezes;
	}

	public double getProbabilidade() {
		return probabilidade;
	}

	public void setProbabilidade(double probabilidade) {
		this.probabilidade = probabilidade;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public void adicionarNVezes() {
		this.nVezes++;
	}
	
	public void adicionarMetadeNVezes() {
		this.nVezes += 0.5;
	}
}
