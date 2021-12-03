package com.test.products.presentation.ProductDetails

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.test.products.R
import com.test.products.databinding.ActivityProductDetailsBinding
import com.test.products.domain.models.ProductModel
import com.test.products.presentation.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDetailsActivity : BaseActivity<ActivityProductDetailsBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val model = intent?.extras?.getParcelable<ProductModel>("model")

        binding.productName.text = model?.productName
        binding.productPrice.text = "${model?.productPrice ?: 0}"

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun setBindingView(): ActivityProductDetailsBinding {
      return  ActivityProductDetailsBinding.inflate(layoutInflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
             android.R.id.home -> {
                this.finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }


}