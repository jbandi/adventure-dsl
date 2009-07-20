package groovydsl

public class Game {

  static {
    Game.metaClass.allowChangesAfterInit = true
  }

  def Game() {

  }

  void go(dir) {
    println "You go $dir"
  }

  void look() {
    println "You look around and see nothing"
  }

  void take(it) {
    println "You take the $it"
  }

  def propertyMissing(String name) {

    // this gets rid of the paranteses for parameterless methods
    if (metaClass.respondsTo(this, name)) {
      this."$name"()
    }

    // this allows to pass string-arguments without quotes
    name
  }

  def methodMissing(String name, args) {
    println "Method missing: $name"
    
    // allows synonyms for 'take'
    if (["grab", "hold", "yoink"].contains(name)) {
      // This would just delegate it each time
      // this.take(args[0])

      // Create a method on the fly (this only works if allowChangesAfterInit = true -> see static init block above)
      Game.metaClass."$name" = { item -> this.take(item) };

      // Call the newly created method
      this."$name"(args[0])
    }

  }
}