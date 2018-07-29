package yandexschool.dmpolyakov.money

import org.junit.Assert
import org.junit.Test
import yandexschool.dmpolyakov.money.models.FinanceOperation
import yandexschool.dmpolyakov.money.utils.sumFinanceOperations
import yandexschool.dmpolyakov.money.utils.toDollars
import yandexschool.dmpolyakov.money.utils.toRubbles

class FinanceUnitTest {

    @Test
    fun int_rubbles_to_dollars() {
        Assert.assertEquals(10.toBigDecimal() / DOLLAR_TO_RUBBLE, 10.toDollars(Currency.Rubble))
    }

    @Test
    fun int_rubbles_to_rubbles() {
        Assert.assertEquals(10.toBigDecimal(), 10.toRubbles(Currency.Rubble))
    }

    @Test
    fun int_dollars_to_rubbles() {
        Assert.assertEquals(10.toBigDecimal() * DOLLAR_TO_RUBBLE, 10.toRubbles(Currency.Dollar))
    }

    @Test
    fun int_dollars_to_dollars() {
        Assert.assertEquals(10.toBigDecimal(), 10.toDollars(Currency.Dollar))
    }

    @Test
    fun sum_operations_only_income() {
        val op = listOf(
                FinanceOperation(OperationType.Income, 150.toBigDecimal(), Currency.Rubble),
                FinanceOperation(OperationType.Income, 2.toBigDecimal(), Currency.Dollar),
                FinanceOperation(OperationType.Income, 100.toBigDecimal(), Currency.Rubble)
        )

        val res = 150.toRubbles(Currency.Rubble) + 2.toRubbles(Currency.Dollar) + 100.toRubbles(Currency.Rubble)
        val s = sumFinanceOperations(op, Currency.Rubble)

        Assert.assertEquals(res, s)
    }

    @Test
    fun sum_operations_only_paid() {
        val op = listOf(
                FinanceOperation(OperationType.Expense, 100.toBigDecimal(), Currency.Rubble),
                FinanceOperation(OperationType.Expense, 3.toBigDecimal(), Currency.Dollar)
        )

        val res = (-100).toRubbles(Currency.Rubble) - 3.toRubbles(Currency.Dollar)
        val s = sumFinanceOperations(op, Currency.Rubble)

        Assert.assertEquals(res, s)
    }

}
