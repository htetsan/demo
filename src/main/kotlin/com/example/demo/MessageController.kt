package com.example.demo

import com.pusher.rest.Pusher
import jdk.internal.net.http.common.Log.CHANNEL
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/message")
class MessageController {
    private val PUSHER_APP_ID = "1550901"
    private val PUSHER_APP_KEY = "7874809b61a13e74bda5"
    private val PUSHER_APP_SECRET = "e315040129eb1fa0aec8"
    private val PUSHER_APP_CLUSTER = "ap1"
    private val CHANNEL_NAME = "pusher-chat-app-development"
    private val EVENT_NAME = "new_message"

    private val pusher = Pusher(PUSHER_APP_ID, PUSHER_APP_KEY, PUSHER_APP_SECRET)

    init {
        pusher.setCluster(PUSHER_APP_CLUSTER)
    }

    @PostMapping
    fun postMessage(@RequestBody message: Message): ResponseEntity<Unit> {
        pusher.trigger(CHANNEL_NAME, EVENT_NAME, message)
        return ResponseEntity.ok().build()
    }
}