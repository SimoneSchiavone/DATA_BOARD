import java.util.ArrayList;

public abstract class Data{
	//Overview: La classe Data rappresenta un tipo di dato modificabile che può far parte della collezione DataBoard.
	// Ogni dato deve implementare obbligatoriamente un metodo display che ne stampa le caratteristiche
	
	//TYPICAL ELEMENT: <nome,id databoard associata,{insieme di amici che hanno messo like al dato}>*/
	
	/*REP INVARIANT: name!=null && name!="" && db>=0 && likes!=null && Forall i.(0<=i<likes.size())-->likes.get(i)!=null
	 *&& Forall i,j.(0<=j,i<likes.size() && i!=j)--> likes.get(i)!=likes.get(j)  && Forall i.(0<=i<likes.size())-->likes.get(i)!="" */
	
	//AF(c)=<c.name,c.db,{c.likes.get(0),...,c.likes.get(c.likes.size()-1)}>
	
	public String name; //nome del dato
	protected int db; //variabile che mi dice a quale DataBoard appartiene il dato (0 indica nessuna DB)
	protected ArrayList<String>likes; //lista delle persone che hanno messo like
	
	//Metodo Costruttore
	public Data(String nm) throws NullPointerException,IllegalArgumentException {
		if (nm==null) throw new NullPointerException();
		if (nm.equals("")) throw new IllegalArgumentException("stringa vuota");
		this.name=nm;
		this.likes=new ArrayList<String>();
		this.db=0; //non pubblicato su nessuna DB
	}
	/*@REQUIRES nm!=null,nm!="" (stringa vuota)
	 *@THROWS: NullPointerException (unchecked,presente in java) se nm==null, IllegalArgumentException (unchecked,
	 *presente in java)
	 *@EFFECTS: crea una nuova istanza della classe Data settando inizialmente il nome con la stringa passata da 
	 *parametro, numero di like 0,lista degli utenti che hanno messo like vuota, e l'id della databoard associata 0
	 *(il che significa che non è pubblicato in nessuna databoard)*/
	
	 protected int getdb() {
		 return this.db;
	 }
	 /*@REQUIRES: niente
	  *@EFFECTS: restituisce l'id della databoard su cui è invocato il metodo
	  *@MODIFIES: niente */
	 
	 protected void setdb(int param) {
		 if(param<0) throw new IllegalArgumentException();
		 this.db=param;
	 }
	 /*@REQUIRES: param>=0
	  *@THROWS: IllegalArgumentException(unchecked presente in java) se param<0
	  *@EFFECTS: (this.db)post=(this.db)pre se controllo fallisce altrimenti (this.db)post=param
	  *@MODIFIES: this.db */
	 
	 protected void addlike(String name) throws NullPointerException{
		 if(name==null)throw new NullPointerException();
		 if(!likes.contains(name)) {
		    this.likes.add(name);
		 }else {
			 this.likes.remove(name);
		 } 
	 }
	 /*@REQUIRES: name!=null
	  *@THROWS: NullPointerException se name==null (unchecked presente in java)
	  *@EFFECTS: se name appartiene all'arraylist this.likes allora nlikes-- e si rimuove name da this.likes.
	  *Altrimenti aggiunge name a this.likes e nlikes++
	  *@MODIFIES: this.nlike,this.likes */
	 
	 protected int getnlike() {
		 
		 return this.likes.size();
	 }
	 /*@REQUIRES: niente
	  *@EFFECTS: restituisce il numero di like del dato su cui è chiamato (osservatore)
	  *@MODIFIES: niente*/
	 
	 protected ArrayList<String> getlikes(){
		 ArrayList<String> a = new ArrayList<String>();
		 for (String i : likes) {
			a.add(i);
		}
		 return a;
	 }
	 /*@REQUIRES: niente
	  *@EFFECTS: restituisce una copia dell'arraylist this.likes che è l'arraylist degli amici che hanno messo like al dato
	  *@MODIFIES: this.db */
	 
	//ogni dato che estende la classe data deve implementare obbligatoriamente un metodo display che sarà conforme
	//alle caratteristiche della singola sottoclasse
	public abstract void display();
}
