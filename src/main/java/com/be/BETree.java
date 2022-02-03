package com.be;

import com.be.utils.RegistryHandler;
import io.github.thebusybiscuit.exoticgarden.schematics.Schematic;
import java.io.File;
import java.io.IOException;
import org.bukkit.Material;
import io.github.thebusybiscuit.exoticgarden.Tree;

public class BETree extends Tree {

    private Schematic schematic;

    public BETree(String fruit, String texture, Material... soil) {
        super(fruit, texture, soil);
    }


    @Override
    public Schematic getSchematic() throws IOException {
        if (this.schematic == null) {
            this.schematic = Schematic.loadSchematic(new File(RegistryHandler.getSchematicsFolder(), getFruitID() + "_TREE.schematic"));
        }

        return this.schematic;
    }
}
