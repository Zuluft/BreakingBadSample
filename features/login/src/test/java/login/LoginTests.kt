package login

import com.zuluft.core.CORE_NETWORKING_MODULE
import com.zuluft.koin_test_api.base.BaseViewModelTest
import com.zuluft.login.R
import com.zuluft.login.di.LOGIN_MODULE
import com.zuluft.login.presentation.LoginViewModel
import com.zuluft.login.presentation.LoginViewState
import com.zuluft.mocks.reader.RawJsonReader
import io.mockk.every
import io.mockk.mockk
import login.data.LoginData
import login.fakes.RawJsonReaderFake
import org.junit.Assert
import org.junit.Test
import org.koin.core.module.Module
import org.koin.dsl.bind
import org.koin.dsl.module
import org.koin.test.inject

class LoginTests : BaseViewModelTest<LoginViewState, LoginViewModel>() {

    private val viewModel by inject<LoginViewModel>()

    override fun provideAppModules(): List<Module> {
        return listOf(
            CORE_NETWORKING_MODULE,
            LOGIN_MODULE,
            module(override = true) {
                single {
                    RawJsonReaderFake()
//                    mockk<RawJsonReader>() {
//                        every {
//                            readRawRes(R.raw.users)
//                        } returns LoginData.USERS
//                    }
                } bind RawJsonReader::class
            })
    }

    override fun provideViewModel(): LoginViewModel {
        return viewModel
    }

    @Test
    fun `test registration screen redirection after register click`() {
        getViewModel().onRegisterClickIntent()
        Assert.assertEquals(getViewState()?.goToRegistrationScreen?.getValue(), true)
    }

    @Test
    fun `test restore pass screen redirection after restore pass click`() {
        getViewModel().onRestorePasswordClickIntent()
        Assert.assertEquals(getViewState()?.goToRestorePasswordScreen?.getValue(), true)
    }

    @Test
    fun `test login for correct user`() {
        getViewModel().onLoginIntent(LoginData.CORRECT_USER)
        Assert.assertEquals(getViewState()?.goToHomeScreen?.getValue(), true)
    }

    @Test
    fun `test login for incorrect user`() {
        getViewModel().onLoginIntent(LoginData.INCORRECT_USER)
        Assert.assertNotEquals(getViewState()?.showError?.getValue(), null)
    }


}