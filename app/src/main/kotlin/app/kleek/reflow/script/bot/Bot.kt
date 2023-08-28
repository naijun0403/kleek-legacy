package app.kleek.reflow.script.bot

import app.kleek.reflow.project.Project
import app.kleek.reflow.script.event.Event

object Bot {

    val list: MutableList<Project> = mutableListOf()

    fun sendEvent(event: Event) {
        list.forEach {
            it.client.sendEvent(event)
        }
    }

    fun addLoadedProject(client: Project) {
        list.add(client)
    }

}