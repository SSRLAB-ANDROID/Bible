package by.ssrlab.bible.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.icu.util.Calendar
import by.ssrlab.bible.utils.vm.PagesVM
import java.text.SimpleDateFormat
import java.util.Locale

class TimeReceiver(private val viewModel: PagesVM): BroadcastReceiver() {

    fun register(context: Context) {
        val intentFilter = IntentFilter().apply {
            addAction(Intent.ACTION_TIME_CHANGED)
            addAction(Intent.ACTION_TIMEZONE_CHANGED)
            addAction(Intent.ACTION_TIME_TICK)
        }

        context.registerReceiver(this, intentFilter)
    }

    fun unregister(context: Context) {
        context.unregisterReceiver(this)
    }

    private fun getTime(pattern: String): String {
        val currentDate = Calendar.getInstance().time
        val df = SimpleDateFormat(pattern, Locale.ROOT)
        return df.format(currentDate)
    }

    private fun getTimeAsString() {
        val hours = getTime("HH")
        val minutes = getTime("mm")
        viewModel.time.value = "$hours:$minutes"
    }

    override fun onReceive(context: Context?, intent: Intent?) {
         getTimeAsString()
    }

    init {
        getTimeAsString()
    }
}