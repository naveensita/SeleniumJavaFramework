package com.coforge.sita.iborders.utils;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.coforge.sita.iborders.testComponents.Listeners;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.Core;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.appender.AbstractAppender;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginElement;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;

import java.io.Serializable;

@Plugin(name = "ExtentLog4j2Appender", category = Core.CATEGORY_NAME, elementType = Appender.ELEMENT_TYPE, printObject = true)
public class ExtentLog4j2Appender extends AbstractAppender {

    protected ExtentLog4j2Appender(String name, Filter filter, Layout<? extends Serializable> layout, boolean ignoreExceptions) {
        super(name, filter, layout, ignoreExceptions, null);
    }

    @PluginFactory
    public static ExtentLog4j2Appender createAppender(
            @PluginAttribute("name") String name,
            @PluginElement("Filter") Filter filter,
            @PluginElement("Layout") Layout<? extends Serializable> layout) {
        return new ExtentLog4j2Appender(name, filter, layout, true);
    }

    @Override
    public void append(LogEvent event) {
        ExtentTest currentTest = Listeners.getExtentTest();
        if (currentTest == null) {
            // Ignore setup/suite logs emitted before a @Test method starts
            return;
        }

        String logMessage = event.getMessage().getFormattedMessage();
        Level level = event.getLevel();

        if (level == Level.INFO) {
            currentTest.log(Status.INFO, logMessage);
        } else if (level == Level.WARN) {
            currentTest.log(Status.WARNING, MarkupHelper.createLabel(logMessage, ExtentColor.ORANGE));
        } else if (level == Level.ERROR || level == Level.FATAL) {
            currentTest.log(Status.FAIL, MarkupHelper.createLabel(logMessage, ExtentColor.RED));
            if (event.getThrown() != null) {
                currentTest.fail(event.getThrown());
            }
        } else if (level == Level.DEBUG) {
            currentTest.log(Status.INFO, "[DEBUG] " + logMessage);
        }
    }
}