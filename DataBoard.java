import java.util.*;

public interface DataBoard<E extends Data> {
	/*OVERVIEW: DataBoard è una collezione di dati di tipo generico E che estendono il tipo Data, modificabile e di lunghezza non definita.
	Il comportamento di oggetti di questa classe è quello di una bacheca che permette la memorizzazione e la visualizzazione di dati. 
 	Il dato implementa una politica di privacy dei dati pubblicati la quale permette di vedere i dati di una certa 
 	categoria solo ai contatti specificati dal creatore della bacheca.  Il controllo di identità del creatore della classe
 	avviene attraverso una stringa password inizialmente settata in fase di creazione di un oggetto DataBoard. 
 	
 	TYPICAL ELEMENT: <password,{insieme di elementi del tipo E che estende data},{insieme di categorie}>*/
	
	//METODI:
	// Crea una categoria di dati se vengono rispettati i controlli di identità
	public void createCategory (String category, String passw) throws NullPointerException,IncorrectPasswordException,DuplicateEntryException,IllegalArgumentException;
	/*@REQUIRES: category!=null, category!="" (stringa vuota), category non ancora presente nell'insieme delle categorie, passw==this.password
	 *@THROWS: NullPointerException se category==null (Eccezione unchecked, presente in java), IncorrectPasswordException(Eccezione
	 *checked non presente in java) se this.password!=passw, DuplicateEntryException (Eccezione unchecked non presente in Java) se
	 *se category già presente nella collezione, IllegalArgumentException (Eccezione unchecked presente in java) se category=="" stringa vuota
	 *@EFFECTS: (this.categories)post=(this.categories)pre se non soddisfatta la clausola requires
	 * (this.categories)post=(this.categories)pre U {category}
	 *@MODIFIES: this
	 **/
	
	// Rimuove una categoria di dati se vengono rispettati i controlli di identità
	public void removeCategory(String category, String passw) throws IncorrectPasswordException,NullPointerException,NotFoundException;
	/*@REQUIRES: category!=null, category presente nell'insieme delle categorie, passw==this.password
	 *@THROWS: NullPointerException (Unchecked, presente in java) se category, IncorrectPasswordException (Eccezione checked) se passw!=this.password,
	 *NotFoundException se category non presente nella collezione (Eccezione checked)
	 *@MODIFIES: this
	 *@EFFECTS: (this.categories)post == (this.categories)pre se non soddisfatta la clausola requires
	 *(this.categories)post=(this.categories)pre\{category} altrimenti.
	 */
	
	
	// Aggiunge un amico ad una categoria di dati se vengono rispettati i controlli di identità
	public void addFriend(String category,String passw,String friend) throws NullPointerException,IncorrectPasswordException,NotFoundException,DuplicateEntryException,IllegalArgumentException;
	/*@REQUIRES: category!=null,this.password==passw,friend!=null, friend non presente nell'insieme di coloro che possono visualizzare
	 * la categoria, category deve essere presente nella lista delle categorie,friend!=""
	 * @THROWS: NullPointerException (unchecked, presente in java) se category==null o friend==null, IncorrectPasswordException (checked) se this.password!=passw,
	 * *NotFoundException (checked) se category non è presente nella collezione, DuplicateEntryException(unchecked, presente in java) se
	 * friend è già presente nella lista di coloro che possono visualizzare, IllegalArgumentException (unchecked presente in java) se friend==""
	 * @MODIFIES: this
	 * @EFFECTS: (this.categories.contacts)post=(this.categories.contacts)pre se clausola requires non è soddisfatta altrimenti
	 * (this.category.contacts)post=(this.category.contacts)pre U {friend}
	 * */
	
	
	// rimuove un amico da una categoria di dati se vengono rispettati i controlli di identità
	public void removeFriend(String passw, String friend,String category) throws NullPointerException,IncorrectPasswordException,NotFoundException;
	/*@REQUIRES: this.password==passw,friend!=null,category!=null,category deve appartenere all'insieme delle categorie 
	 *@THROWS: NullPointerException (unchecked,presente in java) se friend==null || category==null, IncorrectPasswordException (checked) se 
	 *this.password!=passw,NotFoundException(checked) se categoria non presente in collezione
	 *@MODIFIES: this
	 *@EFFECTS: (this.categories.contacts)post=(this.categories.contacts)pre se i controlli della requires non sono soddisfatti altrimenti
	 *(this.category.contacts)post=(this.category.contacts)pre \ {friend}*/
	
	
	//Inserisce un dato in bacheca se vengono rispettati i controlli di identità
	public boolean put(String passw, E data, String category) throws NullPointerException,NotFoundException,IncorrectPasswordException,
																		DuplicateEntryException,MultipleDataBoardException;
	/*@REQUIRES: this.password==passw,data!=null,category!=null,category deve appartenere all'insieme delle categorie,
	 *data non presente nella categoria, data non deve essere presente in altre databoard
	 *@THROWS: NullPointerException (Unchecked,presente in java) se data==null||category==null, NotFoundException (checked) se category
	 *non appartiene alla lista delle categorie, DuplicateEntryException (unchecked) se data già presente nella categoria,
	 *IncorrectPasswordException(checked) se this.password!=passw,MultipleDataBoardException (unchecked) se il dato è stato pubblicato già
	 *in altre databoard
	 *@MODIFIES: this
	 *@EFFECTS: (this.categories.elements)post=(this.categories.elements)pre se i controlli della requires non hanno avuto successo
	 *altrimenti (this.category.elements)post=(this.category.elements)pre U {data}*/
	
	
	//Restituisce una copia del dato in bacheca se vengono rispettati i controlli di identità
	public E get(String passw, E dato) throws NullPointerException, IncorrectPasswordException,NotFoundException;
	/*@REQUIRES: pass==this.password,dato!=null,dato deve appartenere alla databoard
	 *@THROWS: NullPointerException (unchecked, presente in java) se dato==null, IncorrectPasswordException(checked) se this.password!=passw
	 *NotFoundException (unchecked) se il dato non è presente nella databoard
	 *@MODIFIES: niente
	 *@EFFECTS: restituisce la copia deep della prima occorrenza del dato presente in bacheca
	 */
	
	
	
	// Rimuove il dato dalla bacheca se vengono rispettati i controlli di identità
	public E remove(String passw, E dato) throws NullPointerException,IncorrectPasswordException;
	/*@REQUIRES: this.password==passw, dato!=null
	 *@THROWS: NullPointerException (Unchecked,presente in java) se dato==null,IncorrectPasswordException (checked) se this.password!=passw
	 *@MODIFIES: this
	 *@EFFECTS: rimuove tutte le occorrenze del dato nelle categorie se questo è presente nella daboard restituendo quanto eliminato,
	 *altrimenti restituisce null*/
	

	//Crea la lista dei dati in bacheca di una determinata categoria se vengono rispettati i controlli di identità
	public List<E>	getDataCategory(String passw, String category) throws IncorrectPasswordException,NullPointerException,NotFoundException;
	/*@REQUIRES: this.password==passw, category!=null, category presente nell'insieme delle categorie
	 *@THROWS: NullPointerException (unchecked) se category==null, IncorrectPasswordException(checked) se this.password!=passw,
	 *NotFoundException(checked) se la categoria non appartiene alla collezione
	 *@MODIFIES: niente
	 *@EFFECTS: restituisco una lista contenente i dati presenti nella categoria passata come parametro */
	
	
	// Aggiunge un like a un dato se vengono rispettati i controlli di identità
	public void insertLike(String friend, E  data) throws NullPointerException,UnauthorizedFriend,NotFoundException;
	/*@REQUIRES: friend!=null,data!=null,friend deve appartenere all'insieme di amici che può vedere quel dato in almeno una categoria in cui
	 * esso è presente, data deve essere presente nella databoard su cui invoco il metodo
	 *@MODIFIES: this
	 *@THROWS: NullPointerException se friend==null || data==null (unchecked, presente in java), UnauthorizedFriend (unchecked) se
	 *friend non può accedere a nessuna delle categorie in cui il dato è presente, NotFoundException (unchecked) se il dato non è presente
	 *nella databoard. 
	 *@EFFECTS: inserisco friend nella lista degli amici che hanno messo like al dato (passato da parametro) ed incremento il contatore
	 *dei likes. Se friend aveva già messo un like al data, come avviene sulla maggior parte dei social, il like viene rimosso perciò il 
	 *contatore decrementato e friend rimosso dalla lista degli amici che hanno messo like.*/
	
	
	// restituisce un iteratore (senza remove) che genera tutti i dati in bacheca ordinati rispetto al numero di like
	// se vengono rispettati i controlli di identità
	public Iterator<E> getIterator(String passw) throws IncorrectPasswordException;
	/*@REQUIRES: this.password==passw
	 *@THROWS: IncorrectPasswordException se this.password!=passw (checked)
	 *@MODIFIES: niente
	 *@EFFECTS: restituisce un iteratore che genera tutti i dati in bacheca ordinati in base al numero di like*/
	
	
	// restituisce un iteratore (senza remove) che genera tutti i dati in bacheca condivisi
	public Iterator<E> getFriendIterator(String friend) throws NullPointerException;
	/*@REQUIRES: friend!=null
	 *@THROWS: NullPointerException (Unchecked, presente in java) se friend==null
	 *@MODIFIES: niente
	 *@EFFECTS: restituisce un iteratore che genera tutti i dati in bacheca visibili ad un amico passato da parametro*/
}
