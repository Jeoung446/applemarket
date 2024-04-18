package com.example.applemarket

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.applemarket.databinding.ActivitySecondBinding
import java.text.DecimalFormat

class SecondActivity : AppCompatActivity() {
    lateinit var binding: ActivitySecondBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val item: MyItem? = intent.getParcelableExtra("item")
        //아이디
        val id = binding.idTextView
        // 이미지
        val image = binding.imageView
        // 설명
        val product = binding.productTextView
        // 주소
        val adress = binding.adressTextView
        // 상품명
        val name = binding.nameTextView
        // 가격
        val price = binding.priceTextView
        // 백버튼
        val back = binding.backImageView
        back.setOnClickListener {
            finish()
        }
        val heart = binding.goodImageView
        var good = false
        // 좋아요 누를시 숫자 1 증가
        heart.setOnClickListener {
            if (!good) {
                heart.setImageResource(R.drawable.favorite_red)
            } else {
                heart.setImageResource(R.drawable.favorite_24px)
            }

            good = !good
        }
        // 원 표시후 세자리마다 ,체크
        val dec = DecimalFormat("#,### 원")
        // scope함수로 코드의 길이를 줄임
        item?.let {
            val aIcon = it.aIcon
            image.setImageResource(aIcon)
            val aName = it.aName
            name.text = aName
            val aAdress = it.aAdress
            adress.text = aAdress
            val aPrice = dec.format(it.aPrice.toInt())
            price.text = aPrice
            val aProuct = it.aProduct
            product.text = aProuct
            val aId = it.aId
            id.text = aId
        }
    }
}