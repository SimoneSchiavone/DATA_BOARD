
public class NotaAudio extends Data {
	//il tipo di dato NotaAudio rappresenta un dato audio (es. registrazione) che ha come attributo la durata in secondi
	
	/*REP INVARIANT: name!=null && name!="" && db>=0 && likes!=null && Forall i.(0<=i<likes.size())-->likes.get(i)!=null
	 * && Forall i,j.(0<=j,i<likes.size() && i!=j)--> likes.get(i)!=likes.get(j) && duration>0 &&Forall i.(0<=i<likes.size())-->likes.get(i)!=""*/
	
	//AF(c)=<c.name,c.db,{c.likes.get(0),...,c.likes.get(c.likes.size()-1)},c.duration>
	
	public int duration; //durata in secondi
	
	//METODO COSTRUTTORE
	public NotaAudio(String nm,int dur) throws NullPointerException,IllegalArgumentException {
		super(nm);
		if(dur<=0) throw new IllegalArgumentException("durata negativa o nulla");
		this.duration=dur;
	}
	/*@REQUIRES: nm!=null,dur>0
	 *@THROWS: NullPointerException (Unchecked, presente in java) se nm==null, IllegarArgumentException (Unchecked,presente in java) se dur<=0
	 *@EFFECTS: crea una nuova istanza della classe NotaAudio passando come parametro il nome della NotaAudio e la durata in secondi
	 */


	public void display() {
		System.out.println("La NotaAudio "+super.name+" ha durata "+duration+" secondi ed ha "+this.getnlike()+" likes");
	}
	//@EFFECTS: stampa a sullo schermo le proprietà relative alla NotaAudio cioè nome e durata
	
	public NotaAudio Clone(){
		NotaAudio aux=new NotaAudio(this.name,this.duration);
		aux.db=0; //la copia inizialmente non sarà pubblicata in nessuna databoard
		aux.likes=this.getlikes();
		return aux;
	}
	/*@EFFECTS: effettua una deep copy del dato su cui è invocato il metodo*/
}
