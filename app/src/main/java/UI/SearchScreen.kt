package UI

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.quranapp_1.R
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import model.Surah
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import com.example.quranapp_1.data.surahList // لو لسه مش مستورد


@Composable
fun SearchScreen(   navController: NavController) {
    val context = LocalContext.current

    val font = FontFamily(Font(R.font.uthmic_hafs))

    var searchText by remember { mutableStateOf("") }

    val jsonString = remember {
        context.assets.open("quran_ayah.json").bufferedReader().use { it.readText() }
    }

    val gson = Gson()
    val type = object : TypeToken<Map<String, List<Surah>>>() {}.type
    val verseMap: Map<String, List<Surah>> = gson.fromJson(jsonString, type)

    // النتيجة بعد البحث
    val result = remember(searchText) {
        if (searchText.length >= 2) {
            verseMap.flatMap { entry ->
                val surahNumber = entry.key
                val verses = entry.value
                verses.filter { it.text.contains(searchText, ignoreCase = true) }
                    .map { Triple(surahNumber, it.verse, it.text) }
            }
        } else emptyList()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
    ) {

        OutlinedTextField(
            value = searchText,
            onValueChange = { searchText = it },
            label = { Text("اكتب كلمة للبحث") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(result) { (surahNum, verseNum, text) ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            val surahName = surahList[surahNum.toInt() - 1]
                            val encodedName = URLEncoder.encode(surahName, StandardCharsets.UTF_8.toString())
                            navController.navigate("surah/$encodedName")

                        },
                    colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surface)
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(
                            text = text,
                            fontSize = 20.sp,
                            fontFamily = font,
                            color = MaterialTheme.colorScheme.onSurface,
                            textAlign = TextAlign.Right,
                            lineHeight = 30.sp
                        )
                        Text(
                            text = "سورة ${surahList[surahNum.toInt() - 1]} - آية $verseNum",
                            fontSize = 14.sp,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }
        }
    }
}
