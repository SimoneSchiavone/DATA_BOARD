import java.util.*;

@SuppressWarnings("unchecked")
public class MyDataBoard2<E extends Data> implements DataBoard<E> {
	//Overview: La classe MyDataBoard è un tipo generico che rappresenta una bacheca di oggetti del tipo Data o dei suoi sottotipi.
	//La classe ha come attributi un id univoco per ogni oggetto di quella classe che viene creato, una password privata che rappresenta
	//una misura di sicurezza per l'invocazione di alcuni metodi di questa classe e due hashmap; L'hash map catelem rappresenta
	//l'associazione tra categoria(k) ed arraylist dei dati contenuti in essa mentre l'altra l'associazione tra la categoria(k) e arraylist degli
	//amici che possono visualizzare i dati di quella categoria. 
	
	/*REP INVARIANT this.id>=1 && this.password!=null && this.password!="" && this.catelem!=null && this.catfriend!=null && Forall i,j.i,j 
	* contained in catelem.keyset()-->i!=j && Forall i,j.i,j contained in catfriend.keyset()-->i!=j && Forall i,j.i,j are instance of MyDataBoard--> i.getdb()!=j.getdb() &&
	* Forall i.i is instance of MyDataBoard--> i.getdb()>=1 && Forall i.i contained in catelem.keyset()-->catelem.get(i)!=null
	* && Forall i.i contained in catfriend.keyset()-->catfriend.get(i)!=null && 
	* Forall i.i contained in catfriend.keyset()-->(Forall j,k.0<=j,k<catfriend.get(i).size() && j!=k --> catfriend.get(i).get(j)!=catfriend.get(i).get(k)) &&
	* Forall i.i contained in catelem.keyset()-->(Forall j,k.0<=j,k<catelem.get(i).size && j!=k --> catelem.get(i).get(j)!=catelem.get(i).get(k))
	* && catelem.keyset()==(catfriend.keyset() [set con stessi elementi] */
	
	/*AF(c)=<c.id,c.password,{<cat_1,{catelem.get(cat_1)}>,...,<cat_catelem.size(),{catelem.get(cat_catelem.size())} },
	{<cat_1,{catfriend.get(cat_1)}>,...,<cat_catfriend.size(),{catfriend.get(cat_catfriend.size())} }>*/
	
	private int id; //nr identificativo della databoard
	private static int nextid=1; //var statica che tiene conto dell'id della prossima databoard che verrà creata
	private String password;
	public HashMap<String,ArrayList<E>> catelem; 
	//hash map con stringa (categoria) e lista di elementi contenuti in essa
	private HashMap<String,ArrayList<String>> catfriend;
	// hash map con stringa (categoria), e lista di stringhe di amici autorizzati
	
	
	//Hash map con Key una stringa (categoria) e un array list 
	public MyDataBoard2(String psw) throws NullPointerException,IllegalArgumentException {
		if(psw==null) throw new NullPointerException();
		if(psw.equals("")) throw new IllegalArgumentException("Password stringa vuota non valida");
		this.password=psw;
		this.catelem=new HashMap<String,ArrayList<E>>();
		this.catfriend=new HashMap<String,ArrayList<String>>();
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
		if(catelem.containsKey(category)||catfriend.containsKey(category)) new DuplicateEntryException(category);

		//CREAZIONE CATEGORIA
		this.catfriend.put(category, new ArrayList<String>());
		this.catelem.put(category, new ArrayList<E>());
	}

	public void removeCategory(String category, String passw)
			throws IncorrectPasswordException, NullPointerException, NotFoundException {
		//CONTROLLI
		if(category==null) throw new NullPointerException();
		if(!this.password.equals(passw)) throw new IncorrectPasswordException();
		if(!catelem.containsKey(category)||!catfriend.containsKey(category)) throw new NotFoundException(category);
		
		//RIMOZIONE CATEGORIA
		this.catelem.remove(category);
		this.catfriend.remove(category);
	}

	public void addFriend(String category, String passw, String friend)
			throws NullPointerException, IncorrectPasswordException, NotFoundException, DuplicateEntryException,IllegalArgumentException {
		//CONTROLLI
		if(category==null ||friend==null) throw new NullPointerException();
		if(friend.equals(""))throw new IllegalArgumentException("stringa vuota non ammessa");
		if(!this.password.equals(passw)) throw new IncorrectPasswordException();
		if(!catfriend.containsKey(category)||!catelem.containsKey(category)) throw new NotFoundException(category);
		if(catfriend.get(category).contains(friend))throw new DuplicateEntryException(friend); 
		
		//AGGIUNTA
		this.catfriend.get(category).add(friend);
	}

	public void removeFriend(String passw, String friend, String category)
			throws NullPointerException, IncorrectPasswordException, NotFoundException {
		//CONTROLLI
		if(!this.password.equals(passw)) throw new IncorrectPasswordException();
		if(category==null ||friend==null) throw new NullPointerException();
		if(!catfriend.containsKey(category)||!catelem.containsKey(category)) throw new NotFoundException(category);
		if (!catfriend.get(category).contains(friend))throw new NotFoundException(friend);
		//RIMOZIONE
		this.catfriend.get(category).remove(friend);
	}

	public boolean put(String passw, E data, String category) throws NullPointerException, NotFoundException,
			IncorrectPasswordException, DuplicateEntryException, MultipleDataBoardException {

		//CONTROLLI
		if(!this.password.equals(passw)) throw new IncorrectPasswordException();
		if(data.getdb()!=0 && data.getdb()!=this.id)throw new MultipleDataBoardException(data.name,data.getdb());
		//Se non è un dato non allocato e l'id è diverso
		if(data==null ||category==null) throw new NullPointerException();
		if(!catelem.containsKey(category) ||!catfriend.containsKey(category)) throw new NotFoundException(category);
		if(catelem.get(category).contains(data)) throw new DuplicateEntryException(data.name);
		//Il DuplicateEntryException si riferisce al fatto che non posso avere due volte lo stesso dato nella
		//stessa categoria! non è vincolante per categorie diverse
		
		//INSERIMENTO
		data.setdb(this.id);
		return catelem.get(category).add(data); 	
	}

	public E get(String passw, E dato) throws NullPointerException, IncorrectPasswordException, NotFoundException {
		if(dato==null) throw new NullPointerException();
		if(!this.password.equals(passw)) throw new IncorrectPasswordException();
		if(this.id!=dato.getdb()) throw new NotFoundException(dato.name);
		//se l'id associato al dato è corrispondente a questa data board allora sicuramente ci sarà
		//una occorrenza di dato in almeno una categoria
		
		//Iteratore sulle chiavi della HashMap
		Iterator<String> it=catelem.keySet().iterator();
		while (it.hasNext()) {
			String k=it.next();
			if(catelem.get(k).contains(dato)){
				if(catelem.get(k).get(catelem.get(k).indexOf(dato)) instanceof Post) {
					Post p=(Post) catelem.get(k).get(catelem.get(k).indexOf(dato));
					Post pp=p.Clone();
					return (E)pp;
				}
				if(catelem.get(k).get(catelem.get(k).indexOf(dato)) instanceof Immagine) {
					Immagine p=(Immagine) catelem.get(k).get(catelem.get(k).indexOf(dato));
					Immagine pp=p.Clone();
					return (E)pp;
				}
				if(catelem.get(k).get(catelem.get(k).indexOf(dato)) instanceof NotaAudio) {
					NotaAudio p=(NotaAudio) catelem.get(k).get(catelem.get(k).indexOf(dato));
					NotaAudio pp=p.Clone();
					return (E)pp;
				}
				if(catelem.get(k).get(catelem.get(k).indexOf(dato)) instanceof Video) {
					Video p=(Video) catelem.get(k).get(catelem.get(k).indexOf(dato));
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
		if(dato==null) throw new NullPointerException();
		if(!this.password.equals(passw)) throw new IncorrectPasswordException();
		
		//Rimozione
		//Passo 1. Iteratore sulle chiavi della HashMap + Aux per salvarmi una occorrenza da restituire
		Iterator<String> it=catelem.keySet().iterator();
		E aux1; //inizialmente settato a null
		E aux2=null;
		while (it.hasNext()) {
			String k=it.next();
			if(catelem.get(k).contains(dato)){
				aux1=catelem.get(k).remove(catelem.get(k).indexOf(dato));
				if(aux1!=null) aux2=aux1;
			}
		}
		//se non ho trovato restituisco nullo altrimenti una occorrenza che mi sono salvato
		return aux2;
	}

	public List<E> getDataCategory(String passw, String category)
			throws IncorrectPasswordException, NullPointerException, NotFoundException {
		//CONTROLLI 
		if(category==null) throw new NullPointerException();
		if(!this.password.equals(passw)) throw new IncorrectPasswordException();
		if(!catelem.containsKey(category)) throw new NotFoundException(category);
		List<E> aux=catelem.get(category);
		aux=this.arraydeepcopy(aux); 
		//metodo ausiliario che fa la deepcopy degli elementi dell'arraylist iniziale
		return aux;
	}

	public void insertLike(String friend, E data) throws NullPointerException, UnauthorizedFriend {
		//CONTROLLI 
		if(friend==null || data==null) throw new NullPointerException();
		if(this.id!=data.getdb()) throw new NotFoundException(data.name);
		//se l'id associato al dato è corrispondente a questa data board allora sicuramente ci sarà
		//una occorernza di dato in almeno una categoria. 
		
		//come in qualsiasi social, se metto like ad un oggetto che ha già un like, allora lo levo. delego questo
		//controllo al emtodo addlike della classe data		
		
		boolean t=false; //variabile temporanea che mi dice se ho trovato almeno una occorrenza di data in una categoria visibile
		//all'amico, altrimenti devo lanciare una eccezione se l'amico non è autorizzato a vedere nessuna delle categorie
		//che contengono il data.
		
		Iterator<String> it=catelem.keySet().iterator();
		while (it.hasNext()) {
			String k=it.next();
			if(catelem.get(k).contains(data)&&catfriend.get(k).contains(friend)){
				data.addlike(friend);
				t=true;
				break;
			}
		}
		if(t==false) throw new UnauthorizedFriend(friend);		
	}


	public Iterator<E> getIterator(String passw) throws IncorrectPasswordException {
		//Controlli
		if(!this.password.equals(passw)) throw new IncorrectPasswordException();
		//Passo1: mettiamo tutti i dati in una unica struttura List servendosi di un iterator
		List<E> alldata=new ArrayList<E>();
		Iterator<String> it=catelem.keySet().iterator();
		while (it.hasNext()) {
			String k=it.next();
			alldata.addAll(catelem.get(k));
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
		Iterator<String> it=catelem.keySet().iterator();
		while (it.hasNext()) {
			String k=it.next();
			if(catfriend.get(k).contains(friend))
				alldata.addAll(catelem.get(k));
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
	
	public List<E> removeduplicates(List<E> lst){
		//Crea una nuova arraylist
	    List<E> newlst = new ArrayList<E>(); 
	    //scorri attraverso la prima lista 
	    for (E element : lst) { 
	    // se l'elemento non è presente allora aggiungilo 
	    	if (!newlst.contains(element)) { 
	                newlst.add(element); 
	        }
	    } 
	    //restituisco la nuova lista
	    return newlst; 
	}
	
	public void changepsw(String cpassw,String npassw) throws NullPointerException,IncorrectPasswordException,IllegalArgumentException {
		if(npassw==null) throw new NullPointerException();
		if(!this.password.equals(cpassw))throw new IncorrectPasswordException();
		if(npassw.equals(this.password)) throw new IllegalArgumentException("La password deve essere diversa da quella vecchia");
		if(npassw.equals("")) throw new IllegalArgumentException("Stringa vuota non ammessa come password");
		this.password=npassw;		
	}
	/*@REQUIRES: newpsw!=this.password, this.password=cpassw, newpsw!=null,npassw!="" (stringa vuota)
	 *@THROWS: NullPointerException (Unchecked, presente in java) se newpsw==null, IncorrectPasswordException (checked) se this.password!=psw,
	 *IllegalArgumentException (unchecked, presente in java) se newpsw==this.psw oppure newpsw==""
	 *@MODIFIES: this
	 *@EFFECTS: Se i controlli della requires sono superati (this.psw)post=newpsw altrimenti (this.psw)pre=(this.psw)post */
	
	public void printcat() {
		System.out.println("Categorie della Bacheca:\n "+catelem.keySet()+"\n");
	}
	/*@REQUIRES: niente
	 *@EFFECTS: stampa a video tutte le categorie della databoard
	 *@MODIFIES: niente*
	 */
	
	public void printid() {
		System.out.println("l'id di questa databoard è: "+this.id);
	}
	/*@REQUIRES: niente
	 *@EFFECTS: stampa l'id della databoard su cui è invocato il metodo
	 *@MODIFIES: niente*/
	
	public void printautusers(String category)throws NotFoundException,NullPointerException {
		if(category==null) throw new NullPointerException();
		if(!catfriend.containsKey(category)||!catelem.containsKey(category))throw new NotFoundException(category);
		System.out.println("Gli amici autorizzati della categoria "+category+" sono "+catfriend.get(category));
	}
	/*@REQUIRES: category!=null, category appartenente a categories
	 *@THROWS: NullPointerException (unchecked presente in java) se category==null, NotFoundException(checked) se la
	 * categoria non appartiene alla lista
	 *@EFFECTS: stampa il nome degli amici autorizzati alla visione della categoria passata come parametro
	 */
	
	public void printelements(String category)throws NotFoundException,NullPointerException {
		if(category==null) throw new NullPointerException();
		if(!catelem.containsKey(category)||!catfriend.containsKey(category))throw new NotFoundException(category);
		System.out.println("I dati appartenenti alla categoria "+category+" sono\n"+catelem.get(category));
	}
	/*@REQUIRES: category!=null, category appartenente a categories
	 *@THROWS: NullPointerException (unchecked presente in java) se category==null, NotFoundException (checked) se la
	 * categoria non appartiene alla lista
	 *@EFFECTS: stampa il nome degli elementi che appartengono alla categoria passata come parametro*/
	
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
