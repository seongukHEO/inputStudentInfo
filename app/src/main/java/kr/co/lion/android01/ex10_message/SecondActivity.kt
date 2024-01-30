package kr.co.lion.android01.ex10_message

import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.isEmpty
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import kr.co.lion.android01.ex10_message.databinding.ActivitySecondBinding
import java.lang.NumberFormatException

class SecondActivity : AppCompatActivity() {

    lateinit var activitySecondBinding: ActivitySecondBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activitySecondBinding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(activitySecondBinding.root)
        initData()
        initToolBar()
        setState()



    }
    fun initData(){


    }
    fun initToolBar(){
        activitySecondBinding.apply {
            studentInfotoolBar.apply {
                //타이틀 설정
                title = "학생 정보 입력"
                setTitleTextColor(Color.BLACK)

                setNavigationIcon(R.drawable.arrow_back_24px)

                setNavigationOnClickListener {
                    finish()
                }

                inflateMenu(R.menu.input_menu)

                setOnMenuItemClickListener {
                    when (it.itemId) {
                        R.id.inputstudentmenu -> {

                            var name = nameTextField.text.toString().trim()
                            var gradetext = gradeTextField.text.toString().trim()
                            var kortext = korTextField.text.toString().trim()
                            var engtext = engTextField.text.toString().trim()
                            var mathtext = mathTextField.text.toString().trim()

                            if (name.isEmpty() || gradetext.isEmpty() || kortext.isEmpty() || engtext.isEmpty() || mathtext.isEmpty()) {
                                var builder =
                                    MaterialAlertDialogBuilder(this@SecondActivity).apply {
                                        //타이틀
                                        setTitle("입력 오류")
                                        //메시지
                                        setMessage("정보를 알맞게 입력하시오")
                                        //긍정 버튼
                                        setPositiveButton("확인") { dialogInterface: DialogInterface, i: Int ->

                                        }
                                        //부정 버튼
                                        setNegativeButton("취소") { dialogInterface: DialogInterface, i: Int ->

                                        }
                                    }
                                builder.show()
                            } else {
                                try {


                                    var grade = gradetext.toInt()
                                    var kor = kortext.toInt()
                                    var eng = engtext.toInt()
                                    var math = mathtext.toInt()

                                    var info1 = InfoClass(name, grade, kor, eng, math)
                                    var newIntentt = Intent()
                                    newIntentt.putExtra("info1", info1)

                                    setResult(RESULT_OK, newIntentt)
                                    finish()
                                }catch (e: Exception){

                                }
                            }

                        }

                    }

         true
      }

  }
 }


    }
    fun initView() {

    }
    fun setState(){

    }
}







































