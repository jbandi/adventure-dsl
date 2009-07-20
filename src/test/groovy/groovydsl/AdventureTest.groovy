package groovydsl

import groovy.util.GroovyTestCase

class AdventureTest extends GroovyTestCase
{
  void test_explore_commands() {

    def commands = [
            "look",
            "go north ",
            "take dagger",
            "grab sword",
            "grab sword",
            "hold key",
            "yoink axe"
    ]

    def g = new Game()

    def binding = new Binding(game : g)
    GroovyShell shell = new GroovyShell(binding)

    commands.each {
      shell.evaluate("game.with{ $it }");
    }
  }
}
