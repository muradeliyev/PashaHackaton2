package app.pasha.hackaton.ui.kit

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

object Typography {

    private val Roboto = FontFamily.Default

    private fun textStyle(
        fontWeight: FontWeight,
        fontSize: Int,
        lineHeight: Int,
    ): TextStyle = TextStyle(
        fontFamily = Roboto,
        fontWeight = fontWeight,
        fontSize = fontSize.sp,
        lineHeight = lineHeight.sp,
        letterSpacing = 0.sp,
    )

    val h0: TextStyle = textStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 36,
        lineHeight = 40,
    )

    val h1: TextStyle = textStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 32,
        lineHeight = 36,
    )

    val h2: TextStyle = textStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 28,
        lineHeight = 32,
    )

    val h3: TextStyle = textStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 24,
        lineHeight = 28,
    )

    val h4: TextStyle = textStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 20,
        lineHeight = 24,
    )

    val s1: TextStyle = textStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 20,
        lineHeight = 24,
    )

    val s2: TextStyle = textStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 16,
        lineHeight = 20,
    )

    val s3: TextStyle = textStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 14,
        lineHeight = 16,
    )

    val l1: TextStyle = textStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 16,
        lineHeight = 20,
    )

    val l1m: TextStyle = textStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 16,
        lineHeight = 20,
    )

    val l2: TextStyle = textStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 14,
        lineHeight = 16,
    )

    val l2m: TextStyle = textStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 14,
        lineHeight = 16,
    )

    val b1: TextStyle = textStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 16,
        lineHeight = 24,
    )

    val b1m: TextStyle = textStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 16,
        lineHeight = 24,
    )

    val b2: TextStyle = textStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 14,
        lineHeight = 20,
    )

    val b2m: TextStyle = textStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 14,
        lineHeight = 20,
    )

    val c1: TextStyle = textStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 12,
        lineHeight = 16,
    )

    val c1sb: TextStyle = textStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 12,
        lineHeight = 16,
    )

    val c2: TextStyle = textStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 10,
        lineHeight = 14,
    )

    val c2sb: TextStyle = textStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 10,
        lineHeight = 14,
    )
}
