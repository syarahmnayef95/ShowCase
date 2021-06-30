package com.trendyol.showcase.ui.tooltip

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import com.google.android.material.tabs.TabLayout
import com.trendyol.showcase.R
import com.trendyol.showcase.databinding.LayoutTooltipBinding
import com.trendyol.showcase.util.getShowcaseActivity
import kotlinx.android.synthetic.main.layout_tooltip.view.*

class TooltipView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding: LayoutTooltipBinding = DataBindingUtil.inflate(LayoutInflater.from(context),
        R.layout.layout_tooltip, rootView as ViewGroup, true)

    fun bind(tooltipViewState: TooltipViewState) {
        binding.imageViewTooltipClose.setOnClickListener {
            getShowcaseActivity()?.goNext()
        }

        binding.nextBtn.setOnClickListener{
            getShowcaseActivity()?.goNext()
        }
        binding.skipBtn.setOnClickListener{
            getShowcaseActivity()?.onBackPress(false)
        }
        binding.tooltipViewState = tooltipViewState


        for (i:Int in 1..getShowcaseActivity()?.getShowCaseCount()!! )
        binding.tabs.addTab(tabs.newTab())

        getShowcaseActivity()?.getCurrentIndex()?.let {
            binding.tabs.getTabAt(it)?.select()
            if (it>=2){
                binding.skipBtn.visibility= View.VISIBLE
            }

            if (it==getShowcaseActivity()?.getShowCaseCount()!!-1){
                binding.skipBtn.visibility= View.INVISIBLE
                binding.nextBtn.text="تم"

            }
        }

        binding.executePendingBindings()



    }
}
