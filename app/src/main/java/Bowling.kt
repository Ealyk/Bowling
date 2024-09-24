import java.util.LinkedList
import kotlin.random.Random

class Bowling {
    val gameMap: MutableMap<Player, LinkedList<Frame>> = mutableMapOf()
    fun addPlayer(player: Player) {
        gameMap[player] = LinkedList()
    }

    fun round () {
        for (player in gameMap.keys){
            gameMap[player]?.add(player.throwBowl())
            println("${player.name}: ${gameMap[player]?.size} round - ${gameMap[player]}; final score - ${countScore(gameMap[player]!!)}")
        }
    }
    fun countScore(listFrame: LinkedList<Frame>) {
        for (frame in listFrame) {
            if (frame.isStrike){
                TODO()
            }
            if (frame.isSpare){
                TODO()
            }
        }

    }

}
data class Frame(val firstThrow: Int) {
    val secondThrow = Random.nextInt(firstThrow, 11)
    val sum: Int
        get() = firstThrow + secondThrow
    val isStrike: Boolean = firstThrow == 10
    val isSpare: Boolean = sum == 10


    override fun toString(): String {
        return "$firstThrow $secondThrow"
    }

}
data class Player(val name: String) {
    fun throwBowl(): Frame = Frame(
        firstThrow = Random.nextInt(0,11)
    )
}


fun main() {
    val game = Bowling()
    game.addPlayer(Player("kdsap"))
    game.addPlayer(Player("sofssd"))
    game.addPlayer(Player("dossjw"))
    game.round()

}