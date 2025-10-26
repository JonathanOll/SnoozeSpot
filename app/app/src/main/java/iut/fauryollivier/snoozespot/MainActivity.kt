package iut.fauryollivier.snoozespot

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.compose.SnoozeSpotTheme
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.navigation.dependency
import iut.fauryollivier.snoozespot.app.components.BottomBar
import iut.fauryollivier.snoozespot.app.components.TopBar
import iut.fauryollivier.snoozespot.app.pages.NavGraphs
import iut.fauryollivier.snoozespot.app.pages.destinations.FeedScreenDestination

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SnoozeSpotTheme {

                val showBottomBar = remember { mutableStateOf<Boolean>(false) }
                val topBar = remember { mutableStateOf<@Composable () -> Unit>({ TopBar() }) }

                val navController = rememberNavController()

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = topBar.value,
                    bottomBar = {
                        if(showBottomBar.value)
                            BottomBar(navController)
                    }
                ) { innerPadding ->

                    DestinationsNavHost(
                        navGraph = NavGraphs.root,
                        modifier = Modifier.padding(innerPadding),
                        dependenciesContainerBuilder = {
                            dependency(ScaffoldController(showBottomBar, topBar))
                        },
                        navController = navController
                    )

                }
            }
        }
    }
}