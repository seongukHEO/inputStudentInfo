package kr.co.lion.android01.ex10_message

import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kr.co.lion.android01.ex10_message.databinding.ActivityMainBinding
import kr.co.lion.android01.ex10_message.databinding.RowBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    //SeondActivity에서 사용할 런처
    lateinit var secondActivitylauncher:ActivityResultLauncher<Intent>

    //ThirdActivity에서 사용할 런쳐
    lateinit var thirdActivitylauncher:ActivityResultLauncher<Intent>

    var studentList = mutableListOf<InfoClass>()

    override fun onCreate(savedInstanceState: Bundle?) {



        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        initData()
        inittoolBar()
        initView()
        setData()


    }

    //초기 데이터 세팅
    fun initData(){
        var contract = ActivityResultContracts.StartActivityForResult()
        secondActivitylauncher = registerForActivityResult(contract){
            if (it.resultCode == RESULT_OK){
                if (it.data != null){
                    //버전별로 나누기
                    var info1 = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
                        it?.data!!.getParcelableExtra("info1", InfoClass::class.java)
                    }else{
                        it?.data!!.getParcelableExtra("info1")
                    }
                    studentList.add(info1!!)
                    activityMainBinding.recyclerView.adapter?.notifyDataSetChanged()
                }

            }

        }
        var contract2 = ActivityResultContracts.StartActivityForResult()
        thirdActivitylauncher = registerForActivityResult(contract2){

        }


    }

    //툴바 세팅
    fun inittoolBar(){
        activityMainBinding.apply {
            maintoolBar.apply {
                //타이틀 설정
                title = "학생 정보 관리"
                setTitleTextColor(Color.BLACK)

                //main_menu와 연결
                inflateMenu(R.menu.main_menu)

                //메뉴에 있는 버튼을 누를때?
                setOnMenuItemClickListener {
                    when(it.itemId){
                        R.id.goInputStudentData -> {
                            var newIntent = Intent(this@MainActivity, SecondActivity::class.java)
                            secondActivitylauncher.launch(newIntent)
                        }
                    }

                    true
                }

            }
        }

    }

    //초기에 화면에 보여지는 것
    fun initView(){
        activityMainBinding.apply {
            recyclerView.apply {
                //어댑터 설정
                adapter = ReCyclerViewAdapter()
                //레이아웃 설정
                layoutManager = LinearLayoutManager(this@MainActivity)
            }

        }

    }
    //실행
    fun setData(){

    }

    //adapter클래스 생성
    inner class ReCyclerViewAdapter: RecyclerView.Adapter<ReCyclerViewAdapter.ViewHolderClass>(){

        //viewHolderClass 생성
        inner class ViewHolderClass(rowBinding: RowBinding): RecyclerView.ViewHolder(rowBinding.root){
            var rowBinding:RowBinding

            init {
                this.rowBinding = rowBinding

                //리사이클러뷰를 클릭했을 때
                this.rowBinding.root.setOnClickListener {
                    var newIntent = Intent(this@MainActivity, ThirdActivity::class.java)
                    newIntent.putExtra("obj1", studentList[adapterPosition])
                    thirdActivitylauncher.launch(newIntent)
                }

                //가로 세로 길이 맞추기
                this.rowBinding.root.layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )

                //리사이클러뷰를 길게 누르면 삭제 버튼이 뜬다
                this.rowBinding.root.setOnCreateContextMenuListener { menu, v, menuInfo ->
                    menu?.setHeaderTitle("${adapterPosition}번째 항목을 삭제하시겠습니까?")
                    menuInflater.inflate(R.menu.recyclerview_menu, menu)

                    menu?.findItem(R.id.delect)?.setOnMenuItemClickListener {
                        activityMainBinding.apply {
                            studentList.removeAt(adapterPosition)
                            recyclerView.adapter?.notifyDataSetChanged()
                        }
                        true
                    }
                }

            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
            //viewBinding 설정
            var rowBinding = RowBinding.inflate(layoutInflater)
            //viewHolder 설정
            var viewHolderClass = ViewHolderClass(rowBinding)
            return viewHolderClass
        }

        override fun getItemCount(): Int {
            return studentList.size
        }

        override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
            holder.rowBinding.nameTextView.text = studentList[position].name
            holder.rowBinding.gradeTextView.text = "${studentList[position].grade}학년"
        }
    }










}