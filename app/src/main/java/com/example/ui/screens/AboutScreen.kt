package com.example.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.ui.components.HREmblem
import com.example.ui.theme.CardBackground
import com.example.ui.theme.DeepNavy
import com.example.ui.theme.GoldAccent
import com.example.ui.theme.GoldDark
import com.example.ui.theme.GoldLight
import com.example.ui.theme.GoldPrimary
import com.example.ui.theme.PageBackground
import com.example.ui.theme.RoyalBlue
import com.example.ui.theme.SkyBlueLight

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutScreen(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    BackHandler { onBackClick() }

    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier
                    .background(DeepNavy)
                    .testTag("about_topbar"),
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = DeepNavy,
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White,
                    actionIconContentColor = Color.White
                ),
                navigationIcon = {
                    IconButton(
                        onClick = onBackClick,
                        modifier = Modifier.testTag("btn_back")
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                title = {
                    Text(
                        text = "ऐप परिचय (About HR Study)",
                        fontWeight = FontWeight.Bold,
                        fontSize = 17.sp,
                        color = Color.White
                    )
                },
                actions = {
                    HREmblem(size = 32.dp, modifier = Modifier.padding(end = 12.dp))
                }
            )
        },
        containerColor = PageBackground,
        modifier = modifier.fillMaxSize()
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .testTag("about_screen_content"),
            contentPadding = androidx.compose.foundation.layout.PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Hero Brand Card
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("about_hero_card"),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = DeepNavy),
                    border = BorderStroke(1.5.dp, Brush.horizontalGradient(listOf(GoldAccent, RoyalBlue, GoldAccent))),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        // App Logo with Open Book and HR Monogram
                        Box(
                            modifier = Modifier
                                .size(96.dp)
                                .clip(CircleShape)
                                .border(BorderStroke(2.5.dp, Brush.sweepGradient(listOf(GoldAccent, GoldPrimary, Color.White, GoldAccent))), CircleShape)
                                .background(DeepNavy),
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_hr_logo),
                                contentDescription = "HR Study RBSE Logo with Open Book and HR Monogram",
                                modifier = Modifier
                                    .size(90.dp)
                                    .clip(CircleShape),
                                contentScale = ContentScale.Crop
                            )
                        }

                        Spacer(modifier = Modifier.height(14.dp))

                        // App Name
                        Text(
                            text = "HR Study RBSE",
                            color = Color.White,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.ExtraBold,
                            letterSpacing = 0.5.sp,
                            modifier = Modifier.testTag("about_app_name")
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        // Subtitle
                        Text(
                            text = "राजस्थान बोर्ड",
                            color = GoldAccent,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.testTag("about_subtitle")
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        // Brand Identity Pill
                        Surface(
                            shape = RoundedCornerShape(12.dp),
                            color = GoldLight,
                            border = BorderStroke(1.dp, GoldAccent),
                            modifier = Modifier.testTag("about_brand_identity")
                        ) {
                            Text(
                                text = "ब्रांड पहचान: HR",
                                color = GoldDark,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.ExtraBold,
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 3.dp)
                            )
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        // Tagline
                        Surface(
                            shape = RoundedCornerShape(10.dp),
                            color = Color.White.copy(alpha = 0.12f),
                            border = BorderStroke(1.dp, Color.White.copy(alpha = 0.2f)),
                            modifier = Modifier.testTag("about_tagline")
                        ) {
                            Text(
                                text = "कक्षा 9वीं से 12वीं तक की पढ़ाई, अब आसान!",
                                color = Color.White,
                                fontSize = 13.sp,
                                fontWeight = FontWeight.SemiBold,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp)
                            )
                        }

                        Spacer(modifier = Modifier.height(14.dp))

                        Text(
                            text = "संस्करण: 1.0 (RBSE 2025-26 विशेष सत्र)",
                            fontSize = 11.sp,
                            color = Color.White.copy(alpha = 0.7f)
                        )
                    }
                }
            }

            // Mission & Purpose
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = CardBackground),
                    border = BorderStroke(1.dp, Color(0xFFE2E8F0)),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.School,
                                contentDescription = null,
                                tint = RoyalBlue,
                                modifier = Modifier.size(22.dp)
                            )
                            Text(
                                text = "हमारा उद्देश्य (Our Mission)",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = DeepNavy
                            )
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        Text(
                            text = "HR Study RBSE राजस्थान के माध्यमिक एवं उच्च माध्यमिक शिक्षा बोर्ड (RBSE) के छात्रों के लिए समर्पित एक आधुनिक, सरल और उच्च गुणवत्ता युक्त डिजिटल अध्ययन मंच है।\n\nयहाँ कक्षा 9वीं, 10वीं, 11वीं और 12वीं के सभी विद्यार्थियों को NCERT व राजस्थान बोर्ड के नवीनतम पाठ्यक्रम के अनुरूप हस्तलिखित नोट्स, अध्याय-वार सारांश, ब्लूप्रिंट मॉडल पेपर्स और अभ्यास क्विज़ निःशुल्क एवं तीव्र गति से उपलब्ध कराए जाते हैं।",
                            fontSize = 13.sp,
                            lineHeight = 21.sp,
                            color = Color(0xFF2D3748)
                        )
                    }
                }
            }

            // Key Highlights
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = CardBackground),
                    border = BorderStroke(1.dp, Color(0xFFE2E8F0)),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "मुख्य विशेषताएं (Key Features)",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = DeepNavy
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        AboutFeatureItem(
                            title = "राजस्थान बोर्ड परीक्षा ब्लूप्रिंट 2026",
                            desc = "नवीनतम अंक विभाजन योजना व मॉडल प्रश्न पत्रों का सटीक संकलन।"
                        )
                        AboutFeatureItem(
                            title = "हस्तलिखित नोट्स एवं सूत्र तालिका",
                            desc = "अनुभवी शिक्षकों द्वारा तैयार सरल, स्पष्ट एवं प्रभावी अध्ययन सामग्री।"
                        )
                        AboutFeatureItem(
                            title = "पिछले 5 वर्षों के बोर्ड प्रश्न (PYQs)",
                            desc = "बोर्ड परीक्षा में बार-बार पूछे जाने वाले प्रश्नों का संपूर्ण हल।"
                        )
                        AboutFeatureItem(
                            title = "ऑनलाइन वस्तुनिष्ठ टेस्ट व क्विज़",
                            desc = "तत्काल परिणाम एवं विस्तृत व्याख्या के साथ परीक्षा की सटीक तैयारी।"
                        )
                        AboutFeatureItem(
                            title = "ऑफलाइन सेव व बुकमार्क सुविधा",
                            desc = "इंटरनेट के बिना भी महत्वपूर्ण नोट्स कभी भी दोबारा पढ़ें।"
                        )
                    }
                }
            }

            // Contact & Help
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = SkyBlueLight),
                    border = BorderStroke(1.dp, RoyalBlue.copy(alpha = 0.3f))
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Email,
                                contentDescription = null,
                                tint = RoyalBlue,
                                modifier = Modifier.size(20.dp)
                            )
                            Text(
                                text = "सहायता एवं सुझाव (Student Support)",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                                color = DeepNavy
                            )
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "किसी भी प्रश्न, सुझाव या नई अध्ययन सामग्री के अनुरोध हेतु हमें संपर्क करें:",
                            fontSize = 12.sp,
                            color = Color(0xFF2D3748)
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        Surface(
                            shape = RoundedCornerShape(6.dp),
                            color = Color.White,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = "ईमेल: hariomgamar694@gmail.com",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = RoyalBlue,
                                modifier = Modifier.padding(10.dp)
                            )
                        }
                    }
                }
            }

            // Disclaimer Card
            item {
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = Color.White,
                    border = BorderStroke(1.dp, Color(0xFFE2E8F0)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier.padding(12.dp),
                        verticalAlignment = Alignment.Top
                    ) {
                        Icon(
                            imageVector = Icons.Default.Shield,
                            contentDescription = null,
                            tint = Color.Gray,
                            modifier = Modifier
                                .size(16.dp)
                                .padding(top = 2.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "अस्वीकरण: यह एप्लिकेशन राजस्थान बोर्ड (RBSE) के छात्रों के अध्ययन में सहायता हेतु स्वतंत्र रूप से विकसित शैक्षिक ऐप है। आधिकारिक सूचनाओं के लिए माध्यमिक शिक्षा बोर्ड, अजमेर की वेबसाइट देखें।",
                            fontSize = 10.sp,
                            lineHeight = 15.sp,
                            color = Color.Gray
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun AboutFeatureItem(title: String, desc: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp),
        verticalAlignment = Alignment.Top
    ) {
        Icon(
            imageVector = Icons.Default.CheckCircle,
            contentDescription = null,
            tint = GoldDark,
            modifier = Modifier
                .size(16.dp)
                .padding(top = 2.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(
                text = title,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = DeepNavy
            )
            Text(
                text = desc,
                fontSize = 11.sp,
                lineHeight = 16.sp,
                color = Color(0xFF4A5568)
            )
        }
    }
}
