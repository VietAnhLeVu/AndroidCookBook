package com.example.androidcookbook

import androidx.compose.ui.test.assert
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onChild
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.androidcookbook.model.auth.SignInRequest
import com.example.androidcookbook.ui.screen.auth.AuthViewModel
import com.example.androidcookbook.ui.screen.auth.LoginScreen
import com.example.androidcookbook.ui.screen.auth.PASSWORD_TEXT_FIELD_TEST_TAG
import com.example.androidcookbook.ui.screen.auth.USERNAME_TEXT_FIELD_TEST_TAG
import com.example.androidcookbook.ui.theme.AndroidCookbookTheme
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.androidcookbook", appContext.packageName)
    }

    @Test
    fun testLogin() {
        val username = "hoapri123"
        val password = "Password123"

        composeTestRule.setContent {
            AndroidCookbookTheme {
                val authViewModel: AuthViewModel = viewModel(factory = AuthViewModel.Factory)
                LoginScreen(
                    authViewModel = authViewModel,
                    onNavigateToSignUp = {},
                    onForgotPasswordClick = {},
                    onSignInClick = { username, password -> authViewModel.SignIn(SignInRequest(username, password)) }
                )
            }
        }
        composeTestRule.onNodeWithTag(USERNAME_TEXT_FIELD_TEST_TAG).performTextInput(username)
        composeTestRule.onNodeWithTag(PASSWORD_TEXT_FIELD_TEST_TAG).performTextInput(password)
        composeTestRule.onNodeWithText("Sign In").performClick()
    }
}