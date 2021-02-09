package com.ttc.finch_station.data.common

import timber.log.Timber

fun logError(unit: () -> Unit, exception: Exception) {
    val message = exception.toString()
    Timber.e(getLogMessage(unit, message))
}

fun logVerbose(unit: () -> Unit, message: String) {
    Timber.v(getLogMessage(unit, message))
}
fun logDebug(unit: () -> Unit) {
    Timber.d(getLogMessage(unit, ""))
}
fun logInfo(unit: () -> Unit, any: Any) {
    Timber.i(getLogMessage(unit, any.toString()))
}
fun logWarn(unit: () -> Unit, message: String) {
    Timber.w(getLogMessage(unit, message))
}



fun getLogMessage(unit: () -> Unit, message: String): String {
    val className = unit.javaClass.enclosingClass?.simpleName
    val methodName = unit.javaClass.enclosingMethod?.name
    return "className: $className; methodName: $methodName; message: $message"
}

