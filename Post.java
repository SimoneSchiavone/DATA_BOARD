
public class Post extends Data{
	    //La classe Post estende la classe Data e rappresenta un dato di tipo testuale 
		
		/*REP INVARIANT: name!=null && name!="" && db>=0 && likes!=null && Forall i.(0<=i<likes.size())-->likes.get(i)!=null
		 * && Forall i,j.(0<=j,i<likes.size() && i!=j)--> likes.get(i)!=likes.get(j) && announcement!= null && announcement!=""
		 *  &&Forall i.(0<=i<likes.size())-->likes.get(i)!="" */
	
		//AF(c)=<c.name,c.db,{c.likes.get(0),...,c.likes.get(c.likes.size()-1)},c.announcement>
	
		public String announcement; //testo dell'annuncio
		
		//METODO COSTRUTTORE
		public Post(String nm, String txt) throws NullPointerException, IllegalArgumentException {
			super(nm);
			//il controllo sul campo nm è delegato al metodo costruttore di data
			if(txt==null) throw new NullPointerException();
			if(txt.equals("")) throw new IllegalArgumentException("testo annuncio vuoto");
			this.announcement=txt;
		}
		/*@REQUIRES: nm!=null,pl>0,pa>0
		 *@THROWS: NullPointerException (Unchecked, presente in java) se nm==null or txt=null
		 *@EFFECTS: crea una nuova istanza della classe testo passando come parametro il nome dell'annuncio ed il testo
		 * */
		
		//Metodo display necessario per la stampa delle caratteristiche dell'oggetto corrente
		public void display() {
			System.out.println("Il post "+super.name+" è: "+announcement+" ed ha "+this.getnlike()+" likes");
		}
		//@EFFECTS: stampa a sullo schermo le proprietà relative al post cioè nome ed annuncio
		
		public Post Clone(){
			Post aux=new Post(this.name,this.announcement);
			aux.db=0; //la copia non sarà pubblicata in nessuna databoard
			aux.likes=this.getlikes();
			//aux.setattributes(this.getlikes(),this.getdb());
			//metodo ausiliario della classe data che si occupa di settare gli attributi di Data con l'arraylist degli utenti che
			//hanno messo like, del numero di like, e del numero di databoard associata
			return aux;
		}
		/*@EFFECTS: effettua una deep copy del dato su cui è invocato il metodo*/
	
}
	
