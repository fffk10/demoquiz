package com.example.demoquiz

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var countLabel: TextView
    private lateinit var questionLabel: TextView
    private lateinit var answerBtn1: Button
    private lateinit var answerBtn2: Button
    private lateinit var answerBtn3: Button
    private lateinit var answerBtn4: Button

    private var rightAnswer: String = "null" // 正答
    private var rightAnswerCount: Int = 0  // 正答数
    private var quizCount: Int = 1  // 何問目かをカウント
    private val  QUIZ_COUNT = 5 // 出題する問題数

    var quizArray: ArrayList<ArrayList<String>> = ArrayList()

    // 問題文の配列を空文字で初期化
    // {"問題文", "正解", "選択肢1", "選択肢2", "選択肢3"}
    private var quizData =
            arrayOf(
                    arrayOf("この中で主人公は？", "エクレール・ファロン", "ユウナ", "グラディオラス", "カイン・ハイウィンド"),
                    arrayOf("主人公がヴァンの作品は？", "XII", "VII", "IV", "IX"),
                    arrayOf("リディアが覚えるのを躊躇する魔法は？", "ファイア", "ブリザド", "サンダー", "ケアル"),
                    arrayOf("ティーダはどの競技の選手？", "ブリッツボール", "ゲートボール", "コロッセウム", "カードゲーム"),
                    arrayOf("ガーネットの本名は？", "セーラ", "ローザ", "エーコ", "リノア"),
                    arrayOf("ティファが経営する酒場の名前は？", "セブンスヘブン", "シックスヘブン", "フォースヘブン", "セカンドヘブン"),
                    arrayOf("この中でセラの婚約者は？", "スノウ", "アーロン", "スタイナー", "スコール"),
                    arrayOf("FFIX 眠らない町は？", "トレノ", "ダリ", "アレクサンドリア", "リンドブルム"),
                    arrayOf("「この物語の主人公さ」このセリフを言ったのは？", "バルフレア", "クラウド", "バッツ", "ルーネス"),
                    arrayOf("レナの姉は？", "ファリス", "リノア", "ティナ", "クルル")
                    )

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
            val tmpArray: ArrayList<String> = ArrayList()

            for(j in 0..4) {
                tmpArray.add(i[j])
            }

            // tmpArrayをquizArrayに追加
            quizArray.add(tmpArray)
        }

        showNextQuiz()
    }

    private fun showNextQuiz() {
        // クイズカウントラベル更新
        countLabel.text = getString(R.string.count_label, quizCount)

        // ランダムな数字を取得
        val random = (0..quizData.size).random()
        val quiz = quizArray[random]

        // 問題文を表示
        questionLabel.text = quiz[0]

        // 正解をrightAnswerにセット
        rightAnswer = quiz[1]

        //配列から問題文を削除
        quiz.removeAt(0)

        // 正解と選択肢を3つをシャッフル
        quiz.shuffle()

        // 回答ボタンに正解と選択肢3つを表示
        answerBtn1.text = quiz[0]
        answerBtn2.text = quiz[1]
        answerBtn3.text = quiz[2]
        answerBtn4.text = quiz[3]

        // このクイズをquizArrayから削除
        quizArray.removeAt(random)
    }

    public fun checkAnswer(view: View) {
        // どの回答ボタンが押されたか
        val answerBtn: Button = findViewById(view.id)
        val btnText = answerBtn.text.toString()

        lateinit var alertTitle: String
        if (btnText == rightAnswer) {
            alertTitle = "Good!"
            rightAnswerCount++
        } else {
            alertTitle = "Bad.."
        }

        // ダイアログを作成
        val builder = AlertDialog.Builder(this)
        builder.setTitle(alertTitle)
        builder.setMessage("A.$rightAnswer")
        builder.setPositiveButton("OK") { dialogInterface, i ->
            if (quizCount === QUIZ_COUNT) {
                // 結果画面へ移動
            } else {
                quizCount++
                showNextQuiz()
            }
        }
        builder.setCancelable(false)
        builder.show()
    }
}