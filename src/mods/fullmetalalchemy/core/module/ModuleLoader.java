package mods.fullmetalalchemy.core.module;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Collections;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import mods.fullmetalalchemy.core.FullmetalAlchemy;
import mods.fullmetalalchemy.core.config.ConfigSettings;

/**
 * @author viper283
 * 
 * @license Lesser GNU Public License v3
 *          (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class ModuleLoader {

    public static void loadModules() {

        Class<?> clazz;

        URLClassLoader loader;

        if(!(FullmetalAlchemy.modules.isEmpty())) {

            try {

                for(JarFile jar : FullmetalAlchemy.modules) {

                    for(JarEntry entry : Collections.list(jar.entries())) {

                        if(entry.getName().endsWith(".class")) {
                            String className = entry.getName()
                                    .replace("/", ".")
                                    .replace(".class", "");

                            File file = new File(jar.getName());

                            loader = new URLClassLoader(new URL[] {
                                file.toURI().toURL()
                            });

                            clazz = loader.loadClass(className);

                            for(Annotation anot : clazz.getAnnotations()) {

                                if(anot.annotationType().getName() == "mods.fullmetalalchemy.core.api.module.Module"
                                        || anot.annotationType().getName() == "Module") {

                                    FullmetalAlchemy.classesToLoad
                                            .add(clazz);

                                    if(FullmetalAlchemy
                                            .isModuleSpamAllowed()) {

                                        FullmetalAlchemy.logger
                                                .info(String
                                                        .format("Class %s has @Module tag and will be loaded :)",
                                                                clazz.getName()));
                                    }
                                } else {

                                    if(FullmetalAlchemy
                                            .isModuleSpamAllowed()) {

                                        FullmetalAlchemy.logger
                                                .info(String
                                                        .format("Class %s did not have @Module annotation",
                                                                clazz.getName()));
                                    }
                                }
                            }

                            if(FullmetalAlchemy.isModuleSpamAllowed()) {

                                FullmetalAlchemy.logger.info(className);
                            }
                        }
                    }
                }

                for(Class<?> clazz2 : FullmetalAlchemy.classesToLoad) {

                    for(Method method : clazz2.getDeclaredMethods()) {

                        for(Annotation anot : method
                                .getDeclaredAnnotations()) {

                            if(anot.annotationType().getName() == "mods.fullmetalalchemy.core.api.module.Module$Load"
                                    || anot.annotationType().getName() == "Load") {

                                method.invoke(null);

                                FullmetalAlchemy.loadedClasses.add(clazz2);
                            }
                        }
                    }
                }
            } catch(Exception e) {

                throw new RuntimeException(e);
            }

            for(Class<?> clazz3 : FullmetalAlchemy.loadedClasses) {

                if(ConfigSettings.moduleLoadingSpammyMode) {

                    FullmetalAlchemy.logger.info(String.format(
                            "Loaded class %s", clazz3.getSimpleName()));
                }
            }
        }
    }
}