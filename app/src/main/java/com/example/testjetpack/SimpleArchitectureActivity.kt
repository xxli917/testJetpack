package com.example.testjetpack

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.testjetpack.model.Product
import com.example.testjetpack.ui.ProductFragment
import com.example.testjetpack.ui.ProductListFragment

class SimpleArchitectureActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simple_architecture)
        if (savedInstanceState == null) {
            //进入显示ProductListFragment
            val productListFragment = ProductListFragment();
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, productListFragment, ProductListFragment.TAG).commit()
        }
    }

    fun show(product: Product) {
        val productFragment: ProductFragment = ProductFragment.forProduct(product.id)

        supportFragmentManager
            .beginTransaction()
            .addToBackStack("product")
            .replace(
                R.id.fragment_container,
                productFragment, null
            ).commit()
    }
}