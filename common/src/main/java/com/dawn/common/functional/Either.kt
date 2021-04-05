package com.dawn.common.functional



//Please note that the either class itself doesn't hold any values. Its child classes does.
sealed class Either<out L, out R> {
    // by FP conventions left would be the failure of this com.nytimes.newsapp.common.functional.Either
    data class Left<out L>(val a: L) : Either<L, Nothing>()

    // by FP conventions right would be the Success of this com.nytimes.newsapp.common.functional.Either
    data class Right<out R>(val b: R) : Either<Nothing, R>()

    fun <L> left(a: L) = Left(a)
    fun <R> right(b: R) = Right(b)

    fun either(fnL: (L) -> Any, fnR: (R) -> Unit): Any =
        when (this) {
            is Left -> fnL(a)
            is Right -> fnR(b)
        }

}

infix fun <L, R, R2> Either<L, R>.map(f: (R) -> R2): Either<L, R2> = when (this) {
    is Either.Left -> this
    is Either.Right -> Either.Right(f(this.b))
}

infix fun <L, R, R2> Either<L, R>.flatMap(f: (R) -> Either<L, R2>): Either<L, R2> = when (this) {
    is Either.Left -> this
    is Either.Right -> f(b)

}

