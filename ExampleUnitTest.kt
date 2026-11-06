package com.example

import com.example.ai.AiAdvisorService
import com.example.data.local.Converters
import com.example.data.model.ExpenseEntity
import com.example.data.model.ExpenseType
import com.example.data.model.Priority
import com.example.localization.AppLanguage
import com.example.localization.AppStrings
import org.junit.Assert.*
import org.junit.Test

class ExampleUnitTest {
    @Test
    fun testMultilingualStrings() {
        val hindiTitle = AppStrings.get("app_title", AppLanguage.HINDI)
        val urduTitle = AppStrings.get("app_title", AppLanguage.URDU)
        val englishTitle = AppStrings.get("app_title", AppLanguage.ENGLISH)

        assertEquals("कार्य-खाता", hindiTitle)
        assertEquals("روزنامچہ و کھاتہ", urduTitle)
        assertEquals("KaryaKhata", englishTitle)
    }

    @Test
    fun testAiAdvisorInsights() {
        val advisor = AiAdvisorService()
        val expenses = listOf(
            ExpenseEntity(
                title = "Restaurant",
                amount = 4000.0,
                type = ExpenseType.EXPENSE,
                category = "Food"
            )
        )
        val suggestionsHindi = advisor.generateInsights(expenses, 10000.0, AppLanguage.HINDI)
        assertTrue(suggestionsHindi.isNotEmpty())
        assertTrue(suggestionsHindi.any { it.category == "Food" })
        assertTrue(suggestionsHindi.first().estimatedMonthlySaving > 0)
    }

    @Test
    fun testConverters() {
        val converters = Converters()
        assertEquals(Priority.HIGH, converters.toPriority("HIGH"))
        assertEquals("LOW", converters.fromPriority(Priority.LOW))
        assertEquals(ExpenseType.INCOME, converters.toExpenseType("INCOME"))
        assertEquals("EXPENSE", converters.fromExpenseType(ExpenseType.EXPENSE))
    }
}
