package it.polito.tdp.libretto.model;

import java.util.Comparator;

public class ConfrontaVoti implements Comparator<Voto> {

	@Override
	public int compare(Voto o1, Voto o2) {
		//ordine decrescente
		return -(o1.getVoto()-o2.getVoto() );
	}

}
