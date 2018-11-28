package mastermind

import kotlin.math.min

data class Evaluation(val rightPosition: Int, val wrongPosition: Int)

fun evaluateGuess(secret: String, guess: String): Evaluation {
    return checkLetterAndPositionMatches(secret, guess)
}

fun checkLetterAndPositionMatches(secret: String, guess: String): Evaluation {
    val numberOfLettersThatAreInBoth = checkLetterMatches(secret, guess)
    val numberOfLettersWithPositionMatching = checkLetterPositionMatches(secret, guess)
    return Evaluation(numberOfLettersWithPositionMatching, (numberOfLettersThatAreInBoth - numberOfLettersWithPositionMatching))
}

fun checkLetterPositionMatches(secret: String, guess: String): Int {
    return guess.foldIndexed(0) { index, acc, char -> if (secret[index] == char) acc + 1 else acc }
}

fun checkLetterMatches(secret: String, guess: String): Int {
    return (secret + guess).toCharArray().toSet().joinToString("").sumBy { char -> min(secret.count { it == char }, guess.count { it == char }) }
}


