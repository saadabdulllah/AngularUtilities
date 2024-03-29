package org.automation.angular;


import org.automation.angular.scripts.Script;
import org.openqa.selenium.*;
import org.openqa.selenium.internal.WrapsDriver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Javascript WebElement searcher.
 * @author Carlos Alexandro Becker (caarlos0@gmail.com)
 */
public final class JavaScriptBy extends By {
    /**
     * Script to use.
     */
    private final transient Script script;
    /**
     * Root element.
     *
     * FIXME this should not be like that.
     */
    public WebElement root;
    /**
     * Script arguments.
     */
    private final transient Object[] args;

    /**
     * Ctor.
     * @param scrpt Script to use.
//     * @param elem Root WebElement.
     * @param jsargs Script args.
     */
    public JavaScriptBy(
//        final WebElement elem,
        final Script scrpt,
        final Object... jsargs
    ) {
        super();
        this.script = scrpt;
//        this.root = elem;
        this.args = Arrays.copyOf(jsargs, jsargs.length);
    }

    @Override
    public WebElement findElement(final SearchContext context) {
        final List<WebElement> elements = this.findElements(context);
        WebElement result = null;
        if (!elements.isEmpty()) {
            result = elements.get(0);
        }
        return result;
    }

    @Override
    public List<WebElement> findElements(final SearchContext context) {
        final Object[] scriptargs = new Object[this.args.length + 1];
        scriptargs[0] = this.root;
        System.arraycopy(this.args, 0, scriptargs, 1, this.args.length);
        return this.elements(context, scriptargs);
    }

    private List<WebElement> elements(
        final SearchContext context,
        final Object[] jsargs
    ) {
        @SuppressWarnings("unchecked")
        List<WebElement> elements = (List<WebElement>) this.executor(context)
            .executeScript(this.script.content(), jsargs);
        if (elements == null) {
            elements = new ArrayList<>(0);
        }
        return elements;
    }

    private JavascriptExecutor executor(final SearchContext context) {
        JavascriptExecutor jsexecutor = null;
        if (!(context instanceof WebElement)) {
            jsexecutor = (JavascriptExecutor) context;
        }
        if (jsexecutor == null) {
            final WrapsDriver driver = (WrapsDriver) context;
            if (driver != null) {
                jsexecutor = (JavascriptExecutor) driver.getWrappedDriver();
            }
        }
        if (jsexecutor == null) {
            throw new WebDriverException(
                "Could not get an JavaScriptExecutor instance from the context."
            );
        }
        return jsexecutor;
    }
}
