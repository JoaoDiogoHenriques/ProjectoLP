public class ParGenes {
	private Gene gene1;
    private Gene gene2;

    public ParGenes(Gene gene1, Gene gene2){
        this.gene1 = gene1;
        this.gene2 = gene2;
    }

    public Gene getGene1() {
        return gene1;
    }

    public Gene getGene2() {
        return gene2;
    }

    public void setGene1(Gene gene1) {
		this.gene1 = gene1;
	}

	public void setGene2(Gene gene2) {
		this.gene2 = gene2;
	}

	//Escolher aleatoriamente um dos dois genes e retorna-lo
    public Gene escolher(){
        int range = 2;
        int rand = (int)(Math.random() * range) +1;

        if(rand == 1){
            return getGene1();
        } else {
            return getGene2();
        }
    }

    //Verificar dois genes e ver qual é dominante e devolve o dominante, se nenhum for dominante devolve null
    public Gene dominante() {
        if(gene1.isDominante() && gene2.isDominante()){
            return null;
        }
        if (gene1.isDominante()) {
            return gene1;
        }
        if (gene2.isDominante()){
            return gene2;
        }
        return null;
    }
    
    @Override
	public String toString() {
		return "ParGenes [" + gene1 + ", " + gene2 + "]";
	}
}
