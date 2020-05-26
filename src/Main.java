import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {

	static Scanner scan;

	public static void main(String[] args) {

		scan = new Scanner(System.in);

		System.out.println("Bem vindo!");
		start();
		System.out.println("encerrar!");

	}

	public static void start() {
		
		int opcao;
		do {
			opcao = menu();
			switch (opcao) {
			case 1:
				criarPrimeiraGeracao();
				break;
			case 2:
				geracaoTestes();
				break;
			}
		} while (!(opcao == 0));
	}

	public static int menu() {
		System.out.println("\n\n1 - Criar a primeira geracao");
		System.out.println("2 - Usar geracao de testes");
		System.out.println("0 - Voltar\n\n");
		return inputInt();
	}

	public static void criarPrimeiraGeracao() {
		LinkedList<No> pessoas = new LinkedList<No>();
		System.out.println("Escolha o numero de geracoes? O numero de pessoas sera 2^geracoes.");
		int numeroGeracoes = inputInt();
		int numeroPessoas = (int) Math.pow(2, numeroGeracoes);
		System.out.println("Vai ter " + numeroPessoas + " pessoas na primeira geracao");

		System.out.println("Quantos pares de genes quer testar?");
		int numeroParGenes = inputInt();

		for (int i = 0; i < numeroPessoas; i++) {
			No pessoa = new No();
			ArrayList<ParGenes> parGenes = new ArrayList<ParGenes>();

			for (int j = 0; j < numeroParGenes; j++) {
				System.out.println((j + 1) + "º Par de genes da " + (i + 1) + "ª Pessoa");

				String tipo;
				if(pessoas.isEmpty()) {
					System.out.println("Qual e o tipo de gene? (Cor de Olhos, Cor do cabelo...)");
					tipo = scan.nextLine();
				} else {
					tipo = null;
				}

				parGenes.add(new ParGenes(criarGene(tipo), criarGene(tipo)));
				pessoa.setParGenes(parGenes);
			}
			pessoas.add(pessoa);
		}
		Arvore arvore = new Arvore();
		arvore.addBase(pessoas);
		funcionalidades(pessoas, arvore);
	}

	public static Gene criarGene(String tipo) {
		System.out.println("===NOVO GENE===");
		System.out.println("Escreva uma descricao para o gene:");
		String descricao = scan.nextLine();

		System.out.println("E dominador? Y/y = sim | Outro valor = nao");
		boolean deminador = scan.nextLine().equalsIgnoreCase("y");

		return new Gene(descricao, deminador);
	}

	public static void geracaoTestes() {
		LinkedList<No> pessoas = new LinkedList<No>();

		ArrayList<ParGenes> parGenes1 = new ArrayList<ParGenes>();
		parGenes1.add(new ParGenes(new Gene("Olhos castanhos", true), new Gene("Olhos verdes", false)));
		parGenes1.add(new ParGenes(new Gene("Cabelos castanhos", true), new Gene("Cabelos castanhos", true)));
		pessoas.add(new No(null, null, parGenes1, 1));

		ArrayList<ParGenes> parGenes2 = new ArrayList<ParGenes>();
		parGenes2.add(new ParGenes(new Gene("Olhos castanhos", true), new Gene("Olhos verdes", false)));
		parGenes2.add(new ParGenes(new Gene("Cabelos castanhos", true), new Gene("Cabelos loiros", false)));
		pessoas.add(new No(null, null, parGenes2, 2));

		ArrayList<ParGenes> parGenes3 = new ArrayList<ParGenes>();
		parGenes3.add(new ParGenes(new Gene("Olhos castanhos", true), new Gene("Olhos verdes", false)));
		parGenes3.add(new ParGenes(new Gene("Cabelos castanhos", true), new Gene("Cabelos loiros", false)));
		pessoas.add(new No(null, null, parGenes3, 3));

		ArrayList<ParGenes> parGenes4 = new ArrayList<ParGenes>();
		parGenes4.add(new ParGenes(new Gene("Olhos castanhos", true), new Gene("Olhos verdes", false)));
		parGenes4.add(new ParGenes(new Gene("Cabelos loiros", false), new Gene("Cabelos loiros", false)));
		pessoas.add(new No(null, null, parGenes4, 4));

		Arvore arvore = new Arvore();
		arvore.addBase(pessoas);
		funcionalidades(pessoas, arvore);
	}

	public static void funcionalidades(LinkedList<No> pessoas, Arvore arvore) {

		int opcao;
		do {
			opcao = menuFuncionalidade();
			switch (opcao) {
			case 1:
				mostrarGeracao(pessoas);
				break;
			case 2:
				editarGeracao(pessoas);
				break;
			case 3:
				testeSimulacao(arvore);
				break;
			case 4:
				testeProbabilidades(arvore);
				break;
			}
		} while (!(opcao == 0));
	}

	public static int menuFuncionalidade() {
		System.out.println("\n\n1 - Ver a primeira geracao");
		System.out.println("2 - Editar a geracao");
		System.out.println("3 - Simulacao");
		System.out.println("4 - Probabilidades");
		System.out.println("0 - Voltar\n\n");
		return inputInt();
	}

	public static void mostrarGeracao(LinkedList<No> pessoas) {
		System.out.println("A primeira geracao tem esta constituicao:");
		System.out.println("Nº de pessoas: " + pessoas.size());
		for (No pessoa : pessoas) {
			System.out.println(pessoa.toString());
		}
	}

	public static void editarGeracao(LinkedList<No> pessoas) {
		System.out.println("Escolha uma pessoa para editar:\n");
		for (int i = 0; i < pessoas.size(); i++) {
			System.out.println((i + 1) + " - " + pessoas.get(i));
		}
		try {
			No pessoa = pessoas.get(inputInt() - 1);
			editarPessoa(pessoa);
		} catch (IndexOutOfBoundsException e) {
			System.out.println("A pessoa nao existe, operacao cancelada!");
			return;
		}
	}

	public static void editarPessoa(No pessoa) {
		System.out.println("\nEscolha um par de genes para editar:\n");
		for (int i = 0; i < pessoa.getParGenes().size(); i++) {
			System.out.println((i + 1) + " - " + pessoa.getParGenes().get(i));
		}
		try {
			ParGenes parGenes = pessoa.getParGenes().get(inputInt() - 1);
			parGenes.setGene1(criarGene(null)); //TODO: falta por o tipo de gene
			parGenes.setGene2(criarGene(null));
		} catch (IndexOutOfBoundsException e) {
			System.out.println("O par de genes nao existe, operacao cancelada!");
			return;
		}
	}

	public static int inputInt() {
		try {
			return Integer.parseInt(scan.nextLine());
		} catch (NumberFormatException e) {
			System.out.println("Por favor meta um numero inteiro!");
			return inputInt();
		}
	}

	public static void testeSimulacao(Arvore arvore) {
		int opcao;
		do {
			opcao = menuTesteSimulacao();
			No raiz = arvore.simulacao();
			switch (opcao) {
			case 1:
				System.out.println("O resultado da ultima geracao foi:\n" + raiz);
				break;
			case 2:
				System.out.println(arvore.travessiaLargura());
				break;
			case 3:
				System.out.println(arvore.preOrder());
				break;
			case 4:
				System.out.println(arvore.postOrder());
				break;
			}
		} while (!(opcao == 0));
	}
	
	public static int menuTesteSimulacao() {
		System.out.println("\n\n1 - Ver apenas o resultado final");
		System.out.println("2 - Ver tudo em largura");
		System.out.println("3 - Ver tudo em pre-order");
		System.out.println("4 - Ver tudo em Post-order");
		System.out.println("0 - Voltar\n\n");
		return inputInt();
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
