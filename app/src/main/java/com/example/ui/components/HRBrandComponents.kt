package com.example.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.model.ClassGrade
import com.example.ui.theme.DeepNavy
import com.example.ui.theme.GoldAccent
import com.example.ui.theme.GoldDark
import com.example.ui.theme.GoldLight
import com.example.ui.theme.GoldPrimary
import com.example.ui.theme.RoyalBlue
import com.example.ui.theme.SkyBlueLight

/**
 * The signature HR monogram & open book emblem.
 */
@Composable
fun HREmblem(
    size: Dp = 44.dp,
    showBorder: Boolean = true,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(size)
            .clip(CircleShape)
            .then(
                if (showBorder) {
                    Modifier.border(BorderStroke(1.5.dp, Brush.linearGradient(listOf(GoldAccent, GoldPrimary, GoldDark))), CircleShape)
                } else Modifier
            )
            .background(DeepNavy),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_hr_logo),
            contentDescription = "HR Study RBSE Logo",
            modifier = Modifier
                .size(size)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
    }
}

/**
 * Top App Header with persistent HR Study RBSE branding
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HRAppHeader(
    onNavigateBookmarks: () -> Unit,
    onNavigateAbout: () -> Unit,
    bookmarkCount: Int = 0,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        modifier = modifier
            .background(DeepNavy)
            .testTag("hr_app_header"),
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = DeepNavy,
            titleContentColor = Color.White,
            navigationIconContentColor = Color.White,
            actionIconContentColor = Color.White
        ),
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                HREmblem(size = 38.dp)
                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "HR Study RBSE",
                            fontWeight = FontWeight.Bold,
                            fontSize = 17.sp,
                            color = Color.White,
                            letterSpacing = 0.2.sp
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Surface(
                            shape = RoundedCornerShape(4.dp),
                            color = GoldAccent.copy(alpha = 0.2f),
                            border = BorderStroke(0.8.dp, GoldAccent.copy(alpha = 0.6f))
                        ) {
                            Text(
                                text = "HR",
                                color = GoldAccent,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.ExtraBold,
                                modifier = Modifier.padding(horizontal = 4.dp, vertical = 1.dp)
                            )
                        }
                    }
                    Text(
                        text = "राजस्थान बोर्ड (RBSE) • कक्षा 9वीं से 12वीं",
                        fontSize = 11.sp,
                        color = Color.White.copy(alpha = 0.85f),
                        fontWeight = FontWeight.Normal
                    )
                }
            }
        },
        actions = {
            IconButton(
                onClick = onNavigateBookmarks,
                modifier = Modifier.testTag("btn_bookmarks")
            ) {
                if (bookmarkCount > 0) {
                    BadgedBox(
                        badge = {
                            Badge(
                                containerColor = GoldAccent,
                                contentColor = DeepNavy
                            ) {
                                Text("$bookmarkCount", fontWeight = FontWeight.Bold, fontSize = 10.sp)
                            }
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Bookmark,
                            contentDescription = "Saved Notes",
                            tint = Color.White
                        )
                    }
                } else {
                    Icon(
                        imageVector = Icons.Default.Bookmark,
                        contentDescription = "Saved Notes",
                        tint = Color.White
                    )
                }
            }

            IconButton(
                onClick = onNavigateAbout,
                modifier = Modifier.testTag("btn_about")
            ) {
                Icon(
                    imageVector = Icons.Default.Info,
                    contentDescription = "About HR Study RBSE",
                    tint = GoldAccent
                )
            }
        }
    )
}

/**
 * Prominent Hero Banner showcasing full branding:
 * App Name: HR Study RBSE
 * Subtitle: राजस्थान बोर्ड
 * Tagline: कक्षा 9वीं से 12वीं तक की पढ़ाई, अब आसान!
 * Brand Identity: HR
 */
@Composable
fun HRHeroBanner(
    onExploreClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .testTag("hr_hero_banner"),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = DeepNavy),
        border = BorderStroke(1.5.dp, Brush.horizontalGradient(listOf(GoldAccent.copy(alpha = 0.7f), RoyalBlue, GoldAccent.copy(alpha = 0.4f)))),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Brush.radialGradient(
                        colors = listOf(RoyalBlue.copy(alpha = 0.45f), DeepNavy),
                        radius = 800f
                    )
                )
                .padding(18.dp)
        ) {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    // Badge: HR & RBSE
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = GoldLight,
                            border = BorderStroke(1.dp, GoldAccent)
                        ) {
                            Text(
                                text = "ब्रांड पहचान: HR",
                                color = GoldDark,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = Color.White.copy(alpha = 0.15f)
                        ) {
                            Text(
                                text = "राजस्थान बोर्ड",
                                color = Color.White,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Medium,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                            )
                        }
                    }

                    HREmblem(size = 46.dp)
                }

                Spacer(modifier = Modifier.height(12.dp))

                // App Name
                Text(
                    text = "HR Study RBSE",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.White,
                    letterSpacing = 0.4.sp
                )

                // Subtitle
                Text(
                    text = "राजस्थान बोर्ड विशेष डिजिटल अध्ययन केंद्र",
                    fontSize = 13.sp,
                    color = GoldAccent,
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Mandatory Tagline
                Surface(
                    shape = RoundedCornerShape(10.dp),
                    color = Color.White.copy(alpha = 0.12f),
                    border = BorderStroke(1.dp, Color.White.copy(alpha = 0.2f))
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.School,
                            contentDescription = null,
                            tint = GoldAccent,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "कक्षा 9वीं से 12वीं तक की पढ़ाई, अब आसान!",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color.White
                        )
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                // Highlights pills
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    StatPill(number = "कक्षा 9-12", label = "सम्पूर्ण कोर्स")
                    StatPill(number = "100%", label = "RBSE सिलेबस")
                    StatPill(number = "हस्तलिखित", label = "टॉप नोट्स")
                }
            }
        }
    }
}

@Composable
private fun StatPill(number: String, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = number,
            color = GoldAccent,
            fontWeight = FontWeight.Bold,
            fontSize = 13.sp
        )
        Text(
            text = label,
            color = Color.White.copy(alpha = 0.75f),
            fontSize = 10.sp
        )
    }
}

/**
 * Class selector tabs: Class 9, 10, 11, 12
 */
@Composable
fun ClassSelectorBar(
    selectedGrade: ClassGrade,
    onGradeSelected: (ClassGrade) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .testTag("class_selector_bar"),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        ClassGrade.entries.forEach { grade ->
            val isSelected = selectedGrade == grade
            val bgColor by animateColorAsState(
                targetValue = if (isSelected) RoyalBlue else Color.White,
                label = "classBg"
            )
            val textColor by animateColorAsState(
                targetValue = if (isSelected) Color.White else DeepNavy,
                label = "classText"
            )
            val borderColor = if (isSelected) GoldAccent else Color(0xFFD6DFEC)

            Card(
                modifier = Modifier
                    .weight(1f)
                    .clickable { onGradeSelected(grade) }
                    .testTag("chip_${grade.name.lowercase()}"),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = bgColor),
                border = BorderStroke(if (isSelected) 1.5.dp else 1.dp, borderColor),
                elevation = CardDefaults.cardElevation(defaultElevation = if (isSelected) 3.dp else 1.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp, horizontal = 4.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = grade.shortName,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.SemiBold,
                        fontSize = 13.sp,
                        color = textColor,
                        maxLines = 1
                    )
                    if (grade.isBoardExam) {
                        Surface(
                            shape = RoundedCornerShape(4.dp),
                            color = if (isSelected) GoldAccent else GoldLight,
                            modifier = Modifier.padding(top = 2.dp)
                        ) {
                            Text(
                                text = "Board",
                                color = if (isSelected) DeepNavy else GoldDark,
                                fontSize = 9.sp,
                                fontWeight = FontWeight.ExtraBold,
                                modifier = Modifier.padding(horizontal = 4.dp, vertical = 1.dp)
                            )
                        }
                    } else {
                        Spacer(modifier = Modifier.height(13.dp))
                    }
                }
            }
        }
    }
}
