import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;


public final class Main extends JavaPlugin {


    /**
     * onLoad(): Pre-startup script
     * First thing in the server to initialize
     */
    @Override
    public void onLoad() {


    }


    /**
     * onEnable(): startup script
     */
    @Override
    public void onEnable() {

        //DatabaseLink here

        getServer().getPluginManager().registerEvents(new RightClickListener(), this);


    }


    /**
     * onDisable(): Closing script
     * used to remove every clock safely
     */
    @Override
    public void onDisable() {


    }



}
