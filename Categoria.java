import java.util.*;

public class Categoria<E extends Data> {
	//Overview: La classe Categoria<E extends data> rappresenta un tipi di dato astratto che rappresenta una suddivisione per categorie di dati
	//La classe ha come attributi un nome (visibile dall'esterno) e due ArrayList privati contenenti gli elementi di quella categoria
	//e gli utenti autorizzati alla visione dei dati di quella categoria. Tale classe è utilizzata solo nella prima implementazione cioè
	//nella MyDataBoard.
	
	/*REP INVARIANT: name!=null && name!="" (stringa vuota) && autusers!=null && elements!=null && Forall i,j.(0<=i,j<autusers.size()
	*  && i!=j)--> autusers.get(i)!=autuser.get(j) && Forall i,j.(0<=i,j<elements.size() &&	i!=j)--> elements.get(i)!=elements.get(j)
	*  && Forall i.0<=i<autusers-->autusers.get(i)!=null && Forall i.0<=i<autusers.size()-->autusers.get(i)!="" && 
	*  Forall i.0<=i<elements.size()-->elements.get(i)!=null*/
	
	//AF(c) = <c.getname,{c.autusers.get(0),...,c.autursers.get(c.autusers.size()-1)}, {c.elements.get(0),...,c.elements.get(c.elements.size()-1)}>
	
	
	private ArrayList<String>autusers; //ArrayList degli utenti autorizzati alla visualizzazione di elementi della categoria
	private ArrayList<E> elements; //ArrayList dei dati appartententi a quella categoria
	public String name; //nome della categoria
	
	public Categoria(String nm) throws NullPointerException{
		if(nm==null) throw new NullPointerException();
		if(nm.equals("")) throw new IllegalArgumentException("stringa vuota non ammessa");
		autusers=new ArrayList<String>();
		elements=new ArrayList<E>();
		name=nm;		
	}
	/*@REQUIRES: nm!=null,nm!=""
	 *@THROWS: NullPointerException (Unchecked, presente in java) se nm==null,IllegalArgumentException (unchecked, presente in java)
	 *se nm==""
	 *@EFFECTS: crea una nuova istanza della classe Categoria*/

	protected ArrayList<String> getUser(){
		 return this.autusers;
	}
	/*@REQUIRES: niente
	 *@EFFECTS: restituisce l'ArrayList degli utenti autorizzati alla visione
	 *degli elementi di questa categoria
	 *@MODIFIES: niente*/
	
	protected ArrayList<E> getElements(){
		return this.elements;
	}
	/*@REQUIRES: niente
	 *@EFFECTS: restituisce l'ArrayList degli elementi presenti nella categoria
	 *degli elementi di questa categoria
	 *@MODIFIES: niente*/
	
	public boolean containsfriend(String fname) throws NullPointerException {
		if (fname==null) throw new NullPointerException();
		return this.autusers.contains(fname);
	}
	/*@REQUIRES: fname!=null
	 *@THROWS: NullPointerException (unchecked presente in java) se fname==null
	 *@EFFECTS: restituisce true se this.autusers contiene fname, false altrimenti
	 *@MODIFIES: niente*/
	
	public void printautusers() {
		System.out.println(autusers);
	}
	/*@REQUIRES: niente
	 *@EFFECTS: stampa a video la lista degli utenti autorizzati alla visione degli elementi della categoria
	 *@MODIFIES: niente*/
	
	public void printelements() {
		for(E i: elements) {
			System.out.print(i.name+"\t");
		}
		System.out.println();
	}
	/*@REQUIRES: niente
	 *@EFFECTS: stampa a video il nome di tutti i dati contenuti nella categoria
	 *@MODIFIES: niente*/
	
	public E rmvel(E data) throws NullPointerException{
		
		if(!elements.contains(data)) return null; //se non c'è in questa categoria restituisco null
		else 
			return elements.remove(elements.indexOf(data)); 
			//rimuovo l'elemento dalla collezione restituendolo solo se esiste altrimenti return null
	}
	/*@REQUIRES: data!=null
	 *@EFFECTS: rimuove l'occorrenza di data dall'arraylist elements. Se non presente data restituisce null
	 *altrimenti restituisce l'elemento rimosso
	 *@MODIFIES: niente*/
	
}
