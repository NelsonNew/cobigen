package com.devonfw.cobigen.cli.commands;

import java.nio.file.Path;
import java.util.concurrent.Callable;

import com.devonfw.cobigen.cli.constants.MessagesConstants;
import com.devonfw.cobigen.cli.logger.CLILogger;

import ch.qos.logback.classic.Level;
import picocli.CommandLine.Option;

/**
 *
 */
public abstract class CommandCommons implements Callable<Integer> {

  /**
   * If this options is enabled, we will print also debug messages
   */
  @Option(names = { "--verbose", "-v" }, negatable = true, description = MessagesConstants.VERBOSE_OPTION_DESCRIPTION)
  boolean verbose;

  /**
   * Set location of templates project
   */
  @Option(names = { "--templates-project", "-tp" }, description = MessagesConstants.TEMPLATE_PATH_DESCRIPTION)
  Path templatesProject;

  /**
   * If this option is enabled, the old monolithic templates will be upgraded to the new template-set structure.
   */
  @Option(names = { "--upgrade-configuration" }, description = MessagesConstants.UPGRADE_CONFIGURATION_OPTION)
  boolean upgradeConfiguration;

  @Override
  public Integer call() throws Exception {

    if (this.verbose) {
      CLILogger.setLevel(Level.DEBUG);
    }

    return doAction();
  }

  public abstract Integer doAction() throws Exception;

}
