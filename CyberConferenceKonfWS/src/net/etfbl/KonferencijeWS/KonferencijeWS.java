package net.etfbl.KonferencijeWS;


public class KonferencijeWS {

	public String konferencijeZaTekuciMjesec() {
		String konferencije = new String();
		konferencije = KonferencijeWSDao.konferencijeZaTekuciMjesec();
		return konferencije;
	}
}
