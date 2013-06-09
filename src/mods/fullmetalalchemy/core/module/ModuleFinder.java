package mods.fullmetalalchemy.core.module;

import java.io.File;
import java.io.IOException;
import java.util.jar.JarFile;

import mods.fullmetalalchemy.core.FullmetalAlchemy;

/**
 * @author viper283
 * 
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class ModuleFinder {

    public static void findModules(File moduleLocation) throws IOException {

        JarFile jar;

        boolean ran = false;

        File moduleDir = FullmetalAlchemy.platform.getModuleDir();

        FullmetalAlchemy.logger.debug(String.format(
                "Searching for modules in %s", moduleDir.getAbsolutePath()
                        .replace("\\.", "")));

        for(String fileName : moduleDir.list()) {

            ran = true;

            jar = new JarFile(new File(moduleDir, fileName));

            if(FullmetalAlchemy.modules != null) {

                FullmetalAlchemy.modules.add(jar);
            }

            FullmetalAlchemy.logger.debug(String.format(
                    "Found jar file %s adding to list",
                    jar.getName().replace("\\.", "")));
        }

        if(ran == false) {

            FullmetalAlchemy.logger.debug("Your module directory is empty :(");
        }
    }
}