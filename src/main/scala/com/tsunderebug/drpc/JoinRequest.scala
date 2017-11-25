package com.tsunderebug.drpc

import club.minnced.discord.rpc.DiscordJoinRequest

case class JoinRequest(
                        userId: String = null,
                        username: String = null,
                        avatar: String = null
                      )

object JoinRequest{

  def apply(internal: DiscordJoinRequest): JoinRequest = JoinRequest(internal.userId, internal.username, internal.avatar)

}
