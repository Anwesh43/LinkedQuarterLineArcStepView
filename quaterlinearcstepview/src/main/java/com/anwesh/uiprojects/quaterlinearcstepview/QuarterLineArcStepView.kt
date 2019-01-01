package com.anwesh.uiprojects.quaterlinearcstepview

/**
 * Created by anweshmishra on 01/01/19.
 */

import android.view.View
import android.view.MotionEvent
import android.graphics.Paint
import android.graphics.Canvas
import android.graphics.RectF
import android.graphics.Color
import android.app.Activity
import android.content.Context

val nodes : Int = 5
val arcs : Int = 4
val scGap : Float = 0.05f
val scDiv : Double = 0.51
val sizeFactor : Float = 2.8f
val strokeFactor : Int = 90
val rotDeg : Float = 360f
val color : Int = Color.parseColor("#4CAF50")

fun Int.inverse() : Float = 1f / this

fun Float.maxScale(i : Int, n : Int) : Float = Math.max(0f, this - i * n.inverse())

fun Float.divideScale(i : Int, n : Int) : Float = Math.min(n.inverse(), maxScale(i, n)) * n

fun Float.scaleFactor()  : Float = Math.floor(this / scDiv).toFloat()

fun Float.mirrorValue(a : Int, b : Int) : Float = (1 - scaleFactor()) * a.inverse() + scaleFactor() * b.inverse()

fun Float.updateScale(dir : Float, a : Int, b : Int) : Float = mirrorValue(a, b) * dir * scGap

fun Int.scaleX() : Float = 1f - 2 * (this % 2)

fun Int.scaleY() : Float = 1f - 2 * (this / 2)

fun Canvas.drawQLASNode(i : Int, scale : Float, paint : Paint) {
    val w : Float = width.toFloat()
    val h : Float = height.toFloat()
    val gap : Float = w / (nodes + 1)
    val sc1 : Float = scale.divideScale(0, 2)
    val sc2 : Float = scale.divideScale(1, 2)
    val size : Float = gap / sizeFactor
    paint.style = Paint.Style.STROKE
    paint.strokeCap = Paint.Cap.ROUND
    paint.strokeWidth = Math.min(w, h) / strokeFactor
    paint.color = color
    val r : Float = size / 3
    save()
    translate(gap * (i + 1), h / 2)
    for (j in (0..(arcs - 1))) {
        val scj1 : Float = sc1.divideScale(j, arcs)
        val scj2 : Float = sc2.divideScale(j, arcs)
        save()
        scale(j.scaleX(), j.scaleY())
        translate(size/2, size/2)
        rotate(rotDeg * scj2)
        drawArc(RectF(-r, -r, r, r), -90f, rotDeg * scj1, false, paint)
        drawLine(0f, 0f, 0f, -r * 0.9f, paint)
        restore()
    }
    restore()
}

class QuarterLineArcStepView(ctx : Context) : View(ctx) {

    private val paint : Paint = Paint(Paint.ANTI_ALIAS_FLAG)

    override fun onDraw(canvas : Canvas) {

    }

    override fun onTouchEvent(event : MotionEvent) : Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {

            }
        }
        return true
    }
}