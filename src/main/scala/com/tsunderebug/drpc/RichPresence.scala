package com.tsunderebug.drpc

import club.minnced.discord.rpc.{DiscordRPC, DiscordRichPresence}

import scala.util.Random

case class RichPresence(
                         state: String = null,
                         details: String = null,
                         startTimestamp: Long = null,
                         endTimestamp: Long = null,
                         largeImageKey: String = null,
                         largeImageText: String = null,
                         smallImageKey: String = null,
                         smallImageText: String = null,
                         partyId: String = null,
                         partySize: Int = null,
                         partyMax: Int = null,
                         matchSecret: String = null,
                         joinSecret: String = null,
                         spectateSecret: String = null,
                         instance: Byte = new Random().nextLong().asInstanceOf[Byte]
                       ) {

  def submit(): Unit = {
    val internalRPC = new DiscordRichPresence
    internalRPC.state = state
    internalRPC.details = details
    internalRPC.startTimestamp = startTimestamp
    internalRPC.endTimestamp = endTimestamp
    internalRPC.largeImageKey = largeImageKey
    internalRPC.largeImageText = largeImageText
    internalRPC.smallImageKey = smallImageKey
    internalRPC.smallImageText = smallImageText
    internalRPC.partyId = partyId
    internalRPC.partySize = partySize
    internalRPC.partyMax = partyMax
    internalRPC.matchSecret = matchSecret
    internalRPC.spectateSecret = spectateSecret
    internalRPC.joinSecret = joinSecret
    internalRPC.instance = instance
    DiscordRPC.INSTANCE.Discord_UpdatePresence(internalRPC)
  }

}

object RichPresence {

  private var workerThread: Thread = _

  def start(appId: String, handlers: EventHandlers = EventHandlers(), steamId: String = null): Unit = {
    stop()
    DiscordRPC.INSTANCE.Discord_Initialize(appId, handlers.internal, true, steamId)
    workerThread = new Thread(() => {
      while (!Thread.currentThread.isInterrupted) {
        DiscordRPC.INSTANCE.Discord_RunCallbacks()
        try Thread.sleep(2000) catch {case _: InterruptedException =>}
      }
    }, "RPC Callback Handler")
    workerThread.start()
  }

  def stop(): Unit = {
    if(workerThread != null) workerThread.interrupt()
    workerThread = null
  }

}
