package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Campaign
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.EditNote
import androidx.compose.material.icons.filled.HelpCenter
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Quiz
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.StudyRepository
import com.example.model.BoardAlert
import com.example.model.ClassGrade
import com.example.model.ModelPaper
import com.example.model.Subject
import com.example.ui.components.ClassSelectorBar
import com.example.ui.components.HRAppHeader
import com.example.ui.components.HREmblem
import com.example.ui.components.HRHeroBanner
import com.example.ui.theme.CardBackground
import com.example.ui.theme.DeepNavy
import com.example.ui.theme.GoldAccent
import com.example.ui.theme.GoldDark
import com.example.ui.theme.GoldLight
import com.example.ui.theme.PageBackground
import com.example.ui.theme.RoyalBlue
import com.example.ui.theme.SapphireBlue
import com.example.ui.theme.SkyBlueLight

@Composable
fun HomeScreen(
    selectedGrade: ClassGrade,
    onGradeSelected: (ClassGrade) -> Unit,
    onSubjectClick: (Subject) -> Unit,
    onModelPaperClick: (ModelPaper) -> Unit,
    onAlertClick: (BoardAlert) -> Unit,
    onNavigateBookmarks: () -> Unit,
    onNavigateAbout: () -> Unit,
    bookmarkCount: Int,
    modifier: Modifier = Modifier
) {
    var searchQuery by remember { mutableStateOf("") }
    var selectedCategoryIndex by remember { mutableStateOf(0) }

    val subjects = StudyRepository.getSubjectsForGrade(selectedGrade).filter {
        it.nameHindi.contains(searchQuery, ignoreCase = true) ||
                it.nameEnglish.contains(searchQuery, ignoreCase = true)
    }

    val modelPapers = StudyRepository.modelPapers.filter { it.grade == selectedGrade }

    Scaffold(
        topBar = {
            HRAppHeader(
                onNavigateBookmarks = onNavigateBookmarks,
                onNavigateAbout = onNavigateAbout,
                bookmarkCount = bookmarkCount
            )
        },
        containerColor = PageBackground,
        modifier = modifier.fillMaxSize()
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .testTag("home_screen_content"),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // 1. Prominent Hero Banner with consistent brand name, subtitle, tagline and HR identity
            item {
                HRHeroBanner(
                    onExploreClicked = { /* already on home */ }
                )
            }

            // 2. Class Selector Bar (Class 9, 10, 11, 12)
            item {
                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "अपनी कक्षा चुनें (Select Class)",
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp,
                            color = DeepNavy
                        )
                        Surface(
                            shape = RoundedCornerShape(12.dp),
                            color = SkyBlueLight
                        ) {
                            Text(
                                text = selectedGrade.displayName,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = RoyalBlue,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    ClassSelectorBar(
                        selectedGrade = selectedGrade,
                        onGradeSelected = onGradeSelected
                    )
                }
            }

            // 3. Search Bar for Rajasthan Board Topics & Subjects
            item {
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    placeholder = {
                        Text(
                            "विषय या अध्याय खोजें (उदा. गणित, रासायनिक अभिक्रियाएं)...",
                            fontSize = 13.sp,
                            color = Color.Gray
                        )
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search",
                            tint = RoyalBlue
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("search_bar"),
                    shape = RoundedCornerShape(14.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        focusedBorderColor = RoyalBlue,
                        unfocusedBorderColor = Color(0xFFD6DFEC)
                    ),
                    singleLine = true
                )
            }

            // 4. Quick Study Categories
            item {
                Column {
                    Text(
                        text = "त्वरित अध्ययन श्रेणियां",
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                        color = DeepNavy
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        item {
                            QuickCategoryPill(
                                title = "सभी विषय",
                                icon = Icons.AutoMirrored.Filled.MenuBook,
                                isSelected = selectedCategoryIndex == 0,
                                onClick = { selectedCategoryIndex = 0 }
                            )
                        }
                        item {
                            QuickCategoryPill(
                                title = "हस्तलिखित नोट्स",
                                icon = Icons.Default.EditNote,
                                isSelected = selectedCategoryIndex == 1,
                                onClick = { selectedCategoryIndex = 1 }
                            )
                        }
                        item {
                            QuickCategoryPill(
                                title = "मॉडल पेपर्स",
                                icon = Icons.Default.Description,
                                isSelected = selectedCategoryIndex == 2,
                                onClick = { selectedCategoryIndex = 2 }
                            )
                        }
                        item {
                            QuickCategoryPill(
                                title = "बोर्ड क्विज़",
                                icon = Icons.Default.Quiz,
                                isSelected = selectedCategoryIndex == 3,
                                onClick = { selectedCategoryIndex = 3 }
                            )
                        }
                        item {
                            QuickCategoryPill(
                                title = "ब्लूप्रिंट व अपडेट",
                                icon = Icons.Default.Campaign,
                                isSelected = selectedCategoryIndex == 4,
                                onClick = { selectedCategoryIndex = 4 }
                            )
                        }
                    }
                }
            }

            // 5. Subjects Section (for selected class)
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "${selectedGrade.shortName} के विषय",
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            color = DeepNavy
                        )
                        Text(
                            text = "RBSE नवीनतम NCERT पैटर्न",
                            fontSize = 11.sp,
                            color = Color.Gray
                        )
                    }
                    Text(
                        text = "${subjects.size} विषय उपलब्ध",
                        fontSize = 12.sp,
                        color = RoyalBlue,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }

            items(subjects, key = { it.id }) { subject ->
                SubjectCardItem(
                    subject = subject,
                    selectedGrade = selectedGrade,
                    onClick = { onSubjectClick(subject) }
                )
            }

            // 6. RBSE Model Papers & Blueprint Card
            item {
                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "RBSE बोर्ड मॉडल पेपर्स 2026",
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            color = DeepNavy
                        )
                        Surface(
                            shape = RoundedCornerShape(10.dp),
                            color = GoldLight,
                            border = BorderStroke(1.dp, GoldAccent)
                        ) {
                            Text(
                                text = "सत्र 2025-26",
                                color = GoldDark,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    if (modelPapers.isEmpty()) {
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            colors = CardDefaults.cardColors(containerColor = Color.White),
                            border = BorderStroke(1.dp, Color(0xFFE2E8F0)),
                            shape = RoundedCornerShape(14.dp)
                        ) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                Text(
                                    text = "कक्षा 10वीं व 12वीं बोर्ड के आधिकारिक मॉडल पेपर्स उपलब्ध हैं। अन्य कक्षाओं के अभ्यास पत्र जल्द जोड़े जा रहे हैं।",
                                    fontSize = 13.sp,
                                    color = Color.DarkGray
                                )
                            }
                        }
                    } else {
                        modelPapers.forEach { paper ->
                            ModelPaperCardItem(
                                paper = paper,
                                onClick = { onModelPaperClick(paper) }
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                    }
                }
            }

            // 7. Latest Rajasthan Board Updates
            item {
                Column {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Campaign,
                            contentDescription = null,
                            tint = RoyalBlue,
                            modifier = Modifier.size(20.dp)
                        )
                        Text(
                            text = "राजस्थान बोर्ड (RBSE) महत्वपूर्ण सूचनाएं",
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            color = DeepNavy
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    StudyRepository.boardAlerts.forEach { alert ->
                        BoardAlertCard(
                            alert = alert,
                            onClick = { onAlertClick(alert) }
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }

            // 8. Brand Footer Tag
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        HREmblem(size = 36.dp)
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = "HR Study RBSE",
                            fontWeight = FontWeight.Bold,
                            fontSize = 13.sp,
                            color = DeepNavy
                        )
                        Text(
                            text = "कक्षा 9वीं से 12वीं तक की पढ़ाई, अब आसान!",
                            fontSize = 11.sp,
                            color = Color.Gray
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun QuickCategoryPill(
    title: String,
    icon: ImageVector,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Surface(
        onClick = onClick,
        shape = RoundedCornerShape(20.dp),
        color = if (isSelected) RoyalBlue else Color.White,
        border = BorderStroke(1.dp, if (isSelected) GoldAccent else Color(0xFFD6DFEC)),
        shadowElevation = if (isSelected) 2.dp else 0.dp
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = if (isSelected) GoldAccent else RoyalBlue,
                modifier = Modifier.size(16.dp)
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text = title,
                fontSize = 12.sp,
                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                color = if (isSelected) Color.White else DeepNavy
            )
        }
    }
}

@Composable
private fun SubjectCardItem(
    subject: Subject,
    selectedGrade: ClassGrade,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .testTag("subject_card_${subject.id}"),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = CardBackground),
        border = BorderStroke(1.dp, Color(0xFFE2E8F0)),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Subject color bar / icon
            Box(
                modifier = Modifier
                    .size(46.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(subject.primaryColorHex)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = subject.nameHindi.take(1),
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.width(14.dp))

            Column(modifier = Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = subject.nameHindi,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = DeepNavy
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "• ${subject.nameEnglish}",
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                }

                Spacer(modifier = Modifier.height(3.dp))

                Text(
                    text = subject.description,
                    fontSize = 12.sp,
                    color = Color(0xFF4A5568),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(4.dp))

                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Surface(
                        shape = RoundedCornerShape(4.dp),
                        color = SkyBlueLight
                    ) {
                        Text(
                            text = "RBSE पाठ्यपुस्तक",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = RoyalBlue,
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                        )
                    }

                    Surface(
                        shape = RoundedCornerShape(4.dp),
                        color = GoldLight
                    ) {
                        Text(
                            text = "हस्तलिखित नोट्स",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = GoldDark,
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                        )
                    }
                }
            }

            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = "Open Subject",
                tint = RoyalBlue,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}

@Composable
private fun ModelPaperCardItem(
    paper: ModelPaper,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .testTag("model_paper_${paper.id}"),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(1.dp, Color(0xFFE2E8F0)),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    shape = RoundedCornerShape(6.dp),
                    color = SkyBlueLight
                ) {
                    Text(
                        text = "RBSE ${paper.subjectName}",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = RoyalBlue,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                    )
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "पूर्णांक: ${paper.totalMarks} अंक",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = DeepNavy
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = paper.titleHindi,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                color = DeepNavy
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = paper.description,
                fontSize = 12.sp,
                color = Color(0xFF4A5568)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "समय: ${paper.timeDuration}",
                    fontSize = 11.sp,
                    color = Color.Gray
                )

                Text(
                    text = "प्रश्न पत्र व उत्तर देखें →",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = SapphireBlue
                )
            }
        }
    }
}

@Composable
private fun BoardAlertCard(
    alert: BoardAlert,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .testTag("board_alert_${alert.id}"),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = if (alert.isImportant) GoldLight else Color.White),
        border = BorderStroke(1.dp, if (alert.isImportant) GoldAccent else Color(0xFFE2E8F0))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.Top
        ) {
            Surface(
                shape = CircleShape,
                color = if (alert.isImportant) GoldAccent else RoyalBlue,
                modifier = Modifier.size(32.dp)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = if (alert.isImportant) Icons.Default.Star else Icons.Default.Notifications,
                        contentDescription = null,
                        tint = if (alert.isImportant) DeepNavy else Color.White,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Surface(
                        shape = RoundedCornerShape(4.dp),
                        color = Color.White
                    ) {
                        Text(
                            text = alert.tag,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = RoyalBlue,
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                        )
                    }
                    Text(
                        text = alert.date,
                        fontSize = 10.sp,
                        color = Color.Gray
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = alert.title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp,
                    color = DeepNavy
                )

                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    text = alert.description,
                    fontSize = 11.sp,
                    color = Color(0xFF4A5568)
                )
            }
        }
    }
}
