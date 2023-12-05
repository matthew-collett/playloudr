package com.playloudr.app.util
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

// Shoutout ECE3221 DeBoUnCe
class Debouncer(
  private val waitMs: Long,
  private val coroutineScope: CoroutineScope
) {
  private var debounceJob: Job? = null

  fun debounce(action: () -> Unit) {
    debounceJob?.cancel()

    debounceJob = coroutineScope.launch {
      delay(waitMs)
      action()
    }
  }
}
