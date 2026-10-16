package com.example.model

enum class ClassGrade(val displayName: String, val shortName: String, val isBoardExam: Boolean) {
    CLASS_9("कक्षा 9वीं", "कक्षा 9", false),
    CLASS_10("कक्षा 10वीं (Board)", "कक्षा 10", true),
    CLASS_11("कक्षा 11वीं", "कक्षा 11", false),
    CLASS_12("कक्षा 12वीं (Board)", "कक्षा 12", true)
}

enum class StudyCategory(val titleHindi: String, val iconName: String) {
    TEXTBOOKS("पाठ्यपुस्तकें", "menu_book"),
    NOTES("हस्तलिखित नोट्स", "edit_note"),
    MODEL_PAPERS("मॉडल पेपर्स", "description"),
    IMP_QUESTIONS("महत्वपूर्ण प्रश्न", "help_center"),
    QUIZ("ऑनलाइन क्विज़", "quiz"),
    BOARD_UPDATES("बोर्ड अपडेट्स", "campaign")
}

data class Subject(
    val id: String,
    val nameHindi: String,
    val nameEnglish: String,
    val iconName: String,
    val primaryColorHex: Long,
    val grades: List<ClassGrade>,
    val totalChapters: Int,
    val description: String
)

data class QuestionAnswer(
    val id: String,
    val questionHindi: String,
    val answerHindi: String,
    val marks: Int,
    val isPyq: Boolean = false,
    val pyqYear: String = "2024"
)

data class QuizQuestion(
    val id: String,
    val questionHindi: String,
    val options: List<String>,
    val correctIndex: Int,
    val explanationHindi: String
)

data class Chapter(
    val id: String,
    val subjectId: String,
    val grade: ClassGrade,
    val chapterNumber: Int,
    val titleHindi: String,
    val titleEnglish: String,
    val overview: String,
    val keyPoints: List<String>,
    val importantFormulas: List<String>,
    val questions: List<QuestionAnswer>,
    val quizQuestions: List<QuizQuestion>
)

data class ModelPaper(
    val id: String,
    val titleHindi: String,
    val grade: ClassGrade,
    val subjectName: String,
    val year: String,
    val totalMarks: Int = 80,
    val timeDuration: String = "3 घंटे 15 मिनट",
    val description: String
)

data class BoardAlert(
    val id: String,
    val title: String,
    val date: String,
    val tag: String,
    val description: String,
    val isImportant: Boolean = false
)
