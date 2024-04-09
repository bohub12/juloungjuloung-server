package com.juloungjuloung.juju.appender

import ch.qos.logback.classic.spi.ILoggingEvent
import ch.qos.logback.classic.spi.IThrowableProxy
import ch.qos.logback.core.UnsynchronizedAppenderBase
import org.springframework.web.client.RestTemplate
import java.text.SimpleDateFormat

class SlackAppender : UnsynchronizedAppenderBase<ILoggingEvent>() {
    companion object {
        private const val STACK_TRACE_ELEMENT_MAX_SIZE = 15
    }

    lateinit var webHookUrl: String

    override fun append(eventObject: ILoggingEvent?) {
        val restTemplate = RestTemplate()

        val body: Map<String, Any> = createSlackMessage(eventObject!!)
        restTemplate.postForEntity(webHookUrl, body, String::class.java)
    }

    private fun createSlackMessage(eventObject: ILoggingEvent): Map<String, Any> {
        val message = createMessageBody(eventObject)
        return mapOf(
            "attachments" to listOf(
                mapOf(
                    "fallback" to "로그 전송 요청을 실패했어요 :cry:",
                    "color" to "#2eb886",
                    "pretext" to "에러가 발생했어요. 아래 로그를 확인해주세요 :cry:",
                    "author_name" to "어드민서버-에러로그",
                    "text" to message,
                    "ts" to eventObject.timeStamp
                )
            )
        )
    }

    private fun createMessageBody(eventObject: ILoggingEvent): String {
        val baseMessage = "에러가 발생했습니다.\n"
        val pattern = "$baseMessage```%s %s %s [%s]\n%s```\nStack trace\n```\n%s```"
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")

        return String.format(
            pattern,
            simpleDateFormat.format(eventObject.timeStamp),
            eventObject.level,
            eventObject.threadName,
            eventObject.loggerName,
            eventObject.formattedMessage,
            if (eventObject.throwableProxy != null) getStackTrace(eventObject.throwableProxy) else "none"
        )
    }

    private fun getStackTrace(throwableProxy: IThrowableProxy): String? {
        val stacktraceBuilder = StringBuilder()
        stacktraceBuilder.append(throwableProxy.className)
        stacktraceBuilder.append("\n")

        val stackTraces = throwableProxy.stackTraceElementProxyArray
        if (stackTraces.isNullOrEmpty()) {
            return stacktraceBuilder.toString()
        }

        for (index in stackTraces.indices) {
            if (index > STACK_TRACE_ELEMENT_MAX_SIZE) {
                break
            }
            stacktraceBuilder.append(stackTraces[index].toString())
            stacktraceBuilder.append("\n")
        }

        return stacktraceBuilder.toString()
    }
}