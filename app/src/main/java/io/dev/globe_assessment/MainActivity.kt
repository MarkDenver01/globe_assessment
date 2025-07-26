package io.dev.globe_assessment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import io.dev.globe_assessment.navigation.Routes
import io.dev.globe_assessment.navigation.favoriteGraph
import io.dev.globe_assessment.navigation.productGraph
import io.dev.globe_assessment.navigation.settingsGraph
import io.dev.globe_assessment.presentation.theme.Globe_assessmentTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // enableEdgeToEdge()
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            Globe_assessmentTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = Routes.PRODUCT_LIST_ROUTE
                ) {
                    productGraph(navController)
                    favoriteGraph()
                    settingsGraph()
                }
            }
        }
    }
}
