//package randIndex;

import java.io.*;

public class RandIndex
{
  private static String indices = "fich_indexes.dat";
  private static String objetos = "fich_objects.dat";
  private static RandomAccessFile index = null; // Acceso al fichero de indices
  private static RandomAccessFile objFile = null; // Acceso al fichero de objetos

 
 
 
 
  private static Persona leerPersona( long pos )
  {
     byte idade = -1;
     String nome = "";
     int altura = -1;

     long puntero, lonNome;

     try
     {
      // Abre indice para lectura
      index = 
         new RandomAccessFile ( indices, "r" );
      // Se situa en la posicion del indice
      index
         .seek( pos * 8 ); // pos * bytes
      // Recupera el puntero del índice
      puntero = 
         index
         .readLong();
      // Cierra el archivo de indice
      index
         .close();
      
      // Abre el archivo de objetos para lectura
      objFile =
         new RandomAccessFile( objetos, "r" );
      // Posiciona el puntero en el inicio de la entrada
      objFile
         .seek( puntero );
      // Recupera Idade como byte
      idade = 
         objFile
         .readByte();
      // Recupera longitud del nombre

      // Recupera nome como UTF
      nome =
         objFile
         .readUTF();
      // Recupera a altura como enteiro
      altura =
         objFile
         .readInt();
      // Cierra el archivo de objetos
     }
     catch (Exception e)
     {
        System.out
           .println( "Error: " + e.getMessage() );
     }
      
      // Inicializa un nuevo objeto de la clase Persona
      Persona newPerson = new Persona( idade, nome, altura );
      // devuelve el objeto
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
    long ini; // última posicion del fichero en bytes
   // long size; // longitud total de la inserción
   // long fin;
   // String nome = persona.getNome();
   // long nomeLong = nome.length();
   
   try
   { 
    objFile = 
       new RandomAccessFile( objetos, "rw" );
    
    // final del archivo antes de escribir
    ini = objFile.length(); 
    
    // Escribe los datos del objeto al fichero
    objFile
       .writeByte( persona.getIdade() );
   // objFile
   //    .writeLong( nomeLong );
    objFile
       .writeUTF( persona.getNome() );
    objFile
       .writeInt( persona.getAltura() );
    
    // Final del archivo despues de escribir
    // fin = objFile.length(); 
    // Diferencia de tamaño del archivo
    // size = fin - ini;
    objFile
       .close();

    index =
       new RandomAccessFile( indices, "rw" );
    index
       .writeLong( ini );
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
         .printf( "\nEsta persona, de nombre " 
               + persona.getNome() 
               + " y "
               + persona.getIdade() 
               + " años de edad, mide "
               + persona.getAltura()
               + " cm de altura. \n" );
  }


  
  
  
  public static void main( String[] args )
  {
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

  }
}
