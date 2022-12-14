package dev.spotifynutzer.supportbot

import dev.spotifynutzer.supportbot.bot.BotBuilder
import dev.spotifynutzer.supportbot.configuration.ConfigProvider
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.io.File
import kotlin.system.exitProcess

class SupportBot {

  private val botBuilder: BotBuilder
  private val token: String
  private val mainConfigurationFile: File = File("config.json")
  private val logger: Logger = LoggerFactory.getLogger(SupportBot::class.java)

  private val mainConfiguration: MutableMap<String, Any>

  init {

    if (!mainConfigurationFile.exists()) {
      mainConfigurationFile.createNewFile()
      logger.error("Configurationfile doesn't exist, creating it now...")
      setupConfiguration()
    }

    mainConfiguration = ConfigProvider.getConfigFromFile(mainConfigurationFile) ?: HashMap()

    if (mainConfiguration.isEmpty()) {
      logger.error("Configuration is empty, filling in default values...")
      setupConfiguration()
    }

    token = mainConfiguration["token"] as String

    botBuilder = BotBuilder(token)
    botBuilder.build()
  }

  private fun setupConfiguration() {
    mainConfiguration["token"] = ""
    mainConfiguration["devmode"] = false

    ConfigProvider.saveConfig(mainConfigurationFile, mainConfiguration)
    logger.info("Exiting...")
    exitProcess(1)
  }
}

fun main() {
  SupportBot()
}