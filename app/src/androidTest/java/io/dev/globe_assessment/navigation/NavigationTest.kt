package io.dev.globe_assessment.navigation

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import io.dev.globe_assessment.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NavigationTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun navigateToProductDetailScreen_whenProductClicked() {
        // Wait for the list to appear (at least 1 clickable item)
        composeTestRule.waitUntilExists(
            matcher = hasClickAction(),
            timeoutMillis = 10_000
        )

        // Click the first product
        composeTestRule.onAllNodes(hasClickAction())[0].performClick()

        // Assert the back button exists on the detail screen
        composeTestRule.onNodeWithTag("Back").assertExists()
    }

    @Test
    fun navigateBackToProductListScreen_fromDetailScreen() {
        // Wait for the list to appear
        composeTestRule.waitUntilExists(
            matcher = hasClickAction(),
            timeoutMillis = 10_000
        )

        // Click the first product to go to detail screen
        composeTestRule.onAllNodes(hasClickAction())[0].performClick()
    }

    // Helper extension to wait until a node matching the matcher appears
    private fun ComposeTestRule.waitUntilExists(
        matcher: SemanticsMatcher,
        timeoutMillis: Long
    ) {
        waitUntil(timeoutMillis) {
            onAllNodes(matcher).fetchSemanticsNodes().isNotEmpty()
        }
    }
}
