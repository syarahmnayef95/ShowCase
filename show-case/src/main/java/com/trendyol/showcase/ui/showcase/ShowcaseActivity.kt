package com.trendyol.showcase.ui.showcase

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.trendyol.showcase.showcase.ShowcaseManager
import com.trendyol.showcase.showcase.ShowcaseModel
import com.trendyol.showcase.util.LocalizationHelper


class ShowcaseActivity : AppCompatActivity() {
    private lateinit var showCaseList:ArrayList<ShowcaseModel>
     private var showCaseIndex:Int=0


    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(LocalizationHelper.onAttach(newBase))
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(android.R.anim.fade_in, 0)

        LocalizationHelper.setActivityLocal(this, window)


        showCaseList = intent?.extras?.getParcelableArrayList<ShowcaseModel>(BUNDLE_KEY) as ArrayList<ShowcaseModel>
        showCaseList[0]?.let {model->
            val layout = ShowcaseView(this).apply {
                this.showcaseModel = model
                showCaseIndex++
            }
            setContentView(layout)

        }

    }

    fun onBackPress(isHighlightClicked: Boolean = false) {
        setResult(Activity.RESULT_OK, Intent().putExtra(ShowcaseManager.HIGHLIGHT_CLICKED, isHighlightClicked))
        finish()
        overridePendingTransition(0, android.R.anim.fade_out)
    }

    fun goNext(){
        if (showCaseList.size-1>=showCaseIndex) {
            showCaseList[showCaseIndex].let {
                val layout=   ShowcaseView(this).apply {
                    this.showcaseModel = it
                    showCaseIndex++
                }

                setContentView(layout)
            }
        }else{
            onBackPressed()
        }
    }

    override fun onResume() {
        super.onResume()

        LocalizationHelper.setActivityLocal(this, window)
    }

    fun getCurrentIndex():Int=showCaseIndex
    fun getShowCaseCount():Int?=showCaseList.size

    override fun onBackPressed() {
        onBackPress()
    }

    companion object {

        const val BUNDLE_KEY = "bundle_key"
    }
}
