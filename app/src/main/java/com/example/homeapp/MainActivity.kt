package com.example.homeapp

import android.animation.ObjectAnimator
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.animation.DecelerateInterpolator
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import android.app.Activity
import android.graphics.drawable.GradientDrawable

class MainActivity : Activity() {

    private val backgroundColor = Color.rgb(8, 9, 13)
    private val surfaceColor = Color.rgb(17, 19, 26)
    private val primaryColor = Color.rgb(124, 92, 252)
    private val whiteColor = Color.WHITE
    private val secondaryColor = Color.rgb(165, 168, 179)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.statusBarColor = backgroundColor
        window.navigationBarColor = backgroundColor

        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setBackgroundColor(backgroundColor)
            setPadding(dp(24), dp(30), dp(24), dp(24))
        }

        val scrollContent = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
        }

        val logo = TextView(this).apply {
            text = "✦"
            textSize = 32f
            setTextColor(primaryColor)
            gravity = Gravity.CENTER
        }

        scrollContent.addView(
            logo,
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                dp(55)
            )
        )

        val title = TextView(this).apply {
            text = "Welcome Home"
            textSize = 30f
            setTextColor(whiteColor)
            gravity = Gravity.CENTER
            setTypeface(null, android.graphics.Typeface.BOLD)
        }

        scrollContent.addView(
            title,
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                dp(50)
            )
        )

        val subtitle = TextView(this).apply {
            text = "Everything you need, right here."
            textSize = 15f
            setTextColor(secondaryColor)
            gravity = Gravity.CENTER
        }

        scrollContent.addView(
            subtitle,
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                dp(35)
            )
        )

        val card = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
            setPadding(dp(24), dp(28), dp(24), dp(28))
            background = roundedBackground(surfaceColor, 24f)
        }

        val cardTitle = TextView(this).apply {
            text = "Your Home"
            textSize = 22f
            setTextColor(whiteColor)
            gravity = Gravity.CENTER
            setTypeface(null, android.graphics.Typeface.BOLD)
        }

        card.addView(
            cardTitle,
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                dp(40)
            )
        )

        val cardText = TextView(this).apply {
            text = "A simple, clean and modern Android home screen."
            textSize = 14f
            setTextColor(secondaryColor)
            gravity = Gravity.CENTER
        }

        val textParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            dp(55)
        )

        card.addView(cardText, textParams)

        val button = TextView(this).apply {
            text = "Get Started"
            textSize = 15f
            setTextColor(Color.WHITE)
            gravity = Gravity.CENTER
            setTypeface(null, android.graphics.Typeface.BOLD)
            background = roundedBackground(primaryColor, 16f)

            setOnClickListener {
                Toast.makeText(
                    this@MainActivity,
                    "Welcome to HomeApp!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        card.addView(
            button,
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                dp(52)
            )
        )

        val cardParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )

        cardParams.setMargins(0, dp(35), 0, 0)

        scrollContent.addView(card, cardParams)

        val footer = TextView(this).apply {
            text = "HomeApp  •  Version 1.0"
            textSize = 12f
            setTextColor(Color.rgb(100, 103, 115))
            gravity = Gravity.CENTER
        }

        val footerParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            dp(45)
        )

        footerParams.setMargins(0, dp(25), 0, 0)

        scrollContent.addView(footer, footerParams)

        root.addView(
            scrollContent,
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                0,
                1f
            )
        )

        setContentView(root)

        animateIn(logo)
        animateIn(title, 100)
        animateIn(subtitle, 180)
        animateIn(card, 260)
        animateIn(footer, 340)
    }

    private fun roundedBackground(
        color: Int,
        radius: Float
    ): GradientDrawable {
        return GradientDrawable().apply {
            setColor(color)
            cornerRadius = dp(radius.toInt()).toFloat()
        }
    }

    private fun animateIn(view: View, delay: Long = 0) {
        view.alpha = 0f
        view.translationY = dp(18).toFloat()

        view.postDelayed({

            val alpha = ObjectAnimator.ofFloat(
                view,
                View.ALPHA,
                0f,
                1f
            )

            val translation = ObjectAnimator.ofFloat(
                view,
                View.TRANSLATION_Y,
                dp(18).toFloat(),
                0f
            )

            alpha.duration = 500
            translation.duration = 500

            alpha.interpolator = DecelerateInterpolator()
            translation.interpolator = DecelerateInterpolator()

            alpha.start()
            translation.start()

        }, delay)
    }

    private fun dp(value: Int): Int {
        return (value * resources.displayMetrics.density).toInt()
    }
}
