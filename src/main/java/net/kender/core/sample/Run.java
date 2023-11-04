package net.kender.core.sample;

import net.kender.core.UtilsFiles;

public class Run {
    public Run(CommandConstructor a) {
        UtilsFiles.run(a.command);
    }
}
