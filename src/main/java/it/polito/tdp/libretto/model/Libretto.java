package it.polito.tdp.libretto.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Memorizza e gestisce l'insieme dei voti degli esami superati
 * @author salva
 *
 */
public class Libretto {

	private List<Voto> voti = new ArrayList<Voto> ();
	
	//genero un costruttore che non fa nulla: crea libretto nuovo e vuoto
	public Libretto() {
		
	}
	//creo una copia del libretto: non clono i voti, ma li copio su un libretto copiato (copia superficiale)
	public Libretto(Libretto lib) {
		this.voti.addAll(lib.voti);
	}
	
	//aggiungo un metodo add per inserire un voto
		//1 metodo--> migliore perchè un giorno potro inserire nuovi parametri (ES: lode) senza modificare tutto
	public boolean add(Voto v) {
		
		//aggiungo condizioni
		if(this.isConflitto(v) || this.isDuplicato(v)) {
			//non inserire il voto (quindi non faccio nulla)
			return false;
		}
		else {
			this.voti.add(v);	//v è il voto passato come parametro e lo aggiunge alla lista voti
			return true;		//ho inserito il voto
		}
	}
	
		//2 metodo
/*	public void add( String corso, int voto, String data) {
		
	}*/
	
	public String stampaVotiUguali (int voto) {
		//stampa gli esami con il voto uguale a quello passato come parametro
		String s = "";
		for (Voto v : voti) {
			if(v.getVoto() == voto) {
				s+= v.toString()+"\n";
			}
		}
		return s;
	}

	public String toString() {
		String s = "";
		for (Voto v : voti) {
			s+= v.toString()+"\n";
		}
		return s;
	}
	
	/*public int cercaCorso( String nomeC) {
		for(Voto v: voti) {
			if(v.getCorso().equals(nomeC)) {
				
			}
		}
	}*/
	public Libretto estraiVotiUguali(int voto) {
		Libretto nuovo = new Libretto();
		for(Voto v: voti) {
			if(v.getVoto() == voto) {
				nuovo.add(v);
			}
		}
		return nuovo;
	}
/**
 * Dato il nome del corso ricerca se quell'esame è nel libretto
 *  e restituire l'oggetto Voto corrispondente
 *  
 * @param nomeCorso dell'esame da cercare
 * @return il {@link Voto} corrispondente
 */
	public Voto cercaNomeCorso(String nomeCorso) {
		
		/*Opzione1
		 for(Voto v: voti) {
			if(v.getCorso().equals(nomeCorso)) {
				return v;
			}
		}
		return null;*/
		
		//Opzione2
		//metto gli altri due campi 0 e null perchè tanto non mi interessa
		int pos= this.voti.indexOf(new Voto(nomeCorso, 0, null));
		
		if(pos != -1) {
			return this.voti.get(pos);
		}else
			return null;
	}
	
	/**
	 *  ritorna {@code true} e il corso specificato esiste con la stessa valutazione
	 *  se non esiste o se la valutazione è diversa ritorna {@code false}
	 * @param v il {@link Voto} da ricercare
	 * @return l'esistenza del duplicato
	 */
	public boolean isDuplicato(Voto v) {
		Voto esiste = this.cercaNomeCorso(v.getCorso());
		if(esiste == null) {	//non ho trovato il Voto--> non è un duplicato
			return false;
		}
		return (esiste.getVoto() == v.getVoto()) ;
	}
	
	//e' in conflitto se il nome del corso è il medesimo ma il voto non coincide
	public boolean isConflitto(Voto v) {
		Voto esiste = this.cercaNomeCorso(v.getCorso());
		if( esiste == null) {
			return false;
		}
		return (esiste.getVoto() != v.getVoto());
	}
	
	/**
	 * Resituisce un nuovo libretto migliando i voti del libretto originale
	 * @return
	 */
	public Libretto creaMigliorato() {
		Libretto nuovo = new Libretto();
		
		for(Voto v: this.voti) {
			//in questo modo non va bene perchè vado a modificare anche v, quindi devo fare una copia (clone)
			//Voto v2 = v;
			Voto v2 = new Voto(v);
			
			//OPPURE potrei fare, ma non va bene nel caso un domani volessi aggiungere qualcosa nel costruttore
			//Voto v2 = new Voto(v.getCorso(), v.getVoto(), v.getData());
			
			//OPPURE ancora avrei potuto usare il metodo clone costruito in Voto
			//Voto v2 = v.clone();
			
			if(v2.getVoto()>=24) {
				v2.setVoto(v2.getVoto()+2);
				if(v2.getVoto()>30)
					v2.setVoto(30);
			}
			else
				if(v2.getVoto()>=18) 
					v2.setVoto(v2.getVoto()+1);
			nuovo.add(v2);
		}
		
		return nuovo;
	}
	
	/**
	 * riordino in ordine alfabetico e di voto decrescente
	 */
	public void ordinaCorso() {
		Collections.sort(this.voti);	
	}
	
	public void ordinaVoto() {
		Collections.sort(this.voti, new ConfrontaVoti());
		
		//metodo senza creare la lasse compaaratrice
		this.voti.sort(new Comparator<Voto>() {

			@Override
			public int compare(Voto o1, Voto o2) {
				// TODO Auto-generated method stub
				return -(o1.getVoto()-o2.getVoto() );
			}
			} );
		//oppure altro metodo definito con lambda function: in java non ci sono le funzioni ma solo i metodi
		this.voti.sort( (Voto o1,  Voto o2) -> (o2.getVoto()-o1.getVoto()) );
		
	}
	
	public void cancellaVoti() {
		//cancellare voti inferiori a 24
		List<Voto> daRimuovere = new ArrayList<>();
		for(Voto v: this.voti) {
			if(v.getVoto()<24) {
				daRimuovere.add(v);
			}
		}
		
		this.voti.removeAll(daRimuovere);
	}
}
