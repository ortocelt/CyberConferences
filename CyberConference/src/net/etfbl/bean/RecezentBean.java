package net.etfbl.bean;

import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import net.etfbl.dto.Korisnik;

@ManagedBean
@SessionScoped
public class RecezentBean {
	
	public ArrayList<Korisnik> sviRezenti = new ArrayList<Korisnik>();

}
