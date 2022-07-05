package intro

import com.zuluft.intro.di.INTRO_MODULE
import com.zuluft.intro.presentation.IntroViewModel
import com.zuluft.intro.presentation.IntroViewState
import com.zuluft.koin_test_api.base.BaseViewModelTest
import org.junit.Assert
import org.junit.Test
import org.koin.core.module.Module
import org.koin.test.inject


class IntroTest : BaseViewModelTest<IntroViewState, IntroViewModel>() {

  private val viewModel by inject<IntroViewModel>()

  override fun provideAppModules(): List<Module> {
    return listOf(INTRO_MODULE)
  }

  override fun provideViewModel(): IntroViewModel {
    return viewModel
  }

  @Test
  fun `test login redirection after intro scene`() {
    getViewModel().introSceneFinishedIntent()
    Assert.assertEquals(getViewState()?.goToLogin?.getValue(), true)
  }
}