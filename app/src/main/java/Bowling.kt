
import kotlin.random.Random

class Bowling {
    val gameMap: MutableMap<Player, ArrayList<Frame>> = mutableMapOf()
    fun addPlayer(player: Player) {
        gameMap[player] = arrayListOf()
    }
    val bowlRoad = BowlRoad()
    val scoreCount = ScoreCount()
    fun round () {
        for (player in gameMap.keys){
            gameMap[player]?.add(player.throwBowl { bowlRoad.getSkittlesCount() })
            println("${player.name}: ${gameMap[player]?.size} round - ${gameMap[player]}; final score - ${scoreCount.getCountScore(gameMap[player]!!)}")
        }
    }



}
class ScoreCount {
    private fun countScore(listFrame: ArrayList<Frame>): Int {
        var score = 0

        for (frame in listFrame) {
            if (listFrame.indexOf(frame) > 0 && listFrame[listFrame.indexOf(frame) - 1].isSpare) {

                score += frame.firstThrow
            }
            if (listFrame.indexOf(frame) > 1 && listFrame[listFrame.indexOf(frame) - 2].isStrike) {
                score += frame.firstThrow + listFrame[listFrame.indexOf(frame) - 1].firstThrow
            }
            score += frame.sum
        }
        return score
    }
    fun getCountScore(listFrame: ArrayList<Frame>): Int {
        return countScore(listFrame)
    }
}
class BowlRoad  {
    private fun skittlesCount (): Frame {

        val firstThrow = Random.nextInt(0,11)
        val secondThrow = Random.nextInt(0,11 - firstThrow)
        return Frame(firstThrow, secondThrow)

    }
    fun getSkittlesCount(): Frame {
        return skittlesCount()
    }
}
data class Frame(val firstThrow: Int, val secondThrow: Int) {
    val sum: Int
        get() = firstThrow + secondThrow
    val isStrike: Boolean = firstThrow == 10
    val isSpare: Boolean = sum == 10 && firstThrow != 10


    override fun toString(): String {
        return "$firstThrow $secondThrow"
    }

}
data class Player(val name: String) {

    fun throwBowl(
        skittlesCount: () -> Frame
    ):Frame {
        println("${this.name} бросил шар")
        return skittlesCount()
    }
}


fun main() {
    val game = Bowling()
    game.addPlayer(Player("kdsap"))
    game.addPlayer(Player("sofssd"))
    game.addPlayer(Player("dossjw"))
    game.round()
    game.round()
    game.round()
    game.round()
    game.round()
    game.round()


}