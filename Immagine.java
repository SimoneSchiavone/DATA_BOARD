public class Immagine extends Data {
	//La classe Immagine estende la classe Data e rappresenta un dato di tipo immagine con le relative proprietà sulle dimensioni
	
	/*REP INVARIANT: name!=null && name!="" && db>=0 && likes!=null && Forall i.(0<=i<likes.size())-->likes.get(i)!=null
	 * && Forall i,j.(0<=j,i<likes.size() && i!=j)--> likes.get(i)!=likes.get(j) && pixh>0 &&pixw>0
	 * && Forall i.(0<=i<likes.size())-->likes.get(i)!=""*/
	
	//AF(c)=<c.name,c.db,{c.likes.get(0),...,c.likes.get(c.likes.size()-1)},c.pixh,c.pixw>
	
	public int pixh; //pixel altezza
	public int pixw; //pixel larghezza
	

	//METODO COSTRUTTORE
	public Immagine(String nm, int pl,int pa) throws IllegalArgumentException,NullPointerException {
		super(nm);
		if(pl<=0||pa<=0) throw new IllegalArgumentException("dimensione negativa o nulla");
		this.pixw=pl;
		this.pixh=pa;
	}
	/*@REQUIRES: nm!=null,pl>0,pa>0
	 *@THROWS: NullPointerException (Unchecked, presente in java) se nm==null, IllegarArgumentException (Unchecked,presente in java) se pl<=0
	 *or pa<=0
	 *@EFFECTS: crea una nuova istanza della classe immagine passando come parametro il nome della immagine e le dimensioni in pixel */
	
	//Metodo display necessario per la stampa delle caratteristiche dell'oggetto corrente
	public void display() {
		System.out.println("La foto "+super.name+" ha dimensione "+pixh+"x"+pixw+" ed ha "+this.getnlike()+" likes");
	}
	//@EFFECTS: stampa a sullo schermo le proprietà relative all'immagine cioè nome e dimensioni in pixel
	
	public Immagine Clone(){
		Immagine aux=new Immagine(this.name,this.pixh,this.pixw);
		aux.db=0; //la copia non sarà pubblicata in nessuna databoard
		aux.likes=this.getlikes();
		return aux;
	}
	/*@EFFECTS: restituisce una deep copy del dato su cui è invocato il metodo*/
}
