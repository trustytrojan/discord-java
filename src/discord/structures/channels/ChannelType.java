package discord.structures.channels;

public enum ChannelType {
  GuildText,
  DM,
  GuildVoice,
  GroupDM,
  GuildCategory,
  GuildAnnouncement,
  // 6, 7, 8, 9 are not named
  AnnouncementThread, // will be 10 from Discord
  PublicThread, // 11
  PrivateThread, // 12
  GuildStageVoice, // 13
  GuildDirectory, // 14
  GuildForum; // 15

  public static ChannelType get(final int type) {
    switch(type) {
      case 0: return GuildText;
      case 1: return DM;
      case 2: return GuildVoice;
      case 3: return GroupDM;
      case 4: return GuildCategory;
      case 5: return GuildAnnouncement;
      case 10: return AnnouncementThread;
      case 11: return PublicThread;
      case 12: return PrivateThread;
      case 13: return GuildStageVoice;
      case 14: return GuildDirectory;
      case 15: return GuildForum;
      default: return null;
    }
  }

  public static String toString(final ChannelType type) {
    switch(type) {
      case GuildText: return "Text Channel";
      case DM: return "DM Channel";
      case GuildVoice: return "Voice Channel";
      case GroupDM: return "Group DM";
      case GuildCategory: return "Category Channel";
      case GuildAnnouncement: return "Announcement Channel";
      //case AnnouncementThread: return "";
      case PublicThread: return "Public Thread";
      case PrivateThread: return "Private Thread";
      case GuildStageVoice: return "Stage Channel";
      //case GuildDirectory: return "";
      //case GuildForum: return "";
      default: return null;
    }
  }
}
