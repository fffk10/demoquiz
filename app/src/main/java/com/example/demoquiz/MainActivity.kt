package com.example.demoquiz

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var countLabel: TextView
    private lateinit var questionLabel: TextView
    private lateinit var answerBtn1: Button
    private lateinit var answerBtn2: Button
    private lateinit var answerBtn3: Button
    private lateinit var answerBtn4: Button

    private lateinit var rightAnswer: String  // 正答
    private var rightAnswerCount: Int = 0  // 正答数
    private var quizCount: Int = 1  // 何問目かをカウント

    var quizArray: ArrayList<ArrayList<String>> = ArrayList()

    // 問題文の配列を空文字で初期化
    // {"問題文", "正解", "選択肢1", "選択肢2", "選択肢3"}
    var quizData =
            arrayOf(
                    arrayOf("この中で主人公は？", "エクレール・ファロン", "ユウナ", "グラディオラス", "カイン・ハイウィンド"),
                    arrayOf("主人公がヴァンの作品は？", "XII", "VII", "IV", "IX"),
                    arrayOf("リディアが覚えるのを躊躇する魔法は？", "ファイア", "ブリザド", "サンダー", "ケアル"),
                    arrayOf("ティーダはどの競技の選手？", "ブリッツボール", "ゲートボール", "コロッセウム", "カードゲーム"),
                    arrayOf("ガーネットの本名は？", "セーラ", "ローザ", "エーコ", "リノア"))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        countLabel = findViewById(R.id.countLabel)
        questionLabel = findViewById(R.id.questionLabel)
        answerBtn1 = findViewById(R.id.answerBtn1)
        answerBtn2 = findViewById(R.id.answerBtn2)
        answerBtn3 = findViewById(R.id.answerBtn3)
        answerBtn4 = findViewById(R.id.answerBtn4)

        // quizDataからクイズ出題用のquizArrayを作成する
        // 新しい配列を準備
        for(i in quizData) {

            // 新しいArrayListを準備
            var tmpArray: ArrayList<String> = ArrayList()

            for(j in 0..4) {
                tmpArray.add(i[j])
            }

            // tmpArrayをquizArrayに追加
            quizArray.add(tmpArray)
        }

        showNextQuiz()
    }

    public fun showNextQuiz() {
        // クイズカウントラベル更新
        countLabel.text = getString(R.string.count_label, quizCount)

        // ランダムな数字を取得
        val random = (0..5).random()
        var quiz: ArrayList<String> = quizArray.get(random)

        // 問題文を表示
        questionLabel.text = quiz.get(0)

        //配列から問題文を削除
        quiz.removeAt(0)

        // 正解と選択肢を3つをシャッフル
        quiz.shuffle()

        // 回答ボタンに正解と選択肢3つを表示
        answerBtn1.text = quiz.get(0)
        answerBtn2.text = quiz.get(1)
        answerBtn3.text = quiz.get(2)
        answerBtn4.text = quiz.get(3)

        // このクイズをquizArrayから削除
        quizArray.removeAt(random)
    }
}