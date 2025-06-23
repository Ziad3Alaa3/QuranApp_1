package UI

import androidx.compose.foundation.background
import com.example.quranapp_1.data.surahList
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.style.TextAlign


// @Preview(showBackground = true)
@Composable
fun HomeScreen(navController: NavController) {
    var searchText by remember { mutableStateOf("") }

    val filterList = if (searchText.length >= 3) {
        surahList.filter { it.contains(searchText, ignoreCase = true) }
    } else {
        surahList
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
    ) {

        // 1️⃣ مربع البحث (لوحده)
        OutlinedTextField(
            value = searchText,
            onValueChange = { searchText = it },
            label = { Text("ابحث عن سورة") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        // 2️⃣ القائمة (لوحدها تحت مربع البحث)
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            itemsIndexed(filterList) { index, surah ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            val encodedSurah = URLEncoder.encode(surah, StandardCharsets.UTF_8.toString())
                            navController.navigate("surah/$encodedSurah")

                        },
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    ),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Text(
                        text = "${index + 1}. $surah",
                        fontSize = 20.sp,
                        color = Color(0xFF1B4332),
                        modifier = Modifier.padding(16.dp).fillMaxWidth(),
                        textAlign = TextAlign.Start
                    )
                }
            }

            // ✅ زر بحث داخل الآيات
            item {
                Button(
                    onClick = { navController.navigate("search") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                ) {
                    Text("🔍 بحث في الآيات", color = Color.White)
                }
            }
        }
    }
}
