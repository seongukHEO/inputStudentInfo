package kr.co.lion.android01.ex10_message

import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.lion.android01.ex10_message.databinding.ActivityThirdBinding

class ThirdActivity : AppCompatActivity() {

    lateinit var activityThirdBinding: ActivityThirdBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityThirdBinding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(activityThirdBinding.root)

        activityThirdBinding.apply {
            printStudentInfoText.apply {
                var info1 = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
                    intent?.getParcelableExtra("obj1", InfoClass::class.java)
                }else{
                    intent?.getParcelableExtra("obj1")
                }


                text = "이름 : ${info1?.name}\n"
                append("학년 : ${info1?.grade}학년\n")
                append("\n")
                append("국어 점수 : ${info1?.kor}점\n")
                append("영어 점수 : ${info1?.eng}점\n")
                append("수학 점수 : ${info1?.math}점\n")
                append("\n")
                var total = info1!!.kor + info1!!.eng + info1!!.math

                append("총점 : ${total}점\n")
                var avg = total / 3
                append("평균 : ${avg}점\n")
            }

            printStudentToolBar.apply {

                //타이틀 설정
                title = "학생 정보 보기"
                setTitleTextColor(Color.BLACK)

                //툴바에 뒤로가기 아이콘 형성
                setNavigationIcon(R.drawable.arrow_back_24px)
                //아이콘을 클릭했을 때
                setNavigationOnClickListener {
                    finish()
                }

            }
        }
    }

}