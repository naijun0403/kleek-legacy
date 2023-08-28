package app.kleek.reflow.project

import app.kleek.reflow.script.bot.Bot
import app.kleek.reflow.script.client.BotClient
import app.kleek.reflow.script.project.BotProject

object ProjectLoader {

    fun createProject(name: String): BotProject {
        val botProject = BotProject(
            BotClient()
        )
        val project = Project(name, botProject.getClient())
        Bot.addLoadedProject(project)
        return botProject
    }

}