package com.tsunderebug.drpc

import club.minnced.discord.rpc.DiscordEventHandlers

case class EventHandlers(
                        ready: () => Unit = null,
                        disconnected: (Int, String) => Unit = null,
                        errored: (Int, String) => Unit = null,
                        joinGame: (String) => Unit = null,
                        spectateGame: (String) => Unit = null,
                        joinRequest: (JoinRequest) => Unit = null
                        ) {

  def internal: DiscordEventHandlers = {
    val internal: DiscordEventHandlers = new DiscordEventHandlers
    internal.ready = () => ready()
    internal.disconnected = (i, s) => disconnected(i, s)
    internal.errored = (i, s) => errored(i, s)
    internal.joinGame = (s) => joinGame(s)
    internal.spectateGame = (s) => spectateGame(s)
    internal.joinRequest = (r) => joinRequest(JoinRequest(r))
    internal
  }

}
