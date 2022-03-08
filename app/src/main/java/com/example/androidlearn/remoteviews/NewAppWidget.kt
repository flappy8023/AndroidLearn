package com.example.androidlearn.remoteviews

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import android.widget.Toast
import com.example.androidlearn.R

/**
 * Implementation of App Widget functionality.
 */
class NewAppWidget : AppWidgetProvider() {
    private var num = 1

    companion object {
        const val ACTION = "com.example.androidlearn.click"
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        super.onReceive(context, intent)
        if (intent?.action.equals(ACTION)) {
            // Construct the RemoteViews object
            Toast.makeText(context, "click", Toast.LENGTH_LONG).show()
            val views = RemoteViews(context?.packageName, R.layout.new_app_widget)
            views.setTextViewText(R.id.appwidget_text, (num).toString())
            val intent = Intent(NewAppWidget.ACTION)
            intent.setComponent(ComponentName(context!!, NewAppWidget::class.java))
            val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0)
            views.setOnClickPendingIntent(R.id.appwidget_text, pendingIntent)
            // Instruct the widget manager to update the widget
            AppWidgetManager.getInstance(context)
                .updateAppWidget(ComponentName(context!!, NewAppWidget::class.java), views)
        }
    }

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

internal fun updateAppWidget(
    context: Context,
    appWidgetManager: AppWidgetManager,
    appWidgetId: Int
) {
    val widgetText = "hello"
    // Construct the RemoteViews object
    val views = RemoteViews(context.packageName, R.layout.new_app_widget)
    views.setTextViewText(R.id.appwidget_text, widgetText)
    val intent = Intent(NewAppWidget.ACTION)
    intent.setComponent(ComponentName(context!!, NewAppWidget::class.java))

    val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0)
    views.setOnClickPendingIntent(R.id.appwidget_text, pendingIntent)
    // Instruct the widget manager to update the widget
    appWidgetManager.updateAppWidget(appWidgetId, views)
}