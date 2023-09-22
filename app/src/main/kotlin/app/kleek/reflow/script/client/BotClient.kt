package app.kleek.reflow.script.client

import app.kleek.core.CoreHelper
import app.kleek.reflow.compat.oauth.OauthHelper
import app.kleek.reflow.logger.Logger
import app.kleek.reflow.script.event.Event
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class BotClient: CoroutineScope {

    private val eventFlow: MutableSharedFlow<Event> = MutableSharedFlow(
        extraBufferCapacity = Int.MAX_VALUE
    )

    val events: MutableSharedFlow<Event> = eventFlow

    override val coroutineContext: CoroutineContext = SupervisorJob() + Dispatchers.Default

    val oauthHelper: OauthHelper by lazy {
        if (CoreHelper.oauthGetter == null) {
            CoreHelper.oauthGetter = { OauthHelper.create() }
        }

        CoreHelper.oauthGetter!!.invoke()
    }

    fun sendEvent(event: Event) {
        Logger.log("Sending event $event")
        launch {
            eventFlow.emit(event)
        }
    }

}

inline fun <reified T : Event> BotClient.on(
    scope: CoroutineScope = this,
    noinline callback: suspend T.() -> Unit
): Job =
    events.buffer(Channel.UNLIMITED).filterIsInstance<T>()
        .onEach { event ->
            scope.launch { runCatching { callback(event) } }
        }
        .launchIn(scope)