import java.util.Scanner;

public class Persona
{
   private byte idade;
   private String nome;
   private int altura;

   public Persona( byte idade, String nome, int altura )
   {
      this.idade = idade;
      this.nome = nome;
      this.altura = altura;
   }

   public Persona()
   {
      Scanner teclado = new Scanner( System.in );
      System.out
         . print( "\n\n Nome: " );
      this.nome = teclado.nextLine();
      /*
       * Pide la edad y fuerza a que sea byte
       */
      do
      {
         System.out
            . print( "\n Idade: " );
         try
         {
            // Captura String del teclado y lo intenta
            // transformar a un objeto Byte
            Byte bt = 
               Byte
               .parseByte( teclado
                     .nextLine() );
            
            // Transforma el contenido del objeto Byte
            // al tipo primitivo byte y lo asigna a edad            
            this.idade =
              bt 
               .byteValue();
            
            // Sale del bucle
            break;
         }
         catch( Exception e )
         {
            System.out
               .println( "Solo admite valores en el rango {-128, 127}" );
         }
      } while ( true );
      
      /*
       * Pide la altura y fuerza a que sea int
       */
      do
      {
         System.out
            .print( "\n Altura: " );
         try
         {
            // Captura String del teclado y lo intenta
            // transformar a un objeto Byte
            Integer in = 
               Integer
               .parseInt( teclado
                     .nextLine() );
            
            // Transforma el contenido del objeto Byte
            // al tipo primitivo byte y lo asigna a edad            
            this.altura =
              in 
               .intValue();
            
            // Sale del bucle
            break;
         }
         catch( Exception e )
         {
            System.out
               .println( "Fuera de rango." );
         }
      } while ( true );
   }

   public byte getIdade()
   {
      return this.idade;
   }

   public String getNome()
   {
      return this.nome;
   }

   public int getAltura()
   {
      return this.altura;
   }
}
