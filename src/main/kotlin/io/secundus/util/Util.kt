package io.secundus.util

import io.secundus.config.PATTERN
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

/**
 * Application utils and extension functions
 *
 * @author Secundus
 * @since 16.04.17 13:07
 */

/**
 * Random hex generation extension function
 */
fun String.Companion.getRandomHexString(numchars: Int): String {
    val r = Random()
    val sb = StringBuffer()
    while (sb.length < numchars) {
        sb.append(Integer.toHexString(r.nextInt()))
    }
    return sb.toString().substring(0, numchars)
}

/**
 * Convert String to LocalDate extension function
 */
fun String.toLocalDate(): LocalDate {
    return LocalDate.parse(this, DateTimeFormatter.ofPattern(PATTERN))
}

/**
 * Return current date at String format
 */
fun String.Companion.currentDate(): String {
    return LocalDate.now().format(DateTimeFormatter.ofPattern(PATTERN))
}