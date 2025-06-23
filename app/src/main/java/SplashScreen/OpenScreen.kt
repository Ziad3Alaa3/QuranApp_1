package SplashScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.quranapp_1.R
import kotlinx.coroutines.delay

// @Preview(showBackground = true)
@Composable

fun OpenScreen(navController: NavHostController){

     LaunchedEffect(key1 = true) {
         delay(2000)
         navController.navigate("home")
     }


    Box(modifier = Modifier
        .fillMaxSize().background(Color(0x5CFFFFFF)),
        contentAlignment = Alignment.Center
    ) {

        Image(painter = painterResource(id = R.drawable.quran_logo )
        , contentDescription = "شعار القران"
            , modifier = Modifier.size(800.dp).fillMaxWidth()
        )
    }
}