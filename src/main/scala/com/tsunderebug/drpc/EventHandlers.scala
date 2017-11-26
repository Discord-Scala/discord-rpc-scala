package com.tsunderebug.drpc

import club.minnced.discord.rpc.DiscordEventHandlers

case class EventHandlers(
                        ready: () => Unit = () => {},
                        disconnected: (Int, String) => Unit = (_, _) => {},
                        errored: (Int, String) => Unit = (_, _) => {},
                        joinGame: (String) => Unit = (_) => {},
                        spectateGame: (String) => Unit = (_) => {},
                        joinRequest: (JoinRequest) => Unit = (_) => {}
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
