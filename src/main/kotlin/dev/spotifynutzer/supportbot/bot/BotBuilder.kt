package dev.spotifynutzer.supportbot.bot

import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.entities.Activity
import net.dv8tion.jda.api.entities.SelfUser
import net.dv8tion.jda.api.requests.GatewayIntent

/**
 * @author SpotifyNutzer
 * Discord: Paul Weber#1234
 * GitHub: https://github.com/SpotifyNutzeer
 */
class BotBuilder(private val token: String) {

  private lateinit var jdaBuilder: JDABuilder
  lateinit var jda: JDA
  lateinit var selfUser: SelfUser

  fun build() {
    jdaBuilder = JDABuilder.createDefault(
      token,
      GatewayIntent.GUILD_MEMBERS
    )

    jdaBuilder.setActivity(Activity.playing("ONLY ONLINE FOR TESTING"))

    jda = jdaBuilder.build()
    selfUser = jda.selfUser

  }


}