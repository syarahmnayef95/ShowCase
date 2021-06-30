package com.trendyol.showcase.showcase

import android.graphics.Rect
import android.graphics.RectF
import android.os.Parcelable
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.annotation.ColorInt
import androidx.annotation.IntRange
import androidx.annotation.StyleRes
import com.trendyol.showcase.ui.showcase.HighlightType
import com.trendyol.showcase.ui.tooltip.ArrowPosition
import com.trendyol.showcase.util.Constants
import com.trendyol.showcase.util.TooltipFieldUtil
import com.trendyol.showcase.util.locationOnScreenRect
import com.trendyol.showcase.util.toRectF
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ShowcaseModel(val rectF: RectF,
                         val radius: Float,
                         val titleText: String,
                         val descriptionText: String,
                         @ColorInt val titleTextColor: Int,
                         @ColorInt val descriptionTextColor: Int,
                         @ColorInt val popupBackgroundColor: Int,
                         @ColorInt val closeButtonColor: Int,
                         val showCloseButton: Boolean,
                         val arrowPosition: ArrowPosition,
                         val highlightType: HighlightType,
                         val arrowPercentage: Int?,
                         val windowBackgroundColor: Int,
                         val windowBackgroundAlpha: Int,
                         val titleTextSize: Float,
                         val descriptionTextSize: Float,
                         val highlightPadding: Float,
                         val cancellableFromOutsideTouch: Boolean,
                         val viewArrowPosition:Int
) : Parcelable {




//    fun horizontalCenter() = rectF.left + ((rectF.right - rectF.left) / 2)
    fun horizontalCenter() = rectF.left -rectF.right
//    fun horizontalCenter() = rectF.right + ((rectF.right - rectF.left) / 2)
//    fun horizontalCenter() = rectF.right +((rectF.left - rectF.right) / 2)
//    fun horizontalCenter() = view.locationOnScreenRect.exactCenterX()



//    fun horizontalCenter() = 0f


    fun verticalCenter() = rectF.top + ((rectF.bottom - rectF.top) / 2)

    fun bottomOfCircle() = verticalCenter() + radius
    fun topOfCircle() = verticalCenter() - radius

    class Builder {

        private var focusView: View? = null
        private var focusItemView: MenuItem?=null
        private var titleText: String = Constants.DEFAULT_TEXT
        private var descriptionText: String = Constants.DEFAULT_TEXT
        @ColorInt
        private var titleTextColor: Int = Constants.DEFAULT_TEXT_COLOR
        @ColorInt
        private var descriptionTextColor: Int = Constants.DEFAULT_TEXT_COLOR
        @ColorInt
        private var popupBackgroundColor: Int = Constants.DEFAULT_POPUP_COLOR
        private var showCloseButton: Boolean = Constants.DEFAULT_CLOSE_BUTTON_VISIBILITY
        @ColorInt
        private var closeButtonColor: Int = Constants.DEFAULT_TEXT_COLOR
        private var arrowPosition: ArrowPosition = Constants.DEFAULT_ARROW_POSITION
        private var highlightType: HighlightType = Constants.DEFAULT_HIGHLIGHT_TYPE
        private var arrowPercentage: Int? = null
        @ColorInt
        private var windowBackgroundColor: Int = Constants.DEFAULT_COLOR_BACKGROUND
        private var windowBackgroundAlpha: Int = Constants.DEFAULT_BACKGROUND_ALPHA
        private var titleTextSize: Float = Constants.DEFAULT_TITLE_TEXT_SIZE
        private var descriptionTextSize: Float = Constants.DEFAULT_DESCRIPTION_TEXT_SIZE
        private var highlightPadding: Float = Constants.DEFAULT_HIGHLIGHT_PADDING_EXTRA
        @StyleRes
        private var resId: Int? = null
        private var cancellableFromOutsideTouch: Boolean = Constants.DEFAULT_CANCELLABLE_FROM_OUTSIDE_TOUCH
        private var addToSequence:Boolean=false
        private var sequenceViewList:ArrayList<ShowcaseModel>?= arrayListOf()
        fun view(view: View) = apply {
            focusView = view
        }

        fun titleText(title: String) = apply { titleText = title }
        fun descriptionText(description: String) = apply { descriptionText = description }
        fun titleTextColor(@ColorInt color: Int) = apply { titleTextColor = color }
        fun descriptionTextColor(@ColorInt color: Int) = apply { descriptionTextColor = color }
        fun backgroundColor(@ColorInt color: Int) = apply { popupBackgroundColor = color }
        fun closeButtonColor(@ColorInt color: Int) = apply { closeButtonColor = color }
        fun showCloseButton(show: Boolean) = apply { showCloseButton = show }
        fun arrowPosition(position: ArrowPosition) = apply { arrowPosition = position }
        fun highlightType(type: HighlightType) = apply { highlightType = type }

        fun addSequenceView(showcaseModel: ShowcaseModel){
            sequenceViewList?.add(showcaseModel)
        }
        /**
         *
         * Extra padding for highlight area.
         */
        fun highlightPadding(padding: Float) = apply { highlightPadding = padding }

        /**
         *
         * Custom positioning for arrow.
         */
        fun arrowPercentage(@IntRange(from = 0, to = 100) percentage: Int) = apply { arrowPercentage = percentage }

        fun windowBackgroundColor(@ColorInt color: Int) = apply { windowBackgroundColor = color }
        fun windowBackgroundAlpha(@IntRange(from = 0, to = 255) alpha: Int) = apply { windowBackgroundAlpha = alpha }

        /**
         *
         * titleTextSize in SP.
         */
        fun titleTextSize(size: Float) = apply { titleTextSize = size }

        /**
         *
         * descriptionTextSize in SP.
         */
        fun descriptionTextSize(size: Float) = apply { descriptionTextSize = size }

        /**
         *
         * Resource id of an custom style named Showcase.Theme in project.
         */
        fun resId(@StyleRes res: Int) = apply { resId = res }
        fun cancellableFromOutsideTouch(cancellable: Boolean) = apply { cancellableFromOutsideTouch = cancellable }

        var showCasesList:ArrayList<ShowcaseModel> = arrayListOf()

        fun build():ShowcaseModel{
            if (focusView == null && focusItemView==null) {
                throw Exception("view should not be null!")
            }


            val rect = Rect()

            focusView?.getGlobalVisibleRect(rect)
            val showcaseModel = ShowcaseModel(
                    rect.toRectF(),
                    TooltipFieldUtil.calculateRadius(focusView!!),
                    titleText,
                    descriptionText,
                    titleTextColor,
                    descriptionTextColor,
                    popupBackgroundColor,
                    closeButtonColor,
                    showCloseButton,
                    arrowPosition,
                    highlightType,
                    arrowPercentage,
                    windowBackgroundColor,
                    windowBackgroundAlpha,
                    titleTextSize,
                    descriptionTextSize,
                    highlightPadding,
                    cancellableFromOutsideTouch,
                    focusView!!.locationOnScreenRect.centerX())
            return showcaseModel
        }

    }
}
