package discord.gateway;

public enum GatewayEvent {
  Dispatch,
  Heartbeat,
  Identify,
  PresenceUpdate,
  VoiceStateUpdate,
  __,
  Resume,
  Reconnect,
  RequestGuildMembers,
  InvalidSession,
  Hello,
  HeartbeatACK;
}
