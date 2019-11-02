package net.darkblader24.simplesignedit;

import net.darkblader24.simplesignedit.signedit.*;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class SimpleSignEdit extends JavaPlugin{

	private static Plugin plugin;
	private static Logger log;
	private static SignEdit signedit;

	public static SignEdit getSignEdit() {
	    return signedit;
	}

	public static Plugin getPlugin() {
		return plugin;
	}

	public void onEnable() {
		log = getLogger();
		log.info("Plugin is loading..");
		plugin = this;

        if (setupSignEdit()) {
    		getCommand("signedit").setExecutor(new SignEditCommand());
			getServer().getPluginManager().registerEvents(new SignEditListener(), this);

            getConfig().options().copyDefaults(true);
            saveConfig();

    		log.info("[SimpleSignEdit] Plugin loaded!");
        }
        else {
    		log.warning("####################");
    		log.warning("Loading failed!");
    		log.warning("Error: Your server version is not compatible with this plugin!");
        	log.warning("Please look here for updates:");
        	log.warning("https://www.spigotmc.org/resources/simplesignedit.13649/");
    		log.warning("####################");

            Bukkit.getPluginManager().disablePlugin(this);
        }
	}

	@Override
	public void onDisable(){
		log.info("Plugin unloaded!");
	}

    private boolean setupSignEdit() {

        String version;

        try {
            version = Bukkit.getServer().getClass().getPackage().getName().replace(".",  ",").split(",")[3];
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }

		log.info("Your server is running version " + version);

        if (version.equals("v1_8_R3"))
            signedit = new SignEdit_1_8_R3();

        else if (version.equals("v1_9_R1"))
            signedit = new SignEdit_1_9_R1();

        else if (version.equals("v1_9_R2"))
            signedit = new SignEdit_1_9_R2();

        else if (version.equals("v1_10_R1"))
            signedit = new SignEdit_1_10_R1();

        else if (version.equals("v1_11_R1"))
            signedit = new SignEdit_1_11_R1();

        else if (version.equals("v1_12_R1"))
            signedit = new SignEdit_1_12_R1();

        return signedit != null;
    }
}
