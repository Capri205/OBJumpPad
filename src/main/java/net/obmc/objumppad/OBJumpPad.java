package net.obmc.objumppad;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.bukkit.plugin.java.JavaPlugin;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;

public class OBJumpPad extends JavaPlugin {

	private OBJumpPadBootstrap bootstrap;

   	static Logger log = LoggerFactory.getLogger(OBJumpPad.class);

	public static OBJumpPad instance;

	private static final String PLUGINNAME = "OBJumpPad";
	private static final TextComponent CHATMSGPREFIX = Component.text(PLUGINNAME, NamedTextColor.AQUA, TextDecoration.BOLD)
	    .append(Component.text(" » ", NamedTextColor.DARK_GRAY, TextDecoration.BOLD));
	
	public OBJumpPad() {
		instance = this;
	}

	public static OBJumpPad getInstance() {
    	return instance;
    }

    @Override
	public void onEnable() {
		bootstrap = new OBJumpPadBootstrap(this);
		bootstrap.enable();
		log.info("Plugin Version {} activated!", this.getPluginMeta().getVersion());
	}

	@Override
	public void onDisable() {
		if (bootstrap != null) {
            bootstrap.disable();
        }
	}

	// consistent messaging
	public String getPluginName() {
		return PLUGINNAME;
	}
	public TextComponent getChatMsgPrefix() {
		return CHATMSGPREFIX;
	}
}
