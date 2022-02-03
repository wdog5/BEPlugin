package com.be;

import com.be.registry.BEFoodRegistry;
import com.be.registry.BEItemGroups;
import com.be.registry.BEPlants;
import com.be.registry.BETrees;
import com.be.utils.RegistryHandler;
import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun4.libraries.dough.config.Config;
import io.github.thebusybiscuit.slimefun4.libraries.dough.updater.GitHubBuildsUpdater;
import io.github.thebusybiscuit.slimefun4.libraries.dough.updater.PluginUpdater;
import io.github.thebusybiscuit.slimefun4.libraries.paperlib.PaperLib;
import org.bukkit.plugin.java.JavaPlugin;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class BEPlugin extends JavaPlugin implements SlimefunAddon {

    private static Config config;
    private static BEPlugin instance;

    @Override
    public void onEnable() {
        PaperLib.suggestPaper(this);
        instance = this;

        config = new Config(this);
        if (!RegistryHandler.getSchematicsFolder().exists()) {
            RegistryHandler.getSchematicsFolder().mkdirs();
        }

        BEPlants.onPlantsRegister();
        BETrees.onTreesRegister();
        BEFoodRegistry.register(this, BEItemGroups.miscItemGroup, BEItemGroups.drinksItemGroup, BEItemGroups.foodItemGroup);

        // Auto Updater
        if (BEPlugin.config.getBoolean("options.auto-update")) {
            PluginUpdater updater = new GitHubBuildsUpdater(this, getFile(), "1798643961/BEPlugin/master");
            updater.start();
        }
        config.save();
    }

    @Override
    public void onDisable() {
        instance = null;
    }

    @NotNull
    @Override
    public JavaPlugin getJavaPlugin() {
        return this;
    }

    @Nullable
    @Override
    public String getBugTrackerURL() {
        return null;
    }

    public static BEPlugin getInstance() {
        return instance;
    }
}
