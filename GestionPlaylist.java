package urjc.ist.playlist;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class GestionPlaylist {
	
	private int numPlayList;
	private int duracionTotal;
	private List<PlayList> list;
	
	
	public GestionPlaylist() {
		numPlayList = 0;
		duracionTotal = 0;
		list = new ArrayList<PlayList>();
	}
	
	
	
	public boolean addPlayList(Scanner entrada){
		System.out.print("Ingrese nombre de Playlist: ");
		String name = entrada.next();
		
		if (name == ""){
			System.out.println("Debe ingresar un nombre válido. VOLVEMOS.");
			return false;
			
		}else{
			PlayList playlist = new PlayList (name);
			list.add(playlist);
			numPlayList++; //contador
			return true;
		}
	}
	
	
	
	public boolean deletePlayList(Scanner entrada){
		String[] names = new String[20]; 
		
		System.out.print("Playlists existentes: ");
		names = arrayPlayList();
		System.out.println(names);
		System.out.print("Ingrese nombre a borrar: ");
		String name = entrada.next();
			
		int x = hasPlayList(name);
		if(x != -1){ //= encontrado
			list.remove(x); //borramos playlist
			return true;
		}else{ // o no encontrado o ""
			System.out.println("Introduzca un nombre válido o coincidente. VOLVEMOS.");
			return false;
		}
	}
	
	
	//Cambia el nombre a la playlist
	public boolean modifPlayList (Scanner entrada){
		String[] names = new String[20]; 

		System.out.println("Playlist a modificar: ");
		names = arrayPlayList();
		System.out.println(names);
		System.out.print("Escriba cual quiere modificar: ");
		String v = entrada.next();
			
		int x = hasPlayList(v); //comprobamos que lo introducido esté	
		if (x != -1){ // = encontrado
			System.out.print("Nombre nuevo: ");
			v = entrada.next();
			list.get(x).setNombre(v); //cambiamos el nombre
			return true;
		}else{ //o no coincidente o ""
			System.out.println("Introduzca un nombre válido o coincidente. VOLVEMOS.");
			return false;
		}
	}
			
	
//	//comprobacion de nombre
//	public boolean hasPlayList (String[] names, String name){
//		int x = 0;
//		
//		while (x < names.length){
//			if (names[x] == name){
//				return true;
//			}else{
//				x++;
//			}
//		}
//		return false;
//	}
	
		
	//DEVUELVE UN ARRAY CON LOS NOMBRES DE PLAYLIST
	public String[] arrayPlayList (){
		String[] arry = new String[20];
		Iterator<PlayList> x;
		x = list.iterator();
			
		while (x.hasNext()){
			int z = 0;
			arry[z] = list.get(z).getNombre(); 
			z++;
			x.next();
		}
		//if arry= null, ponemos algo??
		return arry;
	}

	
	
	//EXISTE NUESTRA PLAYLIST? --> Si existe, nos devuelve su index.
	public int hasPlayList(String s){
		Iterator<PlayList> x;
		x = list.iterator();
			
		while (x.hasNext()){
			int z = 0;
			if (s == list.get(z).getNombre()){ //list.get(posicion) + getNombre de la clase PlayList
				return z;
			}else{
				z++;
				x.next();
			}
		}
		return -1;
	}

	
	
	
///////MODIF ALBUM Y MODIF CANCION
//		}else if (clase == "Album"){
//			//se puede modif titulo autor o grupo...
//			v= entrada.next();
//			
//			
//			
//			return true;
//			
//			
//			
//			
//		}else if (clase == "Cancion"){
//			//se puede modificar titulo y autor
//			v= entrada.next();
//			
//			
//			return true;
//			
//			
//			
//			
//		}
		//return false;
	
	
	

//-----------------------------------------------------------------------------------------------------
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
			System.out.println("	Salir    (4)");
			
			s = entrada.next();
			if (s == "1"){
				interfazPlay(entrada);
				
				
			//ALBUM	
			}else if(s == "2"){
				interfazAlbum(entrada);
				
				
			//CANCION
			}else if (s == "3"){
				interfazCancion(entrada);
				
			//EXIT   //llamamos a print
			}else if (s == "4"){
				exit = true;
				
		
			}else{
				System.out.println("Ingrese un número, o números del 1 al 4 para continuar...\n");
			}
		}
		entrada.close(); //cerramos sumidero
	}
	
	
	
////////////////////////////////////////////////////////////////////////////////////////////////////
	public void interfazPlay(Scanner entrada){
		//Scanner entrada = new Scanner (System.in);
		boolean exit = false;
		
		while (exit != true){
			System.out.println("¿Que desea hacer?\n");
			System.out.println("	Nueva Playlist     (1)");
			System.out.println("	Modificar Playlist (2)\n");
			System.out.println("	Borrar Playlist    (3)\n");
			System.out.println("	Salir              (4)");
			
			String t = entrada.next();
		
			//NUEVA PLAYLIST
			if (t == "1"){
				addPlayList(entrada); //da igual si success es true o false, volvemos a la interfaz
				
				
			//MODIF PLAYLIST
			}else if (t == "2"){
				if (numPlayList > 0){
					modifPlayList(entrada);
				}else{
					System.out.println("No hay Playlists existentes. Por favor, cree alguna antes de modificar");
				}
				
				
			//BORRAMOS PLAYLIST
			}else if (t =="3"){
				if (numPlayList >0){
					if(deletePlayList(entrada))
						numPlayList--;
				}else{
					System.out.println("No hay Playlists existentes. Por favor, cree alguna antes de borrar");
				}
				
				
			//EXIT
			}else if (t == "4"){
				System.out.println("Volvemos...\n");
				exit = true;
				
				
			}else{
				System.out.println("Ingrese 1..3 la próxima vez...\n"); //si el num es incorrecto, volvemos a interfaz
			}
		}
	}
	
	
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void interfazAlbum(Scanner entrada){
		String[] names = new String[20]; 
		boolean success;
		
		//names = arrayPlayList();
		
		if (names[0] == ""){
				System.out.println("No hay playlist sobre la que crear album. Cree primero una Playlist");
		}
		
		
		System.out.print("¿En qué Playlist quiere insertar su álbum? \n");
		//array de playlists.println
		String s = entrada.next();
		
		//correcto o no?
			
			System.out.println("¿Que desea hacer sobre Album?\n");
			System.out.println("	Nuevo Album     (1)");
			System.out.println("	Modificar Album (2)");
			System.out.println("	Salir           (3)");
			
			String t = entrada.next();
			if (t == "1"){
				//addAlbum();
				
			}else if (t == "2"){
				
			}else{
				
			}
			
	}
	
	
	
	
	
	
	
	public void interfazCancion(Scanner entrada){
		//hay que comprobar si tenemos album primero, antes de dejar añadir canciones!!
		
		System.out.println("¿Que desea hacer sobre su Canción?\n");
		System.out.println("	Nuevo Canción     (1)");
		System.out.println("	Modificar Canción (2)");
		System.out.println("	Salir             (3)");
	}
	
	
	
	public String toString (){
		return "";
	}
	

	
////////////////////////////////////////////////////////////////////	
	public static void main(String[] args) {
		GestionPlaylist gest = new GestionPlaylist();
		gest.interfaz();
		
	}

}
