package it.polito.tdp.libretto.model;

import java.time.LocalDate;

public class TestLibretto {
	
	//le proprieta vengo attribuite agli oggetti
	
	Libretto lib;
	
	//in questo metodo metto tutte le proprieta che mi servono
	private void run() {
		this.lib = new Libretto();	//libretto vuoto
		
		//1.aggiungi voti
		Voto v1 = new Voto("Tecniche di programmazione", 30, LocalDate.of(2020, 06, 15));
		Voto v2 = new Voto("Analisi II", 28, LocalDate.of(2020, 06, 28));
		
		this.lib.add(v1);
		this.lib.add(v2);
		if( this.lib.add(new Voto("Economia", 24, LocalDate.of(2020, 02, 14))) == false) {
			System.out.println("Errore nell'inserimento: esame già superato o la valutanzione è diversa");
		}
		
		//2.stampa esami con voto 28
		System.out.println("Stampa il libretto:\n"+this.lib);
		System.out.println("Stampa la/e stringa/e con voto 28:\n"+this.lib.stampaVotiUguali(28));
		System.out.println("Stampa il libretto con voto 28:\n"+this.lib.estraiVotiUguali(28));
	
		//3.cerca esame superato, avendo qeul nome del corso
		String nomeCorso = "Analisi II";
		Voto votoCorso = lib.cercaNomeCorso(nomeCorso);
		System.out.println("Il voto del corso "+nomeCorso+" e' "+votoCorso.getVoto());
	
		//4 e 5 verifica voti duplicati e/o conflitto
		Voto eco2 = new Voto("Economia", 24, LocalDate.now());
		Voto eco3 = new Voto("Economia", 21, LocalDate.now());
		
		System.out.println(eco2.getCorso()+" con "+eco2.getVoto()+" duplicato: "+lib.isDuplicato(eco2)
							+"/ conflitto: "+lib.isConflitto(eco2));
		System.out.println(eco3.getCorso()+" con "+eco3.getVoto()+" duplicato: "+lib.isDuplicato(eco3)
		+"/ conflitto: "+lib.isConflitto(eco3));
		
		//7. creare un libretto migliorato
		Libretto migliorato = lib.creaMigliorato();
		System.out.println("\nMiglioramento del libretto");
		System.out.println(lib);
		System.out.println(migliorato);
		
		//8.ordinare in ordine alfabetico e in ordine decrescente del voto
		Libretto alfabetico = new Libretto(lib);
		alfabetico.ordinaCorso();
		System.out.println("Stampo ordine alfabetico:\n"+alfabetico);
		
		Libretto decr = new Libretto(lib);
		decr.ordinaVoto();;
		System.out.println("Stampo ordine voti decrescenti:\n"+decr);
		
		//9.elimina voti inferiori a 24
		lib.add(new Voto("Chimica", 19, LocalDate.now()));
		lib.ordinaCorso();
		System.out.println("Libretto completo:\n"+lib);
		lib.cancellaVoti();
		System.out.println("Libretto voti aggiornato:\n"+lib);
	}
	
	
	//non si lavora dentro il main, per cui dico al test di lavorare con run
	public static void main(String[] args) {
		TestLibretto test = new TestLibretto();
		test.run();
	}

}
