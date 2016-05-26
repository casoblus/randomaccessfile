
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
