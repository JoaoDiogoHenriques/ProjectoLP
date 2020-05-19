import java.util.LinkedList;

public class Main {

	public static void main(String[] args) {
		Arvore arvore =  new Arvore();
		
		LinkedList<No> base =  new LinkedList<No>();
		No pessoa1 = new No();
		pessoa1.setNum(1);
		base.add(pessoa1);
		
		No pessoa2 = new No();
		pessoa2.setNum(2);
		base.add(pessoa2);
		
		No pessoa3 = new No();
		pessoa3.setNum(3);
		base.add(pessoa3);
		
		No pessoa4 = new No();
		pessoa4.setNum(4);
		base.add(pessoa4);
		
		No pessoa5 = new No();
		pessoa5.setNum(5);
		base.add(pessoa5);
		
		No pessoa6 = new No();
		pessoa6.setNum(6);
		base.add(pessoa6);
		
		No pessoa7 = new No();
		pessoa7.setNum(7);
		base.add(pessoa7);
		
		No pessoa8 = new No();
		pessoa8.setNum(8);
		base.add(pessoa8);
		
		/*
		1 2 3 4 5 6 7 8
		3 7 11 15
		10 26
		36
		*/
		
		arvore.fazerArvore(base);
		System.out.println(arvore.travessiaLargura());
	}

}
