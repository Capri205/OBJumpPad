package net.obmc.objumppad;

import org.bukkit.event.Listener;

import io.papermc.paper.plugin.bootstrap.BootstrapContext;
import io.papermc.paper.plugin.bootstrap.PluginBootstrap;

public class OBJumpPadBootstrap implements PluginBootstrap {

    private OBJumpPad plugin;
    private OBJumpPadLoader loader;

    private EventListener listener;

    public OBJumpPadBootstrap() {
    }
    
    public OBJumpPadBootstrap(OBJumpPad plugin) {
        this.plugin = plugin;
    }

    // not implemented yet, but let's not stop the server loading the plugin
    @Override
    public void bootstrap(BootstrapContext bc) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    public void enable() {

        // loader
        loader = new OBJumpPadLoader(this.plugin);
        loader.loadConfiguration();
        loader.loadClasses();
        loader.loadDependencies();

        // listeners
        this.listener = new EventListener(loader);
        plugin.getServer().getPluginManager().registerEvents((Listener)this.listener, this.plugin);
    }

    public void disable() {

        //loader.disablePlugin();
        loader = null;
    }
}
