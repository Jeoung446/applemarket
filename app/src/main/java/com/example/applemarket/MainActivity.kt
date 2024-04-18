package com.example.applemarket

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Intent
import android.content.res.ColorStateList
import android.os.Build
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.applemarket.databinding.ActivityMainBinding


@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {
    // binding을 쓰기 위한 사전 작업
    lateinit var binding: ActivityMainBinding

    @SuppressLint("ResourceAsColor", "ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // 알림 설정
        binding.notificationImageView.setOnClickListener {
            notification()
        }

        // 데이터 원본 준비
        val dataList = mutableListOf<MyItem>()
        dataList.add(
            MyItem(
                R.drawable.sample1, "산지 한달된 선풍기 팝니다", "서울 서대문구 창천동", "10000",
                "이사가서 필요가 없어졌어요 급하게 내놓습니다", "대현동", 13
            )
        )
        dataList.add(
            MyItem(
                R.drawable.sample2, "김치냉장고", "인천 계양구 귤형동", "20000",
                "이사로인해 내놔요", "안마담", 8
            )
        )
        dataList.add(
            MyItem(
                R.drawable.sample3, "샤넬 카드지갑", "수성구 밤어동", "10000", "고퀄지갑이구요 " +
                        "\n사용감이 있어서 싸게 내어둡니다", "코코유", 23
            )
        )
        dataList.add(
            MyItem(
                R.drawable.sample4, "금고", "해운대구 우제2동", "10000", "금고 떼서 가져가야함 " +
                        "\n대우월드마크센텀 미국이주관계로 싸게 팝니다", "Nicole", 14
            )
        )
        dataList.add(
            MyItem(
                R.drawable.sample5, "갤럭시 Z플립3", "연제구 연제제8동", "150000", "갤럭시 Z플립3 그린 팝니다" +
                        "\n항시 케이스 씌워서 썻고 필름 한장챙겨드립니다", "절명", 22
            )
        )
        dataList.add(
            MyItem(
                R.drawable.sample6,
                "프라다 복조리백", "수원시 영통구 원천동", "50000", "까임 오염없고 상태 깨끗합니다 " +
                        "정품여부모름", "미니멀하게", 25
            )
        )
        dataList.add(
            MyItem(
                R.drawable.sample7,
                "울산 동해오션뷰 60평 복층 펜트하우스 1일 숙박권 펜션 힐링 숙소 별장",
                "남구 옥동",
                "150000",
                "울산 동해바다뷰 60평 복층 펜트하우스 1일 숙박권" +
                        "\n(에어컨이 없기에 낮은 가격으로 변경했으며 8월 초 가장 더운날 다녀가신 분 경우 시원했다고 잘 지내다 가셨습니다)" +
                        "\n1. 인원: 6명 기준입니다. 1인 10,000원 추가요금" +
                        "\n2. 장소: 북구 블루마시티, 32-33층" +
                        "\n3. 취사도구, 침구류, 세면도구, 드라이기 2개, 선풍기 4대 구비" +
                        "\n4. 예약방법: 예약금 50,000원 하시면 저희는 명함을 드리며 입실 오전 잔금 입금하시면 저희는 동.호수를 알려드리며 고객님은 예약자분 신분증 앞면 주민번호 뒷자리 가리시거나 지우시고 문자로 보내주시면 저희는 카드키를 우편함에 놓아 둡니다." +
                        "\n5. 33층 옥상 야외 테라스 있음, 가스버너 있음\n6. 고기 굽기 가능\n7. 입실 오후 3시, 오전 11시 퇴실, 정리, 정돈 , 밸브 잠금 부탁드립니다." +
                        "\n8. 층간소음 주의 부탁드립니다." +
                        "\n9. 방3개, 화장실3개, 비데 3개\n10. 저희 집안이 쓰는 별장입니다.",
                "굿리치",
                142
            )
        )
        dataList.add(
            MyItem(
                R.drawable.sample8,
                "샤넬 탑핸들 가방",
                "동래구 온천제2동",
                "180000",
                "3년전에 사서 한번 사용하고 그대로 둔 상태입니다. 요즘 사용은 안해봤습니다." +
                        "\n 그래서 저렴하게 내 놓습니다. 중고라 반품은 어렵습니다.",
                "난쉽",
                31
            )
        )
        // recyclerview adapter 연결
        binding.recyclerView.adapter = MyAdapter(dataList)

        val adapter = MyAdapter(dataList)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        // 구분선 연결
        val dividerItemDecoration =
            DividerItemDecoration(binding.recyclerView.context, DividerItemDecoration.VERTICAL)
        binding.recyclerView.addItemDecoration(dividerItemDecoration)

        adapter.itemClick = object : MyAdapter.ItemClick {
            override fun onClick(view: View, position: Int) {
                val item = dataList[position]
                val intent = Intent(this@MainActivity, SecondActivity::class.java).apply {
                    putExtra("item", item)
                }
                startActivity(intent)
            }
        }
        // spinner adapter 연결
        binding.spinnerView.adapter = ArrayAdapter.createFromResource(
            this,
            R.array.apple,
            android.R.layout.simple_list_item_1
        )

        // addTextChageListener과 비슷한 기능
        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                // 최상단일 경우 false 값 리턴
                // -1은 최상단을 의미한다
                val context = recyclerView.context
                if (!binding.recyclerView.canScrollVertically(-1)
                    && newState == RecyclerView.SCROLL_STATE_IDLE
                ) {
                    binding.upFloatingButton.visibility = View.GONE
                    onColor()
                } else {
                    binding.upFloatingButton.visibility = View.VISIBLE
                    val colorG = ContextCompat.getColor(context, R.color.gray)
                    binding.upFloatingButton.backgroundTintList = ColorStateList.valueOf(colorG)
                }
            }
        })

        binding.upFloatingButton.setOnTouchListener { v, event ->
            val context = v.context
            when (event.action) {
                // 손가락이 화면에 닿는 순간 발생하는 이벤트
                MotionEvent.ACTION_DOWN -> {
                    onColor()
                    true
                }
                // 손가락이 화면에서 떨어지는 순간 발생하는 이벤트
                MotionEvent.ACTION_UP -> {
                    // 최상단으로 이동
                    binding.recyclerView.smoothScrollToPosition(0)
                    val fadeIn = AnimationUtils.loadAnimation(context, R.anim.fade_in)
                    binding.upFloatingButton.startAnimation(fadeIn)
                    onColor()
                    true
                }
                else -> false
            }
        }
    }

    fun onColor() {
        val colorB = ContextCompat.getColor(this, R.color.blue)
        binding.upFloatingButton.backgroundTintList = ColorStateList.valueOf(colorB)
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() { // 뒤로가기 누르면 다이얼로그 생성
        val builder = AlertDialog.Builder(this)
        builder.setTitle("종료할까요?") // 다이얼로그 제목
        builder.setCancelable(false) // 다이얼로그 화면 밖 터치 방지
        builder.setIcon(R.mipmap.ic_launcher)
        builder.setPositiveButton("예") { _, which -> super.onBackPressed() } // 선택시 종료
        builder.setNegativeButton("아니요") { _, _ -> } // 선택시 그냥 취소
        builder.show() // 다이얼로그 보이기
    }

    fun notification() {
        val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        val builder: NotificationCompat.Builder

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // 채널 생성
            val channerId = "one-channel"
            val channelName = "My Channel One"
            val channel =
                NotificationChannel(channerId, channelName, NotificationManager.IMPORTANCE_DEFAULT)
            // channel.description = "My Channel One Desc" // 채널 설명
            channel.setShowBadge(true) // 배지표시
            // 채널을 notificationManager에 등록
            manager.createNotificationChannel(channel)
            // 채널 id로 notificationcompat.builder 생성
            builder = NotificationCompat.Builder(this, channerId)
        } else {
            builder = NotificationCompat.Builder(this)
        }
        builder.run {
            builder.setSmallIcon(R.mipmap.ic_launcher) // 스몰 아이콘
            builder.setContentTitle("사과마켓") // 타이틀
            builder.setContentText("키워드 알림") // 내용
            setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText(
                        "이것은 긴텍스트 샘플입니다. 아주 긴 텍스트를 쓸때는 여기다 하면 됩니다.이것은 긴텍스트 샘플입니다. " +
                                "아주 긴 텍스트를 쓸때는 여기다 하면 됩니다.이것은 긴텍스트 샘플입니다. 아주 긴 텍스트를 쓸때는 여기다 하면 됩니다."
                    )
            )// 긴 내용
        }

        manager.notify(11, builder.build())
    }

}

