package com.playloudr.app.util

import java.time.Duration
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

object DateTimeUtils {
  fun resolveTimestamp(timestamp: String?): Instant {
    return try {
      Instant.parse(timestamp ?: return Instant.now())
    } catch (e: DateTimeParseException) {
      Instant.now()
    }
  }

  fun formatTimestamp(timestamp: Instant): String {
    val now = Instant.now()
    val diff = Duration.between(timestamp, now)
    return when {
      diff.toMinutes() < 1 -> "Now"
      diff.toMinutes() < 60 -> "${diff.toMinutes()} ${if (diff.toMinutes() == 1L) "minute" else "minutes"} ago"
      diff.toHours() < 24 -> "${diff.toHours()} ${if (diff.toHours() == 1L) "hour" else "hours"} ago"
      diff.toDays() < 7 -> "${diff.toDays()} ${if (diff.toDays() == 1L) "day" else "days"} ago"
      else -> DateTimeFormatter.ofPattern("dd/MM/yyyy")
        .withZone(ZoneId.systemDefault())
        .format(timestamp)
    }
  }

}