//package randIndex;

import java.io.*;
import java.util.*;

public class RandIndex
{
   private static String indices = "fich_indexes.dat";
   private static String objetos = "fich_objects.dat";
   private static RandomAccessFile index = null; // Acceso al fichero de indices
   private static RandomAccessFile objFile = null; // Acceso al fichero de objetos
   private static final byte indexRegSize = 8;

 
   private static long getRealPosition( long pos )
   {
      return pos * indexRegSize;
   }
 
 

   /*
    * Ordenar el fichero de indice según orden alfabético
    * del nombre.
    */
   private static void ordenarIndices()
   {

   }
   
   
   private static Persona leerPersona( long pos )
   {  

      byte idade = -1;
      String nome = "";
      int altura = -1;
      long puntero; //, lonNome;
      
      try
      {
         /*
          * Abre indice para lectura
          */
         index = 
            new RandomAccessFile ( indices, "r" );
         /*
          *  Se situa en la posicion del indice
          */
         index
            .seek( getRealPosition( pos ) );
      //System.out.println( "SEEK to " + pos );

      /*
       *  Recupera el puntero del índice
       */
      puntero = 
         index.readLong();
      //System.out.println( "Puntero en: " + puntero);
      
      /*
       * Cierra el archivo de indice
       */
      index
         .close();
      
      /*
       * Abre el archivo de objetos para lectura
       */
      objFile =
         new RandomAccessFile( objetos, "r" );
      /*
       * Posiciona el puntero en el inicio de la entrada
       */
      objFile
         .seek( puntero );
      //System.out.println( "se coloca puntero" );
      /*
       *  Recupera Idade como byte
       */
      idade = 
         objFile
         .readByte();
      // System.out.println( "se lee edad" );

      /*
       *  Recupera nome como UTF
       */
      nome =
         objFile
         .readUTF();
      //System.out.println( "se lee nome" );
      /*
       *  Recupera a altura como entero
       */
      altura =
         objFile
         .readInt();
      //System.out.println( "se lee altura" );
      // Cierra el archivo de objetos
      objFile.
        close();
     }
     catch (IOException e)
     {
        System.out
           .println( "Error: " + e.getMessage() );
     }
     catch (Exception e)
     {
        System.out
           .println( "Error: " + e.getMessage() );
     }
      
      /* 
       * Inicializa un nuevo objeto de la clase Persona
       * con los datos recuperados
       */
      Persona newPerson = new Persona( idade, nome, altura );
      /*
       *  devuelve el objeto
       */
      return newPerson; 
  }
  
  /**
   * Grava los datos de la persona al archivo
   * y añade entrada al índice
   *
   * @param Persona() objeto del que se obtienen los datos.
   */
  private static void grabarSiguiente( Persona persona )
  {
    long puntero;
   // long size; // longitud total de la inserción
   // long fin;
   // String nome = persona.getNome();
   // long nomeLong = nome.length();
   
   try
   { 
      objFile =
         new RandomAccessFile( objetos, "rw" );
    
      /*
       * final del archivo antes de escribir
       */
      puntero =
         objFile.length(); 
      //System.out
      //  .println( "tamaño del fichero de objetos: " + pointer );
      objFile
        .seek( puntero );
    
      /*
       *  Escribe los datos del objeto al fichero
       */
      objFile
          .writeByte( persona.getIdade() );
      // objFile
      //    .writeLong( nomeLong );
      objFile
         .writeUTF( persona.getNome() );
      objFile
         .writeInt( persona.getAltura() );
    
      /*
       * Final del archivo despues de escribir
       */
      // fin = objFile.length(); 
      // Diferencia de tamaño del archivo
      // size = fin - ini;
      objFile
         .close();

      /*
       * Abre el archivo de indices para escritura
       */
      index =
         new RandomAccessFile( indices, "rw" );
      /*
       * Pone el puntero al final del archivo
       */
      //System.out
      //   .println( "index en posicion " +index.length() );
      index
         .seek(index
            .length()
             );
      /*
       * Escribe la posición del registro de 
       * los datos del objeto
       */
      index
         .writeLong( puntero );
      /*
       * Cierra el archivo
       */
      index
         .close();
   }
   catch (Exception e)
   {
      System.out
         .println( "Error: " + e.getMessage() );
   }
  }
  



  private static void mostrarPersona( Persona persona )
  {
      System.out
         .printf( "\nEsta persona, de nombre %s y %d años de edad, mide %d cm de altura", 
               persona.getNome(),
               persona.getIdade(), 
               persona.getAltura()
               );
  }



   private static long pedirPosicion()
   {
      Scanner teclado = new Scanner( System.in );
      Long l;
      long lon = -1;
      System.out
        .print( " Introducir posicion: " );
      try
      {
         l = Long.parseLong( teclado.nextLine() );
         lon = l.longValue();
      }
      catch ( Exception e )
      {
         System.out
            .println( "Posicion no válida." );
      }
      return lon;   
   }




   /**
    * Muestra el menú principal, recoge una
    * opcion válida y devuelve un byte
    */
  private static byte mainMenu()
  {
      Byte op = -1;
      Scanner teclado = new Scanner( System.in );
      do
      {
         System.out
            .print("\n\n 1. Mostrar persona, \n 2. Añadir otra persona. \n 0. Salir. " );
         System.out
            .print( "\n Escoge una opcion: ");
         try
         {
            op = Byte.parseByte( teclado.nextLine() );
            break;
         }
         catch ( Exception e )
         {
            System.out
               .println( "Opcion no valida." );
         }
      }  
      while ( true );
      byte bt = op.byteValue();
      return bt;
  }
  
  
  
  public static void main( String[] args )
  {
     /*
      Persona paquito =
         new Persona( (byte)32, "Francisco Alegre y Olé", 169 );

      grabarSiguiente( paquito );

      Persona persona = leerPersona( 0 );

      mostrarPersona(persona);

      
      
      paquito =
         new Persona( (byte)40, "Dositea Fernández", 179 );

      grabarSiguiente( paquito );

      persona = leerPersona( 1 );

      mostrarPersona( persona );

      
      
      paquito =
         new Persona( (byte)56, "Antonio Recio", 175 );

      grabarSiguiente( paquito );

      persona = leerPersona( 2 );
      
      mostrarPersona( persona );
      //*/
      byte op;
      long p;
     do
     {
         op = mainMenu();
         
         switch( op )
         {
            case 1:
               if( ( p = pedirPosicion() ) >= 0 )
               mostrarPersona(
                     leerPersona( p ) 
                     );
               break;
            case 2:
               grabarSiguiente(
                     new Persona()
                     );
               break;
           // case 3:
           //    break;
           // case 4:
           //    break;
            case 0:
               break;
            default:
               System.out
                  .println( "Opción incorrecta" );
               break;
         }
     }
     while ( op != 0 );
  }
}
