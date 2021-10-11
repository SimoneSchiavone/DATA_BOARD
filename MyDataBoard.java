import java.util.*;

@SuppressWarnings("unchecked")
public class MyDataBoard<E extends Data> implements DataBoard<E> {
	//Overview: La classe MyDataBoard è un tipo generico che rappresenta una bacheca di oggetti del tipo Data o dei suoi sottotipi.
	//La classe ha come attributi un id univoco per ogni oggetto di quella classe che viene creato, una password privata che rappresenta
	//una misura di sicurezza per l'invocazione di alcuni metodi di questa classe e un'arraylist di categorie (ognuna delle quali
	//conterrà alcuni dati ed alcuni utenti autorizzati (vedi classe Categoria)

	/*REP INVARIANT id>=1 && this.password!=null && this.password!="" && this.categories!=null && Forall i,j.(0<= i,j <categories.size()
	 * && i!=j)--> categories.get(i).getname()!=categories.get(j).getname() && Forall i,j.i,j are instance of MyDataBoard--> i.getdb()!=j.getdb() 
	 * && Forall i.0<=i<categories.size()-->categories.get(i)!=null */
	
	//AF(c)=<c.id,c.password,{c.categories.get(0),...,c.categories.get(c.categories.size())}>
	
	private int id; //nr identificativo della databoard
	private static int nextid=1; //var statica che tiene conto dell'id della prossima databoard che verrà creata
	private String password;
	private ArrayList<Categoria<E>> categories;
	
	//Metodo Costruttore
	public MyDataBoard(String psw) throws NullPointerException,IllegalArgumentException{
		if(psw==null) throw new NullPointerException("password nulla non ammessa");
		if(psw.equals("")) throw new IllegalArgumentException("Password stringa vuota non valida");
		this.password=psw;
		this.categories=new ArrayList<Categoria<E>>();
		this.id=nextid;
		nextid++;
	}
	/*@REQUIRES: psw!=null
	 *@THROWS: NullPointerException(unchecked presente in java) se psw==null
	 *@EFFECTS: crea una nuova istanza della classe MyDataBoard settando una password iniziale passata da parametro*/
	
	
	public void createCategory(String category, String passw)
			throws NullPointerException, IncorrectPasswordException, DuplicateEntryException,IllegalArgumentException {
		//CONTROLLI
		if(category==null) throw new NullPointerException();
		if(category.equals("")) throw new IllegalArgumentException("stringa vuota non ammessa come categoria");
		if(!this.password.equals(passw)) throw new IncorrectPasswordException();
		for(Categoria<E> i: categories) {
			if(i.name.equals(category))
				throw new DuplicateEntryException(category+" già presente");
		}
		
		//Inserimento nuova categoria
		categories.add(new Categoria<E>(category));
	}

	public void removeCategory(String category, String passw)
			throws IncorrectPasswordException, NullPointerException, NotFoundException {
		//CONTROLLI
		if(category==null) throw new NullPointerException();
		if(!this.password.equals(passw)) throw new IncorrectPasswordException();
		//indice della categoria
		int ind=this.indexOf(category); 
		//definisco un metodo interno privato indexOf
		if(ind==-1) throw new NotFoundException(category);
	
		//RIMOZIONE
		this.categories.remove(ind);
	}

	public void addFriend(String category, String passw, String friend)
			throws NullPointerException, IncorrectPasswordException, NotFoundException,DuplicateEntryException, IllegalArgumentException {
		//CONTROLLI
		if(category==null ||friend==null) throw new NullPointerException();
		if(friend.equals(""))throw new IllegalArgumentException("stringa vuota non ammessa");
		if(!this.password.equals(passw)) throw new IncorrectPasswordException();
		//indice della categoria
		int ind1=this.indexOf(category);
		if(ind1==-1) throw new NotFoundException(category);
		//definisco nella classe categoria un metodo ausiliario containsfriend che mi dice se l'arraylist degli amici autorizzati
		//a visionare gli elementi della categoria passata da parametro contiene già l'amico che vogliamo inserire
		if (categories.get(ind1).containsfriend(friend))throw new DuplicateEntryException(friend+" già presente tra gli amici autorizzati");
		//AGGIUNTA
		//getUser è un metodo interno della classe categoria che restituisce l'arraylist degli amici autorizzati
		this.categories.get(ind1).getUser().add(friend);
	}

	public void removeFriend(String passw, String friend, String category)
			throws NullPointerException, IncorrectPasswordException, NotFoundException {
		//CONTROLLI
		if(!this.password.equals(passw)) throw new IncorrectPasswordException();
		if(category==null || friend==null) throw new NullPointerException();
		//indice della categoria
		int ind1=this.indexOf(category);
		if(ind1==-1) throw new NotFoundException(category);
		//definisco nella classe categoria un metodo ausiliario containsfriend che mi dice se l'arraylist degli amici autorizzati
		//a visionare gli elementi della categoria passata da parametro contiene l'amico che vogliamo inserire
		//(a noi interessa che non lo contenga per lanciare eventualmente l'eccezione NotFoundException)
		if (!categories.get(ind1).containsfriend(friend))throw new NotFoundException(friend);
		
		//RIMOZIONE
		this.categories.get(ind1).getUser().remove(friend);
	}

	public boolean put(String passw, E data, String category)
			throws NullPointerException, NotFoundException, IncorrectPasswordException, DuplicateEntryException,MultipleDataBoardException {
		//CONTROLLI
		if(!this.password.equals(passw)) throw new IncorrectPasswordException();
		if(data.getdb()!=0 && data.getdb()!=this.id)throw new MultipleDataBoardException(data.name,data.getdb());
		//Se non è un dato non allocato e l'id è diverso
		if(data==null || passw==null ||category==null) throw new NullPointerException();
		//indice della categoria
		int ind1=this.indexOf(category);
		if(ind1==-1) throw new NotFoundException(category);
		if(categories.get(ind1).getElements().contains(data)) throw new DuplicateEntryException(data.name);
		//Il DuplicateEntryException si riferisce al fatto che non posso avere due volte lo stesso dato nella
		//stessa categoria! non è vincolante per categorie diverse
		
		//INSERIMENTO
		data.setdb(this.id);
		return categories.get(ind1).getElements().add(data) ;
	}

	public E get(String passw, E dato) throws NullPointerException, IncorrectPasswordException,NotFoundException{
		//CONTROLLI 
		if(dato==null || passw==null) throw new NullPointerException();
		if(!this.password.equals(passw)) throw new IncorrectPasswordException();
		if(this.id!=dato.getdb()) throw new NotFoundException(dato.name);
		//se l'id associato al dato è corrispondente a questa data board allora sicuramente ci sarà
		//una occorrenza di dato in almeno una categoria
		for(Categoria<E> i: categories) {
			if(i.getElements().contains(dato)) {
				if(i.getElements().get(i.getElements().indexOf(dato)) instanceof Post) {
					Post p=(Post) i.getElements().get(i.getElements().indexOf(dato));
					Post pp=p.Clone();
					return (E)pp;
				}
				if(i.getElements().get(i.getElements().indexOf(dato)) instanceof Immagine) {
					Immagine p=(Immagine) i.getElements().get(i.getElements().indexOf(dato));
					Immagine pp=p.Clone();
					return (E)pp;
				}
				if(i.getElements().get(i.getElements().indexOf(dato)) instanceof NotaAudio) {
					NotaAudio p=(NotaAudio) i.getElements().get(i.getElements().indexOf(dato));
					NotaAudio pp=p.Clone();
					return (E)pp;
				}
				if(i.getElements().get(i.getElements().indexOf(dato)) instanceof Video) {
					Video p=(Video) i.getElements().get(i.getElements().indexOf(dato));
					Video pp=p.Clone();
					return (E)pp;
				}
				//restituisco la deep copy della prima occorrenza che trovo
			}	
		}
		return null;
	}

	public E remove(String passw, E dato) throws NullPointerException, IncorrectPasswordException {
		//CONTROLLI 
		if(dato==null || passw==null) throw new NullPointerException();
		if(!this.password.equals(passw)) throw new IncorrectPasswordException();
		
		//Rimozione
		E aux1; 
		E aux2=null; //inizialmente setto che il dato non è in alcuna categoria 
		for(Categoria<E> i: categories) {
			aux1=i.rmvel(dato); //var di supporto per la chiamata di rmvel sulle categorie
			//rmvel è un metodo ausiliario della classe categoria che mi rimuove un elemento passato da parametro dalla lista
			//dei dati della categoria su cui è invocato. 
			if(aux1!=null) aux2=aux1; //mi salvo almeno una volta che non ho ricevuto un null in modo da restituirlo
		}
		//Se ho rimosso il dato dalla data board posso ripubblicarlo in altra DB quindi setto il campo del dato dove salvo
		//il DB associato a 0
		if(aux2!=null) dato.setdb(0);
		return aux2;
	}
	
	public List<E> getDataCategory(String passw, String category)
			throws IncorrectPasswordException, NullPointerException, NotFoundException {
		//CONTROLLI 
		if(category==null || passw==null) throw new NullPointerException();
		if(!this.password.equals(passw)) throw new IncorrectPasswordException();
		//indice della categoria
		int ind1=this.indexOf(category);
		if(ind1==-1) throw new NotFoundException(category);
		
		List<E> aux=new ArrayList<E>(); //costruisco inizialmente lista vuota
		aux.addAll(categories.get(ind1).getElements()); 
		aux=this.arraydeepcopy(aux); 
		//metodo ausiliario che fa la deepcopy degli elementi dell'arraylist iniziale
		return aux;
	}

	public void insertLike(String friend, E data) throws NullPointerException, UnauthorizedFriend, NotFoundException {
		//CONTROLLI 
		if(friend==null || data==null) throw new NullPointerException();
		if(this.id!=data.getdb()) throw new NotFoundException(data.name);
		//se l'id associato al dato è corrispondente a questa data board allora sicuramente ci sarà
		//una occorrenza di data in almeno una categoria. 
		
		//come in qualsiasi social, se metto like ad un oggetto che ha già un like, in realtà lo levo. delego questo
		//controllo al metodo addlike della classe data. Esso controlla la presenza dell'amico nella lista degli utenti che hanno
		//messo like. Se già presente, lo rimuove dalla lista e decrementa di 1 il contatore dei like del dato, altrimenti incrementa
		//il counter dei likes ed aggiunge l'amico alla lista degli utenti che hanno messo like.
		
		boolean t=false; //variabile temporanea che mi dice se ho trovato almeno una occorrenza di data in una categoria visibile
		//all'amico, altrimenti devo lanciare una eccezione se l'amico non è autorizzato a vedere nessuna delle categorie
		//che contengono il data. Utilizzo questa varibile per evitare di fare due scorrimenti della lista delle categorie
		for(Categoria<E> i: categories) {
			if( (i.getElements().contains(data)) && (i.getUser().contains(friend)) ){
					t=true; //ho un accesso valido
					data.addlike(friend); //chiamo il metodo ausiliario sul dato
					break;  //dopo aver trovato una occorrenza di data visibile a friend, aggiungo like ed esco dal ciclo
				}
		}
		if(t==false) throw new UnauthorizedFriend(friend);
	}

	public Iterator<E> getIterator(String passw) throws IncorrectPasswordException {
		//Controlli
		if(!this.password.equals(passw)) throw new IncorrectPasswordException();
		
		//Passo1: mettiamo tutti i dati in una unica struttura List
		List<E> alldata=new ArrayList<E>();
		for(Categoria<E> i: categories) {
			alldata.addAll(i.getElements());
			//add.All è un metodo predefinito di ArrayList che inserisce in coda alla lista tutti i dati di una collezione passata da param.
		}
		
		//Passo2: eliminiamo i duplicati che si possono essere creati a causa di inserimenti dello stesso dato
		//in più categorie
		alldata=this.removeduplicates(alldata);
		//removeduplicates è un metodo privato ausiliario di DataBoard che permette l'eliminazione di duplicati da una lista.
		
		//Passo3: faccio in modo che alldata contenga la deep copy di tutti i suoi elementi in modo da evitare accessi ai dati
		//attraverso l'iteratore
		alldata=this.arraydeepcopy(alldata); //chiamata al metodo ausiliario sotto descritto

		//Passo4: ordinamento Decrescente
		Collections.sort(alldata, new DataComparator());
		//Nella classe DataComparator faccio l'override del metodo compare affinché si abbia un criterio di ordinamento che consideri
		//i like di ciascun dato. Collections.sort permette l'ordinamento di una collezione utilizzando il DataComparator che gli viene
		//passato come parametro insieme alla lista alldata.
		
		//Passo5: trasformo in lista non modificabile per "bloccare" la remove dell'iterator.L'invocazione della remove
		//sull'iterator che viene restituito causa una UnsupportedOperationException
		alldata=Collections.unmodifiableList(alldata);
		
		//Passo6: restituisco l'iteratore facendo una chiamata al metodo .iterator della classe Collection
		return alldata.iterator();	
	}

	public Iterator<E> getFriendIterator(String friend) throws NullPointerException {
		//CONTROLLI 
		if(friend==null) throw new NullPointerException();
		//Passo1: mettiamo tutti i dati in una unica struttura List controllando che l'amico abbia l'accesso
		List<E> alldata=new ArrayList<E>();
		//add.All è un metodo predefinito di ArrayList che inserisce in coda alla lista tutti i dati di una collezione passata da param.
		for(Categoria<E> i: categories) {
			if(i.getUser().contains(friend))
				alldata.addAll(i.getElements());			
		}
		
		//Passo2: eliminiamo i duplicati che si possono essere creati a causa di inserimenti dello stesso dato
		//in più categorie
		alldata=this.removeduplicates(alldata);
		//removeduplicates è un metodo privato ausiliario di DataBoard che permette l'eliminazione di duplicati da una lista
		
		//Passo3: faccio in modo che alldata contenga la deep copy di tutti i suoi elementi in modo da evitare accessi ai dati
		//attraverso l'iteratore
		alldata=this.arraydeepcopy(alldata); //chiamata al metodo ausiliario sotto descritto
		
		//Passo4: trasformo in lista non modificabile per "bloccare" la remove dell'iterator.L'invocazione della remove
		//sull'iterator che viene restituito causa una UnsupportedOperationException
		alldata=Collections.unmodifiableList(alldata);
		
		//Passo5: restituisco l'iteratore facendo una chiamata al metodo .iterator della classe Collection
		return alldata.iterator();
	}
	
	public void changepsw(String cpassw,String npassw) throws NullPointerException,IncorrectPasswordException,IllegalArgumentException {
		if(npassw==null) throw new NullPointerException();
		if(!cpassw.equals(this.password))throw new IncorrectPasswordException();
		if(npassw.equals(this.password)) throw new IllegalArgumentException("La password deve essere diversa da quella vecchia");
		if(npassw.equals("")) throw new IllegalArgumentException("Stringa vuota non ammessa come password");
		this.password=npassw;		
	}
	/*@REQUIRES: newpsw!=this.password, this.password=cpassw, newpsw!=null,npassw!="" (stringa vuota)
	 *@THROWS: NullPointerException (Unchecked, presente in java) se newpsw==null, IncorrectPassword (checked) se this.password!=psw,
	 *IllegalArgumentException (unchecked, presente in java) se newpsw==this.psw oppure newpsw==""
	 *@MODIFIES: this
	 *@EFFECTS: Se i controlli della requires sono superati (this.psw)post=newpsw altrimenti (this.psw)pre=(this.psw)post */
	
	public void printcat() {
		System.out.println("Categorie della Bacheca: ");
		for(Categoria<E> i: categories) {
			System.out.println(i.name);
		}
		System.out.println();
	}
	/*@REQUIRES: niente
	 *@EFFECTS: stampa a video tutte le categorie della databoard
	 *@MODIFIES: niente*
	 */
	
	private int indexOf(String elem) {
		int ind=0;
		for(Categoria<E> i: categories) {
			if(i.name.equals(elem)) {
				return ind;
			}
			ind++;
		}
		return -1;
	}
	/*@REQUIRES: niente
	 *@EFFECTS: restituisce l'indice della categoria elem passata da parametro nella lista delle categorie
	 *della data board. se elem non presente restituisce -1*/
	
	public void printid() {
		System.out.println("l'id di questa databoard è: "+this.id);
	}
	/*@REQUIRES: niente
	 *@EFFECTS: stampa l'id della databoard su cui è invocato il metodo
	 *@MODIFIES: niente*/
	
	
	private List<E> removeduplicates(List<E> lst) throws NullPointerException{
		if (lst==null) throw new NullPointerException();
		// Creo un'arraylist di supporto
	    List<E> newlst = new ArrayList<E>(); 
	    // Scorro tutti gli elementi della lista passata da parametro 
	    for (E element : lst) { 
	    //Se l'elemento non è contenuto nella lista di supporto aggiungilo
	    //altrimenti vai avanti
	    	if (!newlst.contains(element)) { 
	                newlst.add(element); 
	        }
	    } 
	    //restituisco la nuova lista elaborata
	    return newlst; 
	}
	/*@REQUIRES: lst!=null
	 *@MODIFIES: lista passata da parametro
	 *@EFFECTS: rimuove gli elementi duplicati dalla lista passata come parametro e restituisce la nuova lista elaborata*/
	
	public void printelements(String category) throws NotFoundException,NullPointerException{
		if(category==null) throw new NullPointerException();
		int ind=this.indexOf(category);
		if(ind==-1) throw new NotFoundException(category);
		System.out.print("I dati appartenenti alla categoria "+category+" sono ");
		categories.get(ind).printelements();
	}
	/*@REQUIRES: category!=null, category appartenente a categories
	 *@THROWS: NullPointerException (unchecked presente in java) se category==null, NotFoundException (checked) se la
	 * categoria non appartiene alla lista
	 *@EFFECTS: stampa il nome degli elementi che appartengono alla categoria passata come parametro*/
	
	public void printautusers(String category) throws NotFoundException,NullPointerException{
		if(category==null) throw new NullPointerException();
		int ind=this.indexOf(category);
		if(ind==-1) throw new NotFoundException(category);
		System.out.print("Gli amici autorizzati della categoria "+category+" sono ");
		categories.get(ind).printautusers();
	}
	/*@REQUIRES: category!=null, category appartenente a categories
	 *@THROWS: NullPointerException (unchecked presente in java) se category==null, NotFoundException (checked) se la
	 * categoria non appartiene alla lista
	 *@EFFECTS: stampa il nome degli amici autorizzati alla visione della categoria passata come parametro
	 */
	
	private List<E> arraydeepcopy(List<E> input)throws NullPointerException{
		if(input==null)throw new NullPointerException();
		//ArrayList di supporto dove inserirò le copie dei singoli dati
		List<E> cp=new ArrayList<E>();
		for(E i: input) {
			if(i instanceof Post) {
				Post p=(Post) i;
				Post pp=p.Clone();
				cp.add((E)pp);
			}
			if(i instanceof Immagine) {
				Immagine p=(Immagine) i;
				Immagine pp=p.Clone();
				cp.add((E)pp);
			}
			if(i instanceof Video) {
				Video p=(Video) i;
				Video pp=p.Clone();
				cp.add((E)pp);
			}
			if(i instanceof NotaAudio) {
				NotaAudio p=(NotaAudio) i;
				NotaAudio pp=p.Clone();
				cp.add((E)pp);
			}
		}
		return cp;
	}
	/*@REQUIRES: input!=null
	 *@THROWS: NullPointerException (unchecked, presente in java) se input==null
	 *@EFFECTS: il metodo restituisce un'array list contenente la deep copy degli elementi che compongono
	 *l'array list passata come parametro*/
}
