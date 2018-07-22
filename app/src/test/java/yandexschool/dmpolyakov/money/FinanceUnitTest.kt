package yandexschool.dmpolyakov.money

import org.junit.Assert
import org.junit.Test
import yandexschool.dmpolyakov.money.models.FinanceOperation
import yandexschool.dmpolyakov.money.utils.sumFinanceOperations
import yandexschool.dmpolyakov.money.utils.toDollars
import yandexschool.dmpolyakov.money.utils.toRubbles

class FinanceUnitTest {

    @Test
    fun int_rubbles_to_dollars_is_correct() {
        Assert.assertEquals(10 / DOLLAR_TO_RUBBLE, 10.toDollars(Currency.Rubble), 0.001)
    }

    @Test
    fun int_dollars_to_rubbles_is_correct() {
        Assert.assertEquals(10 * DOLLAR_TO_RUBBLE, 10.toRubbles(Currency.Dollar), 0.001)
    }

    @Test
    fun sum_operations_isCorrect() {
        val op = listOf<FinanceOperation>(
                FinanceOperation(OperationType.Income, 150.toDouble(), Currency.Rubble),
                FinanceOperation(OperationType.Paid, 100.toDouble(), Currency.Rubble),
                FinanceOperation(OperationType.Income, 2.toDouble(), Currency.Dollar),
                FinanceOperation(OperationType.Income, 100.toDouble(), Currency.Rubble),
                FinanceOperation(OperationType.Paid, 3.toDouble(), Currency.Dollar)
        )

        val res = 150 - 100 + 2.toRubbles(Currency.Dollar) + 100 - 3.toRubbles(Currency.Dollar)
        val s = sumFinanceOperations(op, Currency.Rubble)

        Assert.assertEquals(res, s, 0.001)
    }

}