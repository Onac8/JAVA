package urjc.ist.playlist;

import java.util.Scanner;

import urjc.ist.playlist.Cancion.Codecs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;


public class GestionPlaylist {
	
	private int duracionTotal;
	private List<PlayList> list;
	
	
	public GestionPlaylist() {
		duracionTotal = 0;
		list = new ArrayList<PlayList>();
	}
	
	
	
	//AÑADIMOS NUEVA PLAYLIST-------------------------------------------------
	private void addPlayList(Scanner entrada){
		System.out.print("Ingrese nombre de Playlist: ");
		String name = entrada.next();
		
		if (name.equals("")){
			System.out.println("Debe ingresar un nombre válido. VOLVEMOS.");
			
		}else{
			PlayList playlist = new PlayList (name);
			list.add(playlist);
			System.out.println("La playlist '" + name + "' se ha insertado en la lista.\n");
			System.out.println("Volvemos al gestor...\n");
		}
	}
	
	//AÑADE ALBUM A LA PLAYLIST DADA----------------------------------------------------------
	private void addAlbum (Scanner entrada, int x){ //x es el index de la playlist dada
		System.out.print("Ingrese titulo, autor y grupo del nuevo Album: ");
		String name1 = entrada.next();
		String name2 = entrada.next();
		String name3 = entrada.next();
			
		if (name1.equals("") || name2.equals("") || name3.equals("")){
			System.out.println("Debe ingresar un titulo, autor y grupo válidos. VOLVEMOS.");
				
		}else{
			Album album1 = new Album (name1,name2,name3);
			list.get(x).addAlbum(album1); //añadimos album en playlist dada, dentro de list
		}
	}
	
	
	
	
	//BORRAMOS PLAYLIST-------------------------------------------------------
	private void deletePlayList(Scanner entrada){
		String[] names = new String[list.size()];
		String name;
		int x;
		
		
		System.out.print("Playlists existentes: ");
		names = arrayPlayList();
		System.out.println(Arrays.toString(names)); //imprimos!!!
		System.out.print("Ingrese nombre a borrar: ");
		name = entrada.next();
			
		x = hasPlayList(name);
		if(x != -1){ //= encontrado
			list.remove(x); //borramos playlist
		}else{ // o no encontrado o ""
			System.out.println("Introduzca un nombre válido o coincidente. VOLVEMOS.");
		}
	}
	
	//BORRAMOS ALBUM-------------------------------------------------------
	private void deleteAlbum(Scanner entrada, int index){
		String names;
		String alb;
		int x;
			
		System.out.print("Lista de albumes:: ");
		names = namesAlbum(index);
		System.out.println(names); 
		System.out.print("Ingrese el album a borrar (titulo): ");
		alb = entrada.next();
				
		x = hasAlbum(alb,index);
		if(x != -1){ //= encontrado
			list.get(index).removeAlbum(x); //borramos album dado x
				
		}else{ // o no encontrado o ""
			System.out.println("Introduzca un nombre válido o coincidente. VOLVEMOS.");
		}
	}
	
	
	
	
	//MODIFICAMOS PLAYLIST (cambio de nombre)--------------------------------------------
	private void modifPlayList (Scanner entrada){
		String[] names = new String[list.size()];
		String v;
		int x;
		
		System.out.println("Playlist a modificar:");
		names = arrayPlayList();
		System.out.println(Arrays.toString(names));
		System.out.print("Escriba cual quiere modificar: ");
		v = entrada.next();
			
		x = hasPlayList(v); //comprobamos que lo introducido esté	
		if (x != -1){ // = encontrado
			System.out.print("Nombre nuevo: ");
			v = entrada.next();
			list.get(x).setNombre(v); //cambiamos el nombre
			System.out.println("Nombre modificado correctamente. Volvemos al gestor...");
			
		}else{ //o no coincidente o ""
			System.out.println("Introduzca un nombre válido o coincidente. VOLVEMOS.");
		}
	}
	
	//MODIFICAMOS ALBUM (cambio de titulo, grupo y/autor)--------------------------------
	private void modifAlbum (Scanner entrada, int index){
		String names;
		String v;
		String s;
		boolean exit=false;
		int x;
		
		System.out.println("Lista de albumes:");
		names = namesAlbum(index); //necesita index para buscar albumes
		System.out.println(names);
		System.out.print("Escriba cual quiere modificar (titulo del album): ");
		v = entrada.next();
				
		x = hasAlbum(v, index); //comprobamos que lo introducido esté	
		if (x != -1){ // = encontrado
			while (!exit){
				System.out.println("	Modificar titulo (1)");
				System.out.println("	Modificar autor  (2)");
				System.out.println("	Modificar grupo  (3)");
				System.out.println("	Salir            (4)\n");
				s = entrada.next();
					
				if (s.equals("1")){
					System.out.print("Nuevo titulo: ");
					s = entrada.next();
					list.get(index).getAlbum(x).setTitulo(s); //cambiamos titulo
						
				}else if (s.equals("2")){
					System.out.print("Nuevo autor: ");
					s = entrada.next();
					list.get(index).getAlbum(x).setAutor(s); //cambiamos autor
						
				}else if (s.equals("3")){
					System.out.print("Nuevo grupo: ");
					s = entrada.next();
					list.get(index).getAlbum(x).setGrupo(s); //cambiamos grupo
						
				}else if (s.equals("4")){
					exit = true;
						
				}else{
					System.out.print("Introduzca numeros del 1..4. Volvemos...");
				}
			}
				
		}else{ //o no coincidente o ""
			System.out.println("Introduzca un nombre válido o coincidente. VOLVEMOS.");
		}
	}
	
	
			
		
	//DEVUELVE UN ARRAY CON LOS NOMBRES DE PLAYLIST--------------------------------------
	private String[] arrayPlayList (){
		String[] arry = new String[list.size()];
		ListIterator<PlayList> it = list.listIterator();
			
		int z = 0;
		while (it.hasNext()){
			arry[z] = it.next().getNombre();
			z++;
		}
		//if arry= null, ponemos algo??
		return arry;
	}
	
	//DEVUELVE UN STRING CON LOS NOMBRES DE LOS ALBUMES, AUTOR Y GRUPO----------------
	private String namesAlbum (int index){	
		ListIterator<Album> it = list.get(index).getAlbumList().listIterator();
		String titulos = "";
			
		while (it.hasNext()){
			titulos += "Titulo: " + it.next().getTitulo() + "  ;   ";
			titulos += "Autor: " + it.previous().getAutor() + "  ;   Grupo: " + it.next().getGrupo();
		}
		return titulos;
	}
		
		

	
	//EXISTE NUESTRA PLAYLIST? --> Si existe, nos devuelve su index-----------------------
	private int hasPlayList(String s){
		Iterator<PlayList> x;
		x = list.iterator();
		int z;
			
		z = 0;
		while (x.hasNext()){
			if (s.equals(list.get(z).getNombre())){ //list.get(posicion) + getNombre de la clase PlayList
				return z;
			}else{
				z++;
				x.next();
			}
		}
		return -1;
	}
	
	//EXISTE NUESTRO ALBUM? --> Si existe, devuelve su index----------------------
	private int hasAlbum(String s, int index){
		ListIterator<Album> it = list.get(index).getAlbumList().listIterator();
			
		
		int z = 0; //contador para el index del albumlist
		while (it.hasNext()){
			if (s.equals(it.next().getTitulo())){ //list.get(posicion) + getNombre de la clase PlayList
				return z;
			}
			z++;
		}
		return -1; //retorna -1 si no encuentra el titulo
	}
	
	
	
	
	
//-----------------------------------------------------------------------------------------------------------------------
//INTERFAZ PRINCIPAL-----------------------------------------------------------------------------------------------------
	public void interfaz(){
		Scanner entrada = new Scanner (System.in);
		boolean exit = false;
		String s;
		
		while (exit != true){
			System.out.println("-------------------");
			System.out.println("GESTOR DE PLAYLISTS");
			System.out.println("-------------------\n");
			System.out.println("¿Qué quiere añadir, borrar o modificar?\n");
			System.out.println("	Playlist (1)");
			System.out.println("	Album    (2)");
			System.out.println("	Cancion  (3)");
			System.out.println("	Salir    (4)\n");
			
			s = entrada.next();
			if (s.equals("1")){
				interfazPlay(entrada);
				
				
			//ALBUM	
			}else if(s.equals("2")){
				if (list.size() == 0){
					System.out.println("No hay playlist. Cree primero una Playlist");
				}else{
				interfazAlbum(entrada);
				}
				
				
			//CANCION
			}else if (s.equals("3")){
				if (list.size() == 0){
					System.out.println("No hay playlist. Cree primero una Playlist");
				}else{
					interfazCancion(entrada);
				}
				
				
			//EXIT   //llamamos a print
			}else if (s.equals("4")){
				exit = true;
				
		
			}else{
				System.out.println("Ingrese un número, o números del 1 al 4 para continuar...\n");
			}
		}
		entrada.close(); //cerramos sumidero
	}
	
	
	
//---------------------------------------------------------------------------------------------------------------	
//INTERFAZ PLAYLIST----------------------------------------------------------------------------------------------
	public void interfazPlay(Scanner entrada){
		String[] names = new String[list.size()]; //para imprimir nombres (4)
		String t;
		
		System.out.println("¿Que desea hacer?\n");
		System.out.println("	Nueva Playlist     (1)");
		System.out.println("	Modificar Playlist (2)");
		System.out.println("	Borrar Playlist    (3)");
		System.out.println("	Imprimir           (4)");
		System.out.println("	Salir              (5)\n");
			
		t = entrada.next();
		
		//NUEVA PLAYLIST
		if (t.equals("1")){
			addPlayList(entrada); //da igual si success es true o false, volvemos a la interfaz
				
				
		//MODIF PLAYLIST
		}else if (t.equals("2")){
			if (list.size() > 0){
				modifPlayList(entrada);
			}else{
				System.out.println("No hay Playlists existentes. Por favor, cree alguna antes de modificar");
			}
				
				
		//BORRAMOS PLAYLIST
		}else if (t.equals("3")){
			if (list.size() >0){
				deletePlayList(entrada);
			}else{
				System.out.println("No hay Playlists existentes. Por favor, cree alguna antes de borrar");
			}
				
				
		//IMPRIME NOMBRES PLAYLIST
		}else if (t.equals("4")){
			System.out.print("Playlist existentes: ");
			names = arrayPlayList();
			System.out.println(Arrays.toString(names) + "\n");
			
			
		//EXIT
		}else if (t.equals("5")){
			System.out.println("Volvemos al gestor...\n");
			//exit = true;
				
				
		}else{
			System.out.println("Ingrese 1..3 la próxima vez...\n"); //si el num es incorrecto, volvemos a interfaz
		}
	}
	
	
//-----------------------------------------------------------------------------------------------------------------
//INTERFAZ ALBUM---------------------------------------------------------------------------------------------------
	public void interfazAlbum(Scanner entrada){
		String[] names = new String[20]; 
		String s;
		int x;
		
		System.out.println("Playlist existentes: ");
		names = arrayPlayList();
		System.out.println(Arrays.toString(names));
		System.out.print("Escriba sobre cual quiere operar: ");
		s = entrada.next();
		
		x = hasPlayList(s); //comprobamos que lo introducido esté. nos devuelve el index
		if (x != -1){ // = encontrada playlist sobre la que operar
			System.out.println("¿Que desea hacer sobre Album?\n");
			System.out.println("	Nuevo Album      (1)");
			System.out.println("	Modificar Album  (2)");
			System.out.println("	Borrar Album     (3)");
			System.out.println("	Salir            (4)");
			s = entrada.next();
		
			if (s.equals("1")){
				addAlbum(entrada,x); //da igual si success es true o false, volvemos a la interfaz
				
				
			}else if (s.equals("2")){
				if (list.get(x).getNumAlbumes() == 0){
					System.out.println("No hay albumes. Cree uno antes de intentar modificar");
				}else{
					modifAlbum(entrada, x); //pasamos el index a modif
				}
				
			}else if (s.equals("3")){
				if (list.get(x).getNumAlbumes() == 0){
					System.out.println("No hay albumes. Cree uno antes de intentar borrar");
				}else{
					deleteAlbum(entrada,x);	 //pasamos el index tambien
				}	
				
			}else if (s.equals("4")){
				System.out.println("Salimos y volvemos al gestor...");
				
			}else{
				System.out.println("Introduzca numeros de 1..4 la próxima vez");
			}
			
		}else{ //o no coincidente o ""
			System.out.println("Introduzca un nombre válido o coincidente de Playlist. VOLVEMOS A INTERFAZ NORMAL.");
		}
	}
	
	
//---------------------------------------------------------------------------------------------------------------	
//INTERFAZ CANCION----------------------------------------------------------------------------------------------	
	public void interfazCancion(Scanner entrada){
		String[] names = new String[20]; 
		String s;
		String v;
		String t;
		String alb;
		int index;
		int indexAlb;
		
		System.out.println("Playlist existentes: ");
		names = arrayPlayList();
		System.out.println(Arrays.toString(names));
		System.out.print("Escriba sobre cual quiere operar: ");
		s = entrada.next();
		
		index = hasPlayList(s); //comprobamos que lo introducido esté. nos devuelve el index
		if (index != -1){ // = encontrada playlist sobre la que operar
			if (list.get(index).getNumAlbumes() == 0){
				System.out.println("No existe ningun Album. Cree uno primero");
				
			}else{
				System.out.println("Lista de albumes:");
				alb = namesAlbum(index); //necesita index para buscar albumes
				System.out.println(names);
				System.out.print("Escriba sobre que album operar (titulo): ");
				v = entrada.next();
				
				indexAlb = hasAlbum(v,index); //necesita index para saber la playlist
				if (indexAlb != -1){
					System.out.println("¿Que desea hacer sobre su Canción?\n");
					System.out.println("	Nueva Canción     (1)");
					System.out.println("	Modificar Canción (2)");
					System.out.println("	Borrar Canción    (3)");
					System.out.println("	Salir             (4)");
					
					t = entrada.next();
					
					if (t.equals("1")){
						addCancion(entrada,index,indexAlb); //da igual si success es true o false, volvemos a la interfaz
						
						
					}else if (t.equals("2")){
						if (list.get(index).getAlbum(indexAlb).getNumCanciones() == 0){
							System.out.println("No hay canciones. Cree una antes de intentar modificar");
						}else{
							//modifCancion(entrada, x); //pasamos los dos index
						}
						
					}else if (t.equals("3")){
						if (list.get(index).getAlbum(indexAlb).getNumCanciones() == 0){
							System.out.println("No hay canciones. Cree una antes de intentar borrar");
						}else{
							//deleteCancion(entrada,x);	 //pasamos los dos index too
						}	
						
					}else if (t.equals("4")){
						System.out.println("Salimos y volvemos al gestor...");
						
					}else{
						System.out.println("Introduzca numeros de 1..4 la próxima vez");
					}
					
					
					
				}else{
					System.out.println("Introduzca un nombre válido o coincidente. VOLVEMOS.");
				}
			}
		
		}else{ //o no coincidente o ""
			System.out.println("Introduzca un nombre válido o coincidente de Playlist. VOLVEMOS A INTERFAZ NORMAL.");
		}
		
	}
	
	
	
	
	//AÑADIR CANCION
	private void addCancion(Scanner entrada, int index, int indexAlb){
		System.out.print("Ingrese titulo, autor, duracion y formato de la nueva Cancion: ");
		Codecs cod = null;
		
		String name1 = entrada.next();
		String name2 = entrada.next();
		String name3 = entrada.next();
		String name4 = entrada.next();
			
		if (name4.equals("MP3")){
			cod =  Codecs.MP3;
		}else if (name4.equals("FLAC")){
			cod = Codecs.FLAC;
		}
		
		if (name1.equals("") || name2.equals("") || name3.equals("0") || name3.equals("") || name4.equals("")){
			System.out.println("Datos mal introducidos");
		//}else if (name3 is not a int --> maaal)
			System.out.println("Datos mal introducidos");
		}else{
			int duracion = Integer.valueOf(name3); //casting de argumento 3
			Cancion cancion1 = new Cancion (name1,name2,duracion,cod);
			list.get(index).getAlbum(indexAlb).addTrack(cancion1); //añadimos album en playlist dada, dentro de list
		}
		
		
	}
	
	
//	//AÑADE ALBUM A LA PLAYLIST DADA----------------------------------------------------------
//		private void addAlbum (Scanner entrada, int x){ //x es el index de la playlist dada
//			System.out.print("Ingrese titulo, autor y grupo del nuevo Album: ");
//			String name1 = entrada.next();
//			String name2 = entrada.next();
//			String name3 = entrada.next();
//				
//			if (name1.equals("") || name2.equals("") || name3.equals("")){
//				System.out.println("Debe ingresar un titulo, autor y grupo válidos. VOLVEMOS.");
//					
//			}else{
//				Album album1 = new Album (name1,name2,name3);
//				list.get(x).addAlbum(album1); //añadimos album en playlist dada, dentro de list
//			}
//		}
	
	
	public String toString (){
		return "";
	}
	

	
////////////////////////////////////////////////////////////////////	
	public static void main(String[] args) {
		GestionPlaylist gest = new GestionPlaylist();
		gest.interfaz();
		//a la que salimos llamamos a toString();
		
	}

}
