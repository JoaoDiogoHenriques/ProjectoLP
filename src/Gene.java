public class Gene {
    private String descricao;
    private boolean dominante;

    public Gene(String descricao, boolean dominante){
        this.descricao = descricao;
        this.dominante = dominante;
    }

    public String getDescricao() {
        return descricao;
    }

    public boolean isDominante() {
        return dominante;
    }

	@Override
	public String toString() {
		return "Gene [" + descricao + ", dominante=" + dominante + "]";
	}
}
