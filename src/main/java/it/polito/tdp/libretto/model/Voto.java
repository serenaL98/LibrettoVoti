package it.polito.tdp.libretto.model;

import java.time.LocalDate;

/**
 * Classe Voto contiene le info dell'esame superato
 * @author salva
 *
 */

public class Voto implements Comparable<Voto>{
	
	private String corso;	//e' un'informazione univoca!
	private int voto;
	private LocalDate data;
	
	/**
	 *in questo modo posso commentare
	 *
	 * Costruisce un nuovo Voto
	 * 
	 * @param corso nome del corso dell'esame superato
	 * @param voto dell'esame superato
	 * @param localDate dell'esame
	 */
	public Voto(String corso, int voto, LocalDate localDate) {
		super();
		this.corso = corso;
		this.voto = voto;
		this.data = localDate;
	}
	
	/**
	 * Copy constructor di {@link Voto} crea un contenuto vuoto copiando quello che c'era in Voto
	 * @param v del copio da copiare
	 */
	public Voto(Voto v) {//sto copiando un riferimento al Voto che avevo
		this.corso = v.corso;	//sarebbe v.getCorso()
		this.data = v.data;
		this.voto = v.voto;		//essendo un int se ne copia il valore e non il riferimento
									//motivo per cui ho fatto questo metodo copy
	}
	
	public String getCorso() {
		return corso;
	}

	public void setCorso(String corso) {
		this.corso = corso;
	}

	public int getVoto() {
		return voto;
	}

	public void setVoto(int voto) {
		this.voto = voto;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return corso +": " + voto + " (" + data+")";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((corso == null) ? 0 : corso.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Voto other = (Voto) obj;
		if (corso == null) {
			if (other.corso != null)
				return false;
		} else if (!corso.equals(other.corso))
			return false;
		return true;
	}
	
	public Voto clone() {
		//crea un clone di quello esistente
		Voto v = new Voto(this.corso, this.voto, this.data);
		return v;
	}
	
	public int compareTo(Voto other) {
		return (this.corso.compareTo(other.corso));
	}
}
