package dev.spotifynutzer.supportbot

import dev.spotifynutzer.supportbot.bot.BotBuilder
import dev.spotifynutzer.supportbot.configuration.ConfigProvider
import dev.spotifynutzer.supportbot.configuration.Configuration
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.io.File
import kotlin.system.exitProcess

class SupportBot {

  private val botBuilder: BotBuilder
  private val token: String
  private val mainConfigurationFile: File = File("config.json")
  private val logger: Logger = LoggerFactory.getLogger(SupportBot::class.java)

  private var mainConfiguration: Configuration = Configuration()

  init {

    if (!mainConfigurationFile.exists()) {
      mainConfigurationFile.createNewFile()
      logger.error("Configurationfile doesn't exist, creating it now...")
      setupConfiguration()
    }

    mainConfiguration = ConfigProvider.getConfigFromFile(mainConfigurationFile) ?: Configuration()

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

    mainConfiguration.config.forEach { logger.debug("Key: ${it.key} | Value: ${it.value}") }

    ConfigProvider.saveConfig(mainConfigurationFile, mainConfiguration)
    logger.info("Exiting...")
    exitProcess(1)
  }
}

fun main() {
  SupportBot()
}