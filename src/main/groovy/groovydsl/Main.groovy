package groovydsl

public class Main {

  public static void main(String [ ] args) throws IOException {
    System.out.println("Type commands (quit to terminate)!");

    def g = new Game()

    def binding = new Binding(game : g)
    def shell = new GroovyShell(binding)

    def converter = new InputStreamReader(System.in);
    def r = new BufferedReader(converter);

    String command = "";
    while (!(command.equals("quit"))){
      command = r.readLine();
      if (!(command.equals("quit"))){
        //System.out.println("You typed: " + command);

        shell.evaluate("game.with{ $command }");

      }
    }
  }
}