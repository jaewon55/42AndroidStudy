package com.example.week7_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.week7_2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.recycler.layoutManager = GridLayoutManager(this, 2)
        val items = mutableListOf<Product>()
        items.add(Product(R.drawable.clothes1, "상품A", "80,000", "명절 기획상품"))
        items.add(Product(R.drawable.clothes2, "상품B", "160,000", "특가상품"))
        items.add(Product(R.drawable.clothes3, "상품C", "70,000", "특가상품"))
        items.add(Product(R.drawable.clothes4, "상품D", "250,000", "특가상품"))
        items.add(Product(R.drawable.clothes5, "상품E", "1,000,000", "신상"))
        items.add(Product(R.drawable.clothes6, "상품F", "2,500,000", "신상"))
        items.add(Product(R.drawable.clothes7, "상품G", "3,500,000", "한정판"))
        items.add(Product(R.drawable.clothes8, "상품H", "400,000", "특가상품"))
        val adapter = MyAdapter(items)
        adapter.listener = object : OnProductItemClickListener {
            override fun onItemClick(holder: MyAdapter.ViewHolder, view: View, position: Int) {
                val item = adapter.items[position]
                Toast.makeText(
                    applicationContext,
                    "${item.name}: ${item.price}원, ${item.explain}",
                    Toast.LENGTH_SHORT
                )
                    .show()
            }
        }
        binding.recycler.adapter = adapter
    }
}