package net.bankingapp.domain.accounts.usecase

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import net.bankingapp.domain.accounts.entities.Account
import net.bankingapp.domain.accounts.entities.Bank
import net.bankingapp.domain.accounts.repo.AccountsRepo
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class GetAccountUseCaseTest {

    private lateinit var cut : GetAccountUseCase
    private val accountsRepo = mockk<AccountsRepo>(relaxed = true)

    private val mainBankAccount1 = Account("123", "Main Bank Account 1", 1234.00, listOf())
    private val mainBankAccount2 = Account("124", "Main Bank Account 2", 1234.00, listOf())
    private val mainBank = Bank("123", "Main Bank", 1234.00, true,
        listOf(mainBankAccount1, mainBankAccount2)
    )
    private val otherBankAccount1 = Account("125", "Other Bank Account 1", 1234.00, listOf())
    private val otherBankAccount2 = Account("126", "Other Bank Account 2", 1234.00, listOf())
    private val otherBank = Bank("124", "Other Bank", 1234.00, false,
        listOf(otherBankAccount1, otherBankAccount2)
    )

    @Before
    fun setUp() {
        cut = GetAccountUseCase(accountsRepo)
        coEvery { accountsRepo.getAccountsForMainBank() } returns listOf(mainBank)
        coEvery { accountsRepo.getAccountsForOtherBanks() } returns listOf(otherBank)
    }

    @Test
    fun givenAccountIdWhenExeThenReturnAccount(): Unit = runBlocking {
        // given
        val accountId = "124"
        val params = GetAccountUseCase.Params(accountId)

        // when
        val account = cut.exe(params)

        // then
        coVerify { accountsRepo.getAccountsForMainBank() }
        assert(account == mainBankAccount2)
        assertEquals(mainBankAccount2.id , account.id)
    }
}