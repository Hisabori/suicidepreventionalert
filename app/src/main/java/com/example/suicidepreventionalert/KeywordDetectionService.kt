package com.example.suicidepreventionalert

import android.accessibilityservice.AccessibilityService
import android.accessibilityservice.AccessibilityServiceInfo
import android.util.Log
import android.view.accessibility.AccessibilityEvent
import android.widget.Toast

class KeywordDetectionService : AccessibilityService() {

    companion object {
        private val KEYWORDS = listOf(
            "자살", "극단선택", "죽고 싶다", "삶이 끝났으면", "모든 것을 포기하고 싶다", "더 이상 살고 싶지 않다"
        )
    }

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        event?.let {
            if (event.eventType == AccessibilityEvent.TYPE_VIEW_TEXT_CHANGED ||
                event.eventType == AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED) {

                val text = event.text.toString()
                for (keyword in KEYWORDS) {
                    if (text.contains(keyword)) {
                        Log.d("KeywordDetection", "Keyword detected: $keyword")
                        showAlert()
                        break
                    }
                }
            }
        }
    }

    override fun onInterrupt() {
        // No action needed
    }

    override fun onServiceConnected() {
        super.onServiceConnected()
        val info = AccessibilityServiceInfo().apply {
            eventTypes = AccessibilityEvent.TYPE_VIEW_TEXT_CHANGED or
                    AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED
            feedbackType = AccessibilityServiceInfo.FEEDBACK_SPOKEN
            notificationTimeout = 100
        }
        serviceInfo = info
    }

    private fun showAlert() {
        Toast.makeText(this, "자살 암시 키워드가 탐지되었습니다. 도움이 필요하시면 109에 연락하세요.", Toast.LENGTH_LONG).show()
    }
}
