import java.util.*;
public class Testbench {
	
	public static void main(String[] args) {
		System.out.println("Benvenuto nel testbench del progetto DataBoard di Simone Schiavone");
		implementazione1();
		implementazione2();
	}
	@SuppressWarnings("unused")
	public static void implementazione1() {		
		//Test della prima implementazione
		
		System.out.println("PROVA IMPLEMENTAZIONE 1\n\n");
		
		System.out.println("TEST SUI METODI COSTRUTTORI DI Data E DEI SUOI SOTTOTIPI");
		
		//--------------------Dichiarazione di oggetti di tipo Immagine--------------------
		try {
			System.out.println("Dichiaro una immagine con nome nullo, mi aspetto NullPointerException");
			Immagine tempimg=new Immagine(null,500,500);
		}catch(NullPointerException e) {
			System.out.println(e);
		}
		try {
			System.out.println("Dichiaro una immagine con nome stringa vuota, mi aspetto IllegalArgumentException");
			Immagine tempimg=new Immagine("",500,500);
		}catch(IllegalArgumentException e) {
			System.out.println(e);
		}
		try {
			System.out.println("Dichiaro una immagine con nr pixel negativo, mi aspetto IllegalArgumentException");
			Immagine tempimg=new Immagine("tramonto",-500,500);
		}catch(IllegalArgumentException e) {
			System.out.println(e);
		}
		Immagine tramonto=new Immagine("sunset",500,540);
		tramonto.display();
		
		//--------------------Dichiarazione oggetti di tipo post--------------------
		Post temppost;
		try {
			System.out.println("Dichiaro un post con nome nullo, mi aspetto NullPointerException");
			temppost=new Post(null,"...");
		}catch(NullPointerException e) {
			System.out.println(e);
		}
		try {
			System.out.println("Dichiaro un post con nome stringa vuota, mi aspetto IllegalArgumentException");
			temppost=new Post("","...");
		}catch(IllegalArgumentException e) {
			System.out.println(e);
		}
		try {
			System.out.println("Dichiaro un post con annuncio vuoto, mi aspetto IllegalArgumentException");
			temppost=new Post("notizia","");
		}catch(IllegalArgumentException e) {
			System.out.println(e);
		}
		Post stato=new Post("notizia","...");
		stato.display();
		
		//--------------------Dichiarazione oggetti di tipo NotaAudio--------------------
		NotaAudio tempaudio;
		try {
			System.out.println("Dichiaro una NotaAudio con nome nullo, mi aspetto NullPointerException");
			tempaudio=new NotaAudio(null,60);
		}catch(NullPointerException e) {
			System.out.println(e);
		}
		try {
			System.out.println("Dichiaro una NotaAudio con nome stringa vuota, mi aspetto IllegalArgumentException");
			tempaudio=new NotaAudio("",60);
		}catch(IllegalArgumentException e) {
			System.out.println(e);
		}
		try {
			System.out.println("Dichiaro una NotaAudio con durata negativa, mi aspetto IllegalArgumentException");
			tempaudio=new NotaAudio("msg",-3);
		}catch(IllegalArgumentException e) {
			System.out.println(e);
		}
		NotaAudio vocale=new NotaAudio("msgvocale",45);
		vocale.display();
		
		//--------------------Dichiarazione oggetti di tipo video--------------------
		Video tempvideo;
		try {
			System.out.println("Dichiaro un video con nome nullo, mi aspetto NullPointerException");
			tempvideo=new Video(null,130,"hd");
		}catch(NullPointerException e) {
			System.out.println(e);
		}
		try {
			System.out.println("Dichiaro un video con nome stringa vuota, mi aspetto IllegalArgumentException");
			tempvideo=new Video("",130,"hd");
		}catch(IllegalArgumentException e) {
			System.out.println(e);
		}
		try {
			System.out.println("Dichiaro un video con durata negativa, mi aspetto IllegalArgumentException");
			tempvideo=new Video("vd",-130,"hd");
		}catch(IllegalArgumentException e) {
			System.out.println(e);
		}
		try {
			System.out.println("Dichiaro un video con qualità non valida, mi aspetto IllegalArgumentException");
			tempvideo=new Video("vd",130,"bassa");
		}catch(IllegalArgumentException e) {
			System.out.println(e);
		}
		Video vd=new Video("video",90,"hd");
		vd.display();
		
		//--------------------Dichiarazione di una databoard--------------------
		System.out.println("\nTEST SUL METODO COSTRUTTORE DELLA DATA BOARD");
		MyDataBoard<Data> tempdb;
		try {
			System.out.println("Dichiaro una bacheca con password nulla, mi aspetto una NullPointerException");
			tempdb=new MyDataBoard<Data>(null);
		}catch(NullPointerException e) {
			System.out.println(e);
		}
		try {
			System.out.println("Dichiaro una bacheca con password stringa vuota, mi aspetto una IllegalArgumentException");
			tempdb=new MyDataBoard<Data>("");
		}catch(IllegalArgumentException e) {
			System.out.println(e);
		}
		MyDataBoard<Data> bacheca=new MyDataBoard<Data>("java");
		MyDataBoard<Post> altrabacheca=new MyDataBoard<Post>("pippo");
		
		//--------------------Creazione di categorie--------------------
		System.out.println("\nTEST SUL METODO CREATE CATEGORY");
		try {
			System.out.println("creo una categoria con nome nullo, mi aspetto una NullPointerException");
			bacheca.createCategory(null, "java");
		}catch(NullPointerException e) {
			System.out.println(e);
		}catch(IncorrectPasswordException e) {
			System.out.println(e);
		}
		try {
			System.out.println("creo una categoria con nome stringa vuota, mi aspetto una IllegalArgumentException");
			bacheca.createCategory("", "java");
		}catch(IllegalArgumentException e) {
			System.out.println(e);
		}catch(IncorrectPasswordException e) {
			System.out.println(e);
		}
		try {
			System.out.println("creo una categoria inserendo una password sbagliata, mi aspetto una IncorrectPasswordException");
			bacheca.createCategory("Test", "wrongpsw");
		}catch(IncorrectPasswordException e) {
			System.out.println(e);
		}
		try {
			System.out.println("Inserisco una categoria già presente, mi aspetto una DuplicateEntryException");
			bacheca.createCategory("cat1", "java");
			bacheca.createCategory("cat1", "java");
		}catch(IncorrectPasswordException e) {
			System.out.println(e);
		}catch(DuplicateEntryException e) {
			System.out.println(e);
		}
		try {
			bacheca.createCategory("cat2", "java");
			bacheca.createCategory("cat3", "java");
		}catch(IncorrectPasswordException e) {
			System.out.println(e);
		}	
		bacheca.printcat(); //stampa categorie prima della rimozione
		
		//--------------------Rimozione di categorie--------------------
		System.out.println("\nTEST SUL METODO REMOVE CATEGORY");
		try {
			System.out.println("rimuovo una categoria con nome nullo, mi aspetto una NullPointerException");
			bacheca.removeCategory(null, "java");
		}catch(NullPointerException e) {
			System.out.println(e);
		}catch(IncorrectPasswordException e) {
			System.out.println(e);
		}
		try {
			System.out.println("rimuovo una categoria non presente, mi aspetto una NotFoundException");
			bacheca.removeCategory("catnonpresente","java");
		}catch(NotFoundException e) {
			System.out.println(e);
		}catch(IncorrectPasswordException e) {
			System.out.println(e);
		}
		try {
			System.out.println("inserisco una password sbagliata, mi aspetto una IncorrectPasswordException");
			bacheca.removeCategory("cat1","wrongpsw");
		}catch(IncorrectPasswordException e) {
			System.out.println(e);
		}
		try {
			bacheca.removeCategory("cat3", "java");
			System.out.println("rimuovo cat3");
			bacheca.printcat(); //Stampa delle categorie dopo la rimozione
			//Osservo la non presenza di cat3 tra le categorie della databoard bacheca
		}catch(IncorrectPasswordException e) {
			System.out.println(e);
		}
		
		//--------------------Aggiunta di amici autorizzati ad una categoria--------------------
		System.out.println("\nTEST SUL METODO ADD FRIEND");
		try {
			System.out.println("aggiungo un amico inserendo una password sbagliata, mi aspetto una IncorrectPasswordException");
			bacheca.addFriend("cat1","wrongpsw","Giovanni");
		}catch(IncorrectPasswordException e) {
			System.out.println(e);
		}
		try {
			System.out.println("aggiungo un amico inserendo una categoria nulla, mi aspetto una NullPointerException");
			bacheca.addFriend(null,"java","Giovanni");
		}catch(IncorrectPasswordException e) {
			System.out.println(e);
		}catch(NullPointerException e) {
			System.out.println(e);
		}
		try {
			System.out.println("aggiungo un amico inserendo un amico nullo, mi aspetto una NullPointerException");
			bacheca.addFriend("cat1","java",null);
		}catch(IncorrectPasswordException e) {
			System.out.println(e);
		}catch(NullPointerException e) {
			System.out.println(e);
		}
		try {
			System.out.println("aggiungo un amico inserendo una categoria non presente, mi aspetto una NotFoundException");
			bacheca.addFriend("catnonpresente","java","Giovanni");
		}catch(IncorrectPasswordException e) {
			System.out.println(e);
		}catch(NotFoundException e) {
			System.out.println(e);
		}
		try {
			System.out.println("aggiungo un amico già presente tra gli autorizzati nella categoria, mi aspetto una DuplicateEntryException");
			bacheca.addFriend("cat1","java","Giovanni");
			bacheca.addFriend("cat1","java","Marco");
			bacheca.addFriend("cat1","java","Anna");
			bacheca.addFriend("cat1","java","Giovanni");
		}catch(IncorrectPasswordException e) {
			System.out.println(e);
		}catch(DuplicateEntryException e) {
			System.out.println(e);
		}
		bacheca.printautusers("cat1"); //stampa utenti autorizzati della categoria
		
		//--------------------Rimozione amici autorizzati ad una categoria--------------------
		System.out.println("\nTEST SUL METODO REMOVE FRIEND");
		try {
			System.out.println("rimuovo un amico inserendo una password sbagliata, mi aspetto una IncorrectPasswordException");
			bacheca.removeFriend("wrongpsw", "Giovanni", "cat1");
		}catch(IncorrectPasswordException e) {
			System.out.println(e);
		}
		try {
			System.out.println("rimuovo un amico inserendo una categoria nulla, mi aspetto una NullPointerException");
			bacheca.removeFriend("java", "Giovanni", null);
		}catch(IncorrectPasswordException e) {
			System.out.println(e);
		}catch(NullPointerException e) {
			System.out.println(e);
		}
		try {
			System.out.println("rimuovo un amico inserendo un amico nullo, mi aspetto una NullPointerException");
			bacheca.removeFriend("java",null,"cat1");
			}catch(IncorrectPasswordException e) {
				System.out.println(e);	
			}catch(NullPointerException e) {
				System.out.println(e);
			}
		try {
			System.out.println("rimuovo un amico inserendo una categoria non presente, mi aspetto una NotFoundException");
			bacheca.removeFriend("java","Giovanni","catnonpresente");
		}catch(IncorrectPasswordException e) {
			System.out.println(e);
		}catch(NotFoundException e) {
			System.out.println(e);
		}
		try {
			bacheca.removeFriend("java","Giovanni","cat1");
			System.out.println("ho rimosso Giovanni");
		}catch(IncorrectPasswordException e) {
			System.out.println(e);
		}catch(NotFoundException e) {
			System.out.println(e);
		}
		bacheca.printautusers("cat1"); //stampa utenti autorizzati della categoria
		//Osservo la non presenza di Giovanni tra gli amici autorizzati di cat1
				
		//--------------------Aggiunta di un dato ad una categoria-------------------- 
		System.out.println("\nTEST SUL METODO PUT");
		Post posttmp=new Post("temporaneo","..");
		try {
			System.out.println("aggiungo un dato in bacheca con una password sbagliata, mi aspetto una IncorrectPasswordException");
			bacheca.put("wrongpsw",posttmp,"cat1");
		}catch(IncorrectPasswordException e) {
			System.out.println(e);
		}
		try {
			System.out.println("aggiungo un dato nullo in bacheca, mi aspetto una NullPointerException");
			bacheca.put("java",null,"cat1");
		}catch(IncorrectPasswordException e) {
			System.out.println(e);
		}catch(NullPointerException e) {
			System.out.println(e);
		}
		try {
			System.out.println("aggiungo un dato in bacheca in una categoria nulla, mi aspetto una NullPointerException");
			bacheca.put("java",posttmp,null);
		}catch(IncorrectPasswordException e) {
			System.out.println(e);
		}catch(NullPointerException e) {
			System.out.println(e);
		}
		try {
			System.out.println("aggiungo un dato in bacheca in una categoria non presente, mi aspetto una NotFoundException");
			bacheca.put("java",posttmp,"catnonpresente");
		}catch(IncorrectPasswordException e) {
			System.out.println(e);
		}catch(NotFoundException e) {
			System.out.println(e);
		}
		try {
			System.out.println("aggiungo un dato in bacheca in una categoria che già contiene in dato, mi aspetto una DuplicateEntryException");
			bacheca.put("java",posttmp,"cat1");
			bacheca.put("java",posttmp,"cat1");
		}catch(IncorrectPasswordException e) {
			System.out.println(e);
		}catch(DuplicateEntryException e) {
			System.out.println(e);
		}
		try {
			System.out.println("aggiungo un dato in bacheca che si trova già in un'altra data board"
					+ ", mi aspetto una MultipleDataBoardException");
			altrabacheca.put("pippo",posttmp,"cat1");
		}catch(IncorrectPasswordException e) {
			System.out.println(e);
		}catch(MultipleDataBoardException e) {
			System.out.println(e);
		}
		try {
			bacheca.put("java",vd,"cat1");
			bacheca.put("java",vocale,"cat1");
			bacheca.put("java",tramonto,"cat2");
			bacheca.put("java",stato,"cat2");
			bacheca.put("java",tramonto,"cat1");
			//da notare l'inserimento di tramonto in entrambe le categorie che è concesso in queste implementazioni
		}catch(IncorrectPasswordException e) {
			System.out.println(e);
		}
			bacheca.printelements("cat1");
			bacheca.printelements("cat2");
			
		//--------------------Ottenimento di una copia del dato in bacheca --------------------
		System.out.println("\nTEST SUL METODO GET");
		Immagine np=new Immagine("imgnp",80,80);
		try {
			System.out.println("invoco una get inserendo una password sbagliata, mi aspetto una IncorrectPasswordException");
			bacheca.get("wrongpsw", posttmp);
		}catch(IncorrectPasswordException e){
			System.out.println(e);
		}
		try {
			System.out.println("invoco una get inserendo un dato non presente in bacheca, mi aspetto una NotFoundException");
			bacheca.get("java", np);
		}catch(IncorrectPasswordException e){
			System.out.println(e);
		}catch(NotFoundException e){
			System.out.println(e);
		}
		try {
			System.out.println("invoco una get inserendo un dato nullo, mi aspetto una NullPointerException");
			bacheca.get("java", null);
		}catch(IncorrectPasswordException e){
			System.out.println(e);
		}catch(NullPointerException e){
			System.out.println(e);
		}
		
		try {
			Data cp=bacheca.get("java", posttmp);	
			//verifico di aver effettivamente avuto una copia del dato
			System.out.println("Aggiungo un like al posttmp e poi invoco la display per dimostrare che ho fatto una deepcopy del dato");
			bacheca.insertLike("Anna", posttmp);
			bacheca.insertLike("Marco", posttmp);
			cp.display();
			posttmp.display();
		}catch(IncorrectPasswordException e){
			System.out.println(e);
		}
		
		
		//--------------------Rimozione di un dato (tutte le occorrenze di esso) dalla bacheca--------------------
		System.out.println("\nTEST SUL METODO REMOVE");
		//stampo i dati che erano in bacheca prima della rimozione
		bacheca.printelements("cat1");
		bacheca.printelements("cat2");
		try {
			System.out.println("invoco una remove inserendo una password sbagliata, mi aspetto una IncorrectPasswordException");
			bacheca.remove("wrongpsw", posttmp);
		}catch(IncorrectPasswordException e){
			System.out.println(e);
		}
		try {
			System.out.println("invoco una remove passando come param. un dato nullo, mi aspetto una NullPointerException");
			bacheca.remove("java",null);
		}catch(IncorrectPasswordException e){
			System.out.println(e);
		}catch(NullPointerException e) {
			System.out.println(e);
		}
		try {
			System.out.println("invoco una remove passando come param. un dato non presente in bacheca, mi aspetto una"
					+ " restituzione di null");
			System.out.println(bacheca.remove("java",np)); //np è una immagine dichiarata nel controllo precedente
		}catch(IncorrectPasswordException e){
			System.out.println(e);
		}catch(NullPointerException e) {
			System.out.println(e);
		}
		//rimuovo il dato temporaneo temp (presente in cat1)
		try {
			bacheca.remove("java", posttmp);
			System.out.println("ho rimosso "+posttmp);
			bacheca.printelements("cat1");
			//Osservo la rimozione di temp da cat1
		}catch(IncorrectPasswordException e) {
			System.out.println(e);
		}
				
		//--------------------Ottenimento di una lista di dati presenti in una categoria--------------------
		System.out.println("\nTEST SUL METODO GETDATACATEGORY");
		List<Data> templist;
		try {
			System.out.println("invoco la getdatacategory con password errata, mi aspetto una IncorrectPasswordException");
			templist=bacheca.getDataCategory("wrongpsw", "cat1");
		}catch(IncorrectPasswordException e) {
			System.out.println(e);
		}
		try {
			System.out.println("invoco la getdatacategory con categoria nulla, mi aspetto una NullPointerException");
			templist=bacheca.getDataCategory("java",null);
		}catch(IncorrectPasswordException e) {
			System.out.println(e);
		}catch(NullPointerException e) {
			System.out.println(e);
		}
		try {
			System.out.println("invoco la getdatacategory con categoria non presente nella bacheca, mi aspetto una NotFoundException");
			templist=bacheca.getDataCategory("java","catnonpresente");
		}catch(IncorrectPasswordException e) {
			System.out.println(e);
		}catch(NotFoundException e) {
			System.out.println(e);
		}
		try {
			System.out.println("Stampa della lista dei dati di cat1");
			templist=bacheca.getDataCategory("java","cat1");
			System.out.println(templist);
			//mi aspetto di avere una lista con gli elementi di cat1
		}catch(IncorrectPasswordException e) {
			System.out.println(e);
		}
			
		//--------------------Inserimento di un like ad un dato presente in bacheca--------------------
		System.out.println("\nTEST SUL METODO INSERTLIKE");
		try {
			System.out.println("invoco la insert like passando una categoria nulla come parametro, mi aspetto"
					+ " una NullPointerException");
			bacheca.insertLike(null, tramonto);
		}catch(NullPointerException e) {
			System.out.println(e);
		}
		try {
			System.out.println("invoco la insert like passando come parametro un dato null, mi aspetto una "
					+ "NullPointerException");
			bacheca.insertLike("Marco",null);
		}catch(NullPointerException e) {
			System.out.println(e);
		}
		try {
			System.out.println("invoco la insert like passando come un amico che non ha accesso a nessuna categoria"
					+ " in cui si trova il dato, mi aspetto una UnauthorizedFriend");
			bacheca.insertLike("Topolino",tramonto);
		}catch(UnauthorizedFriend e) {
			System.out.println(e);
		}
		bacheca.insertLike("Marco",tramonto);
		bacheca.insertLike("Anna", vocale);
		bacheca.insertLike("Marco", vocale);
		bacheca.insertLike("Anna", tramonto);
		//Da notare che Marco mette like al dato tramonto. Marco ha accesso ad almeno una categoria in cui si trova tramonto cioè cat1.
		System.out.println(tramonto.name+" ha "+tramonto.getnlike()+" likes");
		System.out.println(vocale.name+" ha "+vocale.getnlike()+" likes\nMarco richiama la insertlike su sunset");

		bacheca.insertLike("Marco",tramonto); //un doppio like corrisponde alla rimozione di esso
		System.out.println(tramonto.name+" ha "+tramonto.getnlike()+" likes");
				
		//--------------------restituisce un iteratore che genera tutti i dati in bacheca ordinati in base al numero di like--------------------
		System.out.println("\nTEST SUL METODO GETITERATOR");
		Iterator<Data>it = null; //inizializzo con null
		try {
			System.out.println("invoco getiterator inserendo una password sbagliata, mi aspetto una IncorrectPasswordException");
			it=bacheca.getIterator("wrongpsw");
		}catch(IncorrectPasswordException e) {
			System.out.println(e);
		}
		try {
			it=bacheca.getIterator("java");
			System.out.println("chiamo il metodo remove dell'iteratore, mi aspetto una UnsupportedOperationException");
			it.remove();
		}catch(IncorrectPasswordException e) {
			System.out.println(e);
		}catch(UnsupportedOperationException e) {
			System.out.println(e);
		}
		System.out.println("Stampa dei dati in ordine di nr di like ricevuti");
		while(it.hasNext()) {
			Data var=it.next();
			System.out.println(var.name+"->"+var.getnlike());
		}
		
		//--------------------restituisce un iteratore che genera tutti i dati in bacheca visibili ad un amico passato come parametro--------------------
		System.out.println("\nTEST SUL METODO GETFRIEND ITERATOR");
		try {
			System.out.println("invoco il metodo passando come parametro amico una stringa nulla, mi aspetto una NullPointerException");
			it=bacheca.getFriendIterator(null);
		}catch(NullPointerException e) {
			System.out.println(e);
		}
		try {
			System.out.println("chiamo il metodo remove dell'iteratore, mi aspetto una UnsupportedOperationException");
			it=bacheca.getFriendIterator("Anna");
			it.remove();
		}catch(UnsupportedOperationException e) {
			System.out.println(e);
		}
		System.out.println("Stampa dei dati della databoard visibili ad anna");
		while(it.hasNext()) {
			Data var=it.next();
			System.out.print(var.name+"\t");
		}
	}
	
	@SuppressWarnings("unused")
	public static void implementazione2() {
		//Test della seconda implementazione
		
		System.out.println("\n\nPROVA IMPLEMENTAZIONE 2\n\n");
		
		System.out.println("TEST SUI METODI COSTRUTTORI DI Data E DEI SUOI SOTTOTIPI");
		//--------------------Dichiarazione di oggetti di tipo Immagine--------------------
		try {
			System.out.println("Dichiaro una immagine con nome nullo, mi aspetto NullPointerException");
			Immagine tempimg=new Immagine(null,500,500);
		}catch(NullPointerException e) {
			System.out.println(e);
		}
		try {
			System.out.println("Dichiaro una immagine con nome stringa vuota, mi aspetto IllegalArgumentException");
			Immagine tempimg=new Immagine("",500,500);
		}catch(IllegalArgumentException e) {
			System.out.println(e);
		}
		try {
			System.out.println("Dichiaro una immagine con nr pixel negativo, mi aspetto IllegalArgumentException");
			Immagine tempimg=new Immagine("tramonto",-500,500);
		}catch(IllegalArgumentException e) {
			System.out.println(e);
		}
		Immagine tramonto=new Immagine("sunset",500,540);
		tramonto.display();
		//--------------------Dichiarazione oggetti di tipo post--------------------
		Post temppost;
		try {
			System.out.println("Dichiaro un post con nome nullo, mi aspetto NullPointerException");
			temppost=new Post(null,"...");
		}catch(NullPointerException e) {
			System.out.println(e);
		}
		try {
			System.out.println("Dichiaro un post con nome stringa vuota, mi aspetto IllegalArgumentException");
			temppost=new Post("","...");
		}catch(IllegalArgumentException e) {
			System.out.println(e);
		}
		try {
			System.out.println("Dichiaro un post con annuncio vuoto, mi aspetto IllegalArgumentException");
			temppost=new Post("notizia","");
		}catch(IllegalArgumentException e) {
			System.out.println(e);
		}
		Post stato=new Post("notizia","...");
		stato.display();
		
		//--------------------Dichiarazione oggetti di tipo NotaAudio--------------------
		NotaAudio tempaudio;
		try {
			System.out.println("Dichiaro una NotaAudio con nome nullo, mi aspetto NullPointerException");
			tempaudio=new NotaAudio(null,60);
		}catch(NullPointerException e) {
			System.out.println(e);
		}
		try {
			System.out.println("Dichiaro una NotaAudio con nome stringa vuota, mi aspetto IllegalArgumentException");
			tempaudio=new NotaAudio("",60);
		}catch(IllegalArgumentException e) {
			System.out.println(e);
		}
		try {
			System.out.println("Dichiaro una NotaAudio con durata negativa, mi aspetto IllegalArgumentException");
			tempaudio=new NotaAudio("msg",-3);
		}catch(IllegalArgumentException e) {
			System.out.println(e);
		}
		NotaAudio vocale=new NotaAudio("msgvocale",45);
		vocale.display();
		
		//--------------------Dichiarazione oggetti di tipo video--------------------
		Video tempvideo;
		try {
			System.out.println("Dichiaro un video con nome nullo, mi aspetto NullPointerException");
			tempvideo=new Video(null,130,"hd");
		}catch(NullPointerException e) {
			System.out.println(e);
		}
		try {
			System.out.println("Dichiaro un video con nome stringa vuota, mi aspetto IllegalArgumentException");
			tempvideo=new Video("",130,"hd");
		}catch(IllegalArgumentException e) {
			System.out.println(e);
		}
		try {
			System.out.println("Dichiaro un video con durata negativa, mi aspetto IllegalArgumentException");
			tempvideo=new Video("vd",-130,"hd");
		}catch(IllegalArgumentException e) {
			System.out.println(e);
		}
		try {
			System.out.println("Dichiaro un video con qualità non valida, mi aspetto IllegalArgumentException");
			tempvideo=new Video("vd",130,"bassa");
		}catch(IllegalArgumentException e) {
			System.out.println(e);
		}
		Video vd=new Video("video",90,"hd");
		vd.display();
				
		//--------------------Dichiarazione di una databoard--------------------
		System.out.println("\nTEST SUL METODO COSTRUTTORE DELLA DATA BOARD");
		MyDataBoard2<Data> tempdb;
		try {
			System.out.println("Dichiaro una bacheca con password nulla, mi aspetto una NullPointerException");
			tempdb=new MyDataBoard2<Data>(null);
		}catch(NullPointerException e) {
			System.out.println(e);
		}
		try {
			System.out.println("Dichiaro una bacheca con password stringa vuota, mi aspetto una NullPointerException");
			tempdb=new MyDataBoard2<Data>("");
		}catch(IllegalArgumentException e) {
			System.out.println(e);
		}
		MyDataBoard2<Data> bacheca=new MyDataBoard2<Data>("java");
		MyDataBoard2<Post> altrabacheca=new MyDataBoard2<Post>("pippo");
		
		//--------------------Creazione di categorie--------------------
		System.out.println("\nTEST SUL METODO CREATE CATEGORY");
		try {
			System.out.println("creo una categoria con nome nullo, mi aspetto una NullPointerException");
			bacheca.createCategory(null, "java");
		}catch(NullPointerException e) {
			System.out.println(e);
		}catch(IncorrectPasswordException e) {
			System.out.println(e);
		}
		try {
			System.out.println("creo una categoria con nome stringa vuota, mi aspetto una IllegalArgumentException");
			bacheca.createCategory("", "java");
		}catch(IllegalArgumentException e) {
			System.out.println(e);
		}catch(IncorrectPasswordException e) {
			System.out.println(e);
		}
		try {
			System.out.println("creo una categoria inserendo una password sbagliata, mi aspetto una IncorrectPasswordException");
			bacheca.createCategory("Test", "wrongpsw");
		}catch(IncorrectPasswordException e) {
			System.out.println(e);
		}
		try {
			System.out.println("Inserisco una categoria già presente, mi aspetto una DuplicateEntryException");
			bacheca.createCategory("cat1", "java");
			bacheca.createCategory("cat1", "java");
		}catch(IncorrectPasswordException e) {
			System.out.println(e);
		}catch(DuplicateEntryException e) {
			System.out.println(e);
		}
		try {
			bacheca.createCategory("cat2", "java");
			bacheca.createCategory("cat3", "java");
		}catch(IncorrectPasswordException e) {
			System.out.println(e);
		}	
		bacheca.printcat(); //stampa categorie prima della rimozione
		
		//--------------------Rimozione di categorie--------------------
		System.out.println("\nTEST SUL METODO CREATE CATEGORY");
		try {
			System.out.println("rimuovo una categoria con nome nullo, mi aspetto una NullPointerException");
			bacheca.removeCategory(null, "java");
		}catch(NullPointerException e) {
			System.out.println(e);
		}catch(IncorrectPasswordException e) {
			System.out.println(e);
		}
		try {
			System.out.println("rimuovo una categoria non presente, mi aspetto una NotFoundException");
			bacheca.removeCategory("catnonpresente","java");
		}catch(NotFoundException e) {
			System.out.println(e);
		}catch(IncorrectPasswordException e) {
			System.out.println(e);
		}
		try {
			System.out.println("inserisco una password sbagliata, mi aspetto una IncorrectPasswordException");
			bacheca.removeCategory("cat1","wrongpsw");
		}catch(IncorrectPasswordException e) {
			System.out.println(e);
		}
		try {
			bacheca.removeCategory("cat3", "java");
			System.out.println("rimuovo cat3");
			bacheca.printcat(); //Stampa delle categorie dopo la rimozione
			//Osservo la non presenza di cat3 tra le categorie della databoard bacheca
		}catch(IncorrectPasswordException e) {
			System.out.println(e);
		}
		
		//--------------------Aggiunta di amici autorizzati ad una categoria--------------------
		System.out.println("\nTEST SUL METODO ADD FRIEND");
		try {
			System.out.println("aggiungo un amico inserendo una password sbagliata, mi aspetto una IncorrectPasswordException");
			bacheca.addFriend("cat1","wrongpsw","Giovanni");
		}catch(IncorrectPasswordException e) {
			System.out.println(e);
		}
		try {
			System.out.println("aggiungo un amico inserendo una categoria nulla, mi aspetto una NullPointerException");
			bacheca.addFriend(null,"java","Giovanni");
		}catch(IncorrectPasswordException e) {
			System.out.println(e);
		}catch(NullPointerException e) {
			System.out.println(e);
		}
		try {
			System.out.println("aggiungo un amico inserendo un amico nullo, mi aspetto una NullPointerException");
			bacheca.addFriend("cat1","java",null);
		}catch(IncorrectPasswordException e) {
			System.out.println(e);
		}catch(NullPointerException e) {
			System.out.println(e);
		}
		try {
			System.out.println("aggiungo un amico inserendo una categoria non presente, mi aspetto una NotFoundException");
			bacheca.addFriend("catnonpresente","java","Giovanni");
		}catch(IncorrectPasswordException e) {
			System.out.println(e);
		}catch(NotFoundException e) {
			System.out.println(e);
		}
		try {
			System.out.println("aggiungo un amico già presente tra gli autorizzati nella categoria, mi aspetto una DuplicateEntryException");
			bacheca.addFriend("cat1","java","Giovanni");
			bacheca.addFriend("cat1","java","Marco");
			bacheca.addFriend("cat1","java","Anna");
			bacheca.addFriend("cat1","java","Giovanni");
		}catch(IncorrectPasswordException e) {
			System.out.println(e);
		}catch(DuplicateEntryException e) {
			System.out.println(e);
		}
		bacheca.printautusers("cat1"); //stampa utenti autorizzati della categoria
		
		//--------------------Rimozione amici autorizzati ad una categoria--------------------
		System.out.println("\nTEST SUL METODO REMOVE FRIEND");
		try {
			System.out.println("rimuovo un amico inserendo una password sbagliata, mi aspetto una IncorrectPasswordException");
			bacheca.removeFriend("wrongpsw", "Giovanni", "cat1");
		}catch(IncorrectPasswordException e) {
			System.out.println(e);
		}
		try {
			System.out.println("rimuovo un amico inserendo una categoria nulla, mi aspetto una NullPointerException");
			bacheca.removeFriend("java", "Giovanni", null);
		}catch(IncorrectPasswordException e) {
			System.out.println(e);
		}catch(NullPointerException e) {
			System.out.println(e);
		}
		try {
			System.out.println("rimuovo un amico inserendo un amico nullo, mi aspetto una NullPointerException");
			bacheca.removeFriend("java",null,"cat1");
		}catch(IncorrectPasswordException e) {
			System.out.println(e);
		}catch(NullPointerException e) {
			System.out.println(e);
		}
		try {
			System.out.println("aggiungo un amico inserendo una categoria non presente, mi aspetto una NotFoundException");
			bacheca.removeFriend("java","Giovanni","cat1");
			bacheca.removeFriend("java","Giovanni","catnonpresente");
		}catch(IncorrectPasswordException e) {
			System.out.println(e);
		}catch(NotFoundException e) {
			System.out.println(e);
		}
		try {
			bacheca.removeFriend("java","Giovanni","cat1");
			System.out.println("ho rimosso Giovanni");
		}catch(IncorrectPasswordException e) {
			System.out.println(e);
		}catch(NotFoundException e) {
			System.out.println(e);
		}
		bacheca.printautusers("cat1"); //stampa utenti autorizzati della categoria
		//Osservo la non presenza di Giovanni tra gli amici autorizzati di cat1
		
		//--------------------Aggiunta di un dato ad una categoria--------------------
		System.out.println("\nTEST SUL METODO PUT");
		Post posttmp=new Post("temporaneo","..");
		try {
			System.out.println("aggiungo un dato in bacheca con una password sbagliata, mi aspetto una IncorrectPasswordException");
			bacheca.put("wrongpsw",posttmp,"cat1");
		}catch(IncorrectPasswordException e) {
			System.out.println(e);
		}
		try {
			System.out.println("aggiungo un dato nullo in bacheca, mi aspetto una NullPointerException");
			bacheca.put("java",null,"cat1");
		}catch(IncorrectPasswordException e) {
			System.out.println(e);
		}catch(NullPointerException e) {
			System.out.println(e);
		}
		try {
			System.out.println("aggiungo un dato in bacheca in una categoria nulla, mi aspetto una NullPointerException");
			bacheca.put("java",posttmp,null);
		}catch(IncorrectPasswordException e) {
			System.out.println(e);
		}catch(NullPointerException e) {
			System.out.println(e);
		}
		try {
			System.out.println("aggiungo un dato in bacheca in una categoria non presente, mi aspetto una NotFoundException");
			bacheca.put("java",posttmp,"catnonpresente");
		}catch(IncorrectPasswordException e) {
			System.out.println(e);
		}catch(NotFoundException e) {
			System.out.println(e);
		}
		try {
			System.out.println("aggiungo un dato in bacheca in una categoria che già contiene in dato, mi aspetto una DuplicateEntryException");
			bacheca.put("java",posttmp,"cat1");
			bacheca.put("java",posttmp,"cat1");
		}catch(IncorrectPasswordException e) {
			System.out.println(e);
		}catch(DuplicateEntryException e) {
			System.out.println(e);
		}
		try {
			System.out.println("aggiungo un dato in bacheca che si trova già in un'altra data board"
					+ ", mi aspetto una MultipleDataBoardException");
			altrabacheca.put("pippo",posttmp,"cat1");
		}catch(IncorrectPasswordException e) {
			System.out.println(e);
		}catch(MultipleDataBoardException e) {
			System.out.println(e);
		}
		try {
			bacheca.put("java",vd,"cat1");
			bacheca.put("java",vocale,"cat1");
			bacheca.put("java",tramonto,"cat2");
			bacheca.put("java",stato,"cat2");
			bacheca.put("java",tramonto,"cat1");
			//da notare l'inserimento di tramonto in entrambe le categorie
		}catch(IncorrectPasswordException e) {
			System.out.println(e);
		}
		bacheca.printelements("cat1");
		bacheca.printelements("cat2");
	
		//--------------------Ottenimento di una copia del dato in bacheca-------------------- 
		System.out.println("\nTEST SUL METODO GET");
		Immagine np=new Immagine("imgnp",80,80);
		try {
			System.out.println("invoco una get inserendo una password sbagliata, mi aspetto una IncorrectPasswordException");
			bacheca.get("wrongpsw", posttmp);
		}catch(IncorrectPasswordException e){
			System.out.println(e);
		}
		try {
			System.out.println("invoco una get inserendo un dato non presente in bacheca, mi aspetto una NotFoundException");
			bacheca.get("java", np);
		}catch(IncorrectPasswordException e){
			System.out.println(e);
		}catch(NotFoundException e){
			System.out.println(e);
		}
		try {
			System.out.println("invoco una get inserendo un dato nullo, mi aspetto una NullPointerException");
			bacheca.get("java", null);
		}catch(IncorrectPasswordException e){
			System.out.println(e);
		}catch(NullPointerException e){
			System.out.println(e);
		}
		try {
			Data cp=bacheca.get("java", posttmp);	
			//verifico di aver effettivamente avuto una copia del dato
			System.out.println("Aggiungo un like al posttmp e poi invoco la display per dimostrare che ho fatto una deepcopy del dato");
			bacheca.insertLike("Anna", posttmp);
			bacheca.insertLike("Marco", posttmp);
			cp.display();
			posttmp.display();
		}catch(IncorrectPasswordException e){
			System.out.println(e);
		}
		//--------------------Rimozione di un dato (tutte le occorrenze di esso) dalla bacheca-------------------- 
		System.out.println("\nTEST SUL METODO REMOVE");
		//stampo i dati che erano in bacheca prima della rimozione
		bacheca.printelements("cat1");
		bacheca.printelements("cat2");
		try {
			System.out.println("invoco una remove inserendo una password sbagliata, mi aspetto una IncorrectPasswordException");
			bacheca.remove("wrongpsw", posttmp);
		}catch(IncorrectPasswordException e){
			System.out.println(e);
		}
		try {
			System.out.println("invoco una remove passando come param. un dato nullo, mi aspetto una NullPointerException");
			bacheca.remove("java",null);
		}catch(IncorrectPasswordException e){
			System.out.println(e);
		}catch(NullPointerException e) {
			System.out.println(e);
		}
		try {
			System.out.println("invoco una remove passando come param. un dato non presente in bacheca, mi aspetto una"
					+ " restituzione di null");
			System.out.println(bacheca.remove("java",np)); //np è una immagine dichiarata nel controllo precedente
		}catch(IncorrectPasswordException e){
			System.out.println(e);
		}catch(NullPointerException e) {
			System.out.println(e);
		}
		//rimuovo il dato temporaneo temp (presente in cat1)
		try {
			bacheca.remove("java", posttmp);
			System.out.println("ho rimosso "+posttmp);
			bacheca.printelements("cat1");
			//Osservo la rimozione di temp da cat1
		}catch(IncorrectPasswordException e) {
			System.out.println(e);
		}
		
		//--------------------Ottenimento di una lista di dati presenti in una categoria--------------------
		System.out.println("\nTEST SUL METODO GETDATACATEGORY");
		List<Data> templist;
		try {
			System.out.println("invoco la getdatacategory con password errata, mi aspetto una IncorrectPasswordException");
			templist=bacheca.getDataCategory("wrongpsw", "cat1");
		}catch(IncorrectPasswordException e) {
			System.out.println(e);
		}
		try {
			System.out.println("invoco la getdatacategory con categoria nulla, mi aspetto una NullPointerException");
			templist=bacheca.getDataCategory("java",null);
		}catch(IncorrectPasswordException e) {
			System.out.println(e);
		}catch(NullPointerException e) {
			System.out.println(e);
		}
		try {
			System.out.println("invoco la getdatacategory con categoria non presente nella bacheca, mi aspetto una NotFoundException");
			templist=bacheca.getDataCategory("java","catnonpresente");
		}catch(IncorrectPasswordException e) {
			System.out.println(e);
		}catch(NotFoundException e) {
			System.out.println(e);
		}
		try {
			System.out.println("Stampa della lista dei dati di cat1");
			templist=bacheca.getDataCategory("java","cat1");
			System.out.println(templist);
			//mi aspetto di avere una lista con gli elementi di cat1
		}catch(IncorrectPasswordException e) {
			System.out.println(e);
		}
		
		//--------------------Inserimento di un like ad un dato presente in bacheca--------------------
		System.out.println("\nTEST SUL METODO INSERTLIKE");
		try {
			System.out.println("invoco la insert like passando una categoria nulla come parametro, mi aspetto"
					+ " una NullPointerException");
			bacheca.insertLike(null, tramonto);
		}catch(NullPointerException e) {
			System.out.println(e);
		}
		try {
			System.out.println("invoco la insert like passando come parametro un dato null, mi aspetto una "
					+ "NullPointerException");
			bacheca.insertLike("Marco",null);
		}catch(NullPointerException e) {
			System.out.println(e);
		}
		try {
			System.out.println("invoco la insert like passando come un amico che non ha accesso a nessuna categoria"
					+ " in cui si trova il dato, mi aspetto una UnauthorizedFriend");
			bacheca.insertLike("Topolino",tramonto);
		}catch(UnauthorizedFriend e) {
			System.out.println(e);
		}
		bacheca.insertLike("Marco",tramonto);
		bacheca.insertLike("Anna", vocale);
		bacheca.insertLike("Marco", vocale);
		bacheca.insertLike("Anna", tramonto);
		//Da notare che Marco mette like al dato tramonto. Marco ha accesso ad almeno una categoria in cui si trova tramonto cioè cat1.
		System.out.println(tramonto.name+" ha "+tramonto.getnlike()+" likes");
		System.out.println(vocale.name+" ha "+vocale.getnlike()+" likes\nMarco richiama la insertlike su sunset");

		bacheca.insertLike("Marco",tramonto); //un doppio like corrisponde alla rimozione di esso
		System.out.println(tramonto.name+" ha "+tramonto.getnlike()+" likes");
		
		//--------------------restituisce un iteratore che genera tutti i dati in bacheca ordinati in base al numero di like--------------------
		System.out.println("\nTEST SUL METODO GETITERATOR");
		Iterator<Data>it = null; //inizializzo con null
		try {
			System.out.println("invoco getiterator inserendo una password sbagliata, mi aspetto una IncorrectPasswordException");
			it=bacheca.getIterator("wrongpsw");
		}catch(IncorrectPasswordException e) {
			System.out.println(e);
		}
		try {
			it=bacheca.getIterator("java");
			System.out.println("chiamo il metodo remove dell'iteratore, mi aspetto una UnsupportedOperationException");
			it.remove();
		}catch(IncorrectPasswordException e) {
			System.out.println(e);
		}catch(UnsupportedOperationException e) {
			System.out.println(e);
		}
		System.out.println("Stampa dei dati in ordine di nr di like ricevuti");
		while(it.hasNext()) {
			Data var=it.next();
			System.out.println(var.name+"->"+var.getnlike());
		}
		
		//--------------------restituisce un iteratore che genera tutti i dati in bacheca visibili ad un amico passato da parametro--------------------
		System.out.println("\nTEST SUL METODO GETFRIENDITERATOR");
		try {
			System.out.println("invoco il metodo passando come parametro amico una stringa nulla, mi aspetto una NullPointerException");
			it=bacheca.getFriendIterator(null);
		}catch(NullPointerException e) {
			System.out.println(e);
		}
		try {
			System.out.println("chiamo il metodo remove dell'iteratore, mi aspetto una UnsupportedOperationException");
			it=bacheca.getFriendIterator("Anna");
			it.remove();
		}catch(UnsupportedOperationException e) {
			System.out.println(e);
		}
		System.out.println("Stampa dei dati della databoard visibili ad anna");
		while(it.hasNext()) {
			Data var=it.next();
			System.out.print(var.name+"\t");
		}
	}
}
