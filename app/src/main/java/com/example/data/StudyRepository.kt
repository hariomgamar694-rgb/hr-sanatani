package com.example.data

import com.example.model.BoardAlert
import com.example.model.Chapter
import com.example.model.ClassGrade
import com.example.model.ModelPaper
import com.example.model.QuestionAnswer
import com.example.model.QuizQuestion
import com.example.model.Subject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

object StudyRepository {

    private val _bookmarkedChapterIds = MutableStateFlow<Set<String>>(setOf("ch_math_10_1", "ch_sci_10_1"))
    val bookmarkedChapterIds: StateFlow<Set<String>> = _bookmarkedChapterIds.asStateFlow()

    fun toggleBookmark(chapterId: String) {
        val current = _bookmarkedChapterIds.value.toMutableSet()
        if (current.contains(chapterId)) {
            current.remove(chapterId)
        } else {
            current.add(chapterId)
        }
        _bookmarkedChapterIds.value = current
    }

    val subjects: List<Subject> = listOf(
        Subject(
            id = "math",
            nameHindi = "गणित",
            nameEnglish = "Mathematics",
            iconName = "calculate",
            primaryColorHex = 0xFF1565C0,
            grades = listOf(ClassGrade.CLASS_9, ClassGrade.CLASS_10, ClassGrade.CLASS_11, ClassGrade.CLASS_12),
            totalChapters = 14,
            description = "RBSE बोर्ड परीक्षा के लिए संपूर्ण अध्याय, सूत्र, प्रमेय और हल सहित प्रश्न"
        ),
        Subject(
            id = "science",
            nameHindi = "विज्ञान",
            nameEnglish = "Science",
            iconName = "biotech",
            primaryColorHex = 0xFF0D538C,
            grades = listOf(ClassGrade.CLASS_9, ClassGrade.CLASS_10),
            totalChapters = 13,
            description = "भौतिक, रसायन और जीव विज्ञान के संपूर्ण हस्तलिखित नोट्स और चित्र"
        ),
        Subject(
            id = "physics",
            nameHindi = "भौतिक विज्ञान",
            nameEnglish = "Physics",
            iconName = "science",
            primaryColorHex = 0xFF1976D2,
            grades = listOf(ClassGrade.CLASS_11, ClassGrade.CLASS_12),
            totalChapters = 14,
            description = "कक्षा 11वीं व 12वीं के लिए विस्तृत थ्योरी, न्यूमेरिकल एवं बोर्ड मॉडल प्रश्न"
        ),
        Subject(
            id = "chemistry",
            nameHindi = "रसायन विज्ञान",
            nameEnglish = "Chemistry",
            iconName = "bubble_chart",
            primaryColorHex = 0xFF0288D1,
            grades = listOf(ClassGrade.CLASS_11, ClassGrade.CLASS_12),
            totalChapters = 10,
            description = "कार्बनिक, अकार्बनिक और भौतिक रसायन के रिएक्शन चार्ट्स और शॉर्ट नोट्स"
        ),
        Subject(
            id = "biology",
            nameHindi = "जीव विज्ञान",
            nameEnglish = "Biology",
            iconName = "eco",
            primaryColorHex = 0xFF2E7D32,
            grades = listOf(ClassGrade.CLASS_11, ClassGrade.CLASS_12),
            totalChapters = 16,
            description = "स्वच्छ नामांकित चित्र, आनुवंशिकी, जैव प्रौद्योगिकी एवं मानव कल्याण"
        ),
        Subject(
            id = "social_science",
            nameHindi = "सामाजिक विज्ञान",
            nameEnglish = "Social Science",
            iconName = "public",
            primaryColorHex = 0xFFC25E00,
            grades = listOf(ClassGrade.CLASS_9, ClassGrade.CLASS_10),
            totalChapters = 18,
            description = "इतिहास, भूगोल, राजनीति विज्ञान और अर्थशास्त्र के महत्वपूर्ण बिंदु एवं मानचित्र"
        ),
        Subject(
            id = "hindi",
            nameHindi = "हिंदी अनिवार्य",
            nameEnglish = "Hindi Compulsory",
            iconName = "auto_stories",
            primaryColorHex = 0xFF9C27B0,
            grades = listOf(ClassGrade.CLASS_9, ClassGrade.CLASS_10, ClassGrade.CLASS_11, ClassGrade.CLASS_12),
            totalChapters = 12,
            description = "गद्य एवं पद्य खंड, सप्रसंग व्याख्या, व्याकरण, निबंध एवं पत्र लेखन"
        ),
        Subject(
            id = "english",
            nameHindi = "English Compulsory",
            nameEnglish = "English Compulsory",
            iconName = "translate",
            primaryColorHex = 0xFF455A64,
            grades = listOf(ClassGrade.CLASS_9, ClassGrade.CLASS_10, ClassGrade.CLASS_11, ClassGrade.CLASS_12),
            totalChapters = 11,
            description = "Textbooks Q&A, Grammar rules, Report writing and Letter formats"
        )
    )

    val chapters: List<Chapter> = listOf(
        // Class 10 Math Chapter 1
        Chapter(
            id = "ch_math_10_1",
            subjectId = "math",
            grade = ClassGrade.CLASS_10,
            chapterNumber = 1,
            titleHindi = "वास्तविक संख्याएं",
            titleEnglish = "Real Numbers",
            overview = "इस अध्याय में अंकगणित की आधारभूत प्रमेय (Fundamental Theorem of Arithmetic), अपरिमेय संख्याओं का पुनर्भ्रमण और उनके सत्यापन का अध्ययन किया जाता है। बोर्ड परीक्षा में इस अध्याय से 4 अंक के प्रश्न पूछे जाते हैं।",
            keyPoints = listOf(
                "अंकगणित की आधारभूत प्रमेय: प्रत्येक भाज्य संख्या को अभाज्य संख्याओं के एक गुणनफल के रूप में व्यक्त किया जा सकता है।",
                "दो धनात्मक पूर्णांकों a और b के लिए: HCF(a, b) × LCM(a, b) = a × b",
                "यदि p एक अभाज्य संख्या है और p, a² को विभाजित करती है, तो p, a को भी विभाजित करेगी (जहाँ a धनात्मक पूर्णांक है)।",
                "√2, √3, √5 अपरिमेय संख्याएं हैं।",
                "एक परिमेय और एक अपरिमेय संख्या का योग या अंतर सदैव अपरिमेय होता है।"
            ),
            importantFormulas = listOf(
                "HCF(a, b) × LCM(a, b) = a × b",
                "भाज्य = भाजक × भागफल + शेषफल (a = bq + r, जहाँ 0 ≤ r < b)",
                "अभाज्य गुणनखंडन विधि: 140 = 2² × 5 × 7"
            ),
            questions = listOf(
                QuestionAnswer(
                    id = "q1",
                    questionHindi = "सिद्ध कीजिए कि √5 एक अपरिमेय संख्या है।",
                    answerHindi = "उपपत्ति: मान लेते हैं कि √5 एक परिमेय संख्या है। अतः √5 = a/b (जहाँ a और b सह-अभाज्य पूर्णांक हैं तथा b ≠ 0)।\nदोनों पक्षों का वर्ग करने पर: 5 = a²/b² ⇒ 5b² = a²।\nअतः 5, a² को विभाजित करता है, इसलिए 5, a को भी विभाजित करेगा।\nमान लेते हैं a = 5c। मान रखने पर: 5b² = (5c)² = 25c² ⇒ b² = 5c²।\nअतः 5, b को भी विभाजित करता है। इससे a और b में उभयनिष्ठ गुणनखंड 5 प्राप्त होता है जो हमारी इस मान्यता का विरोध करता है कि a और b सह-अभाज्य हैं। अतः √5 एक अपरिमेय संख्या है। (इति सिद्धम्)",
                    marks = 4,
                    isPyq = true,
                    pyqYear = "2024"
                ),
                QuestionAnswer(
                    id = "q2",
                    questionHindi = "संख्याओं 96 और 404 का अभाज्य गुणनखंडन विधि द्वारा HCF ज्ञात कीजिए और फिर इनका LCM ज्ञात कीजिए।",
                    answerHindi = "हल: 96 = 2⁵ × 3\n404 = 2² × 101\nHCF(96, 404) = 2² = 4\nहम जानते हैं: LCM(a, b) = (a × b) / HCF(a, b)\nLCM(96, 404) = (96 × 404) / 4 = 96 × 101 = 9696\nउत्तर: HCF = 4, LCM = 9696",
                    marks = 2,
                    isPyq = true,
                    pyqYear = "2023"
                ),
                QuestionAnswer(
                    id = "q3",
                    questionHindi = "संख्या 140 को अभाज्य गुणनखंडों के गुणनफल के रूप में व्यक्त कीजिए।",
                    answerHindi = "हल: 140 = 2 × 2 × 5 × 7 = 2² × 5 × 7।",
                    marks = 1,
                    isPyq = false
                )
            ),
            quizQuestions = listOf(
                QuizQuestion(
                    id = "qz1",
                    questionHindi = "यदि दो संख्याओं का गुणनफल 1080 है और उनका HCF 6 है, तो उनका LCM होगा:",
                    options = listOf("180", "120", "240", "60"),
                    correctIndex = 0,
                    explanationHindi = "LCM = (दोनों संख्याओं का गुणनफल) / HCF = 1080 / 6 = 180."
                ),
                QuizQuestion(
                    id = "qz2",
                    questionHindi = "निम्न में से कौन सी संख्या अपरिमेय संख्या है?",
                    options = listOf("√4", "√9", "√7", "25/5"),
                    correctIndex = 2,
                    explanationHindi = "√7 का पूर्ण वर्गमूल नहीं निकाला जा सकता, अतः यह अपरिमेय संख्या है।"
                ),
                QuizQuestion(
                    id = "qz3",
                    questionHindi = "संख्या 3 + 2√5 किस प्रकार की संख्या है?",
                    options = listOf("पूर्णांक संख्या", "परिमेय संख्या", "अपरिमेय संख्या", "प्राकृत संख्या"),
                    correctIndex = 2,
                    explanationHindi = "एक परिमेय और एक अपरिमेय संख्या का योग सदैव अपरिमेय संख्या होता है।"
                )
            )
        ),

        // Class 10 Math Chapter 2
        Chapter(
            id = "ch_math_10_2",
            subjectId = "math",
            grade = ClassGrade.CLASS_10,
            chapterNumber = 2,
            titleHindi = "बहुपद",
            titleEnglish = "Polynomials",
            overview = "बहुपद के शून्यकों का ज्यामितीय अर्थ, द्विघात बहुपद के शून्यकों और गुणांकों के बीच संबंध। बोर्ड परीक्षा में 4 अंक का वेटेज।",
            keyPoints = listOf(
                "द्विघात बहुपद का मानक रूप: p(x) = ax² + bx + c (जहाँ a ≠ 0)",
                "यदि α और β शून्यक हैं, तो: शून्यकों का योग (α + β) = -b/a",
                "शून्यकों का गुणनफल (α × β) = c/a",
                "द्विघात बहुपद बनाना: p(x) = k[x² - (α + β)x + αβ]"
            ),
            importantFormulas = listOf(
                "α + β = -b / a",
                "α × β = c / a",
                "p(x) = x² - (शून्यकों का योग)x + (शून्यकों का गुणनफल)"
            ),
            questions = listOf(
                QuestionAnswer(
                    id = "q4",
                    questionHindi = "द्विघात बहुपद x² - 2x - 8 के शून्यक ज्ञात कीजिए और शून्यकों तथा गुणांकों के बीच के संबंध की सत्यता की जांच कीजिए।",
                    answerHindi = "हल: x² - 2x - 8 = 0\nx² - 4x + 2x - 8 = 0\nx(x - 4) + 2(x - 4) = 0\n(x - 4)(x + 2) = 0 ⇒ x = 4, x = -2\nशून्यक α = 4, β = -2 हैं।\nसंबंध की जांच:\nα + β = 4 + (-2) = 2 = -(-2)/1 = -b/a (सत्य)\nαβ = 4 × (-2) = -8 = -8/1 = c/a (सत्य)",
                    marks = 3,
                    isPyq = true,
                    pyqYear = "2024"
                )
            ),
            quizQuestions = listOf(
                QuizQuestion(
                    id = "qz4",
                    questionHindi = "द्विघात बहुपद x² + 7x + 10 के शून्यकों का योगफल क्या होगा?",
                    options = listOf("-7", "7", "10", "-10"),
                    correctIndex = 0,
                    explanationHindi = "शून्यकों का योग = -b/a = -7/1 = -7."
                )
            )
        ),

        // Class 10 Science Chapter 1
        Chapter(
            id = "ch_sci_10_1",
            subjectId = "science",
            grade = ClassGrade.CLASS_10,
            chapterNumber = 1,
            titleHindi = "रासायनिक अभिक्रियाएं एवं समीकरण",
            titleEnglish = "Chemical Reactions and Equations",
            overview = "रासायनिक समीकरण संतुलित करना, संयोजन, वियोजन, विस्थापन, द्विविस्थापन, उपचयन-अपचयन अभिक्रियाएं, संक्षारण और विकृतगंधिता।",
            keyPoints = listOf(
                "संयोजन अभिक्रिया: दो या दो से अधिक अभिकारक मिलकर एकल उत्पाद बनाते हैं। जैसे: CaO + H₂O → Ca(OH)₂ + ऊष्मा",
                "वियोजन अभिक्रिया: एकल अभिकारक टूटकर दो या अधिक उत्पाद बनाता है। जैसे: 2FeSO₄ → Fe₂O₃ + SO₂ + SO₃",
                "ऊष्माक्षेपी अभिक्रियाएं: जिनमें उत्पाद के निर्माण के साथ-साथ ऊष्मा भी उत्पन्न होती है (जैसे श्वसन)।",
                "संक्षारण: जब धातु वायु, नमी या अम्ल के संपर्क में आकर नष्ट होने लगती है (जैसे लोहे पर जंग लगना)।",
                "विकृतगंधिता: वसायुक्त या तैलीय खाद्य पदार्थों का उपचयित होकर गंध व स्वाद बदलना।"
            ),
            importantFormulas = listOf(
                "CaO + H₂O → Ca(OH)₂ + ऊष्मा (बिना बुझा चूना + जल)",
                "C₆H₁₂O₆ + 6O₂ → 6CO₂ + 6H₂O + ऊर्जा (श्वसन)",
                "Fe + CuSO₄ → FeSO₄ + Cu (विस्थापन अभिक्रिया)"
            ),
            questions = listOf(
                QuestionAnswer(
                    id = "qs1",
                    questionHindi = "श्वसन को ऊष्माक्षेपी अभिक्रिया क्यों कहते हैं? समझाइए।",
                    answerHindi = "उत्तर: जीवित रहने के लिए हमें ऊर्जा की आवश्यकता होती है। जो भोजन हम खाते हैं, पाचन क्रिया में वह सरल पदार्थों में टूटता है (जैसे कार्बोहाइड्रेट से ग्लूकोज)। यह ग्लूकोज कोशिकाओं में उपस्थित ऑक्सीजन से मिलकर हमें ऊर्जा प्रदान करता है। क्योंकि इस अभिक्रिया में ऊर्जा मुक्त होती है, इसलिए श्वसन को ऊष्माक्षेपी अभिक्रिया कहते हैं।\nसमीकरण: C₆H₁₂O₆ + 6O₂ → 6CO₂ + 6H₂O + ऊर्जा",
                    marks = 2,
                    isPyq = true,
                    pyqYear = "2024"
                ),
                QuestionAnswer(
                    id = "qs2",
                    questionHindi = "रेडॉक्स (उपचयन-अपचयन) अभिक्रिया किसे कहते हैं? उदाहरण देकर स्पष्ट कीजिए।",
                    answerHindi = "उत्तर: वह रासायनिक अभिक्रिया जिसमें एक अभिकारक का उपचयन (ऑक्सीजन की वृद्धि या हाइड्रोजन का ह्रास) तथा दूसरे अभिकारक का अपचयन (ऑक्सीजन का ह्रास या हाइड्रोजन की वृद्धि) साथ-साथ होता है, उसे रेडॉक्स अभिक्रिया कहते हैं।\nउदाहरण: CuO + H₂ --(ऊष्मा)--> Cu + H₂O\nयहाँ CuO का Cu में अपचयन और H₂ का H₂O में उपचयन हो रहा है।",
                    marks = 3,
                    isPyq = true,
                    pyqYear = "2023"
                )
            ),
            quizQuestions = listOf(
                QuizQuestion(
                    id = "qzs1",
                    questionHindi = "चूने के पानी में कार्बन डाइऑक्साइड (CO₂) गैस प्रवाहित करने पर चूने के पानी का रंग कैसा हो जाता है?",
                    options = listOf("दूधिया (Milky)", "नीला", "लाल", "पीला"),
                    correctIndex = 0,
                    explanationHindi = "कैल्शियम कार्बोनेट (CaCO₃) के अवक्षेप बनने के कारण रंग दूधिया हो जाता है।"
                ),
                QuizQuestion(
                    id = "qzs2",
                    questionHindi = "चिप्स के पैकेट में विकृतगंधिता से बचाने के लिए कौन सी गैस भरी जाती है?",
                    options = listOf("ऑक्सीजन", "नाइट्रोजन", "हाइड्रोजन", "क्लोरीन"),
                    correctIndex = 1,
                    explanationHindi = "नाइट्रोजन एक अक्रिय गैस है जो वसा व तेल का उपचयन होने से रोकती है।"
                )
            )
        ),

        // Class 12 Physics Chapter 1
        Chapter(
            id = "ch_phy_12_1",
            subjectId = "physics",
            grade = ClassGrade.CLASS_12,
            chapterNumber = 1,
            titleHindi = "वैद्युत आवेश तथा क्षेत्र",
            titleEnglish = "Electric Charges and Fields",
            overview = "कक्षा 12वीं राजस्थान बोर्ड: कूलाम का नियम, विद्युत क्षेत्र रेखाएं, विद्युत द्विध्रुव एवं गाउस का नियम तथा इसके महत्वपूर्ण अनुप्रयोग।",
            keyPoints = listOf(
                "आवेश का क्वाण्टीकरण: q = ±ne (जहाँ n = 1, 2, 3... एवं e = 1.6 × 10⁻¹⁹ C)",
                "कूलाम का नियम: F = (1 / 4πε₀) × (|q₁q₂| / r²), जहाँ 1/4πε₀ = 9 × 10⁹ N·m²/C²",
                "विद्युत द्विध्रुव आघूर्ण: p = q × 2a (सदिश राशि, दिशा ऋण आवेश से धन आवेश की ओर)",
                "गाउस का नियम: किसी बंद पृष्ठ से गुजरने वाला कुल विद्युत फ्लक्स Φ = q_enclosed / ε₀"
            ),
            importantFormulas = listOf(
                "F = k (q₁ q₂ / r²)",
                "E (अक्षीय स्थिति) = (1 / 4πε₀) × (2p / r³)",
                "E (निरक्षीय स्थिति) = (1 / 4πε₀) × (p / r³)",
                "Φ = ∮ E · dA = q / ε₀"
            ),
            questions = listOf(
                QuestionAnswer(
                    id = "qp1",
                    questionHindi = "गाउस का नियम लिखिए तथा इसके अनुप्रयोग से अनंत लंबाई के एकसमान आवेशित सीधे तार के कारण विद्युत क्षेत्र की तीव्रता का व्यंजक ज्ञात कीजिए।",
                    answerHindi = "उत्तर: गाउस का नियम: निर्वात में किसी बंद पृष्ठ से गुजरने वाला कुल विद्युत फ्लक्स उस पृष्ठ द्वारा परिबद्ध कुल आवेश का 1/ε₀ गुना होता है, अर्थात Φ = ∮ E · dA = q/ε₀।\nअनंत लंबाई के तार हेतु व्यंजक: E = λ / (2πε₀r), जहाँ λ रेखीय आवेश घनत्व है तथा r तार से लम्बवत दूरी है।",
                    marks = 4,
                    isPyq = true,
                    pyqYear = "2024"
                )
            ),
            quizQuestions = listOf(
                QuizQuestion(
                    id = "qz_p1",
                    questionHindi = "विद्युत क्षेत्र की तीव्रता (E) का SI मात्रक क्या है?",
                    options = listOf("न्यूटन/कूलाम (N/C)", "जूल/कूलाम", "वोल्ट·मीटर", "एम्पीयर"),
                    correctIndex = 0,
                    explanationHindi = "E = F/q, अतः मात्रक न्यूटन प्रति कूलाम (N/C) या वोल्ट प्रति मीटर (V/m) होता है।"
                )
            )
        )
    )

    val modelPapers: List<ModelPaper> = listOf(
        ModelPaper(
            id = "mp_10_math_2026",
            titleHindi = "RBSE कक्षा 10 गणित मॉडल पेपर 2026 (ब्लूप्रिंट आधारित)",
            grade = ClassGrade.CLASS_10,
            subjectName = "गणित",
            year = "2026",
            totalMarks = 80,
            timeDuration = "3 घंटे 15 मिनट",
            description = "नए RBSE 2025-26 परीक्षा पैटर्न के अनुसार: वस्तुनिष्ठ (15 अंक), रिक्त स्थान (7 अंक), अतिलघुत्तरात्मक (10 अंक), लघुत्तरात्मक (24 अंक), दीर्घ उत्तरीय व निबंधात्मक प्रश्न (24 अंक)।"
        ),
        ModelPaper(
            id = "mp_10_sci_2026",
            titleHindi = "RBSE कक्षा 10 विज्ञान मॉडल प्रश्न पत्र 2026",
            grade = ClassGrade.CLASS_10,
            subjectName = "विज्ञान",
            year = "2026",
            totalMarks = 80,
            timeDuration = "3 घंटे 15 मिनट",
            description = "नामांकित चित्र, रासायनिक समीकरण, उपचयन-अपचयन एवं पर्यावरण आधारित प्रश्नोत्तर।"
        ),
        ModelPaper(
            id = "mp_12_phy_2026",
            titleHindi = "RBSE कक्षा 12 भौतिक विज्ञान ब्लूप्रिंट प्रश्न पत्र",
            grade = ClassGrade.CLASS_12,
            subjectName = "भौतिक विज्ञान",
            year = "2026",
            totalMarks = 56,
            timeDuration = "3 घंटे 15 मिनट",
            description = "थ्योरी 56 अंक + सेशनल 14 अंक + प्रैक्टिकल 30 अंक का संपूर्ण आधिकारिक विभाजन।"
        ),
        ModelPaper(
            id = "mp_12_chem_2026",
            titleHindi = "RBSE कक्षा 12 रसायन विज्ञान बोर्ड मॉडल पेपर",
            grade = ClassGrade.CLASS_12,
            subjectName = "रसायन विज्ञान",
            year = "2026",
            totalMarks = 56,
            timeDuration = "3 घंटे 15 मिनट",
            description = "नेम रिएक्शन, न्यूमेरिकल समस्याएं एवं कोऑर्डिनेशन कंपाउंड्स के महत्वपूर्ण प्रश्न।"
        ),
        ModelPaper(
            id = "mp_9_math_2026",
            titleHindi = "RBSE कक्षा 9 वार्षिक परीक्षा अभ्यास पत्र",
            grade = ClassGrade.CLASS_9,
            subjectName = "गणित",
            year = "2026",
            totalMarks = 100,
            timeDuration = "3 घंटे 15 मिनट",
            description = "संख्या पद्धति, ज्यामिति, क्षेत्रमिति और सांख्यिकी का संपूर्ण पुनरावलोकन पत्र।"
        )
    )

    val boardAlerts: List<BoardAlert> = listOf(
        BoardAlert(
            id = "ba1",
            title = "RBSE बोर्ड परीक्षा 2026: नया ब्लूप्रिंट व अंक योजना जारी",
            date = "12 अक्टूबर 2026",
            tag = "ब्लूप्रिंट",
            description = "माध्यमिक शिक्षा बोर्ड राजस्थान, अजमेर द्वारा कक्षा 10वीं एवं 12वीं की मुख्य बोर्ड परीक्षाओं हेतु विस्तृत अंक विभाजन व मॉडल प्रश्न पत्र पोर्टल पर जारी किए गए।",
            isImportant = true
        ),
        BoardAlert(
            id = "ba2",
            title = "कक्षा 9वीं से 12वीं: संशोधित पाठ्यक्रम (NCERT Based) उपलब्ध",
            date = "08 अक्टूबर 2026",
            tag = "सिलेबस",
            description = "RBSE ने सत्र 2025-26 के लिए NCERT आधारित नया सिलेबस जारी किया। HR Study RBSE ऐप में सभी चैप्टर्स नए पाठ्यक्रम के अनुरूप अद्यतन हैं।",
            isImportant = false
        ),
        BoardAlert(
            id = "ba3",
            title = "बोर्ड टॉपर्स की उत्तर पुस्तिकाएं (Answer Sheets) विश्लेषण",
            date = "28 सितंबर 2026",
            tag = "टॉपर टिप्स",
            description = "राजस्थान बोर्ड परीक्षा में पूरे अंक कैसे प्राप्त करें? टॉपर्स की कॉपियों के प्रेजेंटेशन और लिखने के तरीकों की मुख्य विशेषताएं देखें।",
            isImportant = false
        )
    )

    fun getSubjectsForGrade(grade: ClassGrade): List<Subject> {
        return subjects.filter { it.grades.contains(grade) }
    }

    fun getChaptersForSubjectAndGrade(subjectId: String, grade: ClassGrade): List<Chapter> {
        val list = chapters.filter { it.subjectId == subjectId && it.grade == grade }
        if (list.isNotEmpty()) return list
        // Generate placeholder chapter structure if not explicitly seeded
        return listOf(
            Chapter(
                id = "${subjectId}_${grade.name.lowercase()}_1",
                subjectId = subjectId,
                grade = grade,
                chapterNumber = 1,
                titleHindi = "अध्याय 1: आधारभूत अवधारणाएं एवं परिचय",
                titleEnglish = "Chapter 1: Foundations & Concepts",
                overview = "राजस्थान बोर्ड पाठ्यक्रम के अनुसार इस अध्याय के सभी मुख्य सूत्र, परिभाषाएं एवं हल प्रश्न यहाँ संकलित हैं।",
                keyPoints = listOf(
                    "बोर्ड परीक्षा में पूछे जाने वाले प्रमुख बिंदु",
                    "NCERT / RBSE पाठ्यपुस्तक के आधारभूत नियम",
                    "शॉर्ट नोट्स एवं त्वरित पुनरावलोकन सारांश"
                ),
                importantFormulas = listOf(
                    "महत्वपूर्ण नियम 1: सैद्धांतिक परिभाषा एवं अनुप्रयोग",
                    "नियम 2: प्रायोगिक एवं बोर्ड परीक्षा में बार-बार आने वाले सूत्र"
                ),
                questions = listOf(
                    QuestionAnswer(
                        id = "q_gen_1",
                        questionHindi = "इस अध्याय के मुख्य सिद्धांत का वर्णन कीजिए तथा बोर्ड परीक्षा में इसका महत्व बताइए।",
                        answerHindi = "उत्तर: यह अध्याय आगामी बोर्ड परीक्षाओं के लिए आधारभूत है। विस्तृत नोट्स व समाधान ऐप के अगले अपडेट में भी निरंतर जोड़े जा रहे हैं।",
                        marks = 2,
                        isPyq = true,
                        pyqYear = "2024"
                    )
                ),
                quizQuestions = listOf(
                    QuizQuestion(
                        id = "qz_gen_1",
                        questionHindi = "RBSE बोर्ड परीक्षा में अधिकतम अंक प्राप्त करने के लिए सबसे महत्वपूर्ण रणनीति क्या है?",
                        options = listOf("नियमित अभ्यास व मॉडल पेपर्स हल करना", "केवल परीक्षा से एक दिन पहले पढ़ना", "सूत्र याद न करना", "उत्तर बिना स्टेप्स के लिखना"),
                        correctIndex = 0,
                        explanationHindi = "नियमित पुनरावलोकन, ब्लूप्रिंट अनुसार तैयारी और हस्तलिखित नोट्स से उत्कृष्ट अंक प्राप्त होते हैं।"
                    )
                )
            ),
            Chapter(
                id = "${subjectId}_${grade.name.lowercase()}_2",
                subjectId = subjectId,
                grade = grade,
                chapterNumber = 2,
                titleHindi = "अध्याय 2: विस्तृत अध्ययन एवं अनुप्रयोग",
                titleEnglish = "Chapter 2: In-depth Application",
                overview = "राजस्थान बोर्ड के महत्वपूर्ण प्रश्नों का संग्रह एवं मॉडल उत्तर।",
                keyPoints = listOf(
                    "अतिलघुत्तरात्मक एवं लघुत्तरात्मक प्रश्नों की तैयारी",
                    "चित्र, ग्राफ एवं तालिका आधारित प्रश्न"
                ),
                importantFormulas = listOf("अध्याय 2 मुख्य सूत्र एवं समीकरण"),
                questions = listOf(
                    QuestionAnswer(
                        id = "q_gen_2",
                        questionHindi = "बोर्ड परीक्षा के दृष्टिगत इस अध्याय के 2 महत्वपूर्ण प्रश्नों के उत्तर लिखिए।",
                        answerHindi = "उत्तर: बोर्ड परीक्षा के प्रश्नों में मुख्य बिंदुओं को रेखांकित (underline) करना और स्पष्ट लिखावट आवश्यक है।",
                        marks = 3,
                        isPyq = false
                    )
                ),
                quizQuestions = listOf(
                    QuizQuestion(
                        id = "qz_gen_2",
                        questionHindi = "राजस्थान बोर्ड परीक्षा में समय प्रबंधन (Time Management) का सबसे अच्छा तरीका क्या है?",
                        options = listOf("प्रत्येक खंड के लिए समय निर्धारित करना", "किसी प्रश्न पर अत्यधिक समय लगाना", "प्रश्नों को अधूरा छोड़ना", "अंत में उत्तर न जांचना"),
                        correctIndex = 0,
                        explanationHindi = "3 घंटे 15 मिनट में से 15 मिनट प्रश्न पत्र पढ़ने व अंत के 15 मिनट उत्तर पुस्तिका जांचने के लिए रखें।"
                    )
                )
            )
        )
    }

    fun getChapterById(chapterId: String): Chapter? {
        val found = chapters.find { it.id == chapterId }
        if (found != null) return found
        // Check dynamic generated chapters
        for (sub in subjects) {
            for (g in sub.grades) {
                val match = getChaptersForSubjectAndGrade(sub.id, g).find { it.id == chapterId }
                if (match != null) return match
            }
        }
        return chapters.firstOrNull()
    }
}
