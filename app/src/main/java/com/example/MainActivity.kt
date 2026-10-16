package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.StudyRepository
import com.example.model.BoardAlert
import com.example.model.Chapter
import com.example.model.ClassGrade
import com.example.model.ModelPaper
import com.example.model.Subject
import com.example.ui.screens.AboutScreen
import com.example.ui.screens.BookmarksScreen
import com.example.ui.screens.ChapterReaderScreen
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.QuizScreen
import com.example.ui.screens.SplashScreen
import com.example.ui.screens.SubjectDetailScreen
import com.example.ui.theme.DeepNavy
import com.example.ui.theme.HRStudyTheme
import com.example.ui.theme.RoyalBlue

sealed interface AppScreen {
    data object Splash : AppScreen
    data object Home : AppScreen
    data class SubjectDetail(val subject: Subject) : AppScreen
    data class ChapterReader(val chapter: Chapter) : AppScreen
    data class Quiz(val chapter: Chapter) : AppScreen
    data object Bookmarks : AppScreen
    data object About : AppScreen
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HRStudyTheme {
                HRStudyApp()
            }
        }
    }
}

@Composable
fun HRStudyApp() {
    var currentScreen by remember { mutableStateOf<AppScreen>(AppScreen.Splash) }
    var selectedGrade by remember { mutableStateOf(ClassGrade.CLASS_10) }
    var activeModelPaper by remember { mutableStateOf<ModelPaper?>(null) }
    var activeBoardAlert by remember { mutableStateOf<BoardAlert?>(null) }

    val bookmarkedIds by StudyRepository.bookmarkedChapterIds.collectAsState()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        when (val screen = currentScreen) {
            is AppScreen.Splash -> {
                SplashScreen(
                    onContinue = { currentScreen = AppScreen.Home }
                )
            }

            is AppScreen.Home -> {
                HomeScreen(
                    selectedGrade = selectedGrade,
                    onGradeSelected = { selectedGrade = it },
                    onSubjectClick = { subject ->
                        currentScreen = AppScreen.SubjectDetail(subject)
                    },
                    onModelPaperClick = { paper ->
                        activeModelPaper = paper
                    },
                    onAlertClick = { alert ->
                        activeBoardAlert = alert
                    },
                    onNavigateBookmarks = {
                        currentScreen = AppScreen.Bookmarks
                    },
                    onNavigateAbout = {
                        currentScreen = AppScreen.About
                    },
                    bookmarkCount = bookmarkedIds.size
                )
            }

            is AppScreen.SubjectDetail -> {
                SubjectDetailScreen(
                    subject = screen.subject,
                    selectedGrade = selectedGrade,
                    onBackClick = { currentScreen = AppScreen.Home },
                    onChapterClick = { chapter ->
                        currentScreen = AppScreen.ChapterReader(chapter)
                    },
                    onStartQuizClick = { chapter ->
                        currentScreen = AppScreen.Quiz(chapter)
                    },
                    bookmarkedIds = bookmarkedIds,
                    onToggleBookmark = { chapterId ->
                        StudyRepository.toggleBookmark(chapterId)
                    }
                )
            }

            is AppScreen.ChapterReader -> {
                ChapterReaderScreen(
                    chapter = screen.chapter,
                    isBookmarked = bookmarkedIds.contains(screen.chapter.id),
                    onToggleBookmark = {
                        StudyRepository.toggleBookmark(screen.chapter.id)
                    },
                    onBackClick = {
                        val sub = StudyRepository.subjects.find { it.id == screen.chapter.subjectId }
                            ?: StudyRepository.subjects.first()
                        currentScreen = AppScreen.SubjectDetail(sub)
                    },
                    onStartQuizClick = {
                        currentScreen = AppScreen.Quiz(screen.chapter)
                    }
                )
            }

            is AppScreen.Quiz -> {
                QuizScreen(
                    chapter = screen.chapter,
                    onBackClick = {
                        currentScreen = AppScreen.ChapterReader(screen.chapter)
                    }
                )
            }

            is AppScreen.Bookmarks -> {
                BookmarksScreen(
                    bookmarkedIds = bookmarkedIds,
                    onToggleBookmark = { chapterId ->
                        StudyRepository.toggleBookmark(chapterId)
                    },
                    onChapterClick = { chapter ->
                        currentScreen = AppScreen.ChapterReader(chapter)
                    },
                    onBackClick = { currentScreen = AppScreen.Home }
                )
            }

            is AppScreen.About -> {
                AboutScreen(
                    onBackClick = { currentScreen = AppScreen.Home }
                )
            }
        }

        // Model Paper Dialog
        activeModelPaper?.let { paper ->
            AlertDialog(
                onDismissRequest = { activeModelPaper = null },
                containerColor = Color.White,
                title = {
                    Text(
                        text = paper.titleHindi,
                        fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                        fontSize = 17.sp,
                        color = DeepNavy
                    )
                },
                text = {
                    androidx.compose.foundation.layout.Column {
                        Text(
                            text = "RBSE ${paper.grade.shortName} • सत्र 2025-26",
                            fontSize = 12.sp,
                            fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                            color = RoyalBlue
                        )
                        androidx.compose.foundation.layout.Spacer(modifier = Modifier.padding(top = 4.dp))
                        Text(
                            text = "समय: ${paper.timeDuration} | कुल पूर्णांक: ${paper.totalMarks} अंक",
                            fontSize = 12.sp,
                            color = Color.DarkGray
                        )
                        androidx.compose.foundation.layout.Spacer(modifier = Modifier.padding(top = 8.dp))
                        Text(
                            text = paper.description,
                            fontSize = 13.sp,
                            lineHeight = 19.sp,
                            color = Color(0xFF2D3748)
                        )
                    }
                },
                confirmButton = {
                    Button(
                        onClick = { activeModelPaper = null },
                        colors = ButtonDefaults.buttonColors(containerColor = RoyalBlue)
                    ) {
                        Text("ठीक है (OK)", fontSize = 13.sp)
                    }
                }
            )
        }

        // Board Alert Dialog
        activeBoardAlert?.let { alert ->
            AlertDialog(
                onDismissRequest = { activeBoardAlert = null },
                containerColor = Color.White,
                title = {
                    Text(
                        text = alert.title,
                        fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                        fontSize = 16.sp,
                        color = DeepNavy
                    )
                },
                text = {
                    androidx.compose.foundation.layout.Column {
                        Text(
                            text = "दिनांक: ${alert.date} • ${alert.tag}",
                            fontSize = 12.sp,
                            fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                            color = RoyalBlue
                        )
                        androidx.compose.foundation.layout.Spacer(modifier = Modifier.padding(top = 8.dp))
                        Text(
                            text = alert.description,
                            fontSize = 13.sp,
                            lineHeight = 20.sp,
                            color = Color(0xFF2D3748)
                        )
                    }
                },
                confirmButton = {
                    Button(
                        onClick = { activeBoardAlert = null },
                        colors = ButtonDefaults.buttonColors(containerColor = DeepNavy)
                    ) {
                        Text("बंद करें (Close)", fontSize = 13.sp)
                    }
                }
            )
        }
    }
}
