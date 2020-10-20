package com.example.composedesigndemo

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.compose.foundation.Text
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.design_library.UIElementType
import com.example.design_library.primaryButton

class ElementListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val uiElementType = intent.getSerializableExtra(ARG_UI_ELEMENT_TYPE)!! as UIElementType
        val list = when (uiElementType) {
            UIElementType.BUTTON -> ButtonRepository.fetchButtons()
            UIElementType.TEXT -> TODO()
        }

        setContent {
            LazyColumnFor(list) { uiElement ->
                Box(modifier = Modifier.padding(16.dp)) {
                    when (uiElement.type) {
                        UIElementType.BUTTON -> primaryButton(uiElement.variant)
                        UIElementType.TEXT -> TODO()
                    }
                }
            }
        }
    }

    companion object {
        private const val ARG_UI_ELEMENT_TYPE = "ARG_UI_ELEMENT_TYPE"

        fun newIntent(context: Context, uiElementType: UIElementType) =
            Intent(context, ElementListActivity::class.java).apply {
                putExtras(Bundle().apply {
                    putSerializable(ARG_UI_ELEMENT_TYPE, uiElementType)
                })
            }
    }
}