package com.anwesh.uiprojects.linkedquarterlinearcstepview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.anwesh.uiprojects.quaterlinearcstepview.QuarterLineArcStepView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        QuarterLineArcStepView.create(this)
    }
}
