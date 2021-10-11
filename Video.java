
public class Video extends Data {
	//La classe Video estende la classe Data e rappresenta un dato di tipo video con le relative proprietà sulla durata e qualità
	
	/*REP INVARIANT: name!=null && name!="" && db>=0 && likes!=null && Forall i.(0<=i<likes.size())-->likes.get(i)!=null
	 * && Forall i,j.(0<=j,i<likes.size() && i!=j)--> likes.get(i)!=likes.get(j) && duration>=0 && 
	 * (quality=="hd"||quality=="sd"||quality!="ld") && Forall i.(0<=i<likes.size())-->likes.get(i)!=""*/
	
	//AF(c)=<c.name,c.db,{c.likes.get(0),...,c.likes.get(c.likes.size()-1)},c.duration,c.quality>
	
	public int duration; //durata in secondi
	public String quality; //qualità (consentito solo hd, sd, ld)

	//METODO COSTRUTTORE
	public Video(String nm, int dur,String qlt) throws IllegalArgumentException,NullPointerException {
		super(nm);
		if(dur<=0) throw new IllegalArgumentException("durata negativa o nulla");
		if(!qlt.equals("lq") && !qlt.equals("sd") && !qlt.equals("hd")) throw new IllegalArgumentException("qualità "+qlt+" non ammessa");
		this.duration=dur;
		this.quality=qlt;
	}
	/*@REQUIRES: nm!=null,dur>0, qlt appartiene a {ld,hd,sd}
	 *@THROWS: NullPointerException (Unchecked, presente in java) se nm==null, IllegarArgumentException (Unchecked,presente in java) se pl<=0
	 *or pa<=0 or qlt non appartiene a {ld,hd,sd}
	 *@EFFECTS: crea una nuova istanza della classe video passando come parametro il nome, la durata e la qualità del filmato
	 * */
	
	//Metodo display necessario per la stampa delle caratteristiche dell'oggetto corrente
	public void display() {
		System.out.println("Il video "+super.name+" dura "+duration+" secondi, è in qualità "+quality+" ed ha "+this.getnlike()+" likes");
	}
	//@EFFECTS: stampa a sullo schermo le proprietà relative al video cioè nome, qualità e durata in secondi
	
	public Video Clone(){
		Video aux=new Video(this.name,this.duration,this.quality);
		aux.db=0; //la copia inizialmente non sarà in nessuna databoard
		aux.likes=this.getlikes();
		return aux;
	}
	/*@EFFECTS: effettua una deep copy del dato su cui è invocato il metodo*/
		
}
