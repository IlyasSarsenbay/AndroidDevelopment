package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.MenuBinding
import java.util.Locale

class Menu : AppCompatActivity(), MyAdapter.OnItemClickListener {
    private lateinit var recyclerView: RecyclerView
    private lateinit var binding: MenuBinding
    private lateinit var dataList: ArrayList<Item>
    private lateinit var adapter: MyAdapter
    private lateinit var searchEditText: AppCompatEditText
    private lateinit var searchButton: AppCompatButton
    private lateinit var searchList: ArrayList<Item>
    private lateinit var nameList: Array<String>
    private lateinit var descList: Array<String>
    private lateinit var imgList: IntArray
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        nameList = arrayOf("Вау! Кебаб", "Пепперони с грибами", "Ветчина и огурчики", "Миксик", "Пицца Жюльен", "Сырная", "Пепперони фреш")
        descList = arrayOf("Мясо говядины, соус ранч," +
                " сыр моцарелла, сладкий перец, томаты, красный лук" +
                " и фирменный томатный соус","Пикантная пепперони, моцарелла, шампиньоны, соус альфредо","Соус ранч, моцарелла, ветчина из цыпленка, маринованные огурчики, красный лук","Пицца четвертинками с ветчиной из цыпленка, томатами, брынзой, моцареллой," +
                "        фирменным соусом альфредо." +
                " Набор юного садовода в подарок","Цыпленок, шампиньоны, ароматный грибной соус, лук, сухой чеснок," +
                " моцарелла, смесь сыров чеддер и пармезан, фирменный соус альфредо","Моцарелла, сыры чеддер и пармезан, соус альфредо","Пикантная пепперони, мно-о-ого моцареллы, томаты и томатный соус")
        imgList = intArrayOf(R.drawable.kebab,
            R.drawable.pepe,
            R.drawable.vet,
            R.drawable.mix,
            R.drawable.zh,
            R.drawable.ch,
            R.drawable.fr)
        dataList = arrayListOf<Item>()
        searchList = arrayListOf<Item>()
        adapter = MyAdapter(searchList)
        adapter.setOnItemClickListener(this)
        recyclerView.adapter = adapter

        getData()

        searchEditText = findViewById(R.id.Search)
        searchButton = findViewById(R.id.searchButton)

        searchEditText.clearFocus()
        searchList.clear()
        searchList.addAll(dataList)
        searchButton.setOnClickListener {
            adapter.filter(searchEditText.text.toString())
        }
    }

    private fun getData() {
        for (i in nameList.indices) {
            val dataClass = Item(nameList[i],descList[i],imgList[i])
            dataList.add(dataClass)
        }
        searchList.addAll(dataList)
        adapter.notifyDataSetChanged()
    }

    override fun onItemClick(item: Item) {
        when (item.name) {
            "Вау! Кебаб" -> {
                val intent = Intent(this, Kebab::class.java)
                startActivity(intent)
            }
            "Пепперони с грибами" -> {
                val intent = Intent(this, Pepe::class.java)
                startActivity(intent)
            }
            "Ветчина и огурчики" -> {
                val intent = Intent(this, Vet::class.java)
                startActivity(intent)
            }
            "Миксик" -> {
                val intent = Intent(this, Mix::class.java)
                startActivity(intent)
            }
            "Пицца Жюльен" -> {
                val intent = Intent(this, Zh::class.java)
                startActivity(intent)
            }
            "Сырная" -> {
                val intent = Intent(this, Ch::class.java)
                startActivity(intent)
            }
            "Пепперони фреш" -> {
                val intent = Intent(this, Fr::class.java)
                startActivity(intent)
            }
        }
}
}